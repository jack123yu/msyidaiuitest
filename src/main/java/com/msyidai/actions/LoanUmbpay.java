package com.msyidai.actions;
//三方入金审核
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.msyidai.tool.DataSeek;


public class LoanUmbpay extends BasePage{
	private String url="http://test.umbpay.com.cn:9086/boss/login.html";
	List<String> orderId = null;//存储三方商户号
	int flag=0,flag2=0;;//根据页面规律，每次点击经办按钮，就会新生成一个新的元素，每次元素值增加2，故每次点击经办按钮和审核按钮时，增加2
	@FindBy(id="id")
	private WebElement useNameElement;
	@FindBy(id="pwd")
	private WebElement passwrodElement;
	@FindBy(id="verifycode")
	private WebElement verifycode;
	@FindBy(id="submit")
	private WebElement submit;
	@FindBy(linkText="充值处理")
	private WebElement recharge;
	@FindBy(id="3000500002")
	private WebElement fraElement1;
	@FindBy(id="queryButton")
	private WebElement searchElement;
	@FindBy(linkText="充值处理审核")
	private WebElement audit;
	@FindBy(id="3000500003")
	private WebElement fraElement2;
	/*经办按钮*/
	private WebElement ui_btn;
	private void setui_btn(String string){
		ui_btn=driver.findElement(By.xpath("//td[text()='"+string+"']/../td/a[text()='经办']"));
	}
	//经办通过按钮
	private WebElement passElement;
	private void setPassElement(int flag) {
		passElement=driver.findElement(By.xpath("//div[@ligeruiid='Dialog100"+flag+"']//div[@class='l-dialog-btn-inner' and text()='经办通过']"));
		
	}
	//审核按钮
	private WebElement auditpass;
	private void setauditpass(String string){
		auditpass=driver.findElement(By.xpath("//td[text()='"+string+"']/../td/a[text()='审核']"));
	}
	//审核通过按钮
	private WebElement auditpassElement;
	private void setauditpassElement(int flag2) {
		auditpassElement=driver.findElement(By.xpath("//div[@ligeruiid='Dialog100"+flag2+"']//div[@class='l-dialog-btn-inner' and text()='审核通过']"));
		
	}
	
	
	
	
	public  BackLogin loanumpay(String loanId)  {
		driver.get(url);
		useNameElement.sendKeys("30000683");
		passwrodElement.sendKeys("111111");
		verifycode.sendKeys("1111");
		submit.click();
		local.wait(1);
		recharge.click();
		orderId = DataSeek.merchantNums(loanId);	
		for (String string : orderId) {
			local.switchframe(fraElement1);
			searchElement.click();
			local.wait(2);
			setui_btn(string);
			ui_btn.click();
			setPassElement(flag);
			passElement.click();
			flag+=2;
			local.defaultframe();		  		
		}
		driver.get(url);
		useNameElement.sendKeys("30000682");
	    passwrodElement.sendKeys("111111");
	    verifycode.sendKeys("1111");
	    submit.click();
	    local.wait(1);
	    audit.click();
	    for (String string : orderId) {
	        local.switchframe(fraElement2);
	        searchElement.click();
	        local.wait(2);
	        setauditpass(string);
	        auditpass.click();
			setauditpassElement(flag2);
			auditpassElement.click();
			flag2+=2;
		}
	    
	    return new BackLogin();
	}

}
