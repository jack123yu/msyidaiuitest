package com.msyidai.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.msyidai.tool.DataSeek;



//还款入金结果查询
public class RepaymentSearch extends BasePage {
	@FindBy(linkText="财务管理")
	private WebElement financialManagement;
	@FindBy(linkText="还款入金查询")
	private WebElement repaymentSearchMenu;
	private WebElement searchElement;
	private void setSearchElement(String loanId) {
		searchElement=driver.findElement(By.xpath("//td[text()='"+loanId+"']/../td/input[@value='查询']"));
	}
	
    public RepayLoan repayLoabSearch (String loanId)  {
	    String[][] test=DataSeek.getLoanPlan(loanId);
	    local.wait(1);
	    for (int j = 0; j < test[1].length; j++) {
		    if(test[0][j]!=null){
		    local.clickleftmenu(financialManagement, repaymentSearchMenu);
		    setSearchElement(test[0][j]);
		    searchElement.click();
		    local.defaultframe();
		}else {
			return new RepayLoan();
		}
	
}
	    return new RepayLoan();
}
}
