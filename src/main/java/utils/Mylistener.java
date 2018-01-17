package utils;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.IExecutionListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.asserts.IAssert;
import org.testng.internal.IResultListener;
import org.testng.internal.annotations.IAfterMethod;

import cases.TestBase;
import config.BaseConfig;
import tool.FileTool;



public class Mylistener implements  ITestListener,IExecutionListener,ISuiteListener{
   private Logger logger =Logger.getLogger(Mylistener.class);

   
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		logger.info("用例：  "+result.getMethod().getDescription()+"    测试通过");
		
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		logger.error("用例："+result.getMethod().getDescription()+"    测试失败  "+result.getThrowable().getMessage());
	    FileTool.getScreenshots(TestBase.driver, BaseConfig.ConfigData.get("screenshotsPath").toString(), "screenshots");
	    
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		logger.error("跳过用例："+result.getMethod().getDescription());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		logger.info(context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		Iterator<ITestResult> failedTests = context.getFailedTests().getAllResults().iterator();	
		
		while (failedTests.hasNext()) {
			ITestResult result = failedTests.next();
			/*
			 * 通过迭代器获取所有错误的result，如果同一个方法错误的result>1,从迭代器中删除该result
			 * 保留一个错误的result，再判断这个方法中是否有成功的result，如果成功的result数量>0（有成功的result）
			 * 说明重跑机制中有成功的，移除失败的result，保留成功的result，该测试用例测试 通过
			 */
			if(context.getFailedTests().getResults(result.getMethod()).size()>1){
				failedTests.remove();
			}else{
				if(context.getPassedTests().getResults(result.getMethod()).size() > 0){
					failedTests.remove();
				}
			}	
		}
	}

	@Override
	public void onExecutionStart() {
		// TODO Auto-generated method stub
		logger.info("测试开始执行");
	}

	@Override
	public void onExecutionFinish() {
		// TODO Auto-generated method stub
		logger.info("测试执行结束");
	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		logger.info(suite.getName()+"开始执行");
		List<ITestNGMethod> methods=suite.getAllMethods();
		for (ITestNGMethod iTestNGMethod : methods) {
			iTestNGMethod.setRetryAnalyzer(new FailRetry());
		}
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		logger.info(suite.getName()+"执行结束");
	}

	

}
