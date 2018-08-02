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
import lj.model.basic.EquipmentType;
import lj.service.basic.EquipmentTypeService;
import lj.util.DatatablesReturnObject;
import lj.controller.BaseController;

@Controller
@RequestMapping("basic")
public class EquipmentTypeController extends BaseController {
	@Autowired
	private EquipmentTypeService equipmentTypeService;

	@RequestMapping(value = { "/equipmentTypePage" }, method = RequestMethod.GET)
	public String equipmentTypePage(Model model) {
		return "basic/equipmentTypePage";
	}
	
	@RequestMapping(value = { "/equipmentType" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<EquipmentType> findAllPaged(Long queryEquipmentTypeId , String queryEquipmentTypeName , String queryEquipmentTypeDescribe  ) {
		// 2-获得查询数据
		Pager<EquipmentType> pager = equipmentTypeService.findAllPagedEquipmentType(queryEquipmentTypeId , queryEquipmentTypeName , queryEquipmentTypeDescribe  );
		// 3-生成返回格式
		DatatablesReturnObject<EquipmentType> obj = new DatatablesReturnObject<EquipmentType>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
	@RequestMapping(value = { "/equipmentType" }, method = RequestMethod.POST)
	public @ResponseBody String insertEquipmentType(@RequestBody EquipmentType obj,HttpServletRequest req)
	{
		Long retId=equipmentTypeService.insertEquipmentType(obj);
		if(retId>0)
			return "";
		else
			return "新增失败";
	}
	
	@RequestMapping(value = { "/equipmentType" }, method = RequestMethod.PUT)
	public @ResponseBody String updateEquipmentType(@RequestBody EquipmentType obj,HttpServletRequest req)
	{
		String msg=equipmentTypeService.updateEquipmentType(obj);
		return msg;
	}
	
	@RequestMapping(value = { "/equipmentType/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteEquipmentType(@PathVariable Long id)
	{
		String msg=equipmentTypeService.deleteEquipmentType(id);
		return msg;
	}
	
}
