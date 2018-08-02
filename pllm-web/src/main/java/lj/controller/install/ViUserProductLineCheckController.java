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
import lj.model.install.CheckInfo;
import lj.model.install.ViUserProductLineCheck;
import lj.service.install.UserProductLineService;
import lj.service.install.ViUserProductLineCheckService;
import lj.util.DatatablesReturnObject;
/**
 * 生产线验收
 * @author samlv
 *
 */
@Controller
@RequestMapping("install")
public class ViUserProductLineCheckController extends BaseController {
	@Autowired
	private ViUserProductLineCheckService viUserProductLineCheckService;
	@Autowired
	private UserProductLineService userProductLineService=null;

	@RequestMapping(value = { "/viUserProductLineCheckPage" }, method = RequestMethod.GET)
	public String viUserProductLineCheckPage(Model model,HttpServletRequest req) {
		Long loginGroupId=this.getLoginUser(req).getGroupId();
		model.addAttribute("groupId", loginGroupId);
		return "install/viUserProductLineCheckPage";
	}
	
	/*
	 * 根据 组名 显示viUserProductLineCheck（在验收界面显示），查询时过滤：已安装&已验收
	 * */
	@RequestMapping(value = { "/viUserProductLineCheck" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<ViUserProductLineCheck> findAllPaged(Long queryGroupId , String queryUserProductLineName  ) {
		// 2-获得查询数据
		Pager<ViUserProductLineCheck> pager = viUserProductLineCheckService.findAllPagedViUserProductLineCheck(queryGroupId , queryUserProductLineName  );
		// 3-生成返回格式
		DatatablesReturnObject<ViUserProductLineCheck> obj = new DatatablesReturnObject<ViUserProductLineCheck>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
	@RequestMapping(value = { "/checkUserProductLine" }, method = RequestMethod.PUT)
	public @ResponseBody String checkUserProductLine(@RequestBody ViUserProductLineCheck obj,HttpServletRequest req)
	{
		Long loginUserId=this.getLoginUser(req).getUserId();
		obj.setCheckUserId(loginUserId);
		boolean ret=userProductLineService.checkUserProductLine(obj);
		if(ret==true)
			return "";
		else
			return "提交验收失败";
	}
}
