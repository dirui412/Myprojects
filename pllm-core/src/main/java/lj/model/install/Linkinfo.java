package lj.model.install;

public class Linkinfo {
	private Long  linkId;
	public Long getLinkId() {
		return linkId;
	}
	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}
	private Long  userProductLineId;
	public Long getUserProductLineId() {
		return userProductLineId;
	}
	public void setUserProductLineId(Long userProductLineId) {
		this.userProductLineId = userProductLineId;
	}
	private String  fromKey;
	public String getFromKey() {
		return fromKey;
	}
	public void setFromKey(String fromKey) {
		this.fromKey = fromKey;
	}
	private String  toKey;
	public String getToKey() {
		return toKey;
	}
	public void setToKey(String toKey) {
		this.toKey = toKey;
	}
	private String  fromPort;
	public String getFromPort() {
		return fromPort;
	}
	public void setFromPort(String fromPort) {
		this.fromPort = fromPort;
	}
	private String  toPort;
	public String getToPort() {
		return toPort;
	}
	public void setToPort(String toPort) {
		this.toPort = toPort;
	}
	private Double  point1;
	public Double getPoint1() {
		return point1;
	}
	public void setPoint1(Double point1) {
		this.point1 = point1;
	}
	private Double  point2;
	public Double getPoint2() {
		return point2;
	}
	public void setPoint2(Double point2) {
		this.point2 = point2;
	}
	private Double  point3;
	public Double getPoint3() {
		return point3;
	}
	public void setPoint3(Double point3) {
		this.point3 = point3;
	}
	private Double  point4;
	public Double getPoint4() {
		return point4;
	}
	public void setPoint4(Double point4) {
		this.point4 = point4;
	}
	private Double  point5;
	public Double getPoint5() {
		return point5;
	}
	public void setPoint5(Double point5) {
		this.point5 = point5;
	}
	private Double  point6;
	public Double getPoint6() {
		return point6;
	}
	public void setPoint6(Double point6) {
		this.point6 = point6;
	}
	private Double  point7;
	public Double getPoint7() {
		return point7;
	}
	public void setPoint7(Double point7) {
		this.point7 = point7;
	}
	private Double  point8;
	public Double getPoint8() {
		return point8;
	}
	public void setPoint8(Double point8) {
		this.point8 = point8;
	}
	private Double  point9;
	public Double getPoint9() {
		return point9;
	}
	public void setPoint9(Double point9) {
		this.point9 = point9;
	}
	private Double  point10;
	public Double getPoint10() {
		return point10;
	}
	public void setPoint10(Double point10) {
		this.point10 = point10;
	}
	private Double  point11;
	public Double getPoint11() {
		return point11;
	}
	public void setPoint11(Double point11) {
		this.point11 = point11;
	}
	private Double  point12;
	public Double getPoint12() {
		return point12;
	}
	public void setPoint12(Double point12) {
		this.point12 = point12;
	}
	
	public  Linkinfo() {
		super();
	}
	
	public  Linkinfo(
			Long linkId,Long userProductLineId,String fromKey,String toKey,String fromPort,String toPort,Double point1,Double point2,Double point3,Double point4,Double point5,Double point6,Double point7,Double point8,Double point9,Double point10,Double point11,Double point12){
		super();
		this.linkId= linkId;
		this.userProductLineId= userProductLineId;
		this.fromKey= fromKey;
		this.toKey= toKey;
		this.fromPort= fromPort;
		this.toPort= toPort;
		this.point1= point1;
		this.point2= point2;
		this.point3= point3;
		this.point4= point4;
		this.point5= point5;
		this.point6= point6;
		this.point7= point7;
		this.point8= point8;
		this.point9= point9;
		this.point10= point10;
		this.point11= point11;
		this.point12= point12;
	}
	@Override
	public String toString() {
		return "Linkinfo [linkId=" + linkId + ", userProductLineId=" + userProductLineId + ", fromKey=" + fromKey
				+ ", toKey=" + toKey + ", fromPort=" + fromPort + ", toPort=" + toPort + ", point1=" + point1
				+ ", point2=" + point2 + ", point3=" + point3 + ", point4=" + point4 + ", point5=" + point5
				+ ", point6=" + point6 + ", point7=" + point7 + ", point8=" + point8 + ", point9=" + point9
				+ ", point10=" + point10 + ", point11=" + point11 + ", point12=" + point12 + "]";
	}
	
}