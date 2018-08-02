package lj.global;

import java.io.File;

import lj.util.DbUtils;
import lj.util.FileUtils;

/**
 * 应用程序常量
 * 
 * @author samlv
 *
 */
public class AppConst extends PlatformConst {
	// 配置文件路径
	public final static String PATH_CONFIG = "WEB-INF"+File.separator+"config";
	// 上传文件路径
	public final static String PATH_UPLOAD = "upload";
	// 用户上传目录
	public final static String PATH_USER = PATH_UPLOAD + File.separator + "user";
	// 用户头像上传目录
	public final static String PATH_USER_IMAGE = PATH_USER + File.separator + "image";
    
	//下载文件路径
	public final static String PATH_DOWNLOAD="download";
	
	/**
	 * session变量
	 */
	public final static String SESSION_USERINFO="userInfo";
	
	/**
	 * 重要URL
	 */
	public final static String URL_LOGIN="login";
	public final static String URL_MAIN="main";
	public final static String URL_DOWNLOAD="download";
}
