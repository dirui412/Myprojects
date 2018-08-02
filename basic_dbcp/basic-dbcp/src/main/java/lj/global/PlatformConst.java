package lj.global;

import lj.util.DbUtils;
import lj.util.FileUtils;

/***
 * 平台常量
 * 
 * @author samlv
 *
 */
public class PlatformConst {
	// 应用程序字符集
	public final static String APP_CHARSET = "UTF-8";
	// 数据库类型
	public final static String DATABSE_TYPE = DbUtils.DATABASE_TYPE_MYSQL;
	
	// 验证码候选字符
	public final static String IDENTIFIER_CODE_CHARS = "0123456789";
	// 验证码长度
	public final static int IDENTIFIER_CODE_LENGTH = 4;
	
	//分页大小
	public final static int PAGE_SIZE=10;
}
