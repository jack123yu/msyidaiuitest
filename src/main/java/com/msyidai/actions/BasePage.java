package com.msyidai.actions;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import bsh.This;

import com.msyidai.cases.TestBase;
import com.msyidai.utils.locater;
public class BasePage {
	/*
	 * 所有页面对象均继承此类，构造函数实例化localter()，子类页面对象中使用localter中包装的一些方法；
	 *PageFactory.initElements(TestBase.driver, this)实例化页面对象
	 */
	protected static locater local=null;
	protected static WebDriver driver=null;
	public static String loanId="20893";//定义一个参数储存录入的标的供全局使用
	protected JavascriptExecutor js=(JavascriptExecutor)driver;
	protected Logger logger=Logger.getLogger(This.class);
	protected Actions actions;
	public BasePage() {
		// TODO Auto-generated constructor stub
		local=new locater();
		driver=TestBase.driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		PageFactory.initElements(TestBase.driver, this);
		js=(JavascriptExecutor)driver;
		actions=new Actions(driver);
		
	}
	
	

}
