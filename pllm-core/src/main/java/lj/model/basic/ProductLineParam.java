package lj.model.basic;

import java.util.Date;

public class ProductLineParam {
	private Long  productLineParamId;
	public Long getProductLineParamId() {
		return productLineParamId;
	}
	public void setProductLineParamId(Long productLineParamId) {
		this.productLineParamId = productLineParamId;
	}
	private Long  productLineId;
	public Long getProductLineId() {
		return productLineId;
	}
	public void setProductLineId(Long productLineId) {
		this.productLineId = productLineId;
	}
	private String paramName;
	
	
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
	
	public  ProductLineParam() {
		super();
	}
	
	public  ProductLineParam(
	  Long productLineParamId,Long productLineId,String paramName,String paramType,Double paramUp,Double paramDown){
		super();
		this.productLineParamId= productLineParamId;
		this.productLineId= productLineId;
		this.paramName=paramName;
		this.paramType= paramType;
		this.paramUp= paramUp;
		this.paramDown= paramDown;
	}
}