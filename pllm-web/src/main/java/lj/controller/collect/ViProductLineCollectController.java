package lj.controller.collect;

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
import lj.model.collect.ViProductLineCollect;
import lj.model.install.UserProductLine;
import lj.service.collect.ViProductLineCollectService;
import lj.service.install.UserProductLineService;
import lj.service.user.GroupInfoService;
import lj.util.DatatablesReturnObject;

@Controller
@RequestMapping("collect")
public class ViProductLineCollectController extends BaseController {
	@Autowired
	private ViProductLineCollectService viProductLineCollectService;
	
	@Autowired
	private UserProductLineService userProductLineService;

	@RequestMapping(value = { "/viProductLineCollectPage" }, method = RequestMethod.GET)
	public String viProductLineCollectPage(Model model,HttpServletRequest req) {
		Long loginGroupId=this.getLoginUser(req).getGroupId();
		if(loginGroupId.equals(GroupInfoService.GROUP_ID_ADMIN)==false)
			model.addAttribute("groupId", loginGroupId);
		UserProductLine[] userProductLines = userProductLineService.findAllByGroupId(loginGroupId);
		model.addAttribute("userProductLines", userProductLines);
		return "collect/viProductLineCollectPage";
	}
	
	@RequestMapping(value = { "/viProductLineCollect" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<ViProductLineCollect> findAllPaged(
			Long queryGroupId , String queryUserProductLineName , String queryParamName  ) {
		// 2-获得查询数据
		Pager<ViProductLineCollect> pager = viProductLineCollectService.findAllPagedViProductLineCollect(queryGroupId , queryUserProductLineName , queryParamName  );
		// 3-生成返回格式
		DatatablesReturnObject<ViProductLineCollect> obj = new DatatablesReturnObject<ViProductLineCollect>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
	@RequestMapping(value = { "/viProductLineCollect" }, method = RequestMethod.POST)
	public @ResponseBody String insertViProductLineCollect(@RequestBody ViProductLineCollect obj,HttpServletRequest req)
	{
		Long retId=viProductLineCollectService.insertViProductLineCollect(obj);
		if(retId>0)
			return "";
		else
			return "新增失败";
	}
	
	@RequestMapping(value = { "/viProductLineCollect" }, method = RequestMethod.PUT)
	public @ResponseBody String updateViProductLineCollect(@RequestBody ViProductLineCollect obj,HttpServletRequest req)
	{
		String msg=viProductLineCollectService.updateViProductLineCollect(obj);
		return msg;
	}
	
	@RequestMapping(value = { "/viProductLineCollect/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteViProductLineCollect(@PathVariable Long id)
	{
		String msg=viProductLineCollectService.deleteViProductLineCollect(id);
		return msg;
	}
	
}
