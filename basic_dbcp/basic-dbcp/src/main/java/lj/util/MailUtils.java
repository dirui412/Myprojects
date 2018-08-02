package lj.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class MailUtils {

	private static String SMTP_ADDRESS = "smtp.163.com";
	private static String POP3_ADDRESS = "pop3.163.com";

	/**
	 * 创建邮件
	 * 
	 * @param session
	 * @param fromMailAddress
	 * @param toMailAddress
	 * @param subject
	 * @param content
	 * @return
	 */
	private static Message createSimpleMail(Session session, String fromMailAddress, String toMailAddress,
			String subject, String content) {
		Message message = new MimeMessage(session);

		try {
			// 设置发件人
			message.setFrom(new InternetAddress(fromMailAddress));
			// 设置收件人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMailAddress));
			message.setSubject(subject);
			message.setContent(content, "text/html;charset=utf-8");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;
	}

	/**
	 * 发送邮件
	 * 
	 * @param fromMailAddress
	 * @param fromAuthCode
	 * @param toMailAddress
	 * @param subject
	 * @param content
	 * @return
	 */
	public static boolean sendMail(String fromMailAddress, String fromAuthCode, String toMailAddress, String subject,
			String content) {
		// 1-设置smtp属性
		System.setProperty("java.net.preferIPv4Stack", "true");
		Properties props = new Properties();
		// 开启debug调试
		props.setProperty("mail.debug", "true");
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		// 设置邮件服务器主机名
		props.setProperty("mail.host", SMTP_ADDRESS);
		// 发送邮件协议名称
		props.setProperty("mail.transport.protocol", "smtp");

		// 使用java发送邮件5步骤
		try {
			// 1.创建sesssion
			Session session = Session.getInstance(props);
			// 2.通过session获取Transport对象（发送邮件的核心API）
			Transport ts = session.getTransport();
			// 3.通过邮件用户名密码链接
			ts.connect(fromMailAddress, fromAuthCode);
			// 4.创建邮件
			Message msg = createSimpleMail(session, fromMailAddress, toMailAddress, subject, content);
			// 5.发送电子邮件
			ts.sendMessage(msg, msg.getAllRecipients());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private static boolean isNew(Message mimeMessage) throws MessagingException {
		boolean isnew = false;
		Flags flags = mimeMessage.getFlags();
		Flags.Flag[] flag = flags.getSystemFlags();
		for (int i = 0; i < flag.length; i++) {
			if (flag[i] == Flags.Flag.RECENT) {
				isnew = true;
				break;
			}
		}
		return isnew;
	}

	/**
	 * 接收新邮件
	 * 
	 * @param mailAddress
	 * @param mailAuthCode
	 * @return
	 */
	public static SimpleMail[] receiveNewSimpleMessages(String mailAddress, String mailAuthCode, String attchPath) {
		if(!(attchPath==null || attchPath.equals("")))
			if(attchPath.endsWith(File.separator)==false)
				attchPath=attchPath+File.separator;
		// 1-设置smtp属性
		System.setProperty("java.net.preferIPv4Stack", "true");
		// 准备连接服务器的会话信息
		Properties props = new Properties();
		// 开启debug调试
		props.setProperty("mail.debug", "true");
		props.setProperty("mail.store.protocol", "pop3"); // 协议
		props.setProperty("mail.pop3.port", "110"); // 端口
		props.setProperty("mail.pop3.host", POP3_ADDRESS); // pop3服务器
		try {
			// 创建Session实例对象
			Session session = Session.getInstance(props);
			Store store = session.getStore("pop3");
			store.connect(mailAddress, mailAuthCode);
			// 获得收件箱
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE); // 打开收件箱
			// 得到收件箱中的所有邮件,并解析
			Message[] messages = folder.getMessages();
			if (messages.length > 0) {
				SimpleMail[] mails = parseMessage(attchPath, messages[messages.length - 1]);
				return mails;
			}
			folder.close();
			store.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new SimpleMail[0];
		}
		return new SimpleMail[0];
	}

	/**
	 * 解析邮件
	 * 
	 * @param messages
	 *            要解析的邮件列表
	 */
	public static SimpleMail[] parseMessage(String attchPath, Message... messages)
			throws MessagingException, IOException {
		if (messages == null || messages.length < 1)
			return new SimpleMail[0];

		List<SimpleMail> mails = new ArrayList<SimpleMail>();
		try {
			// 解析所有邮件
			for (Message msg : messages) {
				StringBuffer content = new StringBuffer();
				getMailTextContent(msg, content);
				List<String> filePaths=new ArrayList<String>();
				boolean isContainerAttachment = isContainAttach(msg);
				if (!(attchPath == null || attchPath.equals("") == true) && isContainerAttachment == true) {
					filePaths=saveAttachment(msg, attchPath);
				}
				SimpleMail mail = new SimpleMail(msg.getSubject(), content.toString(),filePaths.toArray(new String[0]));
				mails.add(mail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mails.toArray(new SimpleMail[0]);
	}

	/**
	 * 获得邮件文本内容
	 * 
	 * @param part
	 *            邮件体
	 * @param content
	 *            存储邮件文本内容的字符串
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {
		// 如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
		boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
		if (part.isMimeType("text/*") && !isContainTextAttach) {
			content.append(part.getContent().toString());
		} else if (part.isMimeType("message/rfc822")) {
			getMailTextContent((Part) part.getContent(), content);
		} else if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();
			int partCount = multipart.getCount();
			for (int i = 0; i < partCount; i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				getMailTextContent(bodyPart, content);
			}
		}
	}

	/**
	 * 判断邮件中是否包含附件
	 * 
	 * @param msg
	 *            邮件内容
	 * @return 邮件中存在附件返回true，不存在返回false
	 * @throws MessagingException
	 * @throws IOException
	 */
	/**
	 * 判断此邮件是否包含附件
	 */
	private static boolean isContainAttach(Part part) throws Exception {
		boolean attachflag = false;
		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart mpart = mp.getBodyPart(i);
				String disposition = mpart.getDisposition();
				if ((disposition != null)
						&& ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE))))
					attachflag = true;
				else if (mpart.isMimeType("multipart/*")) {
					attachflag = isContainAttach((Part) mpart);
				} else {
					String contype = mpart.getContentType();
					if (contype.toLowerCase().indexOf("application") != -1)
						attachflag = true;
					if (contype.toLowerCase().indexOf("name") != -1)
						attachflag = true;
				}
			}
		} else if (part.isMimeType("message/rfc822")) {
			attachflag = isContainAttach((Part) part.getContent());
		}
		return attachflag;
	}

	/**
	 * 保存附件
	 * 
	 * @param part
	 *            邮件中多个组合体中的其中一个组合体
	 * @param destDir
	 *            附件保存目录
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<String> saveAttachment(Part part, String destDir)
			throws UnsupportedEncodingException, MessagingException, FileNotFoundException, IOException {
		List<String> attachPaths=new ArrayList<String>();
		if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent(); // 复杂体邮件
			// 复杂体邮件包含多个邮件体
			int partCount = multipart.getCount();
			for (int i = 0; i < partCount; i++) {
				// 获得复杂体邮件中其中一个邮件体
				BodyPart bodyPart = multipart.getBodyPart(i);
				// 某一个邮件体也有可能是由多个邮件体组成的复杂体
				String disp = bodyPart.getDisposition();
				if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
					InputStream is = bodyPart.getInputStream();
					saveFile(is, destDir, decodeText(bodyPart.getFileName()));
					attachPaths.add(destDir+decodeText(bodyPart.getFileName()));
				} else if (bodyPart.isMimeType("multipart/*")) {
					List<String> subPaths=saveAttachment(bodyPart, destDir);
					attachPaths.addAll(subPaths);
				} else {
					String contentType = bodyPart.getContentType();
					if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {
						saveFile(bodyPart.getInputStream(), destDir, decodeText(bodyPart.getFileName()));
						attachPaths.add(destDir+decodeText(bodyPart.getFileName()));
					}
				}
			}
		} else if (part.isMimeType("message/rfc822")) {
			List<String> subPaths=saveAttachment((Part) part.getContent(), destDir);
			attachPaths.addAll(subPaths);
		}
		return attachPaths;
	}

	/**
	 * 读取输入流中的数据保存至指定目录
	 * 
	 * @param is
	 *            输入流
	 * @param fileName
	 *            文件名
	 * @param destDir
	 *            文件存储目录
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void saveFile(InputStream is, String destDir, String fileName)
			throws FileNotFoundException, IOException {
		BufferedInputStream bis = new BufferedInputStream(is);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(destDir + fileName)));
		int len = -1;
		while ((len = bis.read()) != -1) {
			bos.write(len);
			bos.flush();
		}
		bos.close();
		bis.close();
	}

	/**
	 * 文本解码
	 * 
	 * @param encodeText
	 *            解码MimeUtility.encodeText(String text)方法编码后的文本
	 * @return 解码后的文本
	 * @throws UnsupportedEncodingException
	 */
	private static String decodeText(String encodeText) throws UnsupportedEncodingException {
		if (encodeText == null || "".equals(encodeText)) {
			return "";
		} else {
			return MimeUtility.decodeText(encodeText);
		}
	}

}
