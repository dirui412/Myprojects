package lj.service.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.basic.IViProductLineParamDao;
import lj.model.base.Pager;
import lj.model.basic.ViProductLineParam;
import lj.service.BaseService;

@Service
public class ViProductLineParamService extends BaseService {

    @Autowired
	private IViProductLineParamDao viProductLineParamDao=null;
	
	public ViProductLineParam[] findAll()
    {
    	ViProductLineParam[] objs=viProductLineParamDao.findAll();
    	return objs;
    }
    
    public ViProductLineParam find(Long id)
	{
		ViProductLineParam obj=viProductLineParamDao.find(id);
		return obj;
	}
    

    public Pager<ViProductLineParam> findAllPagedViProductLineParam(String queryProductLineId,
    		String queryParamName,String queryParamType)
	{
		Pager<ViProductLineParam> pager=viProductLineParamDao.findAllPaged(queryProductLineId,
	    		queryParamName,queryParamType);
		return pager;
	}
	
	public Long insertViProductLineParam(ViProductLineParam obj)
	{
		Long retId=viProductLineParamDao.insert(obj);
		return retId;
	}
	
	public String updateViProductLineParam(ViProductLineParam obj)
	{
		String msg=viProductLineParamDao.update(obj);
		return msg;
	}
	
	public String deleteViProductLineParam(Long id)
	{
		String msg=viProductLineParamDao.delete(id);
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
		return viProductLineParamDao.isRepeat(fieldName, fieldValue, id);
	}
	
}