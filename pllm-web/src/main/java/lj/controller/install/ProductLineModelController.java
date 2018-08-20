package lj.controller.install;

import java.util.Arrays;

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
import lj.model.basic.ViProductLineEquipment;
import lj.model.install.LinkDataArray;
import lj.model.install.Linkinfo;
import lj.model.install.ModelData;
import lj.model.install.MyModelData;
import lj.model.install.NodeDataArray;
import lj.model.install.Productlineposition;
import lj.model.install.UserEquipment;
import lj.model.install.UserProductLine;
import lj.service.BaseServiceConst;
import lj.service.basic.ViProductLineEquipmentService;
import lj.service.install.LinkinfoService;
import lj.service.install.ProductlinepositionService;
import lj.service.install.UserEquipmentService;
import lj.service.install.UserProductLineService;
import lj.util.JsonUtils;
import lj.util.LogUtils;
import lj.util.StringUtils;

/**
 * 生产线安装
 * @author samlv
 *
 */
@Controller
@RequestMapping("install")
public class ProductLineModelController extends BaseController {
	@Autowired
	private UserEquipmentService userEquipmentService;	
	
	@Autowired
	private ProductlinepositionService productlinepositionService;
	
	@Autowired 
	private LinkinfoService linkinfoService;
	
	@Autowired
	UserProductLineService userProductLineService;
	
	@Autowired
	ViProductLineEquipmentService productLineEquipmentService;
	
	/*
	 *1、 将需要的userProductLineId、ViProductLineEquipments(用于显示platte)、
	 *    productLineState(用于判断是否能够更改模型)、myModelDataStr(用于显示右边模型)存至model中
	 * */
	@RequestMapping(value = { "/productLineModel" }, method = RequestMethod.GET)
	public String viUserEquipmentPage(Model model,Long userProductLineId,HttpServletRequest req) {
		//System.out.println("调用了Controller中install/productLineModel:GET");
		model.addAttribute("userProductLineId", userProductLineId);
		
		UserProductLine obj=userProductLineService.find(userProductLineId);
		String productLineState = obj.getProductLineState();
		ViProductLineEquipment[] ViProductLineEquipments=productLineEquipmentService.findAllByProductLineId(obj.getProductLineId());
		model.addAttribute("ViProductLineEquipments", ViProductLineEquipments);
		model.addAttribute("productLineState", productLineState);
		
		MyModelData myModelData = getModelData(userProductLineId);		//调用本类中的getModelData()函数
		String myModelDataStr = JsonUtils.objectToJson(myModelData);
		model.addAttribute("myModelDataStr", myModelDataStr);
		System.out.println("1111111111111111111111111:"+myModelDataStr);
		//req.setAttribute("userProductLineId", userProductLineId);
		return "install/productLineModel";
	}
	/*
	 * 2、从三张表中查询模型信息，myModelDataStr(用于显示右边模型)
	 * */
	public MyModelData getModelData(Long userProductLineId){
		MyModelData myModelData = new MyModelData();
		myModelData.setLinkFromPortIdProperty("fromPort");
		myModelData.setLinkToPortIdProperty("toPort");
		
		Productlineposition productlineposition = productlinepositionService.findByUserProductLineId(userProductLineId);
		//如果该用户生产线不为空
		if(productlineposition!=null){
			//System.out.println(productlineposition.toString());
			ModelData modelData = new ModelData();
			String position = productlineposition.getPosition1()+" "+productlineposition.getPosition2();
			modelData.setPosition(position);
			//System.out.println("1111111111111111111: "+JsonUtils.objectToJson(modelData));
			myModelData.setModelData(modelData);
		}
		
		Linkinfo[] linkinfos = linkinfoService.findByUserProductLineId(userProductLineId);
		if(linkinfos!=null){
			//System.out.println( Arrays.toString(linkinfos));
			int linkCount = linkinfos.length;
			LinkDataArray[] linkDataArray = new LinkDataArray[linkCount]; 
			for(int i=0; i<linkCount; i++){
				linkDataArray[i] = new LinkDataArray();
				linkDataArray[i].setFrom(linkinfos[i].getFromKey());
				linkDataArray[i].setTo(linkinfos[i].getToKey());
				linkDataArray[i].setFromPort(""+linkinfos[i].getFromPort());
				linkDataArray[i].setToPort(""+linkinfos[i].getToPort());
				String[] points = new String[12];
				points[0]=""+linkinfos[i].getPoint1();
				points[1]=""+linkinfos[i].getPoint2();
				points[2]=""+linkinfos[i].getPoint3();
				points[3]=""+linkinfos[i].getPoint4();
				points[4]=""+linkinfos[i].getPoint5();
				points[5]=""+linkinfos[i].getPoint6();
				points[6]=""+linkinfos[i].getPoint7();
				points[7]=""+linkinfos[i].getPoint8();
				points[8]=""+linkinfos[i].getPoint9();
				points[9]=""+linkinfos[i].getPoint10();
				points[10]=""+linkinfos[i].getPoint11();
				points[11]=""+linkinfos[i].getPoint12();
				linkDataArray[i].setPoints(points);
			}
			myModelData.setLinkDataArray(linkDataArray);
		}	
		UserEquipment[] userequipments = userEquipmentService.findByUserProductLineId(userProductLineId);
		if(userequipments!=null){
			//System.out.println( Arrays.toString(userequipments));
			int nodeCount = userequipments.length;
			NodeDataArray[] nodeDataArray = new NodeDataArray[nodeCount];
			for(int i=0; i<nodeCount; i++){
				nodeDataArray[i] = new NodeDataArray();
				nodeDataArray[i].setText(userequipments[i].getText());
				nodeDataArray[i].setKey(userequipments[i].getKeyy());
				nodeDataArray[i].setHiddenId(""+userequipments[i].getEquipmentId()+" "+userequipments[i].getUserEquipmentId());
				nodeDataArray[i].setLoc(userequipments[i].getLoc1()+" "+userequipments[i].getLoc2());
			}
			myModelData.setNodeDataArray(nodeDataArray);
		}
		//String myModelDataStr = myModelData.toString();
		//System.out.println(myModelDataStr);
		return myModelData;
	}
	
