package lj.dao.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.basic.Viequipmentinfo;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;
import lj.util.query.QueryObject;


@Repository
public class ViequipmentinfoDao extends JdbcDao<Viequipmentinfo> implements IViequipmentinfoDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public Viequipmentinfo find(Long equipmentId){
		String sql="select * from Viequipmentinfo where equipmentId=:equipmentId";
		Viequipmentinfo obj=this.find(sql,"equipmentId",equipmentId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public Viequipmentinfo[] findAll()
	{
		String sql="select * from Viequipmentinfo";
		List<Viequipmentinfo> list=this.findAll(sql);
		return list.toArray(new Viequipmentinfo[0]);
	}
	/**
	 * 根据类别查询对象数组
	 */
	@Override
	public Viequipmentinfo[] findByType(Long equipmentTypeId)
	{
		String sql="select * from Viequipmentinfo where equipmentTypeId=:equipmentTypeId";
		List<Viequipmentinfo> list=this.findAll(sql,"equipmentTypeId",equipmentTypeId);
		return list.toArray(new Viequipmentinfo[0]);
	}
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(Viequipmentinfo obj){
	    String sql="insert into Viequipmentinfo(equipmentTypeId,equipmentName,equipmentVersion,equipmentConfig,equipmentMemo,equipmentPicture,equipmentTypeName) ";
		sql=sql+" values(?,?,?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getEquipmentTypeId(),obj.getEquipmentName(),obj.getEquipmentVersion()
				,obj.getEquipmentConfig(),obj.getEquipmentMemo(),obj.getEquipmentPicture(),obj.getEquipmentTypeName());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(Viequipmentinfo obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update Viequipmentinfo set ";
		sql=sql+" equipmentTypeId=?,equipmentName=?,equipmentVersion=?,equipmentConfig=?,equipmentMemo=?,equipmentPicture=?,equipmentTypeName=?";
		sql=sql+" where equipmentId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getEquipmentTypeId(),obj.getEquipmentName(),obj.getEquipmentVersion(),obj.getEquipmentConfig(),
		obj.getEquipmentMemo(),obj.getEquipmentPicture(),obj.getEquipmentTypeName(),obj.getEquipmentId());
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
		String sql="delete from Viequipmentinfo where equipmentId=:equipmentId";
		int ret=this.execute(sql, "equipmentId",id);
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
	public Pager<Viequipmentinfo> findAllPaged(Long equipmentId , Long equipmentTypeId , String equipmentName , String equipmentType , String equipmentConfig , String equipmentMemo ,String equipmentPicture,String equipmentTypeName  ){
		String sql="select * from Viequipmentinfo";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("equipmentId", equipmentId);
		params.put("equipmentTypeId", equipmentTypeId);
		params.put("equipmentName", equipmentName);
		params.put("equipmentType", equipmentType);
		params.put("equipmentConfig", equipmentConfig);
		params.put("equipmentMemo", equipmentMemo);
		params.put("equipmentPicture", equipmentPicture);
		params.put("equipmentTypeName", equipmentTypeName);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("equipmentId");
		Pager<Viequipmentinfo> pager=super.findAllPaged(query.sql, query.params);
		return pager;
	}
	
	@Override
	public Pager<Viequipmentinfo> findAllPaged(){
		String sql="select * from Viequipmentinfo";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("equipmentId");
		Pager<Viequipmentinfo> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from Viequipmentinfo where "+fieldName+"=:"+fieldName;
		Viequipmentinfo oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getEquipmentId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
