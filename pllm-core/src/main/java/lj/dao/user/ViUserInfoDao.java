package lj.dao.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.user.ViUserInfo;
import lj.util.StringUtils;
import lj.util.query.QueryObject;


@Repository
public class ViUserInfoDao extends JdbcDao<ViUserInfo> implements IViUserInfoDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ViUserInfo find(long userId){
		String sql="select * from ViUserInfo where userId=:userId";
		ViUserInfo obj=this.find(sql,"userId",userId);
		return obj;
	}
	
	@Override
	public ViUserInfo[] findAll() {
		String sql = "select * from ViUserInfo";
		List<ViUserInfo> list = this.findAll(sql);
		return list.toArray(new ViUserInfo[0]);
	}
	
	
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	@Override
	public Pager<ViUserInfo> findAllPaged(String queryUserName,String queryUserCode){
		String sql="select * from ViUserInfo";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userName", queryUserName);
		params.put("userCode", queryUserCode);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
		if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("userId");
		Pager<ViUserInfo> pager=super.findAllPaged(query.sql, query.params);
		return pager;
	}
	
	
	/**
	 * 分页查询登录人员所在的用户信息
	 * @param queryUserName
	 * @param queryUserCode
	 * @param queryGroupId
	 * @return
	 */
	public Pager<ViUserInfo> findAllPaged(String queryUserName, String queryUserCode,Long queryGroupId)
	{
		String sql="select * from ViUserInfo";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userName", queryUserName);
		params.put("userCode", queryUserCode);
		params.put("groupId", queryGroupId);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
		if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("userId");
		Pager<ViUserInfo> pager=super.findAllPaged(query.sql, query.params);
		return pager;
	}
	
}
