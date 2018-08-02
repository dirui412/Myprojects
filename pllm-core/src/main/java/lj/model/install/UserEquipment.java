package lj.model.install;

import java.util.Date;

public class UserEquipment {
	private Long  userEquipmentId;
	public Long getUserEquipmentId() {
		return userEquipmentId;
	}
	public void setUserEquipmentId(Long userEquipmentId) {
		this.userEquipmentId = userEquipmentId;
	}
	private Long  userProductLineId;
	public Long getUserProductLineId() {
		return userProductLineId;
	}
	public void setUserProductLineId(Long userProductLineId) {
		this.userProductLineId = userProductLineId;
	}
	private Long  equipmentId;
	public Long getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}
	private Date  installTime;
	public Date getInstallTime() {
		return installTime;
	}
	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
	}
	private String  equipmentState;
	public String getEquipmentState() {
		return equipmentState;
	}
	public void setEquipmentState(String equipmentState) {
		this.equipmentState = equipmentState;
	}
	private String  installParams;
	public String getInstallParams() {
		return installParams;
	}
	public void setInstallParams(String installParams) {
		this.installParams = installParams;
	}
	private String  installMemo;
	public String getInstallMemo() {
		return installMemo;
	}
	public void setInstallMemo(String installMemo) {
		this.installMemo = installMemo;
	}
	private String  text;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	private String  keyy;
	public String getKeyy() {
		return keyy;
	}
	public void setKeyy(String keyy) {
		this.keyy = keyy;
	}
	private Integer  loc1;
	public Integer getLoc1() {
		return loc1;
	}
	public void setLoc1(Integer loc1) {
		this.loc1 = loc1;
	}
	private Integer  loc2;
	public Integer getLoc2() {
		return loc2;
	}
	public void setLoc2(Integer loc2) {
		this.loc2 = loc2;
	}
	public  UserEquipment() {
		super();
	}
	
	public  UserEquipment(
	  Long userEquipmentId,Long userProductLineId,Long equipmentId,Date installTime,String equipmentState,String installParams,String installMemo
	,String text,String keyy,Integer loc1,Integer loc2){
		super();
		this.userEquipmentId= userEquipmentId;
		this.userProductLineId= userProductLineId;
		this.equipmentId= equipmentId;
		this.installTime= installTime;
		this.equipmentState= equipmentState;
		this.installParams= installParams;
		this.installMemo= installMemo;
		this.text= text;
		this.keyy= keyy;
		this.loc1= loc1;
		this.loc2= loc2;
	}
	@Override
	public String toString() {
		return "UserEquipment [userEquipmentId=" + userEquipmentId + ", userProductLineId=" + userProductLineId
				+ ", equipmentId=" + equipmentId + ", installTime=" + installTime + ", equipmentState=" + equipmentState
				+ ", installParams=" + installParams + ", installMemo=" + installMemo + ", text=" + text + ", keyy="
				+ keyy + ", loc1=" + loc1 + ", loc2=" + loc2 + "]";
	}
	
}