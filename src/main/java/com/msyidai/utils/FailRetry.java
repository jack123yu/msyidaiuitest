package com.msyidai.utils;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.msyidai.config.BaseConfig;

public class FailRetry implements IRetryAnalyzer {
    
    private int count=1;
    private Logger logger=Logger.getLogger(FailRetry.class);
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if (count<Integer.valueOf((BaseConfig.ConfigData.get("retryMaxCount").toString()))) {
			logger.info("用例： "+result.getMethod().getDescription()+"   执行失败  进行第"+count+"次重试！");
		    count++;
		    Reporter.setCurrentTestResult(result);
		    Reporter.log("用例： "+result.getMethod().getDescription()+"   执行失败  进行第"+count+"次重试！");
		    return true;
		}
		return false;
	}

}
