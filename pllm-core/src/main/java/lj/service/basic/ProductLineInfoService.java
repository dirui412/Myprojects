package lj.service.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.basic.IProductLineInfoDao;
import lj.model.base.Pager;
import lj.model.basic.ProductLineInfo;
import lj.service.BaseService;

@Service
public class ProductLineInfoService extends BaseService {

    @Autowired
	private IProductLineInfoDao productLineInfoDao=null;
	
	public ProductLineInfo[] findAll()
    {
    	ProductLineInfo[] objs=productLineInfoDao.findAll();
    	return objs;
    }
    
    public ProductLineInfo find(Long id)
	{
		ProductLineInfo obj=productLineInfoDao.find(id);
		return obj;
	}
    

    public Pager<ProductLineInfo> findAllPagedProductLineInfo()
	{
		Pager<ProductLineInfo> pager=productLineInfoDao.findAllPaged();
		return pager;
	}
	
	public Long insertProductLineInfo(ProductLineInfo obj)
	{
		Long retId=productLineInfoDao.insert(obj);
		return retId;
	}
	
	public String updateProductLineInfo(ProductLineInfo obj)
	{
		String msg=productLineInfoDao.update(obj);
		return msg;
	}
	
	public String deleteProductLineInfo(Long id)
	{
		String msg=productLineInfoDao.delete(id);
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
		return productLineInfoDao.isRepeat(fieldName, fieldValue, id);
	}
	
}