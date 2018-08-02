package lj.service.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.basic.IProductLineEquipmentDao;
import lj.model.base.Pager;
import lj.model.basic.ProductLineEquipment;
import lj.service.BaseService;

@Service
public class ProductLineEquipmentService extends BaseService {

    @Autowired
	private IProductLineEquipmentDao productLineEquipmentDao=null;
	
	public ProductLineEquipment[] findAll()
    {
    	ProductLineEquipment[] objs=productLineEquipmentDao.findAll();
    	return objs;
    }
    
    public ProductLineEquipment find(Long id)
	{
		ProductLineEquipment obj=productLineEquipmentDao.find(id);
		return obj;
	}
    

    public Pager<ProductLineEquipment> findAllPagedProductLineEquipment()
	{
		Pager<ProductLineEquipment> pager=productLineEquipmentDao.findAllPaged();
		return pager;
	}
	
	public Long insertProductLineEquipment(ProductLineEquipment obj)
	{
		Long retId=productLineEquipmentDao.insert(obj);
		return retId;
	}
	
	public String updateProductLineEquipment(ProductLineEquipment obj)
	{
		String msg=productLineEquipmentDao.update(obj);
		return msg;
	}
	
	public String deleteProductLineEquipment(Long id)
	{
		String msg=productLineEquipmentDao.delete(id);
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
		return productLineEquipmentDao.isRepeat(fieldName, fieldValue, id);
	}
	
	public String updateProductLineEquipments(Long productLineId,Long[] equipmentIds)
	{
		String str=productLineEquipmentDao.updateProductLineEquipments(productLineId, equipmentIds);
		return str;
	}
}