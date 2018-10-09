package lj.model.install;

import java.util.Date;

public class Gate {
	private Long  gateId;
	public Long getGateId() {
		return gateId;
	}
	public void setGateId(Long gateId) {
		this.gateId = gateId;
	}
	private String  gateName;
	public String getGateName() {
		return gateName;
	}
	public void setGateName(String gateName) {
		this.gateName = gateName;
	}
	private String  gateArriveType;
	public String getGateArriveType() {
		return gateArriveType;
	}
	public void setGateArriveType(String gateArriveType) {
		this.gateArriveType = gateArriveType;
	}
	private String  gateGoType;
	public String getGateGoType() {
		return gateGoType;
	}
	public void setGateGoType(String gateGoType) {
		this.gateGoType = gateGoType;
	}
	private String  gateVersion;
	public String getGateVersion() {
		return gateVersion;
	}
	public void setGateVersion(String gateVersion) {
		this.gateVersion = gateVersion;
	}
	private Integer  priority;
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	public  Gate() {
		super();
	}
	
	public  Gate(
	  Long gateId,String gateName,String gateArriveType,String gateGoType,String gateVersion,Integer priority){
		super();
		this.gateId= gateId;
		this.gateName= gateName;
		this.gateArriveType= gateArriveType;
		this.gateGoType= gateGoType;
		this.gateVersion= gateVersion;
		this.priority= priority;
	}
}