package lj.controller.basic;

import java.util.HashMap;
import java.util.Map;

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
import lj.model.basic.EquipmentType;
import lj.model.basic.ProductLineInfo;
import lj.model.basic.ViProductLineEquipment;
import lj.model.basic.Viequipmentinfo;
import lj.service.basic.EquipmentInfoService;
import lj.service.basic.EquipmentTypeService;
import lj.service.basic.ProductLineEquipmentService;
import lj.service.basic.ProductLineInfoService;
import lj.service.basic.ViProductLineEquipmentService;
import lj.util.DatatablesReturnObject;

@Controller
@RequestMapping("basic")
public class ProductLineInfoController extends BaseController {
	@Autowired
	private ProductLineInfoService productLineInfoService;

	@Autowired
	private ViProductLineEquipmentService viProductLineEquipmentService;

	@Autowired
	private EquipmentInfoService equipmentInfoService;
	
	@Autowired
	private EquipmentTypeService equipmentTypeService;
	
	@Autowired
	private ProductLineEquipmentService productLineEquipmentService;

	@RequestMapping(value = { "/productLineInfoPage" }, method = RequestMethod.GET)
	public String productLineInfoPage(Model model) {
		EquipmentType[] equipmentTypes= equipmentTypeService.findAll();
		Map<String, Viequipmentinfo[]> map = new HashMap<String, Viequipmentinfo[]>();
		for(int i=0; i<equipmentTypes.length; i++){
			Viequipmentinfo[]  ViequipmentInfos = equipmentInfoService.findByType(equipmentTypes[i].getEquipmentTypeId());
			if(ViequipmentInfos!=null){
				map.put(ViequipmentInfos[0].getEquipmentTypeName(), ViequipmentInfos);
			}
		}
		model.addAttribute("map", map);
		System.out.println("1111111111111:"+map.toString());
		return "basic/productLineInfoPage";
	}

	@RequestMapping(value = { "/productLineInfo" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<ProductLineInfo> findAllPaged() {
		// 2-获得查询数据
		Pager<ProductLineInfo> pager = productLineInfoService.findAllPagedProductLineInfo();
		// 3-生成返回格式
		DatatablesReturnObject<ProductLineInfo> obj = new DatatablesReturnObject<ProductLineInfo>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}

	@RequestMapping(value = { "/productLineInfo" }, method = RequestMethod.POST)
	public @ResponseBody String insertProductLineInfo(@RequestBody ProductLineInfo obj, HttpServletRequest req) {
		Long retId = productLineInfoService.insertProductLineInfo(obj);
		if (retId > 0)
			return "";
		else
			return "新增失败";
	}

	@RequestMapping(value = { "/productLineInfo" }, method = RequestMethod.PUT)
	public @ResponseBody String updateProductLineInfo(@RequestBody ProductLineInfo obj, HttpServletRequest req) {
		String msg = productLineInfoService.updateProductLineInfo(obj);
		return msg;
	}

	@RequestMapping(value = { "/productLineInfo/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteProductLineInfo(@PathVariable Long id) {
		String msg = productLineInfoService.deleteProductLineInfo(id);
		return msg;
	}

	@RequestMapping(value = { "/productLineEquipments" }, method = RequestMethod.GET)
	public @ResponseBody ViProductLineEquipment[] productLineEquipments(Long productLineId) {
		ViProductLineEquipment[] objs = viProductLineEquipmentService.findAllByProductLineId(productLineId);
		return objs;
	}

	@RequestMapping(value = { "/postProductLineEquipments" }, method = RequestMethod.POST)
	public @ResponseBody String postProductLineEquipments(Long productLineId, Long[] equipmentIds) {
		String msg = "";
		if (equipmentIds == null)
			msg = productLineEquipmentService.updateProductLineEquipments(productLineId, new Long[0]);
		else {
			for(Long id:equipmentIds)
				System.out.println("equipment id:"+id);
			msg = productLineEquipmentService.updateProductLineEquipments(productLineId, equipmentIds);
		}
		return msg;
	}

}
