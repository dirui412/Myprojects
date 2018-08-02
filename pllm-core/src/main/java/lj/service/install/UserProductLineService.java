package lj.service.install;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;

import lj.dao.install.ICheckInfoDao;
import lj.dao.install.IUserEquipmentDao;
import lj.dao.install.IUserProductLineDao;
import lj.model.base.Pager;
import lj.model.install.CheckInfo;
import lj.model.install.UserProductLine;
import lj.model.install.ViUserProductLineCheck;
import lj.service.BaseService;

@Service
public class UserProductLineService extends BaseService {
	public final static String STATE_USERPRODUCTLINE_SECTOTYPING = "选型中";
	public final static String STATE_USERPRODUCTLINE_SECTOTYPED = "已选型";
	public final static String STATE_USERPRODUCTLINE_INSTALLING="安装中";
	public final static String STATE_USERPRODUCTLINE_CHECKED="已验收";
	
	@Autowired
	private IUserProductLineDao userProductLineDao = null;
	@Autowired
	private IUserEquipmentDao userEquipmentDao = null;
	@Autowired
	private ICheckInfoDao checkInfoDao=null;

	public UserProductLine[] findAll() {
		UserProductLine[] objs = userProductLineDao.findAll();
		return objs;
	}

	public UserProductLine find(Long id) {
		UserProductLine obj = userProductLineDao.find(id);
		return obj;
	}

	public UserProductLine[] findAllByGroupId(Long GroupId)
	{
		UserProductLine[] objs = userProductLineDao.findAllByGroupId(GroupId);
		return objs;
	}
	public Pager<UserProductLine> findAllPagedUserProductLine() {
		Pager<UserProductLine> pager = userProductLineDao.findAllPaged();
		return pager;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public Long insertUserProductLine(UserProductLine obj) {
		obj.setProductLineState(STATE_USERPRODUCTLINE_SECTOTYPING);
		Long retId = userProductLineDao.insert(obj);
		return retId;
	}

	public String updateUserProductLine(UserProductLine obj) {
		String productLineState = userProductLineDao.find(obj.getUserProductLineId()).getProductLineState();
		obj.setProductLineState(productLineState);
		String msg = userProductLineDao.update(obj);
		return msg;
	}

	@Transactional
	public String deleteUserProductLine(Long userProductLineId) {
		String msg = userEquipmentDao.deleteByUserProductLineId(userProductLineId);
		msg = userProductLineDao.delete(userProductLineId);
		return msg;
	}

	/**
	 * 某个字段是否重复
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	public boolean isRepeat(String fieldName, Object fieldValue, Long id) {
		return userProductLineDao.isRepeat(fieldName, fieldValue, id);
	}

	/**
	 * 提交用户选型
	 * 
	 * @param obj
	 * @return
	 */
	public boolean confirmLectoType(Long id) {
		UserProductLine obj = userProductLineDao.find(id);
		obj.setProductLineState(STATE_USERPRODUCTLINE_SECTOTYPED);
		String strMsg = userProductLineDao.update(obj);
		return strMsg == null || strMsg.equals("");
	}
	
	/**
	 * 验收用户生产线
	 * @param check
	 * @return
	 */
	@Transactional
	public boolean checkUserProductLine(ViUserProductLineCheck check)
	{
		CheckInfo obj=checkInfoDao.findByUserProductLineId(check.getUserProductLineId());
		//如果该条生产线没有被验收,将信息插入CheckInfo表
		if(obj==null)
		{
			obj=new CheckInfo(null,check.getCheckUserId(),check.getUserProductLineId(),check.getCheckTime(),check.getCheckMemo());
			long ret=checkInfoDao.insert(obj);
			if(ret<=0)
				return false;
		}
		//更改UserProductLine表的生产线状态为“已验收”
		UserProductLine userProductLine=userProductLineDao.find(check.getUserProductLineId());
		if(userProductLine==null)
			return false;
		userProductLine.setProductLineState(STATE_USERPRODUCTLINE_CHECKED);
		String msg=userProductLineDao.update(userProductLine);
		return StringUtils.isNullOrEmpty(msg);
	}
	

}