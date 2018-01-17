package tool;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Xmlparse {
	SAXReader reader=new SAXReader();
	Document document=null;
	public Xmlparse(String path){
		File file= new File(path);
		try {
			document=reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public String  getvalue(String xpath,String value){
		if (!document.equals(null)) {
			Element element=(Element)(document.selectSingleNode(xpath));
			//System.out.println(element.attributeValue(value));
			return element.attributeValue(value);
		}else {
			//System.out.println("������ļ������쳣");
			return null;
		}
		
	}

}
