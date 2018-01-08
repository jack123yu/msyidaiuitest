package com.msyidai.actions;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CompanyLoanInput extends BasePage {
	String mainFramename="main";
	String leftFramename="leftFrame";
	@FindBy(id="menu_100")
	public WebElement loanInput;
	@FindBy(linkText="企业标的录入")
	public WebElement companyLoan;
	@FindBy(id="txt_assetsId")
	public WebElement assetsId;
	@FindBy(id="txt_orderIndex")
	public WebElement loanOrderIndex;
	@FindBy(id="txt_title")
	public WebElement loanTitle;
	@FindBy(id="txt_amount")
	public WebElement loanAmount;
	@FindBy(id="txt_rate")
	public WebElement loanRate;
	@FindBy(id="txt_openTime")
	public WebElement loanOpenTime;
	@FindBy(xpath="//iframe")
	public WebElement timeFrame;
	@FindBy(id="dpOkInput")
	public WebElement okInput;
	@FindBy(id="txt_openEndTime")
	public WebElement loanEndTime;
	@FindBy(id="txt_transferday")
	public WebElement transferday;
	@FindBy(id="sel_repayType")
	public WebElement repayType;
	@FindBy(xpath="//div[@id='btngrp_loan']/input[@class='apply_btn']")
	public WebElement next;
	@FindBy(id="btn_save")
	public WebElement save;
	
	public  void companyLoanInput(String assetId, String amount,String rate) throws Exception{
		
	
		
		local.switchframe(leftFramename);
		local.wait(1);
		loanInput.click();
		local.defaultframe();
		local.switchframe(mainFramename);
		companyLoan.click();
		assetsId.sendKeys(assetId);
		loanOrderIndex.sendKeys("12222");
		loanTitle.sendKeys("企业标的融资");
		loanAmount.sendKeys(amount);
		loanRate.sendKeys(rate);
		loanOpenTime.click();
		local.switchframe(timeFrame);//切换到时间控件frame
	    okInput.click();	    
	    local.defaultframe();
	    local.switchframe(mainFramename);
	    loanEndTime.click();
	    local.switchframe(timeFrame);//切换到时间控件frame
	    okInput.click();	
	   
	    local.defaultframe();
	    local.switchframe(mainFramename);
	    local.select(repayType, "等额本息");
	    next.click();
	    try {
			if(local.getAlertText().equals("��ת�ó��������ܴ��ڱ������")){
				System.out.println(local.getAlertText());
				return;
			}else if(local.getAlertText().equals("���ڱ겻��ת��!")){
				System.out.println("-----"+local.getAlertText()+"�رյ���¼���ת�ñ��-------");
				 transferday.click();
				 next.click();
			}			
		} catch (NoAlertPresentException e) {
			// TODO Auto-generated catch block
			
		}
	    save.click();
	    local.acceptAlert();
	    driver.switchTo().defaultContent();
	 	
	}
}

