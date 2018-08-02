package lj.service.install;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.install.IViUserEquipmentDao;
import lj.model.base.Pager;
import lj.model.install.ViUserEquipment;
import lj.service.BaseService;

@Service
public class ViUserEquipmentService extends BaseService {

    @Autowired
	private IViUserEquipmentDao viUserEquipmentDao=null;
	
	public ViUserEquipment[] findAll()
    {
    	ViUserEquipment[] objs=viUserEquipmentDao.findAll();
    	return objs;
    }
	

	public ViUserEquipment[] findAllByUserProductLineId(Long userProductLineId)
	{
		ViUserEquipment[] objs=viUserEquipmentDao.findAllByUserProductLineId(userProductLineId);
    	return objs;
	}
    
    public ViUserEquipment find(Long id)
	{
		ViUserEquipment obj=viUserEquipmentDao.find(id);
		return obj;
	}
    

    public Pager<ViUserEquipment> findAllPagedViUserEquipment(Long queryUserProductLineId,String equipmentName  )
	{
		Pager<ViUserEquipment> pager=viUserEquipmentDao.findAllPaged(queryUserProductLineId,equipmentName  );
		return pager;
	}
	
	public Long insertViUserEquipment(ViUserEquipment obj)
	{
		Long retId=viUserEquipmentDao.insert(obj);
		return retId;
	}
	
	public String updateViUserEquipment(ViUserEquipment obj)
	{
		String msg=viUserEquipmentDao.update(obj);
		return msg;
	}
	
	public String deleteViUserEquipment(Long id)
	{
		String msg=viUserEquipmentDao.delete(id);
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
		return viUserEquipmentDao.isRepeat(fieldName, fieldValue, id);
	}
	
}