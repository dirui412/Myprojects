package lj.service.maintain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lj.dao.install.IUserEquipmentDao;
import lj.dao.maintain.IWriteoffInfoDao;
import lj.model.base.Pager;
import lj.model.install.UserEquipment;
import lj.model.maintain.WriteoffInfo;
import lj.service.BaseService;
import lj.service.install.UserEquipmentService;
import lj.util.StringUtils;

@Service
public class WriteoffInfoService extends BaseService {

    @Autowired
	private IWriteoffInfoDao writeoffInfoDao=null;
    @Autowired
    private IUserEquipmentDao userEquipmentDao=null;
    @Autowired
    private UserEquipmentService userEquipmentService=null;
	
	public WriteoffInfo[] findAll()
    {
    	WriteoffInfo[] objs=writeoffInfoDao.findAll();
    	return objs;
    }
    
    public WriteoffInfo find(Long id)
	{
		WriteoffInfo obj=writeoffInfoDao.find(id);
		return obj;
	}
    

    public Pager<WriteoffInfo> findAllPagedWriteoffInfo()
	{
		Pager<WriteoffInfo> pager=writeoffInfoDao.findAllPaged();
		return pager;
	}
	
    /**
     * 新增报废信息
     * @param obj
     * @return
     */
    @Transactional
	public Long insertWriteoffInfo(WriteoffInfo obj)
	{
    	UserEquipment userEquipment=userEquipmentDao.find(obj.getUserEquipmentId());
    	if(userEquipment==null)
    		return -1l;
    	userEquipment.setEquipmentState(UserEquipmentService.STATE_USEREQUIPMENT_WRITEOFFED);
    	String msg=userEquipmentService.updateUserEquipment(userEquipment);
		if(StringUtils.isNullOrEmpty(msg)==false)
			return -1l;
    	Long retId=writeoffInfoDao.insert(obj);
		return retId;
	}
	
	public String updateWriteoffInfo(WriteoffInfo obj)
	{
		String msg=writeoffInfoDao.update(obj);
		return msg;
	}
	
	/**
	 * 删除报废信息
	 * @param id
	 * @return
	 */
	@Transactional
	public String deleteWriteoffInfo(Long id)
	{
		WriteoffInfo obj=writeoffInfoDao.find(id);
		if(obj==null)
			return "报废失败";
		UserEquipment userEquipment=userEquipmentDao.find(obj.getUserEquipmentId());
    	if(userEquipment==null)
    		return "报废失败";
    	userEquipment.setEquipmentState(UserEquipmentService.STATE_USEREQUIPMENT_WRITEOFFED);
    	String msg=userEquipmentService.updateUserEquipment(userEquipment);
		if(StringUtils.isNullOrEmpty(msg)==false)
			return "报废失败";
		msg=writeoffInfoDao.delete(id);
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
		return writeoffInfoDao.isRepeat(fieldName, fieldValue, id);
	}
	
}