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
import lj.model.basic.ViProductLineEquipment;
import lj.service.basic.ViProductLineEquipmentService;
import lj.util.DatatablesReturnObject;
import lj.controller.BaseController;

@Controller
@RequestMapping("basic")
public class ViProductLineEquipmentController extends BaseController {
	@Autowired
	private ViProductLineEquipmentService viProductLineEquipmentService;

	@RequestMapping(value = { "/viProductLineEquipmentPage" }, method = RequestMethod.GET)
	public String viProductLineEquipmentPage(Model model) {
		return "basic/viProductLineEquipmentPage";
	}
	
	@RequestMapping(value = { "/viProductLineEquipment" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<ViProductLineEquipment> findAllPaged(String queryProductName , String queryEquipmentName  ) {
		// 2-获得查询数据
		Pager<ViProductLineEquipment> pager = viProductLineEquipmentService.findAllPagedViProductLineEquipment(queryProductName , queryEquipmentName  );
		// 3-生成返回格式
		DatatablesReturnObject<ViProductLineEquipment> obj = new DatatablesReturnObject<ViProductLineEquipment>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
	@RequestMapping(value = { "/viProductLineEquipment" }, method = RequestMethod.POST)
	public @ResponseBody String insertViProductLineEquipment(@RequestBody ViProductLineEquipment obj,HttpServletRequest req)
	{
		Long retId=viProductLineEquipmentService.insertViProductLineEquipment(obj);
		if(retId>0)
			return "";
		else
			return "新增失败";
	}
	
	@RequestMapping(value = { "/viProductLineEquipment" }, method = RequestMethod.PUT)
	public @ResponseBody String updateViProductLineEquipment(@RequestBody ViProductLineEquipment obj,HttpServletRequest req)
	{
		String msg=viProductLineEquipmentService.updateViProductLineEquipment(obj);
		return msg;
	}
	
	@RequestMapping(value = { "/viProductLineEquipment/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteViProductLineEquipment(@PathVariable Long id)
	{
		String msg=viProductLineEquipmentService.deleteViProductLineEquipment(id);
		return msg;
	}
	
}
