package lj.service.install;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.install.IViUserProductLineCheckDao;
import lj.model.base.Pager;
import lj.model.install.ViUserProductLineCheck;
import lj.service.BaseService;

@Service
public class ViUserProductLineCheckService extends BaseService {

    @Autowired
	private IViUserProductLineCheckDao viUserProductLineCheckDao=null;
	
	public ViUserProductLineCheck[] findAll()
    {
    	ViUserProductLineCheck[] objs=viUserProductLineCheckDao.findAll();
    	return objs;
    }
    
    public ViUserProductLineCheck find(Long id)
	{
		ViUserProductLineCheck obj=viUserProductLineCheckDao.find(id);
		return obj;
	}
    

    public Pager<ViUserProductLineCheck> findAllPagedViUserProductLineCheck(Long groupId , String userProductLineName  )
	{
		Pager<ViUserProductLineCheck> pager=viUserProductLineCheckDao.findAllPaged(groupId , userProductLineName  );
		return pager;
	}
	
	public Long insertViUserProductLineCheck(ViUserProductLineCheck obj)
	{
		Long retId=viUserProductLineCheckDao.insert(obj);
		return retId;
	}
	
	public String updateViUserProductLineCheck(ViUserProductLineCheck obj)
	{
		String msg=viUserProductLineCheckDao.update(obj);
		return msg;
	}
	
	public String deleteViUserProductLineCheck(Long id)
	{
		String msg=viUserProductLineCheckDao.delete(id);
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
		return viUserProductLineCheckDao.isRepeat(fieldName, fieldValue, id);
	}
	
}