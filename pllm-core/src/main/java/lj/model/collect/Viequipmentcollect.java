package lj.model.collect;

import java.util.Date;

public class Viequipmentcollect {
	private Long  equipmentCollectId;
	public Long getEquipmentCollectId() {
		return equipmentCollectId;
	}
	public void setEquipmentCollectId(Long equipmentCollectId) {
		this.equipmentCollectId = equipmentCollectId;
	}
	private Long  userEquipmentId;
	public Long getUserEquipmentId() {
		return userEquipmentId;
	}
	public void setUserEquipmentId(Long userEquipmentId) {
		this.userEquipmentId = userEquipmentId;
	}
	private Long  equipmentParamId;
	public Long getEquipmentParamId() {
		return equipmentParamId;
	}
	public void setEquipmentParamId(Long equipmentParamId) {
		this.equipmentParamId = equipmentParamId;
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
	private Long  equipmentId;
	public Long getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}
	private Long  groupId;
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	private String  equipmentName;
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	private String  groupName;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	private String  userProductLineName;
	public String getUserProductLineName() {
		return userProductLineName;
	}
	public void setUserProductLineName(String userProductLineName) {
		this.userProductLineName = userProductLineName;
	}
	private Long  userProductLineId;
	public Long getUserProductLineId() {
		return userProductLineId;
	}
	public void setUserProductLineId(Long userProductLineId) {
		this.userProductLineId = userProductLineId;
	}
	
	public  Viequipmentcollect() {
		super();
	}
	
	public  Viequipmentcollect(
	  Long equipmentCollectId,Long userEquipmentId,Long equipmentParamId,Date collectTime,Integer paramState,Double paramValue,String paramName,String paramType,Double paramUp,Double paramDown,Long equipmentId,Long groupId,String equipmentName,String groupName,String userProductLineName,Long userProductLineId){
		super();
		this.equipmentCollectId= equipmentCollectId;
		this.userEquipmentId= userEquipmentId;
		this.equipmentParamId= equipmentParamId;
		this.collectTime= collectTime;
		this.paramState= paramState;
		this.paramValue= paramValue;
		this.paramName= paramName;
		this.paramType= paramType;
		this.paramUp= paramUp;
		this.paramDown= paramDown;
		this.equipmentId= equipmentId;
		this.groupId= groupId;
		this.equipmentName= equipmentName;
		this.groupName= groupName;
		this.userProductLineName= userProductLineName;
		this.userProductLineId= userProductLineId;
	}
}