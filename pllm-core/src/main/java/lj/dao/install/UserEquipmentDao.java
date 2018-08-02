package lj.dao.install;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.install.UserEquipment;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;
import lj.util.query.QueryObject;


@Repository
public class UserEquipmentDao extends JdbcDao<UserEquipment> implements IUserEquipmentDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public UserEquipment find(Long userEquipmentId){
		String sql="select * from UserEquipment where userEquipmentId=:userEquipmentId";
		UserEquipment obj=this.find(sql,"userEquipmentId",userEquipmentId);
		return obj;
	}
	/**
	 * 查询所有对象数组
	 */
	@Override
	public UserEquipment[] findByUserProductLineId(Long userProductLineId)
	{
		String sql="select * from UserEquipment where userProductLineId=:userProductLineId";
		List<UserEquipment> list=this.findAll(sql, "userProductLineId",userProductLineId);
		return list.toArray(new UserEquipment[0]);
	}
	/**
	 * 查询所有对象数组
	 */
	@Override
	public UserEquipment[] findAll()
	{
		String sql="select * from UserEquipment";
		List<UserEquipment> list=this.findAll(sql);
		return list.toArray(new UserEquipment[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(UserEquipment obj){
	    String sql="insert into UserEquipment(userProductLineId,equipmentId,installTime,equipmentState,installParams,installMemo,text,keyy,loc1,loc2) ";
		sql=sql+" values(?,?,?,?,?,?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getUserProductLineId(),obj.getEquipmentId(),obj.getInstallTime(),obj.getEquipmentState(),obj.getInstallParams(),obj.getInstallMemo(),obj.getText(),obj.getKeyy(),obj.getLoc1(),obj.getLoc2());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(UserEquipment obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update UserEquipment set ";
		sql=sql+" userProductLineId=?,equipmentId=?,installTime=?,equipmentState=?,installParams=?,installMemo=?,text=?,keyy=?,loc1=?,loc2=?";
		sql=sql+" where userEquipmentId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getUserProductLineId(),obj.getEquipmentId(),obj.getInstallTime(),obj.getEquipmentState(),obj.getInstallParams(),obj.getInstallMemo(),obj.getText(),obj.getKeyy(),obj.getLoc1(),obj.getLoc2(),obj.getUserEquipmentId());
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
		String sql="delete from UserEquipment where userEquipmentId=:userEquipmentId";
		int ret=this.execute(sql, "userEquipmentId",id);
		if(ret>0)
			return StringUtils.STR_EMPTY;
		else
			return BaseServiceConst.MSG_DELETE_FAIL;
	}
	
	public String deleteByUserProductLineId(Long userProductLineId)
	{
		String sql="delete from UserEquipment where userProductLineId=:userProductLineId";
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
	public Pager<UserEquipment> findAllPaged(){
		String sql="select * from UserEquipment";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("userEquipmentId");
		Pager<UserEquipment> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from UserEquipment where "+fieldName+"=:"+fieldName;
		UserEquipment oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getUserEquipmentId().equals(id)==true)
			return false;
		else
			return true;
	}
	

	
}
