package lj.util;

import java.io.File;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;

import lj.dao.base.JdbcDao;
import lj.global.PlatformVar;
import lj.meta.java.JavaBean;

public class GeneratorUtils {

	private final static String TEMPLATE_CLASSENTITY_NAME = "EntityClass.ftl";
	private final static String TEMPLATE_DAO_INTERFACE_NAME = "EntityDaoInterface.ftl";
	private final static String TEMPLATE_DAO_NAME = "EntityDao.ftl";
	private final static String TEMPLAGE_PAGE_LIST = "PageList.ftl";
	private final static String TEMPLATE_SERVICE_CLASS = "ServiceClass.ftl";
	private final static String TEMPLAGE_CONTROLLER_CLASS = "ControllerClass.ftl";
	public final static String PATH_GENERATOR = "generator";
	private static JdbcDao jdbcDao = null;
	private final static String SPLIT_STR=";";

	private GeneratorUtils() {}

	private static void createJdbcDao() {
		BeanFactory factory = SpringUtils.getBeanFactory();
		jdbcDao = factory.getBean("jdbcDao", JdbcDao.class);
	}

	/***
	 * 产生实体类代码
	 * 
	 * @param tableName
	 * @param entityClassPath
	 * @return
	 */
	public static void generatorEntityClass(String packageName, String tableName) {
		String outputPath = PlatformVar.platformPath + File.separator + PATH_GENERATOR;
		generatorEntityClass(packageName, tableName, outputPath);
	}

	/***
	 * 产生实体类代码
	 * 
	 * @param tableName
	 * @param entityClassPath
	 * @return
	 */
	public static void generatorEntityClass(String packageName, String tableName, String generatDir) {
		File dirGenerateDir=new File(generatDir);
		if(dirGenerateDir.exists()==false)
			if(dirGenerateDir.mkdir()==false)
				return;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("packageName", packageName);
		map.put("className", StringUtils.toTypeName(tableName));
		List<JavaBean> fields = generateFields(tableName);
		map.put("fields", fields);
		String outputPath = generatDir+File.separator
				+ StringUtils.toTypeName(tableName) + ".java";
		
		//System.out.println("generatorEntityClass output path:"+outputPath);
		FreeMarkerUtils.generateString(TEMPLATE_CLASSENTITY_NAME, outputPath, map);

	}

	private static List<JavaBean> generateFields(String tableName) {
		List<JavaBean> fields = new ArrayList<JavaBean>();
		if (jdbcDao == null)
			createJdbcDao();
		ResultSetMetaData meta = jdbcDao.getTableOrViewMetaData(tableName);
		try {
			for (int i = 0; i < meta.getColumnCount(); i++) {
				String columnName = meta.getColumnName(i + 1);
				int jdbcType = meta.getColumnType(i + 1);
				Class fieldClass = DbUtils.jdbcTypeToJavaClass(jdbcType);
				String getterName = "get" + StringUtils.toTypeName(columnName);
				String setterName = "set" + StringUtils.toTypeName(columnName);
				JavaBean f = new JavaBean(fieldClass.getSimpleName(), StringUtils.toVarName(columnName),
						StringUtils.toTypeName(columnName), getterName, setterName);
				fields.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.logError("generatorEntityClass:" + e);
		}
		return fields;
	}
	
	/**
	 * 产生查询字段的JavaBean信息
	 * @param queryFields
	 * @return
	 */
	private static List<JavaBean> getQueryFields(String tableName,String[] queryFields)
	{
		List<JavaBean> optionalFields=generateFields(tableName);
		List<JavaBean> list=new ArrayList<JavaBean>();
		for(String fieldName:queryFields)
			for(JavaBean bean:optionalFields)
				if(bean.getBeanName().equals(fieldName)==true)
				{
					list.add(bean);
					break;
				}
		return list;
	}

	/***
	 * 
	 * @param packageName
	 * @param interfaceName
	 * @param modelClassPath
	 * @param modelClassName
	 */
	public static void generatorDaoInterface(String packageName, String tableName, String modelClassPath) {
		String interfaceName = "I" + StringUtils.toTypeName(tableName) + "Dao";
		String outputPath = PlatformVar.platformPath + File.separator + PATH_GENERATOR;
		generatorDaoInterface(packageName, tableName, modelClassPath, new String[0], outputPath);
	}

	/***
	 * 
	 * @param packageName
	 * @param interfaceName
	 * @param modelClassPath
	 * @param modelClassName
	 */
	public static void generatorDaoInterface(String packageName, String tableName, String modelClassPath,
			String[] queryFields,String generatePath) {
		File dirGenerateDir=new File(generatePath);
		if(dirGenerateDir.exists()==false)
			if(dirGenerateDir.mkdir()==false)
				return;
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("packageName", packageName);
		String interfaceName = "I" + StringUtils.toTypeName(tableName) + "Dao";
		variables.put("interfaceName", interfaceName);
		variables.put("modelClassPath", modelClassPath);
		variables.put("modelClassName", StringUtils.toTypeName(tableName));
		variables.put("queryFields", getQueryFields(tableName,queryFields));
		String outputPath =generatePath + File.separator
				+ StringUtils.toTypeName(interfaceName) + ".java";
		FreeMarkerUtils.generateString(TEMPLATE_DAO_INTERFACE_NAME, outputPath, variables);
	}

	public static void generatorDaoClass(String packageName, String tableName, String modelClassPath,
			String indexName) {
		String outputPath = PlatformVar.platformPath + File.separator + PATH_GENERATOR;
		generatorDaoClass(packageName, tableName, modelClassPath,indexName,new String[0],outputPath);
	}
	
	
	public static void generatorDaoClass(String packageName, String tableName, String modelClassPath,
			String indexName,String[] queryFields,String generatePath) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("packageName", packageName);
		String interfaceName = "I" + StringUtils.toTypeName(tableName) + "Dao";
		String className = StringUtils.toTypeName(tableName) + "Dao";
		variables.put("className", className);
		variables.put("modelClassPath", modelClassPath);
		variables.put("modelClassName", StringUtils.toTypeName(tableName));
		variables.put("daoInterfaceName", interfaceName);
		variables.put("indexName", indexName);
		variables.put("indexNameGetter", "get" + StringUtils.toTypeName(indexName));
		variables.put("queryFields", getQueryFields(tableName,queryFields));
		List<JavaBean> fields = generateFields(tableName);
		for (JavaBean temp : fields)
			if (temp.getBeanName().toUpperCase().equals(indexName.toUpperCase()) == true) {
				fields.remove(temp);
				break;
			}
		variables.put("fields", fields);
		String outputPath = generatePath + File.separator
				+ StringUtils.toTypeName(className) + ".java";
		FreeMarkerUtils.generateString(TEMPLATE_DAO_NAME, outputPath, variables);
	}
	
	
	/**
	 * 产生服务器端代码
	 */
	public static void generateServiceCode(String tableName, String daoClassPath, String serviceClassPath,
			String modelClassPath) {
	
		String outputPath = PlatformVar.platformPath + File.separator + PATH_GENERATOR;
		generateServiceCode(tableName,daoClassPath, serviceClassPath,modelClassPath,new String[0],outputPath);
	}
	
