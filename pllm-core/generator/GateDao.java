package lj.dao.install;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.global.PlatformConst;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;
import lj.util.query.QueryObject;
import lj.model.install.Gate;


@Repository
public class GateDao extends JdbcDao<Gate> implements IGateDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public Gate find(Long gateId){
		String sql="select * from Gate where gateId=:gateId";
		Gate obj=this.find(sql,"gateId",gateId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public Gate[] findAll()
	{
		String sql="select * from Gate";
		List<Gate> list=this.findAll(sql);
		return list.toArray(new Gate[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(Gate obj){
	    String sql="insert into Gate(gateName,gateArriveType,gateGoType,gateVersion,priority) ";
		sql=sql+" values(?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getGateName(),obj.getGateArriveType(),obj.getGateGoType(),obj.getGateVersion(),obj.getPriority());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(Gate obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update Gate set ";
		sql=sql+" gateName=?,gateArriveType=?,gateGoType=?,gateVersion=?,priority=?";
		sql=sql+" where gateId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getGateName(),obj.getGateArriveType(),obj.getGateGoType(),obj.getGateVersion(),obj.getPriority(),obj.getGateId());
		if (intRet <= 0)
			msg = BaseServiceConst.MSG_UPDATE_FAIL;
		return msg;
	}
	
	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
	public String delete(Long id)
	{
		String sql="delete from Gate where gateId=:gateId";
		int ret=this.execute(sql, "gateId",id);
		if(ret>0)
			return StringUtils.STR_EMPTY;
		else
			return BaseServiceConst.MSG_DELETE_FAIL;
	}
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	@Override
	public Pager<Gate> findAllPaged(){
		String sql="select * from Gate";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("gateId");
		Pager<Gate> pager=super.findAllPaged(query.sql, query.params);
		return pager;
	}
	
	/**
	 * 某个字段值是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	@Override
	public boolean isRepeat(String fieldName,Object fieldValue,Long id)
	{
		String sql="select * from Gate where "+fieldName+"=:"+fieldName;
		Gate oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getGateId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
