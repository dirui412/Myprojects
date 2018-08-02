package lj.model.sys;

import java.util.Date;

public class RolePriv {
	private Long privId;

	public Long getPrivId() {
		return privId;
	}

	public void setPrivId(Long privId) {
		this.privId = privId;
	}

	private Long roleId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	private Long moduleId;

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	private String allowCreate;

	public String getAllowCreate() {
		return allowCreate;
	}

	public void setAllowCreate(String allowCreate) {
		this.allowCreate = allowCreate;
	}

	private String allowRetrieve;

	public String getAllowRetrieve() {
		return allowRetrieve;
	}

	public void setAllowRetrieve(String allowRetrieve) {
		this.allowRetrieve = allowRetrieve;
	}

	private String allowUpdate;

	public String getAllowUpdate() {
		return allowUpdate;
	}

	public void setAllowUpdate(String allowUpdate) {
		this.allowUpdate = allowUpdate;
	}

	private String allowDelete;

	public String getAllowDelete() {
		return allowDelete;
	}

	public void setAllowDelete(String allowDelete) {
		this.allowDelete = allowDelete;
	}

	private String allowImport;

	public String getAllowImport() {
		return allowImport;
	}

	public void setAllowImport(String allowImport) {
		this.allowImport = allowImport;
	}

	private String allowExport;

	public String getAllowExport() {
		return allowExport;
	}

	public void setAllowExport(String allowExport) {
		this.allowExport = allowExport;
	}

	public RolePriv() {
		super();
	}

	public RolePriv(Long privId, Long roleId, Long moduleId) {
		super();
		this.privId = privId;
		this.roleId = roleId;
		this.moduleId = moduleId;
	}

	public RolePriv(Long privId, Long roleId, Long moduleId, String allowCreate, String allowRetrieve,
			String allowUpdate, String allowDelete, String allowImport, String allowExport) {
		super();
		this.privId = privId;
		this.roleId = roleId;
		this.moduleId = moduleId;
		this.allowCreate = allowCreate;
		this.allowRetrieve = allowRetrieve;
		this.allowUpdate = allowUpdate;
		this.allowDelete = allowDelete;
		this.allowImport = allowImport;
		this.allowExport = allowExport;
	}
}