package com.msyidai.cases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.msyidai.actions.BackLogin;
import com.msyidai.actions.LoanInput;

public class ForeignFinancialLoanInput extends TestBase {
	@Test(description="出国金融标的的录入")
	@Parameters({"foreignLoanassetId","foreignLoanamount","foreignLoanrate"})
	public void foreignFinancialLoanInput(String assetId, String amount, String rate) {
		BackLogin backLogin=new BackLogin();
		LoanInput loanInput=new LoanInput();
		backLogin.backLogin("admin", "password").loanInput.financialLoanInput(assetId, amount, rate);
		asert.assertEquals(loanInput.getResult().contains("成功"), "成功");
		asert.assertAll();
	}
   /* @DataProvider(name="foreignLoanInPutTestData")
	public Object[][] foreignLoanInPutTestData() {
		return new Object[][]{
			                  { "ZC_20180115_175832", "5000", "12"}
		};
		
	}*/
}
