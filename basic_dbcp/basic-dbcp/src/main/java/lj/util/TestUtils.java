package lj.util;

import java.util.Date;
import java.util.Random;

/**
 * 测试工具类
 * 
 * @author samlv
 *
 */
public class TestUtils {
	private final static long TEST_SEED = (new Date()).getTime();
	private static Random ra;

	static {
		ra = new Random(TEST_SEED);
	}

	/**
	 * 是否异常
	 * 
	 * @return
	 */
	public static boolean isAbnormal() {
		int ret = ra.nextInt(10);
		if (ret == 9)
			return true;
		else
			return false;
	}

	public static Double generateTestDouble(Double from, Double to) {

		Double retDouble = from + (to - from) * ra.nextDouble();
		return retDouble;
	}

	public static Integer generateTestInt(Integer from, Integer to) {
		Integer retInteger = from + (int)((to - from) *  ra.nextDouble());
		return retInteger;
	}

	public static Boolean generateTestBoolean() {

		Boolean retBoolean = ra.nextBoolean();
		return retBoolean;
	}

}
