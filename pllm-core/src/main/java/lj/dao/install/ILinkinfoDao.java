package lj.dao.install;

import java.util.Map;

import lj.dao.base.IJdbcDao;
import lj.model.base.Pager;
import lj.model.install.Linkinfo;

public interface ILinkinfoDao extends IJdbcDao<Linkinfo> {
     /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	Linkinfo find(Long id);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	Linkinfo[] findAll();
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	long insert(Linkinfo obj);
	
	/**
	 * 更新对象
	 * @param obj
	 * @return
	 */
	String update(Linkinfo obj);
	
	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
	String delete(Long id);
	
	//删除很多对象
	public String deleteByuserProductLineId(Long userProductLineId);
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	Pager<Linkinfo> findAllPaged(Integer linkId , Long userProductLineId , String fromKey , String toKey , String fromPort , Double toPort , Double point1 , Double point2 , Double point3 , Double point4 , Double point5 , Double point6 , Double point7 , Double point8 , Double point9 , Double point10 , Double point11 , Double point12  );
	
	/**
	 * 某个字段值是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	boolean isRepeat(String fieldName,Object fieldValue,Long id);

	Linkinfo[] findByUserProductLineId(Long userProductLineId);
}
