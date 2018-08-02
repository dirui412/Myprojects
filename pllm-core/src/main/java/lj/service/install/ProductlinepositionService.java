package lj.service.install;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.install.IProductlinepositionDao;
import lj.model.base.Pager;
import lj.model.install.Productlineposition;
import lj.service.BaseService;

@Service
public class ProductlinepositionService extends BaseService {

    @Autowired
	private IProductlinepositionDao productlinepositionDao=null;
	
	public Productlineposition[] findAll()
    {
    	Productlineposition[] objs=productlinepositionDao.findAll();
    	return objs;
    }
    
    public Productlineposition find(Long id)
	{
		Productlineposition obj=productlinepositionDao.find(id);
		return obj;
	}
    
    public Productlineposition findByUserProductLineId(Long userProductLineId){
    	Productlineposition obj=productlinepositionDao.findByUserProductLineId(userProductLineId);
    	return obj;
    }

    public Pager<Productlineposition> findAllPagedProductlineposition(Integer productLinePositionId , Long userProductLineId , Double position1 , Double position2  )
	{
		Pager<Productlineposition> pager=productlinepositionDao.findAllPaged(productLinePositionId , userProductLineId , position1 , position2  );
		return pager;
	}
	
	public Long insertProductlineposition(Productlineposition obj)
	{
		Long retId=productlinepositionDao.insert(obj);
		return retId;
	}
	
	public String updateProductlineposition(Productlineposition obj)
	{
		String msg=productlinepositionDao.update(obj);
		return msg;
	}
	
	public String deleteProductlineposition(Long id)
	{
		String msg=productlinepositionDao.delete(id);
		return msg;
	}
	public String deleteByuserProductLineId(Long userProductLineId){
		String msg=productlinepositionDao.deleteByuserProductLineId(userProductLineId);
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
		return productlinepositionDao.isRepeat(fieldName, fieldValue, id);
	}
	
}