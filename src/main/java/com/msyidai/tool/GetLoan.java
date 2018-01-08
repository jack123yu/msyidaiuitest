package com.msyidai.tool;

public class GetLoan {
	public static  String getLoanId(){
		
	    String result;
		String sql="SELECT loanId FROM loan  ORDER BY loanId desc LIMIT 1";//status=180
		result =DataSeek.findData(sql,"loanId");
		
		
		return result.substring(4);
	}
public static String getLoanTitle(String loanid){
		
	    String result;
		String sql="SELECT title FROM loan WHERE loanId="+loanid;
		result =DataSeek.findData(sql,"title");
		
		
		return result.substring(4);
	}
       //查询标准的transId以供购买转让标的使用
    public static  String getLoanTransId(String loanId){
	    String sql ="SELECT transId from trans_loan  WHERE loanId="+loanId+" AND `status`=300";
        String transId=DataSeek.findData(sql, "transId");
        return transId;
   }
   

}
