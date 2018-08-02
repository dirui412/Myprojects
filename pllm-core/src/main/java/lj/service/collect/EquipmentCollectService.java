package lj.service.collect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.collect.IEquipmentCollectDao;
import lj.model.base.Pager;
import lj.model.collect.EquipmentCollect;
import lj.service.BaseService;

@Service
public class EquipmentCollectService extends BaseService {

    @Autowired
	private IEquipmentCollectDao equipmentCollectDao=null;
	
	public EquipmentCollect[] findAll()
    {
    	EquipmentCollect[] objs=equipmentCollectDao.findAll();
    	return objs;
    }
    
    public EquipmentCollect find(Long id)
	{
		EquipmentCollect obj=equipmentCollectDao.find(id);
		return obj;
	}
    

    public Pager<EquipmentCollect> findAllPagedEquipmentCollect()
	{
		Pager<EquipmentCollect> pager=equipmentCollectDao.findAllPaged();
		return pager;
	}
	
	public Long insertEquipmentCollect(EquipmentCollect obj)
	{
		Long retId=equipmentCollectDao.insert(obj);
		return retId;
	}
	
	public String updateEquipmentCollect(EquipmentCollect obj)
	{
		String msg=equipmentCollectDao.update(obj);
		return msg;
	}
	
	public String deleteEquipmentCollect(Long id)
	{
		String msg=equipmentCollectDao.delete(id);
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
		return equipmentCollectDao.isRepeat(fieldName, fieldValue, id);
	}
	
}