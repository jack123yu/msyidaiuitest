package utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Lists;

public class CheckPoint extends Assertion {
	private int flag = 0;
	private Logger logger = Logger.getLogger(CheckPoint.class);

	public void equals(String actual, String expected, String message) {
		try {
			assertEquals(actual, expected, message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag++;
			logger.error(e.getMessage());
		}
	}

	public void equals(String actual, String expected) {
		try {
			assertEquals(actual, expected);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag++;
			logger.error(e.getMessage());
		}
	}
	public void equals( boolean actual,  boolean expected,  String message) {
		try {
			assertEquals(actual, expected, message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag++;
			logger.error(e.getMessage());
		}
	}
	public void equals( boolean actual,  boolean expected) {
		try {
			assertEquals(actual, expected);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag++;
			logger.error(e.getMessage());
		}
	}

}
