package com.msyidai.actions;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.msyidai.tool.DataSeek;
import com.msyidai.tool.GetDataTime;
import com.msyidai.tool.GetLoan;
import com.mysql.fabric.xmlrpc.base.Data;

public class LoanInput extends BasePage {
	public static String loanId;
	/*
	 * 共有元素
	 */
	public String resultLoanInput;
	@FindBy(linkText = "交易管理")
	private WebElement transmenageElement;
	@FindBy(linkText = "标的录入")
	private WebElement loanInput;
	@FindBy(id = "txt_assetsId")
	private WebElement assetsId;
	@FindBy(id = "txt_orderIndex")
	private WebElement loanOrderIndex;
	@FindBy(id = "txt_title")
	private WebElement loanTitle;
	@FindBy(id = "txt_amount")
	private WebElement loanAmount;
	@FindBy(id = "txt_rate")
	private WebElement loanRate;
	@FindBy(id = "btn_save")
	private WebElement save;

	/*
	 * 企业融资标的轮元素
	 */

	@FindBy(linkText = "企业融资")
	private WebElement companyLoan;
	@FindBy(id = "txt_openTime")
	private WebElement loanOpenTime;
	@FindBy(xpath = "//iframe")
	private WebElement timeFrame;
	@FindBy(id = "dpOkInput")
	private WebElement okInput;
	@FindBy(id = "txt_openEndTime")
	private WebElement loanEndTime;
	@FindBy(id = "txt_transferdays")
	private WebElement transferday;
	@FindBy(id = "sel_repayType")
	private WebElement repayType;
	@FindBy(xpath = "//div[@id='btngrp_loan']/input[@class='apply_btn']")
	private WebElement next;
	
	/*
	 * 个人融资标的元素
	 */
	String filepath="C:\\Users\\Public\\Pictures\\Sample Pictures\\12.jpg";
	@FindBy(linkText = "个人融资")
	private WebElement personLoan;
	@FindBy(id="txt_termCount")
	private WebElement termCount;//还款期限
	@FindBy(id="txt_paymentfactor")
	private WebElement paymentfactor;//还款描述
	@FindBy(id="txt_agencybank")
	private WebElement agencybank;//融资方代理机构
	@FindBy(id="txt_description")
	private WebElement description;//借款描述
	@FindBy(id="tempId")
	private WebElement tempId;//投资人协议模板
	@FindBy(id="txt_loanstatement")
	private WebElement loanstatement;//项目描述
	@FindBy(id="btn_nextStep")
	private WebElement nextStep;
	@FindBy(id="per_idCardNo")
	private WebElement idCardNo;
	@FindBy(id="per_realName")
	private WebElement realName;
	@FindBy(id="per_mobile")
	private WebElement mobile;
	@FindBy(id="per_birth")
	private WebElement birth;
	@FindBy(xpath="//input[@id='per_birth']/../div//div[@class='jsDatePickCloseButton']")
	private WebElement CloseButton;
	@FindBy(id="per_homeTown")
	private WebElement homeTown;
	@FindBy(id="per_residence")
	private WebElement residence;
	@FindBy(id="per_accountOpeningBank_gr")
	private WebElement accountOpeningBank;
	@FindBy(xpath="//ul[@id='resultBankNameGr']/li[5]")
	private WebElement resultBankNameGr;
	@FindBy(id="per_accountName")
	private WebElement accountName;
	@FindBy(id="per_accountNo")
	private WebElement accountNo;
	@FindBy(id="txt_per_position")
	private WebElement position;
	@FindBy(id="txt_per_monthlyIncome")
	private WebElement monthlyIncome;
	@FindBy(id="per_work")
	private WebElement work;
	@FindBy(id="txt_per_companyIndustry")
	private WebElement companyIndustry;
	@FindBy(id="txt_per_companyScopey")
	private WebElement companyScopey;
	@FindBy(id="txt_per_companyWorkYears")
	private WebElement companyWorkYears;
	@FindBy(xpath="//div[@id='fileupload0']//input[@id='files0']")
	private WebElement files0;
	@FindBy(xpath="//div[@id='fileupload0']//button[@type='submit']")
	private WebElement submitFile0;
	@FindBy(id="files1")
	private WebElement files1;
	@FindBy(xpath="//div[@id='fileupload1']//button[@type='submit']")
	private WebElement submitFile1;
	@FindBy(id="files2")
	private WebElement files2;
	@FindBy(xpath="//div[@id='fileupload2']//button[@type='submit']")
	private WebElement submitFile2;
	@FindBy(id="files3")
	private WebElement files3;
	@FindBy(xpath="//div[@id='fileupload3']//button[@type='submit']")
	private WebElement submitFile3;
	/*
	 * 出国金融标的元素
	 */
	@FindBy(linkText = "出国金融")
	private WebElement foreignFinancial;
	// private Logger logger=Logger.getLogger(LoanInput.class);

