package com.msyidai.actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.msyidai.tool.GetLoan;

public class LoanInput extends BasePage {
	public static String loanId="6247";
    public  String resultLoanInput;
	@FindBy(linkText="交易管理")
	private WebElement transmenageElement;
	@FindBy(linkText="标的录入")
	public WebElement loanInput;
	@FindBy(linkText="企业融资")
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
	@FindBy(id="txt_transferdays")
	public WebElement transferday;
	@FindBy(id="sel_repayType")
	public WebElement repayType;
	@FindBy(xpath="//div[@id='btngrp_loan']/input[@class='apply_btn']")
	public WebElement next;
	@FindBy(id="btn_save")
	public WebElement save;
	
	//private Logger logger=Logger.getLogger(LoanInput.class);
	
	
	public  FirstCheck companyLoanInput(String assetId, String amount,String rate ,String transType) {
        
		local.clickleftmenu(transmenageElement, loanInput);
		companyLoan.click();
		assetsId.sendKeys(assetId);
		loanOrderIndex.sendKeys("12222");
		loanTitle.sendKeys("企业标的融资");
		loanAmount.sendKeys(amount);
		loanRate.sendKeys(rate);
		loanOpenTime.click();
		local.setTime(timeFrame, okInput);	    
	    loanEndTime.click();
	    local.setTime(timeFrame, okInput);	   
	    local.select(repayType, "到期还本付息");
	    if (!transType.equals("转让")) {
	    	transferday.click();
		}
	    next.click();
	    save.click();
	    resultLoanInput=local.getAlertText();
	    logger.info(resultLoanInput+"("+transType+")");
	    local.defaultframe();
	    loanId=GetLoan.getLoanId();//查询录入标的id 供后续操作使用
	 	return new FirstCheck();
	}

}
