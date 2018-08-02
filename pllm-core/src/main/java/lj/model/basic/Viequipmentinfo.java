package lj.model.basic;

import java.util.Date;

public class Viequipmentinfo {
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
	private Long  equipmentTypeId;
	public Long getEquipmentTypeId() {
		return equipmentTypeId;
	}
	public void setEquipmentTypeId(Long equipmentTypeId) {
		this.equipmentTypeId = equipmentTypeId;
	}
	private String  equipmentVersion;
	public String getEquipmentVersion() {
		return equipmentVersion;
	}
	public void setEquipmentVersion(String equipmentVersion) {
		this.equipmentVersion = equipmentVersion;
	}
	private String  equipmentConfig;
	public String getEquipmentConfig() {
		return equipmentConfig;
	}
	public void setEquipmentConfig(String equipmentConfig) {
		this.equipmentConfig = equipmentConfig;
	}
	private String  equipmentMemo;
	public String getEquipmentMemo() {
		return equipmentMemo;
	}
	public void setEquipmentMemo(String equipmentMemo) {
		this.equipmentMemo = equipmentMemo;
	}
	private String  equipmentTypeName;
	public String getEquipmentTypeName() {
		return equipmentTypeName;
	}
	public void setEquipmentTypeName(String equipmentTypeName) {
		this.equipmentTypeName = equipmentTypeName;
	}
	private String  equipmentPicture;
	public String getEquipmentPicture() {
		return equipmentPicture;
	}
	public void setEquipmentPicture(String equipmentPicture) {
		this.equipmentPicture = equipmentPicture;
	}
	
	public  Viequipmentinfo() {
		super();
	}
	
	public  Viequipmentinfo(
	  Long equipmentId,String equipmentName,Long equipmentTypeId,String equipmentVersion,String equipmentConfig,String equipmentMemo,String equipmentTypeName,String equipmentPicture){
		super();
		this.equipmentId= equipmentId;
		this.equipmentName= equipmentName;
		this.equipmentTypeId= equipmentTypeId;
		this.equipmentVersion= equipmentVersion;
		this.equipmentConfig= equipmentConfig;
		this.equipmentMemo= equipmentMemo;
		this.equipmentTypeName= equipmentTypeName;
		this.equipmentPicture= equipmentPicture;
	}
}