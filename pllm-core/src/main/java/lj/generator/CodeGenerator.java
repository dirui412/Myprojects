package lj.generator;

import java.io.File;

import lj.global.PlatformFun;
import lj.util.GeneratorUtils;
import lj.util.StringUtils;

public class CodeGenerator {
	private final static String PATH_SRC = "src\\main\\java\\";
	private final static String PATH_JSP = "src\\main\\webapp\\WEB-INF\\jsp\\";

	/**
	 * 生成实体类
	 * 
	 * @param tableName
	 */
	private static void testGenerateEntityClass(String packageName, String tableName) {
		GeneratorUtils.generatorEntityClass(packageName, tableName);
	}

	private static void testGenerateDaoInterface(String packageName, String tableName, String modelClassPath) {
		GeneratorUtils.generatorDaoInterface(packageName, tableName, modelClassPath);
	}

	private static void testGeneratorDaoClass(String packageName, String tableName, String modelClassPath,
			String indexName) {
		GeneratorUtils.generatorDaoClass(packageName, tableName, modelClassPath, indexName);

	}

	private static void testGeneratorPageList(String tableName, String indexName, String contextUrl) {
		GeneratorUtils.generatePageList(tableName, indexName, contextUrl);
	}

	/**
	 * 生成函数
	 * 
	 * @param parentCateogry
	 * @param tableName
	 * @param indexName
	 */
	public static void generate(GeneratorModel model) {
		// 自动生成信息

		String tableName = model.getTableName(), indexName = model.getIndexName(),
				parentCateogry = model.getPackageName();
		String modelClassPath = "lj.model." + parentCateogry;
		String daoClassPath = "lj.dao." + parentCateogry;
		String serviceClassPath = "lj.service." + parentCateogry;
		String controllerClassPath = "lj.controller." + parentCateogry;
		String requestUrl = "../" + model.getPackageName() + "/" + StringUtils.toVarName(tableName);
		String[] queryFields = new String[0];
		if (StringUtils.isNullOrEmpty(model.getQueryFields()) == false)
			queryFields = model.getQueryFields().split(";");
		if (model.isModel())
			GeneratorUtils.generatorEntityClass(modelClassPath, tableName, model.getDirCore() + File.separator
					+ PATH_SRC + "lj" + File.separator + "model" + File.separator + parentCateogry);

		if (model.isIDao())
			GeneratorUtils.generatorDaoInterface(daoClassPath, tableName, modelClassPath, queryFields,
					model.getDirCore() + File.separator + PATH_SRC + "lj" + File.separator + "dao" + File.separator
							+ parentCateogry);
		if (model.isDao())
			GeneratorUtils.generatorDaoClass(daoClassPath, tableName, modelClassPath, indexName, queryFields,
					model.getDirCore() + File.separator + PATH_SRC + "lj" + File.separator + "dao" + File.separator
							+ parentCateogry);

		if (model.isService())
			GeneratorUtils.generateServiceCode(tableName, daoClassPath, serviceClassPath, modelClassPath, queryFields,
					model.getDirCore() + File.separator + PATH_SRC + "lj" + File.separator + "service" + File.separator
							+ parentCateogry);

		if (model.isController())
			GeneratorUtils.generateControllerClass(modelClassPath, serviceClassPath, controllerClassPath, tableName,
					indexName, parentCateogry, queryFields, model.getDirWeb() + File.separator + PATH_SRC + "lj"
							+ File.separator + "controller" + File.separator + parentCateogry);
		if (model.isPage())
			GeneratorUtils.generatePageList(tableName, indexName, requestUrl, queryFields,
					model.getDirWeb() + File.separator + PATH_JSP + File.separator + parentCateogry);
	}

	public static void main(String[] args) {
		PlatformFun.preLoad();
		// 需要设置信息
		String parentCateogry = "install";
		String tableName = "linkinfo";
		String indexName = "linkId";
		String modelClassPath = "lj.model." + parentCateogry;
		String daoClassPath = "lj.dao." + parentCateogry;
		String serviceClassPath = "lj.service." + parentCateogry;
		String controllerClassPath = "lj.controller." + parentCateogry;
		String requestUrl = "../" + parentCateogry + "/" + StringUtils.toVarName(tableName);
		testGenerateEntityClass(modelClassPath, tableName);
		testGenerateDaoInterface(daoClassPath, tableName, modelClassPath);
		testGeneratorDaoClass(daoClassPath, tableName, modelClassPath, indexName);
		GeneratorUtils.generateServiceCode(tableName, daoClassPath, serviceClassPath, modelClassPath);
		GeneratorUtils.generateControllerClass(modelClassPath, serviceClassPath, controllerClassPath, tableName,
				indexName, parentCateogry);
		testGeneratorPageList(tableName, indexName, requestUrl);
	}
}
