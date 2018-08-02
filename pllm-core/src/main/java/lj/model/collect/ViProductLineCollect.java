package lj.model.collect;

import java.util.Date;

public class ViProductLineCollect {
	private Long  productLineCollectId;
	public Long getProductLineCollectId() {
		return productLineCollectId;
	}
	public void setProductLineCollectId(Long productLineCollectId) {
		this.productLineCollectId = productLineCollectId;
	}
	private Long  userProductLineId;
	public Long getUserProductLineId() {
		return userProductLineId;
	}
	public void setUserProductLineId(Long userProductLineId) {
		this.userProductLineId = userProductLineId;
	}
	
	private String userProductLineName;
	public String getUserProductLineName() {
		return userProductLineName;
	}
	public void setUserProductLineName(String userProductLineName) {
		this.userProductLineName = userProductLineName;
	}
	private Long  productLineParamId;
	public Long getProductLineParamId() {
		return productLineParamId;
	}
	public void setProductLineParamId(Long productLineParamId) {
		this.productLineParamId = productLineParamId;
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
	private Long  groupId;
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	private String  groupName;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	private Long  productLineId;
	public Long getProductLineId() {
		return productLineId;
	}
	public void setProductLineId(Long productLineId) {
		this.productLineId = productLineId;
	}
	private String  productName;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	
	public  ViProductLineCollect() {
		super();
	}
	
	public  ViProductLineCollect(
	  Long productLineCollectId,Long userProductLineId,Long productLineParamId,Date collectTime,Integer paramState,Double paramValue,Long groupId,String groupName,Long productLineId,String productName,String paramName,String paramType,Double paramUp,Double paramDown){
		super();
		this.productLineCollectId= productLineCollectId;
		this.userProductLineId= userProductLineId;
		this.productLineParamId= productLineParamId;
		this.collectTime= collectTime;
		this.paramState= paramState;
		this.paramValue= paramValue;
		this.groupId= groupId;
		this.groupName= groupName;
		this.productLineId= productLineId;
		this.productName= productName;
		this.paramName= paramName;
		this.paramType= paramType;
		this.paramUp= paramUp;
		this.paramDown= paramDown;
	}
}