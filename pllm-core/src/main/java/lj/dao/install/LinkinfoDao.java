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
import lj.model.install.Linkinfo;


@Repository
public class LinkinfoDao extends JdbcDao<Linkinfo> implements ILinkinfoDao {
	
	 /**
	 * 根据标识查询对象
	 * @param id
	 * @return
	 */
	@Override
	public Linkinfo find(Long linkId){
		String sql="select * from Linkinfo where linkId=:linkId";
		Linkinfo obj=this.find(sql,"linkId",linkId);
		return obj;
	}
	
	/**
	 * 查询所有对象数组
	 */
	@Override
	public Linkinfo[] findAll()
	{
		String sql="select * from Linkinfo";
		List<Linkinfo> list=this.findAll(sql);
		return list.toArray(new Linkinfo[0]);
	}
	
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	@Override
	public long insert(Linkinfo obj){
	    String sql="insert into Linkinfo(userProductLineId,fromKey,toKey,fromPort,toPort,point1,point2,point3,point4,point5,point6,point7,point8,point9,point10,point11,point12) ";
		sql=sql+" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int intRet=this.jdbcTemplate.update(sql,obj.getUserProductLineId(),obj.getFromKey(),obj.getToKey(),obj.getFromPort(),obj.getToPort(),obj.getPoint1(),obj.getPoint2(),obj.getPoint3(),obj.getPoint4(),obj.getPoint5(),obj.getPoint6(),obj.getPoint7(),obj.getPoint8(),obj.getPoint9(),obj.getPoint10(),obj.getPoint11(),obj.getPoint12());
		if(intRet<=0)
			return IntUtils.INT_FAIL;
		long insertId=this.getNewInsertId();
		return insertId;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public String update(Linkinfo obj){
		String msg = StringUtils.STR_EMPTY;
		String sql = "update Linkinfo set ";
		sql=sql+" userProductLineId=?,fromKey=?,toKey=?,fromPort=?,toPort=?,point1=?,point2=?,point3=?,point4=?,point5=?,point6=?,point7=?,point8=?,point9=?,point10=?,point11=?,point12=?";
		sql=sql+" where linkId=?";
		int intRet = this.jdbcTemplate.update(sql
		,obj.getUserProductLineId(),obj.getFromKey(),obj.getToKey(),obj.getFromPort(),obj.getToPort(),obj.getPoint1(),obj.getPoint2(),obj.getPoint3(),obj.getPoint4(),obj.getPoint5(),obj.getPoint6(),obj.getPoint7(),obj.getPoint8(),obj.getPoint9(),obj.getPoint10(),obj.getPoint11(),obj.getPoint12(),obj.getLinkId());
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
		String sql="delete from Linkinfo where linkId=:linkId";
		int ret=this.execute(sql, "linkId",id);
		if(ret>0)
			return StringUtils.STR_EMPTY;
		else
			return BaseServiceConst.MSG_DELETE_FAIL;
	}
	
	/**
	 * 删除很多对象
	 * @param id
	 * @return
	 */
	public String deleteByuserProductLineId(Long userProductLineId)
	{
		String sql="delete from Linkinfo where userProductLineId=:userProductLineId";
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
	public Pager<Linkinfo> findAllPaged(Integer linkId , Long userProductLineId , String fromKey , String toKey , String fromPort , Double toPort , Double point1 , Double point2 , Double point3 , Double point4 , Double point5 , Double point6 , Double point7 , Double point8 , Double point9 , Double point10 , Double point11 , Double point12  ){
		String sql="select * from Linkinfo";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("linkId", linkId);
		params.put("userProductLineId", userProductLineId);
		params.put("fromKey", fromKey);
		params.put("toKey", toKey);
		params.put("fromPort", fromPort);
		params.put("toPort", toPort);
		params.put("point1", point1);
		params.put("point2", point2);
		params.put("point3", point3);
		params.put("point4", point4);
		params.put("point5", point5);
		params.put("point6", point6);
		params.put("point7", point7);
		params.put("point8", point8);
		params.put("point9", point9);
		params.put("point10", point10);
		params.put("point11", point11);
		params.put("point12", point12);
		QueryObject query=JdbcDao.wrapperSqlWithCondition(sql,params);
	    if(StringUtils.isNullOrEmpty(QueryContext.getSortField())==true)
			QueryContext.setSortField("linkId");
		Pager<Linkinfo> pager=super.findAllPaged(query.sql, query.params);
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
		String sql="select * from Linkinfo where "+fieldName+"=:"+fieldName;
		Linkinfo oldObj=this.find(sql, fieldName, fieldValue);
		if(oldObj==null)
			return false;
		if(oldObj.getLinkId().equals(id)==true)
			return false;
		else
			return true;
	}

	@Override
	public Linkinfo[] findByUserProductLineId(Long userProductLineId) {
		String sql="select * from Linkinfo where userProductLineId=:userProductLineId";
		List<Linkinfo> list=this.findAll(sql,"userProductLineId",userProductLineId);
		return list.toArray(new Linkinfo[0]);
	}
	
}
