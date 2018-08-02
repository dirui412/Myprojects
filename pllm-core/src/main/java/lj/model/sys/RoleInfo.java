package lj.model.sys;

import java.util.Date;

public class RoleInfo {
	private Long  roleId;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	private String  roleName;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	private String  roleMemo;
	public String getRoleMemo() {
		return roleMemo;
	}
	public void setRoleMemo(String roleMemo) {
		this.roleMemo = roleMemo;
	}
	
	public  RoleInfo() {
		super();
	}
	
	public  RoleInfo(
	  Long roleId,String roleName,String roleMemo){
		super();
		this.roleId= roleId;
		this.roleName= roleName;
		this.roleMemo= roleMemo;
	}
}