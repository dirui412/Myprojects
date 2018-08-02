package lj.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.user.IGroupInfoDao;
import lj.model.base.Pager;
import lj.model.user.GroupInfo;
import lj.service.BaseService;

@Service
public class GroupInfoService extends BaseService {
	
	public final static Long GROUP_ID_ADMIN=1l;

    @Autowired
	private IGroupInfoDao groupInfoDao=null;
	
	public GroupInfo[] findAll()
    {
    	GroupInfo[] objs=groupInfoDao.findAll();
    	return objs;
    }
    
    public GroupInfo find(Long id)
	{
		GroupInfo obj=groupInfoDao.find(id);
		return obj;
	}
    

    public Pager<GroupInfo> findAllPagedGroupInfo()
	{
		Pager<GroupInfo> pager=groupInfoDao.findAllPaged();
		return pager;
	}
	
	public Long insertGroupInfo(GroupInfo obj)
	{
		obj.setOpTime(this.getNowTime());
		Long retId=groupInfoDao.insert(obj);
		return retId;
	}
	
	public String updateGroupInfo(GroupInfo obj)
	{
		obj.setOpTime(this.getNowTime());
		String msg=groupInfoDao.update(obj);
		return msg;
	}
	
	public String deleteGroupInfo(Long id)
	{
		if (groupInfoDao.isInuse(id) == true)
			return "班组信息已经使用!";
		String msg=groupInfoDao.delete(id);
		return msg;
	}
	
	/**
	 * 某个字段是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	public boolean isRepeat(String fieldName,Object fieldValue,Long id)
	{
		return groupInfoDao.isRepeat(fieldName, fieldValue, id);
	}
	
}