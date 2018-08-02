package lj.service.collect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.collect.IProductLineCollectDao;
import lj.model.base.Pager;
import lj.model.collect.ProductLineCollect;
import lj.service.BaseService;

@Service
public class ProductLineCollectService extends BaseService {

    @Autowired
	private IProductLineCollectDao productLineCollectDao=null;
	
	public ProductLineCollect[] findAll()
    {
    	ProductLineCollect[] objs=productLineCollectDao.findAll();
    	return objs;
    }
    
    public ProductLineCollect find(Long id)
	{
		ProductLineCollect obj=productLineCollectDao.find(id);
		return obj;
	}
    

    public Pager<ProductLineCollect> findAllPagedProductLineCollect()
	{
		Pager<ProductLineCollect> pager=productLineCollectDao.findAllPaged();
		return pager;
	}
	
	public Long insertProductLineCollect(ProductLineCollect obj)
	{
		Long retId=productLineCollectDao.insert(obj);
		return retId;
	}
	
	public String updateProductLineCollect(ProductLineCollect obj)
	{
		String msg=productLineCollectDao.update(obj);
		return msg;
	}
	
	public String deleteProductLineCollect(Long id)
	{
		String msg=productLineCollectDao.delete(id);
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
		return productLineCollectDao.isRepeat(fieldName, fieldValue, id);
	}
	
}