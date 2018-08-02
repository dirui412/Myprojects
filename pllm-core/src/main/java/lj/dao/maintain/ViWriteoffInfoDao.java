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
import lj.model.maintain.ViWriteoffInfo;


@Repository
public class ViWriteoffInfoDao extends JdbcDao<ViWriteoffInfo> implements IViWriteoffInfoDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ViWriteoffInfo find(Long writeoffId){
		String sql="select * from ViWriteoffInfo where writeoffId=:writeoffId";
		ViWriteoffInfo obj=this.find(sql,"writeoffId",writeoffId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public ViWriteoffInfo[] findAll()
	{
		String sql="select * from ViWriteoffInfo";
		List<ViWriteoffInfo> list=this.findAll(sql);
		return list.toArray(new ViWriteoffInfo[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(ViWriteoffInfo obj){
	    String sql="insert into ViWriteoffInfo(userEquipmentId,writeoffTime,writeoffType,writeoffMemo,userProductLineName,productLineState,groupName,equipmentId,equipmentName,userProductLineId) ";
		sql=sql+" values(?,?,?,?,?,?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getUserEquipmentId(),obj.getWriteoffTime(),obj.getWriteoffType(),obj.getWriteoffMemo(),obj.getUserProductLineName(),obj.getProductLineState(),obj.getGroupName(),obj.getEquipmentId(),obj.getEquipmentName(),obj.getUserProductLineId());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(ViWriteoffInfo obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update ViWriteoffInfo set ";
		sql=sql+" userEquipmentId=?,writeoffTime=?,writeoffType=?,writeoffMemo=?,userProductLineName=?,productLineState=?,groupName=?,equipmentId=?,equipmentName=?,userProductLineId=?";
		sql=sql+" where writeoffId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getUserEquipmentId(),obj.getWriteoffTime(),obj.getWriteoffType(),obj.getWriteoffMemo(),obj.getUserProductLineName(),obj.getProductLineState(),obj.getGroupName(),obj.getEquipmentId(),obj.getEquipmentName(),obj.getUserProductLineId(),obj.getWriteoffId());
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
		String sql="delete from ViWriteoffInfo where writeoffId=:writeoffId";
		int ret=this.execute(sql, "writeoffId",id);
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
	public Pager<ViWriteoffInfo> findAllPaged(String userProductLineName , String equipmentName  ){
		String sql="select * from ViWriteoffInfo";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userProductLineName", userProductLineName);
		params.put("equipmentName", equipmentName);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("writeoffId");
		Pager<ViWriteoffInfo> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from ViWriteoffInfo where "+fieldName+"=:"+fieldName;
		ViWriteoffInfo oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getWriteoffId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
