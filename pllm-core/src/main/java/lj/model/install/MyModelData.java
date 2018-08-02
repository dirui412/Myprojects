package lj.model.install;

import java.util.Arrays;

public class MyModelData {
	//其他的属性
	private String linkFromPortIdProperty;
	public String getLinkFromPortIdProperty() {
		return linkFromPortIdProperty;
	}
	public void setLinkFromPortIdProperty(String linkFromPortIdProperty) {
		this.linkFromPortIdProperty = linkFromPortIdProperty;
	}
	
	private String linkToPortIdProperty;
	public String getLinkToPortIdProperty() {
		return linkToPortIdProperty;
	}
	public void setLinkToPortIdProperty(String linkToPortIdProperty) {
		this.linkToPortIdProperty = linkToPortIdProperty;
	}
	
	private ModelData modelData;
	public ModelData getModelData() {
		return modelData;
	}
	public void setModelData(ModelData modelData) {
		this.modelData = modelData;
	}
	
	private NodeDataArray[] nodeDataArray;
	public NodeDataArray[] getNodeDataArray() {
		return nodeDataArray;
	}
	public void setNodeDataArray(NodeDataArray[] nodeDataArray) {
		this.nodeDataArray = nodeDataArray;
	}
	
	private LinkDataArray[] linkDataArray;
	public LinkDataArray[] getLinkDataArray() {
		return linkDataArray;
	}
	public void setLinkDataArray(LinkDataArray[] linkDataArray) {
		this.linkDataArray = linkDataArray;
	}
	
	
	public MyModelData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyModelData(String linkFromPortIdProperty, String linkToPortIdProperty, ModelData modelData,
			NodeDataArray[] nodeDataArray, LinkDataArray[] linkDataArray) {
		super();
		this.linkFromPortIdProperty = linkFromPortIdProperty;
		this.linkToPortIdProperty = linkToPortIdProperty;
		this.modelData = modelData;
		this.nodeDataArray = nodeDataArray;
		this.linkDataArray = linkDataArray;
	}
	@Override
	public String toString() {
		return "MyModelData [linkFromPortIdProperty=" + linkFromPortIdProperty + ", linkToPortIdProperty="
				+ linkToPortIdProperty + ", modelData=" + modelData + ", nodeDataArray="
				+ Arrays.toString(nodeDataArray) + ", linkDataArray=" + Arrays.toString(linkDataArray) + "]";
	}
}
