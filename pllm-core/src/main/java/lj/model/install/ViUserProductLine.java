package lj.model.install;

import java.util.Date;

public class ViUserProductLine {
	private Integer  userProductLineId;
	public Integer getUserProductLineId() {
		return userProductLineId;
	}
	public void setUserProductLineId(Integer userProductLineId) {
		this.userProductLineId = userProductLineId;
	}
	private Integer  productLineId;
	public Integer getProductLineId() {
		return productLineId;
	}
	public void setProductLineId(Integer productLineId) {
		this.productLineId = productLineId;
	}
	private Long  groupId;
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	private String userProductLineName;
	
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
	private Long  lectotypeUserId;
	public Long getLectotypeUserId() {
		return lectotypeUserId;
	}
	public void setLectotypeUserId(Long lectotypeUserId) {
		this.lectotypeUserId = lectotypeUserId;
	}
	private Date  lectotypeTime;
	public Date getLectotypeTime() {
		return lectotypeTime;
	}
	public void setLectotypeTime(Date lectotypeTime) {
		this.lectotypeTime = lectotypeTime;
	}
	private String  lectotypeParams;
	public String getLectotypeParams() {
		return lectotypeParams;
	}
	public void setLectotypeParams(String lectotypeParams) {
		this.lectotypeParams = lectotypeParams;
	}
	private String  lectotypeMemo;
	public String getLectotypeMemo() {
		return lectotypeMemo;
	}
	public void setLectotypeMemo(String lectotypeMemo) {
		this.lectotypeMemo = lectotypeMemo;
	}
	private String  groupName;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	private String  lectotypeUserName;
	public String getLectotypeUserName() {
		return lectotypeUserName;
	}
	public void setLectotypeUserName(String lectotypeUserName) {
		this.lectotypeUserName = lectotypeUserName;
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
	
	public  ViUserProductLine() {
		super();
	}
	
	public  ViUserProductLine(
	  Integer userProductLineId,Integer productLineId,Long groupId,String userProductLineName,String productLineState,Long lectotypeUserId,Date lectotypeTime,String lectotypeParams,String lectotypeMemo,String groupName,String lectotypeUserName,String productName,String productType){
		super();
		this.userProductLineId= userProductLineId;
		this.productLineId= productLineId;
		this.groupId= groupId;
		this.userProductLineName=userProductLineName;
		this.productLineState= productLineState;
		this.lectotypeUserId= lectotypeUserId;
		this.lectotypeTime= lectotypeTime;
		this.lectotypeParams= lectotypeParams;
		this.lectotypeMemo= lectotypeMemo;
		this.groupName= groupName;
		this.lectotypeUserName= lectotypeUserName;
		this.productName= productName;
		this.productType= productType;
	}
}