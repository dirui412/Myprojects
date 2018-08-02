package ${controllerClassPath};

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lj.model.base.Pager;
import lj.model.base.QueryContext;
import ${modelClassPath}.${modelClassName};
import ${serviceClassPath}.${modelClassName}Service;
import lj.util.DatatablesReturnObject;
import lj.controller.BaseController;

@Controller
@RequestMapping("${parentContextUrl}")
public class ${modelClassName}Controller extends BaseController {
	@Autowired
	private ${modelClassName}Service ${modelClassNameFS}Service;

	@RequestMapping(value = { "/${modelClassNameFS}Page" }, method = RequestMethod.GET)
	public String ${modelClassNameFS}Page(Model model) {
		return "${parentContextUrl}/${modelClassNameFS}Page";
	}
	
	@RequestMapping(value = { "/${modelClassNameFS}" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<${modelClassName}> findAllPaged(<#list queryFields as e>${e.beanType} query${e.beanNameFB} <#if e_has_next>,</#if> </#list>) {
		// 2-获得查询数据
		Pager<${modelClassName}> pager = ${modelClassNameFS}Service.findAllPaged${modelClassName}(<#list queryFields as e>query${e.beanNameFB} <#if e_has_next>,</#if> </#list>);
		// 3-生成返回格式
		DatatablesReturnObject<${modelClassName}> obj = new DatatablesReturnObject<${modelClassName}>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
	@RequestMapping(value = { "/${modelClassNameFS}" }, method = RequestMethod.POST)
	public @ResponseBody String insert${modelClassName}(@RequestBody ${modelClassName} obj,HttpServletRequest req)
	{
		Long retId=${modelClassNameFS}Service.insert${modelClassName}(obj);
		if(retId>0)
			return "";
		else
			return "新增失败";
	}
	
	@RequestMapping(value = { "/${modelClassNameFS}" }, method = RequestMethod.PUT)
	public @ResponseBody String update${modelClassName}(@RequestBody ${modelClassName} obj,HttpServletRequest req)
	{
		String msg=${modelClassNameFS}Service.update${modelClassName}(obj);
		return msg;
	}
	
	@RequestMapping(value = { "/${modelClassNameFS}/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String delete${modelClassName}(@PathVariable Long id)
	{
		String msg=${modelClassNameFS}Service.delete${modelClassName}(id);
		return msg;
	}
	
}
