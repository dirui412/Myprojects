package lj.service.install;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;

import lj.dao.install.IUserEquipmentDao;
import lj.dao.install.IUserProductLineDao;
import lj.model.base.Pager;
import lj.model.install.UserEquipment;
import lj.model.install.UserProductLine;
import lj.service.BaseService;

@Service
public class UserEquipmentService extends BaseService {
	public final static String STATE_USEREQUIPMENT_LECTOTYPE="已选型";
	public final static String STATE_USEREQUIPMENT_INSTALLED="已安装";
	public final static String STATE_USEREQUIPMENT_WRITEOFFED="已报废";
	

    @Autowired
	private IUserEquipmentDao userEquipmentDao=null;
    @Autowired
    private IUserProductLineDao userProductLineDao=null;
	
	public UserEquipment[] findAll()
    {
    	UserEquipment[] objs=userEquipmentDao.findAll();
    	return objs;
    }
	
	public UserEquipment[] findByUserProductLineId(Long userProductLineId){
		UserEquipment[] objs=userEquipmentDao.findByUserProductLineId(userProductLineId);
		return objs;
	}
    public UserEquipment find(Long id)
	{
		UserEquipment obj=userEquipmentDao.find(id);
		return obj;
	}
    

    public Pager<UserEquipment> findAllPagedUserEquipment()
	{
		Pager<UserEquipment> pager=userEquipmentDao.findAllPaged();
		return pager;
	}
	
	public Long insertUserEquipment(UserEquipment obj)
	{
		obj.setEquipmentState(STATE_USEREQUIPMENT_INSTALLED);
		Long retId=userEquipmentDao.insert(obj);
		return retId;
	}
	
	public String updateUserEquipment(UserEquipment obj)
	{
		String msg=userEquipmentDao.update(obj);
		return msg;
	}
	
	public String deleteUserEquipment(Long id)
	{
		String msg=userEquipmentDao.delete(id);
		return msg;
	}
	public String deleteByUserProductLineId(Long userProductLineId)
	{	
		System.out.println("执行了按照生产线id删除用户设备的方法");
		String msg=userEquipmentDao.deleteByUserProductLineId(userProductLineId);
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
		return userEquipmentDao.isRepeat(fieldName, fieldValue, id);
	}
	
	/**
	 * 安装用户设备
	 * @param obj
	 * @return
	 */
	@Transactional
	public boolean installEquipment(UserEquipment obj)
	{
		UserProductLine userProductLine=userProductLineDao.find(obj.getUserProductLineId());
		userProductLine.setProductLineState(STATE_USEREQUIPMENT_INSTALLED);
		String strMsg=userProductLineDao.update(userProductLine);
		System.out.println("1111111111111执行了： installEquipment方法，设备和生产线状态都变为：已安装");
		if(StringUtils.isNullOrEmpty(strMsg)==false)
			return false;
		obj.setEquipmentState(STATE_USEREQUIPMENT_INSTALLED);
		strMsg=userEquipmentDao.update(obj);
		return strMsg==null || strMsg.equals("");
	}
	
}