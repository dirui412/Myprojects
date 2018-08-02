package lj.dao.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.sys.ModuleInfo;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;


@Repository
public class ModuleInfoDao extends JdbcDao<ModuleInfo> implements IModuleInfoDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ModuleInfo find(long moduleId){
		String sql="select * from ModuleInfo where moduleId=:moduleId";
		ModuleInfo obj=this.find(sql,"moduleId",moduleId);
		return obj;
	}
	
	public ModuleInfo[] findAll(){
		String sql="select * from ModuleInfo ";
		List<ModuleInfo> list=this.findAll(sql);
		ModuleInfo[] objs=list.toArray(new ModuleInfo[0]);
		return objs;
	}
	
	public ModuleInfo[] findAll(String[] moduleIds){
		String sql="select * from ModuleInfo where parentModuleId is not null "
				+" and roleId=:roleId and moduleId not in :moduleIds ";
		List<ModuleInfo> list=this.findAll(sql);
		ModuleInfo[] objs=list.toArray(new ModuleInfo[0]);
		return objs;
	}
	
	public ModuleInfo[] findAllParentModules(){
		String sql="select * from ModuleInfo where parentModuleId is null";
		List<ModuleInfo> list=this.findAll(sql);
		ModuleInfo[] objs=list.toArray(new ModuleInfo[0]);
		return objs;
	}
	
	/**
	 * 查询所有子模块
	 */
	@Override
	public ModuleInfo[] findAllChildModules(long id){
		String sql="select * from ModuleInfo where parentModuleId=:parentModuleId order by moduleCode";
		List<ModuleInfo> list=this.findAll(sql, "parentModuleId", id);
		ModuleInfo[] objs=list.toArray(new ModuleInfo[0]);
		return objs;
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(ModuleInfo obj){
	    String sql="insert into ModuleInfo(parentModuleId,moduleName,moduleCode,moduleTitle,moduleKind,entityName,entityIdName,moduleUrl) ";
		sql=sql+" values(?,?,?,?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getParentModuleId(),obj.getModuleName(),obj.getModuleCode(),obj.getModuleTitle(),obj.getModuleKind(),obj.getEntityName(),obj.getEntityIdName(),obj.getModuleUrl());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(ModuleInfo obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update ModuleInfo set ";
		sql=sql+" parentModuleId=?,moduleName=?,moduleCode=?,moduleTitle=?,moduleKind=?,entityName=?,entityIdName=?,moduleUrl=?";
		sql=sql+" where moduleId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getParentModuleId(),obj.getModuleName(),obj.getModuleCode(),obj.getModuleTitle(),obj.getModuleKind(),obj.getEntityName(),obj.getEntityIdName(),obj.getModuleUrl(),obj.getModuleId());
		if (intRet <= 0)
			msg = BaseServiceConst.MSG_UPDATE_FAIL;
		return msg;
	}
	/**
	 * 是否已经使用
	 * @param areaId
	 * @return
	 */
	@Override
	public boolean isInuse(long id){
		String sql="select count(*) from ModuleInfo where parentModuleId=:parentModuleId";
		long count=this.queryCount(sql, "parentModuleId", id);
		if(count>0)
			return true;

	    return false;
	}
	

	/**
	 *删除
	 */
	@Override
	public String delete(long id){
		String sql="delete from ModuleInfo where moduleId=:moduleId";
		int ret=this.execute(sql, "moduleId",id);
		if(ret>0)
			return StringUtils.STR_EMPTY;
		else
			return BaseServiceConst.MSG_DELETE_FAIL;
	}
	
}
