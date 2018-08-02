package lj.model.maintain;

import java.util.Date;

public class WriteoffInfo {
	private Long  writeoffId;
	public Long getWriteoffId() {
		return writeoffId;
	}
	public void setWriteoffId(Long writeoffId) {
		this.writeoffId = writeoffId;
	}
	private Long  userEquipmentId;
	public Long getUserEquipmentId() {
		return userEquipmentId;
	}
	public void setUserEquipmentId(Long userEquipmentId) {
		this.userEquipmentId = userEquipmentId;
	}
	private Date  writeoffTime;
	public Date getWriteoffTime() {
		return writeoffTime;
	}
	public void setWriteoffTime(Date writeoffTime) {
		this.writeoffTime = writeoffTime;
	}
	private String  writeoffType;
	public String getWriteoffType() {
		return writeoffType;
	}
	public void setWriteoffType(String writeoffType) {
		this.writeoffType = writeoffType;
	}
	private String  writeoffMemo;
	public String getWriteoffMemo() {
		return writeoffMemo;
	}
	public void setWriteoffMemo(String writeoffMemo) {
		this.writeoffMemo = writeoffMemo;
	}
	
	public  WriteoffInfo() {
		super();
	}
	
	public  WriteoffInfo(
	  Long writeoffId,Long userEquipmentId,Date writeoffTime,String writeoffType,String writeoffMemo){
		super();
		this.writeoffId= writeoffId;
		this.userEquipmentId= userEquipmentId;
		this.writeoffTime= writeoffTime;
		this.writeoffType= writeoffType;
		this.writeoffMemo= writeoffMemo;
	}
}