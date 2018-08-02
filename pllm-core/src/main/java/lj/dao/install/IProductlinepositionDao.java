package lj.dao.install;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.install.Productlineposition;

public interface IProductlinepositionDao extends IJdbcDao<Productlineposition> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	Productlineposition find(Long id);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	Productlineposition[] findAll();
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	long insert(Productlineposition obj);
	
	/**
	 * 更新对象
	 * @param obj
	 * @return
	 */
	String update(Productlineposition obj);
	
	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
	String delete(Long id);
	String deleteByuserProductLineId(Long userProductLineId);
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	Pager<Productlineposition> findAllPaged(Integer productLinePositionId , Long userProductLineId , Double position1 , Double position2  );
	
	/**
	 * 某个字段值是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	boolean isRepeat(String fieldName,Object fieldValue,Long id);
	
	//根据userProductLineId查找Productlineposition
	Productlineposition findByUserProductLineId(Long userProductLineId);
}
