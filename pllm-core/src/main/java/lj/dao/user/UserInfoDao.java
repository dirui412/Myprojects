package lj.dao.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.user.UserInfo;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;

@Repository
public class UserInfoDao extends JdbcDao<UserInfo> implements IUserInfoDao {
	
	public UserInfo find(long userId){
		String sql="select * from UserInfo where userId=:userId";
		UserInfo obj=this.find(sql,"userId",userId);
		return obj;
	}
	
	public UserInfo findByUsercode(String userCode){
		String sql="select * from UserInfo where userCode=:userCode";
		UserInfo obj=this.find(sql,"userCode",userCode);
		return obj;
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(UserInfo obj){
	    String sql="insert into UserInfo(groupId,userName,userSex,userPhone,userCode,userPassword,registerTime,userState,lastLoginTime,opTime,opUserId) ";
		sql=sql+" values(?,?,?,?,?,?,?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getGroupId(),obj.getUserName(),obj.getUserSex(),obj.getUserPhone(),
				obj.getUserCode(),obj.getUserPassword(),obj.getRegisterTime(),
				obj.getUserState(),obj.getLastLoginTime(),obj.getOpTime(),obj.getOpUserId());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(UserInfo obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update UserInfo set ";
		sql=sql+" groupId=?,userName=?,userSex=?,userPhone=?,userCode=?,userPassword=?,registerTime=?,opTime=?,opUserId=?,userState=?";
		sql=sql+" where userId=?";
//		System.out.println("UserInfo.update userName:"+obj.getUserName()+",userSex:"+obj.getUserSex());
//		System.out.println("UserInfo.update userPhone:"+obj.getUserPhone()+",userCode:"+obj.getUserCode());
//		System.out.println("UserInfo.update userPassword:"+obj.getUserPassword()+",registerTime:"+obj.getRegisterTime());
//		System.out.println("UserInfo.update opTime:"+obj.getOpTime()+",opUserId:"+obj.getOpUserId());
//		System.out.println("UserInfo.update userRfid:"+obj.getUserRfid());
		int intRet = this.jdbcTemplate.update(sql
				,obj.getGroupId(),obj.getUserName(),obj.getUserSex(),obj.getUserPhone(),obj.getUserCode(),
		obj.getUserPassword(),obj.getRegisterTime(),obj.getOpTime(),
		obj.getOpUserId(),obj.getUserState(),obj.getUserId());
		if (intRet <= 0)
			msg = BaseServiceConst.MSG_UPDATE_FAIL;
		return msg;
	}
	
	public String delete(long id){
		String sql="delete from UserInfo where userId=:userId";
		int ret=this.execute(sql, "userId",id);
		if(ret>0)
			return StringUtils.STR_EMPTY;
		else
			return BaseServiceConst.MSG_DELETE_FAIL;
	}
	
	/**
	 * 是否使用
	 */
	public boolean isInuse(long id){
		
		String sql="select count(*) from GroupInfo where opUserId=:opUserId";
		Long count=this.queryCount(sql, "opUserId", id);
		if(count>0)
			return true;
		
		sql="select count(*) from UserInfo where opUserId=:opUserId";
		count=this.queryCount(sql, "opUserId", id);
		if(count>0)
			return true;
	
		
	    return false;
	}
	
	/**
	 * 更新密码
	 */
	@Override
	public String changePassword(long id,String userPassword){
		String sql="update UserInfo set userPassword=:userPassword where userId=:userId";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId", id);
		params.put("userPassword", userPassword);
		int ret=this.execute(sql, params);
		if(ret>0)
			return StringUtils.STR_EMPTY;
		else
			return BaseServiceConst.MSG_UPDATE_FAIL;
	}
	

	
}
