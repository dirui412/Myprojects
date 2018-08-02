package lj.dao.install;

import java.util.Map;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.install.ViUserEquipment;

public interface IViUserEquipmentDao extends IJdbcDao<ViUserEquipment> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	ViUserEquipment find(Long id);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	ViUserEquipment[] findAll();
	
	ViUserEquipment[] findAllByUserProductLineId(Long userProductLineId);
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	long insert(ViUserEquipment obj);
	
	/**
	 * 更新对象
	 * @param obj
	 * @return
	 */
	String update(ViUserEquipment obj);
	
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
	Pager<ViUserEquipment> findAllPaged(Long userProductLineId,String equipmentName  );
	
	/**
	 * 某个字段值是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	boolean isRepeat(String fieldName,Object fieldValue,Long id);
}