	public FirstCheck companyLoanInput(String assetId, String amount, String rate, String transType) {

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
		resultLoanInput = local.getAlertText();
		logger.info(resultLoanInput + "(" + transType + ")");
		local.defaultframe();
		loanId = GetLoan.getLoanId();// 查询录入标的id 供后续操作使用
		return new FirstCheck();
	}

	public FirstCheck personLoanInput(String assetId, String amount, String rate) {

		local.clickleftmenu(transmenageElement, loanInput);
		personLoan.click();
		assetsId.sendKeys(assetId);
		loanOrderIndex.sendKeys("12222");
		loanTitle.sendKeys("个人融资");
		loanAmount.sendKeys(amount);
		termCount.sendKeys("30");
		loanRate.sendKeys(rate);
		paymentfactor.sendKeys("个人标测试");
		agencybank.sendKeys("测试测试");
		description.sendKeys("个人融资标的借款描述");
		local.select(tempId, "测试模板1");
		loanstatement.sendKeys("个人融资项目描述");
		nextStep.click();
		idCardNo.sendKeys("411023199106041015");
		realName.sendKeys("卢继超");
		mobile.sendKeys("15637707077");
		birth.sendKeys("1991-08-07");
		CloseButton.click();
		js.executeScript("document.getElementById('per_homeTown').removeAttribute('readonly')");
		homeTown.sendKeys("北京市");
		js.executeScript("document.getElementById('per_residence').removeAttribute('readonly')");
		residence.sendKeys("北京市");
		accountOpeningBank.sendKeys("中国");
		local.wait(1);
		resultBankNameGr.click();
		accountName.sendKeys("卢继超");
		accountNo.sendKeys("6226220506989926");
		nextStep.click();
		nextStep.click();
		position.sendKeys("总统");
		local.select(monthlyIncome, "50000元以上");
		js.executeScript("document.getElementById('per_work').removeAttribute('readonly')");
		work.sendKeys("北京市");
		local.select(companyIndustry, "政府机关");
		local.select(companyScopey, "500人以上");
		local.select(companyWorkYears, "5年以上");
		nextStep.click();
		nextStep.click();
		js.executeScript("scrollTo(3000,0)");
		local.wait(1);
		files0.sendKeys(filepath);
		submitFile0.click();
		local.wait(1);
		files1.sendKeys(filepath);
		submitFile1.click();
		local.wait(1);
		files2.sendKeys(filepath);
		submitFile2.click();
		local.wait(1);
		files3.sendKeys(filepath);
		submitFile3.click();
		local.wait(1);
		save.click();
		resultLoanInput=local.getAlertText();
		logger.info(resultLoanInput);
		loanId = GetLoan.getLoanId();
		local.defaultframe();
		DataSeek.updateParameter("UPDATE  loan set  openTime='"+GetDataTime.getCurrentTime()+"' WHERE loanId="+loanId);
		return new FirstCheck();
	}
	public FirstCheck financialLoanInput(String assetId, String amount, String rate) {

		local.clickleftmenu(transmenageElement, loanInput);
		foreignFinancial.click();
		assetsId.sendKeys(assetId);
		loanOrderIndex.sendKeys("12222");
		loanTitle.sendKeys("出国金融标的");
		loanAmount.sendKeys(amount);
		termCount.sendKeys("30");
		loanRate.sendKeys(rate);
		paymentfactor.sendKeys("出国金融标测试");
		agencybank.sendKeys("测试测试");
		description.sendKeys("出国金融融资标的借款描述");
		local.select(tempId, "测试模板1");
		loanstatement.sendKeys("出国金额项目描述");
		nextStep.click();
		idCardNo.sendKeys("411023199106041015");
		realName.sendKeys("卢继超");
		mobile.sendKeys("15637707077");
		birth.sendKeys("1991-08-07");
		CloseButton.click();
		js.executeScript("document.getElementById('per_homeTown').removeAttribute('readonly')");
		homeTown.sendKeys("北京市");
		js.executeScript("document.getElementById('per_residence').removeAttribute('readonly')");
		residence.sendKeys("北京市");
		accountOpeningBank.sendKeys("中国");
		local.wait(1);
		resultBankNameGr.click();
		accountName.sendKeys("卢继超");
		accountNo.sendKeys("6226220506989926");
		nextStep.click();
		nextStep.click();
		position.sendKeys("总统");
		local.select(monthlyIncome, "50000元以上");
		js.executeScript("document.getElementById('per_work').removeAttribute('readonly')");
		work.sendKeys("北京市");
		local.select(companyIndustry, "政府机关");
		local.select(companyScopey, "500人以上");
		local.select(companyWorkYears, "5年以上");
		nextStep.click();
		nextStep.click();
		
		save.click();
		resultLoanInput=local.getAlertText();
		logger.info(resultLoanInput);
		local.defaultframe();
		loanId = GetLoan.getLoanId();
		return new FirstCheck();
	}

}
