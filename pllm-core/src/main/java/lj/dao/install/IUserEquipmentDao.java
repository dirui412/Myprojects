package lj.dao.install;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.install.UserEquipment;

public interface IUserEquipmentDao extends IJdbcDao<UserEquipment> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	UserEquipment find(Long id);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	UserEquipment[] findAll();
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	long insert(UserEquipment obj);
	
	/**
	 * 更新对象
	 * @param obj
	 * @return
	 */
	String update(UserEquipment obj);
	
	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
	String delete(Long id);
	
	/**
	 * 删除很多对象
	 * @param id
	 * @return
	 */
	String deleteByUserProductLineId(Long userProductLineId);
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	Pager<UserEquipment> findAllPaged();
	
	/**
	 * 某个字段值是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	boolean isRepeat(String fieldName,Object fieldValue,Long id);

	UserEquipment[] findByUserProductLineId(Long userProductLineId);

	
	
}
