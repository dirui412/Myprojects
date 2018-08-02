package lj.dao.sys;

import java.util.Map;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.sys.RolePriv;

public interface IRolePrivDao extends IJdbcDao<RolePriv> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	RolePriv find(long id);
	

	
	/**
	 * 根据角色查询角色权限
	 * @param roleId
	 * @return
	 */
	RolePriv[] findAllByRoleId(long roleId);
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	long insert(RolePriv obj);
	
	/**
	 * 更新对象
	 * @param obj
	 * @return
	 */
	String update(RolePriv obj);
	
	/**
	 * 更新角色权限
	 * @param RoleId
	 * @param moduleIds
	 * @return
	 */
	String updateRolePrivs(long RoleId,Long[] moduleIds);
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	Pager<RolePriv> findAllPaged();
}
