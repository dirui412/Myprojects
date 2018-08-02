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
import lj.model.basic.ViEquipmentParam;


@Repository
public class ViEquipmentParamDao extends JdbcDao<ViEquipmentParam> implements IViEquipmentParamDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ViEquipmentParam find(Long equipmentParamId){
		String sql="select * from ViEquipmentParam where equipmentParamId=:equipmentParamId";
		ViEquipmentParam obj=this.find(sql,"equipmentParamId",equipmentParamId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public ViEquipmentParam[] findAll()
	{
		String sql="select * from ViEquipmentParam";
		List<ViEquipmentParam> list=this.findAll(sql);
		return list.toArray(new ViEquipmentParam[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(ViEquipmentParam obj){
	    String sql="insert into ViEquipmentParam(equipmentId,paramName,paramType,paramUp,paramDown,equipmentName,equipmentVersion) ";
		sql=sql+" values(?,?,?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getEquipmentId(),obj.getParamName(),obj.getParamType(),obj.getParamUp(),obj.getParamDown(),obj.getEquipmentName(),obj.geteEquipmentVersion());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(ViEquipmentParam obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update ViEquipmentParam set ";
		sql=sql+" equipmentId=?,paramName=?,paramType=?,paramUp=?,paramDown=?,equipmentName=?,equipmentVersion=?";
		sql=sql+" where equipmentParamId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getEquipmentId(),obj.getParamName(),obj.getParamType(),obj.getParamUp(),obj.getParamDown(),obj.getEquipmentName(),obj.geteEquipmentVersion(),obj.getEquipmentParamId());
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
		String sql="delete from ViEquipmentParam where equipmentParamId=:equipmentParamId";
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
	public Pager<ViEquipmentParam> findAllPaged(
			Long queryEquipmentId,String paramName , String paramType  ){
		String sql="select * from ViEquipmentParam";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("equipmentId", queryEquipmentId);
		params.put("paramName", paramName);
		params.put("paramType", paramType);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("equipmentParamId");
		Pager<ViEquipmentParam> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from ViEquipmentParam where "+fieldName+"=:"+fieldName;
		ViEquipmentParam oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getEquipmentParamId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
