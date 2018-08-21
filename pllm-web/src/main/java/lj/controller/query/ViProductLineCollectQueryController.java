package lj.controller.query;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lj.controller.BaseController;
import lj.model.base.ChartStringDoublePoint;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.basic.ProductLineParam;
import lj.model.basic.ViProductLineEquipment;
import lj.model.collect.ViProductLineCollect;
import lj.model.install.LinkDataArray;
import lj.model.install.Linkinfo;
import lj.model.install.ModelData;
import lj.model.install.MyModelData;
import lj.model.install.NodeDataArray;
import lj.model.install.Productlineposition;
import lj.model.install.UserEquipment;
import lj.model.install.UserProductLine;
import lj.model.install.ViUserProductLine;
import lj.service.basic.ProductLineParamService;
import lj.service.basic.ViProductLineEquipmentService;
import lj.service.collect.ViProductLineCollectService;
import lj.service.install.LinkinfoService;
import lj.service.install.ProductlinepositionService;
import lj.service.install.UserEquipmentService;
import lj.service.install.UserProductLineService;
import lj.service.install.ViUserProductLineService;
import lj.service.query.ViProductLineCollectQueryService;
import lj.service.user.GroupInfoService;
import lj.util.DatatablesReturnObject;
import lj.util.FlotModel;
import lj.util.FlotUtils;
import lj.util.JsonUtils;

/**
 * 生产线采集查询
 * 
 * @author samlv
 *
 */
@Controller
@RequestMapping("query")
public class ViProductLineCollectQueryController extends BaseController {
	@Autowired
	UserProductLineService userProductLineService;
	@Autowired
	private UserEquipmentService userEquipmentService;
	@Autowired
	ViProductLineEquipmentService productLineEquipmentService;
	@Autowired
	private ProductlinepositionService productlinepositionService;

	@Autowired
	private LinkinfoService linkinfoService;
	@Autowired
	private ViUserProductLineService viUserProductLineService = null;
	@Autowired
	private ViProductLineCollectService viProductLineCollectService = null;
	@Autowired
	private ViProductLineCollectQueryService viProductLineCollectQueryService;
	@Autowired
	private ProductLineParamService productLineParamService = null;

	@RequestMapping(value = { "/viProductLineCollectQueryPage" }, method = RequestMethod.GET)
	public String viProductLineCollectQueryPage(Model model, HttpServletRequest req) {
		Long longGroupId = getLoginUser(req).getGroupId();
		ViUserProductLine[] userProductLines = null;
		if (longGroupId.equals(GroupInfoService.GROUP_ID_ADMIN) == true)
			userProductLines = viUserProductLineService.findAll();
		else
			userProductLines = viUserProductLineService.findAllByGroupId(longGroupId);
		model.addAttribute("userProductLines", userProductLines);
		return "query/viProductLineCollectQueryPage";
	}

	@RequestMapping(value = { "/viProductLineCollectQuery" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<ViProductLineCollect> findAllPaged(Long queryUserProductLineId) {
		// 2-获得查询数据
		Pager<ViProductLineCollect> pager = viProductLineCollectService
				.findAllPagedViProductLineCollect(queryUserProductLineId);
		// 3-生成返回格式
		DatatablesReturnObject<ViProductLineCollect> obj = new DatatablesReturnObject<ViProductLineCollect>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}

	@RequestMapping(value = { "/userProductLineCharts" }, method = RequestMethod.GET)
	public String userProductLineCharts(Model model, Long userProductLineId, HttpServletRequest req) {
		model.addAttribute("userProductLineId", userProductLineId);
		
		UserProductLine obj = userProductLineService.find(userProductLineId);
		ViProductLineEquipment[] ViProductLineEquipments = productLineEquipmentService
				.findAllByProductLineId(obj.getProductLineId());
		model.addAttribute("ViProductLineEquipments", ViProductLineEquipments);

		MyModelData myModelData = getModelData(userProductLineId); // 调用本类中的getModelData()函数
		String myModelDataStr = JsonUtils.objectToJson(myModelData);
		model.addAttribute("myModelDataStr", myModelDataStr);
		System.out.println("ViProductLineCollectQueryController/userProductLineCharts:" + myModelDataStr);
		return "query/userProductLineCharts";
	}

	/**
	 * 查询客户生产线对应的参数
	 * 
	 * @param queryUserProductLineId
	 * @return
	 */
	@RequestMapping(value = { "/userProductLineParams" }, method = RequestMethod.GET)
	public @ResponseBody ProductLineParam[] userProductLineParams(Long queryUserProductLineId) {
		ProductLineParam[] params = viProductLineCollectQueryService.findAllProductLineParams(queryUserProductLineId);
		System.out.println("userProductLineParams:" + JsonUtils.objectToJson(params));
		return params;
	}
/*
	*//**
	 * 图表控制器
	 * 
	 * @param queryUserProductLineId
	 * @return
	 *//*
	@RequestMapping(value = { "/viProductLineCollectChart" }, method = RequestMethod.GET)
	public @ResponseBody FlotModel viProductLineCollectChart(Long queryUserProductLineId,
			Long queryProductLineParamId) {
		ChartStringDoublePoint[] objs = viProductLineCollectQueryService.find24Hour(queryUserProductLineId,
				queryProductLineParamId);
		ProductLineParam param = productLineParamService.find(queryProductLineParamId);
		FlotModel model = FlotUtils.serialsToFlot(new ChartStringDoublePoint[][] { objs },
				new String[] { param.getParamName() }, "xValue", "yValue");
		System.out.println("flot model:" + JsonUtils.objectToJson(model));
		return model;
	}*/

/*	*//**
	 * 图表控制器
	 * 
	 * @param queryUserProductLineId
	 * @return
	 *//*
	@RequestMapping(value = { "/viProductLineCollectChartAll" }, method = RequestMethod.GET)
	public @ResponseBody FlotModel viProductLineCollectChartAll(Long queryUserProductLineId) {
		ProductLineParam[] params = viProductLineCollectQueryService.findAllProductLineParams(queryUserProductLineId);
		ChartStringDoublePoint[][] objs = viProductLineCollectQueryService.findAll24Hour(params,
				queryUserProductLineId);
		String[] paramNames = new String[params.length];
		for (int i = 0; i < params.length; ++i)
			paramNames[i] = params[i].getParamName();

		FlotModel model = FlotUtils.serialsToFlot(objs, paramNames, "xValue", "yValue");
		System.out.println("flot model:" + JsonUtils.objectToJson(model));
		return model;
	}*/

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
