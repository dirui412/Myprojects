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
import lj.model.install.Puck;


@Repository
public class PuckDao extends JdbcDao<Puck> implements IPuckDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public Puck find(Long puckId){
		String sql="select * from Puck where puckId=:puckId";
		Puck obj=this.find(sql,"puckId",puckId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public Puck[] findAll()
	{
		String sql="select * from Puck";
		List<Puck> list=this.findAll(sql);
		return list.toArray(new Puck[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(Puck obj){
	    String sql="insert into Puck(puckName,arriveDate,arriveTime,goDate,goTime,puckArriveType,puckGoType,puckVersion) ";
		sql=sql+" values(?,?,?,?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getPuckName(),obj.getArriveDate(),obj.getArriveTime(),obj.getGoDate(),obj.getGoTime(),obj.getPuckArriveType(),obj.getPuckGoType(),obj.getPuckVersion());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(Puck obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update Puck set ";
		sql=sql+" puckName=?,arriveDate=?,arriveTime=?,goDate=?,goTime=?,puckArriveType=?,puckGoType=?,puckVersion=?";
		sql=sql+" where puckId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getPuckName(),obj.getArriveDate(),obj.getArriveTime(),obj.getGoDate(),obj.getGoTime(),obj.getPuckArriveType(),obj.getPuckGoType(),obj.getPuckVersion(),obj.getPuckId());
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
		String sql="delete from Puck where puckId=:puckId";
		int ret=this.execute(sql, "puckId",id);
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
	public Pager<Puck> findAllPaged(){
		String sql="select * from Puck";
		Map<String,Object> params=new HashMap<String,Object>();
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("puckId");
		Pager<Puck> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from Puck where "+fieldName+"=:"+fieldName;
		Puck oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getPuckId().equals(id)==true)
			return false;
		else
			return true;
	}
	
}
