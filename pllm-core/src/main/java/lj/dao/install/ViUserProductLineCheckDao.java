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
import lj.model.install.ViUserProductLineCheck;


@Repository
public class ViUserProductLineCheckDao extends JdbcDao<ViUserProductLineCheck> implements IViUserProductLineCheckDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public ViUserProductLineCheck find(Long userProductLineId){
		String sql="select * from ViUserProductLineCheck where userProductLineId=:userProductLineId";
		ViUserProductLineCheck obj=this.find(sql,"userProductLineId",userProductLineId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public ViUserProductLineCheck[] findAll()
	{
		String sql="select * from ViUserProductLineCheck";
		List<ViUserProductLineCheck> list=this.findAll(sql);
		return list.toArray(new ViUserProductLineCheck[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(ViUserProductLineCheck obj){
	    String sql="insert into ViUserProductLineCheck(productLineId,groupId,productLineState,lectotypeUserId,lectotypeTime,lectotypeParams,lectotypeMemo,groupName,lectotypeUserName,productName,productType,checkId,checkUserId,checkTime,checkMemo,checkUserName) ";
		sql=sql+" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getProductLineId(),obj.getGroupId(),obj.getProductLineState(),obj.getLectotypeUserId(),obj.getLectotypeTime(),obj.getLectotypeParams(),obj.getLectotypeMemo(),obj.getGroupName(),obj.getLectotypeUserName(),obj.getProductName(),obj.getProductType(),obj.getCheckId(),obj.getCheckUserId(),obj.getCheckTime(),obj.getCheckMemo(),obj.getCheckUserName());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(ViUserProductLineCheck obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update ViUserProductLineCheck set ";
		sql=sql+" productLineId=?,groupId=?,productLineState=?,lectotypeUserId=?,lectotypeTime=?,lectotypeParams=?,lectotypeMemo=?,groupName=?,lectotypeUserName=?,productName=?,productType=?,checkId=?,checkUserId=?,checkTime=?,checkMemo=?,checkUserName=?";
		sql=sql+" where userProductLineId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getProductLineId(),obj.getGroupId(),obj.getProductLineState(),obj.getLectotypeUserId(),obj.getLectotypeTime(),obj.getLectotypeParams(),obj.getLectotypeMemo(),obj.getGroupName(),obj.getLectotypeUserName(),obj.getProductName(),obj.getProductType(),obj.getCheckId(),obj.getCheckUserId(),obj.getCheckTime(),obj.getCheckMemo(),obj.getCheckUserName(),obj.getUserProductLineId());
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
		String sql="delete from ViUserProductLineCheck where userProductLineId=:userProductLineId";
		int ret=this.execute(sql, "userProductLineId",id);
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
	public Pager<ViUserProductLineCheck> findAllPaged(Long groupId , String userProductLineName){
		String sql="select * from ViUserProductLineCheck where (productLineState='已安装' or productLineState='已验收') ";
		if(groupId!=null && groupId>0)
			sql=sql+" and groupId="+groupId;
		if(StringUtils.isNullOrEmpty(userProductLineName)==false)
			sql=sql+" and userProductLineName like '%"+userProductLineName+"%' ";
		 if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
				QueryContext.setSortField("userProductLineId");
		Pager<ViUserProductLineCheck> pager=super.findAllPaged(sql);
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
		String sql="select * from ViUserProductLineCheck where "+fieldName+"=:"+fieldName;
		ViUserProductLineCheck oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getUserProductLineId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
