package autoMail;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import frs_cls.JdbcConnect;

public class MailCompose {
	Properties props = new Properties();
	boolean debug = false;

	

	   Connection con=null;



	public void m1(HashSet<String> uhs, String cdate, String pdate,
			String area_name, String area_huma_email,String sid) throws ClassNotFoundException,
			SQLException {
		System.out.println(sid);
		props.put("mail.smtp.host", "smtp.basixindia.com"); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.port", "2525"); 
		
		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(props, auth);
		session.setDebug(debug);
		try {
			String username = "xxxxxxx"; 
			String password = "xxxxxxx";
			Message msg = new MimeMessage(session); 
			System.out.println("State Head Email :" + area_huma_email);//
			InternetAddress addressFrom = new InternetAddress("frs@basixindia.com"); 
			//InternetAddress addressAdm = new InternetAddress("sujatha@basixindia.com");
			String sql1="select  (select USERNAME from FRS_USER where HUMA_ID=am.HUMA_ID),(select PASSWORD from FRS_USER where HUMA_ID=am.HUMA_ID) FROM AREA_MSTR am where am.HUMA_ID='"+sid+"'";
			//Connection con1 = new JdbcConnect().getConnection();
			Statement st1 = con.createStatement();
			ResultSet urs1= st1.executeQuery(sql1);
			while (urs1.next()) {
			username=urs1.getString(1);
			password=urs1.getString(2); 
			}
			//InternetAddress addressCC = new InternetAddress("rajasekhar.m@basixindia.com");
			//msg.setRecipient(Message.RecipientType.CC, addressCC);
			 
			msg.setFrom(addressFrom);
			InternetAddress addressTo = new InternetAddress(area_huma_email);
			msg.setRecipient(Message.RecipientType.TO, addressTo);
			msg.setRecipient(Message.RecipientType.CC, addressFrom);//Added CC by Rajashekhar
			//msg.setRecipient(Message.RecipientType.CC, addressAdm);//Added CC by Rajashekhar
			msg.setSubject("Customer connect non performer List ");
			StringBuffer body1 = new StringBuffer(
							"<br><b>Dear State Head,</b><br><br>"
							+ "<font color='#0B4599'>Please find below the list of Units/Centers in "+area_name+" region which have not\n"
							+"conducted any Customer Connect activities in the period  "
							+ pdate + " To " + cdate + "<br>");
			body1.append("<BR>This is to help you motivate them to do so. You can check the active Units/Centers and the activities conducted by logging in at" +
					" http://frs.basixindia.com/ <br>Username: "+username+"<br>Password: "+password+"<br><br>");
			body1.append("<table border><TR bgcolor='#C2DFFF'><th>Sno</th><th>UnitName</th><th>Unit Mailid</th><th>Unit ContactNo</th><th>Unit Head</th><th>UH Mail</th><th>UH ContactNo</th></tr>");// border
			Connection con = new JdbcConnect().getConnection();
			Statement st = con.createStatement();
			ResultSet urs = null;
			Iterator<String> it = uhs.iterator();
			int sno = 1;
			while (it.hasNext()) {
				String BSFLUNIT_ID = it.next();  
				String sql = "select nvl(bsflunit_name,' ') as bsflunit_name,nvl(bsflunit_email,' ') as bsflunit_email,nvl(bsflunit_phone,' ') as bsflunit_phone,nvl((select HUMA_FNAME||' '||HUMA_lNAME from huma_mstr where huma_id=u.huma_id),' ') as huma_name,nvl((select huma_email from huma_mstr where huma_id=u.huma_id),' ') as huma_email,nvl((select huma_mobile from huma_mstr where huma_id=u.huma_id),' ') as huma_mobile from bsflunit_mstr u where u.BSFLUNIT_UCODE='"+ BSFLUNIT_ID + "' order by bsflunit_name";
				urs = st.executeQuery(sql);
				while (urs.next()) {
				body1.append("<tr><td>" + (sno++) + "</td>" + "<td>"
					 + urs.getString("bsflunit_name") + "</td>" + "<td>" + urs.getString("bsflunit_email")
					 + "</td>" + "<td>" + urs.getString("bsflunit_phone") + "</td>" + "<td>"
					 + urs.getString("huma_name") + "</td>" + "<td>" + urs.getString("huma_email") + "</td>"
					 + "<td>" + urs.getString("huma_mobile") + "</td></tr>");
				}
			}
			body1.append("</table>");
			body1.append("<br><br><font color='#0B4599'>Note: This is system generated mail. Please do not reply to this mail, In case of any clarification please contact helpdesk@basixindia.com  / IT support team at HYD.<BR><BR>Best Regards,<BR>System Development Team,<br>HYD.</font>");
			msg.setContent(body1.toString(), "text/html");
			msg.saveChanges();
			Transport.send(msg);
			//System.out.println("All Messages sent");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}

class SMTPAuthenticator extends javax.mail.Authenticator {
	public PasswordAuthentication getPasswordAuthentication() {
		Properties configProp = new Properties();
		//Getting the username and Password from the Properties file
		InputStream inn = this.getClass().getResourceAsStream("/prop/Credentials.properties");
        try {
            configProp.load(inn);
        } catch (IOException e) {
            e.printStackTrace();
        }

		String username = configProp.getProperty("mail.username");
		String password = configProp.getProperty("mail.password");
		
        /*
		String username = "frs@basixindia.com";
		String password = "Windows@7";
		*/return new PasswordAuthentication(username, password);
	}
}