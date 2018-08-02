package ${serviceClassPath};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${daoClassPath}.I${modelClassName}Dao;
import lj.model.base.Pager;
import ${modelClassPath}.${modelClassName};
import lj.service.BaseService;

@Service
public class ${modelClassName}Service extends BaseService {

    @Autowired
	private I${modelClassName}Dao ${modelClassNameFS}Dao=null;
	
	public ${modelClassName}[] findAll()
    {
    	${modelClassName}[] objs=${modelClassNameFS}Dao.findAll();
    	return objs;
    }
    
    public ${modelClassName} find(Long id)
	{
		${modelClassName} obj=${modelClassNameFS}Dao.find(id);
		return obj;
	}
    

    public Pager<${modelClassName}> findAllPaged${modelClassName}(<#list queryFields as e>${e.beanType} ${e.beanName} <#if e_has_next>,</#if> </#list>)
	{
		Pager<${modelClassName}> pager=${modelClassNameFS}Dao.findAllPaged(<#list queryFields as e>${e.beanName} <#if e_has_next>,</#if> </#list>);
		return pager;
	}
	
	public Long insert${modelClassName}(${modelClassName} obj)
	{
		Long retId=${modelClassNameFS}Dao.insert(obj);
		return retId;
	}
	
	public String update${modelClassName}(${modelClassName} obj)
	{
		String msg=${modelClassNameFS}Dao.update(obj);
		return msg;
	}
	
	public String delete${modelClassName}(Long id)
	{
		String msg=${modelClassNameFS}Dao.delete(id);
		return msg;
	}
	
	/**
	 * 某个字段是否重复
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	public boolean isRepeat(String fieldName,Object fieldValue,Long id)
	{
		return ${modelClassNameFS}Dao.isRepeat(fieldName, fieldValue, id);
	}
	
}