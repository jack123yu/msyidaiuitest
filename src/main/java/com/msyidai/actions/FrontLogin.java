package com.msyidai.actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FrontLogin extends BasePage {
	private String url="https://passport.msyidai.com/login?jump=http://www.msyidai.com/index";
	@FindBy(id="userName")
	private WebElement usenameInput;
	@FindBy(id="password")
	private WebElement passwordInput;
	@FindBy(id="submit")
	private WebElement submit;
	
	public  FrontHomePage  frontLogin(String username,String password)  {
		driver.get(url);
		driver.manage().deleteAllCookies();
		usenameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		submit.click();
		local.wait(1);
		return new FrontHomePage();
	}
	
}
