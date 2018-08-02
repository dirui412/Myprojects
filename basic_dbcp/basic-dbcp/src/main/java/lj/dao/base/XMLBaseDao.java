package lj.dao.base;

import lj.util.IntUtils;
import lj.util.XmlUtils;

/***
 * 基于XML的DAO基类对象
 * @author samlv
 *
 * @param <T>
 */
public class XMLBaseDao<T> implements IXmlDao<T> {
	/**
	 * 配置文件路径
	 */
	private String configFilePath;
	
	/***
	 * 构造方法
	 * @param configFilePath
	 */
	public XMLBaseDao(String configFilePath){
		this.configFilePath=configFilePath;
	}
	

	@Override
	public T loadFromFile() {
		T config = (T) XmlUtils.loadFromFile(configFilePath);
		return config;
	}

	@Override
	public int saveToFile(T t) {
		try {
			XmlUtils.saveToFile(configFilePath, t);
			return IntUtils.INT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return IntUtils.INT_FAIL;
		}
	}

}
