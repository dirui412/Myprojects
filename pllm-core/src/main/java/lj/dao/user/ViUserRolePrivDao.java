package lj.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.user.ViUserRolePriv;

@Repository
public class ViUserRolePrivDao extends JdbcDao<ViUserRolePriv> implements IViUserRolePrivDao {

	@Override
	public ViUserRolePriv[] findAllByUserId(long userId){
		String sql="select * from ViUserRolePriv where userId=:userId";
		List<ViUserRolePriv> list=this.findAll(sql, "userId", userId);
		ViUserRolePriv[] objs=list.toArray(new ViUserRolePriv[0]);
		return objs;
	}
}