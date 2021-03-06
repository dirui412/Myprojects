package lj.util;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import lj.global.PlatformConst;
import lj.util.query.DateCondition;
import lj.util.query.DoubleCondition;
import lj.util.query.IntCondition;
import lj.util.query.LongCondition;

public class DbUtils {
	public final static String DATABASE_TYPE_MYSQL = "mysql";
	public final static String DATABASE_TYPE_SQLSERVER = "sqlServer";
	public final static String DATABASE_TYPE_ORACLE = "oracle";

	public final static int PAGE_SIZE = 10;

	public static Class JAVA_TYPE_LONG = Long.class;
	public static Class JAVA_TYPE_INT = Integer.class;
	public static Class JAVA_TYPE_DOUBLE = Double.class;
	public static Class JAVA_TYPE_STRING = String.class;
	public static Class JAVA_TYPE_DATE = Date.class;
	public final static String QUERY_POSTFIX_BEGIN = "Begin";
	public final static String QUERY_POSTFIX_END = "End";

	// 禁止实例对象
	private DbUtils() {
	}

	/**
	 * 根据参数名称和参数值生产SQL条件语句
	 * 
	 * @param paramName
	 * @param paramValue
	 * @return
	 */
	public static String generateConditionSql(String paramName, Object paramValue) {

		String str = StringUtils.STR_EMPTY;
		// 字符串查询条件
		if (paramValue instanceof String) {
			String con = (String) paramValue;
			if (StringUtils.isNullOrEmpty(con) == false)
				str = str + paramName + " like :" + paramName;
		}
		// 整数查询条件
		else if (paramValue instanceof IntCondition) {
			IntCondition con = (IntCondition) paramValue;
			if (con.beginValue != null)
				str = str + paramName + ">=:" + paramName + QUERY_POSTFIX_BEGIN;
			if (con.endValue != null) {
				if (con.beginValue != null)
					str = str + " and ";
				str = str + paramName + "<:" + paramName + QUERY_POSTFIX_END;
			}
		}
		// 长整数查询条件
		else if (paramValue instanceof LongCondition) {
			LongCondition con = (LongCondition) paramValue;
			if (con.beginValue != null)
				str = str + paramName + ">=:" + paramName + QUERY_POSTFIX_BEGIN;
			if (con.endValue != null) {
				if (con.beginValue != null)
					str = str + " and ";
				str = str + paramName + "<:" + paramName + QUERY_POSTFIX_END;
			}
		}
		// 浮点数查询条件
		else if (paramValue instanceof DoubleCondition) {
			DoubleCondition con = (DoubleCondition) paramValue;
			if (con.beginValue != null)
				str = str + paramName + ">=:" + paramName + QUERY_POSTFIX_BEGIN;
			if (con.endValue != null) {
				if (con.beginValue != null)
					str = str + " and ";
				str = str + paramName + "<:" + paramName + QUERY_POSTFIX_END;
			}
		}
		// 时间查询条件
		else if (paramValue instanceof DateCondition) {
			DateCondition con = (DateCondition) paramValue;
			if (con.beginValue != null)
				str = str + paramName + ">=:" + paramName + QUERY_POSTFIX_BEGIN;
			if (con.endValue != null) {
				if (con.beginValue != null)
					str = str + " and ";
				str = str + paramName + "<:" + paramName + QUERY_POSTFIX_END;
			}
		}
		// 数组
		else if (paramValue instanceof Object[]) {
			Object[] ary = (Object[]) paramValue;
			if (ary.length>0)
				str = str + paramName + " in (:" + paramName + " )";
		}
		// 列表
		else if (paramValue instanceof List) {
			List list = (List) paramValue;
			if (list.size() > 0)
				str = str + paramName + " in (:" + paramName + " )";
		} else {
			str = str + paramName + "=:" + paramName;
		}
		return str;
	}

	/**
	 * jdbc type->java class
	 * 
	 * @param jdbcType
	 * @return
	 */
	public static Class jdbcTypeToJavaClass(int jdbcType) {
		if (jdbcType == Types.INTEGER)
			return Integer.class;
		else if (jdbcType == Types.TINYINT || jdbcType == Types.BIGINT)
			return Long.class;
		else if (jdbcType == Types.DECIMAL || jdbcType == Types.DOUBLE)
			return Double.class;
		else if (jdbcType == Types.DATE || jdbcType == Types.TIMESTAMP)
			return Date.class;
		else
			return String.class;
	}

	/**
	 * sql->page sql
	 * 
	 * @param sql
	 * @param pager
	 * @return
	 */
	public static String wrapSqlToPageSql(String sql, String sortField, String sortType, Long start, Long pageSize) {
		String newSql = StringUtils.STR_EMPTY;
		if (PlatformConst.DATABSE_TYPE.equals(DbUtils.DATABASE_TYPE_MYSQL) == true) {
			newSql = sql + " order by " + sortField + " " + sortType;
			newSql = newSql + " LIMIT ";
			newSql = newSql + start;
			newSql = newSql + "," + pageSize;
		}
		return newSql;
	}

}
