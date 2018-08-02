package lj.model.basic;

public class EquipmentType {
	private Long  equipmentTypeId;
	public Long getEquipmentTypeId() {
		return equipmentTypeId;
	}
	public void setEquipmentTypeId(Long equipmentTypeId) {
		this.equipmentTypeId = equipmentTypeId;
	}
	private String  equipmentTypeName;
	public String getEquipmentTypeName() {
		return equipmentTypeName;
	}
	public void setEquipmentTypeName(String equipmentTypeName) {
		this.equipmentTypeName = equipmentTypeName;
	}
	private String  equipmentTypeDescribe;
	public String getEquipmentTypeDescribe() {
		return equipmentTypeDescribe;
	}
	public void setEquipmentTypeDescribe(String equipmentTypeDescribe) {
		this.equipmentTypeDescribe = equipmentTypeDescribe;
	}
	
	public  EquipmentType() {
		super();
	}
	
	public  EquipmentType(
	  Long equipmentTypeId,String equipmentTypeName,String equipmentTypeDescribe){
		super();
		this.equipmentTypeId= equipmentTypeId;
		this.equipmentTypeName= equipmentTypeName;
		this.equipmentTypeDescribe= equipmentTypeDescribe;
	}
}