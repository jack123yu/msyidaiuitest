package com.msyidai.actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.msyidai.config.BaseConfig;
import com.msyidai.tool.FileTool;
import com.msyidai.tool.GetLoan;
import com.msyidai.utils.MyException;


public class Invest extends BasePage{
	//投资一般标的元素
	@FindBy(id="myinvest_2")
	public WebElement investAmount;
	@FindBy(id="t_i_submit")
	public WebElement submit;
	@FindBy(xpath="//dd[@name='quickChargePhoneCode' and @style='display: block;']//a[@id='quickChargePhoneValidate']")
	private WebElement sendCodeElement;
	@FindBy(id="quickChargePhoneVCode")
	private WebElement inputCodeElement;
	@FindBy(id="zfmm")//投资一般标的与转让标的均用这个元素
	public WebElement paypassword;
	//与购买转让标的公用一个元素
	@FindBy(id="investButton")
	public WebElement investButton;
	//投资转让标的页面元素
	@FindBy(linkText="立即投资")
	public WebElement transLoanInvest;
	//同意协议按钮
	@FindBy(id="agreecheckbox")
	public WebElement agreecheckbox;
	@FindBy(id="message")//错误信息显示
	public WebElement errorMenage;
	@FindBy(xpath="//h2")
	private WebElement successElement;
	/*
	 * h5投资元素
	 */
	@FindBy(xpath="//div[@ng-click='doInvest(loanDetail)']")
	private WebElement doInvest;
	@FindBy(xpath="//input[@ng-model='investAmount']")
	private WebElement h5investAmount;
	@FindBy(xpath="//button[@ng-click='btnNext()']")
	private WebElement btnNext;
	@FindBy(xpath="//input[@type='password']")
	private WebElement h5PayPassword;
	@FindBy(xpath="//button[@type='submit']")
	private WebElement submitbutton;
	@FindBy(xpath="//div[@class='success-logo']/div")
	private WebElement result;

	public  FrontHomePage  investLoan(String amount,String loanId,String password) {
		
	    local.to("https://www.msyidai.com/loanDetail?loanId="+loanId);
	    investAmount.clear();
		investAmount.sendKeys(amount);
		submit.click();
		if (local.IsexistWelement(sendCodeElement)) {
			local.wait(1);
			sendCodeElement.click();
			if(!errorMenage.getText().equals("")){
				logger.error(errorMenage.getText());
				FileTool.getScreenshots(driver, BaseConfig.screenshotsPath, "investError");
			}
			inputCodeElement.clear();
			inputCodeElement.sendKeys("111111");
		}
		paypassword.clear();
		paypassword.sendKeys(password);
		local.wait(1);
		investButton.click();
		local.wait(1);
		String successTitle=successElement.getText();
		
		if(successTitle.contains("投资成功")){
			
			logger.info("标的"+loanId+successTitle);
		}else {
			logger.error(errorMenage.getText());
			FileTool.getScreenshots(driver, BaseConfig.screenshotsPath, "investError");
			
		}
		return new FrontHomePage();
		} 
		//购买转让标的
		public FrontHomePage investTransLoan(String loanId,String password){
			String transId=GetLoan.getLoanTransId(loanId);
			local.to("https://www.msyidai.com/toTransDetailPage?transId="+transId);
			transLoanInvest.click();
			if (local.IsexistWelement(sendCodeElement)) {
				sendCodeElement.click();
				inputCodeElement.clear();
				inputCodeElement.sendKeys("111111");
			}
			paypassword.clear();
			paypassword.sendKeys(password);
			agreecheckbox.click();
			investAmount.click();
			return new FrontHomePage();
		} 
		public ShakeAwards h5Invest(String loanId,String amount,String password) {
		    local.to("http://h5.msyidai.com/h5/investment/details?loanId="+loanId);
		    doInvest.click();
		    h5investAmount.sendKeys(amount);
		    local.wait(1);
		    btnNext.click();
		    h5PayPassword.sendKeys(password);
		    submitbutton.click();
		    logger.info( result.getText());
		    return new ShakeAwards();
		}




}
