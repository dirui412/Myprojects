package lj.model.user;

public class ViUserInfo extends UserInfo {
	
	
	private String  opUserName;
	public String getOpUserName() {
		return opUserName;
	}
	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}
	
	private String groupName;
	
	
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	private String groupType;
	
	
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public  ViUserInfo() {
		super();
	}
	
	
}