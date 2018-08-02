package lj.dao.collect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.collect.Viequipmentcollect;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;
import lj.util.query.QueryObject;


@Repository
public class ViequipmentcollectDao extends JdbcDao<Viequipmentcollect> implements IViequipmentcollectDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public Viequipmentcollect find(Long equipmentCollectId){
		String sql="select * from Viequipmentcollect where equipmentCollectId=:equipmentCollectId";
		Viequipmentcollect obj=this.find(sql,"equipmentCollectId",equipmentCollectId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public Viequipmentcollect[] findAll()
	{
		String sql="select * from Viequipmentcollect";
		List<Viequipmentcollect> list=this.findAll(sql);
		return list.toArray(new Viequipmentcollect[0]);
	}
	
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	@Override
	public Pager<Viequipmentcollect> findAllPaged(String userProductLineName , String equipmentName ,String paraName){
		String sql="select * from Viequipmentcollect";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userProductLineName", userProductLineName);
		params.put("equipmentName", equipmentName);
		params.put("paraName", paraName);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("equipmentCollectId");
		Pager<Viequipmentcollect> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from Viequipmentcollect where "+fieldName+"=:"+fieldName;
		Viequipmentcollect oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getEquipmentCollectId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
