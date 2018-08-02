package lj.service.maintain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.maintain.IViWriteoffInfoDao;
import lj.model.base.Pager;
import lj.model.maintain.ViWriteoffInfo;
import lj.service.BaseService;

@Service
public class ViWriteoffInfoService extends BaseService {

    @Autowired
	private IViWriteoffInfoDao viWriteoffInfoDao=null;
	
	public ViWriteoffInfo[] findAll()
    {
    	ViWriteoffInfo[] objs=viWriteoffInfoDao.findAll();
    	return objs;
    }
    
    public ViWriteoffInfo find(Long id)
	{
		ViWriteoffInfo obj=viWriteoffInfoDao.find(id);
		return obj;
	}
    

    public Pager<ViWriteoffInfo> findAllPagedViWriteoffInfo(String userProductLineName , String equipmentName  )
	{
		Pager<ViWriteoffInfo> pager=viWriteoffInfoDao.findAllPaged(userProductLineName , equipmentName  );
		return pager;
	}
	
	public Long insertViWriteoffInfo(ViWriteoffInfo obj)
	{
		Long retId=viWriteoffInfoDao.insert(obj);
		return retId;
	}
	
	public String updateViWriteoffInfo(ViWriteoffInfo obj)
	{
		String msg=viWriteoffInfoDao.update(obj);
		return msg;
	}
	
	public String deleteViWriteoffInfo(Long id)
	{
		String msg=viWriteoffInfoDao.delete(id);
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
		return viWriteoffInfoDao.isRepeat(fieldName, fieldValue, id);
	}
	
}