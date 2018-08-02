package lj.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import lj.global.PlatformVar;

public class FreeMarkerUtils {
	private final static String PATH_TEMPLATE = "template";
	private static Configuration cfg =null;

	/***
	 * 禁止调用构造方法
	 */
	private FreeMarkerUtils(){
		
	}
	/***
	 * 
	 * @return
	 */
	private static Configuration createConfiguration() {
		// 1-设置编码格式
		cfg=new Configuration();
		cfg.setEncoding(Locale.getDefault(), FileUtils.ENCODING_UTF8);
		// 2-指定模板文件的数据源，这里是一个文件目录
		File templatePath = null;
		if (StringUtils.isNullOrEmpty(PlatformVar.platformPath) == true)
			templatePath = new File(PATH_TEMPLATE);
		else
			templatePath = new File(PlatformVar.platformPath +File.separator + PATH_TEMPLATE);
		try {
			cfg.setDirectoryForTemplateLoading(templatePath);
			cfg.setObjectWrapper(new DefaultObjectWrapper()); // 这条语句可省略
			return cfg;
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.logError("FreeMarkerUtils.getConfiguration:" + e);
			return null;
		}
	}

	/***
	 * 
	 * @param templateName
	 * @return
	 */
	public static void generateString(String templateName, String outputPath, Map<String,Object> map) {
		if(cfg==null)
			cfg=createConfiguration();
		Writer writer = null;
		try {
			// 1-加载模板文件
			Template template = cfg.getTemplate(templateName);
			// 2-设置输出流
			File outputFile = new File(outputPath);
			if(outputFile.exists()==false)
				outputFile.createNewFile();
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8"));
			// 3-生成输出文件
			template.process(map, writer);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.logError("FreeMarkerUtils.generateString:" + e);
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (Exception innerEx) {
					innerEx.printStackTrace();
					LogUtils.logError("FreeMarkerUtils.generateString:" + innerEx);
				}
		}
	}
}