	public static void generateServiceCode(String tableName, String daoClassPath, String serviceClassPath,
			String modelClassPath,String[] queryFields,String generatePath) {
		File dirGenerateDir=new File(generatePath);
		if(dirGenerateDir.exists()==false)
			if(dirGenerateDir.mkdir()==false)
				return;
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("modelClassName", StringUtils.toTypeName(tableName));
		variables.put("modelClassNameFS", StringUtils.toVarName(tableName));
		variables.put("daoClassPath", daoClassPath);
		variables.put("serviceClassPath", serviceClassPath);
		variables.put("modelClassPath", modelClassPath);
		variables.put("queryFields", getQueryFields(tableName,queryFields));
		String outputPath = generatePath + File.separator
				+ StringUtils.toTypeName(tableName) + "Service.java";
		FreeMarkerUtils.generateString(TEMPLATE_SERVICE_CLASS, outputPath, variables);
	}
	
	public static void generateControllerClass(String modelClassPath, String serviceClassPath,
			String controllerClassPath, String tableName, String indexName, String parentContextUrl) {
		
		String outputPath = PlatformVar.platformPath + File.separator + PATH_GENERATOR ;
		generateControllerClass(modelClassPath, serviceClassPath,controllerClassPath,tableName,indexName, 
				parentContextUrl,new String[0],outputPath);
	}
	
	/**
	 * 
	 * @param modelClassPath
	 * @param serviceClassPath
	 * @param controllerClassPath
	 * @param tableName
	 * @param indexName
	 * @param parentContextUrl
	 * @param queryFields
	 * @param generatePath
	 */
	public static void generateControllerClass(String modelClassPath, String serviceClassPath,
			String controllerClassPath, String tableName, String indexName, String parentContextUrl,
			String[] queryFields,String generatePath) {
		File dirGenerateDir=new File(generatePath);
		if(dirGenerateDir.exists()==false)
			if(dirGenerateDir.mkdir()==false)
				return;
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("controllerClassPath", controllerClassPath);
		variables.put("serviceClassPath", serviceClassPath);
		variables.put("modelClassPath", modelClassPath);
		variables.put("modelClassName", StringUtils.toTypeName(tableName));
		variables.put("indexName", indexName);
		variables.put("modelClassNameFS", StringUtils.toVarName(tableName));
		variables.put("parentContextUrl", parentContextUrl);
		variables.put("queryFields", getQueryFields(tableName,queryFields));
		String outputPath = generatePath+ File.separator
				+ StringUtils.toTypeName(tableName) + "Controller.java";
		FreeMarkerUtils.generateString(TEMPLAGE_CONTROLLER_CLASS, outputPath, variables);
	}

	public static void generatePageList(String tableName, String indexName, String contextUrl) {
		String outputPath = PlatformVar.platformPath + File.separator + PATH_GENERATOR;
		generatePageList(tableName, indexName, contextUrl,new String[0],outputPath);
	}
	
	
	public static void generatePageList(String tableName, String indexName, String contextUrl,
			String[] queryFields,String generatePath) {
		File dirGenerateDir=new File(generatePath);
		if(dirGenerateDir.exists()==false)
			if(dirGenerateDir.mkdir()==false)
				return;
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("indexName", indexName);
		variables.put("indexNameFB", StringUtils.toTypeName(indexName));
		variables.put("modelClassName", StringUtils.toTypeName(tableName));
		variables.put("modelClassNameFS", StringUtils.toVarName(tableName));

		variables.put("getAllUrl", contextUrl + "/");
		variables.put("getUrl", contextUrl + "/");
		variables.put("putUrl", contextUrl + "/");
		variables.put("postUrl", contextUrl + "/");
		variables.put("deleteUrl", contextUrl + "/");
		variables.put("queryFields", getQueryFields(tableName,queryFields));
		List<JavaBean> fields = generateFields(tableName);
		for (JavaBean temp : fields)
			if (temp.getBeanName().toUpperCase().equals(indexName.toUpperCase()) == true) {
				fields.remove(temp);
				break;
			}
		variables.put("fields", fields);
		String outputPath = generatePath + File.separator
				+ StringUtils.toCamelCase(tableName) + "Page.jsp";
		FreeMarkerUtils.generateString(TEMPLAGE_PAGE_LIST, outputPath, variables);
	}

}
