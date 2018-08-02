package lj.model.maintain;

import java.util.Date;

public class ViRepairInfo {
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
	private String equipmentState;
	
	
	public String getEquipmentState() {
		return equipmentState;
	}
	public void setEquipmentState(String equipmentState) {
		this.equipmentState = equipmentState;
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
	private String  userProductLineName;
	public String getUserProductLineName() {
		return userProductLineName;
	}
	public void setUserProductLineName(String userProductLineName) {
		this.userProductLineName = userProductLineName;
	}
	private String  productLineState;
	public String getProductLineState() {
		return productLineState;
	}
	public void setProductLineState(String productLineState) {
		this.productLineState = productLineState;
	}
	private String  groupName;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	private Long  equipmentId;
	public Long getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}
	private String  equipmentName;
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	private Long  userProductLineId;
	public Long getUserProductLineId() {
		return userProductLineId;
	}
	public void setUserProductLineId(Long userProductLineId) {
		this.userProductLineId = userProductLineId;
	}
	
	public  ViRepairInfo() {
		super();
	}
	
	public  ViRepairInfo(
	  Long repairId,Long userEquipmentId,String equipmentState,Date repairTime,String repairType,String repairMemo,String userProductLineName,String productLineState,String groupName,Long equipmentId,String equipmentName,Long userProductLineId){
		super();
		this.repairId= repairId;
		
		this.userEquipmentId= userEquipmentId;
		this.equipmentState=equipmentState;
		this.repairTime= repairTime;
		this.repairType= repairType;
		this.repairMemo= repairMemo;
		this.userProductLineName= userProductLineName;
		this.productLineState= productLineState;
		this.groupName= groupName;
		this.equipmentId= equipmentId;
		this.equipmentName= equipmentName;
		this.userProductLineId= userProductLineId;
	}
}