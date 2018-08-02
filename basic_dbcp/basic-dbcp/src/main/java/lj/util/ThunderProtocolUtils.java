package lj.util;

import java.util.Base64;
import java.util.HashMap;

/**
 * 迅雷下载工具类(获取迅雷地址，但是不使用迅雷工具)
 * 
 * @author samlv
 *
 */
public class ThunderProtocolUtils {
	private final static String URL_PRE = "thunder://";
	private final static String URL_END = "\"";
	private final static Base64.Decoder decoder = Base64.getDecoder();
	private final static byte[] buffer = new byte[1024];

	private ThunderProtocolUtils() {

	}

	public static void download(String httpUrl, String filePath) {
		try {
			String thunderUrl = HttpUtils.doGet(httpUrl, new HashMap<String, String>());
			System.out.println("thunder url:"+thunderUrl);
			int startInde = thunderUrl.indexOf(URL_PRE);
			int endIndex = thunderUrl.indexOf(URL_END, startInde);
			String str = thunderUrl.substring(startInde + URL_PRE.length(), endIndex);
			str = new String(decoder.decode(str), "UTF-8");
			String realUrl = str.substring(2, str.length() - 2);
			HttpUtils.doDownLoad(realUrl, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		download("http://www.qktsw.com/downm.asp?id=4278&t=0&k=4", "d:\\1.mp3");
	}

}