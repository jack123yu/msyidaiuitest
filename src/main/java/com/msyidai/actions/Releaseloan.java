package com.msyidai.actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.msyidai.config.BaseConfig;
import com.msyidai.tool.FileTool;
import com.msyidai.tool.GetLoan;




public class Releaseloan extends BasePage {
	@FindBy(linkText="交易管理")
	private WebElement transmenageElement;
	@FindBy(linkText="满标")//满标导航按钮
	private WebElement releaseloan;
	@FindBy(id="btn_establish")//标的成立按钮
	private WebElement establish;
	@FindBy(id="btn_releasePrepare")//放款准备
	private WebElement releasePrepare;
	@FindBy(id="btn_release")
	private WebElement release;//放款按钮
	@FindBy(xpath="//form[@id='releaseloanSelectPayment']/input[@value='1']")
	private WebElement releaseloanSelect;
	@FindBy(id="btn_releaseOK")//放款确认按钮
	private WebElement releaseOK;
	//满标列表标的名称按钮
	private WebElement loanTitlElement;
	private void setLoanTitleElement(){
		loanTitlElement=driver.findElement(By.xpath("//lable[@id='lbl1']//td[@class='table_box']/a[text()='"+GetLoan.getLoanTitle(LoanInput.loanId)+"']"));
	}
	public  Repaymentpayed  releaseLoan(String loanid)  {
		local.clickleftmenu(transmenageElement, releaseloan);
		setLoanTitleElement();
		loanTitlElement.click();
		establish.click();
		local.acceptAlert();
		local.acceptAlert();
		if (local.IsexistWelement(releasePrepare)) {
			logger.info("标的"+loanId+": 标的成功成功");
			releasePrepare.click();
			local.wait(1);
			/*
			 * tiString未点击放款准备的弹框提示
			 */
			String tiString=local.getAlertText();
			if (local.IsexistWelement(release)) {
				logger.info("标的"+loanId+tiString);
				release.click();
				releaseloanSelect.click();
				releaseOK.click();
				logger.info("标的"+loanId+"放款成功");
			}else {
				logger.error("标的"+loanId+"放款准备失败");
				FileTool.getScreenshots(driver, BaseConfig.screenshotsPath, "放款失败");
			}
			
		}else {
			logger.error("标的"+loanId+": 标的成功失败");
			FileTool.getScreenshots(driver, BaseConfig.screenshotsPath, "标的成立失败");
		}
		local.defaultframe();
		 return new Repaymentpayed();
	}
   
}
