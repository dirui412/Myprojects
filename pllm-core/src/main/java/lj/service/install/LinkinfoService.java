package lj.service.install;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.install.ILinkinfoDao;
import lj.model.base.Pager;
import lj.model.install.Linkinfo;
import lj.service.BaseService;

@Service
public class LinkinfoService extends BaseService {

    @Autowired
	private ILinkinfoDao linkinfoDao=null;
	
	public Linkinfo[] findAll()
    {
    	Linkinfo[] objs=linkinfoDao.findAll();
    	return objs;
    }
    
    public Linkinfo find(Long id)
	{
		Linkinfo obj=linkinfoDao.find(id);
		return obj;
	}
    

    public Pager<Linkinfo> findAllPagedLinkinfo(Integer linkId , Long userProductLineId , String fromKey , String toKey , String fromPort , Double toPort , Double point1 , Double point2 , Double point3 , Double point4 , Double point5 , Double point6 , Double point7 , Double point8 , Double point9 , Double point10 , Double point11 , Double point12  )
	{
		Pager<Linkinfo> pager=linkinfoDao.findAllPaged(linkId , userProductLineId , fromKey , toKey , fromPort , toPort , point1 , point2 , point3 , point4 , point5 , point6 , point7 , point8 , point9 , point10 , point11 , point12  );
		return pager;
	}
	
	public Long insertLinkinfo(Linkinfo obj)
	{
		Long retId=linkinfoDao.insert(obj);
		return retId;
	}
	
	public String updateLinkinfo(Linkinfo obj)
	{
		String msg=linkinfoDao.update(obj);
		return msg;
	}
	
	public String deleteLinkinfo(Long id)
	{
		String msg=linkinfoDao.delete(id);
		return msg;
	}
	public String deleteByuserProductLineId(Long userProductLineId){
		String msg=linkinfoDao.deleteByuserProductLineId(userProductLineId);
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
		return linkinfoDao.isRepeat(fieldName, fieldValue, id);
	}

	public Linkinfo[] findByUserProductLineId(Long userProductLineId) {
		Linkinfo[] objs=linkinfoDao.findByUserProductLineId(userProductLineId);
		return objs;
	}
	
}