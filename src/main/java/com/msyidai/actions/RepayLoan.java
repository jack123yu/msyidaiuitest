package com.msyidai.actions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.msyidai.tool.DataSeek;

/*
 * 还款
 */
public class RepayLoan extends BasePage {
	@FindBy(linkText="财务管理")
	private WebElement financialManagement;
	@FindBy(linkText="还款管理")
	private WebElement repaykMenu;
	@FindBy(xpath="//input[@value='还款资金准备']")
	private WebElement repayPrepare;
	@FindBy(id="btn_repay")
	private WebElement btn_repay;
	@FindBy(xpath="//tr[2]/td")
	private WebElement repayResults;
	@FindBy(id="searchEndDate")
	private WebElement searchEndDate;
	@FindBy(id="btn_searchSubmit")
	private WebElement searchSubmit;


	private WebElement repayIdsElement;
	private void setrepayIdsElement (String loanId) {
		repayIdsElement=driver.findElement(By.xpath("//td[text()='"+loanId+"']/../td/input[@name='repayIds']"));
	}
	
	
	
	public BonusReturn repayLoan(String loanId ){
		
	    List<String> loanDueDate=new ArrayList<String>();
	    String sql="UPDATE loan set BorrowBearingEndDate=now() WHERE loanId="+loanId;//���±��ֹϢ��Ϊ��ǰʱ��
		DataSeek.updateParameter(sql);
		local.wait(1);
		if(DataSeek.getLoanAssertRepayType(loanId)==2){
			local.clickleftmenu(financialManagement, repaykMenu);
			setrepayIdsElement(loanId);
			repayIdsElement.click();
			local.wait(0.5);
			repayPrepare.click();
			local.acceptAlert();
			local.acceptAlert();
			local.wait(0.5);
			btn_repay.click();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
			local.acceptAlert();
			System.out.println(repayResults.getText());
			local.defaultframe();
		}else {
			loanDueDate=DataSeek.getLoanDueDate(loanId);
			for (String string : loanDueDate) {
				local.clickleftmenu(financialManagement, repaykMenu);
				js.executeScript("document.getElementById('searchEndDate').readOnly=false");
				js.executeScript("document.getElementsByClassName('JsDatePickBox')[0].setAttribute('id','testId')");
			    js.executeScript("$('div').removeClass('JsDatePickBox')");
			    searchEndDate.clear();
			    searchEndDate.sendKeys(string);
			    searchSubmit.click();
			    repayIdsElement.click();
			    local.wait(1);
			    repayPrepare.click();
			    local.acceptAlert();
			  
			   Alert alert2= driver.switchTo().alert();
			   String title=alert2.getText();
			   if (title.contains("不足")) {
				   System.out.println(alert2.getText());
				
				   alert2.accept();
				
				   local.acceptAlert();
				   local.pagedown();
				   btn_repay.click();
				   local.acceptAlert();
				   
			   }else {
				   alert2.accept();
				   btn_repay.click();
				   local.acceptAlert();
			  }
			   System.out.println(repayResults.getText());
			   local.defaultframe();
			}
			
		}
		
		
	 
	    
	    
	    
	 return new BonusReturn();   
	    
	}
	
	

	
	
    
}
