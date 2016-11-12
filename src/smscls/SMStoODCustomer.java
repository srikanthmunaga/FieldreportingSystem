package smscls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import static frs_cls.TargetVsAchievementSMS.*;

import frs_cls.JdbcConnect;

public class SMStoODCustomer extends HttpServlet {
	public JdbcConnect jc;
	public static String line = "";// comment it later
	private Log SMSTRACK = LogFactory.getLog("SMSTRACK");
	static BufferedWriter out;
	private static final long serialVersionUID = 1L;

	public SMStoODCustomer() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	Connection con, conn;
	private static Properties configProp = new Properties();
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/SMSsendsuccess.jsp");
		RequestDispatcher rd1 = getServletContext().getRequestDispatcher(
				"/SMSfaiil.jsp");
		// pleasewait.jsp
		// RequestDispatcher
		// rd=getServletContext().getRequestDispatcher("/pleasewait.jsp");
		try {
			HttpSession ses = request.getSession(false);
			String URL = "jdbc:oracle:thin:@172.16.1.56:1521:DSTAT";
			String USER = "DSTAT_NOV13";
			String PASS = "TATSD";
			// String URL = "jdbc:oracle:thin:@172.16.1.189:1521:srinu";
			// String USER = "STEMSLIVE";
			// String PASS = "STEMSLIVE";
			jc = new JdbcConnect();
			// Connection conn =null;
			Driver myDriver = new oracle.jdbc.driver.OracleDriver();
			DriverManager.registerDriver(myDriver);
			conn = DriverManager.getConnection(URL, USER, PASS);
			// Query for other than AP, ORISSA
			// System.out.println("MSR @DEBUG1");

			// System.out.println(request.getParameter("oddays"));
			String huma_id[] = request.getParameterValues("huma_id");
			// System.out.println(huma_id.length);
			String areaList = (String) ses.getAttribute("areaList");
			String oddays = (String) ses.getAttribute("oddays");
			String unitList = (String) ses.getAttribute("unitList");
			String area_lang = (String) ses.getAttribute("area_lang");

			String msg = null;
			Date today = new Date();
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("ddMMyyyy");
			String date = DATE_FORMAT.format(today);

			System.out.println("Today in dd-MM-yyyy format : " + date);
			String filename = "ODCustomerSMSLog" + date + ".log";
			String workingDir = System.getProperty("user.dir");
			StringTokenizer st = new StringTokenizer(workingDir, "\\");
			String path = "";
			while (st.hasMoreElements()) {
				path += st.nextElement() + "/";
			}

			// Odiya language SMS

			System.out.println(path);
			File file1 = new File(path + "log");
			File file = new File(path + "log/" + filename);
			try {
				if (!file.exists()) {
					file1.mkdirs();
					file.createNewFile();

				}
				OutputStreamWriter writer = new OutputStreamWriter(
						new FileOutputStream(path + "log/" + filename, true),
						"UTF-8");
				out = new BufferedWriter(writer);

			} catch (IOException e) {
				out.write(e.getMessage());
			}
			System.out.println("unit list is :" + unitList);
			// String
// ul="'','006','012','	015','018','023','032','033','034','035','036','041','044','051','053','058','059','060','061','062','063','064','065','067','069','073','080','081','083','085','086','087','088','090','091','092','094','095','098','100','101','102','103','106','107','110','111','112','114','115','116','119','122','124','125','126','127','128','129','131','142','201','205','206','211','216','217','219','222','228','229','230','234','237','238','240','245','246','256','258','260','261','264','265','267','268','269','270','273','274','277','279','281','283','284','287','288','289','290','291','292','293','294','295','297','299','302','308','309','337','801','908','918','932','933','935','941','942','946','948','949','AO','CSC','PF'";
			// String sql =
			// "select * from SMS where PHONE_NO IS NOT NULL and ODDAYS >=90 AND UNIT_CODE IN ("+unitList+
			// ")";

			String sql = "select * from SMS where PHONE_NO IS NOT NULL and ODDAYS >="
					+ oddays + " AND UNIT_CODE IN (" + unitList + ")";
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ResultSet rs = null;
			try {
				rs = ps1.executeQuery();
			} catch (Exception e) {
				out.write(e.getMessage());
			}
			con=jc.getConnection();
			PreparedStatement ps3 = con.prepareStatement("insert into SMS_ODCUST(SMS_ODCUST_CUST_NAME,SMS_ODCUST_LOAN_NO,SMS_ODCUST_OD_DAYS,SMS_ODCUST_OSAMT,SMS_ODCUST_SMS,SMS_ODCUST_MOBILE_NO) values(?,?,?,?,?,?)");
			//con.close();
			while (rs.next()) {
				
				String clientname=rs.getString("CLIENT_NAME");
				long od_days=rs.getInt("ODDAYS");
				long loanno=rs.getInt("LOAN_NO");
				if (area_lang.equals("H")) {
					StringBuffer sb = new StringBuffer(200);
					sb.append("Namaskar ");
					sb.append(rs.getString("CLIENT_NAME"));
					sb.append(", Basix loan no ");
					sb.append(rs.getInt("LOAN_NO"));
					sb.append(", ke anusar aap ne ");
					sb.append(rs.getInt("ODDAYS"));
					sb.append(" dino se Rs.");
					sb.append(rs.getInt("OSPRNAMT") + rs.getInt("V_CINT")
							+ rs.getInt("OSICRAMT"));// total OD
					sb.append(" ka bhuktaan nahi kiya hai. Kripya iska bhuktaan turant Karen. Dhanyavaad.");
					sb.trimToSize();
					msg = sb.toString();
				} else if (area_lang.equals("O")) {
					StringBuffer sb = new StringBuffer(200);
					sb.append("Namaskar ");
					sb.append(rs.getString("CLIENT_NAME"));
					sb.append(", Basix ru neithiba loan no ");
					sb.append(rs.getInt("LOAN_NO"));
					sb.append(", anusare apana ");
					sb.append(rs.getInt("ODDAYS"));
					sb.append(" dina hela  Rs.");
					sb.append(rs.getInt("OSPRNAMT") + rs.getInt("V_CINT")
							+ rs.getInt("OSICRAMT"));// total OD
					sb.append(" deinahanti. Dayakari sighra ferasta karantu. Dhanyavaad.");
					sb.trimToSize();
					msg = sb.toString();

				}
				/*
				 * else if (area_lang.equals("T")) { sb.append("Namaskar ");
				 * sb.append(rs.getString("CLIENT_NAME"));
				 * sb.append(", Basix ru neithiba loan no ");
				 * sb.append(rs.getInt("LOAN_NO"));
				 * sb.append(", anusare apana ");
				 * sb.append(rs.getInt("ODDAYS")); sb.append(" dina hela  Rs.");
				 * sb.append(rs.getInt("OSPRNAMT") + rs.getInt("V_CINT") +
				 * rs.getInt("OSICRAMT"));// total OD sb.append(
				 * " deinahanti. Dayakari sighra ferasta karantu. Dhanyavaad.");
				 * sb.trimToSize();
				 * 
				 * }
				 */else {
					msg = null;
					System.out.println("please select on language ");

				}
				// mobile number length checking
				// String line="Message not sent";
				if (msg == null)
					break;
				String mobilenumber = rs.getString("PHONE_NO");
				long osamt = rs.getInt("OSPRNAMT") + rs.getInt("V_CINT")
						+ rs.getInt("OSICRAMT");
				if (mobilenumber != null && mobilenumber != "")
					if (mobilenumber.toString().length() >= 10 && osamt > 0) {
						System.out.println(mobilenumber + "     "
								+ msg.toString());
						out.write("[" + new Date() + "]; " + line
								+ "; Mobile Number= " + mobilenumber
								+ "; Unit Name=" + rs.getString("UNIT_NAME")
								+ "-" + rs.getString("UNIT_CODE")
								+ "; Message= " + msg.toString()+ "; ODDAYS= " + od_days+ "; OSAMT= " + osamt+ "; LoanNo= " + loanno+ "; Client Name= " + clientname);//od_days
						out.newLine();
						//String sql1 = "insert into SMS_ODCUST(SMS_ODCUST_CUST_NAME,SMS_ODCUST_LOAN_NO,SMS_ODCUST_OD_DAYS,SMS_ODCUST_OSAMT,SMS_ODCUST_SMS,SMS_ODCUST_MOBILE_NO) values(?,?,?,?,?,?)";
						//PreparedStatement ps3 = con.prepareStatement(sql1);
						
						//String sql1 = "insert into SMS_ODCUST(SMS_ODCUST_CUST_NAME,SMS_ODCUST_LOAN_NO,SMS_ODCUST_OD_DAYS,SMS_ODCUST_OSAMT,SMS_ODCUST_SMS,SMS_ODCUST_MOBILE_NO) values(?,?,?,?,?,?)";
						//PreparedStatement ps3 = con.prepareStatement("insert into SMS_ODCUST(SMS_ODCUST_CUST_NAME,SMS_ODCUST_LOAN_NO,SMS_ODCUST_OD_DAYS,SMS_ODCUST_OSAMT,SMS_ODCUST_SMS,SMS_ODCUST_MOBILE_NO) values(?,?,?,?,?,?)");
						try{
						ps3.setString(1, clientname);
						ps3.setLong(2, loanno);
						ps3.setLong(3, od_days);
						ps3.setLong(4, osamt);
						ps3.setString(5, msg.toString());
						ps3.setString(6, mobilenumber);// new String((String)
						ps3.executeUpdate();
						}
						catch (Exception e) {
							// TODO: handle exception
							System.out.println("Exception during SMS trace table entry");
							e.printStackTrace();
						}
						//con.commit();
						// sendTargetVsAchievementSMS(mobilenumber,msg.toString(),
						//rs.getString("UNIT_NAME"),rs.getString("UNIT_CODE"),osamt,loanno,clientname,od_days);
					}// if
			}// while
			con.close();
			conn.close();
			out.close();
			
			if (area_lang.equals("H") || area_lang.equals("O"))
				rd.forward(request, response);
			else
				rd1.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			out.write(e.getMessage());
		} catch (IOException e) {
			out.write(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					/**/// System.out.println("con="+con);
					con.close();
					/**/// System.out.println("con after close="+con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (conn != null)
				try {
					/**/// System.out.println("con="+con);
					conn.close();
					/**/// System.out.println("con after close="+con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	//InputStream in = this.getClass().getResourceAsStream("/prop/Credentials.properties");
	public static void sendTargetVsAchievementSMS(String mobilenumber,
			String message, String unitname, String unitcide, long osamt ,long loanno , String clientname,long od_days)
			throws IOException {
		// message="Hi_SMSreceiver_";
		// message= URLEncoder.encode(message, "UTF-8");
		InputStream in2 = SMStoODCustomer.class.getResourceAsStream("/prop/Credentials.properties");
        try {
            configProp.load(in2);
        } catch (IOException e) {
            e.printStackTrace();
        }
		String urlString;
        String domainNameUrl = configProp.getProperty("sms.domainNameUrl");
        String user = configProp.getProperty("sms.user");
        String passwd = configProp.getProperty("sms.passwd");
        String sid = configProp.getProperty("sms.sid");
        String mtype = configProp.getProperty("sms.mtype");
		try {
			urlString = domainNameUrl + "?User=" + user + "&passwd=" + passwd
					+ "&mobilenumber=" + mobilenumber.trim() + "&message="
					+ URLEncoder.encode(message, "UTF-8") + "&sid=" + sid
					+ "&mtype=" + mtype;

			System.out.println("urlString=" + urlString);
			URL url = new URL(urlString);

			URLConnection urlConn = url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));

			String line = "";
			while ((line = in.readLine()) != null) {

				// System.out.println(line);
				// smslog.info(line + "; Massage="+ message + "; Mobilenumber="
				// + mobilenumber);
				// System.out.println("["+new
				// Date()+"]; "+line+"; Mobile Number= "+mobilenumber+"; Unit Name="+unitname+"-"+unitcide+"; Message= "+message);
				out.write("[" + new Date() + "]; " + line + "; Mobile Number= "
						+ mobilenumber + "; Unit Name=" + unitname + "-"
						+ unitcide + "; Message= " + message+ "; ODDAYS= " + od_days+ "; OSAMT= " + osamt+ "; LoanNo= " + loanno+ "; Client Name= " + clientname);
				out.newLine();
			}
		} catch (MalformedURLException e) {
			out.write(e.getMessage());
		} catch (IOException e) {
			out.write(e.getMessage());
		} catch (Exception e) {
			out.write(e.getMessage());
		}

	}

}
