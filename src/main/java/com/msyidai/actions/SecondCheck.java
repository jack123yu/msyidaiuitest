package com.msyidai.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.msyidai.tool.GetLoan;


public class SecondCheck extends BasePage  {
	
	@FindBy(linkText="交易管理")
	private WebElement transmenageElement;
	@FindBy(linkText="复审")
	private WebElement secondCheck;
	@FindBy(xpath="//table[@id='detailAudit']/tbody/tr/td[@align='center']/input[@value='通过']")
	private WebElement checkpass;
	@FindBy(xpath="//input[@id='checked_button']")
	private WebElement confirmPass;
    private WebElement loanTitleElement;
	private void setloanTitlElement() {
		loanTitleElement=driver.findElement(By.xpath("//a[text()='"+GetLoan.getLoanTitle(LoanInput.loanId)+"']"));
		
	}
	public OpenLoan  secondChek() {
		
		local.clickleftmenu(transmenageElement, secondCheck);
		setloanTitlElement();
	    loanTitleElement.click();
		checkpass.click();
		confirmPass.click();
		local.acceptAlert();
		logger.info("标的"+LoanInput.loanId+"复审通过");
		local.defaultframe();
		return new OpenLoan();
	}

}
