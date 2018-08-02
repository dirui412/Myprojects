package lj.controller.sys;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
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
import lj.model.sys.ModuleInfo;
import lj.model.sys.RoleInfo;
import lj.model.sys.RolePriv;
import lj.model.sys.SysParam;
import lj.model.sys.ViModuleInfo;
import lj.service.BaseServiceConst;
import lj.service.sys.SystemService;
import lj.util.DatatablesReturnObject;
import lj.util.LogUtils;
import lj.util.StringUtils;

/**
 * 基础服务
 * 
 * @author samlv
 *
 */
@Controller
@RequestMapping("sys")
public class SystemController {
	private SystemService systemService;

	@Resource
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@RequestMapping(value = { "/sysParams" })
	public String sysParams(Model model) {
		System.out.println("sysParams is requested");
		return "sys/sysParams";
	}

	@RequestMapping(value = { "/sysParams/getAll" }, method = RequestMethod.POST)
	public @ResponseBody DatatablesReturnObject<SysParam> getAllParamInfos(HttpServletRequest req) {
		// 1-获得查询数据
		//System.out.println("/sysParams/getAll");
		SysParam[] params=systemService.findSysParams();
		List<SysParam> list=Arrays.asList(params);
		// 2-生成返回格式
		DatatablesReturnObject<SysParam> obj = new DatatablesReturnObject<SysParam>(
				QueryContext.getPageDraw(), list.size(),list.size(),list);
		return obj;
	}
	
	@RequestMapping(value = { "/sysParams/post" }, method = RequestMethod.POST)
	public @ResponseBody String postSysParam(String paramId, String paramName, String paramValue) {
		System.out.println("/sysParams/post paramId:" + paramId + ",paramName:" + paramName);
		System.out.println("/sysParams/post paramValue:" + paramValue);
		String msg = StringUtils.STR_EMPTY;
		try {
			long id = Long.parseLong(paramId);
			// 更新
			if (id > 0) {
				msg = systemService.updateSysParam(id, paramName, paramValue);
			}
		} catch (Exception e) {
			msg = BaseServiceConst.MSG_POST_ERROR;
			e.printStackTrace();
			LogUtils.logError("/sysParams/post:", e);
		}
		System.out.println("/sysParams/post response:" + msg);
		return msg;
	}


	
	@RequestMapping(value = { "/moduleInfos" })
	public String moduleInfos(Model model) {
		ModuleInfo[] objs = systemService.findAllParentModules();
		model.addAttribute("parentModules", objs);
		return "sys/moduleInfos";
	}

	@RequestMapping(value = { "/moduleInfos/parentModuleInfos" })
	public @ResponseBody ModuleInfo[] parentModules() {
		ModuleInfo[] objs = systemService.findAllParentModules();
		Gson gson = new GsonBuilder().setDateFormat(StringUtils.DATETIME_FORMAT_JSON).create();
		String str = gson.toJson(objs, ModuleInfo[].class);
		System.out.println("/moduleInfos/parentModuleInfos reponse:" + str);
		return objs;
	}

	@RequestMapping(value = { "/moduleInfos/getAll" }, method = RequestMethod.POST)
	public @ResponseBody DatatablesReturnObject<ViModuleInfo> getAllModuleInfos(HttpServletRequest req,
			String queryParentModuleId, String queryEntityName) {
		// 1-解析请求
		//System.out.println("/moduleInfos/getAll queryParentModuleId:" + queryParentModuleId);
		//System.out.println("/moduleInfos/getAll queryEntityName:" + queryEntityName);

		Long parentModuleId = null;
		try {
			parentModuleId = Long.parseLong(queryParentModuleId);
		} catch (Exception e) {
			parentModuleId = null;
		}
		// 2-获得查询数据
		Pager<ViModuleInfo> pager = systemService.findAllPagedModuleInfos(parentModuleId, queryEntityName);
		// 3-生成返回格式
		DatatablesReturnObject<ViModuleInfo> obj = new DatatablesReturnObject<ViModuleInfo>(QueryContext.getPageDraw(), pager);
		return obj;
	}

