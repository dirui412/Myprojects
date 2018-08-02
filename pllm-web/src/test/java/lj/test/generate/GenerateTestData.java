package lj.test.generate;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import lj.model.basic.EquipmentParam;
import lj.model.basic.ProductLineParam;
import lj.model.collect.EquipmentCollect;
import lj.model.collect.ProductLineCollect;
import lj.model.install.UserEquipment;
import lj.model.install.UserProductLine;
import lj.service.basic.EquipmentParamService;
import lj.service.basic.ProductLineParamService;
import lj.service.install.UserEquipmentService;
import lj.service.install.UserProductLineService;
import lj.util.HttpUtils;
import lj.util.JsonUtils;
import lj.util.SpringUtils;
import lj.util.StringUtils;
import lj.util.TestUtils;

public class GenerateTestData {
	
	/**
	 * 新增采集信息
	 */
	
	@Autowired
	private static UserEquipmentService userEquipmentService;
	
	@Autowired
	private static EquipmentParamService equipmentParamService;
	
	@Autowired
	private static UserProductLineService userProductLineService;
	
	@Autowired
	private static ProductLineParamService productLineParamService;

	public static void generateProductLineCollect(Long userProductLineId) {
		/*
		 * 1.1、根据用户生产线标识获取用户设备组
		 * */
		UserEquipment[] userEquipments= userEquipmentService.findByUserProductLineId(userProductLineId);
		for(int i=0; i<userEquipments.length; i++){
			/*
			 * 1.2、对每一个用户设备得到其设备参数数组
			 * --(null,userEquipmentId,equipmentParamId,collectTime,paramType,paramValue	)
			 * */
			Long userEquipmentId = userEquipments[i].getUserEquipmentId();		//userEquipmentId
			Long equipmentId = userEquipments[i].getEquipmentId();		//equipmentId
			EquipmentParam[] equipmentParams  = equipmentParamService.findAllByEquipmentId(equipmentId);
			for(int j=0; j<equipmentParams.length; j++){
				Long equipmentParamId = equipmentParams[i].getEquipmentParamId();	//equipmentParamId
				Double paramUp = equipmentParams[i].getParamUp();					//paramUp
				Double paramDown = equipmentParams[i].getParamDown();				//paramDown
				String paramType = equipmentParams[i].getParamType();				//paramType
				Date nowTime=new Date();
				Date lastDay=new Date(nowTime.getTime()-60*60*24*1000);
				for(long t=lastDay.getTime();t<nowTime.getTime();t=t+60*60*1000)	//循环24次 24小时
				{
					EquipmentCollect collect;
					if(paramType=="开关量"){
						Double paramValue=TestUtils.generateTestDouble(paramDown,paramUp);		//生成0-220之前的随机数
						collect=new EquipmentCollect(null,userEquipmentId,equipmentParamId,new Date(t),null,paramValue);
						String strJson=JsonUtils.objectToJson(collect,StringUtils.DATETIME_FORMAT);
						String str=HttpUtils.doPostRest("http://localhost:8080/pllm-web/rest/equipmentCollect", strJson);
						System.out.println(new Date(t)+":"+str);
					}
					else{
						int paramTypeInt=TestUtils.generateTestInt(new Integer(paramDown.toString()),
								new Integer(paramDown.toString()));
						collect=new EquipmentCollect(null,userEquipmentId,equipmentParamId,new Date(t),paramTypeInt,null);
						String strJson=JsonUtils.objectToJson(collect,StringUtils.DATETIME_FORMAT);
						String str=HttpUtils.doPostRest("http://localhost:8080/pllm-web/rest/equipmentCollect", strJson);
						System.out.println(new Date(t)+":"+str);
					}
				}
			}
		}
		
		/*
		 * 2.1、根据用户生产线标识获取用户生产线
		 * */
		UserProductLine UserProductLine = userProductLineService.find(userProductLineId);
		Long productLineId = UserProductLine.getProductLineId();		//productLineId
		ProductLineParam[]  productLineParams= productLineParamService.findAllByProdunctLineId(productLineId);
		for(int i=0; i<productLineParams.length; i++){
			Long productLineParamId = productLineParams[i].getProductLineParamId();		//productLineParamId
			String paramType = productLineParams[i].getParamType();						//paramType
			Double paramDown = productLineParams[i].getParamDown();						//paramDown
			Double paramUp = productLineParams[i].getParamUp();							//paramUp
			Date nowTime=new Date();
			Date lastDay=new Date(nowTime.getTime()-60*60*24*1000);
			for(long t=lastDay.getTime();t<nowTime.getTime();t=t+60*60*1000)	//循环24次 24小时
			{
				ProductLineCollect collect;
				if(paramType=="开关量"){
					Double paramValue=TestUtils.generateTestDouble(paramDown,paramUp);		//生成0-220之前的随机数
					collect=new ProductLineCollect(null,userProductLineId,productLineParamId,new Date(t),null,paramValue);
					String strJson=JsonUtils.objectToJson(collect,StringUtils.DATETIME_FORMAT);
					String str=HttpUtils.doPostRest("http://localhost:8080/pllm-web/rest/productLineCollect", strJson);
					System.out.println(new Date(t)+":"+str);
				}
				else{
					int paramTypeInt=TestUtils.generateTestInt(new Integer(paramDown.toString()),
							new Integer(paramDown.toString()));
					collect=new ProductLineCollect(null,userProductLineId,productLineParamId,new Date(t),paramTypeInt,null);
					String strJson=JsonUtils.objectToJson(collect,StringUtils.DATETIME_FORMAT);
					String str=HttpUtils.doPostRest("http://localhost:8080/pllm-web/rest/productLineCollect", strJson);
					System.out.println(new Date(t)+":"+str);
				}
			}
		}
	}

	public static void main(String[] args) {
		SpringUtils.getBeanFactory();
		generateProductLineCollect(41l);
		generateProductLineCollect(42l);
		generateProductLineCollect(43l);
	}

}
