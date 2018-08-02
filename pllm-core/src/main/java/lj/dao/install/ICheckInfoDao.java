package lj.dao.install;

import java.util.Map;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.install.CheckInfo;

public interface ICheckInfoDao extends IJdbcDao<CheckInfo> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	CheckInfo find(Long id);
	
	/**
	 * 根据用户生产线标识查询验收信息
	 * @param userProductLineId
	 * @return
	 */
	CheckInfo findByUserProductLineId(Long userProductLineId);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	CheckInfo[] findAll();
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	long insert(CheckInfo obj);
	
	/**
	 * 更新对象
	 * @param obj
	 * @return
	 */
	String update(CheckInfo obj);
	
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
	Pager<CheckInfo> findAllPaged();
	
	/**
	 * 某个字段值是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	boolean isRepeat(String fieldName,Object fieldValue,Long id);
}
