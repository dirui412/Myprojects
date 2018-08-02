package lj.global;

import java.util.Random;
import lj.util.IntUtils;
import lj.util.StringUtils;

/**
 * 应用程序静态方法
 * 
 * @author samlv
 *
 */
public class PlatformFun {

	/*
	 * 预装载
	 */
	public static int preLoad() {
		// 1-保存运行路径
		PlatformVar.platformPath = System.getProperty("user.dir");
		return IntUtils.INT_SUCCESS;
	}

	/**
	 * 产生随机验证码
	 * 
	 * @return
	 */
	public static String generateIdentifyingCode(int numOfChars) {
		String str = StringUtils.STR_EMPTY;
		Random random = new Random();
		String optionalCodes = PlatformConst.IDENTIFIER_CODE_CHARS;
		for (int i = 0; i < numOfChars; i++)
			str = str + optionalCodes.charAt(random.nextInt(optionalCodes.length()));
		return str;
	}
}
