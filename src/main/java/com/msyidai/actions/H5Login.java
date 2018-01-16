package com.msyidai.actions;
/*
 * h5登陆---
 */
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class H5Login extends BasePage{
	private String url="http://h5.msyidai.com/h5/account/login";
	@FindBy(xpath="//input[@type='tel']")
	private WebElement usenameInput;
	@FindBy(xpath="//input[@type='password']")
	private WebElement passwordInput;
	@FindBy(xpath="//button[@type='button']")
	private WebElement submit;
	
	public  Invest  frontLogin(String username,String password)  {
		driver.get(url);
		driver.manage().deleteAllCookies();
		usenameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		submit.click();
		local.wait(1);
		return new Invest();
	}

}
