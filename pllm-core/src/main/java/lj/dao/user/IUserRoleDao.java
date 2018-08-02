package lj.dao.user;

import java.util.Map;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.user.UserRole;

public interface IUserRoleDao extends IJdbcDao<UserRole> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	UserRole find(long id);
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	long insert(UserRole obj);
	
	/**
	 * 更新对象
	 * @param obj
	 * @return
	 */
	String update(UserRole obj);
	
	String updateUserRoles(long userId,Long[] roleIds);
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	Pager<UserRole> findAllPaged();
	
	
}
