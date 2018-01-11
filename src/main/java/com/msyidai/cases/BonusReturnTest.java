package com.msyidai.cases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.msyidai.actions.BackLogin;
import com.msyidai.actions.BonusReturn;
import com.msyidai.actions.FrontLogin;
import com.msyidai.actions.LoanInput;

public class BonusReturnTest extends TestBase{
	@Test(dataProvider="providerNumbers")
	public void  bonusReturnTest(String assetId,String amount, String rate,String transType) {
		try {
			BackLogin backLogin=new BackLogin();
			BonusReturn bonusReturn=new BonusReturn();
			FrontLogin frontLogin=new FrontLogin();
			backLogin.backLogin("admin", "password").loanInput.companyLoanInput(assetId, amount, rate, transType).firstCheck(LoanInput.loanId)
			.lottery(LoanInput.loanId, "合伙人专用").secondChek().openObject();
			frontLogin.frontLogin("18201247562", "a11111111").init().invest.investLoan(amount, LoanInput.loanId, "123123")
			.init().shakeAwards.investShakeAwards(LoanInput.loanId);
			backLogin.backLogin("admin", "password").releaseloan.releaseLoan(LoanInput.loanId).returnLoanAmount(LoanInput.loanId)
			.repaymentCheck.repayLaonCheck(LoanInput.loanId).loanumpay(LoanInput.loanId).repaymentSearch.repayLoabSearch(LoanInput.loanId)
			.repayLoan(LoanInput.loanId);
			bonusReturn.bonusReturn(LoanInput.loanId);
			assertion.assertEquals(bonusReturn.getBonusReturnReslut().contains("奖金发放成功"), true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	@DataProvider(name="providerNumbers")
	public Object[][] providerNumbers(){
		return new Object[][]{{"ZC_20171213_173100","5000","12","转让"}
		};
	}

}
