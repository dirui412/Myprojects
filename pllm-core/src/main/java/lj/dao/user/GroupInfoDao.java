package lj.dao.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.user.GroupInfo;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;
import lj.util.query.QueryObject;


@Repository
public class GroupInfoDao extends JdbcDao<GroupInfo> implements IGroupInfoDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public GroupInfo find(Long groupId){
		String sql="select * from GroupInfo where groupId=:groupId";
		GroupInfo obj=this.find(sql,"groupId",groupId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public GroupInfo[] findAll()
	{
		String sql="select * from GroupInfo";
		List<GroupInfo> list=this.findAll(sql);
		return list.toArray(new GroupInfo[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(GroupInfo obj){
	    String sql="insert into GroupInfo(groupName,groupType,groupMemo,opUserId,opTime) ";
		sql=sql+" values(?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getGroupName(),obj.getGroupType(),obj.getGroupMemo(),obj.getOpUserId(),obj.getOpTime());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(GroupInfo obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update GroupInfo set ";
		sql=sql+" groupName=?,groupType=?,groupMemo=?,opUserId=?,opTime=?";
		sql=sql+" where groupId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getGroupName(),obj.getGroupType(),obj.getGroupMemo(),obj.getOpUserId(),obj.getOpTime(),obj.getGroupId());
		if (intRet <= 0)
			msg = BaseServiceConst.MSG_UPDATE_FAIL;
		return msg;
	}
	
	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
	public String delete(Long id)
	{
		String sql="delete from GroupInfo where groupId=:groupId";
		int ret=this.execute(sql, "groupId",id);
		if(ret>0)
			return StringUtils.STR_EMPTY;
		else
			return BaseServiceConst.MSG_DELETE_FAIL;
	}
	
	/**
     * 分页查询
     * @param pageIndex：请求页index
     * @param sortField：排序字段
     * @param sortType：排序类型(asc/desc)
     * @return
     */
	@Override
	public Pager<GroupInfo> findAllPaged(){
		String sql="select * from GroupInfo";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("groupId");
		Pager<GroupInfo> pager=super.findAllPaged(query.sql, query.params);
		return pager;
	}
	
	/**
	 * 某个字段值是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	@Override
	public boolean isRepeat(String fieldName,Object fieldValue,Long id)
	{
		String sql="select * from GroupInfo where "+fieldName+"=:"+fieldName;
		GroupInfo oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getGroupId().equals(id)==true)
			return false;
		else
			return true;
	}
	
	/**
	 * 是否使用
	 */
	public boolean isInuse(long id){
		
		String sql="select count(*) from UserInfo where groupId=:groupId";
		Long count=this.queryCount(sql, "groupId", id);
		if(count>0)
			return true;
		
		
	    return false;
	}
	
}
