package lj.dao.user;

import lj.dao.base.IJdbcDao;
import lj.model.user.ViUserRolePriv;

public interface IViUserRolePrivDao extends IJdbcDao<ViUserRolePriv> {
	/**
	 * 根据用户标识查询用户权限
	 * @param userId
	 * @return
	 */
	ViUserRolePriv[] findAllByUserId(long userId);
}
