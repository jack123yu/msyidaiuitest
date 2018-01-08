package com.msyidai.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Lists;

public class CheckPoint extends Assertion{
	private Logger logger=Logger.getLogger(CheckPoint.class);
	public static void asserttrue(String actual ,String expected) {
		Assert.assertEquals(actual, expected);
	}
	

	@Override
	public void  onAssertSuccess(IAssert assertCommand) {
		System.out.println(assertCommand.getActual());
		logger.info(assertCommand.getActual());
		logger.info(assertCommand.getExpected());
		logger.info(assertCommand.getClass());
		logger.info(assertCommand.getMessage());
		
	}
	

}
