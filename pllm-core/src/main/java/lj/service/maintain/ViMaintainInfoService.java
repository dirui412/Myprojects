package lj.service.maintain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.maintain.IViMaintainInfoDao;
import lj.model.base.Pager;
import lj.model.maintain.ViMaintainInfo;
import lj.service.BaseService;

@Service
public class ViMaintainInfoService extends BaseService {

    @Autowired
	private IViMaintainInfoDao viMaintainInfoDao=null;
	
	public ViMaintainInfo[] findAll()
    {
    	ViMaintainInfo[] objs=viMaintainInfoDao.findAll();
    	return objs;
    }
    
    public ViMaintainInfo find(Long id)
	{
		ViMaintainInfo obj=viMaintainInfoDao.find(id);
		return obj;
	}
    

    public Pager<ViMaintainInfo> findAllPagedViMaintainInfo(String userProductLineName,String equipmentName  )
	{
		Pager<ViMaintainInfo> pager=viMaintainInfoDao.findAllPaged(userProductLineName,equipmentName  );
		return pager;
	}
	
	public Long insertViMaintainInfo(ViMaintainInfo obj)
	{
		Long retId=viMaintainInfoDao.insert(obj);
		return retId;
	}
	
	public String updateViMaintainInfo(ViMaintainInfo obj)
	{
		String msg=viMaintainInfoDao.update(obj);
		return msg;
	}
	
	public String deleteViMaintainInfo(Long id)
	{
		String msg=viMaintainInfoDao.delete(id);
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
		return viMaintainInfoDao.isRepeat(fieldName, fieldValue, id);
	}
	
}