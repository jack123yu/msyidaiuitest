package com.msyidai.actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.msyidai.tool.GetLoan;



public class FirstCheck extends BasePage{
	private String loanTitle=null;
	@FindBy(linkText="交易管理")
	private WebElement transmenageElement;
	@FindBy(linkText="录入审核")
	private WebElement firstCheck;
	@FindBy(xpath=".//*[@id='checked_button']")
	private WebElement checkButton;
	private WebElement loan_title;;
	//查询标的名称，返回一个webElement对象,赋值给loan_title;
	private void title(String loanId){
		loanTitle=GetLoan.getLoanTitle(loanId);
		loan_title=driver.findElement(By.linkText(loanTitle));
	}
	
	//public MarketSet marketSet=new MarketSet();

    public  MarketSet firstCheck(String loanId){
	    local.clickleftmenu(transmenageElement, firstCheck);
	    title(loanId);
	    loan_title.click();
	    checkButton.click();
	    local.acceptAlert();
	    local.defaultframe();
	    logger.info("标的"+LoanInput.loanId+"初审通过！");
	    return new MarketSet();
}


}
