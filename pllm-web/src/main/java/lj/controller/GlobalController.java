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
import lj.model.basic.ViProductLineEquipment;
import lj.model.install.LinkDataArray;
import lj.model.install.Linkinfo;
import lj.model.install.ModelData;
import lj.model.install.MyModelData;
import lj.model.install.NodeDataArray;
import lj.model.install.Productlineposition;
import lj.model.install.UserEquipment;
import lj.model.install.UserProductLine;
import lj.model.install.ViUserProductLine;
import lj.model.sys.ViModuleInfo;
import lj.model.user.UserInfo;
import lj.service.basic.ViProductLineEquipmentService;
import lj.service.install.LinkinfoService;
import lj.service.install.ProductlinepositionService;
import lj.service.install.UserEquipmentService;
import lj.service.install.UserProductLineService;
import lj.service.install.ViUserProductLineService;
import lj.service.sys.SystemService;
import lj.service.user.GroupInfoService;
import lj.service.user.UserInfoService;
import lj.util.JsonUtils;
import lj.util.StringUtils;

@Controller
public class GlobalController extends BaseController {
	@Autowired
	private ViUserProductLineService viUserProductLineService;
	
	@Autowired
	private UserProductLineService userProductLineService;
	
	@Autowired
	private ViProductLineEquipmentService productLineEquipmentService;
	
	@Autowired
	private ProductlinepositionService productlinepositionService;

	@Autowired
	private LinkinfoService linkinfoService;
	
	@Autowired
	private UserEquipmentService userEquipmentService;
	
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
	
	@RequestMapping(value = { "/GeneralChart" }, method = RequestMethod.GET)
	public String GeneralChart(Model model, Long userProductLineId, HttpServletRequest req) {
		//1、获取当前用户的用户生产线至model中
		Long longGroupId = getLoginUser(req).getGroupId();
		ViUserProductLine[] userProductLines = null;
		if (longGroupId.equals(GroupInfoService.GROUP_ID_ADMIN) == true)
			userProductLines = viUserProductLineService.findAll();
		else
			userProductLines = viUserProductLineService.findAllByGroupId(longGroupId);
		model.addAttribute("userProductLines", userProductLines);
		
		//2、将userProductLineId、ViProductLineEquipments、myModelDataStr放入model
		model.addAttribute("userProductLineId", userProductLineId);
		UserProductLine obj = userProductLineService.find(userProductLineId);
		ViProductLineEquipment[] ViProductLineEquipments = productLineEquipmentService
				.findAllByProductLineId(obj.getProductLineId());
		model.addAttribute("ViProductLineEquipments", ViProductLineEquipments);

		MyModelData myModelData = getModelData(userProductLineId); // 调用本类中的getModelData()函数
		String myModelDataStr = JsonUtils.objectToJson(myModelData);
		model.addAttribute("myModelDataStr", myModelDataStr);
		System.out.println("GlobalController/GeneralChart: " + myModelDataStr);
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
	
	public MyModelData getModelData(Long userProductLineId) {
		MyModelData myModelData = new MyModelData();
		myModelData.setLinkFromPortIdProperty("fromPort");
		myModelData.setLinkToPortIdProperty("toPort");

		Productlineposition productlineposition = productlinepositionService.findByUserProductLineId(userProductLineId);
		// 如果该用户生产线不为空
		if (productlineposition != null) {
			// System.out.println(productlineposition.toString());
			ModelData modelData = new ModelData();
			String position = productlineposition.getPosition1() + " " + productlineposition.getPosition2();
			modelData.setPosition(position);
			// System.out.println("1111111111111111111:
			// "+JsonUtils.objectToJson(modelData));
			myModelData.setModelData(modelData);
		}

		Linkinfo[] linkinfos = linkinfoService.findByUserProductLineId(userProductLineId);
		if (linkinfos != null) {
			// System.out.println( Arrays.toString(linkinfos));
			int linkCount = linkinfos.length;
			LinkDataArray[] linkDataArray = new LinkDataArray[linkCount];
			for (int i = 0; i < linkCount; i++) {
				linkDataArray[i] = new LinkDataArray();
				linkDataArray[i].setFrom(linkinfos[i].getFromKey());
				linkDataArray[i].setTo(linkinfos[i].getToKey());
				linkDataArray[i].setFromPort("" + linkinfos[i].getFromPort());
				linkDataArray[i].setToPort("" + linkinfos[i].getToPort());
				String[] points = new String[12];
				points[0] = "" + linkinfos[i].getPoint1();
				points[1] = "" + linkinfos[i].getPoint2();
				points[2] = "" + linkinfos[i].getPoint3();
				points[3] = "" + linkinfos[i].getPoint4();
				points[4] = "" + linkinfos[i].getPoint5();
				points[5] = "" + linkinfos[i].getPoint6();
				points[6] = "" + linkinfos[i].getPoint7();
				points[7] = "" + linkinfos[i].getPoint8();
				points[8] = "" + linkinfos[i].getPoint9();
				points[9] = "" + linkinfos[i].getPoint10();
				points[10] = "" + linkinfos[i].getPoint11();
				points[11] = "" + linkinfos[i].getPoint12();
				linkDataArray[i].setPoints(points);
			}
			myModelData.setLinkDataArray(linkDataArray);
		}
		UserEquipment[] userequipments = userEquipmentService.findByUserProductLineId(userProductLineId);
		if (userequipments != null) {
			// System.out.println( Arrays.toString(userequipments));
			int nodeCount = userequipments.length;
			NodeDataArray[] nodeDataArray = new NodeDataArray[nodeCount];
			for (int i = 0; i < nodeCount; i++) {
				nodeDataArray[i] = new NodeDataArray();
				nodeDataArray[i].setText(userequipments[i].getText());
				nodeDataArray[i].setKey(userequipments[i].getKeyy());
				nodeDataArray[i].setHiddenId(""+userequipments[i].getEquipmentId()+" "+userequipments[i].getUserEquipmentId());
				nodeDataArray[i].setLoc(userequipments[i].getLoc1() + " " + userequipments[i].getLoc2());
			}
			myModelData.setNodeDataArray(nodeDataArray);
		}
		// String myModelDataStr = myModelData.toString();
		// System.out.println(myModelDataStr);
		return myModelData;
	}
}
