package com.msyidai.actions;

public class FrontHomePage extends BasePage{
	
	
	public Invest invest=new Invest();
	public TransferLoan transferLoan=new TransferLoan();
	public ShakeAwards shakeAwards=new ShakeAwards();
	public FrontHomePage  init() {
		driver.get("https://www.msyidai.com");
		return this;
	}

}
