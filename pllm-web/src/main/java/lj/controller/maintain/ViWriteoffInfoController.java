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
import lj.model.maintain.ViWriteoffInfo;
import lj.model.maintain.WriteoffInfo;
import lj.service.install.ViUserProductLineService;
import lj.service.maintain.ViWriteoffInfoService;
import lj.service.maintain.WriteoffInfoService;
import lj.util.DatatablesReturnObject;

@Controller
@RequestMapping("maintain")
public class ViWriteoffInfoController extends BaseController {
	@Autowired
	private ViWriteoffInfoService viWriteoffInfoService;
	@Autowired
	private ViUserProductLineService userProductLineService=null;
	@Autowired
	private WriteoffInfoService writeoffInfoService=null;

	@RequestMapping(value = { "/viWriteoffInfoPage" }, method = RequestMethod.GET)
	public String viWriteoffInfoPage(Model model) {
		ViUserProductLine[] userProductLines=userProductLineService.findAll();
		model.addAttribute("userProductLines", userProductLines);
		return "maintain/viWriteoffInfoPage";
	}
	
	@RequestMapping(value = { "/viWriteoffInfo" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<ViWriteoffInfo> findAllPaged(String queryUserProductLineName , String queryEquipmentName  ) {
		// 2-获得查询数据
		Pager<ViWriteoffInfo> pager = viWriteoffInfoService.findAllPagedViWriteoffInfo(queryUserProductLineName , queryEquipmentName  );
		// 3-生成返回格式
		DatatablesReturnObject<ViWriteoffInfo> obj = new DatatablesReturnObject<ViWriteoffInfo>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
	@RequestMapping(value = { "/viWriteoffInfo" }, method = RequestMethod.POST)
	public @ResponseBody String insertViWriteoffInfo(@RequestBody WriteoffInfo obj,HttpServletRequest req)
	{
		Long retId=writeoffInfoService.insertWriteoffInfo(obj);
		if(retId>0)
			return "";
		else
			return "新增失败";
	}
	
	@RequestMapping(value = { "/viWriteoffInfo" }, method = RequestMethod.PUT)
	public @ResponseBody String updateViWriteoffInfo(@RequestBody WriteoffInfo obj,HttpServletRequest req)
	{
		String msg=writeoffInfoService.updateWriteoffInfo(obj);
		return msg;
	}
	
	@RequestMapping(value = { "/viWriteoffInfo/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteViWriteoffInfo(@PathVariable Long id)
	{
		String msg=writeoffInfoService.deleteWriteoffInfo(id);
		return msg;
	}
	
}
