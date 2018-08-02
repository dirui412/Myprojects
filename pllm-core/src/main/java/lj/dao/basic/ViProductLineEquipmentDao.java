package lj.dao.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.basic.ViProductLineEquipment;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;
import lj.util.query.QueryObject;


@Repository
public class ViProductLineEquipmentDao extends JdbcDao<ViProductLineEquipment> implements IViProductLineEquipmentDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ViProductLineEquipment find(Long productLineEquipmentId){
		String sql="select * from ViProductLineEquipment where productLineEquipmentId=:productLineEquipmentId";
		ViProductLineEquipment obj=this.find(sql,"productLineEquipmentId",productLineEquipmentId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public ViProductLineEquipment[] findAll()
	{
		String sql="select * from ViProductLineEquipment";
		List<ViProductLineEquipment> list=this.findAll(sql);
		return list.toArray(new ViProductLineEquipment[0]);
	}
	
	/**
	 * 查询所有生产线标识
	 * @param productLineId
	 * @return
	 */
	public ViProductLineEquipment[] findAllByProductLineId(Long productLineId)
	{
		String sql="select * from ViProductLineEquipment where productLineId=:productLineId";
		List<ViProductLineEquipment> productLineEquipments=this.findAll(sql, "productLineId", productLineId);
		return productLineEquipments.toArray(new ViProductLineEquipment[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(ViProductLineEquipment obj){
	    String sql="insert into ViProductLineEquipment(productLineId,equipmentId,productName,equipmentName) ";
		sql=sql+" values(?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getProductLineId(),obj.getEquipmentId(),obj.getProductName(),obj.getEquipmentName());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(ViProductLineEquipment obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update ViProductLineEquipment set ";
		sql=sql+" productLineId=?,equipmentId=?,productName=?,equipmentName=?";
		sql=sql+" where productLineEquipmentId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getProductLineId(),obj.getEquipmentId(),obj.getProductName(),obj.getEquipmentName(),obj.getProductLineEquipmentId());
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
		String sql="delete from ViProductLineEquipment where productLineEquipmentId=:productLineEquipmentId";
		int ret=this.execute(sql, "productLineEquipmentId",id);
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
	public Pager<ViProductLineEquipment> findAllPaged(String productName , String equipmentName  ){
		String sql="select * from ViProductLineEquipment";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("productName", productName);
		params.put("equipmentName", equipmentName);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("productLineEquipmentId");
		Pager<ViProductLineEquipment> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from ViProductLineEquipment where "+fieldName+"=:"+fieldName;
		ViProductLineEquipment oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getProductLineEquipmentId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
