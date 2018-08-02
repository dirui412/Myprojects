package lj.dao.install;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.install.UserProductLine;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;
import lj.util.query.QueryObject;


@Repository
public class UserProductLineDao extends JdbcDao<UserProductLine> implements IUserProductLineDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public UserProductLine find(Long userProductLineId){
		String sql="select * from UserProductLine where userProductLineId=:userProductLineId";
		UserProductLine obj=this.find(sql,"userProductLineId",userProductLineId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public UserProductLine[] findAll()
	{
		String sql="select * from UserProductLine";
		List<UserProductLine> list=this.findAll(sql);
		return list.toArray(new UserProductLine[0]);
	}
	
	/**
	 * 根据用户组名查询所有对象数组
	 */
	@Override
	public UserProductLine[] findAllByGroupId(Long GroupId)
	{
		String sql="select * from UserProductLine where GroupId=:GroupId";
		List<UserProductLine> list=this.findAll(sql,"GroupId",GroupId);
		return list.toArray(new UserProductLine[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(UserProductLine obj){
	    String sql="insert into UserProductLine(productLineId,groupId,userProductLineName,productLineState,lectotypeUserId,lectotypeTime,lectotypeParams,lectotypeMemo) ";
		sql=sql+" values(?,?,?,?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getProductLineId(),obj.getGroupId(),obj.getUserProductLineName(),obj.getProductLineState(),obj.getLectotypeUserId(),obj.getLectotypeTime(),obj.getLectotypeParams(),obj.getLectotypeMemo());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(UserProductLine obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update UserProductLine set ";
		sql=sql+" productLineId=?,groupId=?,userProductLineName=?,productLineState=?,lectotypeUserId=?,lectotypeTime=?,lectotypeParams=?,lectotypeMemo=?";
		sql=sql+" where userProductLineId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getProductLineId(),obj.getGroupId(),obj.getUserProductLineName(),obj.getProductLineState(),obj.getLectotypeUserId(),obj.getLectotypeTime(),obj.getLectotypeParams(),obj.getLectotypeMemo(),obj.getUserProductLineId());
		if (intRet <= 0)
			msg = BaseServiceConst.MSG_UPDATE_FAIL;
		return msg;
	}
	
	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
	public String delete(Long userProductLineId)
	{
		
		String sql="delete from UserProductLine where userProductLineId=:userProductLineId";
		int ret=this.execute(sql, "userProductLineId",userProductLineId);
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
	public Pager<UserProductLine> findAllPaged(){
		String sql="select * from UserProductLine";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("userProductLineId");
		Pager<UserProductLine> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from UserProductLine where "+fieldName+"=:"+fieldName;
		UserProductLine oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getUserProductLineId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
