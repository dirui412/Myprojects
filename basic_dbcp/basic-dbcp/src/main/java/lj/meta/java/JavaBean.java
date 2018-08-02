package lj.meta.java;

/***
 * java bean
 * @author samlv
 *
 */
public class JavaBean {
	private String beanType;
	private String beanName;
	private String beanNameFB;  //首字母大写
	private String getterName;
	private String setterName;
	
	public String getBeanType() {
		return beanType;
	}
	public void setBeanType(String beanType) {
		this.beanType = beanType;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getBeanNameFB() {
		return beanNameFB;
	}
	public void setBeanNameFB(String beanNameFB) {
		this.beanNameFB = beanNameFB;
	}
	public String getGetterName() {
		return getterName;
	}
	public void setGetterName(String getterName) {
		this.getterName = getterName;
	}
	public String getSetterName() {
		return setterName;
	}
	public void setSetterName(String setterName) {
		this.setterName = setterName;
	}
	public JavaBean(String beanType, String beanName, String beanNameFB, String getterName, String setterName) {
		super();
		this.beanType = beanType;
		this.beanName = beanName;
		this.beanNameFB = beanNameFB;
		this.getterName = getterName;
		this.setterName = setterName;
	}
	public JavaBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
