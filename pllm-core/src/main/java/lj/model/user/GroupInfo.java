package lj.model.user;

import java.util.Date;

public class GroupInfo {
	private Long  groupId;
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	private String  groupName;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	private String  groupType;
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	private String  groupMemo;
	public String getGroupMemo() {
		return groupMemo;
	}
	public void setGroupMemo(String groupMemo) {
		this.groupMemo = groupMemo;
	}
	private Long  opUserId;
	public Long getOpUserId() {
		return opUserId;
	}
	public void setOpUserId(Long opUserId) {
		this.opUserId = opUserId;
	}
	private Date  opTime;
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	
	public  GroupInfo() {
		super();
	}
	
	public  GroupInfo(
	  Long groupId,String groupName,String groupType,String groupMemo,Long opUserId,Date opTime){
		super();
		this.groupId= groupId;
		this.groupName= groupName;
		this.groupType= groupType;
		this.groupMemo= groupMemo;
		this.opUserId= opUserId;
		this.opTime= opTime;
	}
}