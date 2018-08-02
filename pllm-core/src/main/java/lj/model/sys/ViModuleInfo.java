package lj.model.sys;

/**
 * 包括父子信息
 * @author samlv
 *
 */
public class ViModuleInfo extends ModuleInfo {

	private String parentModuleName;

	public String getParentModuleName() {
		return parentModuleName;
	}

	public void setParentModuleName(String parentModuleName) {
		this.parentModuleName = parentModuleName;
	}

	private String parentModuleTitle;

	public String getParentModuleTitle() {
		return parentModuleTitle;
	}

	public void setParentModuleTitle(String parentModuleTitle) {
		this.parentModuleTitle = parentModuleTitle;
	}

	private ModuleInfo[] childModules;

	public ModuleInfo[] getChildModules() {
		return childModules;
	}

	public void setChildModules(ModuleInfo[] childModules) {
		this.childModules = childModules;
	}

	public ViModuleInfo() {
		super();
	}

}