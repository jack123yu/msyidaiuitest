package com.msyidai.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/*奖金返还*/

public class BonusReturn extends BasePage{
	@FindBy(linkText="财务管理")
	private WebElement financialManagement;
	@FindBy(linkText="奖金返还")
	private WebElement bonusReturnMenu;
	@FindBy(id="btn_trans")
	private WebElement prepareButton;
	@FindBy(id="btn_repay")
	private WebElement repayButton;
	@FindBy(xpath="//tbody/tr[2]/td")
	private WebElement resultElement;
	
	private WebElement loanIdButton;
	private void setloanIdButton(String loanId) {
		loanIdButton=driver.findElement(By.xpath("//td[text()='"+loanId+"']/../td/input[@name='repayIds']"));
		
	}
	private String reslut;
	public String getBonusReturnReslut() {
		return this.reslut;
	}
	
	public void bonusReturn(String loanId) {
		local.wait(2);
		local.clickleftmenu(financialManagement, bonusReturnMenu);
		setloanIdButton(loanId);
		loanIdButton.click();
		prepareButton.click();
		logger.info(local.getAlertText());
		local.acceptAlert();
		setloanIdButton(loanId);
		loanIdButton.click();
		repayButton.click();
		local.wait(1);
		logger.info(local.getAlertText());
		reslut=resultElement.getText();
		logger.info(reslut);
		
		
		
	}

	
}
