package lj.dao.collect;

import java.util.Map;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.collect.ViProductLineCollect;

public interface IViProductLineCollectDao extends IJdbcDao<ViProductLineCollect> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	ViProductLineCollect find(Long id);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	ViProductLineCollect[] findAll();
	
	
	
	
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	long insert(ViProductLineCollect obj);
	
	/**
	 * 更新对象
	 * @param obj
	 * @return
	 */
	String update(ViProductLineCollect obj);
	
	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
	String delete(Long id);
	
	
	
	Pager<ViProductLineCollect> findAllPaged(Long groupId , String userProductLineName , String paramName );
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	Pager<ViProductLineCollect> findAllPaged(Long userProductLineId);
	
	 
	
	/**
	 * 某个字段值是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	boolean isRepeat(String fieldName,Object fieldValue,Long id);
	

}
