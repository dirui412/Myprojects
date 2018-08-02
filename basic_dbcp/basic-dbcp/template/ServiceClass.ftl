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

    public Pager<${modelClassName}> findAllPaged${modelClassName}()
	{
		Pager<${modelClassName}> pager=${modelClassNameFS}Dao.findAllPaged();
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
	
}