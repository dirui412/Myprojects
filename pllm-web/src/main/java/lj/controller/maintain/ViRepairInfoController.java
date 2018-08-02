package lj.controller.maintain;

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
import lj.model.install.ViUserProductLine;
import lj.model.maintain.RepairInfo;
import lj.model.maintain.ViRepairInfo;
import lj.service.install.ViUserProductLineService;
import lj.service.maintain.RepairInfoService;
import lj.service.maintain.ViRepairInfoService;
import lj.util.DatatablesReturnObject;

@Controller
@RequestMapping("maintain")
public class ViRepairInfoController extends BaseController {
	@Autowired
	private ViRepairInfoService viRepairInfoService;
	@Autowired
	private RepairInfoService repairInfoService=null;
	@Autowired
	private ViUserProductLineService userProductLineService=null;
	
	@RequestMapping(value = { "/viRepairInfoPage" }, method = RequestMethod.GET)
	public String viRepairInfoPage(Model model) {
		ViUserProductLine[] userProductLines=userProductLineService.findAll();
		model.addAttribute("userProductLines", userProductLines);
		return "maintain/viRepairInfoPage";
	}
	
	@RequestMapping(value = { "/viRepairInfo" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<ViRepairInfo> findAllPaged(String queryUserProductLineName,String queryEquipmentName  ) {
		// 2-获得查询数据
		Pager<ViRepairInfo> pager = viRepairInfoService.findAllPagedViRepairInfo(queryUserProductLineName,queryEquipmentName  );
		// 3-生成返回格式
		DatatablesReturnObject<ViRepairInfo> obj = new DatatablesReturnObject<ViRepairInfo>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
	@RequestMapping(value = { "/viRepairInfo" }, method = RequestMethod.POST)
	public @ResponseBody String insertViRepairInfo(@RequestBody RepairInfo obj,HttpServletRequest req)
	{
		Long retId=repairInfoService.insertRepairInfo(obj);
		if(retId>0)
			return "";
		else
			return "新增失败";
	}
	
	@RequestMapping(value = { "/viRepairInfo" }, method = RequestMethod.PUT)
	public @ResponseBody String updateViRepairInfo(@RequestBody RepairInfo obj,HttpServletRequest req)
	{
		String msg=repairInfoService.updateRepairInfo(obj);
		return msg;
	}
	
	@RequestMapping(value = { "/viRepairInfo/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteViRepairInfo(@PathVariable Long id)
	{
		String msg=repairInfoService.deleteRepairInfo(id);
		return msg;
	}
	
}
