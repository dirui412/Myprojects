package lj.service.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.basic.IViEquipmentParamDao;
import lj.model.base.Pager;
import lj.model.basic.ViEquipmentParam;
import lj.service.BaseService;

@Service
public class ViEquipmentParamService extends BaseService {

    @Autowired
	private IViEquipmentParamDao viEquipmentParamDao=null;
	
	public ViEquipmentParam[] findAll()
    {
    	ViEquipmentParam[] objs=viEquipmentParamDao.findAll();
    	return objs;
    }
    
    public ViEquipmentParam find(Long id)
	{
		ViEquipmentParam obj=viEquipmentParamDao.find(id);
		return obj;
	}
    

    public Pager<ViEquipmentParam> findAllPagedViEquipmentParam(
    		Long queryEquipmentId,String paramName , String paramType  )
	{
		Pager<ViEquipmentParam> pager=viEquipmentParamDao.findAllPaged(
				queryEquipmentId,paramName , paramType  );
		return pager;
	}
	
	public Long insertViEquipmentParam(ViEquipmentParam obj)
	{
		Long retId=viEquipmentParamDao.insert(obj);
		return retId;
	}
	
	public String updateViEquipmentParam(ViEquipmentParam obj)
	{
		String msg=viEquipmentParamDao.update(obj);
		return msg;
	}
	
	public String deleteViEquipmentParam(Long id)
	{
		String msg=viEquipmentParamDao.delete(id);
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
		return viEquipmentParamDao.isRepeat(fieldName, fieldValue, id);
	}
	
}