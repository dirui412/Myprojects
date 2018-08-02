package lj.service.install;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lj.dao.install.ICheckInfoDao;
import lj.dao.install.IUserProductLineDao;
import lj.model.base.Pager;
import lj.model.install.CheckInfo;
import lj.model.install.UserProductLine;
import lj.model.install.ViUserProductLineCheck;
import lj.service.BaseService;

/**
 * 生产线验收服务
 * @author samlv
 *
 */
@Service
public class CheckInfoService extends BaseService {

    @Autowired
	private ICheckInfoDao checkInfoDao=null;

    
	
	public CheckInfo[] findAll()
    {
    	CheckInfo[] objs=checkInfoDao.findAll();
    	return objs;
    }
    
    public CheckInfo find(Long id)
	{
		CheckInfo obj=checkInfoDao.find(id);
		return obj;
	}
    

    public Pager<CheckInfo> findAllPagedCheckInfo()
	{
		Pager<CheckInfo> pager=checkInfoDao.findAllPaged();
		return pager;
	}
	
	public Long insertCheckInfo(CheckInfo obj)
	{
		Long retId=checkInfoDao.insert(obj);
		return retId;
	}
	
	public String updateCheckInfo(CheckInfo obj)
	{
		String msg=checkInfoDao.update(obj);
		return msg;
	}
	
	public String deleteCheckInfo(Long id)
	{
		String msg=checkInfoDao.delete(id);
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
		return checkInfoDao.isRepeat(fieldName, fieldValue, id);
	}
	
	
}