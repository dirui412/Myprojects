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
import lj.model.basic.ViProductLineParam;


@Repository
public class ViProductLineParamDao extends JdbcDao<ViProductLineParam> implements IViProductLineParamDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ViProductLineParam find(Long productLineParamId){
		String sql="select * from ViProductLineParam where productLineParamId=:productLineParamId";
		ViProductLineParam obj=this.find(sql,"productLineParamId",productLineParamId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public ViProductLineParam[] findAll()
	{
		String sql="select * from ViProductLineParam";
		List<ViProductLineParam> list=this.findAll(sql);
		return list.toArray(new ViProductLineParam[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(ViProductLineParam obj){
	    String sql="insert into ViProductLineParam() ";
		sql=sql+" values()";
		int intRet=this.jdbcTemplate.update(sql);
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(ViProductLineParam obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update ViProductLineParam set ";
		sql=sql+" ";
		sql=sql+" where productLineParamId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getProductLineParamId());
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
		String sql="delete from ViProductLineParam where productLineParamId=:productLineParamId";
		int ret=this.execute(sql, "productLineParamId",id);
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
	public Pager<ViProductLineParam> findAllPaged(){
		String sql="select * from ViProductLineParam";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("productLineParamId");
		Pager<ViProductLineParam> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from ViProductLineParam where "+fieldName+"=:"+fieldName;
		ViProductLineParam oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getProductLineParamId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
