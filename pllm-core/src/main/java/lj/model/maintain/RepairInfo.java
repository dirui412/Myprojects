package lj.model.maintain;

import java.util.Date;

public class RepairInfo {
	private Long  repairId;
	public Long getRepairId() {
		return repairId;
	}
	public void setRepairId(Long repairId) {
		this.repairId = repairId;
	}
	private Long  userEquipmentId;
	public Long getUserEquipmentId() {
		return userEquipmentId;
	}
	public void setUserEquipmentId(Long userEquipmentId) {
		this.userEquipmentId = userEquipmentId;
	}
	private Date  repairTime;
	public Date getRepairTime() {
		return repairTime;
	}
	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}
	private String  repairType;
	public String getRepairType() {
		return repairType;
	}
	public void setRepairType(String repairType) {
		this.repairType = repairType;
	}
	private String  repairMemo;
	public String getRepairMemo() {
		return repairMemo;
	}
	public void setRepairMemo(String repairMemo) {
		this.repairMemo = repairMemo;
	}
	
	public  RepairInfo() {
		super();
	}
	
	public  RepairInfo(
	  Long repairId,Long userEquipmentId,Date repairTime,String repairType,String repairMemo){
		super();
		this.repairId= repairId;
		this.userEquipmentId= userEquipmentId;
		this.repairTime= repairTime;
		this.repairType= repairType;
		this.repairMemo= repairMemo;
	}
}