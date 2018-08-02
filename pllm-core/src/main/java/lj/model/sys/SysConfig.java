package lj.model.sys;

/***
 * 系统配置类
 * 
 * @author samlv
 *
 */
public class SysConfig {
	//平台相关参数
	private String systemName;            //系统名称
	private String companyName;           //公司名称
	private String defaultPassword;       //系统默认密码


	
	
	public String getSystemName() {
		return systemName;
	}


	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getDefaultPassword() {
		return defaultPassword;
	}


	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}


	


	public SysConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
}
