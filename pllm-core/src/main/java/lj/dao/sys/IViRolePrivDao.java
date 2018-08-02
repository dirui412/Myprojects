package lj.dao.sys;

import java.util.Map;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.sys.ViRolePriv;

public interface IViRolePrivDao extends IJdbcDao<ViRolePriv> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	ViRolePriv find(long id);
	
	
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	Pager<ViRolePriv> findAllPaged();
}
