package com.msyidai.actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;




public  class MarketSet extends BasePage{
	/*
	 * 运营活动设置页面公共页面元素
	 */
	@FindBy(linkText="交易管理")
	private  WebElement transmenageElement;
	@FindBy(linkText="营销活动设置")
	private  WebElement setactivitymenu;
	@FindBy(id="btn_save")
	private  WebElement save;
	//标的列表中未设置按钮
	private  WebElement loanMarketingConfig=null;
	private void setLoanMarketingConfig() {
		loanMarketingConfig=driver.findElement(By.id("tda_"+LoanInput.loanId));
	}
	/*
	 * 摇奖加息活动页面元素
	 */
	@FindBy(id="chk_rates_lottery")
	private WebElement lotteryButtonElement;
	@FindBy(xpath="//input[@id='hid_rates_lottery']/../td/a")
	private WebElement setlottey;
	@FindBy(xpath="//div[@id='jsRatesDe']//Select[@id='activityId']")
	private WebElement selectElement;
	@FindBy(id="txt_lotterytypes")
	private WebElement setlotteytype;
	@FindBy(id="txt_lotttype1_rate")
	private WebElement setLotttype1Rate;
	@FindBy(id="txt_lotttype1_shares")
	private WebElement setLotttype1Shares;
	@FindBy(id="txt_lotttype2_rate")
	private WebElement setLotttype2Rate;
	@FindBy(id="txt_lotttype2_shares")
	private WebElement setLotttype2Shares;
	/*
	 * 猜猜乐页面元素
	 */
	@FindBy(id="chk_happy_guess")
	private WebElement happyGuessElement;
	@FindBy(xpath="//input[@id='hid_happy_guess']/../td/a")
	private WebElement setHappyGuessElement;
	@FindBy(id="txt_floatRate")
	private WebElement setFloatRateElement;
	@FindBy(id="txt_maxRate")
	private WebElement setMaxRateElement;
	@FindBy(id="txt_actId")
	private WebElement setactivityElement;
	@FindBy(id="txt_guessId")
	private WebElement setGuessActivityElement;
	/*
	 * 优惠券设置页面元素
	 */
	@FindBy(id="chk_coupon_model")
	private WebElement couponModelButton;
	@FindBy(xpath="//input[@id='chk_coupon_model']/../..//td/a[text()=' 设置活动>>']")
	private WebElement setCouponMode;
	@FindBy(xpath="//tr[@id='3']/td/input[@value='3']")//代金券
	private WebElement setCoupon1;
	@FindBy(xpath="//tr[@id='1']/td/input[@value='1']")//优惠券
	private WebElement setCoupon2;
	
	
	
	//不做任何设置
	public  SecondCheck  marketSet() {
		local.clickleftmenu(transmenageElement, setactivitymenu);
		setLoanMarketingConfig();
	    loanMarketingConfig.click();
	    save.click();
	    local.acceptAlert();
	    local.defaultframe();
	    logger.info("标的"+LoanInput.loanId+"运营活动设置通过，没有配置任何活动");
	    return new SecondCheck();
	}
	
	/*
	 * 摇奖加息
	 */
	public  SecondCheck lottery(String loanId,String activityName) {
		local.clickleftmenu(transmenageElement, setactivitymenu);
		setLoanMarketingConfig();
	    loanMarketingConfig.click();
	    lotteryButtonElement.click();
	    setlottey.click();	
	    local.select(selectElement, activityName);
	    setlotteytype.clear();
	    setlotteytype.sendKeys("2");
		local.tabDown();
		setLotttype1Rate.clear();
		setLotttype1Rate.sendKeys("30");
		setLotttype1Shares.clear();
		setLotttype1Shares.sendKeys("25");
		setLotttype2Rate.clear();
		setLotttype2Rate.sendKeys("20");
		setLotttype2Shares.clear();
		setLotttype2Shares.sendKeys("20");
	    save.click();
	    logger.info("标的"+LoanInput.loanId+"运营活动设置通过，配置摇奖加息2种，30% 25份，20%20份");
		local.defaultframe();
		return new SecondCheck();	
	}
	/*
	 * 猜猜乐活动
	 */
    public  SecondCheck  happyGuess(String loanId,String guessActivityName,String activityName){
		
	    local.clickleftmenu(transmenageElement, setactivitymenu);
		setLoanMarketingConfig();
	    loanMarketingConfig.click();
	    happyGuessElement.click();
	    setHappyGuessElement.click();
	    setFloatRateElement.sendKeys("10");
	    setMaxRateElement.sendKeys("30");
		local.select(setactivityElement, activityName);
		local.select(setGuessActivityElement, guessActivityName);
		save.click();
		logger.info("标的"+LoanInput.loanId+"运营活动设置通过，配置猜猜乐");
		local.defaultframe();
		return new SecondCheck();
	}
 /*
  * 设置优惠券代金券
  */
    public  SecondCheck  increasesRates(String loanId) {
	    local.clickleftmenu(transmenageElement, setactivitymenu);
		setLoanMarketingConfig();
		loanMarketingConfig.click();
		couponModelButton.click();
		setCouponMode.click();
		setCoupon1.click();
		setCoupon2.click();
		save.click();
		logger.info("标的"+LoanInput.loanId+"运营活动设置通过，配置优惠券代金券");
	    local.defaultframe();
		return new SecondCheck();
	}

}
