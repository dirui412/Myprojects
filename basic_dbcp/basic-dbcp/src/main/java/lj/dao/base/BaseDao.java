package lj.dao.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.util.StringUtils;

/**
 * 抽象基础DAO类
 * @author samlv
 *
 * @param <T>
 */
abstract public class BaseDao<T> implements IBaseDao<T> {
	
	
	
	@Override
	public T find(String sql, Map<String, Object> params) {
		List<T> objs=this.findAll(sql, params);
		if(objs.size()<=0)
			return null;
		return objs.get(0);
	}

	
	@Override
	public T find(String sql, String paramName, Object paramValue) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(paramName, paramValue);
		return this.find(sql,map);
	}
	
	@Override
	public T find(String sql) {
		return this.find(sql,new HashMap<String,Object>());
	}
	
	@Override
	public List<T> findAll(String sql, String paramName, Object paramValue) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(paramName, paramValue);
		List<T> objs = this.findAll(sql, params);
		return objs;
	}
	
	@Override
	public List<T> findAll(String sql) {
		// TODO Auto-generated method stub
		List<T> objs = this.findAll(sql, new HashMap<String, Object>());
		return objs;
	}
	
	@Override
	public Pager<T> findAllPaged(String sql, String paramName, Object paramValue) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(paramName, paramValue);
		Pager<T> objs = this.findAllPaged(sql, params);
		return objs;
	}

	@Override
	public Pager<T> findAllPaged(String sql) {
		Pager<T> objs = this.findAllPaged(sql, new HashMap<String, Object>());
		return objs;
	}
	
	@Override
	public int execute(String sql, String paramName, Object paramValue) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(paramName, paramValue);
		return this.execute(sql, params);
	}

	@Override
	public int execute(String sql) {
		return this.execute(sql, new HashMap<String, Object>());
	}

	/**
	 * SQL加上排序语句
	 * 
	 * @param sql
	 * @return
	 */
	protected String wrapperHqlWithOrder(String sql) {
		String sortField = QueryContext.getSortField();
		String sortType = QueryContext.getSortType();
		if (StringUtils.isNullOrEmpty(sortField)) {
			sql = sql + " order by " + sortField;
			if (StringUtils.isNullOrEmpty(sortType) || sortType.equals(SORT_TYPE_ASC))
				sql = sql + " " + SORT_TYPE_ASC;
			else
				sql = sql + " " + SORT_TYPE_DESC;
		}
		return sql;
	}
}
