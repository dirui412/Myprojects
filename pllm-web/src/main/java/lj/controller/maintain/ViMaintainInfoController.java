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
import lj.model.install.ViUserEquipment;
import lj.model.install.ViUserProductLine;
import lj.model.maintain.MaintainInfo;
import lj.model.maintain.ViMaintainInfo;
import lj.service.install.ViUserEquipmentService;
import lj.service.install.ViUserProductLineService;
import lj.service.maintain.MaintainInfoService;
import lj.service.maintain.ViMaintainInfoService;
import lj.util.DatatablesReturnObject;
/**
 * 设备维护
 * @author samlv
 *
 */
@Controller
@RequestMapping("maintain")
public class ViMaintainInfoController extends BaseController {
	@Autowired
	private ViMaintainInfoService viMaintainInfoService;
	@Autowired
	private ViUserProductLineService userProductLineService=null;
	@Autowired
	private ViUserEquipmentService userEquipmentService=null;
	@Autowired
	private MaintainInfoService maintainInfoService=null;
	

	@RequestMapping(value = { "/viMaintainInfoPage" }, method = RequestMethod.GET)
	public String viMaintainInfoPage(Model model) {
		ViUserProductLine[] userProductLines=userProductLineService.findAll();
		model.addAttribute("userProductLines", userProductLines);
		return "maintain/viMaintainInfoPage";
	}
	
	@RequestMapping(value = { "/userEquipments" }, method = RequestMethod.GET)
	public @ResponseBody ViUserEquipment[] userEquipments(Long queryUserProductLineId) {
		ViUserEquipment[] objs=userEquipmentService.findAllByUserProductLineId(queryUserProductLineId);
		return objs;
	}
	
	@RequestMapping(value = { "/viMaintainInfo" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<ViMaintainInfo> findAllPaged(String queryUserProductLineName,String queryEquipmentName  ) {
		// 2-获得查询数据
		Pager<ViMaintainInfo> pager = viMaintainInfoService.findAllPagedViMaintainInfo(queryUserProductLineName,queryEquipmentName  );
		// 3-生成返回格式
		DatatablesReturnObject<ViMaintainInfo> obj = new DatatablesReturnObject<ViMaintainInfo>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
	@RequestMapping(value = { "/viMaintainInfo" }, method = RequestMethod.POST)
	public @ResponseBody String insertViMaintainInfo(@RequestBody MaintainInfo obj,HttpServletRequest req)
	{
		Long retId=maintainInfoService.insertMaintainInfo(obj);
		if(retId>0)
			return "";
		else
			return "新增失败";
	}
	
	@RequestMapping(value = { "/viMaintainInfo" }, method = RequestMethod.PUT)
	public @ResponseBody String updateViMaintainInfo(@RequestBody MaintainInfo obj,HttpServletRequest req)
	{
		String msg=maintainInfoService.updateMaintainInfo(obj);
		return msg;
	}
	
	@RequestMapping(value = { "/viMaintainInfo/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteViMaintainInfo(@PathVariable Long id)
	{
		String msg=maintainInfoService.deleteMaintainInfo(id);
		return msg;
	}
	
}
