package lj.dao.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.basic.EquipmentInfo;
import lj.service.BaseServiceConst;
import lj.util.IntUtils;
import lj.util.StringUtils;
import lj.util.query.QueryObject;


@Repository
public class EquipmentInfoDao extends JdbcDao<EquipmentInfo> implements IEquipmentInfoDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public EquipmentInfo find(Long equipmentId){
		String sql="select * from EquipmentInfo where equipmentId=:equipmentId";
		EquipmentInfo obj=this.find(sql,"equipmentId",equipmentId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public EquipmentInfo[] findAll()
	{
		String sql="select * from EquipmentInfo";
		List<EquipmentInfo> list=this.findAll(sql);
		return list.toArray(new EquipmentInfo[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(EquipmentInfo obj){
	    String sql="insert into EquipmentInfo(equipmentTypeId,equipmentName,equipmentVersion,equipmentConfig,equipmentMemo,equipmentPicture) ";
		sql=sql+" values(?,?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getEquipmentTypeId(),obj.getEquipmentName(),obj.getEquipmentVersion()
				,obj.getEquipmentConfig(),obj.getEquipmentMemo(),obj.getEquipmentPicture());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	/**
	 * 插入设备图片
	 * @param obj
	 * @return
	 */
	@Override
	public String insertPicture(Long equipmentId,String url){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update EquipmentInfo set ";
		sql=sql+"equipmentPicture=?";
		sql=sql+" where equipmentId=?";
		int intRet = this.jdbcTemplate.update(sql,url,equipmentId);
		if (intRet <= 0)
			msg = BaseServiceConst.MSG_UPDATE_FAIL;
		return msg;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(EquipmentInfo obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update EquipmentInfo set ";
		sql=sql+"equipmentTypeId=?,equipmentName=?,equipmentVersion=?,equipmentConfig=?,equipmentMemo=?,equipmentPicture=?";
		sql=sql+" where equipmentId=?";
		int intRet = this.jdbcTemplate.update(sql,obj.getEquipmentTypeId()
		,obj.getEquipmentName(),obj.getEquipmentVersion(),obj.getEquipmentConfig(),
		obj.getEquipmentMemo(),obj.getEquipmentPicture(),obj.getEquipmentId());
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
		String sql="delete from EquipmentInfo where equipmentId=:equipmentId";
		int ret=this.execute(sql, "equipmentId",id);
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
	public Pager<EquipmentInfo> findAllPaged(){
		String sql="select * from EquipmentInfo";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("equipmentId");
		Pager<EquipmentInfo> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from EquipmentInfo where "+fieldName+"=:"+fieldName;
		EquipmentInfo oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getEquipmentId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
