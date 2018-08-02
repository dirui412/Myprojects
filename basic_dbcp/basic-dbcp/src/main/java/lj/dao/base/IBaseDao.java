package lj.dao.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lj.model.base.Pager;

/**
 * DAO基本接口
 * 
 * @author samlv
 *
 * @param <T>
 */
public interface IBaseDao<T> {
	String SORT_TYPE_ASC = "asc";
	String SORT_TYPE_DESC = "desc";

	/*
	 * 查询对象
	 */
	T find(String sql, Map<String, Object> params);

	T find(String sql, String paramName, Object paramValue);

	T find(String sql);

	/*
	 * 查询所有对象数组
	 */
	List<T> findAll(String sql, Map<String, Object> params);

	List<T> findAll(String sql, String paramName, Object paramValue);

	List<T> findAll(String sql);

	/*
	 * 查询所有对象数组(带分页)
	 */
	Pager<T> findAllPaged(String sql, Map<String, Object> params);

	Pager<T> findAllPaged(String sql, String paramName, Object paramValue);

	Pager<T> findAllPaged(String sql);

	/*
	 * 执行语句
	 */
	int execute(String sql, Map<String, Object> params);

	int execute(String sql, String paramName, Object paramValue);

	int execute(String sql);

	/*
	 * 系统方法
	 */
	Date getNowTime();

}
