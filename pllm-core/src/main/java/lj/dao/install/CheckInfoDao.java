package lj.dao.install;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.global.PlatformConst;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;
import lj.util.query.QueryObject;
import lj.model.install.CheckInfo;


@Repository
public class CheckInfoDao extends JdbcDao<CheckInfo> implements ICheckInfoDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public CheckInfo find(Long checkId){
		String sql="select * from CheckInfo where checkId=:checkId";
		CheckInfo obj=this.find(sql,"checkId",checkId);
		return obj;
	}
	
	
	/**
	 * 根据用户生产线标识查询验收信息
	 * @param userProductLineId
	 * @return
	 */
	public CheckInfo findByUserProductLineId(Long userProductLineId)
	{
		String sql="select * from CheckInfo where userProductLineId=:userProductLineId";
		CheckInfo obj=this.find(sql,"userProductLineId",userProductLineId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public CheckInfo[] findAll()
	{
		String sql="select * from CheckInfo";
		List<CheckInfo> list=this.findAll(sql);
		return list.toArray(new CheckInfo[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(CheckInfo obj){
	    String sql="insert into CheckInfo(checkUserId,userProductLineId,checkTime,checkMemo) ";
		sql=sql+" values(?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getCheckUserId(),obj.getUserProductLineId(),obj.getCheckTime(),obj.getCheckMemo());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(CheckInfo obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update CheckInfo set ";
		sql=sql+" checkUserId=?,userProductLineId=?,checkTime=?,checkMemo=?";
		sql=sql+" where checkId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getCheckUserId(),obj.getUserProductLineId(),obj.getCheckTime(),obj.getCheckMemo(),obj.getCheckId());
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
		String sql="delete from CheckInfo where checkId=:checkId";
		int ret=this.execute(sql, "checkId",id);
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
	public Pager<CheckInfo> findAllPaged(){
		String sql="select * from CheckInfo";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("checkId");
		Pager<CheckInfo> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from CheckInfo where "+fieldName+"=:"+fieldName;
		CheckInfo oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getCheckId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
