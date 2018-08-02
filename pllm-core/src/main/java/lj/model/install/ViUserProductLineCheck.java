package lj.model.install;

import java.util.Date;

public class ViUserProductLineCheck {
	private Long  userProductLineId;
	public Long getUserProductLineId() {
		return userProductLineId;
	}
	public void setUserProductLineId(Long userProductLineId) {
		this.userProductLineId = userProductLineId;
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
	private Long  checkId;
	public Long getCheckId() {
		return checkId;
	}
	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}
	private Long  checkUserId;
	public Long getCheckUserId() {
		return checkUserId;
	}
	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}
	private Date  checkTime;
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	private String  checkMemo;
	public String getCheckMemo() {
		return checkMemo;
	}
	public void setCheckMemo(String checkMemo) {
		this.checkMemo = checkMemo;
	}
	private String  checkUserName;
	public String getCheckUserName() {
		return checkUserName;
	}
	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}
	
	public  ViUserProductLineCheck() {
		super();
	}
	
	public  ViUserProductLineCheck(
	  Long userProductLineId,Long productLineId,Long groupId,String userProductLineName,String productLineState,Long lectotypeUserId,Date lectotypeTime,String lectotypeParams,String lectotypeMemo,String groupName,String lectotypeUserName,String productName,String productType,Long checkId,Long checkUserId,Date checkTime,String checkMemo,String checkUserName){
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
		this.checkId= checkId;
		this.checkUserId= checkUserId;
		this.checkTime= checkTime;
		this.checkMemo= checkMemo;
		this.checkUserName= checkUserName;
	}
}