package lj.model.basic;

import java.util.Date;

public class ViProductLineEquipment {
	private Long  productLineEquipmentId;
	public Long getProductLineEquipmentId() {
		return productLineEquipmentId;
	}
	public void setProductLineEquipmentId(Long productLineEquipmentId) {
		this.productLineEquipmentId = productLineEquipmentId;
	}
	private Long  productLineId;
	public Long getProductLineId() {
		return productLineId;
	}
	public void setProductLineId(Long productLineId) {
		this.productLineId = productLineId;
	}
	private Long  equipmentId;
	public Long getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}
	private String  productName;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	private String  equipmentName;
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
	public  ViProductLineEquipment() {
		super();
	}
	
	public  ViProductLineEquipment(
	  Long productLineEquipmentId,Long productLineId,Long equipmentId,String productName,String equipmentName){
		super();
		this.productLineEquipmentId= productLineEquipmentId;
		this.productLineId= productLineId;
		this.equipmentId= equipmentId;
		this.productName= productName;
		this.equipmentName= equipmentName;
	}
}