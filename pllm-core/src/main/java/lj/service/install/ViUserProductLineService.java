package lj.service.install;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.install.IViUserProductLineDao;
import lj.model.base.Pager;
import lj.model.install.ViUserProductLine;
import lj.service.BaseService;

@Service
public class ViUserProductLineService extends BaseService {

    @Autowired
	private IViUserProductLineDao viUserProductLineDao=null;
	
	public ViUserProductLine[] findAll()
    {
    	ViUserProductLine[] objs=viUserProductLineDao.findAll();
    	return objs;
    }
	
	public ViUserProductLine[] findAllByGroupId(Long groupId)
    {
    	ViUserProductLine[] objs=viUserProductLineDao.findAllByGroupId(groupId);
    	return objs;
    }
    
    public ViUserProductLine find(Long id)
	{
		ViUserProductLine obj=viUserProductLineDao.find(id);
		return obj;
	}
    

    public Pager<ViUserProductLine> findAllPagedViUserProductLine(String userProductLineName,Long groupId  )
	{
		Pager<ViUserProductLine> pager=viUserProductLineDao.findAllPaged(userProductLineName,groupId);
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
		return viUserProductLineDao.isRepeat(fieldName, fieldValue, id);
	}
	
}