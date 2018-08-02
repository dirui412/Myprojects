package lj.model.install;

public class Productlineposition {
	private Long  productLinePositionId;
	public Long getProductLinePositionId() {
		return productLinePositionId;
	}
	public void setProductLinePositionId(Long productLinePositionId) {
		this.productLinePositionId = productLinePositionId;
	}
	private Long  userProductLineId;
	public Long getUserProductLineId() {
		return userProductLineId;
	}
	public void setUserProductLineId(Long userProductLineId) {
		this.userProductLineId = userProductLineId;
	}
	private Double  position1;
	public Double getPosition1() {
		return position1;
	}
	public void setPosition1(Double position1) {
		this.position1 = position1;
	}
	private Double  position2;
	public Double getPosition2() {
		return position2;
	}
	public void setPosition2(Double position2) {
		this.position2 = position2;
	}
	
	public  Productlineposition() {
		super();
	}
	
	public  Productlineposition(
			Long productLinePositionId,Long userProductLineId,Double position1,Double position2){
		super();
		this.productLinePositionId= productLinePositionId;
		this.userProductLineId= userProductLineId;
		this.position1= position1;
		this.position2= position2;
	}
	@Override
	public String toString() {
		return "Productlineposition [productLinePositionId=" + productLinePositionId + ", userProductLineId="
				+ userProductLineId + ", position1=" + position1 + ", position2=" + position2 + "]";
	}
	
}