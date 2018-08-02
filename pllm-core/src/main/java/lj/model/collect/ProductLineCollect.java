package lj.model.collect;

import java.util.Date;

public class ProductLineCollect {
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
	
	public  ProductLineCollect() {
		super();
	}
	
	public  ProductLineCollect(
	  Long productLineCollectId,Long userProductLineId,Long productLineParamId,Date collectTime,Integer paramState,Double paramValue){
		super();
		this.productLineCollectId= productLineCollectId;
		this.userProductLineId= userProductLineId;
		this.productLineParamId= productLineParamId;
		this.collectTime= collectTime;
		this.paramState= paramState;
		this.paramValue= paramValue;
	}
}