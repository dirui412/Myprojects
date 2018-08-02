package lj.dao.user;

import lj.dao.base.IJdbcDao;
import lj.model.user.UserInfo;

public interface IUserInfoDao extends IJdbcDao<UserInfo> {
	UserInfo find(long id);
	
	UserInfo findByUsercode(String userCode);
	
	long insert(UserInfo obj);
	
	String update(UserInfo obj);
	
	String delete(long id);
	
	boolean isInuse(long id);
	
	String changePassword(long id,String userPassword);
	
}
