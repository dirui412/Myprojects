package lj.util;

/**
 * Flot的序列模型
 * @author samlv
 *
 */
public class FlotSerial{
	private String label;         //序列标题
	private Object[][] data;      //序列数据,x轴:Object[0],y轴:Object[1]
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Object[][] getData() {
		return data;
	}
	public void setData(Object[][] data) {
		this.data = data;
	}
	public FlotSerial(String label, Object[][] data) {
		super();
		this.label = label;
		this.data = data;
	}
	public FlotSerial() {
		super();
		// TODO Auto-generated constructor stub
	}
}