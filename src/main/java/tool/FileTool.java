package tool;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class FileTool {
	private static Logger logger=Logger.getLogger(FileTool.class);
	public static void createFolder(String path){
		File file=new File(path);
		if(!file.exists()){
			file.mkdir();
		}
	}
	public static void getScreenshots(WebDriver driver,String path,String name){
		createFolder(path);
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			logger.info(path+"\\"+name+"_"+GetDataTime.getCurrentFileTime()+".jpg");
			FileUtils.copyFile(file, new File(path+"\\"+name+"_"+GetDataTime.getCurrentFileTime()+".jpg"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			logger.error(e.getMessage());
		}

	}
	
}
