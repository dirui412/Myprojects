package lj.service.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lj.dao.basic.IViProductLineEquipmentDao;
import lj.model.base.Pager;
import lj.model.basic.ViProductLineEquipment;
import lj.service.BaseService;

@Service
public class ViProductLineEquipmentService extends BaseService {

	@Autowired
	private IViProductLineEquipmentDao viProductLineEquipmentDao = null;

	public ViProductLineEquipment[] findAll() {
		ViProductLineEquipment[] objs = viProductLineEquipmentDao.findAll();
		return objs;
	}

	/**
	 * 查询所有生产线标识
	 * 
	 * @param productLineId
	 * @return
	 */
	public ViProductLineEquipment[] findAllByProductLineId(Long productLineId) {

		ViProductLineEquipment[] objs = viProductLineEquipmentDao.findAllByProductLineId(productLineId);
		return objs;
	}

	public ViProductLineEquipment find(Long id) {
		ViProductLineEquipment obj = viProductLineEquipmentDao.find(id);
		return obj;
	}

	public Pager<ViProductLineEquipment> findAllPagedViProductLineEquipment(String productName, String equipmentName) {
		Pager<ViProductLineEquipment> pager = viProductLineEquipmentDao.findAllPaged(productName, equipmentName);
		return pager;
	}

	public Long insertViProductLineEquipment(ViProductLineEquipment obj) {
		Long retId = viProductLineEquipmentDao.insert(obj);
		return retId;
	}

	public String updateViProductLineEquipment(ViProductLineEquipment obj) {
		String msg = viProductLineEquipmentDao.update(obj);
		return msg;
	}

	public String deleteViProductLineEquipment(Long id) {
		String msg = viProductLineEquipmentDao.delete(id);
		return msg;
	}

	/**
	 * 某个字段是否重复
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	public boolean isRepeat(String fieldName, Object fieldValue, Long id) {
		return viProductLineEquipmentDao.isRepeat(fieldName, fieldValue, id);
	}

}