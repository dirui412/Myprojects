package lj.dao.base;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import lj.global.PlatformConst;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.util.DbUtils;
import lj.util.IntUtils;
import lj.util.LogUtils;
import lj.util.MysqlUtils;
import lj.util.ReflectUtil;
import lj.util.StringUtils;
import lj.util.query.DateCondition;
import lj.util.query.DoubleCondition;
import lj.util.query.IntCondition;
import lj.util.query.LongCondition;
import lj.util.query.QueryObject;

/*
 * JDBC DAO基类
 */
@Repository("jdbcDao")
public class JdbcDao<T> extends BaseDao<T> implements IJdbcDao<T>  {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * 参数类
	 */
	private Class<T> paramCls;

	/**
	 * 获得参数类
	 * 
	 * @return
	 */
	protected Class<T> getParamClass() {
		if (paramCls == null) {
			Type[] paramTypes = ReflectUtil.getGenericParamTypes(this);
			if (paramTypes.length <= 0)
				return null;
			paramCls = (Class<T>) paramTypes[0];
		}
		return paramCls;
	}

	@Override
	public List<T> findAll(String sql, Map<String, Object> params) {
		Object[] paramValues = new Object[0];
		// for(Object obj:paramValues)
		// System.out.println("JdbcDao.findAll param:"+obj.toString());
		if (params != null && params.size() > 0)
			paramValues = params.values().toArray();
		//System.out.println("JdbcDao.findAll sql:"+sql);
		List<T> objs = namedParameterJdbcTemplate.query(sql, params,
				BeanPropertyRowMapper.newInstance(getParamClass()));
		return objs;
	}

	@Override
	public Pager<T> findAllPaged(String sql, Map<String, Object> params) {
		// 1-初始化分页对象
		Pager<T> pager = this.getPager();
		// 2-查询数据
		String pageSql = wrapSqlToPageSql(sql);
		List<T> datas = this.findAll(pageSql, params);
//		System.out.println("findAllPaged pageSql:"+pageSql);
//		System.out.print("findAllPaged params:");
//		for(Object obj:params.values())
//			System.out.print(obj+",");
//		System.out.println("");
//		System.out.println("findAllPaged size of data:"+datas.size());
		// 3-计算总函数
		String countSql = wrapSqlToCountSql(sql);
		long total = queryCount(countSql, params);
//		System.out.println("findAllPaged countSql:"+countSql);
//		System.out.println("findAllPaged total:"+total);
		// 4-再次设置分页对象(数据和总行数)
		pager.setDatas(datas);
		pager.setRowTotal(total);
		return pager;
	}

	private Pager<T> getPager() {
		Pager<T> pager = new Pager<T>();
		Integer pageSize = QueryContext.getPageSize();
		Integer pageOffset = QueryContext.getPageIndex();
		if (pageOffset == null || pageOffset < 0)
			pageOffset = 0;
		if (pageSize == null || pageSize < 0)
			pageSize = PlatformConst.PAGE_SIZE;
		pager.setPageIndex(pageOffset);
		pager.setPageSize(pageSize);
		return pager;
	}

	@Override
	public int execute(String sql, Map<String, Object> params) {
		return this.namedParameterJdbcTemplate.update(sql, params);
	}

	/**
	 * 查询新增Id
	 * 
	 * @return
	 */
	public long getNewInsertId() {
		String sql = null;
		if (PlatformConst.DATABSE_TYPE.equals(DbUtils.DATABASE_TYPE_MYSQL) == true)
			sql = MysqlUtils.SQL_NEW_ID;
		else
			return IntUtils.INT_FAIL;
		SqlRowSet idRow = jdbcTemplate.queryForRowSet(sql);
		if (idRow == null || idRow.next() == false)
			return IntUtils.INT_FAIL;
		return idRow.getLong(MysqlUtils.COLUMN_NEW_ID);
	}

	/**
	 * 查询当前时间
	 * 
	 * @return
	 */
	public Date getNowTime() {
		String sql = null;
		if (PlatformConst.DATABSE_TYPE.equals(DbUtils.DATABASE_TYPE_MYSQL) == true)
			sql = MysqlUtils.SQL_NEW_TIME;
		else
			return new Date();
		SqlRowSet idRow = jdbcTemplate.queryForRowSet(sql);
		if (idRow == null || idRow.next() == false)
			return new Date();
		Date date = new Date(idRow.getDate(MysqlUtils.COLUMN_NOW_TIME).getTime());
		return date;
	}

