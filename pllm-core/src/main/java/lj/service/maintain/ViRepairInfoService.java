package lj.service.maintain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.maintain.IViRepairInfoDao;
import lj.model.base.Pager;
import lj.model.maintain.ViRepairInfo;
import lj.service.BaseService;

@Service
public class ViRepairInfoService extends BaseService {

    @Autowired
	private IViRepairInfoDao viRepairInfoDao=null;
	
	public ViRepairInfo[] findAll()
    {
    	ViRepairInfo[] objs=viRepairInfoDao.findAll();
    	return objs;
    }
    
    public ViRepairInfo find(Long id)
	{
		ViRepairInfo obj=viRepairInfoDao.find(id);
		return obj;
	}
    

    public Pager<ViRepairInfo> findAllPagedViRepairInfo(String userProductLineName,String equipmentName  )
	{
		Pager<ViRepairInfo> pager=viRepairInfoDao.findAllPaged(userProductLineName,equipmentName  );
		return pager;
	}
	
	public Long insertViRepairInfo(ViRepairInfo obj)
	{
		Long retId=viRepairInfoDao.insert(obj);
		return retId;
	}
	
	public String updateViRepairInfo(ViRepairInfo obj)
	{
		String msg=viRepairInfoDao.update(obj);
		return msg;
	}
	
	public String deleteViRepairInfo(Long id)
	{
		String msg=viRepairInfoDao.delete(id);
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
		return viRepairInfoDao.isRepeat(fieldName, fieldValue, id);
	}
	
}