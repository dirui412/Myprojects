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
import lj.model.maintain.WriteoffInfo;


@Repository
public class WriteoffInfoDao extends JdbcDao<WriteoffInfo> implements IWriteoffInfoDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public WriteoffInfo find(Long writeoffId){
		String sql="select * from WriteoffInfo where writeoffId=:writeoffId";
		WriteoffInfo obj=this.find(sql,"writeoffId",writeoffId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public WriteoffInfo[] findAll()
	{
		String sql="select * from WriteoffInfo";
		List<WriteoffInfo> list=this.findAll(sql);
		return list.toArray(new WriteoffInfo[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(WriteoffInfo obj){
	    String sql="insert into WriteoffInfo(userEquipmentId,writeoffTime,writeoffType,writeoffMemo) ";
		sql=sql+" values(?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getUserEquipmentId(),obj.getWriteoffTime(),obj.getWriteoffType(),obj.getWriteoffMemo());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(WriteoffInfo obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update WriteoffInfo set ";
		sql=sql+" userEquipmentId=?,writeoffTime=?,writeoffType=?,writeoffMemo=?";
		sql=sql+" where writeoffId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getUserEquipmentId(),obj.getWriteoffTime(),obj.getWriteoffType(),obj.getWriteoffMemo(),obj.getWriteoffId());
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
		String sql="delete from WriteoffInfo where writeoffId=:writeoffId";
		int ret=this.execute(sql, "writeoffId",id);
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
	public Pager<WriteoffInfo> findAllPaged(){
		String sql="select * from WriteoffInfo";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("writeoffId");
		Pager<WriteoffInfo> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from WriteoffInfo where "+fieldName+"=:"+fieldName;
		WriteoffInfo oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getWriteoffId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
