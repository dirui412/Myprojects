package lj.controller.collect;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lj.controller.BaseController;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.collect.Viequipmentcollect;
import lj.model.install.UserProductLine;
import lj.service.collect.ViequipmentcollectService;
import lj.service.install.UserProductLineService;
import lj.util.DatatablesReturnObject;

@Controller
@RequestMapping("collect")
public class ViequipmentcollectController extends BaseController {
	@Autowired
	private ViequipmentcollectService viequipmentcollectService;

	@Autowired
	private UserProductLineService userProductLineService;
	
	@RequestMapping(value = { "/viequipmentcollectPage" }, method = RequestMethod.GET)
	public String viequipmentcollectPage(Model model,HttpServletRequest req) {
		Long loginGroupId=this.getLoginUser(req).getGroupId();
		UserProductLine[] userProductLines = userProductLineService.findAllByGroupId(loginGroupId);
		model.addAttribute("userProductLines", userProductLines);
		return "collect/viequipmentcollectPage";
	}
	
	@RequestMapping(value = { "/viequipmentcollect" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<Viequipmentcollect> findAllPaged(  String queryUserProductLineName , String queryEquipmentName,String queryParamName ) {
		// 2-获得查询数据
		Pager<Viequipmentcollect> pager = viequipmentcollectService.findAllPagedViequipmentcollect(queryUserProductLineName , queryEquipmentName , queryParamName);
		// 3-生成返回格式
		DatatablesReturnObject<Viequipmentcollect> obj = new DatatablesReturnObject<Viequipmentcollect>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
}
