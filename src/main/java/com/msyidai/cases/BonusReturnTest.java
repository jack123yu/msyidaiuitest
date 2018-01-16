package com.msyidai.cases;

/*
 * 奖金返还
 */
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.msyidai.actions.BackLogin;
import com.msyidai.actions.BonusReturn;
import com.msyidai.actions.FrontLogin;
import com.msyidai.actions.H5Login;
import com.msyidai.actions.LoanInput;
import com.msyidai.config.BaseConfig;
import com.msyidai.tool.FileTool;
import com.msyidai.utils.locater;

public class BonusReturnTest extends TestBase {
	@Test(dataProvider = "personLoanBonusReturnTestData", description = "个人标的三方奖金返还测试")
	public void personLoanBonusReturnTest(String assetId, String amount, String rate) {

		BackLogin backLogin = new BackLogin();
		BonusReturn bonusReturn = new BonusReturn();
		FrontLogin frontLogin = new FrontLogin();
		H5Login h5Login = new H5Login();
		backLogin.backLogin("admin", "password").loanInput.personLoanInput(assetId, amount, rate)
				.firstCheck(LoanInput.loanId).lottery(LoanInput.loanId, "活动714").secondChek().openObject();
		/*
		 * frontLogin.frontLogin("18201247562",
		 * "a11111111").init().invest.investLoan(amount, LoanInput.loanId,
		 * "123123") .init().shakeAwards.investShakeAwards(LoanInput.loanId);
		 */
		h5Login.frontLogin("15010736151", "a1234567").h5Invest(LoanInput.loanId, amount, "123123")
				.h5InvestShakeAwards(LoanInput.loanId);
		backLogin.backLogin("admin", "password").releaseloan.releaseLoan(LoanInput.loanId)
				.returnLoanAmount(LoanInput.loanId);
		backLogin.backLogin("admin1", "password").repaymentCheck.repayLaonCheck(LoanInput.loanId)
				.loanumpay(LoanInput.loanId);
		backLogin.backLogin("admin", "password").repaymentSearch.repayLoabSearch(LoanInput.loanId)
				.repayLoan(LoanInput.loanId);
		bonusReturn.bonusReturn(LoanInput.loanId);
		assertion.assertEquals(bonusReturn.getBonusReturnReslut().contains("奖金发放成功"), true);

	}

	@Test(dataProvider = "companyLoanBonusReturnTestData", description = "企业标的三方奖金返还测试")
	public void companyLoanBonusReturnTest(String assetId, String amount, String rate, String transType) {

		BackLogin backLogin = new BackLogin();
		BonusReturn bonusReturn = new BonusReturn();
		FrontLogin frontLogin = new FrontLogin();
		H5Login h5Login = new H5Login();
		backLogin.backLogin("admin", "password").loanInput.companyLoanInput(assetId, amount, rate, transType)
				.firstCheck(LoanInput.loanId).lottery(LoanInput.loanId, "活动714").secondChek().openObject();
		/*
		 * frontLogin.frontLogin("18201247562",
		 * "a11111111").init().invest.investLoan(amount, LoanInput.loanId,
		 * "123123") .init().shakeAwards.investShakeAwards(LoanInput.loanId);
		 */
		h5Login.frontLogin("15010736151", "a1234567").h5Invest(LoanInput.loanId, amount, "123123")
				.h5InvestShakeAwards(LoanInput.loanId);
		backLogin.backLogin("admin", "password").releaseloan.releaseLoan(LoanInput.loanId)
				.returnLoanAmount(LoanInput.loanId);
		backLogin.backLogin("admin1", "password").repaymentCheck.repayLaonCheck(LoanInput.loanId)
				.loanumpay(LoanInput.loanId);
		backLogin.backLogin("admin", "password").repaymentSearch.repayLoabSearch(LoanInput.loanId)
				.repayLoan(LoanInput.loanId);
		bonusReturn.bonusReturn(LoanInput.loanId);
		assertion.assertEquals(bonusReturn.getBonusReturnReslut().contains("奖金发放成功"), true);

	}
	@Test(dataProvider = "foreignLoanBonusReturnTestData", description = "出国金融标的三方奖金返还测试")
	public void foreignLoanBonusReturnTest(String assetId, String amount, String rate) {

		BackLogin backLogin = new BackLogin();
		BonusReturn bonusReturn = new BonusReturn();
		FrontLogin frontLogin = new FrontLogin();
		H5Login h5Login = new H5Login();
		backLogin.backLogin("admin", "password").loanInput.financialLoanInput(assetId, amount, rate)
				.firstCheck(LoanInput.loanId).lottery(LoanInput.loanId, "活动714").secondChek().openObject();
		/*
		 * frontLogin.frontLogin("18201247562",
		 * "a11111111").init().invest.investLoan(amount, LoanInput.loanId,
		 * "123123") .init().shakeAwards.investShakeAwards(LoanInput.loanId);
		 */
		h5Login.frontLogin("15010736151", "a1234567").h5Invest(LoanInput.loanId, amount, "123123")
				.h5InvestShakeAwards(LoanInput.loanId);
		backLogin.backLogin("admin", "password").releaseloan.releaseLoan(LoanInput.loanId)
				.returnLoanAmount(LoanInput.loanId);
		backLogin.backLogin("admin1", "password").repaymentCheck.repayLaonCheck(LoanInput.loanId)
				.loanumpay(LoanInput.loanId);
		backLogin.backLogin("admin", "password").repaymentSearch.repayLoabSearch(LoanInput.loanId)
				.repayLoan(LoanInput.loanId);
		bonusReturn.bonusReturn(LoanInput.loanId);
		assertion.assertEquals(bonusReturn.getBonusReturnReslut().contains("奖金发放成功"), true);

	}

	@DataProvider(name = "personLoanBonusReturnTestData")
	public Object[][] personLoanBonusReturnTestData() {
		return new Object[][] { { "ZC_20180115_175832", "5000", "12", },

		};
	}

	@DataProvider(name = "companyLoanBonusReturnTestData")
	public Object[][] companyLoanBonusReturnTestData() {
		return new Object[][] { { "ZC_20180115_175832", "5000", "12", "转让" },

		};
	}
	@DataProvider(name = "foreignLoanBonusReturnTestData")
	public Object[][] foreignLoanBonusReturnTestData() {
		return new Object[][] { { "ZC_20180115_175832", "5000", "12"},

		};
	}

}
