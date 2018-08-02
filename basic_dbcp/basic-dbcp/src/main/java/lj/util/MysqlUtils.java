package lj.util;

public class MysqlUtils {
	public final static String SQL_NEW_ID="select LAST_INSERT_ID() as newId";
	public final static String COLUMN_NEW_ID="newId";
	public final static String SQL_NEW_TIME="select now() as nowTime";
	public final static String COLUMN_NOW_TIME="nowTime";
}
