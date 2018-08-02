package lj.controller.user;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lj.controller.BaseController;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.sys.RoleInfo;
import lj.model.user.GroupInfo;
import lj.model.user.ViUserInfo;
import lj.model.user.ViUserRole;
import lj.service.BaseServiceConst;
import lj.service.sys.SystemService;
import lj.service.user.GroupInfoService;
import lj.service.user.UserInfoService;
import lj.util.DatatablesReturnObject;
import lj.util.LogUtils;
import lj.util.StringUtils;

@Controller
@RequestMapping("user")
public class UserInfoController  extends BaseController {

	@Autowired
	private UserInfoService userInfoService = null;

	@Autowired
	private GroupInfoService groupInfoService = null;

	@Autowired
	private SystemService systemService = null;

	@RequestMapping(value = { "/userInfos" })
	public String userInfos(Model model, HttpServletRequest req) {
		RoleInfo[] roleInfos = systemService.findAllRoles();
		model.addAttribute("roleInfos", roleInfos);
		Long loginGroupId = BaseController.getLoginUser(req).getGroupId();
		if (loginGroupId.equals(GroupInfoService.GROUP_ID_ADMIN) == true) {
			GroupInfo[] groupInfos = groupInfoService.findAll();
			model.addAttribute("groupInfos", groupInfos);
		}
		else {
			GroupInfo groupInfo=groupInfoService.find(loginGroupId);
			model.addAttribute("groupInfos", new GroupInfo[] {groupInfo});
		}
		return "user/userInfos";
	}

	@RequestMapping(value = { "/userInfos/getAll" }, method = RequestMethod.POST)
	public @ResponseBody DatatablesReturnObject<ViUserInfo> getAllUserInfos(HttpServletRequest req,
			String queryUserName, String queryUserCode) {
		// 1-解析请求
		// System.out.println("/userInfos/getAll queryUserName:" +
		// queryUserName);
		// System.out.println("/userInfos/getAll queryUserCode:" +
		// queryUserCode);
		Long loginUserId = BaseController.getLoginUser(req).getUserId();
		Long loginGroupId = BaseController.getLoginUser(req).getGroupId();
		ViUserRole[] userRoles = userInfoService.findAllUserRolesByUserId(loginUserId);

		// 2-获得查询数据
		Pager<ViUserInfo> pager = new Pager<ViUserInfo>();
		boolean isAllPrividge = false;
		for (ViUserRole userRole : userRoles)
			if (userRole.getRoleId() == 1 || userRole.getRoleId() == 2) {
				isAllPrividge = true;
				break;
			}

		if (isAllPrividge == true)
			pager = userInfoService.findAllPagedUserInfos(queryUserName, queryUserCode);
		else
			pager = userInfoService.findAllPagedUserInfos(queryUserName, queryUserCode, loginGroupId);

		// 3-生成返回格式
		DatatablesReturnObject<ViUserInfo> obj = new DatatablesReturnObject<ViUserInfo>(QueryContext.getPageDraw(),
				pager);
		return obj;
	}

