package com.msyidai.utils;
/*
 * 定义了不同非浏览器---
 */
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	public static WebDriver createrDriver(String type,String driverpath){
		 WebDriver driver=null;
		 ChromeDriverService service=null;
		String drivetype=type.toLowerCase().trim();
		switch (drivetype) {
		case "chrome":
			    if(!driverpath.equals(null)){
			    	service=new ChromeDriverService.Builder()
			    	        .usingAnyFreePort()
			    	        .usingDriverExecutable(new File(driverpath))
			    	        .build();
			    }else {
			    	service=new ChromeDriverService.Builder()
	    	        .usingAnyFreePort()
	    	        .build();
				}
			    driver=new ChromeDriver(service);
			break;
		case "firefox":
		    if(!driverpath.equals(null)){
		    	System.setProperty("webdriver.firefox.bin", driverpath);
		    }
		    driver=new FirefoxDriver();
		break;
		case "ie":
		    if(!driverpath.equals(null)){
		    	System.setProperty("webdriver.ie.driver", driverpath);
		    }
		    driver=new FirefoxDriver();
		break;
		default:
			System.out.println("只支持一下浏览器chrome、Firefox、Ie");
			break;
		}
		return driver;
		
	}

}
