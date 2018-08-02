package lj.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.sys.IModuleInfoDao;
import lj.dao.sys.IRoleInfoDao;
import lj.dao.sys.IRolePrivDao;
import lj.dao.sys.ISysConfigDao;
import lj.dao.sys.IViModuleInfoDao;
import lj.model.base.Pager;
import lj.model.sys.ModuleInfo;
import lj.model.sys.RoleInfo;
import lj.model.sys.RolePriv;
import lj.model.sys.SysParam;
import lj.model.sys.ViModuleInfo;
import lj.util.StringUtils;

/**
 * 系统服务
 */
@Service("systemService")
public class SystemService {
	public final static String MSG_AREA_INUSE = "区域信息已经使用";
	public final static String MSG_AREACODE_EXISTS = "区域编码已经存在";
	public final static long ROLE_ID_DEV=1;    //开发人员
	public final static long ROLE_ID_ADMIN=2;  //管理员
	
	@Autowired
	private ISysConfigDao sysConfigDao = null;
	@Autowired
	private IModuleInfoDao moduleInfoDao = null;
	@Autowired
	private IViModuleInfoDao viModuleInfoDao = null;
	@Autowired
	private IRoleInfoDao roleInfoDao = null;
	@Autowired
	private IRolePrivDao rolePrivDao = null;

    /**
     * 查询所有系统参数
     * @return
     */
	public SysParam[] findSysParams() {
		SysParam[] params=sysConfigDao.findAll();
		return params;
	}
	
	/**
	 * 更新系统参数
	 * @param paramId
	 * @param paramName
	 * @param paramValue
	 * @return
	 */
	public String updateSysParam(long paramId,String paramName,String paramValue){
		SysParam param=new SysParam(paramId,paramName,paramValue,StringUtils.STR_EMPTY);
		String msg=sysConfigDao.update(param);
		return msg;
	}

	/**
	 * 分页查询模块信息
	 * 
	 * @param pageIndex
	 * @param sortField
	 * @param sortType
	 * @param queryModuleName
	 * @param queryEntityName
	 * @return
	 */
	public Pager<ViModuleInfo> findAllPagedModuleInfos(Long queryParentModuleId, String queryEntityName) {
		Pager<ViModuleInfo> pager = viModuleInfoDao.findAllPaged(queryParentModuleId,
				queryEntityName);
		return pager;
	}

	public ModuleInfo[] findAllModules() {
		ModuleInfo[] modules = moduleInfoDao.findAll();
		return modules;
	}

	public ModuleInfo[] findAllParentModules() {
		ModuleInfo[] modules = moduleInfoDao.findAllParentModules();
		return modules;
	}

	/**
	 * 查询所有父模块(包含其子模块)
	 * 
	 * @return
	 */
	public ViModuleInfo[] findAllViParentModules() {
		ViModuleInfo[] modules = viModuleInfoDao.findAllParentModules();
		for (ViModuleInfo temp : modules) {
			ModuleInfo[] children = moduleInfoDao.findAllChildModules(temp.getModuleId());
			temp.setChildModules(children);
		}
		return modules;
	}

	public long insertModuleInfo(Long parentModuleId, String moduleName, String moduleCode, String moduleTitle,
			String moduleUrl, String entityName, String entityIdName) {
		ModuleInfo obj = new ModuleInfo(null, parentModuleId, moduleName, moduleCode, moduleTitle, null, entityName,
				entityIdName, moduleUrl);
		long newId = moduleInfoDao.insert(obj);
		return newId;
	}

	public String updateModuleInfo(long moduleId, Long parentModuleId, String moduleName, String moduleCode,
			String moduleTitle, String moduleUrl, String entityName, String entityIdName) {
		ModuleInfo obj = new ModuleInfo(moduleId, parentModuleId, moduleName, moduleCode, moduleTitle, null, entityName,
				entityIdName, moduleUrl);
		String msg = moduleInfoDao.update(obj);
		return msg;
	}

	public String deleteModuleInfo(long id) {
		if (moduleInfoDao.isInuse(id) == true)
			return MSG_AREA_INUSE;
		String msg = moduleInfoDao.delete(id);
		return msg;
	}

	public Pager<RoleInfo> findAllPagedRoleInfos(String queryRoleName) {
		Pager<RoleInfo> pager = roleInfoDao.findAllPaged( queryRoleName);
		return pager;
	}

	public RoleInfo[] findAllRoles() {
		RoleInfo[] objs = roleInfoDao.findAll();
		return objs;
	}

	public String updateRoleInfo(Long roleId, String roleName, String roleMemo) {
		RoleInfo obj = new RoleInfo(roleId, roleName, roleMemo);
		String msg = roleInfoDao.update(obj);
		return msg;
	}

	/**
	 * 根据角色标识查询权限标识
	 * 
	 * @param roleId
	 * @return
	 */
	public RolePriv[] findAllByRoleId(long roleId) {
		RolePriv[] objs = rolePrivDao.findAllByRoleId(roleId);
		return objs;
	}
	
	

	/**
	 * 更新角色权限
	 * 
	 * @param roleId
	 * @param moduleIds
	 * @return
	 */
	public String updateRolePrivs(long roleId, Long[] moduleIds) {
		String msg = rolePrivDao.updateRolePrivs(roleId, moduleIds);
		return msg;
	}
}
