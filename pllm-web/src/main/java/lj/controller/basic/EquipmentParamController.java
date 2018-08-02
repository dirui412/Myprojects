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
import lj.model.basic.EquipmentParam;
import lj.service.basic.EquipmentParamService;
import lj.util.DatatablesReturnObject;
import lj.controller.BaseController;

@Controller
@RequestMapping("basic")
public class EquipmentParamController extends BaseController {
	@Autowired
	private EquipmentParamService equipmentParamService;

	@RequestMapping(value = { "/equipmentParamPage" }, method = RequestMethod.GET)
	public String equipmentParamPage(Model model) {
		return "basic/equipmentParamPage";
	}
	
	@RequestMapping(value = { "/equipmentParam" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<EquipmentParam> findAllPaged() {
		// 2-获得查询数据
		Pager<EquipmentParam> pager = equipmentParamService.findAllPagedEquipmentParam();
		// 3-生成返回格式
		DatatablesReturnObject<EquipmentParam> obj = new DatatablesReturnObject<EquipmentParam>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}

	@RequestMapping(value = { "/equipmentParam" }, method = RequestMethod.POST)
	public @ResponseBody String insertEquipmentParam(@RequestBody EquipmentParam obj,HttpServletRequest req)
	{
		Long retId=equipmentParamService.insertEquipmentParam(obj);
		if(retId>0)
			return "";
		else
			return "新增失败";
	}
	
	@RequestMapping(value = { "/equipmentParam" }, method = RequestMethod.PUT)
	public @ResponseBody String updateEquipmentParam(@RequestBody EquipmentParam obj,HttpServletRequest req)
	{
		String msg=equipmentParamService.updateEquipmentParam(obj);
		return msg;
	}
	
	@RequestMapping(value = { "/equipmentParam/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteEquipmentParam(@PathVariable Long id)
	{
		String msg=equipmentParamService.deleteEquipmentParam(id);
		return msg;
	}
	
}
