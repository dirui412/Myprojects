package lj.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Float工具类
 * 
 * @author samlv
 *
 */
public class FlotUtils {
	private FlotUtils() {
	}

	/**
	 * Object[][]->Flot Model
	 * 
	 * @param objs
	 * @param xField
	 * @param yField
	 * @return
	 */
	public static FlotModel serialsToFlot(Object[][] objss, String[] serialLabels, String xField, String yField) {
		FlotSerial[] serials = new FlotSerial[objss.length];
		Map<Object, Object> xTickMap = new LinkedHashMap<Object, Object>();
		Map<Object, Object> yTickMap = new LinkedHashMap<Object, Object>();

		// 1-逐个Serial产生
		try {
			for (int i = 0; i < objss.length; ++i) {
				// 1.1=初始序列数据
				Object[][] data = new Object[objss[i].length][2];
				// 1.2-逐个序列生成数据
				for (int j = 0; j < objss[i].length; ++j) {
					Object obj = objss[i][j];
					Field field = obj.getClass().getDeclaredField(xField);
					field.setAccessible(true);
					data[j][0] = field.get(obj);
					if (data[j][0] instanceof Integer || data[j][0] instanceof Double || data[j][0] instanceof Float)
					{
						xTickMap.put(data[j][0], data[j][0]);
					}
					else
					{
						xTickMap.put(j, data[j][0]);
						data[j][0] = j;
					}
					field = obj.getClass().getDeclaredField(yField);
					field.setAccessible(true);
					data[j][1] = field.get(obj);
					if (data[j][1] instanceof Integer || data[j][1] instanceof Double || data[j][1] instanceof Float)
					{
						
						yTickMap.put(data[j][1], data[j][1]);
					}
					else
					{
						yTickMap.put(j, data[j][1]);
						data[j][1] = j;
					}
				}
				// 1.3-生成序列模型
				serials[i] = new FlotSerial(serialLabels[i], data);
			}
		} catch (Exception e) {
			LogUtils.logError("转成Flot数据模型异常", e);
			e.printStackTrace();
		}

		// 2-生成刻度
		Object[][] xTicks = new Object[xTickMap.size()][2];
		int index = 0;
		for (Entry<Object, Object> entry : xTickMap.entrySet()) {
			xTicks[index][0] = entry.getKey();
			xTicks[index][1] = entry.getValue();
			index++;
		}
		Object[][] yTicks = new Object[yTickMap.size()][2];
		index = 0;
		for (Entry<Object, Object> entry : yTickMap.entrySet()) {
			yTicks[index][0] = entry.getKey();
			yTicks[index][1] = entry.getValue();
			index++;
		}

		// 3-生成Float
		FlotModel model = new FlotModel(serials, xTicks, yTicks);
		return model;
	}
}
