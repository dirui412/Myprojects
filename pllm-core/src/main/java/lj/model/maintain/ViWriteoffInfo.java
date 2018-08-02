package lj.model.maintain;

import java.util.Date;

public class ViWriteoffInfo {
	private Long  writeoffId;
	public Long getWriteoffId() {
		return writeoffId;
	}
	public void setWriteoffId(Long writeoffId) {
		this.writeoffId = writeoffId;
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
	private Date  writeoffTime;
	public Date getWriteoffTime() {
		return writeoffTime;
	}
	public void setWriteoffTime(Date writeoffTime) {
		this.writeoffTime = writeoffTime;
	}
	private String  writeoffType;
	public String getWriteoffType() {
		return writeoffType;
	}
	public void setWriteoffType(String writeoffType) {
		this.writeoffType = writeoffType;
	}
	private String  writeoffMemo;
	public String getWriteoffMemo() {
		return writeoffMemo;
	}
	public void setWriteoffMemo(String writeoffMemo) {
		this.writeoffMemo = writeoffMemo;
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
	
	public  ViWriteoffInfo() {
		super();
	}
	
	public  ViWriteoffInfo(
	  Long writeoffId,Long userEquipmentId,String equipmentState,Date writeoffTime,String writeoffType,String writeoffMemo,String userProductLineName,String productLineState,String groupName,Long equipmentId,String equipmentName,Long userProductLineId){
		super();
		this.writeoffId= writeoffId;
		this.userEquipmentId= userEquipmentId;
		this.equipmentState=equipmentState;
		this.writeoffTime= writeoffTime;
		this.writeoffType= writeoffType;
		this.writeoffMemo= writeoffMemo;
		this.userProductLineName= userProductLineName;
		this.productLineState= productLineState;
		this.groupName= groupName;
		this.equipmentId= equipmentId;
		this.equipmentName= equipmentName;
		this.userProductLineId= userProductLineId;
	}
}