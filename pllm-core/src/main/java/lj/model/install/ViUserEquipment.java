package lj.model.install;

import java.util.Date;

public class ViUserEquipment {
	private Long  userEquipmentId;
	public Long getUserEquipmentId() {
		return userEquipmentId;
	}
	public void setUserEquipmentId(Long userEquipmentId) {
		this.userEquipmentId = userEquipmentId;
	}
	private Long  userProductLineId;
	public Long getUserProductLineId() {
		return userProductLineId;
	}
	public void setUserProductLineId(Long userProductLineId) {
		this.userProductLineId = userProductLineId;
	}
	private Long  equipmentId;
	public Long getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}
	private Date  installTime;
	public Date getInstallTime() {
		return installTime;
	}
	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
	}
	private String  equipmentState;
	public String getEquipmentState() {
		return equipmentState;
	}
	public void setEquipmentState(String equipmentState) {
		this.equipmentState = equipmentState;
	}
	private String  installParams;
	public String getInstallParams() {
		return installParams;
	}
	public void setInstallParams(String installParams) {
		this.installParams = installParams;
	}
	private String  installMemo;
	public String getInstallMemo() {
		return installMemo;
	}
	public void setInstallMemo(String installMemo) {
		this.installMemo = installMemo;
	}
	private String  equipmentName;
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	private String  equipmentType;
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	private Long  productLineId;
	public Long getProductLineId() {
		return productLineId;
	}
	public void setProductLineId(Long productLineId) {
		this.productLineId = productLineId;
	}
	private Long  groupId;
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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
	private String  productName;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	private String  productType;
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	public  ViUserEquipment() {
		super();
	}
	
	public  ViUserEquipment(
	  Long userEquipmentId,Long userProductLineId,Long equipmentId,Date installTime,String equipmentState,String installParams,String installMemo,String equipmentName,String equipmentType,Long productLineId,Long groupId,String userProductLineName,String productLineState,String groupName,String productName,String productType){
		super();
		this.userEquipmentId= userEquipmentId;
		this.userProductLineId= userProductLineId;
		this.equipmentId= equipmentId;
		this.installTime= installTime;
		this.equipmentState= equipmentState;
		this.installParams= installParams;
		this.installMemo= installMemo;
		this.equipmentName= equipmentName;
		this.equipmentType= equipmentType;
		this.productLineId= productLineId;
		this.groupId= groupId;
		this.userProductLineName= userProductLineName;
		this.productLineState= productLineState;
		this.groupName= groupName;
		this.productName= productName;
		this.productType= productType;
	}
}