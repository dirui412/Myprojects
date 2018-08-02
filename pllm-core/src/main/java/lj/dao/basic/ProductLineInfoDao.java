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
import lj.model.basic.ProductLineInfo;


@Repository
public class ProductLineInfoDao extends JdbcDao<ProductLineInfo> implements IProductLineInfoDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ProductLineInfo find(Long productLineId){
		String sql="select * from ProductLineInfo where productLineId=:productLineId";
		ProductLineInfo obj=this.find(sql,"productLineId",productLineId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public ProductLineInfo[] findAll()
	{
		String sql="select * from ProductLineInfo";
		List<ProductLineInfo> list=this.findAll(sql);
		return list.toArray(new ProductLineInfo[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(ProductLineInfo obj){
	    String sql="insert into ProductLineInfo(productName,productVersion,productConfig,productMemo) ";
		sql=sql+" values(?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getProductName(),obj.getProductVersion(),obj.getProductConfig(),obj.getProductMemo());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(ProductLineInfo obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update ProductLineInfo set ";
		sql=sql+" productName=?,productVersion=?,productConfig=?,productMemo=?";
		sql=sql+" where productLineId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getProductName(),obj.getProductVersion(),obj.getProductConfig(),obj.getProductMemo(),obj.getProductLineId());
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
		String sql="delete from ProductLineInfo where productLineId=:productLineId";
		int ret=this.execute(sql, "productLineId",id);
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
	public Pager<ProductLineInfo> findAllPaged(){
		String sql="select * from ProductLineInfo";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("productLineId");
		Pager<ProductLineInfo> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from ProductLineInfo where "+fieldName+"=:"+fieldName;
		ProductLineInfo oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getProductLineId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
