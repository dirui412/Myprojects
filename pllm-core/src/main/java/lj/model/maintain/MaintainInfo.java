package lj.model.maintain;

import java.util.Date;

public class MaintainInfo {
	private Long  maintainId;
	public Long getMaintainId() {
		return maintainId;
	}
	public void setMaintainId(Long maintainId) {
		this.maintainId = maintainId;
	}
	private Long  userEquipmentId;
	public Long getUserEquipmentId() {
		return userEquipmentId;
	}
	public void setUserEquipmentId(Long userEquipmentId) {
		this.userEquipmentId = userEquipmentId;
	}
	private Date  maintainTime;
	public Date getMaintainTime() {
		return maintainTime;
	}
	public void setMaintainTime(Date maintainTime) {
		this.maintainTime = maintainTime;
	}
	private String  maintainType;
	public String getMaintainType() {
		return maintainType;
	}
	public void setMaintainType(String maintainType) {
		this.maintainType = maintainType;
	}
	private String  maintainMemo;
	public String getMaintainMemo() {
		return maintainMemo;
	}
	public void setMaintainMemo(String maintainMemo) {
		this.maintainMemo = maintainMemo;
	}
	
	public  MaintainInfo() {
		super();
	}
	
	public  MaintainInfo(
	  Long maintainId,Long userEquipmentId,Date maintainTime,String maintainType,String maintainMemo){
		super();
		this.maintainId= maintainId;
		this.userEquipmentId= userEquipmentId;
		this.maintainTime= maintainTime;
		this.maintainType= maintainType;
		this.maintainMemo= maintainMemo;
	}
}