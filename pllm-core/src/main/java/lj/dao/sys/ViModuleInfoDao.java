package lj.dao.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.sys.ViModuleInfo;
import lj.util.StringUtils;
import lj.util.query.QueryObject;

@Repository
public class ViModuleInfoDao extends JdbcDao<ViModuleInfo> implements IViModuleInfoDao {

	/**
	 * 根据标识查询对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ViModuleInfo find(long moduleId) {
		String sql = "select * from ViModuleInfo where moduleId=:moduleId";
		ViModuleInfo obj = this.find(sql, "moduleId", moduleId);
		return obj;
	}

	public ViModuleInfo[] findAllParentModules() {
		String sql = "select * from ViModuleInfo where parentModuleId is null order by moduleCode ";
		List<ViModuleInfo> list = this.findAll(sql);
		ViModuleInfo[] objs = list.toArray(new ViModuleInfo[0]);

		return objs;
	}

	/**
	 * 分页查询
	 * 
	 * @param pageIndex：请求页index
	 * @param sortField：排序字段
	 * @param sortType：排序类型(asc/desc)
	 * @return
	 */
	@Override
	public Pager<ViModuleInfo> findAllPaged(Long queryParentModuleId,String queryEntityName) {
		String sql = "select * from ViModuleInfo ";
		Map<String, Object> params = new HashMap<String, Object>();
		if (queryParentModuleId != null && queryParentModuleId > 0)
			params.put("parentModuleId", queryParentModuleId);
		params.put("entityName", queryEntityName);
		QueryObject query = JdbcDao.wrapperSqlWithCondition(sql, params);
		if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("moduleId");
		Pager<ViModuleInfo> pager = super.findAllPaged(query.sql, query.params);
		return pager;
	}

}
