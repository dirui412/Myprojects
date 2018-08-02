package lj.dao.collect;

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
import lj.model.collect.ProductLineCollect;


@Repository
public class ProductLineCollectDao extends JdbcDao<ProductLineCollect> implements IProductLineCollectDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ProductLineCollect find(Long productLineCollectId){
		String sql="select * from ProductLineCollect where productLineCollectId=:productLineCollectId";
		ProductLineCollect obj=this.find(sql,"productLineCollectId",productLineCollectId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public ProductLineCollect[] findAll()
	{
		String sql="select * from ProductLineCollect";
		List<ProductLineCollect> list=this.findAll(sql);
		return list.toArray(new ProductLineCollect[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(ProductLineCollect obj){
	    String sql="insert into ProductLineCollect(userProductLineId,productLineParamId,collectTime,paramState,paramValue) ";
		sql=sql+" values(?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getUserProductLineId(),obj.getProductLineParamId(),obj.getCollectTime(),obj.getParamState(),obj.getParamValue());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(ProductLineCollect obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update ProductLineCollect set ";
		sql=sql+" userProductLineId=?,productLineParamId=?,collectTime=?,paramState=?,paramValue=?";
		sql=sql+" where productLineCollectId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getUserProductLineId(),obj.getProductLineParamId(),obj.getCollectTime(),obj.getParamState(),obj.getParamValue(),obj.getProductLineCollectId());
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
		String sql="delete from ProductLineCollect where productLineCollectId=:productLineCollectId";
		int ret=this.execute(sql, "productLineCollectId",id);
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
	public Pager<ProductLineCollect> findAllPaged(){
		String sql="select * from ProductLineCollect";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("productLineCollectId");
		Pager<ProductLineCollect> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from ProductLineCollect where "+fieldName+"=:"+fieldName;
		ProductLineCollect oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getProductLineCollectId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
