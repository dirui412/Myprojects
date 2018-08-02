package lj.service.maintain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.maintain.IRepairInfoDao;
import lj.model.base.Pager;
import lj.model.maintain.RepairInfo;
import lj.service.BaseService;

@Service
public class RepairInfoService extends BaseService {

    @Autowired
	private IRepairInfoDao repairInfoDao=null;
	
	public RepairInfo[] findAll()
    {
    	RepairInfo[] objs=repairInfoDao.findAll();
    	return objs;
    }
    
    public RepairInfo find(Long id)
	{
		RepairInfo obj=repairInfoDao.find(id);
		return obj;
	}
    

    public Pager<RepairInfo> findAllPagedRepairInfo()
	{
		Pager<RepairInfo> pager=repairInfoDao.findAllPaged();
		return pager;
	}
	
	public Long insertRepairInfo(RepairInfo obj)
	{
		Long retId=repairInfoDao.insert(obj);
		return retId;
	}
	
	public String updateRepairInfo(RepairInfo obj)
	{
		String msg=repairInfoDao.update(obj);
		return msg;
	}
	
	public String deleteRepairInfo(Long id)
	{
		String msg=repairInfoDao.delete(id);
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
		return repairInfoDao.isRepeat(fieldName, fieldValue, id);
	}
	
}