package lj.model.install;

public class ModelData {
	//Productlineposition
	private String  position;
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public ModelData(String position) {
		super();
		this.position = position;
	}
	public ModelData() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ModelData [position=" + position + "]";
	}
	
}
