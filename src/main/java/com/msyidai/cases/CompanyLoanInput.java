package com.msyidai.cases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.msyidai.actions.BackLogin;
import com.msyidai.actions.LoanInput;

public class CompanyLoanInput extends TestBase{
	@Test(description="企业融资标的的录入")
	@Parameters({"CompanyLoanassetId","CompanyLoanamount","CompanyLoanrate","CompanyLoantransType"})
	public void  companyLoanInputTest(String assetId, String amount,String rate,String transType) {
		BackLogin backLogin=new BackLogin();
		LoanInput loanInput=new LoanInput();
	    backLogin.backLogin("admin", "password").loanInput.companyLoanInput(assetId, amount, rate, transType);
	    asert.assertEquals(loanInput.getResult().contains("成功"), "成功");
	    asert.assertAll();
	}
	@DataProvider(name="providerNumbers")
	public Object[][] providerNumbers(){
		return new Object[][]{{"ZC_20171213_173100","5000","12","转让"}
		};
	}
}
