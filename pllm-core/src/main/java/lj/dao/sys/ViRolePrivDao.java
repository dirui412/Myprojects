package lj.dao.sys;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.sys.ViRolePriv;
import lj.util.StringUtils;
import lj.util.query.QueryObject;


@Repository
public class ViRolePrivDao extends JdbcDao<ViRolePriv> implements IViRolePrivDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ViRolePriv find(long privId){
		String sql="select * from ViRolePriv where privId=:privId";
		ViRolePriv obj=this.find(sql,"privId",privId);
		return obj;
	}
	
	
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	@Override
	public Pager<ViRolePriv> findAllPaged(){
		String sql="select * from ViRolePriv";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
		if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("privId");
		Pager<ViRolePriv> pager=super.findAllPaged(query.sql, query.params);
		return pager;
	}
	
}
