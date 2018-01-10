package com.msyidai.actions;

import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.msyidai.config.BaseConfig;
import com.msyidai.tool.DataSeek;
import com.msyidai.tool.FileTool;
import com.msyidai.tool.GetDataTime;
/*
 * 发布变现
 */
import com.msyidai.tool.GetLoan;
import com.msyidai.utils.MyException;

public class TransferLoan extends BasePage{
	@FindBy(xpath="//li[@name='transferLoanFormSub']")//可变现按钮
	private WebElement LoanFormSub;
	@FindBy(xpath="//td[@class='ta-l']/a")//标的名称
	private By LoanTitle=By.xpath("//td[@class='ta-l']/a");
	@FindBy(id="_payPassword")
	private WebElement payPassword;
	@FindBy(className="trans-sendbtn")
	private WebElement sendElement;
	@FindBy(xpath="//input[@id='prjStatus' and @value='sub']/..//a[text()='下一页']")
	private WebElement next;
	@FindBy(className="main-title")
	private WebElement successElement;
	
	private WebElement transLoanButton;
	private void  setTransLoanButton(String loanTitle) {
		transLoanButton=driver.findElement(By.xpath("//a[@title='"+loanTitle+"']/../..//td[@class='u-operating']/a"));
	}
	
	public  FrontHomePage postedTransferLoan(String loanId,String paypassword) {
		driver.get("https://www.msyidai.com/transferLoan");
		String sql="update loan set BorrowBearingStartDate ='"+GetDataTime.getNextDay(new Date(), -31)+"' WHERE loanId="+loanId;
		DataSeek.updateParameter(sql);
		LoanFormSub.click();
		while (true) {
			List<WebElement> list=driver.findElements(LoanTitle);
			for (WebElement webElement2 : list) {
				String loanTitle=webElement2.getText();
				if(loanTitle.equals(GetLoan.getLoanTitle(loanId))){
					setTransLoanButton(GetLoan.getLoanTitle(loanId));
					transLoanButton.click();
					payPassword.sendKeys(paypassword);
					sendElement.click();
					local.wait(1);
					if (!successElement.getText().contains("已发布")) {
						FileTool.getScreenshots(driver, BaseConfig.screenshotsPath, "investTrans");
						local.wait(1);
						local.pageUp();
						FileTool.getScreenshots(driver, BaseConfig.screenshotsPath, "investTrans");
						logger.error("标的"+loanId+"变现失败！！请前往目录："+BaseConfig.screenshotsPath+"  下查看");
						
					}
					
					logger.info("标的"+loanId+successElement.getText());
					return new FrontHomePage();
				}
			}
			try {
				next.click();
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				break;
			}

		}
		return new FrontHomePage();
	}

}
