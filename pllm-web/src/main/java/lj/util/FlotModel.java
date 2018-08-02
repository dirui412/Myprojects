package lj.util;

/**
 * jquery float数据模型
 * 
 * @author samlv
 *
 */
public class FlotModel {
	private FlotSerial[] serials;  //多个序列数据
	private Object[][] xTicks;   //x轴刻度
	private Object[][] yTicks;   //y轴刻度
	public FlotSerial[] getSerials() {
		return serials;
	}
	public void setSerials(FlotSerial[] serials) {
		this.serials = serials;
	}
	public Object[][] getxTicks() {
		return xTicks;
	}
	public void setxTicks(Object[][] xTicks) {
		this.xTicks = xTicks;
	}
	public Object[][] getyTicks() {
		return yTicks;
	}
	public void setyTicks(Object[][] yTicks) {
		this.yTicks = yTicks;
	}
	public FlotModel(FlotSerial[] serials, Object[][] xTicks, Object[][] yTicks) {
		super();
		this.serials = serials;
		this.xTicks = xTicks;
		this.yTicks = yTicks;
	}
	public FlotModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