	/*
	 * 3.1 Save模型时保存信息值productlineposition表
	 * */
	@RequestMapping(value = { "/productlineposition/post" }, method = RequestMethod.POST)
	public @ResponseBody String insertProductlineposition(@RequestBody Productlineposition productlineposition,HttpServletRequest req) {
		System.out.println("调用了Controller中install/productLineModel/post:POST"
				+" productlineposition.getProductLinePositionId(): "+productlineposition.getProductLinePositionId()
				+" productlineposition.getUserProductLineId(): "+productlineposition.getUserProductLineId()
				+" productlineposition.getPosition1(): "+productlineposition.getPosition1()
				+" productlineposition.getPosition2(): "+productlineposition.getPosition2());
		String msg = StringUtils.STR_EMPTY;
		if(productlineposition!=null){
			Long userProductLineId = productlineposition.getUserProductLineId();
			productlinepositionService.deleteByuserProductLineId(userProductLineId);
			try {
					if(productlineposition.getProductLinePositionId() == null){
						Long retId=productlinepositionService.insertProductlineposition(productlineposition);
						if(retId<=0)
							msg = "新增失败";
					}
					else{//更新有错误！！！getUserEquipmentId值为空
						String msg1=productlinepositionService.updateProductlineposition(productlineposition);
						if(msg1=="更新失败")
							msg=msg1;
					}
			} catch (Exception e) {
				msg = BaseServiceConst.MSG_POST_ERROR;
				e.printStackTrace();
				LogUtils.logError("/linkinfo/post:", e);
			}
		}
		return msg;
	}
	
