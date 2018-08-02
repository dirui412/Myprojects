package lj.controller.basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lj.controller.BaseController;
import lj.model.base.Pager;
import lj.model.base.QueryContext;
import lj.model.basic.EquipmentInfo;
import lj.model.basic.EquipmentType;
import lj.model.basic.Viequipmentinfo;
import lj.service.basic.EquipmentInfoService;
import lj.service.basic.EquipmentTypeService;
import lj.util.DatatablesReturnObject;
import lj.util.StringUtils;

@Controller
@RequestMapping("basic")
public class EquipmentInfoController extends BaseController {
	@Autowired
	private EquipmentInfoService equipmentInfoService;
	
	@Autowired
	private EquipmentTypeService equipmentTypeService;
	
	@RequestMapping(value = { "/equipmentInfoPage" }, method = RequestMethod.GET)
	public String equipmentInfoPage(Model model) {
		EquipmentType[] equipmentTypes = equipmentTypeService.findAll();
		model.addAttribute("equipmentTypes", equipmentTypes);
		return "basic/equipmentInfoPage";
	}
	
	@RequestMapping(value = { "/equipmentInfo" }, method = RequestMethod.GET)
	public @ResponseBody DatatablesReturnObject<Viequipmentinfo> findAllPaged() {
		// 2-获得查询数据
		Pager<Viequipmentinfo> pager = equipmentInfoService.findAllPagedEquipmentInfo();
		// 3-生成返回格式
		DatatablesReturnObject<Viequipmentinfo> obj = new DatatablesReturnObject<Viequipmentinfo>(
				QueryContext.getPageDraw(), pager);
		return obj;
	}
	
	@RequestMapping(value = { "/equipmentInfo" }, method = RequestMethod.POST)
	public @ResponseBody String insertEquipmentInfo(@RequestBody EquipmentInfo obj,HttpServletRequest req)
	{
		Long retId=equipmentInfoService.insertEquipmentInfo(obj);
		if(retId>0)
			return "";
		else
			return "新增失败";
	}
	
	@RequestMapping(value = { "/equipmentInfo" }, method = RequestMethod.PUT)
	public @ResponseBody String updateEquipmentInfo(@RequestBody EquipmentInfo obj,HttpServletRequest req)
	{
		String msg=equipmentInfoService.updateEquipmentInfo(obj);
		return msg;
	}
	
	@RequestMapping(value = { "/equipmentInfo/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteEquipmentInfo(@PathVariable Long id)
	{
		String msg=equipmentInfoService.deleteEquipmentInfo(id);
		return msg;
	}
	
	@RequestMapping(value = { "/equipmentInfoUpload/{equipmentId}" }, method = RequestMethod.POST)
	public @ResponseBody String uploadPicture(HttpServletRequest request,@PathVariable Long equipmentId,
			@RequestParam("fileName") MultipartFile file) throws IOException
	{
		System.out.println("进入uploadPicture函数啦！！！！");
		String msg = StringUtils.STR_EMPTY;
		
		String equipmentName = equipmentInfoService.findVi(equipmentId).getEquipmentName();
		//图片的名字用设备Id和Name一起命名
		//一个设备保存三张图片，以备一条生产线中需要多个同种设备时可以正常显示设备图片
		String fileName = equipmentId+equipmentName+".png";
		String fileName1 = equipmentId+equipmentName+"2.png";
		String fileName2 = equipmentId+equipmentName+"3.png";
        try {  
        	/*
    		 * 1、将前台传过来的MultipartFile存至指定URL
    		 * */
        	String uploadFolderName = "resources\\assets\\images\\HS\\";
        	//String curProjectPath = request.getSession().getServletContext().getRealPath("/");  
        	String curProjectPath = "G:\\projects\\pllm备份\\pllm-web\\src\\main\\webapp\\";
        	String saveDirectoryPath = curProjectPath + uploadFolderName;
        	File saveDirectory = new File(saveDirectoryPath);
        	if (!file.isEmpty()) { 
        		//一个file只能执行一次transferTo()方法，file就被清空
                file.transferTo(new File(saveDirectory, fileName)); 
            }
        	/*
        	 * 2、复制文件为三份
        	 * */
        	File filetemp = new File(saveDirectoryPath+fileName);
        	File file1 = new File(saveDirectoryPath+fileName1);
        	File file2 = new File(saveDirectoryPath+fileName2);
        	FileUtils.copyFile(filetemp, file1);
        	FileUtils.copyFile(filetemp, file2);
        	/*
        	 * 3、将图片的URL存放至MySQL中
        	 * */
        	msg = equipmentInfoService.insertPicture(equipmentId, saveDirectoryPath+fileName);
        	System.out.println("Project real path ["+ saveDirectoryPath+fileName +"]");
            return msg;
        }catch (Exception e) {  
            return msg;  
        } 
	}
}
