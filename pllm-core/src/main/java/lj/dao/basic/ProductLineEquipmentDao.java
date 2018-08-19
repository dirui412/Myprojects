package lj.dao.basic;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
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
import lj.model.basic.ProductLineEquipment;
import lj.model.user.UserRole;


@Repository
public class ProductLineEquipmentDao extends JdbcDao<ProductLineEquipment> implements IProductLineEquipmentDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ProductLineEquipment find(Long productLineEquipmentId){
		String sql="select * from ProductLineEquipment where productLineEquipmentId=:productLineEquipmentId";
		ProductLineEquipment obj=this.find(sql,"productLineEquipmentId",productLineEquipmentId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public ProductLineEquipment[] findAll()
	{
		String sql="select * from ProductLineEquipment";
		List<ProductLineEquipment> list=this.findAll(sql);
		return list.toArray(new ProductLineEquipment[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(ProductLineEquipment obj){
	    String sql="insert into ProductLineEquipment(productLineId,equipmentId) ";
		sql=sql+" values(?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getProductLineId(),obj.getEquipmentId());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(ProductLineEquipment obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update ProductLineEquipment set ";
		sql=sql+" productLineId=?,equipmentId=?";
		sql=sql+" where productLineEquipmentId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getProductLineId(),obj.getEquipmentId(),obj.getProductLineEquipmentId());
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
		String sql="delete from ProductLineEquipment where productLineEquipmentId=:productLineEquipmentId";
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
	public Pager<ProductLineEquipment> findAllPaged(){
		String sql="select * from ProductLineEquipment";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("productLineEquipmentId");
		Pager<ProductLineEquipment> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from ProductLineEquipment where "+fieldName+"=:"+fieldName;
		ProductLineEquipment oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getProductLineEquipmentId().equals(id)==true)
			return false;
		else
			return true;
	}
	
	/**
	 * 更新设备生产线
	 */
	@Override
	public String updateProductLineEquipments(Long productLineId,Long[] equipmentIds){
		String msg=StringUtils.STR_EMPTY;
		//0-取消所有设备
		if(equipmentIds==null || equipmentIds.length<=0)
		{
			String sql="delete from ProductLineEquipment where productLineId=:productLineId";
			this.execute(sql, "productLineId", productLineId);
			return StringUtils.STR_EMPTY;
		}
		//1-删除没有选定的生产线设备
		String sql="delete from ProductLineEquipment where productLineId=:productLineId and equipmentId not in (:equipmentIds) ";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("productLineId", productLineId);
		params.put("equipmentIds", Arrays.asList(equipmentIds));
		this.execute(sql, params);
		//2-查询已经存在的生产线设备
		sql="select * from ProductLineEquipment where productLineId=:productLineId and equipmentId in (:equipmentIds) ";
		List<ProductLineEquipment> existProductLineEquipments=this.findAll(sql,params);
		List<Long> existsEquipmentIdsList=new ArrayList<Long>();
		for(ProductLineEquipment temp:existProductLineEquipments)
			existsEquipmentIdsList.add(temp.getEquipmentId());
		Long[] existsEquipmentIds = existsEquipmentIdsList.toArray(new Long[0]);
		Arrays.sort(existsEquipmentIds);			//Attention！使用Array的二分查找方法必须先使用sort函数对其进行排序
		//3-新增不存在的生产线设备
		List<Long> newIds=new ArrayList<Long>();
		for(Long equipmentId:equipmentIds)
			if(Arrays.binarySearch(existsEquipmentIds, equipmentId)<0)
				newIds.add(equipmentId);
		for(Long equipmentId:newIds)
		{
			ProductLineEquipment obj=new ProductLineEquipment(null,productLineId,equipmentId);
			long newId=this.insert(obj);
			if(newId<0)
				return BaseServiceConst.MSG_UPDATE_FAIL;
		}
		return msg;
	}
	
}