	@RequestMapping(value = { "/moduleInfos/post" }, method = RequestMethod.POST)
	public @ResponseBody String postModuleInfo(HttpServletRequest req, String moduleId, String parentModuleId,
			String moduleName, String moduleCode, String moduleTitle, String moduleUrl, String entityName,
			String entityIdName) {
		System.out.println("/moduleInfos/post moduleId:" + moduleId + ",parentModuleId:" + parentModuleId);
		System.out.println("/moduleInfos/post moduleCode:" + moduleCode);
		String msg = StringUtils.STR_EMPTY;
		try {
			long id = Long.parseLong(moduleId);
			Long lParentModuleId = null;
			if (StringUtils.isNullOrEmpty(parentModuleId) == false)
				lParentModuleId = Long.parseLong(parentModuleId);
			long opUserId = BaseController.getLoginUser(req).getUserId();
			// 更新
			if (id > 0) {
				msg = systemService.updateModuleInfo(id, lParentModuleId, moduleName, moduleCode, moduleTitle,
						moduleUrl, entityName, entityIdName);
			}
			// 新增
			else {
				long newId = systemService.insertModuleInfo(lParentModuleId, moduleName, moduleCode, moduleTitle,
						moduleUrl, entityName, entityIdName);
				if (newId <= 0)
					msg = BaseServiceConst.MSG_INSERT_FAIL;
			}
		} catch (Exception e) {
			msg = BaseServiceConst.MSG_POST_ERROR;
			e.printStackTrace();
			LogUtils.logError("/moduleInfos/post:", e);
		}
		System.out.println("/moduleInfos/post response:" + msg);
		return msg;
	}

	@RequestMapping(value = { "/moduleInfos/delete" })
	public @ResponseBody String deleteModuleInfo(String moduleId) {
		System.out.println("/moduleInfos/delete areaId:" + moduleId);
		String msg = StringUtils.STR_EMPTY;
		try {
			long id = Long.parseLong(moduleId);
			msg = systemService.deleteModuleInfo(id);
		} catch (Exception e) {
			msg = BaseServiceConst.MSG_DELETE_FAIL;
			e.printStackTrace();
			LogUtils.logError("/moduleInfos/delete:", e);
		}
		System.out.println("/moduleInfos/delete response:" + msg);
		return msg;
	}

	@RequestMapping(value = { "/roleInfos" })
	public String roleInfos(Model model) {
		ViModuleInfo[] parentModules = systemService.findAllViParentModules();
		model.addAttribute("parentModules", parentModules);
		return "sys/roleInfos";
	}

	@RequestMapping(value = { "/roleInfos/getAll" }, method = RequestMethod.POST)
	public @ResponseBody DatatablesReturnObject<RoleInfo> getAllModuleInfos(HttpServletRequest req,
			String queryRoleName) {
		// 1-获得查询数据
		//System.out.println("/roleInfos/getAll queryRoleName:" + queryRoleName);
		Pager<RoleInfo> pager = systemService.findAllPagedRoleInfos(queryRoleName);
		// 3-生成返回格式
		DatatablesReturnObject<RoleInfo> obj = new DatatablesReturnObject<RoleInfo>(QueryContext.getPageDraw(), pager);
		return obj;
	}

	@RequestMapping(value = { "/roleInfos/post" })
	public @ResponseBody String postRoleInfos(String roleId, String roleName, String roleMemo) {
		System.out.println("/roleInfos/post roleId:" + roleId);
		String msg = StringUtils.STR_EMPTY;
		try {
			long id = Long.parseLong(roleId);
			msg = systemService.updateRoleInfo(id, roleName, roleMemo);
		} catch (Exception e) {
			msg = BaseServiceConst.MSG_UPDATE_FAIL;
			e.printStackTrace();
			LogUtils.logError("/roleInfos/post:", e);
		}
		System.out.println("/roleInfos/post response:" + msg);
		return msg;
	}

	@RequestMapping(value = { "/rolePrivs/getAllByRoleId" })
	public @ResponseBody RolePriv[] getAllByRoleId(String roleId) {
		Long id = null;
		try {
			id = Long.parseLong(roleId);
		} catch (Exception e) {
			id = null;
		}
		RolePriv[] objs = systemService.findAllByRoleId(id);
		Gson gson = new GsonBuilder().setDateFormat(StringUtils.DATETIME_FORMAT_JSON).create();
		String str = gson.toJson(objs, RolePriv[].class);
		System.out.println("/rolePrivs/getAllByRoleId reponse:" + str);
		return objs;
	}

	@RequestMapping(value = { "/rolePrivs/postRolePrivs" })
	public @ResponseBody String postRolePrivs(String roleId, String[] moduleIds) {
		String str = StringUtils.STR_EMPTY;
		for (String moduleId : moduleIds)
			str = str + moduleId + ",";
		System.out.println("/rolePrivs/postRolePrivs moduleIds:" + str);
		String msg = StringUtils.STR_EMPTY;
		try {
			Long lRoleId = Long.parseLong(roleId);
			Set<Long> moduleIdList = new HashSet<Long>();
			for (String temp : moduleIds) {
				long moduleId = Long.parseLong(temp);
				moduleIdList.add(moduleId);
			}
			msg = systemService.updateRolePrivs(lRoleId, moduleIdList.toArray(new Long[0]));
		} catch (Exception e) {
			msg = BaseServiceConst.MSG_UPDATE_FAIL;
			e.printStackTrace();
			LogUtils.logError("/rolePrivs/postRolePrivs:", e);
		}
		System.out.println("/rolePrivs/postRolePrivs response:" + msg);
		return msg;
	}

}
