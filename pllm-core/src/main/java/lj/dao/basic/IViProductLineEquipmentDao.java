package lj.dao.basic;

import java.util.Map;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.basic.ViProductLineEquipment;

public interface IViProductLineEquipmentDao extends IJdbcDao<ViProductLineEquipment> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	ViProductLineEquipment find(Long id);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	ViProductLineEquipment[] findAll();
	
	/**
	 * 查询所有生产线标识
	 * @param productLineId
	 * @return
	 */
	ViProductLineEquipment[] findAllByProductLineId(Long productLineId);
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	long insert(ViProductLineEquipment obj);
	
	/**
	 * 更新对象
	 * @param obj
	 * @return
	 */
	String update(ViProductLineEquipment obj);
	
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
	Pager<ViProductLineEquipment> findAllPaged(String productName , String equipmentName  );
	
	/**
	 * 某个字段值是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	boolean isRepeat(String fieldName,Object fieldValue,Long id);
	
	
}
