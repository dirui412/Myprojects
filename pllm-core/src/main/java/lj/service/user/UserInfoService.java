package lj.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.sys.IModuleInfoDao;
import lj.dao.sys.IViModuleInfoDao;
import lj.dao.user.IUserInfoDao;
import lj.dao.user.IUserRoleDao;
import lj.dao.user.IViUserInfoDao;
import lj.dao.user.IViUserRoleDao;
import lj.dao.user.IViUserRolePrivDao;
import lj.global.AppVar;
import lj.model.base.Pager;
import lj.model.sys.ModuleInfo;
import lj.model.sys.ViModuleInfo;
import lj.model.user.UserInfo;
import lj.model.user.ViUserInfo;
import lj.model.user.ViUserRole;
import lj.model.user.ViUserRolePriv;
import lj.service.BaseService;
import lj.service.BaseServiceConst;
import lj.util.Md5Utils;
import lj.util.StringUtils;

/**
 * 用户信息服务
 * @author samlv
 *
 */
@Service
public class UserInfoService extends BaseService {

	@Autowired
	private IUserInfoDao userDao = null;
	@Autowired
	private IViUserInfoDao viUserInfoDao = null;
	@Autowired
	private IUserRoleDao userRoleDao = null;
	@Autowired
	private IViUserRoleDao viUserRoleDao = null;
	@Autowired
	private IViUserRolePrivDao viUserRolePrivDao = null;
	@Autowired
	private IViModuleInfoDao viModuleInfoDao = null;
	@Autowired
	private IModuleInfoDao moduleInfoDao = null;



	public UserInfo findUserInfoByUserCode(String userCode) {
		UserInfo user = userDao.findByUsercode(userCode);
		return user;
	}

	public String login(String userCode, String userPassword) {
		String str = StringUtils.STR_EMPTY;
		UserInfo obj = this.findUserInfoByUserCode(userCode);
		if (obj == null)
			return UserInfoServiceConst.LOGIN_ERROR_USERNOTEXISTS;
		String encodeInput = Md5Utils.MD5Encode(userPassword);
		if (obj.getUserPassword().equals(encodeInput) == false)
			return UserInfoServiceConst.LOGIN_ERROR_PASSWORD;
		String userState = obj.getUserState();
		if (userState != null && userState.equals(UserInfoServiceConst.USER_STATE_WRITEOFF))
			return UserInfoServiceConst.LOGIN_ERROR_WRITEOFF;
		return str;
	}

	/**
	 * 查询用户信息
	 * @param queryUserName
	 * @param queryUserCode
	 * @return
	 */
	public Pager<ViUserInfo> findAllPagedUserInfos(String queryUserName, String queryUserCode) {
		Pager<ViUserInfo> pager = viUserInfoDao.findAllPaged(queryUserName, queryUserCode);
		return pager;
	}
	
	/**
	 * 查询用户信息
	 * @param queryUserName
	 * @param queryUserCode
	 * @param queryGroupId
	 * @return
	 */
	public Pager<ViUserInfo> findAllPagedUserInfos(String queryUserName, String queryUserCode,Long queryGroupId) {
		Pager<ViUserInfo> pager = viUserInfoDao.findAllPaged(queryUserName, queryUserCode,queryGroupId);
		return pager;
	}
	
	public ViUserInfo[] findUserInfos()
	{
		return viUserInfoDao.findAll();
	}

