package com.msyidai.actions;
//开标
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpenLoan extends BasePage {
	
	@FindBy(linkText="交易管理")
	private WebElement transmenageElement;
	@FindBy(linkText="开标")
	private WebElement openLoanElement;
	@FindBy(xpath="//form[@id='openloanAll']/table[@class='table_border']/tbody/tr/td[@ align='center']/input[@value='批量开标']")
	private WebElement openLoan;
	private WebElement loanElement;
	private void setLoanElement(){
		loanElement=driver.findElement(By.xpath("//tr[@id='"+LoanInput.loanId+"']/td/input[@value='"+LoanInput.loanId+"']"));
	}
	public  void openObject() {
		local.clickleftmenu(transmenageElement, openLoanElement);
		setLoanElement();
		loanElement.click();
		openLoan.click();
		local.acceptAlert();
		logger.info(local.getAlertText());
	}

}
