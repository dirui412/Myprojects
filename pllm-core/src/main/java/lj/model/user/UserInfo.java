package lj.model.user;

import java.util.Date;

public class UserInfo {
	private Long  userId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	private Long groupId;
	
	
	
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	private String  userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private String userSex;
	
	
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	private String  userPhone;
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	private String  userCode;
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	private String  userPassword;
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	private Date  registerTime;
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	private String  userState;
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	private Date  lastLoginTime;
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	private Date  opTime;
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	private Long  opUserId;
	public Long getOpUserId() {
		return opUserId;
	}
	public void setOpUserId(Long opUserId) {
		this.opUserId = opUserId;
	}
	
	
	public  UserInfo() {
		super();
	}
	
	
	
	public  UserInfo(
			Long userId,Long groupId,String userName,String userSex,String userPhone,String userCode,
			String userPassword,Date registerTime,String userState,
			Date lastLoginTime,Date opTime,Long opUserId){
		super();
		this.userId= userId;
		this.groupId=groupId;
		this.userName= userName;
		this.userSex=userSex;
		this.userPhone= userPhone;
		this.userCode= userCode;
		this.userPassword= userPassword;
		this.registerTime= registerTime;
		this.userState= userState;
		this.lastLoginTime= lastLoginTime;
		this.opTime= opTime;
		this.opUserId= opUserId;
	}
}