package lj.model.basic;

import java.util.Date;

public class ProductLineInfo {
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
	private String  productVersion;
	public String getProductVersion() {
		return productVersion;
	}
	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}
	private String  productConfig;
	public String getProductConfig() {
		return productConfig;
	}
	public void setProductConfig(String productConfig) {
		this.productConfig = productConfig;
	}
	private String  productMemo;
	public String getProductMemo() {
		return productMemo;
	}
	public void setProductMemo(String productMemo) {
		this.productMemo = productMemo;
	}
	
	public  ProductLineInfo() {
		super();
	}
	
	public  ProductLineInfo(
	  Long productLineId,String productName,String productVersion,String productConfig,String productMemo){
		super();
		this.productLineId= productLineId;
		this.productName= productName;
		this.productVersion= productVersion;
		this.productConfig= productConfig;
		this.productMemo= productMemo;
	}
}