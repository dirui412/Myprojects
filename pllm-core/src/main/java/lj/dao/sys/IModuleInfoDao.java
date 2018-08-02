package lj.dao.sys;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.sys.ModuleInfo;

public interface IModuleInfoDao extends IJdbcDao<ModuleInfo> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	ModuleInfo find(long id);
	
	
	/**
	 * 查询所有
	 * @return
	 */
	ModuleInfo[] findAll();
	
	/**
	 * 所有模块标识属于moduleIds的模块信息
	 * @param moduleIds
	 * @return
	 */
	ModuleInfo[] findAll(String[] moduleIds);
	
	/**
	 * 查询所有父模块
	 * @return
	 */
	ModuleInfo[] findAllParentModules();
	
	
	/**
	 * 查询所有子模块
	 * @param id
	 * @return
	 */
	ModuleInfo[] findAllChildModules(long id);
	
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	long insert(ModuleInfo obj);
	
	
	
	/**
	 * 更新对象
	 * @param obj
	 * @return
	 */
	String update(ModuleInfo obj);
	
	/**
	 * 是否使用
	 * @param id
	 * @return
	 */
	boolean isInuse(long id);
	
	
	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
	String delete(long id);
	
}
