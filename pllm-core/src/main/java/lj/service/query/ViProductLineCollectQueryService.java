package lj.service.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.query.ViProductLineCollectQueryDao;
import lj.model.base.ChartStringDoublePoint;
import lj.model.basic.ProductLineParam;
import lj.model.collect.ViProductLineCollect;
import lj.service.BaseService;

@Service
public class ViProductLineCollectQueryService extends BaseService {
	
	@Autowired
	private ViProductLineCollectQueryDao viUserProductLineCollectDao=null;
	
	/**
	 * 查询用户生产线参数数组
	 * @param userProductLineId
	 * @return
	 */
	public ProductLineParam[] findAllProductLineParams(Long userProductLineId){
		ProductLineParam[] productLineParamIds=viUserProductLineCollectDao.findAllProductLineParams(userProductLineId);
		return productLineParamIds;
	}
	
	/**
	 * 生成用户生产线参数统计数组
	 * @param userProductLineId
	 * @return
	 */
	public ChartStringDoublePoint[][] findAll24Hour(ProductLineParam[] produceLineParams,Long userProductLineId)
	{
		ChartStringDoublePoint[][] objs=new ChartStringDoublePoint[produceLineParams.length][];
	    
		//2-生产用户生产线参数统计数据
		for(int i=0;i<produceLineParams.length;++i)
		{
			Long productLineParamId=produceLineParams[i].getProductLineParamId();
			objs[i]=viUserProductLineCollectDao.findAll24Hour(userProductLineId,productLineParamId);
		}
    	return objs;
	}
	
	public ChartStringDoublePoint[] find24Hour(Long userProductLineId,Long productLineParamId)
	{
		ChartStringDoublePoint[] objs=viUserProductLineCollectDao.findAll24Hour(userProductLineId,productLineParamId);
    	return objs;
	}
}
