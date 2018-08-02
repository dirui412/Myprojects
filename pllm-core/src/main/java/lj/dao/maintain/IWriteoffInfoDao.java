package lj.dao.maintain;

import java.util.Map;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.maintain.WriteoffInfo;

public interface IWriteoffInfoDao extends IJdbcDao<WriteoffInfo> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	WriteoffInfo find(Long id);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	WriteoffInfo[] findAll();
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	long insert(WriteoffInfo obj);
	
	/**
	 * 更新对象
	 * @param obj
	 * @return
	 */
	String update(WriteoffInfo obj);
	
	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
	String delete(Long id);
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	Pager<WriteoffInfo> findAllPaged();
	
	/**
	 * 某个字段值是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	boolean isRepeat(String fieldName,Object fieldValue,Long id);
}
