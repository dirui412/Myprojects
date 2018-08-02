package lj.model.base;

public class ChartStringDoublePoint {
	private String xValue;
	private Double yValue;
	public String getxValue() {
		return xValue;
	}
	public void setxValue(String xValue) {
		this.xValue = xValue;
	}
	public Double getyValue() {
		return yValue;
	}
	public void setyValue(Double yValue) {
		this.yValue = yValue;
	}
	public ChartStringDoublePoint(String xValue, Double yValue) {
		super();
		this.xValue = xValue;
		this.yValue = yValue;
	}
	public ChartStringDoublePoint() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
