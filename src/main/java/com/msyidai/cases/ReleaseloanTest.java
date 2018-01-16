package com.msyidai.cases;

import org.testng.annotations.Test;

import com.msyidai.actions.BackLogin;

/*
 * 满标-标的成立-放款测试
 */
public class ReleaseloanTest extends TestBase {
	@Test
	public void  releaseloanTest() {
		BackLogin backLogin =new BackLogin();
		backLogin.backLogin("admin", "password").releaseloan.releaseLoan("2090113686");
	}

}
