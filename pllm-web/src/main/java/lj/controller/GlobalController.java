package lj.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lj.global.AppConst;
import lj.global.AppVar;
import lj.model.sys.ViModuleInfo;
import lj.model.user.UserInfo;
import lj.service.sys.SystemService;
import lj.service.user.UserInfoService;
import lj.util.StringUtils;

@Controller
public class GlobalController extends BaseController {
	
	@Autowired
	private UserInfoService userService = null;
	
	@Autowired
	private SystemService systemService=null;
	
	@RequestMapping(value = { "/test" })
	public String test(Model model) {
		System.out.println("test is requested");
		return "test";
	}

	@RequestMapping(value = { "/" })
	public String index(Model model) {
		System.out.println("index is requested");
		return AppConst.URL_LOGIN;
	}

	@RequestMapping(value = { "/"+AppConst.URL_LOGIN }, method = RequestMethod.POST)
	public @ResponseBody String login(HttpServletRequest req,String userCode, String userPassword, Model model) {
		//System.out.println("login userCode:" + userCode + ",userPassword:" + userPassword);
		String msg = userService.login(userCode, userPassword);
		System.out.println("login reponse:" + msg);
		if (StringUtils.isNullOrEmpty(msg) == true){
			//1-将用户信息放入Session
			UserInfo userInfo=userService.findUserInfoByUserCode(userCode);
			req.getSession().setAttribute(AppConst.SESSION_USERINFO
					, userInfo);
			
			return msg;
			//return "main_backup";
		}
		else{
			model.addAttribute("userCode", userCode);
			model.addAttribute("userPassword", userPassword);
			model.addAttribute("errorMessage", msg);
			return msg;
		}
	}
	
	@RequestMapping(value = { "main" })
	public String main(HttpServletRequest req,Model model) {
		//1-读入用户模块信息
		long loginUserId = BaseController.getLoginUser(req).getUserId();
		ViModuleInfo[] parentModules=userService.findAllUserModules(loginUserId);
		model.addAttribute("parentModules", parentModules);
		return AppConst.URL_MAIN;
	}
	
	@RequestMapping(value = { "/exitSystem" })
	public @ResponseBody String exitSystem(HttpServletRequest req) {
		//1-清空Session
		req.getSession().invalidate();
		//2-退出系统
		return StringUtils.STR_EMPTY;
	}
	
	@RequestMapping(value = { "/download" })
	public String download(HttpServletRequest req) {
		return AppConst.URL_DOWNLOAD;
	}
	
	@RequestMapping(value = { "/GeneralChart" })
	public String GeneralChart(HttpServletRequest req) {
		
		return "GeneralChart";
	}
	
	@RequestMapping("/downloadFile")
	public String downloadFile(String fileName, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ fileName);
		try {
			String path =AppVar.appPath+File.separator+AppConst.PATH_DOWNLOAD;
			System.out.println("downloadFile path:"+path);
			System.out.println("downloadFile fileName:"+fileName);
			InputStream inputStream = new FileInputStream(new File(path
					+ File.separator +fileName));
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			 // 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        //  返回值要注意，要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
		return fileName;
	}
}