	/**
	 * 新增用户信息
	 * 
	 * @param groupId
	 * @param userName
	 * @param userPhone
	 * @param userCode
	 * @param userPassword
	 * @param registerTime
	 * @param opUserId
	 * @return
	 */
	public String insertUserInfo(Long groupId, String userName, String userSex, String userPhone, String userCode,
			long opUserId) {
		// 工号存在&&状态=="正常";
		UserInfo existUserInfo = userDao.findByUsercode(userCode);
		if (existUserInfo != null
				&& existUserInfo.getUserState().equals(UserInfoServiceConst.USER_STATE_NORMAL) == true)
			return "用户编号已经存在，而且正在使用";

		Date opTime = userDao.getNowTime();
		UserInfo user = new UserInfo();
		user.setUserId((long) -1);
		user.setGroupId(groupId);
		user.setUserName(userName);
		user.setUserSex(userSex);
		user.setUserPhone(userPhone);
		user.setUserCode(userCode);
		// 设置默认密码
		String encodePassword = Md5Utils.MD5Encode(AppVar.sysConfig.getDefaultPassword());
		user.setUserPassword(encodePassword);
		user.setRegisterTime(this.getNowTime());
		user.setUserState(UserInfoServiceConst.USER_STATE_NORMAL);
		user.setOpTime(opTime);
		user.setOpUserId(opUserId);
		long newId = userDao.insert(user);
		if (newId > 0)
			return StringUtils.STR_EMPTY;
		else
			return BaseServiceConst.MSG_INSERT_FAIL;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param userId
	 * @param groupId
	 * @param userName
	 * @param userPhone
	 * @param userCode
	 * @param registerTime
	 * @param opUserId
	 * @return
	 */
	public String updateUserInfo(Long userId, Long groupId, String userName, String userSex, String userPhone,
			String userCode, long opUserId) {

		// 工号存在&&状态=="正常";
		UserInfo otherUser = userDao.findByUsercode(userCode);
		if (otherUser != null && otherUser.getUserState().equals(UserInfoServiceConst.USER_STATE_NORMAL) == true
				&& otherUser.getUserId().equals(userId) == false)
			return "不能使用正在使用的用户编号";

		Date opTime = userDao.getNowTime();
		UserInfo user = userDao.find(userId);
		user.setUserId(userId);
		user.setGroupId(groupId);
		user.setUserName(userName);
		user.setUserSex(userSex);
		user.setUserPhone(userPhone);
		user.setUserCode(userCode);
		user.setOpTime(opTime);
		user.setOpUserId(opUserId);
		String msg = userDao.update(user);
		return msg;
	}

	/**
	 * 更新用户密码
	 * 
	 * @param userId
	 * @param userPassword
	 * @return
	 */
	public String changeUserPassword(long userId, String userPassword) {
		String encodePassword = Md5Utils.MD5Encode(userPassword);
		String msg = userDao.changePassword(userId, encodePassword);
		return msg;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param groupId
	 * @return
	 */
	public String deleteUserInfo(long userId) {
		if (userDao.isInuse(userId) == true)
			return "用户信息已经使用!";
		String msg = userDao.delete(userId);
		return msg;
	}

	/**
	 * 注销用户信息
	 * 
	 * @param groupId
	 * @return
	 */
	public String writeoffUserInfo(long userId) {
		UserInfo userInfo = userDao.find(userId);
		userInfo.setUserState(UserInfoServiceConst.USER_STATE_WRITEOFF);
		String msg = userDao.update(userInfo);
		return msg;
	}

	/**
	 * 查询所用权限
	 * @param userId
	 * @return
	 */
	public ViUserRole[] findAllUserRolesByUserId(long userId) {
		ViUserRole[] objs = viUserRoleDao.findAllByUserId(userId);
		return objs;
	}

	public String updateUserRoles(long userId, Long[] roleIds) {
		String msg = userRoleDao.updateUserRoles(userId, roleIds);
		return msg;
	}

	/**
	 * 查询用户可以访问模块信息(包括父子信息)
	 * 
	 * @return
	 */
	public ViModuleInfo[] findAllUserModules(long userId) {
		// 1-查询用户权限
		ViUserRolePriv[] userPrivs = viUserRolePrivDao.findAllByUserId(userId);
		Set<Long> userModuleIds = new HashSet<Long>();
		for (ViUserRolePriv priv : userPrivs) {
			userModuleIds.add(priv.getModuleId());
		}

		// 2-查询所有父模块
		ViModuleInfo[] allParentModules = viModuleInfoDao.findAllParentModules();

		// 3-逐个模块访问，判断用户是否有权限访问
		List<ViModuleInfo> retParentMoubles = new ArrayList<ViModuleInfo>();
		for (ViModuleInfo temp : allParentModules) {
			ModuleInfo[] children = moduleInfoDao.findAllChildModules(temp.getModuleId());
			boolean ifAdd = false;
			List<ModuleInfo> userModules = new ArrayList<ModuleInfo>();
			for (ModuleInfo module : children)
				if (userModuleIds.contains(module.getModuleId()) == true) {
					userModules.add(module);
					ifAdd = true;
				}

			if (ifAdd == true) {
				temp.setChildModules(userModules.toArray(new ModuleInfo[0]));
				retParentMoubles.add(temp);
			}
		}
		return retParentMoubles.toArray(new ViModuleInfo[0]);
	}

}
