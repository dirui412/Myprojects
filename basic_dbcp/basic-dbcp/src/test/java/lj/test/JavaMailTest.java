package lj.test;

import java.io.File;

import lj.util.MailUtils;
import lj.util.SimpleMail;

public class JavaMailTest {

	public static void main(String[] args) {
		String content="GFS:19N,14N,108E,116E|0.25,0.25|0,12,24..72|WIND,PRESS,APCP,TCDC,AIRTMP,HTSGW,WVPER,WVDIR,SEATMP,GUST,CAPE";
		MailUtils.sendMail("samlv2000@163.com", "xxxxxxxxxx", "query@saildocs.com", "query grib",content);
		SimpleMail[] mails=MailUtils.receiveNewSimpleMessages("samlv2000@163.com", "xxxxxxxxxxxxxx","d:");
		for(SimpleMail mail:mails)
		{
			System.out.println("subject:"+mail.getMailSubject());
			System.out.println("content:"+mail.getMailContent());
			for(String fileName:mail.getAttachmentPaths())
			   System.out.println("attachment:"+fileName);
		}
	}

}
