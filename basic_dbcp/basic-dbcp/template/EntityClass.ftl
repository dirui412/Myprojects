package ${packageName};

import java.util.Date;

public class ${className} {
    <#list fields as e>
	private ${e.beanType}  ${e.beanName};
	public ${e.beanType} ${e.getterName}() {
		return ${e.beanName};
	}
	public void ${e.setterName}(${e.beanType} ${e.beanName}) {
		this.${e.beanName} = ${e.beanName};
	}
    </#list>
	
	public  ${className}() {
		super();
	}
	
	public  ${className}(
	  <#list fields as e>${e.beanType} ${e.beanName}<#if e_has_next>,</#if></#list>){
		super();
		<#list fields as e>
		this.${e.beanName}= ${e.beanName};
		</#list>
	}
}