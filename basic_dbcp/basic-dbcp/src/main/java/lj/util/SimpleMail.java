package lj.util;

public class SimpleMail{
	private String mailSubject;
	private String mailContent;
	private String[] attachmentPaths;


	

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	
	public String[] getAttachmentPaths(){
		return attachmentPaths;
	}

	public void setAttachmentPaths(String[] attachmentPaths) {
		this.attachmentPaths = attachmentPaths;
	}

	public SimpleMail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SimpleMail(String mailSubject, String mailContent, String[] attachmentPaths) {
		super();
		this.mailSubject = mailSubject;
		this.mailContent = mailContent;
		this.attachmentPaths = attachmentPaths;
	}

	public SimpleMail(String mailSubject, String mailContent) {
		this(mailSubject, mailContent, new String[0]);
	}

}