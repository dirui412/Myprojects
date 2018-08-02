package lj.model.install;

import java.util.Date;

public class CheckInfo {
	private Long  checkId;
	public Long getCheckId() {
		return checkId;
	}
	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}
	private Long  checkUserId;
	public Long getCheckUserId() {
		return checkUserId;
	}
	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}
	private Long  userProductLineId;
	public Long getUserProductLineId() {
		return userProductLineId;
	}
	public void setUserProductLineId(Long userProductLineId) {
		this.userProductLineId = userProductLineId;
	}
	private Date  checkTime;
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	private String  checkMemo;
	public String getCheckMemo() {
		return checkMemo;
	}
	public void setCheckMemo(String checkMemo) {
		this.checkMemo = checkMemo;
	}
	
	public  CheckInfo() {
		super();
	}
	
	public  CheckInfo(
	  Long checkId,Long checkUserId,Long userProductLineId,Date checkTime,String checkMemo){
		super();
		this.checkId= checkId;
		this.checkUserId= checkUserId;
		this.userProductLineId= userProductLineId;
		this.checkTime= checkTime;
		this.checkMemo= checkMemo;
	}
}