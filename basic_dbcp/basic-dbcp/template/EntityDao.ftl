package ${packageName};

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
import ${modelClassPath}.${modelClassName};


@Repository
public class ${className} extends JdbcDao<${modelClassName}> implements ${daoInterfaceName} {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ${modelClassName} find(Long ${indexName}){
		String sql="select * from ${modelClassName} where ${indexName}=:${indexName}";
		${modelClassName} obj=this.find(sql,"${indexName}",${indexName});
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public ${modelClassName}[] findAll()
	{
		String sql="select * from ${modelClassName}";
		List<${modelClassName}> list=this.findAll(sql);
		return list.toArray(new ${modelClassName}[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(${modelClassName} obj){
	    String sql="insert into ${modelClassName}(<#list fields as e>${e.beanName}<#if e_has_next>,</#if></#list>) ";
		sql=sql+" values(<#list fields as e>?<#if e_has_next>,</#if></#list>)";
		int intRet=this.jdbcTemplate.update(sql<#list fields as e>,obj.${e.getterName}()</#list>);
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(${modelClassName} obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update ${modelClassName} set ";
		sql=sql+" <#list fields as e>${e.beanName}=?<#if e_has_next>,</#if></#list>";
		sql=sql+" where ${indexName}=?";
		int intRet = this.jdbcTemplate.update(sql
		<#list fields as e>,obj.${e.getterName}()</#list>,obj.${indexNameGetter}());
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
		String sql="delete from ${modelClassName} where ${indexName}=:${indexName}";
		int ret=this.execute(sql, "${indexName}",id);
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
	public Pager<${modelClassName}> findAllPaged(){
		String sql="select * from ${modelClassName}";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("${indexName}");
		Pager<${modelClassName}> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from ${modelClassName} where "+fieldName+"=:"+fieldName;
		${modelClassName} oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.${indexNameGetter}().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
