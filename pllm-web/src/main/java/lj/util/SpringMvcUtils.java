package lj.util;

import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class SpringMvcUtils {
	
	/**
	 * 将MultipartFile写入文件
	 * @param uploadFile
	 * @param targetPath
	 * @return
	 */
	public static boolean writeFile(MultipartFile multipartFile, String targetPath) {
		try {
			FileOutputStream fs = new FileOutputStream(targetPath);
			fs.write(multipartFile.getBytes());
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
