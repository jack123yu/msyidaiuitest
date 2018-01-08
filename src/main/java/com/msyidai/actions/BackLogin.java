package com.msyidai.actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.msyidai.config.BaseConfig;

public class BackLogin  extends BasePage{
	@FindBy(id="username")
	private WebElement usenameElement;
	@FindBy(id="password")
	private WebElement pasdWebElement;
	@FindBy(id="login")
	private WebElement loginElement;
	
	public LoanInput loanInput=new LoanInput();
	public RepaymentSearch repaymentSearch=new RepaymentSearch();
	public Releaseloan releaseloan=new Releaseloan();
	public RepaymentCheck repaymentCheck=new RepaymentCheck();
	RepayLoan repayLoan=new RepayLoan();
	
	
	public BackLogin backLogin(String username,String password) {
		local.to(BaseConfig.IP+"/admin/loginuser.jsp");
		driver.manage().deleteAllCookies();
		usenameElement.sendKeys(username);
		pasdWebElement.sendKeys(password);
		loginElement.click();
		return this;
	}
	

}