	/**
	 * 查询数量
	 * 
	 * @param sql
	 * @return
	 */
	public long queryCount(String sql, String paramName, Object paramValue) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(paramName, paramValue);
		long ret = queryCount(sql, params);
		return ret;
	}

	/**
	 * 查询数量
	 * 
	 * @param sql
	 * @return
	 */
	public long queryCount(String sql, Map<String, Object> params) {
		long ret = namedParameterJdbcTemplate.queryForObject(sql, params, Long.class);
		return ret;
	}

	public List<Object> queryForList(String sql, Map<String, Object> params) {
		List<Object> list = namedParameterJdbcTemplate.queryForList(sql, params, Object.class);
		return list;

	}

	/***
	 * 获得数据库连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		DataSource ds = this.jdbcTemplate.getDataSource();
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.logError("JdbcDao.getConnection():" + e);
		}
		return conn;
	}

	/**
	 * 查询数据表或者视图元数组
	 * 
	 * @param tableName
	 * @return
	 */
	public ResultSetMetaData getTableOrViewMetaData(String tableName) {
		if (StringUtils.isNullOrEmpty(PlatformConst.DATABSE_TYPE) == true)
			return null;
		String sql = StringUtils.STR_EMPTY;
		if (PlatformConst.DATABSE_TYPE.equals(DbUtils.DATABASE_TYPE_MYSQL) == true)
			sql = "select * from " + tableName + " limit 0,0";
		else if (PlatformConst.DATABSE_TYPE.equals(DbUtils.DATABASE_TYPE_SQLSERVER) == true)
			sql = "select top 0 * from " + tableName;
		else if (PlatformConst.DATABSE_TYPE.equals(DbUtils.DATABASE_TYPE_ORACLE) == true)
			sql = "select * from " + tableName + " where rownum<1";
		else
			return null;
		Connection conn = getConnection();
		if (conn == null)
			return null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rs.getMetaData();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.logError("getResultSetMetaData:" + e);
			return null;
		} finally {
			try {
				conn.close();
			} catch (Exception innerEx) {
				innerEx.printStackTrace();
				LogUtils.logError("getResultSetMetaData:" + innerEx);
			}
		}
	}

	/**
	 * 查询SQL元数组
	 * 
	 * @param sql
	 * @return
	 */
	public ResultSetMetaData getSqlMetaData(String sql) {

		Connection conn = getConnection();
		if (conn == null)
			return null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rs.getMetaData();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.logError("getResultSetMetaData:" + e);
			return null;
		} finally {
			try {
				conn.close();
			} catch (Exception innerEx) {
				innerEx.printStackTrace();
				LogUtils.logError("getResultSetMetaData:" + innerEx);
			}
		}
	}

	/**
	 * 获得确省排序字段
	 * 
	 * @param fieldName
	 * @return
	 */
	public String getDefaultSortField(String sql) {
		ResultSetMetaData metaData = getSqlMetaData(sql);
		try {
			if (metaData.getColumnCount() > 0)
				return metaData.getColumnName(1);
			else
				return "";
		} catch (Exception e) {
			LogUtils.logError("JdbcDao.getDefaultSortField", e);
			return "";
		}
	}

	/**
	 * sql->sql condition(named)
	 * 
	 * @param params
	 * @return
	 */
	public static QueryObject wrapperSqlWithCondition(String sql, Map<String, Object> params) {
		QueryObject query = new QueryObject();
		query.sql = sql;
		query.params = new HashMap<String, Object>();
		if (params.size() <= 0)
			return query;
		String strCon = StringUtils.STR_EMPTY;
		String[] keys = params.keySet().toArray(new String[0]);
		for (String key : keys) {
			// 更新SQL语句
			Object obj = params.get(key);
			if (obj == null)
				continue;
			String subCon = DbUtils.generateConditionSql(key, obj);
			if (StringUtils.isNullOrEmpty(subCon) == false) {
				if (StringUtils.isNullOrEmpty(strCon) == false)
					strCon = strCon + " and ";
				strCon = strCon + " ( " + subCon + " ) ";
			}
			// 更新参数列表
			// 字符串查询条件
			if (obj instanceof String) {
				String str = (String) obj;
				if (StringUtils.isNullOrEmpty(str) == false)
					query.params.put(key, "%" + str + "%");
			}
			// 整数查询条件
			else if (obj instanceof IntCondition) {
				IntCondition conInt = (IntCondition) obj;
				if (conInt.beginValue != null)
					query.params.put(key + DbUtils.QUERY_POSTFIX_BEGIN, conInt.beginValue);
				if (conInt.endValue != null)
					query.params.put(key + DbUtils.QUERY_POSTFIX_END, conInt.endValue);
			}
			// 长整数查询条件
			else if (obj instanceof LongCondition) {
				LongCondition conLong = (LongCondition) obj;
				if (conLong.beginValue != null)
					query.params.put(key + DbUtils.QUERY_POSTFIX_BEGIN, conLong.beginValue);
				if (conLong.endValue != null)
					query.params.put(key + DbUtils.QUERY_POSTFIX_END, conLong.endValue);
			}
			// 浮点数查询条件
			else if (obj instanceof DoubleCondition) {
				DoubleCondition conDouble = (DoubleCondition) obj;
				if (conDouble.beginValue != null)
					query.params.put(key + DbUtils.QUERY_POSTFIX_BEGIN, conDouble.beginValue);
				if (conDouble.endValue != null)
					query.params.put(key + DbUtils.QUERY_POSTFIX_END, conDouble.endValue);
			}
			// 时间查询条件
			else if (obj instanceof DateCondition) {
				DateCondition conDate = (DateCondition) obj;
				if (conDate.beginValue != null)
					query.params.put(key + DbUtils.QUERY_POSTFIX_BEGIN, conDate.beginValue);
				if (conDate.endValue != null)
					query.params.put(key + DbUtils.QUERY_POSTFIX_END, conDate.endValue);
			}
			// 数组查询条
			else if (obj instanceof Object[]) {
				query.params.put(key, obj);
			}
			// 列表查询条
			else if (obj instanceof List) {
				query.params.put(key, obj);
			} else {
				query.params.put(key, obj);
			}

		}
		if (StringUtils.isNullOrEmpty(strCon) == false)
			query.sql = query.sql + " where " + strCon;
		System.out.println("wrapperSqlWithCondition sql:" + query.sql);
		return query;
	}

	/**
	 * 将SQL-》count sql
	 * 
	 * @param sql
	 * @return
	 */
	protected static String wrapSqlToCountSql(String sql) {
		String newSql = StringUtils.STR_EMPTY;
		if (PlatformConst.DATABSE_TYPE.equals(DbUtils.DATABASE_TYPE_MYSQL) == true)
			newSql = "select count(*) from ( " + sql + " ) temp";
		else if (PlatformConst.DATABSE_TYPE.equals(DbUtils.DATABASE_TYPE_SQLSERVER) == true)
			newSql = "select count(*) from ( " + sql + " ) temp";
		else if (PlatformConst.DATABSE_TYPE.equals(DbUtils.DATABASE_TYPE_ORACLE) == true)
			newSql = "select count(*) from ( " + sql + " ) temp";
		else
			newSql = "select count(*) from ( " + sql + " ) temp";
		return newSql;
	}

	/**
	 * sql->page sql
	 * 
	 * @param sql
	 * @param pager
	 * @return
	 */
	protected String wrapSqlToPageSql(String sql) {
		String newSql = StringUtils.STR_EMPTY;
		if (PlatformConst.DATABSE_TYPE.equals(DbUtils.DATABASE_TYPE_MYSQL) == true) {
			String sortField = QueryContext.getSortField();
			if (StringUtils.isNullOrEmpty(sortField) == true)
				sortField = this.getDefaultSortField(sql);
			newSql = sql + " order by " + sortField + " " + QueryContext.getSortType();
			newSql = newSql + " LIMIT ";
			// System.out.println("QueryContext.getPageIndex():"+(QueryContext.getPageIndex()==null));
			// System.out.println("QueryContext.getPageSize():"+(QueryContext.getPageSize()==null));
			newSql = newSql + QueryContext.getPageIndex() * QueryContext.getPageSize();
			newSql = newSql + "," + QueryContext.getPageSize();
		}
		return newSql;
	}

}
