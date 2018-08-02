package lj.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lj.model.user.ViUserInfo;
import lj.service.user.UserInfoService;

@Controller
@RequestMapping("rest")
public class UserInfoRest {
	@Autowired
	private UserInfoService userService=null;
	
	@RequestMapping(value = { "/userInfo" }, method = RequestMethod.GET)
	public @ResponseBody ViUserInfo[] getUserInfo()
	{
		ViUserInfo[] users=userService.findUserInfos();
		return users;
	}
	
	@RequestMapping(value = { "/userInfo" }, method = RequestMethod.POST)
	public @ResponseBody ViUserInfo[] postUserInfo(String userName)
	{
		System.out.println("rest success userName:"+userName);
		ViUserInfo[] users=userService.findUserInfos();
		return users;
	}
}
