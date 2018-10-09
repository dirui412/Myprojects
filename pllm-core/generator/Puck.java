package lj.model.install;

import java.util.Date;

public class Puck {
	private Long  puckId;
	public Long getPuckId() {
		return puckId;
	}
	public void setPuckId(Long puckId) {
		this.puckId = puckId;
	}
	private String  puckName;
	public String getPuckName() {
		return puckName;
	}
	public void setPuckName(String puckName) {
		this.puckName = puckName;
	}
	private Integer  arriveDate;
	public Integer getArriveDate() {
		return arriveDate;
	}
	public void setArriveDate(Integer arriveDate) {
		this.arriveDate = arriveDate;
	}
	private Integer  arriveTime;
	public Integer getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(Integer arriveTime) {
		this.arriveTime = arriveTime;
	}
	private Integer  goDate;
	public Integer getGoDate() {
		return goDate;
	}
	public void setGoDate(Integer goDate) {
		this.goDate = goDate;
	}
	private Integer  goTime;
	public Integer getGoTime() {
		return goTime;
	}
	public void setGoTime(Integer goTime) {
		this.goTime = goTime;
	}
	private String  puckArriveType;
	public String getPuckArriveType() {
		return puckArriveType;
	}
	public void setPuckArriveType(String puckArriveType) {
		this.puckArriveType = puckArriveType;
	}
	private String  puckGoType;
	public String getPuckGoType() {
		return puckGoType;
	}
	public void setPuckGoType(String puckGoType) {
		this.puckGoType = puckGoType;
	}
	private String  puckVersion;
	public String getPuckVersion() {
		return puckVersion;
	}
	public void setPuckVersion(String puckVersion) {
		this.puckVersion = puckVersion;
	}
	
	public  Puck() {
		super();
	}
	
	public  Puck(
	  Long puckId,String puckName,Integer arriveDate,Integer arriveTime,Integer goDate,Integer goTime,String puckArriveType,String puckGoType,String puckVersion){
		super();
		this.puckId= puckId;
		this.puckName= puckName;
		this.arriveDate= arriveDate;
		this.arriveTime= arriveTime;
		this.goDate= goDate;
		this.goTime= goTime;
		this.puckArriveType= puckArriveType;
		this.puckGoType= puckGoType;
		this.puckVersion= puckVersion;
	}
}