	@RequestMapping(value = { "/userInfos/post" }, method = RequestMethod.POST)
	public @ResponseBody String postUserInfo(HttpServletRequest req, String userId, String userName, String userSex,
			String userPhone, String userCode, String groupId) {
		 System.out.println("/userInfos/post userId:" + userId + ",userName:" +
		 userName);
		// System.out.println("/userInfos/post userPhone:" + userPhone);
		String msg = StringUtils.STR_EMPTY;
		try {
			Long id = Long.parseLong(userId);
			long opUserId = BaseController.getLoginUser(req).getUserId();
			// 更新
			if (id > 0){
				msg = userInfoService.updateUserInfo(id, Long.parseLong(groupId), userName, userSex, userPhone,
						userCode, opUserId);
			}
			// 新增
			else {
				msg = userInfoService.insertUserInfo(Long.parseLong(groupId), userName, userSex, userPhone, userCode,
						opUserId);
			}
		} catch (Exception e) {
			msg = BaseServiceConst.MSG_POST_ERROR;
			e.printStackTrace();
			LogUtils.logError("/userInfos/post:", e);
		}
		System.out.println("/userInfos/post response:" + msg);
		return msg;
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/userInfos/delete" })
	public @ResponseBody String deleteUserInfo(String userId) {
		System.out.println("/userInfos/delete userId:" + userId);
		String msg = StringUtils.STR_EMPTY;
		try {
			long id = Long.parseLong(userId);
			msg = userInfoService.deleteUserInfo(id);
		} catch (Exception e) {
			msg = BaseServiceConst.MSG_DELETE_FAIL;
			e.printStackTrace();
			LogUtils.logError("/userInfos/delete:", e);
		}
		System.out.println("/userInfos/delete response:" + msg);
		return msg;
	}

	/**
	 * 注销
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/userInfos/writeoff" })
	public @ResponseBody String writeoffUserInfo(String userId) {
		System.out.println("/userInfos/writeoff userId:" + userId);
		String msg = StringUtils.STR_EMPTY;
		try {
			long id = Long.parseLong(userId);
			msg = userInfoService.writeoffUserInfo(id);
		} catch (Exception e) {
			msg = "注销失败!";
			e.printStackTrace();
			LogUtils.logError("/userInfos/writeoff:", e);
		}
		System.out.println("/userInfos/writeoff response:" + msg);
		return msg;
	}

	/**
	 * 更改密码
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/userInfos/changeUserPassword" })
	public @ResponseBody String changeUserPassword(HttpServletRequest req, String userPassword) {
		System.out.println("/userInfos/changeUserPassword userPassword:" + userPassword);
		String msg = StringUtils.STR_EMPTY;
		try {
			long loginUserId = BaseController.getLoginUser(req).getUserId();
			msg = userInfoService.changeUserPassword(loginUserId, userPassword);
		} catch (Exception e) {
			msg = BaseServiceConst.MSG_UPDATE_FAIL;
			e.printStackTrace();
			LogUtils.logError("/userInfos/changeUserPassword:", e);
		}
		System.out.println("/userInfos/changeUserPassword response:" + msg);
		return msg;
	}

	@RequestMapping(value = { "/userRoles/getAllByUserId" })
	public @ResponseBody ViUserRole[] getAllByUserId(String userId) {
		Long id = null;
		try {
			id = Long.parseLong(userId);
		} catch (Exception e) {
			id = null;
		}
		ViUserRole[] objs = userInfoService.findAllUserRolesByUserId(id);
		Gson gson = new GsonBuilder().setDateFormat(StringUtils.DATETIME_FORMAT_JSON).create();
		String str = gson.toJson(objs, ViUserRole[].class);
		System.out.println("/userRoles/getAllByUserId reponse:" + str);
		return objs;
	}

	@RequestMapping(value = { "/userRoles/postUserRoles" })
	public @ResponseBody String postUserRoles(String userId, String[] roleIds) {
		// System.out.println("/userRoles/postUserRoles userId:" + userId);
		String msg = StringUtils.STR_EMPTY;
		try {
			Long id = Long.parseLong(userId);
			if(roleIds!=null){
				Set<Long> roleIdList = new HashSet<Long>();
				for (String temp : roleIds) {
					long roleId = Long.parseLong(temp);
					roleIdList.add(roleId);
				}
				msg = userInfoService.updateUserRoles(id, roleIdList.toArray(new Long[0]));
			}
			else
				msg = userInfoService.updateUserRoles(id, null);
			
		} catch (Exception e) {
			msg = BaseServiceConst.MSG_UPDATE_FAIL;
			e.printStackTrace();
			LogUtils.logError("/userRoles/postUserRoles:", e);
		}
		// System.out.println("/userRoles/postUserRoles response:" + msg);
		return msg;
	}

}
