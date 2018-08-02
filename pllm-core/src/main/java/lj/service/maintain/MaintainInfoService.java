package lj.service.maintain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.maintain.IMaintainInfoDao;
import lj.model.base.Pager;
import lj.model.maintain.MaintainInfo;
import lj.service.BaseService;

@Service
public class MaintainInfoService extends BaseService {

    @Autowired
	private IMaintainInfoDao maintainInfoDao=null;
	
	public MaintainInfo[] findAll()
    {
    	MaintainInfo[] objs=maintainInfoDao.findAll();
    	return objs;
    }
    
    public MaintainInfo find(Long id)
	{
		MaintainInfo obj=maintainInfoDao.find(id);
		return obj;
	}
    

    public Pager<MaintainInfo> findAllPagedMaintainInfo()
	{
		Pager<MaintainInfo> pager=maintainInfoDao.findAllPaged();
		return pager;
	}
	
	public Long insertMaintainInfo(MaintainInfo obj)
	{
		Long retId=maintainInfoDao.insert(obj);
		return retId;
	}
	
	public String updateMaintainInfo(MaintainInfo obj)
	{
		String msg=maintainInfoDao.update(obj);
		return msg;
	}
	
	public String deleteMaintainInfo(Long id)
	{
		String msg=maintainInfoDao.delete(id);
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
		return maintainInfoDao.isRepeat(fieldName, fieldValue, id);
	}
	
}