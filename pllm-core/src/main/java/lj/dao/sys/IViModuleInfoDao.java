package lj.dao.sys;

import java.util.Map;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.sys.ViModuleInfo;

public interface IViModuleInfoDao extends IJdbcDao<ViModuleInfo> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	ViModuleInfo find(long id);
	
	ViModuleInfo[] findAllParentModules();
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	Pager<ViModuleInfo> findAllPaged(Long queryParentModuleId,String queryEntityName);
}
