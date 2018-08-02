package lj.dao.base;

/***
 * 基于XML的DAO接口
 * @author samlv
 *
 * @param <T>
 */
public interface IXmlDao<T> {
	
	
	T loadFromFile();
	
	int saveToFile(T t);
}
