package lj.dao.collect;

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
import lj.model.collect.ViProductLineCollect;


@Repository
public class ViProductLineCollectDao extends JdbcDao<ViProductLineCollect> implements IViProductLineCollectDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ViProductLineCollect find(Long productLineCollectId){
		String sql="select * from ViProductLineCollect where productLineCollectId=:productLineCollectId";
		ViProductLineCollect obj=this.find(sql,"productLineCollectId",productLineCollectId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public ViProductLineCollect[] findAll()
	{
		String sql="select * from ViProductLineCollect";
		List<ViProductLineCollect> list=this.findAll(sql);
		return list.toArray(new ViProductLineCollect[0]);
	}
	
	
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(ViProductLineCollect obj){
	    String sql="insert into ViProductLineCollect(userProductLineId,productLineParamId,collectTime,paramState,paramValue,groupId,groupName,productLineId,productName,paramName,paramType,paramUp,paramDown) ";
		sql=sql+" values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getUserProductLineId(),obj.getProductLineParamId(),obj.getCollectTime(),obj.getParamState(),obj.getParamValue(),obj.getGroupId(),obj.getGroupName(),obj.getProductLineId(),obj.getProductName(),obj.getParamName(),obj.getParamType(),obj.getParamUp(),obj.getParamDown());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(ViProductLineCollect obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update ViProductLineCollect set ";
		sql=sql+" userProductLineId=?,productLineParamId=?,collectTime=?,paramState=?,paramValue=?,groupId=?,groupName=?,productLineId=?,productName=?,paramName=?,paramType=?,paramUp=?,paramDown=?";
		sql=sql+" where productLineCollectId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getUserProductLineId(),obj.getProductLineParamId(),obj.getCollectTime(),obj.getParamState(),obj.getParamValue(),obj.getGroupId(),obj.getGroupName(),obj.getProductLineId(),obj.getProductName(),obj.getParamName(),obj.getParamType(),obj.getParamUp(),obj.getParamDown(),obj.getProductLineCollectId());
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
		String sql="delete from ViProductLineCollect where productLineCollectId=:productLineCollectId";
		int ret=this.execute(sql, "productLineCollectId",id);
		if(ret>0)
			return StringUtils.STR_EMPTY;
		else
			return BaseServiceConst.MSG_DELETE_FAIL;
	}
	
	public Pager<ViProductLineCollect> findAllPaged(Long groupId , String userProductLineName , String paramName )
	{
		String sql="select * from ViProductLineCollect";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("groupId", groupId);
		params.put("userProductLineName", userProductLineName);
		params.put("paramName", paramName);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("productLineCollectId");
		Pager<ViProductLineCollect> pager=super.findAllPaged(query.sql, query.params);
		return pager;
	}
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	@Override
	public Pager<ViProductLineCollect> findAllPaged(Long userProductLineId){
		String sql="select * from ViProductLineCollect";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userProductLineId", userProductLineId);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("productLineCollectId");
		Pager<ViProductLineCollect> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from ViProductLineCollect where "+fieldName+"=:"+fieldName;
		ViProductLineCollect oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getProductLineCollectId().equals(id)==true)
			return false;
		else
			return true;
	}
	

}
