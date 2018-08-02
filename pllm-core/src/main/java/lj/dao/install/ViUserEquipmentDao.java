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
import lj.model.install.ViUserEquipment;


@Repository
public class ViUserEquipmentDao extends JdbcDao<ViUserEquipment> implements IViUserEquipmentDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ViUserEquipment find(Long userEquipmentId){
		String sql="select * from ViUserEquipment where userEquipmentId=:userEquipmentId";
		ViUserEquipment obj=this.find(sql,"userEquipmentId",userEquipmentId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public ViUserEquipment[] findAll()
	{
		String sql="select * from ViUserEquipment";
		List<ViUserEquipment> list=this.findAll(sql);
		return list.toArray(new ViUserEquipment[0]);
	}
	
	public ViUserEquipment[] findAllByUserProductLineId(Long userProductLineId)
	{
		String sql="select * from ViUserEquipment where userProductLineId=:userProductLineId";
		List<ViUserEquipment> list=this.findAll(sql,"userProductLineId",userProductLineId);
		return list.toArray(new ViUserEquipment[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(ViUserEquipment obj){
	    String sql="insert into ViUserEquipment(userProductLineId,equipmentId,installTime,equipmentState,installParams,installMemo,groupName,productName,equipmentName) ";
		sql=sql+" values(?,?,?,?,?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getUserProductLineId(),obj.getEquipmentId(),obj.getInstallTime(),obj.getEquipmentState(),obj.getInstallParams(),obj.getInstallMemo(),obj.getGroupName(),obj.getProductName(),obj.getEquipmentName());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(ViUserEquipment obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update ViUserEquipment set ";
		sql=sql+" userProductLineId=?,equipmentId=?,installTime=?,equipmentState=?,installParams=?,installMemo=?,groupName=?,productName=?,equipmentName=?";
		sql=sql+" where userEquipmentId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getUserProductLineId(),obj.getEquipmentId(),obj.getInstallTime(),obj.getEquipmentState(),obj.getInstallParams(),obj.getInstallMemo(),obj.getGroupName(),obj.getProductName(),obj.getEquipmentName(),obj.getUserEquipmentId());
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
		String sql="delete from ViUserEquipment where userEquipmentId=:userEquipmentId";
		int ret=this.execute(sql, "userEquipmentId",id);
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
	public Pager<ViUserEquipment> findAllPaged(Long userProductLineId,String equipmentName  ){
		String sql="select * from ViUserEquipment";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userProductLineId", userProductLineId);
		params.put("equipmentName", equipmentName);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("userEquipmentId");
		Pager<ViUserEquipment> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from ViUserEquipment where "+fieldName+"=:"+fieldName;
		ViUserEquipment oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getUserEquipmentId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
