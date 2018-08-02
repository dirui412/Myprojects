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
import lj.model.collect.EquipmentCollect;


@Repository
public class EquipmentCollectDao extends JdbcDao<EquipmentCollect> implements IEquipmentCollectDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public EquipmentCollect find(Long equipmentCollectId){
		String sql="select * from EquipmentCollect where equipmentCollectId=:equipmentCollectId";
		EquipmentCollect obj=this.find(sql,"equipmentCollectId",equipmentCollectId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public EquipmentCollect[] findAll()
	{
		String sql="select * from EquipmentCollect";
		List<EquipmentCollect> list=this.findAll(sql);
		return list.toArray(new EquipmentCollect[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(EquipmentCollect obj){
	    String sql="insert into EquipmentCollect(equipmentParamId,userEquipmentId,collectTime,paramState,paramValue) ";
		sql=sql+" values(?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getEquipmentParamId(),obj.getUserEquipmentId(),obj.getCollectTime(),obj.getParamState(),obj.getParamValue());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(EquipmentCollect obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update EquipmentCollect set ";
		sql=sql+" equipmentParamId=?,userEquipmentId=?,collectTime=?,paramState=?,paramValue=?";
		sql=sql+" where equipmentCollectId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getEquipmentParamId(),obj.getUserEquipmentId(),obj.getCollectTime(),obj.getParamState(),obj.getParamValue(),obj.getEquipmentCollectId());
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
		String sql="delete from EquipmentCollect where equipmentCollectId=:equipmentCollectId";
		int ret=this.execute(sql, "equipmentCollectId",id);
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
	public Pager<EquipmentCollect> findAllPaged(){
		String sql="select * from EquipmentCollect";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("equipmentCollectId");
		Pager<EquipmentCollect> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from EquipmentCollect where "+fieldName+"=:"+fieldName;
		EquipmentCollect oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getEquipmentCollectId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
