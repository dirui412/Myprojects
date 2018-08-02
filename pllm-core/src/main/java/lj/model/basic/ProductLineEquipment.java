package lj.model.basic;

import java.util.Date;

public class ProductLineEquipment {
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
	
	public  ProductLineEquipment() {
		super();
	}
	
	public  ProductLineEquipment(
	  Long productLineEquipmentId,Long productLineId,Long equipmentId){
		super();
		this.productLineEquipmentId= productLineEquipmentId;
		this.productLineId= productLineId;
		this.equipmentId= equipmentId;
	}
}