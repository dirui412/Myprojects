package lj.dao.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.user.ViUserRole;
import lj.util.StringUtils;
import lj.util.query.QueryObject;


@Repository
public class ViUserRoleDao extends JdbcDao<ViUserRole> implements IViUserRoleDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ViUserRole find(long userRoleId){
		String sql="select * from ViUserRole where userRoleId=:userRoleId";
		ViUserRole obj=this.find(sql,"userRoleId",userRoleId);
		return obj;
	}
	
	public ViUserRole[] findAllByUserId(long userId){
		String sql="select * from ViUserRole where userId=:userId";
		List<ViUserRole> list=this.findAll(sql, "userId", userId);
		ViUserRole[] objs=list.toArray(new ViUserRole[0]);
		return objs;
	}
	
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	@Override
	public Pager<ViUserRole> findAllPaged(){
		String sql="select * from ViUserRole";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
		if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("userRoleId");
		Pager<ViUserRole> pager=super.findAllPaged(query.sql, query.params);
		return pager;
	}
	
}
