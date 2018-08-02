package lj.dao.maintain;

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
import lj.model.maintain.ViRepairInfo;


@Repository
public class ViRepairInfoDao extends JdbcDao<ViRepairInfo> implements IViRepairInfoDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ViRepairInfo find(Long repairId){
		String sql="select * from ViRepairInfo where repairId=:repairId";
		ViRepairInfo obj=this.find(sql,"repairId",repairId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public ViRepairInfo[] findAll()
	{
		String sql="select * from ViRepairInfo";
		List<ViRepairInfo> list=this.findAll(sql);
		return list.toArray(new ViRepairInfo[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(ViRepairInfo obj){
	    String sql="insert into ViRepairInfo(userEquipmentId,repairTime,repairType,repairMemo,userProductLineName,productLineState,groupName,equipmentId,equipmentName,userProductLineId) ";
		sql=sql+" values(?,?,?,?,?,?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getUserEquipmentId(),obj.getRepairTime(),obj.getRepairType(),obj.getRepairMemo(),obj.getUserProductLineName(),obj.getProductLineState(),obj.getGroupName(),obj.getEquipmentId(),obj.getEquipmentName(),obj.getUserProductLineId());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(ViRepairInfo obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update ViRepairInfo set ";
		sql=sql+" userEquipmentId=?,repairTime=?,repairType=?,repairMemo=?,userProductLineName=?,productLineState=?,groupName=?,equipmentId=?,equipmentName=?,userProductLineId=?";
		sql=sql+" where repairId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getUserEquipmentId(),obj.getRepairTime(),obj.getRepairType(),obj.getRepairMemo(),obj.getUserProductLineName(),obj.getProductLineState(),obj.getGroupName(),obj.getEquipmentId(),obj.getEquipmentName(),obj.getUserProductLineId(),obj.getRepairId());
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
		String sql="delete from ViRepairInfo where repairId=:repairId";
		int ret=this.execute(sql, "repairId",id);
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
	public Pager<ViRepairInfo> findAllPaged(String userProductLineName,String equipmentName  ){
		String sql="select * from ViRepairInfo";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userProductLineName",userProductLineName);
		params.put("equipmentName", equipmentName);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("repairId");
		Pager<ViRepairInfo> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from ViRepairInfo where "+fieldName+"=:"+fieldName;
		ViRepairInfo oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getRepairId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
