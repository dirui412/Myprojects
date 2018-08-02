package lj.model.install;

public class NodeDataArray {
	//UserEquipment
		private String  text;
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		private String hiddenId;
		public String getHiddenId() {
			return hiddenId;
		}
		public void setHiddenId(String hiddenId) {
			this.hiddenId = hiddenId;
		}
		
		private String  key;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		private String  loc;
		public String getLoc() {
			return loc;
		}
		public void setLoc(String loc) {
			this.loc = loc;
		}
		public NodeDataArray(String text, String hiddenId, String key, String loc) {
			super();
			this.text = text;
			this.hiddenId = hiddenId;
			this.key = key;
			this.loc = loc;
		}
		public NodeDataArray() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "NodeDataArray [text=" + text + ", hiddenId=" + hiddenId + ", key=" + key + ", loc=" + loc + "]";
		}
		
}
