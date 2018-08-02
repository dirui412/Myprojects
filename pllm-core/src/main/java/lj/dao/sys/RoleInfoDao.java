package lj.dao.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.sys.RoleInfo;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;
import lj.util.query.QueryObject;


@Repository
public class RoleInfoDao extends JdbcDao<RoleInfo> implements IRoleInfoDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public RoleInfo find(long roleId){
		String sql="select * from RoleInfo where roleId=:roleId";
		RoleInfo obj=this.find(sql,"roleId",roleId);
		return obj;
	}
	
	public RoleInfo[] findAll(){
		String sql="select * from RoleInfo";
		List<RoleInfo> list=this.findAll(sql);
		RoleInfo[] objs=list.toArray(new RoleInfo[0]);
		return objs;
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(RoleInfo obj){
	    String sql="insert into RoleInfo(roleName,roleMemo) ";
		sql=sql+" values(?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getRoleName(),obj.getRoleMemo());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(RoleInfo obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update RoleInfo set ";
		sql=sql+" roleName=?,roleMemo=?";
		sql=sql+" where roleId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getRoleName(),obj.getRoleMemo(),obj.getRoleId());
		if (intRet <= 0)
			msg = BaseServiceConst.MSG_UPDATE_FAIL;
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
	public Pager<RoleInfo> findAllPaged(String queryRoleName){
		String sql="select * from RoleInfo";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("roleName", queryRoleName);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
		if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("roleId");
		Pager<RoleInfo> pager=super.findAllPaged(query.sql, query.params);
		return pager;
	}
	
}
