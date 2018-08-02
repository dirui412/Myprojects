package lj.dao.sys;

import lj.dao.base.IXmlDao;
import lj.model.sys.SysConfig;
import lj.model.sys.SysParam;

public interface ISysConfigDao extends IXmlDao<SysConfig> {

	/**
	 * SysConfig object->SysPram Arrays
	 * @return
	 */
	SysParam[] findAll();
	
	/**
	 * 更新系统配置
	 * @param obj
	 * @return
	 */
	String update(SysParam obj);
}
