package lj.model.user;

public class ViUserRole {
	private Long  userRoleId;
	public Long getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}
	private Long  roleId;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	private Long  userId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	private Long  moduleId;
	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	
	public  ViUserRole() {
		super();
	}
	
	public  ViUserRole(
	  Long userRoleId,Long roleId,Long userId,Long moduleId){
		super();
		this.userRoleId= userRoleId;
		this.roleId= roleId;
		this.userId= userId;
		this.moduleId= moduleId;
	}
}