package lj.controller.user;

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
import lj.model.user.GroupInfo;
import lj.service.user.GroupInfoService;
import lj.util.DatatablesReturnObject;

@Controller
@RequestMapping("user")
public class GroupInfoController extends BaseController {
	@Autowired
	private GroupInfoService groupInfoService;

	@RequestMapping(value = { "/groupInfoPage" }, method = RequestMethod.GET)
	public String groupInfoPage(Model model) {
		return "user/groupInfoPage";
	}
	
	@RequestMapping(value = { "/groupInfo" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<GroupInfo> findAllPaged() {
		// 2-获得查询数据
		Pager<GroupInfo> pager = groupInfoService.findAllPagedGroupInfo();
		// 3-生成返回格式
		DatatablesReturnObject<GroupInfo> obj = new DatatablesReturnObject<GroupInfo>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
	@RequestMapping(value = { "/groupInfo" }, method = RequestMethod.POST)
	public @ResponseBody String insertGroupInfo(@RequestBody GroupInfo obj,HttpServletRequest req)
	{
		Long retId=groupInfoService.insertGroupInfo(obj);
		if(retId>0)
			return "";
		else
			return "新增失败";
	}
	
	@RequestMapping(value = { "/groupInfo" }, method = RequestMethod.PUT)
	public @ResponseBody String updateGroupInfo(@RequestBody GroupInfo obj,HttpServletRequest req)
	{
		String msg=groupInfoService.updateGroupInfo(obj);
		return msg;
	}
	
	@RequestMapping(value = { "/groupInfo/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteGroupInfo(@PathVariable Long id)
	{
		String msg=groupInfoService.deleteGroupInfo(id);
		return msg;
	}
	
}
