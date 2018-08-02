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
import lj.model.basic.ProductLineEquipment;
import lj.service.basic.ProductLineEquipmentService;
import lj.util.DatatablesReturnObject;
import lj.controller.BaseController;

@Controller
@RequestMapping("basic")
public class ProductLineEquipmentController extends BaseController {
	@Autowired
	private ProductLineEquipmentService productLineEquipmentService;

	@RequestMapping(value = { "/productLineEquipmentPage" }, method = RequestMethod.GET)
	public String productLineEquipmentPage(Model model) {
		return "basic/productLineEquipmentPage";
	}
	
	@RequestMapping(value = { "/productLineEquipment" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<ProductLineEquipment> findAllPaged() {
		// 2-获得查询数据
		Pager<ProductLineEquipment> pager = productLineEquipmentService.findAllPagedProductLineEquipment();
		// 3-生成返回格式
		DatatablesReturnObject<ProductLineEquipment> obj = new DatatablesReturnObject<ProductLineEquipment>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
	@RequestMapping(value = { "/productLineEquipment" }, method = RequestMethod.POST)
	public @ResponseBody String insertProductLineEquipment(@RequestBody ProductLineEquipment obj,HttpServletRequest req)
	{
		Long retId=productLineEquipmentService.insertProductLineEquipment(obj);
		if(retId>0)
			return "";
		else
			return "新增失败";
	}
	
	@RequestMapping(value = { "/productLineEquipment" }, method = RequestMethod.PUT)
	public @ResponseBody String updateProductLineEquipment(@RequestBody ProductLineEquipment obj,HttpServletRequest req)
	{
		String msg=productLineEquipmentService.updateProductLineEquipment(obj);
		return msg;
	}
	
	@RequestMapping(value = { "/productLineEquipment/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteProductLineEquipment(@PathVariable Long id)
	{
		String msg=productLineEquipmentService.deleteProductLineEquipment(id);
		return msg;
	}
	
}
