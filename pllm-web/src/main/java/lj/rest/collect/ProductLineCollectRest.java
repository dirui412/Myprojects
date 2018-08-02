package lj.rest.collect;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lj.model.basic.EquipmentParam;
import lj.model.basic.ProductLineParam;
import lj.model.collect.EquipmentCollect;
import lj.model.collect.ProductLineCollect;
import lj.model.install.UserEquipment;
import lj.model.install.UserProductLine;
import lj.service.basic.EquipmentParamService;
import lj.service.basic.ProductLineParamService;
import lj.service.collect.EquipmentCollectService;
import lj.service.collect.ProductLineCollectService;
import lj.service.install.UserEquipmentService;
import lj.service.install.UserProductLineService;
import lj.util.TestUtils;

@Controller
@RequestMapping("rest")
public class ProductLineCollectRest {
	/**
	 * 新增采集信息
	 */
	
	@Autowired
	private UserEquipmentService userEquipmentService;
	
	@Autowired
	private EquipmentParamService equipmentParamService;
	
	@Autowired
	private UserProductLineService userProductLineService;
	
	@Autowired
	private ProductLineParamService productLineParamService;
	
	@Autowired
	ProductLineCollectService productLineCollectService=null;
	
	@Autowired
	EquipmentCollectService equipmentCollectService=null;
	
	@RequestMapping(value = { "/productLineCollect" }, method = RequestMethod.POST)
	public @ResponseBody String insertProductLineParam(@RequestParam(value="userProductLineIds[]") Long[] userProductLineIds)
	{
		/*
		 * 1.1、根据用户生产线标识获取用户生产线
		 * */
		//System.out.println("进入rest/productLineCollect函数insertProductLineParam");
		for(int num=0; num<userProductLineIds.length; num++){
			//System.out.println(userProductLineIds[num]+userProductLineService.toString());
			UserProductLine UserProductLine = userProductLineService.find(userProductLineIds[num]);
			Long productLineId = UserProductLine.getProductLineId();		//productLineId
			ProductLineParam[]  productLineParams= productLineParamService.findAllByProdunctLineId(productLineId);
			for(int i=0; i<productLineParams.length; i++){
				Long productLineParamId = productLineParams[i].getProductLineParamId();		//productLineParamId
				String paramType = productLineParams[i].getParamType();						//paramType
				Double paramDown = productLineParams[i].getParamDown();						//paramDown
				Double paramUp = productLineParams[i].getParamUp();							//paramUp
				Date nowTime=new Date();
				Date lastDay=new Date(nowTime.getTime()-60*60*24*1000);
				System.out.println("111111111111-insertProductLineParam-paramType:"+paramType);
				for(long t=lastDay.getTime();t<nowTime.getTime();t=t+6*60*60*1000)	//循环24次 24小时
				{
					ProductLineCollect collect;
					if(paramType.equals("模拟量")){
						Double paramValue=TestUtils.generateTestDouble(paramDown,paramUp);		//生成0-220之前的随机数
						collect=new ProductLineCollect(null,userProductLineIds[num],productLineParamId,new Date(t),null,paramValue);
					}
					else{
						int paramTypeInt=TestUtils.generateTestInt(new Integer(paramDown.intValue()),
								new Integer(paramUp.intValue()));
						collect=new ProductLineCollect(null,userProductLineIds[num],productLineParamId,new Date(t),paramTypeInt,null);
					}
					productLineCollectService.insertProductLineCollect(collect);
				}
			}
		}
		return "";
	}
	
	@RequestMapping(value = { "/equipmentCollect" }, method = RequestMethod.POST)
	public @ResponseBody String insertEquipmentParam(@RequestParam(value="userProductLineIds[]") Long[] userProductLineIds)
	{
		/*
		 * 2.1、根据用户生产线标识获取用户设备组
		 * */
		for(int num=0; num<userProductLineIds.length; num++){
			UserEquipment[] userEquipments= userEquipmentService.findByUserProductLineId(userProductLineIds[num]);
			System.out.println("userEquipments.length"+userEquipments.length);
			for(int i=0; i<userEquipments.length; i++){
				/*
				 * 2.2、对每一个用户设备得到其设备参数数组
				 * --(null,userEquipmentId,equipmentParamId,collectTime,paramType,paramValue	)
				 * */
				Long userEquipmentId = userEquipments[i].getUserEquipmentId();		//userEquipmentId
				Long equipmentId = userEquipments[i].getEquipmentId();		//equipmentId
				EquipmentParam[] equipmentParams  = equipmentParamService.findAllByEquipmentId(equipmentId);
				System.out.println("equipmentParams.length"+equipmentParams.length);
				for(int j=0; j<equipmentParams.length; j++){
					Long equipmentParamId = equipmentParams[j].getEquipmentParamId();	//equipmentParamId
					Double paramUp = equipmentParams[j].getParamUp();					//paramUp
					Double paramDown = equipmentParams[j].getParamDown();				//paramDown
					String paramType = equipmentParams[j].getParamType();				//paramType
					Date nowTime=new Date();
					Date lastDay=new Date(nowTime.getTime()-60*60*24*1000);
					System.out.println("111111111111-insertEquipmentParam-paramType:"+paramType);
					for(long t=lastDay.getTime();t<nowTime.getTime();t=t+6*60*60*1000)	//循环24次 24小时
					{
						EquipmentCollect collect;
						if(paramType.equals("模拟量")){
							Double paramValue=TestUtils.generateTestDouble(paramDown,paramUp);		//生成0-220之前的随机数
							collect=new EquipmentCollect(null,userEquipmentId,equipmentParamId,new Date(t),null,paramValue);
						}
						else{
							int paramTypeInt=TestUtils.generateTestInt(new Integer(paramDown.intValue()),
									new Integer(paramUp.intValue()));
							collect=new EquipmentCollect(null,userEquipmentId,equipmentParamId,new Date(t),paramTypeInt,null);
						}
						equipmentCollectService.insertEquipmentCollect(collect);
					}
				}
			}
		}
		return "";
	}
}
