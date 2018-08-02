package lj.dao.collect;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.collect.Viequipmentcollect;

public interface IViequipmentcollectDao extends IJdbcDao<Viequipmentcollect> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	Viequipmentcollect find(Long id);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	Viequipmentcollect[] findAll();
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	Pager<Viequipmentcollect> findAllPaged(String userProductLineName, String equipmentName, String paraName);
	/**
	 * 某个字段值是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	boolean isRepeat(String fieldName,Object fieldValue,Long id);

	
}
