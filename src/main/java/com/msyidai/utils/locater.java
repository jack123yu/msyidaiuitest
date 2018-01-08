package com.msyidai.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.msyidai.cases.TestBase;

public class locater {
	private WebDriver driver=null;
	Actions actions=null;
	public locater() {
		// TODO Auto-generated constructor stub
		driver=TestBase.driver;
		actions=new Actions(driver);
	}
	/*定义等待类入参为int类型
	*/
	public void wait(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	public void wait(Double seconds){
		try {
			Thread.sleep((long) (seconds*1000.00));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	public void  to(String url) {
		wait(0.5);
		driver.get(url);
		wait(1);
	}
	/*切换frame到做导航，中间等待1秒，再退出做导航栏
	 * */
	public void switchleftframe(String frameName){
		driver.switchTo().frame(frameName);
		wait(1);
		driver.switchTo().defaultContent();
	}
	/*
	 * 封装一个切换菜单的方法，此方法现仅适用于易贷后台，切换frame窗口，->leftFrame->main
	 */
	public void clickleftmenu(WebElement webElement1, WebElement webElement2){
		driver.switchTo().frame("leftFrame");
		webElement1.click();
		wait(1);
		webElement2.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("main");
	}
	//切换到任意的一个frame,入参为frame名称
	public void switchframe(String frameName){
		driver.switchTo().frame(frameName);
	}
	public void switchframe(WebElement webElement){
		driver.switchTo().frame(webElement);
	}
	//退出当前frame，与swichframe一块使用，使用与在当前frame上有操作的情况
    public void defaultframe(){
    	driver.switchTo().defaultContent();
	}
    /*选择下拉框操作可根据下拉框的文字描述进行操作、value序号进行操作
    */
    public void  select(WebElement webElement,String VisibleText)  {
		Select select=new Select(webElement);
		select.selectByVisibleText(VisibleText);
	}
    /*以下定义包装了部分keys的操作
*/
    public void pagedown(){
    	actions.sendKeys(Keys.PAGE_DOWN).perform();
    }
    public void tabDown(){
    	actions.sendKeys(Keys.TAB).perform();
    }
    public void pageUp(){
    	actions.sendKeys(Keys.PAGE_UP).perform();
    }
    /*定义包装弹框处理类alert
     * */
    public String getAlertText() {
    	Alert alert=driver.switchTo().alert();
    	wait(1);
    	String text=alert.getText();
		alert.accept();
		return text;
	}
    public void acceptAlert() {
    	Alert alert;
		try {
			alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			// TODO Auto-generated catch block
			try {
				wait(2);
				alert = driver.switchTo().alert();
				alert.accept();
			} catch (NoAlertPresentException e1) {
				// TODO Auto-generated catch block
				wait(2);
				alert = driver.switchTo().alert();
				alert.accept();
			}
		}
    	
	}
    public void dismissAlert() {
    	Alert alert=driver.switchTo().alert();
    	wait(1);
		alert.dismiss();
	}
    /*
     * 封装时间控件的操作，为了方便操作选择的时间均为当天的时间，默认跳转到时间控件点击确认，均适用于易贷后台,录标的标的募集开始时间结束时间与还款时间等场景;
     * 主页面frame写死为“main”
     */
    public void  setTime(WebElement webElement1,WebElement webElement2) {
    	driver.switchTo().frame(webElement1);//切换到时间控件frame
		wait(0.5);
	    webElement2.click();    
	    driver.switchTo().defaultContent();
	    driver.switchTo().frame("main");
	    
	}
    public boolean IsexistWelement(WebElement webElement) {
    	try {
    		webElement.getText();
    		return true;
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
   
}
