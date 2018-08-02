package lj.service.collect;

import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.collect.IViequipmentcollectDao;
import lj.model.base.Pager;
import lj.model.collect.Viequipmentcollect;
import lj.service.BaseService;

@Service
public class ViequipmentcollectService extends BaseService {

	FileInputStream fileInputStream;
    @Autowired
	private IViequipmentcollectDao viequipmentcollectDao=null;
	
	public Viequipmentcollect[] findAll()
    {
    	Viequipmentcollect[] objs=viequipmentcollectDao.findAll();
    	return objs;
    }
    
    public Viequipmentcollect find(Long id)
	{
		Viequipmentcollect obj=viequipmentcollectDao.find(id);
		return obj;
	}
    

    public Pager<Viequipmentcollect> findAllPagedViequipmentcollect(  String userProductLineName  , String equipmentName ,  String paraName )
	{
		Pager<Viequipmentcollect> pager=viequipmentcollectDao.findAllPaged(userProductLineName ,  equipmentName , paraName );
		return pager;
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
		return viequipmentcollectDao.isRepeat(fieldName, fieldValue, id);
	}
	
}