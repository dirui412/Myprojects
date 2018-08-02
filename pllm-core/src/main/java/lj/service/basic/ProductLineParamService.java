package lj.service.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.basic.IProductLineParamDao;
import lj.model.base.Pager;
import lj.model.basic.ProductLineParam;
import lj.service.BaseService;

@Service
public class ProductLineParamService extends BaseService {
	
    @Autowired
	private IProductLineParamDao productLineParamDao=null;
	
	public ProductLineParam[] findAll()
    {
    	ProductLineParam[] objs=productLineParamDao.findAll();
    	return objs;
    }
    
    public ProductLineParam find(Long id)
	{
		ProductLineParam obj=productLineParamDao.find(id);
		return obj;
	}
    
	public ProductLineParam[] findAllByProdunctLineId(Long productLineId)
	{
		ProductLineParam[] objs=productLineParamDao.findAllByProdunctLineId(productLineId);
    	return objs;
	}

    public Pager<ProductLineParam> findAllPagedProductLineParam()
	{
		Pager<ProductLineParam> pager=productLineParamDao.findAllPaged();
		return pager;
	}
	
	public Long insertProductLineParam(ProductLineParam obj)
	{
		Long retId=productLineParamDao.insert(obj);
		return retId;
	}
	
	public String updateProductLineParam(ProductLineParam obj)
	{
		String msg=productLineParamDao.update(obj);
		return msg;
	}
	
	public String deleteProductLineParam(Long id)
	{
		String msg=productLineParamDao.delete(id);
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
		return productLineParamDao.isRepeat(fieldName, fieldValue, id);
	}
	
}