	/*
	 * 3.2 Save模型时保存信息值linkinfo表
	 * */
	@RequestMapping(value = { "/linkinfo/post" }, method = RequestMethod.POST)
	public @ResponseBody String insertLinkinfos(@RequestBody Linkinfo[] linkinfos,HttpServletRequest req) {
		for(int i=0; i<linkinfos.length; i++){
			System.out.println("调用了Controller中install/productLineModel/post,POST:"
					+" linkinfos[" +i+ "].getUserProductLineId(): "+linkinfos[i].getUserProductLineId()
					+" linkinfos[" +i+ "].getFromKey(): "+linkinfos[i].getFromKey()
					+" linkinfos[" +i+ "].getToPort(): "+linkinfos[i].getToPort()
					+" linkinfos[" +i+ "].getPoint1(): "+linkinfos[i].getPoint1());
		}
		String msg = StringUtils.STR_EMPTY;
		if(linkinfos!=null){
			Long userProductLineId = linkinfos[0].getUserProductLineId();
			linkinfoService.deleteByuserProductLineId(userProductLineId);
			try {
				for(int i=0; i<linkinfos.length; i++){
					if(linkinfos[i].getLinkId()==null){
						Long retId=linkinfoService.insertLinkinfo(linkinfos[i]);
						if(retId<=0)
							msg = "新增失败";
					}
					else{//更新有错误！！！getUserEquipmentId值为空
						String msg1=linkinfoService.updateLinkinfo(linkinfos[i]);
						if(msg1=="更新失败")
							msg=msg1;
					}
				}
			} catch (Exception e) {
				msg = BaseServiceConst.MSG_POST_ERROR;
				e.printStackTrace();
				LogUtils.logError("/linkinfo/post:", e);
			}
		}
		return msg;
	}
	
	/*
	 * 3.3 Save模型时保存信息值userequipment表
	 * */
	@RequestMapping(value = { "/userequipment/post" }, method = RequestMethod.POST)
	public @ResponseBody String insertUserequipment(@RequestBody UserEquipment[] userequipments,HttpServletRequest req) {
		for(int i=0; i<userequipments.length; i++){
			System.out.println("调用了Controller中install/userequipment/post,POST:"
					+" userequipments[" +i+ "].getUserEquipmentId(): " + userequipments[i].getUserEquipmentId()
					+" userequipments[" +i+ "].getUserProductLineId(): "+userequipments[i].getUserProductLineId()
					+" userequipments[" +i+ "].getEquipmentId():"+ userequipments[i].getEquipmentId()
					+" userequipments[" +i+ "].getText(): "+userequipments[i].getText()
					+" userequipments[" +i+ "].getKeyy(): "+userequipments[i].getKeyy()
					+" userequipments[" +i+ "].getLoc1(): "+userequipments[i].getLoc1()
					+" userequipments[" +i+ "].getLoc2(): "+userequipments[i].getLoc2());
		}
		String msg = StringUtils.STR_EMPTY;
		if(userequipments!=null){
			Long userProductLineId = userequipments[0].getUserProductLineId();
			userEquipmentService.deleteByUserProductLineId(userProductLineId);
			try {
				for(int i=0; i<userequipments.length; i++){
					if(userequipments[i].getUserEquipmentId()==null){
						Long retId=userEquipmentService.insertUserEquipment(userequipments[i]);	//插入设备，设备改为“已安装”
						if(retId<=0)
							msg = "新增失败";
						userEquipmentService.installEquipment(userequipments[i]); //安装设备，生产线改成“已安装”
					}
					else{//更新有错误！！！getUserEquipmentId值为空
						String msg1=userEquipmentService.updateUserEquipment(userequipments[i]);
						if(msg1=="更新失败")
							msg=msg1;
					}
				}
			} catch (Exception e) {
				msg = BaseServiceConst.MSG_POST_ERROR;
				e.printStackTrace();
				LogUtils.logError("/userequipment/post:", e);
			}
		}
		return msg;
	}
	/*
	 * 3.4 Save信息时，当模型信息为空，则删除该userProductLineId的三张表中信息。
	 * */
	@RequestMapping(value = { "/deleteThisUserProductLine/{userProductLineId}" }, method = RequestMethod.POST)
	public @ResponseBody String deleteThisUserProductLine(@PathVariable Long userProductLineId) {
		productlinepositionService.deleteByuserProductLineId(userProductLineId);
		linkinfoService.deleteByuserProductLineId(userProductLineId);
		userEquipmentService.deleteByUserProductLineId(userProductLineId);
		return "";
	}
}
