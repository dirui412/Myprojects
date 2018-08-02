package lj.model.user;

import java.util.Date;

public class UserRole {
	private Long  userRoleId;
	public Long getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}
	private Long  userId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	private Long  roleId;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public  UserRole() {
		super();
	}
	
	public  UserRole(
	  Long userRoleId,Long userId,Long roleId){
		super();
		this.userRoleId= userRoleId;
		this.userId= userId;
		this.roleId= roleId;
	}
}