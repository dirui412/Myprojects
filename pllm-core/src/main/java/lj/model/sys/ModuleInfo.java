package lj.model.sys;

import java.util.Date;

public class ModuleInfo {
	private Long  moduleId;
	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	private Long  parentModuleId;
	public Long getParentModuleId() {
		return parentModuleId;
	}
	public void setParentModuleId(Long parentModuleId) {
		this.parentModuleId = parentModuleId;
	}
	private String  moduleName;
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	private String  moduleCode;
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	private String  moduleTitle;
	public String getModuleTitle() {
		return moduleTitle;
	}
	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}
	private String  moduleKind;
	public String getModuleKind() {
		return moduleKind;
	}
	public void setModuleKind(String moduleKind) {
		this.moduleKind = moduleKind;
	}
	private String  entityName;
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	private String  entityIdName;
	public String getEntityIdName() {
		return entityIdName;
	}
	public void setEntityIdName(String entityIdName) {
		this.entityIdName = entityIdName;
	}
	private String  moduleUrl;
	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	
	public  ModuleInfo() {
		super();
	}
	
	public  ModuleInfo(
	  Long moduleId,Long parentModuleId,String moduleName,String moduleCode,String moduleTitle,String moduleKind,String entityName,String entityIdName,String moduleUrl){
		super();
		this.moduleId= moduleId;
		this.parentModuleId= parentModuleId;
		this.moduleName= moduleName;
		this.moduleCode= moduleCode;
		this.moduleTitle= moduleTitle;
		this.moduleKind= moduleKind;
		this.entityName= entityName;
		this.entityIdName= entityIdName;
		this.moduleUrl= moduleUrl;
	}
}