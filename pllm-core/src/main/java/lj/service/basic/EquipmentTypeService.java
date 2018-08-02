package lj.service.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.basic.IEquipmentTypeDao;
import lj.model.base.Pager;
import lj.model.basic.EquipmentType;
import lj.service.BaseService;

@Service
public class EquipmentTypeService extends BaseService {

    @Autowired
	private IEquipmentTypeDao equipmentTypeDao=null;
	
	public EquipmentType[] findAll()
    {
    	EquipmentType[] objs=equipmentTypeDao.findAll();
    	return objs;
    }
    
    public EquipmentType find(Long id)
	{
		EquipmentType obj=equipmentTypeDao.find(id);
		return obj;
	}
    

    public Pager<EquipmentType> findAllPagedEquipmentType(Long equipmentTypeId , String equipmentTypeName , String equipmentTypeDescribe  )
	{
		Pager<EquipmentType> pager=equipmentTypeDao.findAllPaged(equipmentTypeId , equipmentTypeName , equipmentTypeDescribe  );
		return pager;
	}
	
	public Long insertEquipmentType(EquipmentType obj)
	{
		Long retId=equipmentTypeDao.insert(obj);
		return retId;
	}
	
	public String updateEquipmentType(EquipmentType obj)
	{
		String msg=equipmentTypeDao.update(obj);
		return msg;
	}
	
	public String deleteEquipmentType(Long id)
	{
		String msg=equipmentTypeDao.delete(id);
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
		return equipmentTypeDao.isRepeat(fieldName, fieldValue, id);
	}
	
}