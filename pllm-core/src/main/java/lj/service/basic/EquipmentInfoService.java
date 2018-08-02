package lj.service.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.basic.IEquipmentInfoDao;
import lj.dao.basic.ViequipmentinfoDao;
import lj.model.base.Pager;
import lj.model.basic.EquipmentInfo;
import lj.model.basic.Viequipmentinfo;
import lj.service.BaseService;

@Service
public class EquipmentInfoService extends BaseService {

    @Autowired
	private IEquipmentInfoDao equipmentInfoDao=null;
	
    @Autowired
    private ViequipmentinfoDao viequipmentinfoDao;
    
	public EquipmentInfo[] findAll()
    {
    	EquipmentInfo[] objs=equipmentInfoDao.findAll();
    	return objs;
    }
	public Viequipmentinfo[] findByType(Long equipmentTypeId)
	{
		Viequipmentinfo[] objs = viequipmentinfoDao.findByType(equipmentTypeId);
		return objs;
	}
    public EquipmentInfo find(Long id)
	{
		EquipmentInfo obj=equipmentInfoDao.find(id);
		return obj;
	}
    
    public Viequipmentinfo findVi(Long id)
	{
    	Viequipmentinfo obj=viequipmentinfoDao.find(id);
		return obj;
	}
    
    public Pager<Viequipmentinfo> findAllPagedEquipmentInfo()
   	{
   		Pager<Viequipmentinfo> pager=viequipmentinfoDao.findAllPaged();
   		return pager;
   	}
	
	public Long insertEquipmentInfo(EquipmentInfo obj)
	{
		Long retId=equipmentInfoDao.insert(obj);
		return retId;
	}
	
	public String insertPicture(Long equipmentId,String url){
		String msg = equipmentInfoDao.insertPicture(equipmentId, url);
		return msg;
	}
	
	public String updateEquipmentInfo(EquipmentInfo obj)
	{
		String msg=equipmentInfoDao.update(obj);
		return msg;
	}
	
	public String deleteEquipmentInfo(Long id)
	{
		String msg=equipmentInfoDao.delete(id);
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
		return equipmentInfoDao.isRepeat(fieldName, fieldValue, id);
	}
	
}