package lj.dao.install;

import java.util.Map;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.install.ViUserProductLine;

public interface IViUserProductLineDao extends IJdbcDao<ViUserProductLine> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	ViUserProductLine find(Long id);
	
	
	
	/**
	 * 查询所有对象
	 * @return
	 */
	ViUserProductLine[] findAll();
	
	ViUserProductLine[] findAllByGroupId(Long groupId);
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	Pager<ViUserProductLine> findAllPaged(String userProductLineName,Long groupId);
	
	/**
	 * 某个字段值是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	boolean isRepeat(String fieldName,Object fieldValue,Long id);
}
