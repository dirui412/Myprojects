package lj.controller.basic;

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
import lj.model.basic.ViProductLineParam;
import lj.service.basic.ViProductLineParamService;
import lj.util.DatatablesReturnObject;
import lj.controller.BaseController;

@Controller
@RequestMapping("basic")
public class ViProductLineParamController extends BaseController {
	@Autowired
	private ViProductLineParamService viProductLineParamService;

	@RequestMapping(value = { "/viProductLineParamPage" }, method = RequestMethod.GET)
	public String viProductLineParamPage(Model model) {
		return "basic/viProductLineParamPage";
	}
	
	@RequestMapping(value = { "/viProductLineParam" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<ViProductLineParam> findAllPaged() {
		// 2-获得查询数据
		Pager<ViProductLineParam> pager = viProductLineParamService.findAllPagedViProductLineParam();
		// 3-生成返回格式
		DatatablesReturnObject<ViProductLineParam> obj = new DatatablesReturnObject<ViProductLineParam>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
	@RequestMapping(value = { "/viProductLineParam" }, method = RequestMethod.POST)
	public @ResponseBody String insertViProductLineParam(@RequestBody ViProductLineParam obj,HttpServletRequest req)
	{
		Long retId=viProductLineParamService.insertViProductLineParam(obj);
		if(retId>0)
			return "";
		else
			return "新增失败";
	}
	
	@RequestMapping(value = { "/viProductLineParam" }, method = RequestMethod.PUT)
	public @ResponseBody String updateViProductLineParam(@RequestBody ViProductLineParam obj,HttpServletRequest req)
	{
		String msg=viProductLineParamService.updateViProductLineParam(obj);
		return msg;
	}
	
	@RequestMapping(value = { "/viProductLineParam/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteViProductLineParam(@PathVariable Long id)
	{
		String msg=viProductLineParamService.deleteViProductLineParam(id);
		return msg;
	}
	
}
