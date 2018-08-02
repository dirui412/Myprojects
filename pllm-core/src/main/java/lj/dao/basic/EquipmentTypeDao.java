package lj.dao.basic;

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
import lj.model.basic.EquipmentType;


@Repository
public class EquipmentTypeDao extends JdbcDao<EquipmentType> implements IEquipmentTypeDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public EquipmentType find(Long equipmentTypeId){
		String sql="select * from EquipmentType where equipmentTypeId=:equipmentTypeId";
		EquipmentType obj=this.find(sql,"equipmentTypeId",equipmentTypeId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public EquipmentType[] findAll()
	{
		String sql="select * from EquipmentType";
		List<EquipmentType> list=this.findAll(sql);
		return list.toArray(new EquipmentType[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(EquipmentType obj){
	    String sql="insert into EquipmentType(equipmentTypeName,equipmentTypeDescribe) ";
		sql=sql+" values(?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getEquipmentTypeName(),obj.getEquipmentTypeDescribe());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(EquipmentType obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update EquipmentType set ";
		sql=sql+" equipmentTypeName=?,equipmentTypeDescribe=?";
		sql=sql+" where equipmentTypeId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getEquipmentTypeName(),obj.getEquipmentTypeDescribe(),obj.getEquipmentTypeId());
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
		String sql="delete from EquipmentType where equipmentTypeId=:equipmentTypeId";
		int ret=this.execute(sql, "equipmentTypeId",id);
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
	public Pager<EquipmentType> findAllPaged(Long equipmentTypeId , String equipmentTypeName , String equipmentTypeDescribe  ){
		String sql="select * from EquipmentType";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("equipmentTypeId", equipmentTypeId);
		params.put("equipmentTypeName", equipmentTypeName);
		params.put("equipmentTypeDescribe", equipmentTypeDescribe);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("equipmentTypeId");
		Pager<EquipmentType> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from EquipmentType where "+fieldName+"=:"+fieldName;
		EquipmentType oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getEquipmentTypeId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
