package lj.dao.maintain;

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
import lj.model.maintain.MaintainInfo;


@Repository
public class MaintainInfoDao extends JdbcDao<MaintainInfo> implements IMaintainInfoDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public MaintainInfo find(Long maintainId){
		String sql="select * from MaintainInfo where maintainId=:maintainId";
		MaintainInfo obj=this.find(sql,"maintainId",maintainId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public MaintainInfo[] findAll()
	{
		String sql="select * from MaintainInfo";
		List<MaintainInfo> list=this.findAll(sql);
		return list.toArray(new MaintainInfo[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(MaintainInfo obj){
	    String sql="insert into MaintainInfo(userEquipmentId,maintainTime,maintainType,maintainMemo) ";
		sql=sql+" values(?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getUserEquipmentId(),obj.getMaintainTime(),obj.getMaintainType(),obj.getMaintainMemo());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(MaintainInfo obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update MaintainInfo set ";
		sql=sql+" userEquipmentId=?,maintainTime=?,maintainType=?,maintainMemo=?";
		sql=sql+" where maintainId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getUserEquipmentId(),obj.getMaintainTime(),obj.getMaintainType(),obj.getMaintainMemo(),obj.getMaintainId());
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
		String sql="delete from MaintainInfo where maintainId=:maintainId";
		int ret=this.execute(sql, "maintainId",id);
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
	public Pager<MaintainInfo> findAllPaged(){
		String sql="select * from MaintainInfo";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("maintainId");
		Pager<MaintainInfo> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from MaintainInfo where "+fieldName+"=:"+fieldName;
		MaintainInfo oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getMaintainId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
