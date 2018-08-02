package lj.dao.base;

import java.sql.Connection;
import java.util.Map;
import lj.model.base.Pager;

/*
 * 基本数据访问接口
 */
public interface IJdbcDao<T> extends IBaseDao<T> {
	/**
	 * 获得JDBC连接
	 * @return
	 */
	Connection getConnection();
}
