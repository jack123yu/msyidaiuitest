package com.msyidai.actions;
/*
 * 摇奖加息页面，投资后进行摇奖，一键摇奖
 */

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShakeAwards extends BasePage{
	@FindBy(className="getAllBtn")
	private WebElement getAllButton;
	@FindBy(className="aui_state_highlight")
	private WebElement sureButtonElement;
	
	public  void  investShakeAwards(String loanId) {
		driver.get("http://www.msyidai.com/toPrizePage?loanId="+loanId);
		getAllButton.click();
		local.wait(1);
		sureButtonElement.click();
		
		
		
	}

}
