package lj.dao.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.basic.EquipmentParam;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;
import lj.util.query.QueryObject;


@Repository
public class EquipmentParamDao extends JdbcDao<EquipmentParam> implements IEquipmentParamDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public EquipmentParam find(Long equipmentParamId){
		String sql="select * from EquipmentParam where equipmentParamId=:equipmentParamId";
		EquipmentParam obj=this.find(sql,"equipmentParamId",equipmentParamId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public EquipmentParam[] findAll()
	{
		String sql="select * from EquipmentParam";
		List<EquipmentParam> list=this.findAll(sql);
		return list.toArray(new EquipmentParam[0]);
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public EquipmentParam[] findAllByEquipmentId(Long equipmentId)
	{
		String sql="select * from EquipmentParam where equipmentId=:equipmentId ";
		List<EquipmentParam> list=this.findAll(sql,"equipmentId",equipmentId);
		return list.toArray(new EquipmentParam[0]);
	}
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(EquipmentParam obj){
	    String sql="insert into EquipmentParam(equipmentId,paramName,paramType,paramUp,paramDown) ";
		sql=sql+" values(?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getEquipmentId(),obj.getParamName(),obj.getParamType(),obj.getParamUp(),obj.getParamDown());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(EquipmentParam obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update EquipmentParam set ";
		sql=sql+" equipmentId=?,paramName=?,paramType=?,paramUp=?,paramDown=?";
		sql=sql+" where equipmentParamId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getEquipmentId(),obj.getParamName(),obj.getParamType(),obj.getParamUp(),obj.getParamDown(),obj.getEquipmentParamId());
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
		String sql="delete from EquipmentParam where equipmentParamId=:equipmentParamId";
		int ret=this.execute(sql, "equipmentParamId",id);
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
	public Pager<EquipmentParam> findAllPaged(){
		String sql="select * from EquipmentParam";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("equipmentParamId");
		Pager<EquipmentParam> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from EquipmentParam where "+fieldName+"=:"+fieldName;
		EquipmentParam oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getEquipmentParamId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
