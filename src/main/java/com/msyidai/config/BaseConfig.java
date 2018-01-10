package com.msyidai.config;

import com.msyidai.tool.GetDataTime;

public class BaseConfig {
	public static String driverType="chrome";
	public static String driverPath="E:\\peizhichajian\\chromedriver.exe";
	
	public static String screenshotsPath="E:\\MyWorkspaces\\msyidai-uitest\\image\\"+GetDataTime.getNowDateTime();
	public static int retryMaxCount=3;
	
	public static String IP="http://172.30.10.62:9080";
    public static String url = "jdbc:mysql://172.30.1.49/zkbc"; 
	public static String user = "root";
    public static String password = "msds007";
	 // public static String password = "root";//74库的密码
}
