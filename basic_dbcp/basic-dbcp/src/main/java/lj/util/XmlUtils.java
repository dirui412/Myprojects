package lj.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.TimeZone;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import lj.global.PlatformConst;

public class XmlUtils {
	
    /**
     * 从文件读入XMl
     * @param filePath
     * @return
     */
	public static Object loadFromFile(String filePath){
		try {
			XStream xStream=new XStream(new DomDriver(PlatformConst.APP_CHARSET));
			xStream.registerConverter(new DateConverter(StringUtils.DATETIME_FORMAT, null,  
		            TimeZone.getTimeZone("GMT+8")));  
			InputStream is = new FileInputStream(filePath);
			int length=is.available();
			if(length<=0)
				return null;
			ObjectInputStream input = xStream.createObjectInputStream(is);
			Object obj=input.readObject();
			is.close();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param filePath
	 * @param obj
	 */
	public static void saveToFile(String filePath,Object obj){
		try {
			XStream xStream=new XStream(new DomDriver(PlatformConst.APP_CHARSET));
			xStream.registerConverter(new DateConverter(StringUtils.DATETIME_FORMAT, null,  
		            TimeZone.getTimeZone("GMT+8")));  
			OutputStream os = new FileOutputStream(filePath);
			ObjectOutputStream out = xStream.createObjectOutputStream(os);
			out.writeObject(obj);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
