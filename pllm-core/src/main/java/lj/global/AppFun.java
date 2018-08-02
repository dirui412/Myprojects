package lj.global;

import lj.dao.sys.SysConfigDao;
import lj.util.IntUtils;
import lj.util.StringUtils;

public class AppFun extends PlatformFun {
	
	
	/*
	 * 预装载
	 */
	public static int preLoad() {

		// 1-读入系统配置文件
		SysConfigDao configDao = new SysConfigDao();
		AppVar.sysConfig = configDao.loadFromFile();
		return IntUtils.INT_SUCCESS;
	}
	
	/**
	 * 退出系统
	 * @return
	 */
	public String exitSystem(){
		
		return StringUtils.STR_EMPTY;
	}
	
	
}
