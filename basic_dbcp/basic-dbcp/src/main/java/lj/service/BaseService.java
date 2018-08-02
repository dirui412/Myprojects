package lj.service;

import java.util.Date;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import lj.global.PlatformConst;
import lj.global.PlatformFun;
import lj.util.DbUtils;
import lj.util.MysqlUtils;
import lj.util.SpringUtils;



public class BaseService {
	public final static String PARAM_TYPE_SWITCH="开关量";
	public final static String PARAM_TYPE_ANALOG="模拟量";
	

	
	public static String generateVerifyingCode(){
		String str=PlatformFun.generateIdentifyingCode(PlatformConst.IDENTIFIER_CODE_LENGTH);
		return str;
	}

	public  Date getNowTime()
	{
		BeanFactory factory=SpringUtils.getBeanFactory();
		JdbcTemplate jdbcTemplate=factory.getBean("jdbcTemplate", JdbcTemplate.class);
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

}
