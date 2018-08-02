package lj.dao.user;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.user.ViUserRole;

public interface IViUserRoleDao extends IJdbcDao<ViUserRole> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	ViUserRole find(long id);
	
	/**
	 * 根据用户标识查询用户角色
	 * @param userId
	 * @return
	 */
	ViUserRole[] findAllByUserId(long userId);
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	Pager<ViUserRole> findAllPaged();
}
