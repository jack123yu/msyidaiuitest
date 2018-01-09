package com.msyidai.cases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.LoggingAssert;

import com.msyidai.actions.FrontLogin;
import com.msyidai.utils.locater;

public class login extends TestBase {
    @Test(priority=1,description="正常登陆测试")
    @Parameters({"usename1","password1"})
	public void loginsucces(String usename,String password){
		FrontLogin frontLogin=new FrontLogin();
		frontLogin.frontLogin(usename, password);
		Assertion  assertion=new Assertion();
		assertion.assertEquals(driver.getPageSource().contains("您好"), true);
		
	}
    @Test(priority=2,description="密码错误测试")
    @Parameters({"usename2","password2"})
    
   	public void loginpasswordflase(String usename,String password){
   		FrontLogin frontLogin=new FrontLogin();
   		frontLogin.frontLogin(usename, password);
   		assertEquals(driver.getPageSource().contains("账号或密码错误"), true,"密码错误");	
   	}
    @Test(priority=3,description="账户错误测试")
    @Parameters({"usename3","password3"})
   	public void loginusenameflase(String usename,String password){
   		FrontLogin frontLogin=new FrontLogin();
   		frontLogin.frontLogin(usename, password);
   		assertEquals(driver.getPageSource().contains("账号或密码错误"), true,"账户错误");
   	}


}
