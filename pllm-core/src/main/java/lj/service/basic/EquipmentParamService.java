package lj.service.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.basic.IEquipmentParamDao;
import lj.model.base.Pager;
import lj.model.basic.EquipmentParam;
import lj.service.BaseService;

@Service
public class EquipmentParamService extends BaseService {

    @Autowired
	private IEquipmentParamDao equipmentParamDao=null;
	
	public EquipmentParam[] findAll()
    {
    	EquipmentParam[] objs=equipmentParamDao.findAll();
    	return objs;
    }
    
    public EquipmentParam find(Long id)
	{
		EquipmentParam obj=equipmentParamDao.find(id);
		return obj;
	}
    
    public EquipmentParam[] findAllByEquipmentId(Long equipmentId)
	{
    	EquipmentParam[] objs = equipmentParamDao.findAllByEquipmentId(equipmentId);
    	return objs;
	}
    
    public Pager<EquipmentParam> findAllPagedEquipmentParam()
	{
		Pager<EquipmentParam> pager=equipmentParamDao.findAllPaged();
		return pager;
	}
	
	public Long insertEquipmentParam(EquipmentParam obj)
	{
		Long retId=equipmentParamDao.insert(obj);
		return retId;
	}
	
	public String updateEquipmentParam(EquipmentParam obj)
	{
		String msg=equipmentParamDao.update(obj);
		return msg;
	}
	
	public String deleteEquipmentParam(Long id)
	{
		String msg=equipmentParamDao.delete(id);
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
		return equipmentParamDao.isRepeat(fieldName, fieldValue, id);
	}
	
}