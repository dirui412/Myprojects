package lj.test;


import lj.global.PlatformFun;
import lj.util.GeneratorUtils;
import lj.util.StringUtils;

public class CodeGenerator {

	
	/**
	 * 生成实体类
	 * @param tableName
	 */
	private static void testGenerateEntityClass(String packageName,String tableName){
		GeneratorUtils.generatorEntityClass(packageName,tableName);
	}

	private static void testGenerateDaoInterface(String packageName, String tableName,String modelClassPath){
		GeneratorUtils.generatorDaoInterface(packageName, tableName, modelClassPath);
	}
	
	private static void testGeneratorDaoClass(String packageName, String tableName,String modelClassPath,String indexName) {
		GeneratorUtils.generatorDaoClass(packageName, tableName, modelClassPath, indexName);
		
	}
	
	private static void testGeneratorPageList(String tableName,String indexName,String contextUrl) {
		GeneratorUtils.generatePageList(tableName, indexName,contextUrl);
	}
	
	public static void main(String[] args) {
		PlatformFun.preLoad();
		String parentCateogry="query";
		String tableName="ViCircuitCollect";
		String indexName="circuitCollectId";
		String modelClassPath="lj.model."+parentCateogry;
		String daoClassPath="lj.dao."+parentCateogry;
		String serviceClassPath="lj.service."+parentCateogry;
		String controllerClassPath="lj.controller."+parentCateogry;
		String requestUrl="../"+parentCateogry+"/"+StringUtils.toVarName(tableName);
		testGenerateEntityClass(modelClassPath, tableName);
		testGenerateDaoInterface(daoClassPath, tableName, modelClassPath);
		testGeneratorDaoClass(daoClassPath, tableName, modelClassPath,indexName);
		GeneratorUtils.generateServiceCode(tableName,daoClassPath, serviceClassPath,modelClassPath);
		GeneratorUtils.generateControllerClass(modelClassPath,serviceClassPath, controllerClassPath, tableName,indexName,parentCateogry);
		testGeneratorPageList(tableName,indexName,requestUrl);
	}
}
