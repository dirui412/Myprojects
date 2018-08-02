package lj.dao.sys;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import lj.dao.base.XMLBaseDao;
import lj.global.AppConst;
import lj.global.AppVar;
import lj.model.sys.SysConfig;
import lj.model.sys.SysParam;
import lj.service.BaseServiceConst;
import lj.util.FileUtils;
import lj.util.IntUtils;
import lj.util.StringUtils;

/***
 * 系统配置DAO对象
 * 
 * @author samlv
 *
 * @param <SysConfig>
 */
@Repository
public class SysConfigDao  extends XMLBaseDao<SysConfig> implements ISysConfigDao {
	public final static String ATTR_SYSTEM_NAME_CAPTION="系统名称";
	public final static String ATTR_COMPANY_NAME_CAPTION="公司名称";

	private final static String CONFIG_FILE_PATH = AppConst.PATH_CONFIG + File.separator+ "SysConfig.xml";

	/***
	 * 构造方法
	 */
	public SysConfigDao() {
		super(AppVar.appPath+File.separator+CONFIG_FILE_PATH);
		//System.out.println("SysConfigDao config path:"+AppVar.appPath+File.separator+CONFIG_FILE_PATH);
	}
	
	/**
	 * 系统配置-》系统参数
	 * @return
	 */
	public SysParam[] findAll(){
		if(AppVar.sysConfig==null)
			return new SysParam[0];
		List<SysParam> params=new ArrayList<SysParam>();
		long index=0;
		SysParam param=new SysParam(++index,ATTR_SYSTEM_NAME_CAPTION,AppVar.sysConfig.getSystemName(),StringUtils.STR_EMPTY);
		params.add(param);
		param=new SysParam(++index,ATTR_COMPANY_NAME_CAPTION,AppVar.sysConfig.getCompanyName(),StringUtils.STR_EMPTY);
		params.add(param);
		return params.toArray(new SysParam[0]);
	}
	
	
	/**
	 * 更新系统对象
	 */
	public String update(SysParam obj){
		//1-更新全局变量
		if(obj.getParamName().equals(SysConfigDao.ATTR_SYSTEM_NAME_CAPTION)==true){
			AppVar.sysConfig.setSystemName(obj.getParamValue());
		}
		else if(obj.getParamName().equals(SysConfigDao.ATTR_COMPANY_NAME_CAPTION)==true){
			AppVar.sysConfig.setCompanyName(obj.getParamValue());
		}
		else
			return BaseServiceConst.MSG_UPDATE_FAIL;
		//2-保持到配置文件
		int ret=this.saveToFile(AppVar.sysConfig);
		//3-返回
		if(ret==IntUtils.INT_FAIL)
			return BaseServiceConst.MSG_UPDATE_FAIL;
		else
			return StringUtils.STR_EMPTY;
	}

}
