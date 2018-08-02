package lj.model.basic;

import java.util.Date;

public class ViEquipmentParam {
	private Long  equipmentParamId;
	public Long getEquipmentParamId() {
		return equipmentParamId;
	}
	public void setEquipmentParamId(Long equipmentParamId) {
		this.equipmentParamId = equipmentParamId;
	}
	private Long  equipmentId;
	public Long getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}
	private String  paramName;
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	private String  paramType;
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	private Double  paramUp;
	public Double getParamUp() {
		return paramUp;
	}
	public void setParamUp(Double paramUp) {
		this.paramUp = paramUp;
	}
	private Double  paramDown;
	public Double getParamDown() {
		return paramDown;
	}
	public void setParamDown(Double paramDown) {
		this.paramDown = paramDown;
	}
	private String  equipmentName;
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	private String  equipmentVersion;
	public String geteEquipmentVersion() {
		return equipmentVersion;
	}
	public void setEquipmentVersion(String equipmentVersion) {
		this.equipmentVersion = equipmentVersion;
	}
	
	public  ViEquipmentParam() {
		super();
	}
	
	public  ViEquipmentParam(
	  Long equipmentParamId,Long equipmentId,String paramName,String paramType,Double paramUp,Double paramDown,String equipmentName,String equipmentVersion){
		super();
		this.equipmentParamId= equipmentParamId;
		this.equipmentId= equipmentId;
		this.paramName= paramName;
		this.paramType= paramType;
		this.paramUp= paramUp;
		this.paramDown= paramDown;
		this.equipmentName= equipmentName;
		this.equipmentVersion= equipmentVersion;
	}
}