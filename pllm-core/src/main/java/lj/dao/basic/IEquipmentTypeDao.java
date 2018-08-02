package lj.dao.basic;

import java.util.Map;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.basic.EquipmentType;

public interface IEquipmentTypeDao extends IJdbcDao<EquipmentType> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	EquipmentType find(Long id);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	EquipmentType[] findAll();
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	long insert(EquipmentType obj);
	
	/**
	 * 更新对象
	 * @param obj
	 * @return
	 */
	String update(EquipmentType obj);
	
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
	Pager<EquipmentType> findAllPaged(Long equipmentTypeId , String equipmentTypeName , String equipmentTypeDescribe  );
	
	/**
	 * 某个字段值是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	boolean isRepeat(String fieldName,Object fieldValue,Long id);
}
