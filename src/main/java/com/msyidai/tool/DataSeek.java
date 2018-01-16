package com.msyidai.tool;
/*数据查询

*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.msyidai.config.BaseConfig;



//根据sql 查询库中某个字段
public class DataSeek {
    
	public static String findData(String sql,String parameter){
		 Connection  con;
		 String result = null;
	     try {              
		     Class.forName("com.mysql.jdbc.Driver");//class.forname加载数据库驱动 
		 } catch (ClassNotFoundException e) {
				System.out.println("数据库加载失败，请确认Java数据库驱动是否缺失！");
				
			}
			   Statement stmt = null;
			try {
				con = DriverManager.getConnection(BaseConfig.url,BaseConfig.user,BaseConfig.password);
				stmt = con.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				 System.out.println("数据库链接失败，请查询数据库密码是否正确");
			}
			try {
			     ResultSet rs=stmt.executeQuery(sql);
				 while(rs.next()){ 
					 result+=rs.getString(parameter);
				   }  				
			} catch (Exception e) {
				// TODO: handle exception
			}
			return result;	   
	}
	
	
/*
 * 根据标的Id 查询标的还款入金时，商户的流水号，用于三方审核时定位按钮元素
 *  通过标的Id查询标的的三方商户流水号，从而定位三方入金审核页面操作按钮                
 *   "SELECT substring_index((SELECT substring_index((SELECT sendReportContent FROM platform_send_receive_message psrm "+
 *   "LEFT JOIN repayment_payed rp ON psrm.orderId = rp.merOrderId "+
 *     "WHERE rp.loanId = '"+loanId+"'),'</bussflowno>',1 )),'<bussflowno>',-1)  as orderId"
 */
	public static List<String> merchantNums(String loanId) {
	    String sql="SELECT sendReportContent FROM platform_send_receive_message psrm "+
                "LEFT JOIN repayment_payed rp ON psrm.orderId = rp.merOrderId "+
                "WHERE rp.loanId = '"+loanId+"'";
		 Connection  con;
		
	     List<String> merchantNums=new ArrayList<String>() ;//定义String数组存放还款入金三方的商户流水号
		
	 
			   try {
				   Class.forName("com.mysql.jdbc.Driver");//调用Class.forName()方法加载驱动程序
				  
			} catch (ClassNotFoundException e) {
				System.out.println("找不到MySQL驱动,请确认jar包是否导入!");
				
			}
			   Statement stmt =null;
			try {
				con = DriverManager.getConnection(BaseConfig.url,BaseConfig.user,BaseConfig.password);//调用DriverManager对象的getConnection()方法，获得一个Connection对象
				  
				   stmt = con.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				 System.out.println("数据库链接失败,请确认数据库密码是否正确！");
			}
			   
				  
				   ResultSet rs = null;
				try {
					rs = stmt.executeQuery(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("sql :"+sql+"执行失败！");
				}
				  // result=rs.getString("orderId");
				   try {
					while(rs.next()){
						  // result=rs.getString("orderId");
						   String tempString=rs.getString("sendReportContent");
						  // System.out.println(IsJsonData.iIsJsonData(tempString));
						   //如果有"mac="标的为卡易还标的取xml内容给orderId
						   if(tempString.contains("mac=")){
							//   System.out.println(tempString);
							
							 tempString=tempString.substring(tempString.indexOf("<"));
							//  System.out.println(tempString);
							 merchantNums.add(tempString.substring(115, 147));
						   }else{
							   merchantNums.add(tempString.substring(121, 153));
						   }
   }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("sql :"+sql+"查询异常");
				}

			return merchantNums;
			   
	}
	
		
		public static String[][] getLoanPlan(String loanId){
			String sql="SELECT * FROM loan_phase WHERE loanId ="+ loanId+ "";
			String[][] loanPlan=new String[3][20];
			
			
			 Connection  con;
			
			
		 
				   try {
					   Class.forName("com.mysql.jdbc.Driver");//调用Class.forName()方法加载驱动程序
					
				} catch (ClassNotFoundException e) {
					System.out.println("数据库驱动失败,请确认Java数据库驱动jar包是否添加！");
					
				}
				   try {
					  
					   con = DriverManager.getConnection(BaseConfig.url,BaseConfig.user,BaseConfig.password);//调用DriverManager对象的getConnection()方法，获得一个Connection对象
					   Statement stmt = con.createStatement(); 
					  
					   ResultSet rs=stmt.executeQuery(sql);
					   for(int i=0;rs.next();){
								   loanPlan[0][i]=rs.getString("loanId");
								   loanPlan[1][i]=rs.getString("phaseNumber");
								   loanPlan[2][i]=rs.getString("plannedTermAmount");
								  i++;
								   
						
						}
					  
					  
					   
					  				
				} catch (Exception e) {
					// TODO: handle exception
				}
				return loanPlan;
		}
		/*
		 * 查询标的的还款类型，还款时根据还款类型判断查询还款日期的方式，
		 */
		public static  Integer getLoanAssertRepayType(String loanId){
			int repayType = 0;
			String sql="SELECT fi.repayType as repayType from loan l INNER JOIN financial_assets fi ON fi.assetsId=l.assetsId WHERE l.loanId="+loanId;
		
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("数据库驱动失败,请确认Java数据库驱动jar包是否添加！");
			}
			Connection connection = null;
			try {
				connection = DriverManager.getConnection(BaseConfig.url,BaseConfig.user,BaseConfig.password);
			} catch (SQLException e) {
				System.out.println("数据库链接失败，请确认数据库密码是否正确！");
			}
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(sql);
			} catch (SQLException e1) {
				
			}
			ResultSet resultSet = null;
			try {
				resultSet = preparedStatement.executeQuery();
			} catch (SQLException e1) {
				System.out.println("sql执行失败");
			}
			try {
				while (resultSet.next()) {
					repayType=resultSet.getInt("repayType");
				}
			} catch (SQLException e) {
			System.out.println("数据读取失败");
			}
			
			
			
			return repayType;
		}
		public static List<String> getLoanDueDate(String loanId){
			String sql="SELECT dueDate from loan_phase WHERE loanId="+loanId;
			Connection connection=null;
			 List<String> loanDueDate=new ArrayList<String>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("数据库驱动失败,请确认Java数据库驱动jar包是否添加！");
			}
			
			
			try {
				connection=DriverManager.getConnection(BaseConfig.url,BaseConfig.user,BaseConfig.password);
				connection.createStatement();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 PreparedStatement psmt = null;
			try {
				psmt = connection.prepareStatement(sql);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				ResultSet resultSet1= psmt.executeQuery(sql);
				while (resultSet1.next()) {
					loanDueDate.add(resultSet1.getString("dueDate").substring(0,10));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
			return loanDueDate;
			
		}
		
		/*更新数据库中每一个字段*/
		public static void  updateParameter(String sql){
			 Connection  con = null;
				   try {
					   Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					System.out.println("数据库驱动失败,请确认Java数据库驱动jar包是否添加！");
					
				}
				
				try {
					con = DriverManager.getConnection(BaseConfig.url,BaseConfig.user,BaseConfig.password);
					con.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("数据库链接失败，请确认数据库密码是否正确！");
				}
					 PreparedStatement psmt;
					try {
						psmt = con.prepareStatement(sql);
					
						 psmt.execute(); 
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("sql执行失败");
					}   
		}



	 
	
}
