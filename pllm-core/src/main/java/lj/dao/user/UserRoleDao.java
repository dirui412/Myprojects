package lj.dao.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.user.UserRole;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;
import lj.util.query.QueryObject;


@Repository
public class UserRoleDao extends JdbcDao<UserRole> implements IUserRoleDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public UserRole find(long userRoleId){
		String sql="select * from UserRole where userRoleId=:userRoleId";
		UserRole obj=this.find(sql,"userRoleId",userRoleId);
		return obj;
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(UserRole obj){
	    String sql="insert into UserRole(roleId,userId) ";
		sql=sql+" values(?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getRoleId(),obj.getUserId());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(UserRole obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update UserRole set ";
		sql=sql+" roleId=?,userId=?";
		sql=sql+" where userRoleId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getRoleId(),obj.getUserId(),obj.getUserRoleId());
		if (intRet <= 0)
			msg = BaseServiceConst.MSG_UPDATE_FAIL;
		return msg;
	}
	
	/**
	 * 更新角色权限
	 */
	@Override
	public String updateUserRoles(long userId,Long[] roleIds){
		String msg=StringUtils.STR_EMPTY;
		if(roleIds!=null){
			//1-删除不在moduleIds的角色权限
			String sql="delete from UserRole where userId=:userId and roleId not in (:roleIds) ";
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("userId", userId);
			params.put("roleIds", Arrays.asList(roleIds));
			this.execute(sql, params);
			//2-查询已经存在的角色权限
			sql="select * from UserRole where userId=:userId and roleId in (:roleIds) ";
			List<UserRole> existUserRoles=this.findAll(sql,params);
			List<Long> existsRoleIds=new ArrayList<Long>();
			for(UserRole temp:existUserRoles)
				existsRoleIds.add(temp.getRoleId());
			//3-新增不存在的角色权限
			List<Long> newList=new ArrayList<Long>();
			for(Long roleId:roleIds)
				if(Arrays.binarySearch(existsRoleIds.toArray(new Long[0]), roleId)<0)
					newList.add(roleId);
			for(Long roleId:newList)
			{
				UserRole obj=new UserRole(null,userId,roleId);
				long newId=this.insert(obj);
				if(newId<0)
					return BaseServiceConst.MSG_UPDATE_FAIL;
			}
		}
		else{
			String sql="delete from UserRole where userId=:userId";
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("userId", userId);
			this.execute(sql, params);
		}
		return msg;
	}
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	@Override
	public Pager<UserRole> findAllPaged(){
		String sql="select * from UserRole";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
		if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("roleId");
		Pager<UserRole> pager=super.findAllPaged(query.sql, query.params);
		return pager;
	}
	
	
	
}
