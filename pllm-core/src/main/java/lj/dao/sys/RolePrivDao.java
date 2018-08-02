package lj.dao.sys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.sys.RolePriv;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;
import lj.util.query.QueryObject;


@Repository
public class RolePrivDao extends JdbcDao<RolePriv> implements IRolePrivDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public RolePriv find(long privId){
		String sql="select * from RolePriv where privId=:privId";
		RolePriv obj=this.find(sql,"privId",privId);
		return obj;
	}
	

	
	public RolePriv[] findAllByRoleId(long roleId){
		String sql="select * from RolePriv where roleId=:roleId";
		List<RolePriv> list=this.findAll(sql, "roleId", roleId);
		RolePriv[] objs=list.toArray(new RolePriv[0]);
		return objs;
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(RolePriv obj){
	    String sql="insert into RolePriv(roleId,moduleId) ";
		sql=sql+" values(?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getRoleId(),obj.getModuleId());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(RolePriv obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update RolePriv set ";
		sql=sql+" roleId=?,moduleId=?,allowCreate=?,allowRetrieve=?,allowUpdate=?,allowDelete=?,allowImport=?,allowExport=?";
		sql=sql+" where privId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getRoleId(),obj.getModuleId(),obj.getAllowCreate(),obj.getAllowRetrieve(),obj.getAllowUpdate(),obj.getAllowDelete(),obj.getAllowImport(),obj.getAllowExport(),obj.getPrivId());
		if (intRet <= 0)
			msg = BaseServiceConst.MSG_UPDATE_FAIL;
		return msg;
	}
	
	/**
	 * 更新角色权限
	 */
	@Override
	public String updateRolePrivs(long roleId,Long[] moduleIds){
		String msg=StringUtils.STR_EMPTY;
		//1-删除不在moduleIds的角色权限
		String sql="delete from RolePriv where roleId=:roleId and moduleId not in (:moduleIds) ";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("roleId", roleId);
		params.put("moduleIds", Arrays.asList(moduleIds));
		int count=this.execute(sql, params);
		//System.out.println("updateRolePrivs delete count:"+count);
		//2-查询已经存在的角色权限
		sql="select * from RolePriv where roleId=:roleId and moduleId in (:moduleIds) ";
		List<RolePriv> existRolePrivs=this.findAll(sql,params);
		Set<Long> existsModuleIdList=new HashSet<Long>();
		for(RolePriv temp:existRolePrivs)
			existsModuleIdList.add(temp.getModuleId());
		Long[] existsModuleIds=existsModuleIdList.toArray(new Long[0]);
		//System.out.println("updateRolePrivs exist count:"+existsModuleIds.length);
		//3-新增不存在的角色权限
		List<Long> newList=new ArrayList<Long>();
		for(long moduleId:moduleIds)
			if(Arrays.binarySearch(existsModuleIds, moduleId)<0){
				//System.out.println("updateRolePrivs not in moduleId:"+moduleId);
				newList.add(moduleId);
			}
		for(Long moduleId:newList)
		{
			RolePriv obj=new RolePriv(null,roleId,moduleId);
			long newId=this.insert(obj);
			if(newId<0)
				return BaseServiceConst.MSG_UPDATE_FAIL;
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
	public Pager<RolePriv> findAllPaged(){
		String sql="select * from RolePriv";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
		if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("privId");
		Pager<RolePriv> pager=super.findAllPaged(query.sql, query.params);
		return pager;
	}
	
}
