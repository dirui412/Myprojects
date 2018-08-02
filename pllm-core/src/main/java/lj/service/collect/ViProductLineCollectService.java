package lj.service.collect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.collect.IViProductLineCollectDao;
import lj.model.base.Pager;
import lj.model.collect.ViProductLineCollect;
import lj.service.BaseService;

@Service
public class ViProductLineCollectService extends BaseService {

    @Autowired
	private IViProductLineCollectDao viProductLineCollectDao=null;
	
	public ViProductLineCollect[] findAll()
    {
    	ViProductLineCollect[] objs=viProductLineCollectDao.findAll();
    	return objs;
    }
	
	
    
    public ViProductLineCollect find(Long id)
	{
		ViProductLineCollect obj=viProductLineCollectDao.find(id);
		return obj;
	}
    

    public Pager<ViProductLineCollect> findAllPagedViProductLineCollect(Long groupId , String userProductLineName , String paramName)
	{
		Pager<ViProductLineCollect> pager=viProductLineCollectDao.findAllPaged(groupId,userProductLineName,paramName);
		return pager;
	}
    
    public Pager<ViProductLineCollect> findAllPagedViProductLineCollect(Long userProductLineId  )
   	{
   		Pager<ViProductLineCollect> pager=viProductLineCollectDao.findAllPaged(userProductLineId);
   		return pager;
   	}
	
	public Long insertViProductLineCollect(ViProductLineCollect obj)
	{
		Long retId=viProductLineCollectDao.insert(obj);
		return retId;
	}
	
	public String updateViProductLineCollect(ViProductLineCollect obj)
	{
		String msg=viProductLineCollectDao.update(obj);
		return msg;
	}
	
	public String deleteViProductLineCollect(Long id)
	{
		String msg=viProductLineCollectDao.delete(id);
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
		return viProductLineCollectDao.isRepeat(fieldName, fieldValue, id);
	}
	
	
	
}