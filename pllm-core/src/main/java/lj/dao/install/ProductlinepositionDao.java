package lj.dao.install;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.install.Productlineposition;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;
import lj.util.query.QueryObject;


@Repository
public class ProductlinepositionDao extends JdbcDao<Productlineposition> implements IProductlinepositionDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public Productlineposition find(Long productLinePositionId){
		String sql="select * from Productlineposition where productLinePositionId=:productLinePositionId";
		Productlineposition obj=this.find(sql,"productLinePositionId",productLinePositionId);
		return obj;
	}
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public Productlineposition findByUserProductLineId(Long userProductLineId){
		String sql="select * from Productlineposition where userProductLineId=:userProductLineId";
		Productlineposition obj=this.find(sql,"userProductLineId",userProductLineId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public Productlineposition[] findAll()
	{
		String sql="select * from Productlineposition";
		List<Productlineposition> list=this.findAll(sql);
		return list.toArray(new Productlineposition[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(Productlineposition obj){
	    String sql="insert into Productlineposition(userProductLineId,position1,position2) ";
		sql=sql+" values(?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getUserProductLineId(),obj.getPosition1(),obj.getPosition2());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(Productlineposition obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update Productlineposition set ";
		sql=sql+" userProductLineId=?,position1=?,position2=?";
		sql=sql+" where productLinePositionId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getUserProductLineId(),obj.getPosition1(),obj.getPosition2(),obj.getProductLinePositionId());
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
		String sql="delete from Productlineposition where productLinePositionId=:productLinePositionId";
		int ret=this.execute(sql, "productLinePositionId",id);
		if(ret>0)
			return StringUtils.STR_EMPTY;
		else
			return BaseServiceConst.MSG_DELETE_FAIL;
	}
	
	/**
	 * 根据userProductLineId删除很多对象
	 * @param id
	 * @return
	 */
	public String deleteByuserProductLineId(Long userProductLineId)
	{
		String sql="delete from Productlineposition where userProductLineId=:userProductLineId";
		int ret=this.execute(sql, "userProductLineId",userProductLineId);
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
	public Pager<Productlineposition> findAllPaged(Integer productLinePositionId , Long userProductLineId , Double position1 , Double position2  ){
		String sql="select * from Productlineposition";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("productLinePositionId", productLinePositionId);
		params.put("userProductLineId", userProductLineId);
		params.put("position1", position1);
		params.put("position2", position2);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("productLinePositionId");
		Pager<Productlineposition> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from Productlineposition where "+fieldName+"=:"+fieldName;
		Productlineposition oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getProductLinePositionId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
