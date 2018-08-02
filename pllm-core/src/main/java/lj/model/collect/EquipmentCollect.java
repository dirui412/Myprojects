package lj.model.collect;

import java.util.Date;

public class EquipmentCollect {
	private Long  equipmentCollectId;
	public Long getEquipmentCollectId() {
		return equipmentCollectId;
	}
	public void setEquipmentCollectId(Long equipmentCollectId) {
		this.equipmentCollectId = equipmentCollectId;
	}
	private Long  equipmentParamId;
	public Long getEquipmentParamId() {
		return equipmentParamId;
	}
	public void setEquipmentParamId(Long equipmentParamId) {
		this.equipmentParamId = equipmentParamId;
	}
	private Long  userEquipmentId;
	public Long getUserEquipmentId() {
		return userEquipmentId;
	}
	public void setUserEquipmentId(Long userEquipmentId) {
		this.userEquipmentId = userEquipmentId;
	}
	private Date  collectTime;
	public Date getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}
	private Integer  paramState;
	public Integer getParamState() {
		return paramState;
	}
	public void setParamState(Integer paramState) {
		this.paramState = paramState;
	}
	private Double  paramValue;
	public Double getParamValue() {
		return paramValue;
	}
	public void setParamValue(Double paramValue) {
		this.paramValue = paramValue;
	}
	
	public  EquipmentCollect() {
		super();
	}
	
	public  EquipmentCollect(
	  Long equipmentCollectId,Long userEquipmentId,Long equipmentParamId,Date collectTime,Integer paramState,Double paramValue){
		super();
		this.equipmentCollectId= equipmentCollectId;
		this.userEquipmentId= userEquipmentId;
		this.equipmentParamId= equipmentParamId;
		this.collectTime= collectTime;
		this.paramState= paramState;
		this.paramValue= paramValue;
	}
}