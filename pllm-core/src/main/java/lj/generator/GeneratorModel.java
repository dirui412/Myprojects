package lj.generator;

public class GeneratorModel {
	private String packageName;
	private String tableName;
	private String indexName;
	private String dirCore;
	private String dirWeb;
	private String queryFields;
	private boolean isModel;
	private boolean isIDao;
	private boolean isDao;
	private boolean isService;
	private boolean isController;
	private boolean isPage;

	

	public boolean isModel() {
		return isModel;
	}

	public void setModel(boolean isModel) {
		this.isModel = isModel;
	}

	public boolean isIDao() {
		return isIDao;
	}

	public void setIDao(boolean isIDao) {
		this.isIDao = isIDao;
	}

	public boolean isDao() {
		return isDao;
	}

	public void setDao(boolean isDao) {
		this.isDao = isDao;
	}

	public boolean isService() {
		return isService;
	}

	public void setService(boolean isService) {
		this.isService = isService;
	}

	public boolean isController() {
		return isController;
	}

	public void setController(boolean isController) {
		this.isController = isController;
	}

	public boolean isPage() {
		return isPage;
	}

	public void setPage(boolean isPage) {
		this.isPage = isPage;
	}

	public String getDirCore() {
		return dirCore;
	}

	public void setDirCore(String dirCore) {
		this.dirCore = dirCore;
	}

	public String getDirWeb() {
		return dirWeb;
	}

	public void setDirWeb(String dirWeb) {
		this.dirWeb = dirWeb;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public GeneratorModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getQueryFields() {
		return queryFields;
	}

	public void setQueryFields(String queryFields) {
		this.queryFields = queryFields;
	}

	public GeneratorModel(String packageName, String tableName, String indexName, String dirCore, String dirWeb,
			String queryFields, boolean isModel, boolean isIDao, boolean isDao, boolean isService, boolean isController,
			boolean isPage) {
		super();
		this.packageName = packageName;
		this.tableName = tableName;
		this.indexName = indexName;
		this.dirCore = dirCore;
		this.dirWeb = dirWeb;
		this.queryFields = queryFields;
		this.isModel = isModel;
		this.isIDao = isIDao;
		this.isDao = isDao;
		this.isService = isService;
		this.isController = isController;
		this.isPage = isPage;
	}

	
	
	
}
