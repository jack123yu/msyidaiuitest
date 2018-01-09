package com.msyidai.cases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.msyidai.actions.BackLogin;
import com.msyidai.actions.FrontLogin;
import com.msyidai.actions.LoanInput;
import com.msyidai.tool.FileTool;

@Test(dataProvider="providerNumbers")
public class ReleaseTransLoan extends TestBase{
	public void releaseTransLoan(String assetId, String amount,String rate,String transType) {
	
			BackLogin backLogin=new BackLogin();
			backLogin.backLogin("admin", "password").loanInput.companyLoanInput(assetId, amount, rate, transType)
			          .firstCheck(LoanInput.loanId).marketSet().secondChek().openObject();
			FrontLogin frontLogin=new FrontLogin();
			frontLogin.frontLogin("18201247562", "a11111111").init().invest.investLoan(amount, LoanInput.loanId, "123123");
			backLogin.backLogin("admin", "password").releaseloan.releaseLoan(LoanInput.loanId);
		
			frontLogin.frontLogin("18201247562", "a11111111").init().transferLoan.postedTransferLoan(LoanInput.loanId, "123123");
		
		
	}
	@DataProvider(name="providerNumbers")
	public Object[][] providerNumbers() {
		
		return new Object[][]{{"ZC_20171115_165535","10000","12","转让"},
			               
		};
	}

}
