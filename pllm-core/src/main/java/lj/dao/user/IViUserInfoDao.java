package lj.dao.user;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.user.ViUserInfo;

public interface IViUserInfoDao extends IJdbcDao<ViUserInfo> {
	/**
	 * 根据标识查询对象
	 * 
	 * @param id
	 * @return
	 */
	ViUserInfo find(long id);

	ViUserInfo[] findAll();

	/**
	 * 分页查询
	 * 
	 * @param pageIndex：请求页index
	 * @param sortField：排序字段
	 * @param sortType：排序类型(asc/desc)
	 * @return
	 */
	Pager<ViUserInfo> findAllPaged(String queryUserName, String queryUserCode);
	
	
	/**
	 * 分页查询登录人员所在的用户信息
	 * @param queryUserName
	 * @param queryUserCode
	 * @param queryGroupId
	 * @return
	 */
	Pager<ViUserInfo> findAllPaged(String queryUserName, String queryUserCode,Long queryGroupId);
}
