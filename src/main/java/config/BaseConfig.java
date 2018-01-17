package config;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import bsh.This;





public class BaseConfig {	
	public static HashMap ConfigData=new HashMap();
	static{	
    	SAXReader reader=new SAXReader();
    	File file=new File("./config.xml");
    	try {
			Document document=reader.read(file);
			List<Element> list=document.getRootElement().elements();
			for (Element element : list) {
            String key=element.getName();
            String value=element.getText();
            ConfigData.put(key,value);  
			}			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(This.class).error(e.getMessage());
		}
    
	}
	/*
	 * 遍历Hashmap方法
	 */
	/*public static void main(String[] args) {
		configData();
		HashMap dict=config.dictData;
		
		Iterator iterator=dictData.entrySet().iterator();
		Map.Entry entry =(Map.Entry) iterator.next();
		、、System.out.println("密码为："+config.dictData.get("password"));
		while (iterator.hasNext()) {
			Map.Entry entry2 =(Map.Entry) iterator.next();
			System.out.println(entry2.getKey());
			System.out.println(entry2.getValue());
		}
		

	}
*/
}
