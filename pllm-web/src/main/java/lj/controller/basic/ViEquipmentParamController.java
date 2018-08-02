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
import lj.model.basic.ViEquipmentParam;
import lj.service.basic.ViEquipmentParamService;
import lj.util.DatatablesReturnObject;
import lj.controller.BaseController;

@Controller
@RequestMapping("basic")
public class ViEquipmentParamController extends BaseController {
	@Autowired
	private ViEquipmentParamService viEquipmentParamService;

	@RequestMapping(value = { "/viEquipmentParamPage" }, method = RequestMethod.GET)
	public String viEquipmentParamPage(Model model,String equipmentId) {
		model.addAttribute("equipmentId",equipmentId);
		return "basic/viEquipmentParamPage";
	}
	
	@RequestMapping(value = { "/viEquipmentParam" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<ViEquipmentParam> findAllPaged(
			Long queryEquipmentId,String queryParamName , String queryParamType  ) {
		// 2-获得查询数据
		Pager<ViEquipmentParam> pager = viEquipmentParamService.findAllPagedViEquipmentParam(queryEquipmentId,queryParamName , queryParamType  );
		// 3-生成返回格式
		DatatablesReturnObject<ViEquipmentParam> obj = new DatatablesReturnObject<ViEquipmentParam>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
	@RequestMapping(value = { "/viEquipmentParam" }, method = RequestMethod.POST)
	public @ResponseBody String insertViEquipmentParam(@RequestBody ViEquipmentParam obj,HttpServletRequest req)
	{
		Long retId=viEquipmentParamService.insertViEquipmentParam(obj);
		if(retId>0)
			return "";
		else
			return "新增失败";
	}
	
	@RequestMapping(value = { "/viEquipmentParam" }, method = RequestMethod.PUT)
	public @ResponseBody String updateViEquipmentParam(@RequestBody ViEquipmentParam obj,HttpServletRequest req)
	{
		String msg=viEquipmentParamService.updateViEquipmentParam(obj);
		return msg;
	}
	
	@RequestMapping(value = { "/viEquipmentParam/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteViEquipmentParam(@PathVariable Long id)
	{
		String msg=viEquipmentParamService.deleteViEquipmentParam(id);
		return msg;
	}
	
}
