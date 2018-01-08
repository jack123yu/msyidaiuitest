package com.msyidai.actions;
//还款入金审核
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.msyidai.tool.DataSeek;


public class RepaymentCheck extends BasePage {
	@FindBy(linkText="财务管理")
	private WebElement financialManagement;
	@FindBy(linkText="还款入金审核")
	private WebElement repaymentCheckMenu;
    @FindBy(id="btn_submit")
    private WebElement btn_submit;
	private WebElement passElement;
	private void  setPassElement(String loanId) {
		passElement=driver.findElement(By.xpath("//td[text()='"+loanId+"']/..//td/input[@value='通过']"));
	}
	public  LoanUmbpay repayLaonCheck(String loanId) {
		String[][] test=DataSeek.getLoanPlan(loanId);
		local.wait(1);
		for (int j = 0; j < test[1].length; j++) {
			local.clickleftmenu(financialManagement, repaymentCheckMenu);
			if(test[0][j]!=null){
				setPassElement(loanId);
				passElement.click();
				btn_submit.click();
				local.defaultframe();
			}else {
				return new LoanUmbpay();
			}
			
			
		}
       return new LoanUmbpay();
	}

}
