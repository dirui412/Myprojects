package lj.model.maintain;

import java.util.Date;

public class ViMaintainInfo {
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
	private String equipmentState;
	
	
	public String getEquipmentState() {
		return equipmentState;
	}
	public void setEquipmentState(String equipmentState) {
		this.equipmentState = equipmentState;
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
	
	public  ViMaintainInfo() {
		super();
	}
	
	public  ViMaintainInfo(
	  Long maintainId,Long userEquipmentId,String equipmentState,Date maintainTime,String maintainType,String maintainMemo,String userProductLineName,String productLineState,String groupName,Long equipmentId,String equipmentName,Long userProductLineId){
		super();
		this.maintainId= maintainId;
		this.equipmentState=equipmentState;
		this.userEquipmentId= userEquipmentId;
		this.maintainTime= maintainTime;
		this.maintainType= maintainType;
		this.maintainMemo= maintainMemo;
		this.userProductLineName= userProductLineName;
		this.productLineState= productLineState;
		this.groupName= groupName;
		this.equipmentId= equipmentId;
		this.equipmentName= equipmentName;
		this.userProductLineId= userProductLineId;
	}
}