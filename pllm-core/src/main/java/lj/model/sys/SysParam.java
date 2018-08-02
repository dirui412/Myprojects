package lj.model.sys;

public class SysParam {
	private Long  paramId;
	public Long getParamId() {
		return paramId;
	}
	public void setParamId(Long paramId) {
		this.paramId = paramId;
	}
	private String  paramName;
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	private String  paramValue;
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	private String  paramMemo;
	public String getParamMemo() {
		return paramMemo;
	}
	public void setParamMemo(String paramMemo) {
		this.paramMemo = paramMemo;
	}
	
	public  SysParam() {
		super();
	}
	
	public  SysParam(
	  Long paramId,String paramName,String paramValue,String paramMemo){
		super();
		this.paramId= paramId;
		this.paramName= paramName;
		this.paramValue= paramValue;
		this.paramMemo= paramMemo;
	}
}