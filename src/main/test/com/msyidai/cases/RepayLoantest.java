package com.msyidai.cases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.msyidai.actions.BackLogin;
import com.msyidai.actions.RepayLoan;

public class RepayLoantest extends TestBase {
	@Test(dataProvider="providerNumbers")
	public void repayLoanTest(String loanId){
		RepayLoan repayLoan=new RepayLoan();
		BackLogin backLogin=new BackLogin();
		backLogin.backLogin("admin", "password");
		repayLoan.repayLoan(loanId);
	}
	@DataProvider(name="providerNumbers")
	public Object[][] providerNumbers() {
		
		return new Object[][]{{"20907"}
		};
	}

}
