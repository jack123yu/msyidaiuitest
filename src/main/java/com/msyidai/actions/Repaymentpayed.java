package com.msyidai.actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.msyidai.tool.DataSeek;
import com.msyidai.tool.GetDataTime;


//还款入金
public class Repaymentpayed extends BasePage{
	
	@FindBy(linkText="财务管理")
	private WebElement financialManagement;
	@FindBy(linkText="还款入金申请")
	private WebElement repaymentMenu;
	@FindBy(id="repaymentPayed_loanId")
	private WebElement repaymentloanId;
	@FindBy(id="repaymentPayed_phaseNumber")
	private WebElement repaymentphaseNumber;
	@FindBy(id="projectId")
	private WebElement projectId;
	@FindBy(id="repaymentPayed_orderId")
	private WebElement repaymentorderId;
	@FindBy(id="repaymentPayed_amount")
	private WebElement repaymentamount;
	@FindBy(id="payBankName")
	private WebElement payBankName;
	@FindBy(id="payBankAccount")
	private WebElement payBankAccount;
	@FindBy(id="payBankAccountName")
	private WebElement payBankAccountName;
	@FindBy(id="business")//账号类型：对公对私
	private WebElement business;
	@FindBy(id="receiveDate")
	private WebElement receiveDate;
	@FindBy(className="apply_btn")
	private WebElement apply_btn;
	
	public  BackLogin returnLoanAmount(String loanId) {
		String[][] test=DataSeek.getLoanPlan(loanId);
		
		for (int j = 0; j < test[1].length; j++) {
			if(test[0][j]!=null){
				local.wait(1);
				local.clickleftmenu(financialManagement, repaymentMenu);
				repaymentloanId.sendKeys(test[0][j]);
				repaymentphaseNumber.sendKeys(test[1][j]);
				projectId.sendKeys("2676271");
				repaymentorderId.sendKeys("1222");
				repaymentamount.sendKeys(test[2][j]);
				payBankName.sendKeys("中国民生银行");
				payBankAccount.sendKeys("6226220506989926");
				payBankAccountName.sendKeys("李四");
				local.select(business, "对私");
				js.executeScript("document.getElementById('receiveDate').readOnly=false");
				js.executeScript("document.getElementsByClassName('JsDatePickBox')[0].setAttribute('id','testId')");
			    js.executeScript("$('div').removeClass('JsDatePickBox')");
			    receiveDate.click();
			    receiveDate.sendKeys(GetDataTime.getDateTime());
			    apply_btn.click();
			    local.acceptAlert();
			    local.defaultframe();
				
			}else {
				return null;
			}
			
	}
		return new BackLogin();
	}

}
