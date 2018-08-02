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

import lj.controller.BaseController;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.basic.ProductLineParam;
import lj.model.basic.ViProductLineParam;
import lj.service.basic.ProductLineParamService;
import lj.service.basic.ViProductLineParamService;
import lj.util.DatatablesReturnObject;

@Controller
@RequestMapping("basic")
public class ViProductLineParamController extends BaseController {
	@Autowired
	private ViProductLineParamService viProductLineParamService;
	@Autowired
	private ProductLineParamService productLineParamService;
	
	                            
	@RequestMapping(value = { "/viProductLineParamPage" }, method = RequestMethod.GET)
	public String viProductLineParamPage(Model model,String productLineId) {
		model.addAttribute("productLineId",productLineId);
		return "basic/viProductLineParamPage";
	}
	
	@RequestMapping(value = { "/viProductLineParam" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<ViProductLineParam> findAllPaged(String queryProductLineId,
			String queryParamName,String queryParamType) {
		System.out.println(" queryProductLineId:"+queryProductLineId+",queryParamName:"+queryParamName+",queryParamType:"+queryParamType);;
		// 2-获得查询数据
		Pager<ViProductLineParam> pager = viProductLineParamService.findAllPagedViProductLineParam(
				queryProductLineId,queryParamName,queryParamType);
		// 3-生成返回格式
		DatatablesReturnObject<ViProductLineParam> obj = new DatatablesReturnObject<ViProductLineParam>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
	@RequestMapping(value = { "/viProductLineParam" }, method = RequestMethod.POST)
	public @ResponseBody String insertViProductLineParam(@RequestBody ProductLineParam obj,HttpServletRequest req)
	{
		Long retId=productLineParamService.insertProductLineParam(obj);
		if(retId>0)
			return "";
		else
			return "新增失败";
	}
	
	@RequestMapping(value = { "/viProductLineParam" }, method = RequestMethod.PUT)
	public @ResponseBody String updateViProductLineParam(@RequestBody ProductLineParam obj,HttpServletRequest req)
	{
		String msg=productLineParamService.updateProductLineParam(obj);
		return msg;
	}
	
	@RequestMapping(value = { "/viProductLineParam/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteViProductLineParam(@PathVariable Long id)
	{
		String msg=productLineParamService.deleteProductLineParam(id);
		return msg;
	}
	
}
