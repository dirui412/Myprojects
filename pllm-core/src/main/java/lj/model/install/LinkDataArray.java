package lj.model.install;

import java.util.Arrays;

public class LinkDataArray {
	//Linkinfo
		private String  from;
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		private String  to;
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
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
		private String[]  points;
		public String[] getPoints() {
			return points;
		}
		public void setPoints(String[] points) {
			this.points = points;
		}
		public LinkDataArray(String from, String to, String fromPort, String toPort, String[] points) {
			super();
			this.from = from;
			this.to = to;
			this.fromPort = fromPort;
			this.toPort = toPort;
			this.points = points;
		}
		public LinkDataArray() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "LinkDataArray [from=" + from + ", to=" + to + ", fromPort=" + fromPort + ", toPort=" + toPort
					+ ", points=" + Arrays.toString(points) + "]";
		}
		
}
