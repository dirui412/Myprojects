package lj.model.install;

import java.util.Date;

public class UserProductLine {
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
	
	public  UserProductLine() {
		super();
	}
	
	public  UserProductLine(
			Long userProductLineId,Long productLineId,Long groupId,String userProductLineName,String productLineState,Long lectotypeUserId,Date lectotypeTime,String lectotypeParams,String lectotypeMemo){
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
	}
}