package lj.dao.install;

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
import lj.model.install.ViUserProductLine;


@Repository
public class ViUserProductLineDao extends JdbcDao<ViUserProductLine> implements IViUserProductLineDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ViUserProductLine find(Long userProductLineId){
		String sql="select * from ViUserProductLine where userProductLineId=:userProductLineId";
		ViUserProductLine obj=this.find(sql,"userProductLineId",userProductLineId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public ViUserProductLine[] findAll()
	{
		String sql="select * from ViUserProductLine";
		List<ViUserProductLine> list=this.findAll(sql);
		return list.toArray(new ViUserProductLine[0]);
	}
	
	
	public ViUserProductLine[] findAllByGroupId(Long groupId)
	{
		String sql="select * from ViUserProductLine";
		Map<String,Object> params=new HashMap<String,Object>();
		if(groupId!=null && groupId>0)
			params.put("groupId", groupId);
		List<ViUserProductLine> list=this.findAll(sql, params);
		return list.toArray(new ViUserProductLine[0]);
	}
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	@Override
	public Pager<ViUserProductLine> findAllPaged(String userProductLineName,Long groupId ){
		String sql="select * from ViUserProductLine";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userProductLineName", userProductLineName);
		params.put("groupId", groupId);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("userProductLineId");
		Pager<ViUserProductLine> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from ViUserProductLine where "+fieldName+"=:"+fieldName;
		ViUserProductLine oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getUserProductLineId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
