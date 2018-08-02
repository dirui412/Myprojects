package lj.dao.query;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lj.dao.base.JdbcDao;
import lj.dao.basic.IProductLineParamDao;
import lj.model.base.ChartStringDoublePoint;
import lj.model.basic.ProductLineParam;
import lj.model.collect.ViProductLineCollect;
import lj.util.StringUtils;

@Repository
public class ViProductLineCollectQueryDao extends JdbcDao<ChartStringDoublePoint> {

	@Autowired
	private IProductLineParamDao productLineParamDao = null;

	public ProductLineParam[] findAllProductLineParams(Long userProductLineId) {
		// 1-查询所有用户生产线参数
		String sql = "select distinct productLineParamId from ProductLineCollect where userProductLineId="
				+ userProductLineId;
		List<Object> productLineParamIds = this.queryForList(sql, new HashMap<String, Object>());

		ProductLineParam[] objs = new ProductLineParam[productLineParamIds.size()];
		for (int i = 0; i < productLineParamIds.size(); ++i) {
			Long id = (Long) productLineParamIds.get(i);
			objs[i] = productLineParamDao.find(id);
		}
		return objs;
	}

	/**
	 * 
	 * @param groupId
	 * @param userProductLineId
	 * @return
	 */
	public ChartStringDoublePoint[] findAll24Hour(Long userProductLineId, Long productLineParamId) {
		Date nowTime = this.getNowTime();
		Date lastDay = new Date(nowTime.getTime() - 60 * 60 * 24 * 1000);
		String sql = "select DATE_FORMAT(collectTime,'%d-%H') as xValue, sum(paramValue) as yValue "
				+ " from viproductlinecollect " + " where userProductLineId=" + userProductLineId
				+ " and productLineParamId=" + productLineParamId + " and collectTime>='"
				+ StringUtils.timeToString(lastDay, StringUtils.DATETIME_FORMAT) + "' " + " and collectTime<'"
				+ StringUtils.timeToString(nowTime, StringUtils.DATETIME_FORMAT) + "' "
				+ " and paramValue is not null group by xValue "
				+ " union select DATE_FORMAT(collectTime,'%d-%H') as xValue, sum(paramState) as yValue "
				+ " from viproductlinecollect " + " where userProductLineId=" + userProductLineId
				+ " and productLineParamId=" + productLineParamId + " and collectTime>='"
				+ StringUtils.timeToString(lastDay, StringUtils.DATETIME_FORMAT) + "' " + " and collectTime<'"
				+ StringUtils.timeToString(nowTime, StringUtils.DATETIME_FORMAT) + "' "
				+ " and paramState is not null group by xValue order by xValue ";
		System.out.println("ViProductLineCollectQueryDao findAll24Hour:" + sql);
		List<ChartStringDoublePoint> list = this.findAll(sql);
		return list.toArray(new ChartStringDoublePoint[0]);
	}
}
