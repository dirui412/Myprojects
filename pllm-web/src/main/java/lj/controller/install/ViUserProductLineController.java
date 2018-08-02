package lj.controller.install;

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
import lj.model.basic.ProductLineInfo;
import lj.model.install.UserProductLine;
import lj.model.install.ViUserProductLine;
import lj.service.basic.ProductLineInfoService;
import lj.service.install.UserProductLineService;
import lj.service.install.ViUserProductLineService;
import lj.util.DatatablesReturnObject;
/**
 * 生产线选型
 * @author samlv
 *
 */
@Controller
@RequestMapping("install")
public class ViUserProductLineController extends BaseController {
	@Autowired
	private ViUserProductLineService viUserProductLineService;
	
	@Autowired
	private ProductLineInfoService productLineSerice=null;
	
	@Autowired
	private UserProductLineService userProductLineService=null;

	@RequestMapping(value = { "/viUserProductLinePage" }, method = RequestMethod.GET)
	public String viUserProductLinePage(Model model) {
		ProductLineInfo[] productLines=productLineSerice.findAll();
		model.addAttribute("productLines", productLines);
		return "install/viUserProductLinePage";
	}
	
	@RequestMapping(value = { "/viUserProductLine" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<ViUserProductLine> findAllPaged(String queryUserProductLineName,HttpServletRequest req) {
		/*
		 * Attention:显示哪些用户生产线是根据用户组ID筛选的（同一个用户组即为一个公司）
		 * */
		Long loginGroupId=this.getLoginUser(req).getGroupId();
		// 2-获得查询数据
		Pager<ViUserProductLine> pager = viUserProductLineService.findAllPagedViUserProductLine(queryUserProductLineName,loginGroupId);
		// 3-生成返回格式
		DatatablesReturnObject<ViUserProductLine> obj = new DatatablesReturnObject<ViUserProductLine>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
	@RequestMapping(value = { "/viUserProductLine" }, method = RequestMethod.POST)
	public @ResponseBody String insertViUserProductLine(@RequestBody UserProductLine obj,HttpServletRequest req)
	{
		/*
		 * Attention:插入新的用户生产线时，groupId,lectotypeUserId是根据当前注册用户获得的（同一个用户组即为一个公司）
		 * */
		long loginUserId = BaseController.getLoginUser(req).getUserId();
		long loginGroupId= BaseController.getLoginUser(req).getGroupId();
		obj.setGroupId(loginGroupId);obj.setLectotypeUserId(loginUserId);
		Long retId=userProductLineService.insertUserProductLine(obj);
		if(retId>0)
			return "";
		else
			return "新增失败";
	}
	
	@RequestMapping(value = { "/viUserProductLine" }, method = RequestMethod.PUT)
	public @ResponseBody String updateViUserProductLine(@RequestBody UserProductLine obj,HttpServletRequest req)
	{
		/*
		 * Attention:更新的用户生产线时，lectotypeUserId是根据当前注册用户获得的（同一个用户组即为一个公司）
		 * */
		long loginUserId = BaseController.getLoginUser(req).getUserId();
		long loginGroupId= BaseController.getLoginUser(req).getGroupId();
		obj.setGroupId(loginGroupId);obj.setLectotypeUserId(loginUserId);
		String msg=userProductLineService.updateUserProductLine(obj);
		return msg;
	}
	
	@RequestMapping(value = { "/viUserProductLine/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteViUserProductLine(@PathVariable Long id)
	{
		UserProductLine obj=userProductLineService.find(id);
		String msg=userProductLineService.deleteUserProductLine(id);
		return msg;
	}
	
	@RequestMapping(value = { "/confirmLectoType/{id}" }, method = RequestMethod.POST)
	public @ResponseBody String confirmLectoType(@PathVariable Long id)
	{
		boolean ret=userProductLineService.confirmLectoType(id);
		if(ret==true)
			return "";
		else
			return "选型失败";
	}
	
}
