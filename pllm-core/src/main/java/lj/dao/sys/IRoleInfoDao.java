package lj.dao.sys;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.sys.RoleInfo;

public interface IRoleInfoDao extends IJdbcDao<RoleInfo> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	RoleInfo find(long id);
	
	/**
	 * 查询所有角色信息
	 * @return
	 */
	RoleInfo[] findAll();
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	long insert(RoleInfo obj);
	
	/**
	 * 更新对象
	 * @param obj
	 * @return
	 */
	String update(RoleInfo obj);
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	Pager<RoleInfo> findAllPaged(String queryRoleName);
}
