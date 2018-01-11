package com.msyidai.cases;



import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.msyidai.actions.FrontLogin;

//检查变现
public class CheckCashIn extends TestBase {
	@Test
	public void checkCashIn(){
		FrontLogin frontLogin =new FrontLogin();
	
		frontLogin.frontLogin("18201247562", "a11111111").init();
		String source=driver.getPageSource();
		assertion.assertEquals(source.contains("变现"), true);
		
	}
}
