package smscls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import frs_cls.JdbcConnect;
import frs_cls.TargetVsAchievementSMS;
import frs_cls.DateUtil;

/**
 * Servlet implementation class SMSreceiver hello
 */

public class SMSreceiver extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private static final char E = 0;
	// private String FRS_noValuesRemarks;
	private String FRS_date;
	private String huma_id;
	private int FRS_village_count;
	private int FRS_Cust_count;
	private int FRS_total_amt;
	private int FRS_total_accounts;
	private int FRS_od_amt;
	private int FRS_od_accounts;
	private int FRS_SDRcust_count = 0;
	private String remarks = "";
	java.sql.Date sd1;
	// private Logger log = Logger.getLogger("FRS");
	private Log smslog = LogFactory.getLog("SMSTRACK1"); // Logger.getLogger("SMSTRACK");
	HashMap<String, String> hs = new HashMap<String, String>();

	static JdbcConnect js = new JdbcConnect();
	Connection con;
	Statement st1 = null, st2 = null, st3 = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SMSreceiver() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// PrintWriter out=response.getWriter();
		/**/// //System.out.println("inside the doGetMethod");
		// Getting sender's phone number and message text
		// response.getWriter().println("hi sreenivasarao");
		// String senderPhone = request.getParameter("sender");
		// String messageText = request.getParameter("text");

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "unused", "deprecation" })
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		// http://localhost:8081/ROD.FRS/SMSreceiver?text=ID.E4614DT.01.10.VL.02.CUS.30.TAC.3.TAMT.550 ODAMT.500.ODCL.01&sender=+919396812884&scts=2013-07-10 11:39:22
		// http://localhost:8081/ROD.FRS/SMSreceiver?text=ID.E5036DT.29.01.VL.02.CUS.30.TAC.3.TAMT.550 ODAMT.500.ODCL.01&sender=+919396812884&scts=2013-07-10 11:39:22
		// http://localhost:8081/ROD.FRS/SMSreceiver?text=ID.E4614DT.02.10.VL.04.CUS.30.TAC.4.TAMT.660
		// ODAMT.610. ODCL.01 SDR.0&sender=+919396812884&scts=2013-07-10
		// 11:50:23
		// http://localhost:8081/ROD.FRS/SMSreceiver?text=ID.E4614DT.0310
		// Leave&sender=+919396812884&scts=2013-07-10 11:40:18
		// http://localhost:8081/ROD.FRS/SMSreceiver?text=ID.E4614DT.04.10
		// Leave&sender=+0&scts=2013-07-10 11:40:19
		//
		// http://localhost:8081/ROD.FRS/SMSreceiver?text=ID.E4703DT.04.10.VL.07.CUS.30.TAC.2.TAMT.710
		// ODAMT.705.ODCL.01&sender=+919396812884&scts=2013-07-10 11:39:22
		// http://localhost:8081/ROD.FRS/SMSreceiver?text=ID.E4703DT.0310.VL.01.CUS.30.TAC.5.TAMT.815
		// ODAMT.810. ODCL.01 SDR.0&sender=+919396812884&scts=2013-07-10
		// 11:50:23
		// http://localhost:8081/ROD.FRS/SMSreceiver?text=ID.E4703DT.02.10
		// Meeting&sender=+919396812884&scts=2013-07-10 11:40:18
		// http://localhost:8081/ROD.FRS/SMSreceiver?text=ID.E4703DT.01.10.
		// VL.01.CUS.30.TAC.5.TAMT.815 ODAMT.810. ODCL.01
		// SDR01&sender=+919396812884&scts=2013-07-10 11:50:24

		String sms_text1 = request.getParameter("text");// SMS text
		String sender_number = request.getParameter("sender");// Sender phone
																// number
		String sent_dt1 = request.getParameter("scts");// SMS center time when
														// the sender sent the
														// message

		// ORIGINAL
		// ID.E4853,DT.0805,VL.1,CUS.25,TAC.7,TAMT.6700,UHLOG_ODAMT.6700,ODCL.6
		// ID.E4889,DT.2205,VL.2,CUS.25,TAC.0,TAMT.0,UHLOG_ODAMT.0,ODCL.0
		// String sms_text1
		// ="ID.E5357,DT.0907,VL. 02,CUS.29,TAC.0,TAMT.0,ODAMT.0,ODCL.0";
		// //"Id.E5182,Dt.0513 vl.,cus.O18,Tamt.3200O,UHLOG_ODAMT,O,Odcl.0 param9";//
		// dT.01105-id.4992,VL0 CUS..!10,TAC.0,TAMT.10 UHLOG_ODAMT 0 ODCL.0
		// ";//"ID.E5271,DT.0105,VL.1,CUS.25,TAC.5,TAMT.6700,UHLOG_ODAMT.6700,ODCL.6";
		// String sender_number = "+12345678945";
		// String sent_dt1 = "2013-04-25 11:04:40";

		System.out.println("sms_text1=" + sms_text1);
		String logmsg = "; SMS=" + sms_text1 + "; SENDER=" + sender_number
				+ "; SENT DT=" + sent_dt1 + "; ACKNL=";
		// String massage = "Please enter";
		// SMS relaxations after Analysis As on 23May13 starts
		// here-----------------------------
		/**/// System.out.println("Original="+sms_text1);
		/**/// System.out.println("Uppercase="+sms_text1.toUpperCase());//Conversion
		// to upper case
		/**/// System.out.println("Spaces trim using reg exp="+sms_text1.replaceAll("^\\s+",
				// "").replaceAll("\\s+$", ""));//Removing starting & ending non
				// word
		// chars
		/**/// System.out.println("Spaces trim="+sms_text1.trim());//Removing
		// starting & ending non word chars
		/**/// System.out.println("Replacing non word+digit chars="+sms_text1.replaceAll("[^A-Za-z0-9]",
				// "").toUpperCase());//Removing non chars & non digits
		if (sms_text1 == null) {
			out.write("No SMS is supplied, pls check..!");
			return;
		}

		try {
			// -------------------------------Duplicate SMS avoid code starts
			// here----------------------
			/**/// System.out.println("Starting of duplicate SMS check");
			con = js.getConnection();
			String sqld = "select distinct 'duplicate' from frs_recovery where SENDER_NUMBER=to_number('"
					+ sender_number
					+ "') and TO_CHAR(sent_dt- (11/24/60) * 30,'yyyy-MM-dd HH24:mi:ss')='"
					+ sent_dt1 + "'";
			// "select 'duplicate' from frs_recovery where SENDER_NUMBER=to_number('"+sender_number+"') and TO_CHAR(sent_dt,'yyyy-MM-dd HH:mm:ss')='"+sent_dt1+"'";
			st3 = con.createStatement();
			ResultSet rs3 = st3.executeQuery(sqld);
			if (rs3.next()) { /**/// //System.out.println("inside the if of duplicate SMS check");
				st3.close();
				rs3.close();
				// out.write("Duplicate SMS received from No."+sender_number+" with sent time."+sent_dt1);
				smslog.info(logmsg + "Duplicate SMS received from No."
						+ sender_number + " with sent time." + sent_dt1);
				System.out.println("Duplicate SMS received from No."
						+ sender_number + " with sent time." + sent_dt1);
				return;
			}
			// st3.close();
			/**/// System.out.println("Ending of duplicate SMS check");
			// -------------------------------Duplicate SMS avoid code ends
			// here----------------------

			sms_text1 = sms_text1.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
			/**/// System.out.println("Spaces trim="+sms_text1.trim());//Removing
			// starting & ending non word chars
//			System.out.println("SMS with spaces as delimeter="
//					+ sms_text1.replaceAll("ID", " ID.")
//							.replaceAll("DT", " DT.").replaceAll("VL", " VL.")
//							.replaceAll("CUS", " CUS.")
//							.replaceAll("TAC", " TAC.")
//							.replaceAll("TAMT", " TAMT.")
//							.replaceAll("ODAMT", " ODAMT.")
//							.replaceAll("ODCL", " ODCL.")
//							.replaceAll("SDR", " SDR.").trim());// Making SMS as
//																// per our
//																// format
			sms_text1 = sms_text1.replaceAll("ID", " ID.")
					.replaceAll("DT", " DT.").replaceAll("VL", " VL.")
					.replaceAll("CUS", " CUS.").replaceAll("TAC", " TAC.")
					.replaceAll("TAMT", " TAMT.")
					.replaceAll("ODAMT", " ODAMT.")
					.replaceAll("ODCL", " ODCL.").replaceAll("SDR", " SDR.")
					.trim();

			// SMS relaxations after Analysis As on 23May13 ends
			// here-----------------------------
			// String sms_text2 = "";
			int l = sms_text1.lastIndexOf("ID");
			int k = sms_text1.lastIndexOf("DT");
			/**/// System.out.println("position of the ID :" + l);
			if (l != 0) {
				String s = sms_text1.substring(0, l);
				String s1 = sms_text1.substring(l);
				if (k > l)
					sms_text1 = s1 + s;
				else if (k == 0)
					sms_text1 = s1 + " " + s;
				else if (k != 0)
					sms_text1 = s1 + s;
			}
			//System.out.println("formatted SMS :" + sms_text1);

			// sms_text1 ="ID4992DT2305VL10CUS40TAC1TAMT1ODAMT1ODCL1";

			// "IDE4992VL10DT2305CUS40TAC1TAMT1ODAMT1ODCL1";

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// not
																				// "YYYY-MM-DD HH:MM:SS"
			// String
			// logmsg="; SMS="+sms_text1+"; SENDER="+sender_number+"; SENT DT="+sent_dt1+"; ACKNL=";

			// try {
			// con = js.getConnection();

			Date sent_dt = sdf.parse(sent_dt1);
			cal.setTime(sent_dt);
			cal.add(Calendar.HOUR, 5);
			cal.add(Calendar.MINUTE, 30);
			// cal.MINUTE(Calendar.SECOND,14);// cal.add(Calendar.SECOND,14);
			java.util.Date dt = cal.getTime();
			// java.sql.Timestamp sd=new java.sql.Timestamp(cal.getTime());
			java.sql.Timestamp sd = new java.sql.Timestamp(dt.getTime());

			/**/// System.out.println("Actual time for 2013-04-25 11:04:40 = "+sd);
			//System.out.println(sent_dt1);
			/**/// System.out.println(sent_dt);
			/**/// System.out.println(cal.getTime());
			//System.out.println(dt);
			//System.out.println(sender_number);
			// String sms_text1 =
			// "Eid.E5131 dt.09012013 vil.10 cus.20 tamt.3000 tacn.11 ODAMT.2000 closedodacn.10";//request.getParameter("text");//SMS
			// message text/
			String sql = "insert into FRS_RECOVERY(huma_id ,FRS_DATE ,FRS_VILLAGE_COUNT ,FRS_CUST_COUNT ,"
					+ "FRS_TOTAL_AMT ,FRS_TOTAL_ACCOUNTS ,FRS_OD_AMT ,FRS_OD_ACCOUNTS,frs_seqid,frs_cby,sender_number,sent_dt,sms_count,FRS_SDRcust_count) "
					+ "values(?,to_date(?,'ddmmyyyy'),?,?,?,?,?,?,user_seqid.nextval,?,?,?,1,?)";

			// try {
			StringTokenizer st;
			if (sms_text1.contains(","))
				st = new StringTokenizer(sms_text1, ",");
			else if (sms_text1.contains(" "))
				st = new StringTokenizer(sms_text1, " ");
			else {
				// logmsg="Your SMS is of Wrong format, pls check..!";//Your SMS
				// is of Wrong format, pls check..!
				out.write("Your SMS is of Wrong format, pls check..!");
				smslog.info(logmsg
						+ "Your SMS is of Wrong format, pls check..!");
				return;
			}
			// More parameters than the format error checking
			if (st.countTokens() > 9) {
				out.write("Some extra parameters found  or wrongly spelled , pls check..!");
				smslog.info(logmsg
						+ "Some extra parameters found  or wrongly spelled , pls check..!");
				return;
			}

			hs.clear();
			while (st.hasMoreElements()) {
				String txt = (String) st.nextElement();
				int i = txt.lastIndexOf('.');
				String key = txt.substring(0, i);
				/**/// System.out.println("key :" + key);
				String value = txt.substring(i + 1);
				hs.put(key, value);

			}
			int fieldsize = 0;
			// String id=hs.get
			huma_id = hs.get("ID");
			//System.out.println("employee id :" + huma_id);
			int huma_idlen = huma_id.length();
			fieldsize = hs.size();
			//System.out.println("fields_size=" + fieldsize);
			// try{
			if (huma_id.charAt(0) != "E".charAt(0)) {
				huma_id = "E" + huma_id;
			}

			if (fieldsize == 2 && sms_text1.contains("DT")
					&& sms_text1.contains("ID")) {

				if (huma_idlen > 5) {
					/**/// //System.out.println("Msr debug ENPID LENGTH >5 ");
					String huma_id1 = hs.get("ID").replaceAll("O", "0");
					// remarks=FRS_date.substring(4);
					Matcher m = Pattern.compile("[^0-9]*([0-9]+).*").matcher(
							huma_id1);
					if (m.matches())
						System.out.println(m.group(1));
					String a = m.group(1);
					// String a="srinu";
					huma_id = "E" + a;
					int a1 = a.length();

					int c = huma_id.indexOf(a) + a1;
					remarks = huma_id1.substring(c);
					System.out.println("remarks1 :" + remarks);
				} else
					remarks = "";

				// huma_id SERVER SIDE VALIDATION
				String fd = "";
				/**/// System.out.println("IN field size 2 if loop ");
				if (huma_idlen == 0) {
					out.write("Employee Id Should not be empty..!");
					smslog.info(logmsg + "Employee Id Should not be empty..!");
					// log.info(huma_id+"   "+sms_text1+"");
					return;
				}

				if (huma_id.charAt(0) != "E".charAt(0)) {
					huma_id = "E" + huma_id;
				}

				String sql2 = "select distinct huma_id,huma_fname from huma_mstr where huma_id='"
						+ huma_id + "'";// select * from huma_mstr where
										// huma_id='E2317'
				st2 = con.createStatement();
				ResultSet rs = st2.executeQuery(sql2);
				if ((rs.next()) == false) {
					st2.close();
					rs.close();
					out.write("Wrong Emp ID, pls check..!");// out.write("Employee is not authorized pls check huma_id");
					smslog.info(logmsg + "Wrong Emp ID, pls check..!");
					return;
				}
				// st2.close();

				// FRS_date server side validation starts here
				// frs_date_Validation: {
				FRS_date = hs.get("DT").replaceAll("O", "0");
				/**/// System.out.println("FRS DATE IS :" + FRS_date);
				fd = FRS_date;
				/**/// System.out.println("DATE :" + fd);
				if (FRS_date.length() > 4) {
					FRS_date = hs.get("DT").replaceAll("O", "0");
					fd = FRS_date.substring(0, 4);
					/**/// System.out.println("FRS date is " + fd);
					if (remarks.trim() == "")
						remarks = FRS_date.substring(4);
					else
						remarks = "";
					/**/// System.out.println("remarks 2:"+remarks);
				}
				/**/// System.out.println("remarks 3:" + remarks);
				if (remarks.trim() == "") {// throw new Exception();
					out.write("Wrong SMS format, pls check..!");
					smslog.info(logmsg + "Wrong SMS format, pls check..!");
					return;
				}
				int fdlentgth = fd.length();
				/**/// System.out.println("frs date length is :"+fdlentgth);
				if (fdlentgth == 0) {
					out.write("Date Should Not be Empty, pls check..!");
					smslog.info(logmsg
							+ "Date Should Not be Empty, pls check..!");
					return;
				}
				if (fdlentgth > 4 && fd.charAt(0) == '0')
					FRS_date = FRS_date.substring(1); /**/// System.out.println("FRS_date="+FRS_date);

				int fdlentgth2 = fd.length(); /**/// System.out.println("FRS_date.lenfth="+FRS_date.length());
				if (fdlentgth2 != 4) {
				}

				// The code including below c if ondition is to check future
				// date -added by Rajashekhar
				Calendar FRS_cal = Calendar.getInstance();
				Date sys_date = FRS_cal.getTime();
				SimpleDateFormat FRS_sdf = new SimpleDateFormat("ddMMyyyy");// not
																			// "YYYY-MM-DD HH:MM:SS"
				Date FRS_dt = FRS_sdf.parse(fd + FRS_cal.get(Calendar.YEAR));// +
																				// 2013
				// SimpleDateFormat FRS_sdf = new SimpleDateFormat("ddMM");//
				// not "YYYY-MM-DD HH:MM:SS"
				// Date FRS_dt = FRS_sdf.parse(fd);
				FRS_cal.setTime(FRS_dt);

				// if (FRS_cal.getTime().after(sys_date)) {
				// out.write("Date Should Not be a future date, pls check..!");
				// smslog.info(logmsg
				// + "Date Should Not be a future date, pls check..!");
				// return;
				// }
				// --------------- To accept Dec SMSs in next year Jan month
				int yr = FRS_cal.get(Calendar.YEAR);
				if (FRS_cal.getTime().after(sys_date)) {
					long timeDifference = (FRS_dt.getTime())
							- (sys_date.getTime());
					int daysInBetween = (int) (timeDifference / (24 * 60 * 60 * 1000));
					System.out.println("daysInBetween=" + daysInBetween);
					if (daysInBetween >= 334 && daysInBetween <= 366)
						yr = FRS_cal.get(Calendar.YEAR) - 1;
					else {
						out.write("Date Should Not be a future date, pls check..!");
						smslog.info(logmsg
								+ "Date Should Not be a future date, pls check..!");
						return;
					}
				} else
					yr = FRS_cal.get(Calendar.YEAR);
				// System.out.println("yr="+yr);
				// ----------------
				// }
				// FRS_date server side validation ends here
				String lsql = "INSERT INTO FRS_RECOVERY(huma_id,FRS_DATE,FRS_NO_VALUES_REMARKS,frs_seqid,frs_cby,sender_number,sent_dt,sms_count) VALUES(?,to_date(?,'ddmmyyyy'),?,user_seqid.nextval,?,?,?,1)";
				PreparedStatement ps = con.prepareStatement(lsql);
				ps.setString(1, huma_id);
				// ps.setString(2, fd);
				ps.setString(2, fd + yr);
				ps.setString(3, remarks);
				ps.setString(4, null);// new String((String)
										// request.getSession().getAttribute("username")));
				ps.setString(5, sender_number);
				ps.setTimestamp(6, sd);
				ps.executeUpdate();
				con.commit();

				System.out.println("The details saved Successfully for DT."
						+ fd + " of " + huma_id + " with " + remarks);
				smslog.info(logmsg + "The details saved Successfully for DT."
						+ fd + " of " + huma_id + " with " + remarks);
				// below one line code for
				// sendTargetVsAchievementSMS----------------------
				FRS_od_amt = 0;
				targetVsAchievement_call(con, huma_id,
						rs.getString("huma_fname"), fd + yr, FRS_od_amt,
						sender_number);

				ps.close();
				con.close();
				return;
			}

			if (!(fieldsize == 8 || fieldsize == 9)) {
				if (fieldsize < 8) {
					out.write("Some parameters are missing or wrongly spelled , pls check..!");
					smslog.info(logmsg
							+ "Some parameters are missing or wrongly spelled , pls check..!");
				} else if (fieldsize == 8 && hs.containsKey("SDR")) {
					out.write("Some parameters are missing or wrongly spelled , pls check..!");
					smslog.info(logmsg
							+ "Some parameters are missing or wrongly spelled , pls check..!");
				} else // if(fieldsize>8)
				{
					out.write("Your sent msg is of Wrong format, pls check..!");// Your
																				// sent
																				// msg
																				// is
																				// of
																				// Wrong
																				// format,
																				// pls
																				// check..!
					smslog.info(logmsg
							+ "Your sent msg is of Wrong format, pls check..!");
				}

				return;
				/*
				 * out.write("Your sent msg is of Wrong format, pls check..!");//
				 * Your sent msg was Wrong pls check..! smslog.info(logmsg+
				 * "Your sent msg is of Wrong format, pls check..!"); return;
				 */
			}
			/**/// System.out.println("the size of the hsash map :"+fieldsize);
			if (huma_idlen == 0) {
				out.write("Employee Id Should not be empty..!");
				smslog.info(logmsg + "Employee Id Should not be empty..!");
				// log.info(huma_id+"   "+sms_text1+"");
				return;
			}
			// Boolean eidcheck=checkhuma_id(huma_id,response);
			if (huma_id.charAt(0) != "E".charAt(0)) {
				huma_id = "E" + huma_id;
			}

			String sql2 = "select distinct huma_id,huma_fname from huma_mstr where huma_id='"
					+ huma_id + "'";// select * from huma_mstr where
									// huma_id='E2317'
			st2 = con.createStatement();
			ResultSet rs = st2.executeQuery(sql2);
			if ((rs.next()) == false) {
				st2.close();
				rs.close();
				out.write("Wrong Emp ID, pls check..!");// out.write("Employee is not authorized pls check huma_id");
				smslog.info(logmsg + "Wrong Emp ID, pls check..!");
				return;
			}
			// st2.close();
			// FRS_date server side validation starts here
			// frs_date_Validation: {
			FRS_date = hs.get("DT").replaceAll("O", "0");

			int fdlentgth = FRS_date.length();
			/**/// System.out.println("frs date length is :"+fdlentgth);
			if (fdlentgth == 0) {
				out.write("Date Should Not be Empty, pls check..!");
				smslog.info(logmsg + "Date Should Not be Empty, pls check..!");
				return;
			}
			if (fdlentgth > 4 && FRS_date.charAt(0) == '0')
				FRS_date = FRS_date.substring(1); /**/// System.out.println("FRS_date="+FRS_date);

			int fdlentgth2 = FRS_date.length(); /**/// System.out.println("FRS_date.lenfth="+FRS_date.length());
			if (fdlentgth2 != 4) {
				out.write("Date Should be 4 digits (DDMM), pls check..!");
				smslog.info(logmsg
						+ "Date Should be 4 digits (DDMM), pls check..!");
				return;
			}

			// The code including below c if ondition is to check future
			// date -added by Rajashekhar
			Calendar FRS_cal = Calendar.getInstance();
			Date sys_date = FRS_cal.getTime();
			SimpleDateFormat FRS_sdf = new SimpleDateFormat("ddMMyyyy");// not
																		// "YYYY-MM-DD HH:MM:SS"
			Date FRS_dt = FRS_sdf.parse(FRS_date + FRS_cal.get(Calendar.YEAR));// +
																				// 2013
			//System.out.println("getYear=" + FRS_cal.get(Calendar.YEAR));
			// Date FRS_dt = FRS_sdf.parse(FRS_date + sys_date.getYear());
			// SimpleDateFormat FRS_sdf = new SimpleDateFormat("ddMM");// not
			// "YYYY-MM-DD HH:MM:SS"
			// Date FRS_dt = FRS_sdf.parse(FRS_date);
			FRS_cal.setTime(FRS_dt);

			// if (FRS_cal.getTime().after(sys_date)) {
			// out.write("Date Should Not be a future date, pls check..!");
			// smslog.info(logmsg
			// + "Date Should Not be a future date, pls check..!");
			// return;
			// }
			// --------------- To accept Dec SMSs in next year Jan month
			int yr = FRS_cal.get(Calendar.YEAR);
			if (FRS_cal.getTime().after(sys_date)) {
				long timeDifference = (FRS_dt.getTime()) - (sys_date.getTime());
				int daysInBetween = (int) (timeDifference / (24 * 60 * 60 * 1000));
				System.out.println("daysInBetween=" + daysInBetween);
				if (daysInBetween >= 334 && daysInBetween <= 366)
					yr = FRS_cal.get(Calendar.YEAR) - 1;
				else {
					out.write("Date Should Not be a future date, pls check..!");
					smslog.info(logmsg
							+ "Date Should Not be a future date, pls check..!");
					return;
				}
			} else
				yr = FRS_cal.get(Calendar.YEAR);
			// System.out.println("yr="+yr);
			// ----------------
			// }
			// FRS_date server side validation ends here

			String VL = hs.get("VL");/**/// System.out.println("VL="+VL);
			/**/// System.out.println("VL="+VL.replaceAll("[^A-Za-z0-9]", ""));
			FRS_village_count = Integer.parseInt(VL.replaceAll("^0*O*0*", "0"));// .replaceAll("O",
																				// "0"));
			String CUS = hs.get("CUS");
			FRS_Cust_count = Integer.parseInt(CUS.replaceAll("^0*O*0*", "0"));// .replaceAll("O",
																				// "0"));
			String TAMT = hs.get("TAMT");
			FRS_total_amt = Integer.parseInt(TAMT.replaceAll("^0*O*0*", "0"));// .replaceAll("O",
																				// "0"));
			String TAC = hs.get("TAC");
			FRS_total_accounts = Integer.parseInt(TAC
					.replaceAll("^0*O*0*", "0"));// .replaceAll("O", "0"));
			String ODAMT = hs.get("ODAMT");
			FRS_od_amt = Integer.parseInt(ODAMT.replaceAll("^0*O*0*", "0"));// .replaceAll("O",
																			// "0"));
			String ODCL = hs.get("ODCL");
			FRS_od_accounts = Integer.parseInt(ODCL.replaceAll("^0*O*0*", "0"));// .replaceAll("O",
																				// "0"));
			if (fieldsize == 9) {
				String SDR = hs.get("SDR");
				FRS_SDRcust_count = Integer.parseInt(SDR.replaceAll("^0*O*0*",
						"0"));// .replaceAll("O",
			} else
				FRS_SDRcust_count = 0; // "0"));

			if (FRS_village_count == 0
					& ((FRS_Cust_count + FRS_od_accounts + FRS_SDRcust_count
							+ FRS_od_amt + FRS_total_accounts + FRS_total_amt) != 0)) {
				out.write("No of Villages Visited must be Non Zero when other values there, pls check..!");
				smslog.info(logmsg + "No of Villages Visited must be Non Zero");
				return;
			}

			if (FRS_total_amt == 0 && FRS_total_accounts != 0) {
				out.write("TAMT should not be zero when TAC is there, pls check..!");
				smslog.info(logmsg
						+ "TAMT should not be zero when TAC is there, pls check..!");
				return;
			}
			if (FRS_total_amt > 0 && FRS_total_accounts == 0) {
				out.write("TAC should not be zero when TAMT is there, pls check..!");
				smslog.info(logmsg
						+ "TAC should not be zero when TAMT is there, pls check..!");
				return;
			}
			if (FRS_od_amt == 0 && FRS_od_accounts != 0) {
				out.write("ODAMT should not be zero when ODCL is there, pls check..!");
				smslog.info(logmsg
						+ "ODAMT should not be zero when ODCL is there, pls check..!");
				return;
			} // if(FRS_total_amt!=0 && FRS_od_amt!=0)
			if (FRS_total_amt < FRS_od_amt) {
				out.write("TAMT should not less than ODAMT, pls check..!");
				smslog.info(logmsg
						+ "TAMT should not less than ODAMT, pls check..!");
				return;
			} // if(FRS_total_accounts!=0 &&FRS_od_accounts!=0)
			if (FRS_od_accounts > FRS_total_accounts) {
				out.write("TAC should not less than ODCL, pls check..!");
				smslog.info(logmsg
						+ "TAC should not less than ODCL, pls check..!");
				return;
			}
			if (FRS_total_accounts > FRS_Cust_count) {
				out.write("CUS should not less than TAC, pls check..!");
				smslog.info(logmsg
						+ "CUS should not less than TAC, pls check..!");
				return;
			}
			if (FRS_SDRcust_count != 0)
				if (FRS_SDRcust_count > FRS_Cust_count) {
					out.write("SDR should not greater than CUS, pls check..!");
					smslog.info(logmsg
							+ "SDR should not greater than CUS, pls check..!");
					return;
				}

			/**/// System.out.println(hs);
			// con=js.getConnection();
			// ////log.info("JDBC Connection was created");
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, huma_id);
			// ps.setString(2, FRS_date);
			ps.setString(2, FRS_date + yr);
			ps.setInt(3, FRS_village_count);
			ps.setInt(4, FRS_Cust_count);
			ps.setInt(5, FRS_total_amt);
			ps.setInt(6, FRS_total_accounts);
			ps.setInt(7, FRS_od_amt);
			ps.setInt(8, FRS_od_accounts);
			ps.setString(9, null);// new String((String)
									// request.getSession().getAttribute("username")));
			ps.setString(10, sender_number);
			ps.setTimestamp(11, sd);
			ps.setInt(12, FRS_SDRcust_count);
			ps.executeUpdate();
			con.commit();
			/**/// System.out.println("one row inserted in the db");
			ps.close();
			// con.close();
			// out.write("The details saved Successfully for DT."+FRS_date+" of "+huma_id);//Write("Reply message text");
			smslog.info(logmsg + "The details saved Successfully for DT."
					+ FRS_date + " of " + huma_id);
			System.out.println("The details saved Successfully for DT."
					+ FRS_date + " of " + huma_id);
			// below one line code for
			// sendTargetVsAchievementSMS----------------------
			targetVsAchievement_call(con, huma_id, rs.getString("huma_fname"),
					FRS_date + yr, FRS_od_amt, sender_number);

			con.close();
		} catch (NumberFormatException nfe) {
			out.write("VL,CUS,TAC,TAMT,ODAMT,ODCL,SDR are should be numbers only, pls check..!");
			// String logmsg = null;
			smslog.info(logmsg
					+ "VL,CUS,TAC,TAMT,ODAMT,ODCL,SDR are should be numbers only, pls check..!");
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			if (sqle.getErrorCode() == 1) {
				out.write("Already existing date, pls check..!");
				smslog.info(logmsg + "Already existing date, pls check..!");
				// sqle.printStackTrace();
			} else {
				out.write("Software Error, pls try after some time..!");
				smslog.info(logmsg
						+ "Software Error, pls try after some time..!");
				sqle.printStackTrace();
			}
		} catch (Exception e) {
			out.write("Wrong SMS format or other error, pls check..!");
			smslog.info(logmsg
					+ "Wrong SMS format or other error, pls check..!");
			e.printStackTrace();
		} finally { /**/// //System.out.println("inside finally of post");
			if (con != null)
				try {
					/**/// System.out.println("con="+con);
					con.close();
					/**/// System.out.println("con after close="+con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public static void targetVsAchievement_call(Connection con, String huma_id,
			String huma_fname, String FRS_date, int FRS_od_amt,
			String sender_number) {
		// //System.out.println("inside the targetVsAchievemnt_call method");
		// below lines code for sendTargetVsAchievementSMS
		// start----------------------
		try {// System.out.println("the frs_date="+FRS_cal.getTime());
			TargetVsAchievementSMS tvas = new TargetVsAchievementSMS();// target_frs_DATE
			// System.out.println("after object target table query");
			// PreparedStatement ps2 = con
			// .prepareStatement("select * from(select t1.target_frs_od_amt as amt1,t2.target_frs_od_amt as amt2,to_char(t1.target_frs_DATE,'dd-Mon-yyyy') as date1,to_char(t2.target_frs_DATE,'dd-Mon-yyyy') as date2, row_number() over (order by t2.target_frs_DATE) rn from target_frs_recovery t2,target_frs_recovery t1 where t2.target_frs_DATE > t1.target_frs_DATE and t1.huma_id=t2.huma_id and t1.huma_id=? and to_char(t1.target_frs_DATE,'ddmmyyyy')=? ) where rn = 1");

			// Tosend SMS to LSR-------------------------below two statements
			String planVsAch_query = "select "
					+ "to_char(s.date1,'MON') as month1,"
					+ "to_char(s.date1,'dd-Mon-yyyy') as date1,"
					+ " nvl((select sum(target_frs_od_amt) from target_frs_recovery t where t.huma_id=s.huma_id and to_char(t.target_frs_date,'mmyyyy')=to_char(s.date1,'mmyyyy')),0) as target, "
					+ "nvl((select sum(frs_od_amt) from frs_recovery f where f.huma_id=s.huma_id and to_char(f.frs_date,'mmyyyy')=to_char(s.date1,'mmyyyy')),0) as Achievement "
					+ "from (select to_date(?,'ddmmyyyy') as date1,? as huma_id from dual) s"; 
			makeSMS(con, huma_id, huma_fname, FRS_date, sender_number, tvas,
					planVsAch_query, "", "LSR");

			// Tosend SMS to UH start-------------------stat
			String bsflunit = "''";
			unitwise_sms(con, huma_id, FRS_date, tvas, bsflunit, false);
			// Tosend SMS to UH start-------------------end

		} catch (Exception e) {
			e.printStackTrace();
		}
		// above lines code for sendTargetVsAchievementSMS
		// end----------------------
	}

	public void schedule_uhsms(
	// Connection con, String huma_id,
	// String huma_fname, String FRS_date, int FRS_od_amt,
	// String sender_number
	) {
		// //System.out.println("inside the targetVsAchievemnt_call method");
		// below lines code for sendTargetVsAchievementSMS
		// start----------------------
		try {// System.out.println("the frs_date="+FRS_cal.getTime());
			TargetVsAchievementSMS tvas = new TargetVsAchievementSMS();// target_frs_DATE
			// System.out.println("after object target table query");

			// Tosend SMS to LSR-------------------------below two statements
			String unitList_query = "select bsflunit_ucode as bsflunit,"
					+ "(select huma_id from huma_mstr h WHERE upper(huma_status) LIKE '%ACTIVE%' and huma_DESIGNATION IN (select grade_id from grade_mstr where (GRADE_name like '%LSR%' or GRADE_name like '%FS%')) and bsflunit_ucode=b.bsflunit_ucode and rownum=1) "
					+ "as huma_id " + "from bsflunit_mstr b";
			Connection con = js.getConnection();
			String FRS_date = DateUtil.getCurrentDate("ddmmyyyy", -1);
			PreparedStatement ps4 = con.prepareStatement(unitList_query);
			ps4.setString(1, FRS_date); 
			// System.out.println("Inside the SMSreciever, before executing query and frs_date="+FRS_date);
			ResultSet rs4 = ps4.executeQuery();
			// System.out.println("After executing target table query");
			if (rs4 != null)
				while (rs4.next()) {
					// Tosend SMS to UH start-------------------stat

					unitwise_sms(con, rs4.getString("huma_id"), FRS_date, tvas,
							rs4.getString("bsflunit"), true);

					// Tosend SMS to UH start-------------------end
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// above lines code for sendTargetVsAchievementSMS
		// end----------------------
	}

	private static void unitwise_sms(Connection con, String huma_id,
			String FRS_date, TargetVsAchievementSMS tvas, String bsflunit,
			boolean scheduleFlow) throws SQLException {
		String planVsAch_query2 = "select month1,date1,"
				+ "sum(target) as target,sum(Achievement) as Achievement"
				+ " from"
				+ "(select "
				+ "to_char(s.date1,'MON') as month1,"
				+ "to_char(s.date1,'dd-Mon-yyyy') as date1,"
				+ " nvl((select sum(target_frs_od_amt) from target_frs_recovery t where t.huma_id=s.huma_id and to_char(t.target_frs_date,'mmyyyy')=to_char(s.date1,'mmyyyy')),0) as target,"
				+ " nvl((select sum(frs_od_amt) from frs_recovery f where f.huma_id=s.huma_id and to_char(f.frs_date,'mmyyyy')=to_char(s.date1,'mmyyyy')),0) as Achievement"
				+ " from"
				+ "("
				+ "select"
				+ " huma_id as huma_id,to_date(?,'ddmmyyyy') as date1"
				+ " from huma_mstr"
				+ " where bsflunit_ucode in (select nvl("
				+ bsflunit
				+ ",bsflunit_ucode) from bsflunit_mstr where huma_id in (select huma_id from bsflunit_mstr where bsflunit_ucode in (select bsflunit_ucode from huma_mstr where huma_id=?)))"
				+ ") s)";

		String huma_fname2 = "UH";
		String sender_number2 = "0";

		String huma_id2 = null;
		int defaulter_hr_count = 5;
		int active_hr_count = 0;

//		String uhQuery = "select huma_fname as huma_fname2,huma_mobile as sender_number2, huma_id as huma_id2,"
//				+ "(select count(*) from huma_mstr h WHERE upper(huma_status) LIKE '%ACTIVE%' and huma_DESIGNATION IN (select grade_id from grade_mstr where (GRADE_name like '%LSR%' or GRADE_name like '%FS%')) and bsflunit_ucode in (select nvl("
//				+ bsflunit
//				+ ",bsflunit_ucode) from bsflunit_mstr where huma_id=h2.huma_id) and h.huma_id not in (select huma_id from frs_recovery f where to_char(f.frs_date,'ddmmyyyy')=?)) as 	 "
//				+ ",(select count(*) from huma_mstr h WHERE upper(huma_status) LIKE '%ACTIVE%' and huma_DESIGNATION IN (select grade_id from grade_mstr where (GRADE_name like '%LSR%' or GRADE_name like '%FS%')) and bsflunit_ucode in (select nvl("
//				+ bsflunit
//				+ ",bsflunit_ucode) from bsflunit_mstr where huma_id=h2.huma_id)) as active_hr_count"
//				+ "from ("
//				+ "select huma_fname,huma_mobile, huma_id,bsflunit_ucode,? as huma_id1 from huma_mstr h1 where huma_id in (select huma_id from bsflunit_mstr "
//				+ "where bsflunit_ucode in (select nvl("
//				+ bsflunit
//				+ ",bsflunit_ucode) from huma_mstr where huma_id=?))) h2";
		String uhQuery = "select huma_fname as huma_fname2,huma_mobile as sender_number2, huma_id as huma_id2," +
				"(select count(*) from huma_mstr h WHERE upper(huma_status) LIKE '%ACTIVE%' and huma_DESIGNATION IN (select grade_id from grade_mstr where (GRADE_name like '%LSR%' or GRADE_name like '%FS%')) and bsflunit_ucode in (select nvl("+bsflunit+",bsflunit_ucode) from bsflunit_mstr where huma_id=h2.huma_id) and h.huma_id not in (select huma_id from frs_recovery f where to_char(f.frs_date,'ddmmyyyy')=?)) as defaulter_hr_count," +
				"(select count(*) from huma_mstr h WHERE upper(huma_status) LIKE '%ACTIVE%' and huma_DESIGNATION IN (select grade_id from grade_mstr where (GRADE_name like '%LSR%' or GRADE_name like '%FS%')) and bsflunit_ucode in (select nvl("+bsflunit+",bsflunit_ucode) from bsflunit_mstr where huma_id=h2.huma_id)) as active_hr_count" +
				" from (" +
				"select huma_fname,huma_mobile, huma_id,bsflunit_ucode,? as huma_id1 from huma_mstr h1 where huma_id in (select huma_id from bsflunit_mstr" +
				" where bsflunit_ucode in (select nvl("+bsflunit+",bsflunit_ucode) from huma_mstr where huma_id=?))" +
				" ) h2";
		//System.out.println("uhQuery="+uhQuery);
		PreparedStatement ps1 = con.prepareStatement(uhQuery);
		// System.out.println("after praparing target table query");
		ps1.setString(1, FRS_date);
		ps1.setString(2, huma_id);
		ps1.setString(3, huma_id);
		// System.out.println("Inside the SMSreciever, before executing query and frs_date="+FRS_date);
		ResultSet rs1 = ps1.executeQuery();
		// System.out.println("After executing target table query");
		if (rs1 == null)
			System.out.println("result set is null in Target Vs achement SMS");
		if (rs1.next()) {
			// System.out.println("inside rs2.next true and target="+rs2.getDouble("target")+" and achievement="+rs2.getDouble("Achievement"));
			huma_fname2 = rs1.getString("huma_fname2");
			sender_number2 = rs1.getString("sender_number2");
			huma_id2 = rs1.getString("huma_id2");
			defaulter_hr_count = Integer.parseInt(rs1
					.getString("defaulter_hr_count"));
			active_hr_count = Integer
					.parseInt(rs1.getString("active_hr_count"));
		} else {
			huma_fname2 = "UH";
			sender_number2 = "0";

			huma_id2 = null;
			defaulter_hr_count = 5;
			active_hr_count = 0;

		}
		System.out.println("huma_fname2=" + huma_fname2);
		System.out.println("sender_number2=" + sender_number2);
		System.out.println("huma_id2=" + huma_id2);
		System.out.println("defaulter_hr_count=" + defaulter_hr_count);
		System.out.println("active_hr_count=" + active_hr_count);

		if (huma_id2 != null && (!sender_number2.equals("0"))
				&& (sender_number2.length() > 9))
			if (!(active_hr_count == 1 && huma_id.equals(huma_id2))) 
			{
				System.out.println("scheduleFlow="+scheduleFlow);
				if ((!scheduleFlow) && (defaulter_hr_count <= 0))
					makeSMS(con, huma_id, huma_fname2, FRS_date,
							sender_number2, tvas, planVsAch_query2, "", "UH");
				else if ((scheduleFlow) && (defaulter_hr_count > 0))
					makeSMS(con, huma_id, huma_fname2, FRS_date,
							sender_number2, tvas, planVsAch_query2, defaulter_hr_count+" people are not sent", "UH");
			}
		
	}

	private static void makeSMS(Connection con, String huma_id,
			String huma_fname, String FRS_date, String sender_number,
			TargetVsAchievementSMS tvas, String planVsAch_query,
			String sms_suffix, String whoseSms) throws SQLException {
		PreparedStatement ps2 = con.prepareStatement(planVsAch_query);
		 //System.out.println("after praparing target table query and planVsAch_query="+planVsAch_query);
		System.out.println("planVsAch_query="+planVsAch_query);
		 System.out.println("huma_id="+huma_id);
		 System.out.println("FRS_date="+FRS_date);
		ps2.setString(1, FRS_date);
		ps2.setString(2, huma_id); // System.out.println("huma_id="+huma_id);
		// System.out.println("Inside the SMSreciever, before executing query and frs_date="+FRS_date);
		ResultSet rs2 = ps2.executeQuery();
		// System.out.println("After executing target table query");
		if (rs2 == null)
			System.out.println("result set is null in SMSreceiver");
		if ((rs2.next()) && rs2.getDouble("target") != 0) {
			String forYourUnit;
			// System.out.println("inside rs2.next true and target="+rs2.getDouble("target")+" and achievement="+rs2.getDouble("Achievement"));
			if (whoseSms.equals("UH"))
				forYourUnit="for your Unit ";
			else
				forYourUnit=" ";
				
			String message = huma_fname
					+ ", OD TRG ("
					+ rs2.getString("month1")
					+ ") "
					+ forYourUnit
					+ Math.round(rs2.getDouble("target"))
					+ " , ACH as on "
					+ rs2.getString("date1")
					+ "  "
					+ Math.round(rs2.getDouble("Achievement"))
					+ " . BAL TGT "
					+ Math.round(rs2.getDouble("target")
							- rs2.getDouble("Achievement")) + " " + sms_suffix;

			System.out.println(whoseSms+" message=" + message);
			 tvas.sendTargetVsAchievementSMS(sender_number, message, whoseSms);
			//System.out.println("SMS send to sperry,message=" + message);
			System.out.println("sender_number="
					+ sender_number);

			ps2.close();
			rs2.close();
		} else {
			// System.out.println("INside else part (No target part) and target="+rs2.getDouble("target")+" and achievement="+rs2.getDouble("Achievement"));
			System.out.println("No target records for given huma_id=" + huma_id
					+ " and date=" + FRS_date);
		}
	}
}