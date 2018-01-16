package com.msyidai.cases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.msyidai.actions.BackLogin;
import com.msyidai.actions.FrontLogin;
import com.msyidai.actions.LoanInput;

public class CompanyLoanInputRepay extends TestBase{
	@Test(dataProvider="providerNumbers",description="企业标的还款测试")
	public void  companyLoanInputRepay(String assetId, String amount,String rate,String transType) {
		BackLogin backLogin=new BackLogin();
		backLogin.backLogin("admin", "password").loanInput.companyLoanInput(assetId, amount, rate, transType)
		          .firstCheck(LoanInput.loanId).marketSet().secondChek().openObject();
		FrontLogin frontLogin=new FrontLogin();
		frontLogin.frontLogin("18201247562", "a11111111").init().invest.investLoan(amount, LoanInput.loanId, "123123");
		backLogin.backLogin("admin", "password").releaseloan.releaseLoan(LoanInput.loanId).returnLoanAmount(LoanInput.loanId);
		backLogin.backLogin("admin1", "password").repaymentCheck.repayLaonCheck(LoanInput.loanId).loanumpay(LoanInput.loanId)
		         .backLogin("admin", "password").repaymentSearch.repayLoabSearch(LoanInput.loanId).repayLoan(LoanInput.loanId);
	}
	@DataProvider(name="providerNumbers")
	public Object[][] providerNumbers() {
		
		return new Object[][]{{"ZC_20171213_173100","5000","12","转让"},
			                  {"ZC_20171213_173100","5000","12","非转让"}
		};
	}

}
