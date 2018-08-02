package lj.dao.collect;

import java.util.Map;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.collect.EquipmentCollect;

public interface IEquipmentCollectDao extends IJdbcDao<EquipmentCollect> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	EquipmentCollect find(Long id);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	EquipmentCollect[] findAll();
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	long insert(EquipmentCollect obj);
	
	/**
	 * 更新对象
	 * @param obj
	 * @return
	 */
	String update(EquipmentCollect obj);
	
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
	Pager<EquipmentCollect> findAllPaged();
	
	/**
	 * 某个字段值是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	boolean isRepeat(String fieldName,Object fieldValue,Long id);
}
