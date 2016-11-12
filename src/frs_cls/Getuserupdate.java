package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.SkipPageException;

import com.google.common.cache.Cache;

public final class Getuserupdate extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con = null;
	ResultSet rs = null, rs1 = null, rs2 = null, rs3 = null, rs4 = null;
	Statement st = null, st1 = null, st2 = null, st3 = null, st4 = null;
	PreparedStatement ps = null, ps1 = null, ps2 = null, ps3 = null,
			ps4 = null;
	private String cache = "";
	private String ucode=null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// //System.out.println("inside the doGet method");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession(false);
		JspFactory _jspxFactory = null;
		PageContext pageContext = null;
		HttpSession session = null;
		ServletContext application = null;
		JspWriter out = null;
		JspWriter _jspx_out = null;
		PageContext _jspx_page_context = null;

		// System.out.println("session="+session);
		String huma_idd = request.getSession().getAttribute("huma_id")
				.toString();
		request.getSession().getAttribute("username").toString();
		String userrole = request.getSession().getAttribute("userrole")
				.toString();

		try {
			_jspxFactory = JspFactory.getDefaultFactory();
			response.setContentType("text/html");
			pageContext = _jspxFactory.getPageContext(this, request, response,
					null, true, 8192, true);
			_jspx_page_context = pageContext;
			application = pageContext.getServletContext();
			pageContext.getServletConfig();
			session = pageContext.getSession();
			out = pageContext.getOut();
			_jspx_out = out;

			JdbcConnect dbConn = null;
			synchronized (request) {
				dbConn = (JdbcConnect) _jspx_page_context.getAttribute(
						"dbConn", PageContext.REQUEST_SCOPE);
				if (dbConn == null) {
					dbConn = new JdbcConnect();
					_jspx_page_context.setAttribute("dbConn", dbConn,
							PageContext.REQUEST_SCOPE);
				}
			}
			if (((HttpServletRequest) request).getSession().getAttribute(
					"username") == null) {
				response.sendRedirect("frslogin.jsp"); // Not logged in,
														// redirect to
														// login page.
			}

			else // if (((HttpServletRequest)
					// request).getSession().getAttribute("user") != null)
			{
				response.setHeader("Cache-Control", "no-cache");

				String decide = "";// it is the decision to check whether the
									// requested program type
				String key1 = "";// the key 1indicates by reference of which
									// variable the query happened
				String key2 = "";// url=url+"?huma_id="+huma_id+"&log_date="+log_date;//+"&cont_amount="+amt;
				String key3 = "";
				String key4 = "";
				String key5 = "";
				String key6 = "";
				String key7 = "";
				// System.out.println("hey going to read the keys");
				decide = request.getParameter("decide").toString();
				key1 = request.getParameter("key1").toString();
				key2 = request.getParameter("key2").toString();
				// key2 = request.getParameter("key3").toString();
				String data = "";
				// String data2 = "";
				// //System.out.println("inside the Getuserupdate and decide = "+decide);
				// System.out.println("hey the decide="+decide);
				// System.out.println("hey the key1="+key1);
				// System.out.println("hai the key2="+key2);

 				application.getInitParameter("driver");
				application.getInitParameter("url");
				application.getInitParameter("user");
				application.getInitParameter("pwd");

				try {
					// Class.forName(driver);
					con = dbConn.getConnection();
					// con = DriverManager.getConnection(url,user,pwd);

					String curhuma_id = "";
					// below two lines code to take current user id, if he/she
					// is not a super user.
					/*
					 * if (!((String) session.getAttribute("userType"))
					 * .equals("SUPER")) curhuma_id = (String)
					 * ((HttpServletRequest) request)
					 * .getSession().getAttribute("user");
					 */// System.out.println("Current huma_id="+curhuma_id);

					if (decide.equals("comp_mstr")) { // System.out.println("hey inside the if for the comp_mstr");
						if (key1 == "")
							ps = con.prepareStatement("select comp_id,comp_name from comp_mstr order by comp_id");
						else {
							if (key1.length() >= 4)// if(comp_id2!="")
								// key1 = key1.substring(0, 4);//
								// substring(0,4);
								key1 = key1
										.substring(key1.lastIndexOf("-") + 1);
							// //System.out.println("inside the comp_mstr and key1="+key1);

							ps = con.prepareStatement("select comp_id,comp_name from comp_mstr where comp_id='"
									+ key1 + "' order by comp_id");
						}
						rs = ps.executeQuery();// System.out.println("Hey going for while loop");
						while (rs.next()) { // //System.out.println("inside the main while loop");
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "//////";
							// //System.out.println("inside the while loop,the data is ="+data);
						}// while
					}
					else if (decide.equals("stationary_id")) { // System.out.println("hey inside the if for the comp_id");

                        ps = con.prepareStatement("select s_id from stationary_mstr where s_id=(select max(s_id) from stationary_mstr)");
                        rs = ps.executeQuery();
                        if (rs == null) {
                               System.out.println("rs is null in getuserupate and in s_id");
                        }
                        if (rs.next()) {
                               do {
                                      String s = (rs.getString(1)).substring(0, 1);
                                      
                                      int n = Integer.parseInt((rs.getString(1))
                                                    .substring(1));
                                      n = n + 1;
                               
                                      if (n <= 9)
                                             data = s + "00" + n;
                                      if ((n >= 10) && (n <= 99))
                                             data = s + "0" + n;
                                      if ((n >= 100) && (n <= 999))
                                             data = s + n;
                                      if (n > 999)
                                             throw new Exception();
                               } while (rs.next());// while
                        }// if
                        else
                               data = "S001";
                 }// 
					
					else if (decide.equals("activity_id")) { // System.out.println("hey inside the if for the comp_id");

                        ps = con.prepareStatement("select act_id from statehead_activities where act_id=(select max(act_id) from statehead_activities)");
                        rs = ps.executeQuery();
                        if (rs == null) {
                               System.out.println("rs is null in getuserupate and in act_id");
                        }
                        if (rs.next()) {
                               do {
                                      String A = (rs.getString(1)).substring(0, 1);
                                      
                                      int n = Integer.parseInt((rs.getString(1))
                                                    .substring(1));
                                      n = n + 1;
                               
                                      if (n <= 9)
                                             data = A + "00" + n;
                                      if ((n >= 10) && (n <= 99))
                                             data = A + "0" + n;
                                      if ((n >= 100) && (n <= 999))
                                             data = A + n;
                                      if (n > 999)
                                             throw new Exception();
                               } while (rs.next());// while
                        }// if
                        else
                               data = "A001";
                        System.out.println(data);
                 }// 
					else if (decide.equals("stationary_mstr")) { // System.out.println("hey inside the if for the country_mstr");
                        if (key1 == "")
                               ps = con.prepareStatement("select s_id,s_name,s_opening_stock,to_char(s_cdate,'dd-mm-yyyy') from stationary_mstr order by s_id");
                        else {
                               if (key1.length() >= 3)// if(huma_id2!="")
                                      // key1 = key1.substring(0, 3);//
                                      // substring(0,1);
                                      key1 = key1
                                                    .substring(key1.lastIndexOf("-") + 1);

                               ps = con.prepareStatement("select s_id,s_name,s_opening_stock,to_char(s_cdate,'dd-mm-yyyy') from stationary_mstr where s_id='"
                                             + key1 + "' order by s_id");
                        }
                        rs = ps.executeQuery();// System.out.println("Hey going for while loop");
                        while (rs.next()){
                               data = data + rs.getString(1) + "::::::"
                                             + rs.getString(2) + "::::::"
                                             + rs.getString(3) + "::::::"
                                             + rs.getString(4) + "//////";
					}
				}
					
					else if (decide.equals("war_target1"))
					{ 	
							if (key1.length() >= 3)
							key1 = key1.substring(key1.lastIndexOf('-') + 1);
							System.out.println("Key1"+key1);
							key2 = key2.substring(key2.lastIndexOf('-') + 1);
							System.out.println("Key2"+key2);
							if ((userrole.equals("admin"))|| (userrole.equals("audit")))
							{
								ps = con.prepareStatement("select "
							+ "wp.WAR_CONTROLENO,"
							/*+ "wp.WORK_CONTROLENO,"*/
							+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
							+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
							+ " to_char(wp.WORK_DATE,'dd-mm-yyyy') as WORK_DATE,"
							+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=wp.huma_id) as huma_id2,"
							/*+ "w.WAR_CUST_COUNT,"*/
							+ "wp.ACT_CAT,"
							//+ "w.WAR_SDRCUST_COUNT,"
							+ "wp.ACT_NAME,"
							+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=wp.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(wp.WORK_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
							/*+ "w.WAR_villages,"*/
							+ "to_char(wp.WORK_DATE,'Day') as WORK_DAY"
							+ " from WORK_PLAN wp ,WAR_TARGET w,bsflunit_mstr b, huma_mstr h where wp.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
							+ "and h.bsflunit_ucode=nvl('"
							+ key1
							+ "',h.bsflunit_ucode) and wp.huma_id=nvl('"
							+ key2
							+ "',wp.huma_id) order by b.area_id, b.bsflunit_ucode, wp.huma_id, wp.WORK_SEQID");
							}
							else if (userrole.equals("areahead"))
							{
								ps = con.prepareStatement("select "
							+ "w.WAR_CONTROLENO,"
							/*+ "wp.WORK_CONTROLENO,"*/
							+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
							+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
							+ " to_char(wp.WORK_DATE,'dd-mm-yyyy') as WORK_DATE,"
							/*+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,w.WAR_CUST_COUNT,w.WAR_SDRCUST_COUNT,"*/
							+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=wp.huma_id) as huma_id2,wp.ACT_CAT,wp.ACT_NAME,"
							+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=wp.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(wp.WORK_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
							/*+ "w.WAR_villages,"*/
							+ "to_char(wp.WORK_DATE,'Day') as WORK_DAY"
							+ " from WORK_PLAN wp,WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where wp.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
							+ "and h.bsflunit_ucode=nvl('"
							+ key1
							+ "',h.bsflunit_ucode) and wp.huma_id=nvl('"
							+ key2
							+ "',wp.huma_id) and b.bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where area_id in (select area_id from area_mstr where huma_id='"
							+ huma_idd
							+ "')) order by b.area_id, b.bsflunit_ucode, wp.huma_id, wp.WORK_SEQID");
							}
							else if (userrole.equals("unithead"))
							{
								ps = con.prepareStatement("select "
							+ "w.WAR_CONTROLENO,"
							/*+ "wp.WORK_CONTROLENO,"*/
							+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
							+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
							+ " to_char(wp.WORK_DATE,'dd-mm-yyyy') as WORK_DATE,"
							/*+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,w.WAR_CUST_COUNT,w.WAR_SDRCUST_COUNT,"*/
							+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=wp.huma_id) as huma_id2,wp.ACT_CAT,wp.ACT_NAME,"
							+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=wp.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(wp.WORK_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
							/*+ "w.WAR_villages,"*/
							+ "to_char(wp.WORK_DATE,'Day') as WORK_DAY"
							+ " from WORK_PLAN wp,WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where wp.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
							+ "and h.bsflunit_ucode=nvl('"
							+ key1
							+ "',h.bsflunit_ucode) and wp.huma_id=nvl('"
							+ key2
							+ "',wp.huma_id) and b.bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where huma_id='"
							+ huma_idd
							+ "') order by b.area_id, b.bsflunit_ucode, wp.huma_id, wp.WORK_SEQID");
							}
							else
								ps = con.prepareStatement("select "
							+ "w.WAR_CONTROLENO,"
							/*+ "wp.WORK_CONTROLENO,"*/
							+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
							+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
							+ " to_char(wp.WORK_DATE,'dd-mm-yyyy') as WORK_DATE,"
							+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=wp.huma_id) as huma_id2,"
							/*+ "w.WAR_CUST_COUNT,"
							+ "w.WAR_SDRCUST_COUNT,"*/
							+ "wp.ACT_CAT,"
							+ "wp.ACT_NAME,"
							+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=wp.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(wp.WORK_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
							/*+ "w.WAR_villages,"*/
							+ "to_char(wp.WORK_DATE,'Day') as WORK_DAY"
							+ " from WORK_PLAN wp,WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where wp.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
							+ "and h.bsflunit_ucode=nvl('"
							+ key1
							+ "',h.bsflunit_ucode) and wp.huma_id=nvl('"
							+ key2
							+ "',wp.huma_id) and wp.huma_id ='"
							+ huma_idd
							+ "' order by b.area_id, b.bsflunit_ucode, wp.huma_id, wp.WORK_SEQID");
							
							rs = ps.executeQuery();
							//ps1 = con.prepareStatement("select VNAME from village_mstr where vcode=? and BSFLUNIT_UCODE=?");
							ps1 = con.prepareStatement("select am_id from mp_activity_mstr where am_id=? and ac_id=?");
							while (rs.next())
							{
								StringTokenizer st = null;
								String vl = rs.getString(6);
							System.out.println("*************vl************: "+vl);
								cache = "";
								String un = rs.getString(2);
								//System.out.println("*************un************: "+un);
								int i1 = un.lastIndexOf('-');
								String ucode = un.substring(i1 + 1);
								//System.out.println("*************ucode************: "+ucode);
								String cache1 = "";
								st = new StringTokenizer(vl, "::");
								while (st.hasMoreElements())
								{
									String temp = (String) st.nextElement();
									//System.out.println("*************temp************: "+temp);
									ps1.setString(1, temp);
									ps1.setString(2, ucode);
									rs1 = ps1.executeQuery();
									if (rs1.next())
									{
										cache1 = rs1.getString(1) + "-";
										
									}
									else
									{
										cache1 = "";
									}
									cache += cache1 + temp + "::";
									System.out.println("Cache:"+cache);
							}
							int length = cache.length();
							cache = cache.substring(0, length - 2);
							/*data = data + rs.getString(1) + "::::::"
							+ rs.getString(2) + "::::::"
							+ rs.getString(3) + "::::::"
							+ rs.getString(4) + "::::::"
							+ rs.getString(5) + "::::::"
							+ rs.getString(6) + "::::::"
							+ rs.getString(7) + "::::::"
							+ rs.getString(8) + "::::::"
							+ cache	+ "::::::"
							+ rs.getString(9) + "//////";
							*/
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8)+ "::::::"
									+ cache	+ "::::::"
									//+ cache	+ "::::::"
									+ rs.getString(9) + "//////";
							
							System.out.println(data);
							
							//===================
							/*data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::" 
									+ cache	+ "::::::"
									// + rs.getString(10)+ "::::::"
								+ rs.getString(10) + "//////";*/
							//===================
						}
			}// else if(decide=="war_target1")
					
					
					else if (decide.equals("ACTCAT_ID.ACTIVITY_ID1")) { // System.out.println("hey inside the if for the area_id.comp_id");
						String ACTCAT_id = null;
						// System.out.println("unit name with number is :"+key1);
						ps = con.prepareStatement("select ac_id from mp_activity_mstr where am_id='"
								+ key1.substring(key1.lastIndexOf('-') + 1)
								+ "'");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							ACTCAT_id = data + rs.getString(1);

						// System.out.println("Activity id is :"+key1);
						ps = con.prepareStatement("select ac_name||' '||ac_id from ac_mstr where ac_id='"
								+ ACTCAT_id + "'");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::";
						// System.out.println("Region name was :"+data);
					}// if(decide=="area_id.comp_id")
						
					else if (decide.equals("war_target2"))
					{ 		
							if (key1.length() >= 3)
							key1 = key1.substring(key1.lastIndexOf('-') + 1);
							//System.out.println("Key1"+key1);
							key2 = key2.substring(key2.lastIndexOf('-') + 1);
							//System.out.println("Key2"+key2);
							if ((userrole.equals("admin"))|| (userrole.equals("audit")))
							{
								ps = con.prepareStatement("select "									
							+ "w.WAR_CONTROLENO,"
							/*+ "wp.WORK_CONTROLENO,"*/
							+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
							+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
							+ " to_char(wp.WORK_DATE,'dd-mm-yyyy') as WORK_DATE,"
							+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=wp.huma_id) as huma_id2,"
							/*+ "(select huma_fname||' '||huma_lname from huma_mstr where huma_id=wp.huma_id) as huma_name,"*/
							/*+ "w.WAR_CUST_COUNT,"*/
							+ "wp.UHFXLSR,"
							+ "wp.NO_VILLAGES,"
							+ "wp.VILLAGE_NAMES,"
							+ "wp.ODFTOD,"
							+ "wp.CUSTOMERS,"
							+ "wp.STAY_UNIT,"
							+ "wp.OBSERVATIONS,"
							//+ "w.WAR_SDRCUST_COUNT,"
							+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=wp.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(wp.WORK_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
							/*+ "w.WAR_villages,"*/
							+ "to_char(wp.WORK_DATE,'Day') as WORK_DAY"
							+ " from WORK_PLAN1 wp ,WAR_TARGET w,bsflunit_mstr b, huma_mstr h where wp.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
							+ "and h.bsflunit_ucode=nvl('"
							+ key1
							+ "',h.bsflunit_ucode) and wp.huma_id=nvl('"
							+ key2
							+ "',wp.huma_id) order by b.area_id, b.bsflunit_ucode, wp.huma_id, wp.WORK_SEQID");
							}
							else if (userrole.equals("areahead"))
							{
								ps = con.prepareStatement("select "
							+ "w.WAR_CONTROLENO,"
							/*+ "wp.WORK_CONTROLENO,"*/
							+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
							+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
							+ " to_char(wp.WORK_DATE,'dd-mm-yyyy') as WORK_DATE,"
							/*+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,w.WAR_CUST_COUNT,w.WAR_SDRCUST_COUNT,"*/
							+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=wp.huma_id) as huma_id2,"
							/*+ "(select huma_fname||' '||huma_lname from huma_mstr where huma_id=wp.huma_id) as huma_name,"*/
							+ "wp.UHFXLSR,"
							+ "wp.NO_VILLAGES,"
							+ "wp.VILLAGE_NAMES,"
							+ "wp.ODFTOD,"
							+ "wp.CUSTOMERS,"
							+ "wp.STAY_UNIT,"
							+ "wp.OBSERVATIONS,"
							+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=wp.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(wp.WORK_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
							/*+ "w.WAR_villages,"*/
							+ "to_char(wp.WORK_DATE,'Day') as WORK_DAY"
							+ " from WORK_PLAN1 wp,WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where wp.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
							+ "and h.bsflunit_ucode=nvl('"
							+ key1
							+ "',h.bsflunit_ucode) and wp.huma_id=nvl('"
							+ key2
							+ "',wp.huma_id) and b.bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where area_id in (select area_id from area_mstr where huma_id='"
							+ huma_idd
							+ "')) order by b.area_id, b.bsflunit_ucode, wp.huma_id, wp.WORK_SEQID");
							}
							else if (userrole.equals("unithead"))
							{
								ps = con.prepareStatement("select "
							+ "w.WAR_CONTROLENO,"
							/*+ "wp.WORK_CONTROLENO,"*/
							+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
							+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
							+ " to_char(wp.WORK_DATE,'dd-mm-yyyy') as WORK_DATE,"
							/*+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,w.WAR_CUST_COUNT,w.WAR_SDRCUST_COUNT,"*/
							/*+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=wp.huma_id) as huma_id2,wp.ACT_CAT,wp.ACT_NAME,"*/
							+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=wp.huma_id) as huma_id2,"
							/*+ "(select huma_fname||' '||huma_lname from huma_mstr where huma_id=wp.huma_id) as huma_name,"*/
							+ "wp.UHFXLSR,"
							+ "wp.NO_VILLAGES,"
							+ "wp.VILLAGE_NAMES,"
							+ "wp.ODFTOD,"
							+ "wp.CUSTOMERS,"
							+ "wp.STAY_UNIT,"
							+ "wp.OBSERVATIONS,"
							+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=wp.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(wp.WORK_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
							/*+ "w.WAR_villages,"*/
							+ "to_char(wp.WORK_DATE,'Day') as WORK_DAY"
							+ " from WORK_PLAN1 wp,WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where wp.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
							+ "and h.bsflunit_ucode=nvl('"
							+ key1
							+ "',h.bsflunit_ucode) and wp.huma_id=nvl('"
							+ key2
							+ "',wp.huma_id) and b.bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where huma_id='"
							+ huma_idd
							+ "') order by b.area_id, b.bsflunit_ucode, wp.huma_id, wp.WORK_SEQID");
							}
							else
								ps = con.prepareStatement("select "
							+ "w.WAR_CONTROLENO,"
							/*+ "wp.WORK_CONTROLENO,"*/
							+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
							+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
							+ " to_char(wp.WORK_DATE,'dd-mm-yyyy') as WORK_DATE,"
							+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=wp.huma_id) as huma_id2,"
							/*+ "(select huma_fname||' '||huma_lname from huma_mstr where huma_id=wp.huma_id) as huma_name,"*/
							+ "wp.UHFXLSR,"
							+ "wp.NO_VILLAGES,"
							+ "wp.VILLAGE_NAMES,"
							+ "wp.ODFTOD,"
							+ "wp.CUSTOMERS,"
							+ "wp.STAY_UNIT,"
							+ "wp.OBSERVATIONS,"
							/*+ "w.WAR_CUST_COUNT,"
							+ "w.WAR_SDRCUST_COUNT,"*/
							/*+ "wp.ACT_CAT,"
							+ "wp.ACT_NAME,"*/
							+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=wp.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(wp.WORK_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
							/*+ "w.WAR_villages,"*/
							+ "to_char(wp.WORK_DATE,'Day') as WORK_DAY"
							+ " from WORK_PLAN1 wp,WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where wp.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
							+ "and h.bsflunit_ucode=nvl('"
							+ key1
							+ "',h.bsflunit_ucode) and wp.huma_id=nvl('"
							+ key2
							+ "',wp.huma_id) and wp.huma_id ='"
							+ huma_idd
							+ "' order by b.area_id, b.bsflunit_ucode, wp.huma_id, wp.WORK_SEQID");
							
							rs = ps.executeQuery();
							ps1 = con
									.prepareStatement("select VNAME from village_mstr where vcode=? and BSFLUNIT_UCODE=?");
							while (rs.next()) {
								// System.out.println("target amount is :"+rs.getLong(8));
								StringTokenizer st = null;
								String vl = rs.getString(1);
								
								cache = "";
								String un = rs.getString(3);
								// System.out.println("unit name and code "+un );
								int i1 = un.lastIndexOf('-');
								String ucode = un.substring(i1 + 1);
								// System.out.println("unit code is "+ucode);
								String cache1 = "";
								// if (vl.contains("::")) {
								st = new StringTokenizer(vl, "::");
								while (st.hasMoreElements()) {
									String temp = (String) st.nextElement();
									ps1.setString(1, temp);
									ps1.setString(2, ucode);
									rs1 = ps1.executeQuery();
									if (rs1.next()) {
										cache1 = rs1.getString(1) + "-";
									}
									// below else black added by Rajashekhar to
									// avoid wrong village names if village not
									// found in village master
									else {
										cache1 = "";
									}// while
										// System.out.println(cache1+"-"+temp+"::");
									cache += cache1 + temp + "::";
									// System.out.println("cache="+cache);
								}// while
							int length = cache.length();
							cache = cache.substring(0, length - 2);
							data = data + rs.getString(1) + "::::::"
							+ rs.getString(2) + "::::::"
							+ rs.getString(3) + "::::::"
							+ rs.getString(4) + "::::::"
							+ rs.getString(5) + "::::::"
							+ rs.getString(6) + "::::::"
							
							+ rs.getString(7) + "::::::"
								+ cache	+ "::::::"
							+ rs.getString(8) + "::::::"
							+ rs.getString(9) + "::::::"
							+ rs.getString(10) + "::::::"
							+ rs.getString(11) + "::::::"
							+ rs.getString(12) + "::::::"
							+ rs.getString(13) + "::::::"
							+ rs.getString(14) + "//////";
						
							/*data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8)+ "::::::"
									+ cache	+ "::::::"
									//+ cache	+ "::::::"
									+ rs.getString(9) + "//////";*/
							
							//===================
							/*data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::" 
									+ cache	+ "::::::"
									// + rs.getString(10)+ "::::::"
								+ rs.getString(10) + "//////";*/
							//===================
						}
			}// else if(decide=="war_target2")
					else if (decide.equals("BSFLUNIT_UCODE")) { // System.out.println("hey inside the if for the country_mstr");
                       // if (key1 == "")
                         //      ps = con.prepareStatement("select s_id,s_name,s_opening_stock,to_char(s_cdate,'dd-mm-yyyy') from stationary_mstr order by s_id");
                       // else {
                               if (key1.length() >= 3)// if(huma_id2!="")
                                      // key1 = key1.substring(0, 3);//
                                      // substring(0,1);
                                      key1 = key1
                                                    .substring(key1.lastIndexOf("-") + 1);

                               ps = con.prepareStatement("select BSFLUNIT_NAME from BSFLUNIT_MSTR where BSFLUNIT_UCODE='"
                                             + key1 + "'");
                       // }
                        rs = ps.executeQuery();// System.out.println("Hey going for while loop");
                        while (rs.next()){
                               data = data + rs.getString(1) + "::::::";
                                             //+ rs.getString(2) + "::::::"
                                             //+ rs.getString(3) + "::::::"
                                             //+ rs.getString(4) + "//////";
					}
				}
				


					
					
					// if(decide=="comp_mstr")
					/*
					 * else if (decide.equals("comp_id")) {
					 * //System.out.println(
					 * "hey inside the if for the comp_id");
					 * 
					 * ps = con.prepareStatement(
					 * "select comp_id from comp_mstr where comp_id=(select max(comp_id) from comp_mstr)"
					 * ); rs = ps.executeQuery(); if (rs == null) { System.out
					 * .println("rs is null in getuserupate and in comp_id"); }
					 * if (rs.next()) { do { String s =
					 * (rs.getString(1)).substring(0, 1); int n =
					 * Integer.parseInt((rs.getString(1)) .substring(1)); n = n
					 * + 1; if (n <= 9) data = s + "0" + n; if ((n >= 10) && (n
					 * <= 99)) data = s + "00" + n; if ((n >= 100) && (n <=
					 * 999)) data = s + n; if (n > 999) throw new Exception(); }
					 * while (rs.next());// while }// if else data = "C001"; }//
					 * else if(decide=="comp_id")
					 */
					else if (decide.equals("comp_id")) { // System.out.println("hey inside the if for the comp_id");

						ps = con.prepareStatement("select comp_id from comp_mstr where comp_id=(select max(comp_id) from comp_mstr)");
						rs = ps.executeQuery();
						if (rs == null) {
							System.out
									.println("rs is null in getuserupate and in comp_id");
						}
						if (rs.next()) {
							do {
								String s = (rs.getString(1)).substring(0, 1);
								int n = Integer.parseInt((rs.getString(1))
										.substring(1));
								n = n + 1;
								if (n <= 9)
									data = s + "00" + n;
								if ((n >= 10) && (n <= 99))
									data = s + "0" + n;
								if ((n >= 100) && (n <= 999))
									data = s + n;
								if (n > 999)
									throw new Exception();
							} while (rs.next());// while
						}// if
						else
							data = "C001";
					}// else if(decide=="comp_id")
					else if (decide.equals("s_id.s_name")) { // System.out.println("hey inside the if for the bsflunit_mstr");
						// String key3 = "";
						
						 key1= key1.substring(key1.lastIndexOf('-') + 1);
						 //key2= key2.substring(key1.lastIndexOf('-') + 1);
						// System.out.println("key1="+key1+" key2="+key2+" key3="+key3);
						ps = con.prepareStatement("select s_name from stationary_mstr where s_id='" + key1+ "'");
								

						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "//////";
				}//else if(decide=="s_id.s_name")

					
					else if (decide.equals("ucode_id.unit_nam")) { // System.out.println("hey inside the if for the busi_id.comp_id");
						//if (key1.length() >= 4)// if(busi_id>=4)
						key1 = key1.substring(key1.lastIndexOf('-') + 1);// substring(0,4);
						ps = con.prepareStatement("select bsflunit_name from bsflunit_mstr  where bsflunit_ucode='"+ key1 + "'");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::";
											
					}
					
					else if(decide.equals("s_id.i_stock")){
						key1= key1.substring(key1.lastIndexOf('-') + 1);
//						 key2= key2.substring(key1.lastIndexOf('-') + 1);
						 
						 ps=con.prepareStatement("select s_name from stationary_mstr a where s_id='"+key1+"'");
						 rs=ps.executeQuery();
						 while(rs.next()){
							 data=data+rs.getString(1)+ "::::::";
								//	 +rs.getString(2)+ "::::::";
						 }
					}
					else if (decide.equals("ucode_id.unit_nam")) { // System.out.println("hey inside the if for the busi_id.comp_id");
						//if (key1.length() >= 4)// if(busi_id>=4)
						key1 = key1.substring(key1.lastIndexOf('-') + 1);// substring(0,4);
						ps = con.prepareStatement("select bsflunit_name from bsflunit_mstr  where bsflunit_ucode='"+ key1 + "'");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::";
											
					}
					else if (decide.equals("ucode_id.unit_name")) { // System.out.println("hey inside the if for the busi_id.comp_id");
						//if (key1.length() >= 4)// if(busi_id>=4)
						key1 = key1.substring(key1.lastIndexOf('-') + 1);// substring(0,4);
						ps = con.prepareStatement("select bsflunit_name from bsflunit_mstr where bsflunit_ucode='"
								+ key1 + "'");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::";
											
					}

					else if (decide.equals("unit_id.busi_id.comp_id")) { // System.out.println("hey inside the if for the busi_id.comp_id");
						/*
						 * if (key1.length() >= 3)// if(busi_id>=4) key1 =
						 * key1.substring(0, 3);// substring(0,4);
						 */
						key1 = key1.substring(key1.lastIndexOf("-") + 1);
						// //System.out.println("inside unit_id.busi_id.comp_id and key1="+key1);
						// ps =
						// con.prepareStatement("select a.AREA_ID||' '||a.AREA_NAME, c.comp_id||' '||c.comp_name from comp_mstr c, AREA_MSTR a,BSFLUNIT_MSTR u where c.comp_id=a.comp_id and a.AREA_ID=u.AREA_ID and u.BSFLUNIT_UCODE='"+
						// key1 + "'");
						ps = con.prepareStatement("select a.AREA_NAME||'-'||a.AREA_ID, c.comp_name||'-'||c.comp_id from comp_mstr c, AREA_MSTR a,BSFLUNIT_MSTR u where c.comp_id=a.comp_id and a.AREA_ID=u.AREA_ID and u.BSFLUNIT_UCODE='"
								+ key1 + "'");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::";
					}// if(decide=="busi_id.comp_id")

					else if (decide.equals("Unitname.code")) { // System.out.println("hey inside the if for the busi_id.comp_id");
						/*
						 * if (key1.length() >= 3)// if(busi_id>=4) key1 =
						 * key1.substring(0, 3);// substring(0,4);
						 */
						key1 = key1.substring(key1.lastIndexOf("-") + 1);
						// //System.out.println("inside unit_id.busi_id.comp_id and key1="+key1);
						// ps =
						// con.prepareStatement("select a.AREA_ID||' '||a.AREA_NAME, c.comp_id||' '||c.comp_name from comp_mstr c, AREA_MSTR a,BSFLUNIT_MSTR u where c.comp_id=a.comp_id and a.AREA_ID=u.AREA_ID and u.BSFLUNIT_UCODE='"+
						// key1 + "'");
						ps = con.prepareStatement("select BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE from  BSFLUNIT_MSTR where AREA_ID='"
								+ key1 + "'");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::";

					}// if(decide=="busi_id.comp_id")

					if (decide.equals("act_mstr")) { // System.out.println("hey inside the if for the comp_mstr");
						if (key1 == "")
							ps = con.prepareStatement("select ACTIVITY_ID,ACTIVITY_NAME,(select ACTCAT_NAME||'-'||ACTCAT_ID from ACTCAT_MSTR ac where ac.ACTCAT_ID=ACTIVITY_MSTR.ACTCAT_ID) as cat_id from ACTIVITY_MSTR order by ACTIVITY_ID");
						else {
							ps = con.prepareStatement("select ACTIVITY_ID,ACTIVITY_NAME, (select ACTCAT_NAME||'-'||ACTCAT_ID from ACTCAT_MSTR ac where ac.ACTCAT_ID=ACTIVITY_MSTR.ACTCAT_ID) as cat_id from ACTIVITY_MSTR where ACTIVITY_ID='"
									+ key1.substring(key1.lastIndexOf("-") + 1)
									+ "' order by ACTIVITY_ID");
						}
						rs = ps.executeQuery();// System.out.println("Hey going for while loop");
						while (rs.next()) { // //System.out.println("inside the main while loop");
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "//////";
							// //System.out.println("inside the while loop,the data is ="+data);
						}// while
					}// if(decide=="comp_mstr")
					else if (decide.equals("a_id")) { // System.out.println("hey inside the if for the comp_id");

						ps = con.prepareStatement("select ACTIVITY_ID from ACTIVITY_MSTR where ACTIVITY_ID=(select max(ACTIVITY_ID) from ACTIVITY_MSTR)");
						rs = ps.executeQuery();
						if (rs == null) {
							// System.out.println("rs is null in getuserupate and in comp_id");
						}
						if (rs.next()) {
							do {
								String s = (rs.getString(1)).substring(0, 1);
								int n = Integer.parseInt((rs.getString(1))
										.substring(1));
								n = n + 1;
								if (n <= 9)
									data = s + "00" + n;
								if ((n >= 10) && (n <= 99))
									data = s + "0" + n;
								if ((n >= 100) && (n <= 999))
									data = s + n;
								if (n > 999)
									throw new Exception();
							} while (rs.next());// while
						}// if
						else
							data = "A001";
					}// else if(decide=="comp_id")

					else if (decide.equals("comp_mstr1")) { // //System.out.println("Msr debug inside the if for the comp_mstr");
						if (key1 == "")
							ps = con.prepareStatement("select ACTCAT_ID,ACTCAT_NAME from ACTCAT_MSTR order by ACTCAT_ID");
						else {
							/*
							 * if (key1.length() >= 5)// if(comp_id2!="") key1 =
							 * key1.substring(0, 5);// substring(0,4);
							 */
							ps = con.prepareStatement("select ACTCAT_ID,ACTCAT_NAME from ACTCAT_MSTR where ACTCAT_ID='"
									+ key1.substring(key1.lastIndexOf("-") + 1)
									+ "' order by ACTCAT_ID");
						}
						rs = ps.executeQuery();// System.out.println("Hey going for while loop");
						while (rs.next()) { // //System.out.println("inside the main while loop");
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "//////";
							// //System.out.println("inside the while loop,the data is ="+data);
						}// while
					}// if(decide=="comp_mstr")
					else if (decide.equals("cat_id")) { // System.out.println("hey inside the if for the comp_id");

						ps = con.prepareStatement("select ACTCAT_ID from ACTCAT_MSTR where ACTCAT_ID=(select max(ACTCAT_ID) from ACTCAT_MSTR)");
						rs = ps.executeQuery();
						if (rs == null) {
							// System.out.println("rs is null in getuserupate and in comp_id");
						}
						if (rs.next()) {
							do {
								String s = (rs.getString(1)).substring(0, 2);
								int n = Integer.parseInt((rs.getString(1))
										.substring(2));
								n = n + 1;
								if (n <= 9)
									data = s + "00" + n;
								if ((n >= 10) && (n <= 99))
									data = s + "0" + n;
								if ((n >= 100) && (n <= 999))
									data = s + n;
								if (n > 999)
									throw new Exception();
							} while (rs.next());// while
						}// if
						else
							data = "AC001";
					}// else if(decide=="comp_id")

					else if (decide.equals("area_mstr")) { // System.out.println("hey inside the if for the area_mstr");
						// if (key1 == "")
						// ps =
						// con.prepareStatement("select (select comp_id||' '||comp_name from comp_mstr where comp_id=area_mstr.comp_id) as comp_id,area_id,area_name,area_remarks,area_strategy,(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=area_mstr.huma_id) as huma_id from area_mstr order by area_id");
						// else {
						/*
						 * if (key1.length() >= 4)// if(area_id2!="") key1 =
						 * key1.substring(0, 4);
						 */// substring(0,4);

						if (key1.length() >= 3)// if(area_id2!="")
							// key1 = key1.substring(0, 3);
							key1 = key1.substring(key1.lastIndexOf("-") + 1);
						key2 = key2.substring(key2.lastIndexOf("-") + 1);

						key3 = request.getParameter("key3").toString();
						System.out.println("key1=" + key1 + " key2=" + key2
								+ " key3=" + key3);
						ps = con.prepareStatement("select (select comp_name||'-'||comp_id from comp_mstr where comp_id=area_mstr.comp_id) as comp_id,area_id,area_name,area_remarks,area_Strategy,area_lang,(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=area_mstr.huma_id) as huma_id from area_mstr where area_id=nvl('"

								+ key1
								+ "',area_id) and  area_mstr.huma_id=nvl('"
								+ key2
								+ "',area_mstr.huma_id) and area_lang=nvl('"
								+ key3 + "',area_lang) order by area_id");
						// }
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "//////";
					}// if(decide=="area_mstr")

					// Added By Rajesh
					else if (decide.equals("HRStaus_mstr")) {
						if (key1.length() >= 3)
							key3 = request.getParameter("key3").toString();
						System.out.println("key1=" + key1 + " key2=" + key2
								+ " key3=" + key3);
						ps = con.prepareStatement("select hrstatus_name,hrstatus_freezed,hrstatus_transfer,HRSTATUS_ID from hrstatus_mstr where hrstatus_name=nvl('"
								+ key1
								+ "',HRSTATUS_NAME) and  HRSTATUS_FREEZED=nvl('"
								+ key2
								+ "',HRSTATUS_FREEZED) and HRSTATUS_TRANSFER=nvl('"
								+ key3
								+ "',HRSTATUS_TRANSFER) order by HRSTATUS_ID");
						// }
						rs = ps.executeQuery();
						while (rs.next())
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "//////";
					}

					else if (decide.equals("area_id")) { // System.out.println("hey inside the if for the area_id");

						ps = con.prepareStatement("select area_id from area_mstr where area_id=(select max(area_id) from area_mstr)");
						rs = ps.executeQuery();
						if (rs == null) {
							System.out
									.println("rs is null in getuserupate and in area_id");
						}
						if (rs.next()) {
							do {
								String s = (rs.getString(1)).substring(0, 1);
								int n = Integer.parseInt((rs.getString(1))
										.substring(1));
								n = n + 1;
								/*
								 * if (n <= 9) data = s + "00" + n; if ((n >=
								 * 10) && (n <= 99)) data = s + "0" + n; if ((n
								 * >= 100) && (n <= 999)) data = s + n;
								 */
								if (n <= 9)
									data = s + "00" + n;
								if ((n >= 10) && (n <= 99))
									data = s + n;
								if ((n >= 100) && (n <= 999))
									data = s + n;
								if (n > 999)
									throw new Exception();
							} while (rs.next());// while
						}// if
						else
							data = "B001";
					}// else if(decide="area_id")
					else if (decide.equals("prod_mstr")) { // System.out.println("hey inside the if for the prod_mstr");
						if (key1 == "")
							ps = con.prepareStatement("select (select comp_id||' '||comp_name from comp_mstr where comp_id=prod_mstr.comp_id) as comp_id,(select area_id||' '||area_name from area_mstr where area_id=prod_mstr.area_id) as area_id,prod_id,prod_name,prod_version,prod_author,prod_frontend,prod_backend,to_char(prod_releasedate,'dd-mm-yyyy') from prod_mstr order by prod_id");
						else {
							if (key1.length() >= 5)// if(prod_id!="")
								key1 = key1.substring(0, 5);// substring(0,4);

							ps = con.prepareStatement("select (select comp_id||' '||comp_name from comp_mstr where comp_id=prod_mstr.comp_id) as comp_id,(select area_id||' '||area_name from area_mstr where area_id=prod_mstr.area_id) as area_id,prod_id,prod_name,prod_version,prod_author,prod_frontend,prod_backend,to_char(prod_releasedate,'dd-mm-yyyy') from prod_mstr where prod_id='"
									+ key1 + "' order by prod_id");
						}
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "//////";
					}// if(decide=="prod_mstr")
					else if (decide.equals("prod_id")) { // System.out.println("hey inside the if for the prod_id");
						ps = con.prepareStatement("select prod_id from prod_mstr where prod_id=(select max(prod_id) from prod_mstr)");
						rs = ps.executeQuery();
						if (rs == null) {
							System.out
									.println("rs is null in getuserupdate for prod_id");
						}
						if (rs.next()) {
							do {
								String s = (rs.getString(1)).substring(0, 1);
								int n = Integer.parseInt((rs.getString(1))
										.substring(1));
								n = n + 1;
								if (n <= 9)
									data = s + "000" + n;
								if ((n >= 10) && (n <= 99))
									data = s + "00" + n;
								if ((n >= 100) && (n <= 999))
									data = s + "0" + n;
								if ((n >= 1000) && (n <= 9999))
									data = s + n;
								if (n > 9999)
									throw new Exception();
							} while (rs.next());// while
						}// if
						else
							data = "P0001";
					}// else if(decide=="prod_id")
					else if (decide.equals("BSFLUNIT_NAME.area_name")) { // System.out.println("hey inside the if for the area_id.comp_id");
						/*
						 * if (key1.length() >= 4)// if(area_id>=4) key1 =
						 * key1.substring(0, 4);// substring(0,4);
						 *//*
							 * String txt=key1; int i = txt.lastIndexOf('-');
							 * String key=txt.substring(0, i); String
							 * value=txt.substring(i+1); key1=value;
							 */
						// System.out.println("unit number is :"+key1);
						if (key1.length() >= 4)
							key1 = key1.substring(key1.lastIndexOf("-") + 1);
						// ps =
						// con.prepareStatement("select area_name from bsflunit_mstr where BSFLUNIT_UCODE='"+
						// key1+"'");
						ps = con.prepareStatement("select a.area_name||'-'||a.area_id from area_mstr a where a.area_id=(select b.area_id from  bsflunit_mstr b where b.bsflunit_ucode='"
								+ key1 + "')");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::";
						// System.out.println("Region name was :"+data);
					}// if(decide=="area_id.comp_id")
					else if (decide.equals("area_id.comp_id")) { // System.out.println("hey inside the if for the area_id.comp_id");
						if (key1.length() >= 4)// if(area_id>=4)
							key1 = key1.substring(0, 4);// substring(0,4);
						ps = con.prepareStatement("select comp_id||' '||comp_name from comp_mstr where comp_id=(select comp_id from area_mstr where area_id='"
								+ key1 + "')");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::";
					}// if(decide=="area_id.comp_id")
					else if (decide.equals("ACTCAT_ID.ACTIVITY_ID")) { // System.out.println("hey inside the if for the area_id.comp_id");
						String ACTCAT_id = null;
						// System.out.println("unit name with number is :"+key1);
						ps = con.prepareStatement("select ACTCAT_ID from ACTIVITY_MSTR where ACTIVITY_ID='"
								+ key1.substring(key1.lastIndexOf('-') + 1)
								+ "'");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							ACTCAT_id = data + rs.getString(1);

						// System.out.println("Activity id is :"+key1);
						ps = con.prepareStatement("select ACTCAT_NAME||' '||ACTCAT_ID from ACTCAT_MSTR where ACTCAT_ID='"
								+ ACTCAT_id + "'");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::";
						// System.out.println("Region name was :"+data);
					}// if(decide=="area_id.comp_id")
					else if (decide.equals("client_mstr")) { // System.out.println("hey inside the if for the client_mstr");
						if (key1.length() >= 7)// if(client_id!="")
							key1 = key1.substring(0, 7);// substring(0,7);
						if (key2.length() >= 7)// if(city_id>=7)
							key2 = key2.substring(0, 7);// substring(0,7);
						ps = con.prepareStatement("select 	   				client_id,					to_char(client_meetDate,'dd-mm-yyyy hh:mi:ss am'),					client_name,					client_ceo,					client_address,					(select city_id||' '||city_name from city_mstr where city_id=client_mstr.client_city)as client_city,					(select state_id||' '||state_name from state_mstr where state_id=(select state_id from city_mstr where city_id=client_mstr.client_city)) as client_state,					client_phone,					client_fax,					client_email,					client_web,					client_metPersonName,					client_metPersonDesignation,					client_metPersonMobile,					client_type,					client_legality,					client_startYear,					client_activities,					client_states,					client_districts,					client_branches,					client_outstanding,					client_customers,					client_savings,					client_deposits,					client_savCustomers,					client_mis,					client_desc,					client_risk,					client_opsHrPolocy,					client_staff,					client_grants,					client_plan,					client_nbranches,					client_misPerson,					client_potential,					client_remarks,					client_cdate,					client_mdate				from client_mstr where client_id=nvl('"
								+ key1
								+ "',client_id) and client_city=nvl('"
								+ key2 + "',client_city)");

						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next()) {
							for (int i = 1; i <= 37; i++) {
								if (i == 37)
									data = data + rs.getString(i);
								else
									data = data + rs.getString(i) + "::::::";
							}// for loop
							data = data + "//////";
						}// while(rs.next())
					}// if(decide=="client_mstr")
					else if (decide.equals("client_id")) { // System.out.println("hey inside the if for the client_id");
						ps = con.prepareStatement("select client_id from client_mstr where client_id=(select max(client_id) from client_mstr)");
						rs = ps.executeQuery();
						if (rs == null)
							System.out
									.println("rs is null in getuserupdate(client_id)");
						if (rs.next()) {
							do {
								String s = (rs.getString(1)).substring(0, 1);
								int n = Integer.parseInt((rs.getString(1))
										.substring(1));
								n = n + 1;
								if (n <= 9)
									data = s + "00000" + n;
								if ((n >= 10) && (n <= 99))
									data = s + "0000" + n;
								if ((n >= 100) && (n <= 999))
									data = s + "000" + n;
								if ((n >= 1000) && (n <= 9999))
									data = s + "00" + n;
								if ((n >= 10000) && (n <= 99999))
									data = s + "0" + n;
								if ((n >= 100000) && (n <= 999999))
									data = s + n;
								if (n > 999999)
									throw new Exception();
							} while (rs.next());// while
						}// if
						else
							data = "C000001";
					}
					else if (decide.equals("st_id.st_name")) { // System.out.println("hey inside the if for the city_id.state_id");
						//if (key1.length() >= 7)// if(city_id>=7)
							key1 = request.getParameter("key1");// substring(0,7);
						ps = con.prepareStatement("select nvl(ope_stock,0)-nvl(clo_stock,0)avi from stationary_mst where st_id='"
								+ key1 + "'");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::";
					}
					// else if(decide=="client_id")
					else if (decide.equals("city_id.state_id")) { // System.out.println("hey inside the if for the city_id.state_id");
						if (key1.length() >= 7)// if(city_id>=7)
							key1 = key1.substring(0, 7);// substring(0,7);
						ps = con.prepareStatement("select state_id||' '||state_name from state_mstr where state_id=(select state_id from city_mstr where city_id='"
								+ key1 + "')");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::";
					}// if(decide=="city_id.state_id")
					else if (decide.equals("BSFLUNIT_MSTR")) { // System.out.println("hey inside the if for the bsflunit_mstr");
						// String key3 = "";
						key1 = key1.substring(key1.lastIndexOf('-') + 1);
						key2 = request.getParameter("key2");
						key2 = key2.substring(key2.lastIndexOf('-') + 1);
						key3 = request.getParameter("key3");
						key3 = key3.substring(key3.lastIndexOf('-') + 1);
						// System.out.println("key1="+key1+" key2="+key2+" key3="+key3);
						ps = con.prepareStatement("select "
								+ "b.bsflunit_ucode,"
								+ "b.bsflunit_name,"
								+ "(select a.area_name||'-'||a.area_ID from area_mstr a where a.area_id=b.area_id) as area_id,"
								+ "(select h.huma_fname||' '||h.huma_lname||'-'||h.huma_id from huma_mstr h where h.huma_id=b.huma_id) as huma_id,"
								+ "b.bsflunit_email," + "b.bsflunit_phone,"
								+ "b.bsflunit_mobile "
								+ "from bsflunit_mstr b "
								+ "where  b.bsflunit_ucode=nvl('" + key1
								+ "',b.bsflunit_ucode) "
								+ "and (b.area_id=nvl('" + key2
								+ "',b.area_id) or (b.area_id is null and '"
								+ key2 + "' is null)) "
								+ "and (b.huma_id=nvl('" + key3
								+ "',b.huma_id) or (b.huma_id is null and '"
								+ key3 + "' is null)) "
								+ "order by bsflunit_ucode");

						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "//////";
						

					}// if(decide=="bsflunit_mstr")
					else if (decide.equals("unit_inde")) { // System.out.println("hey inside the if for the bsflunit_mstr");
						// String key3 = "";
						key1 = key1.substring(key1.lastIndexOf('-') + 1);
												
						// System.out.println("key1="+key1+" key2="+key2+" key3="+key3);
						ps = con.prepareStatement("select bsflunit_ucode,bsflunit_name,s_id,s_name,to_char(date_of_indent,'dd-mm-yyyy'),clos_stock,new_stock,in_by,pre_by from unit_inde where bsflunit_ucode='" + key1+ "'");
								

						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "//////";
						System.out.println(data);
					}
					else if (decide.equals("issue_indent")) { // System.out.println("hey inside the if for the bsflunit_mstr");
						// String key3 = "";
						key1 = key1.substring(key1.lastIndexOf('-') + 1);
												
						// System.out.println("key1="+key1+" key2="+key2+" key3="+key3);
						ps = con.prepareStatement("select bsflunit_ucode,bsflunit_name,last_issued,clo_stock_unit,avail_stock,iss_stock,to_char(date_of_cou,'dd-mm-yyyy'),cou_id,cou_name from issue_indent where bsflunit_ucode='" + key1+ "'");
								

						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"			
									+ rs.getString(9) + "//////";
						
					}
					else if (decide.equals("STOCK_ENTRY")) { // System.out.println("hey inside the if for the bsflunit_mstr");
						// String key3 = "";{ // System.out.println("hey inside the else if for the city_mstr");
						if (key1 == "")
							ps = con.prepareStatement("select s_id,s_name,to_char(date_of_stock,'dd-mm-yyyy'),no_of_stock,value_of_purchase from stock_entry order by s_id");
						else{
						key1 = key1.substring(key1.lastIndexOf('-') + 1);											
						// System.out.println("key1="+key1+" key2="+key2+" key3="+key3);
						ps = con.prepareStatement("select s_id,s_name,to_char(date_of_stock,'dd-mm-yyyy'),no_of_stock,value_of_purchase from stock_entry where s_id='" + key1+ "' order by s_id");
								
						}
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						
						while (rs.next()){
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "//////";
						
					}
				}
				
					else if (decide.equals("stationary_mst")) { // System.out.println("hey inside the if for the bsflunit_mstr");
						// String key3 = "";
						key1 = request.getParameter("key1");
//						key2 = request.getParameter("key2");
//						
//						key3 = request.getParameter("key3");
						
						// System.out.println("key1="+key1+" key2="+key2+" key3="+key3);
						ps = con.prepareStatement("select st_id,st_name,to_char(date_of_entry,'dd-mm-yyyy'),ope_stock,clo_stock from stationary_mst where st_id='" + key1+ "'");
								

						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"			
									+ rs.getString(5) + "//////";
						
					}
					
					
					else if (decide.equals("country_mstr")) { // System.out.println("hey inside the if for the country_mstr");
						if (key1 == "")
							ps = con.prepareStatement("select country_id,country_name,country_currency from country_mstr order by country_id");
						else {
							if (key1.length() >= 3)// if(huma_id2!="")
								// key1 = key1.substring(0, 3);//
								// substring(0,1);
								key1 = key1
										.substring(key1.lastIndexOf("-") + 1);

							ps = con.prepareStatement("select country_id,country_name,country_currency from country_mstr where country_id='"
									+ key1 + "' order by country_id");
						}
						rs = ps.executeQuery();// System.out.println("Hey going for while loop");
						while (rs.next())
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "//////";
					}// if(decide=="country_mstr")
					else if (decide.equals("country_id")) { // System.out.println("hey inside the if for the country_id");
						ps = con.prepareStatement("select country_id from country_mstr where country_id=(select max(country_id) from country_mstr)");
						rs = ps.executeQuery();
						if (rs == null) {
							// System.out.println("rs is null");
						}
						if (rs.next()) {
							do {
								int n = Integer.parseInt(rs.getString(1));
								n = n + 1;
								if (n <= 9)
									data = "00" + n;
								if ((n >= 10) && (n <= 99))
									data = "0" + n;
								if ((n >= 100) && (n <= 999))
									data = "" + n;
								if (n > 999)// if(n>9999)
									throw new Exception();
							} while (rs.next());// while
						}// if
						else
							data = "001";
					}// else if(decide ="country_id")
					else if (decide.equals("state_mstr")) {
						// System.out.println("hey inside the else if for the state_mstr");
						if (key1 == "")
							ps = con.prepareStatement("select country_id,state_id,state_name from state_mstr order by state_id");
						else {
							if (key1.length() >= 3)// if(huma_id2!="")
								// key1 = key1.substring(0, 3);//
								// substring(0,1);
								key1 = key1
										.substring(key1.lastIndexOf("-") + 1);

							ps = con.prepareStatement("select country_id,state_id,state_name from state_mstr  where state_id='"
									+ key1 + "' order by state_id");
						}
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of state master");
						while (rs.next()) { // //System.out.println("inside the main while loop of the state master");

							ps1 = con
									.prepareStatement("select country_name||'-'||country_id from country_mstr where country_id='"
											+ rs.getString(1) + "'");
							// ps1 =
							// con.prepareStatement("select logtype from users where user_id='"+((HttpServletRequest)
							// request).getSession().getAttribute("user")+"'");

							rs1 = ps1.executeQuery();
							rs1.next();// System.out.println("at first visit data is :"+data);

							data = data + rs1.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "//////";
							// System.out.println("end of the state while loop,the data is ="+data);
						}// while
					}// else if(decide=="state_mstr")
					else if (decide.equals("state_id")) { // System.out.println("hey inside the if for the state_id");
						ps = con.prepareStatement("select state_id from state_mstr where state_id=(select max(state_id) from state_mstr)");
						rs = ps.executeQuery();
						if (rs == null) {
							// System.out.println("rs is null");
						}
						if (rs.next()) {
							do {
								int n = Integer.parseInt(rs.getString(1));
								n = n + 1;
								if (n <= 9)
									data = "00" + n;
								if ((n >= 10) && (n <= 99))
									data = "0" + n;
								if ((n >= 100) && (n <= 999))
									data = "" + n;
								if (n > 999)// if(n>9999)
									throw new Exception();
							} while (rs.next());// while
						}// if
						else
							data = "001";
					}// else if(decide ="state_id")
					else if (decide.equals("city_mstr"))// querying for the city
														// master data
					{ // System.out.println("hey inside the else if for the city_mstr");
						if (key1 == "")
							ps = con.prepareStatement("select state_id,city_id,city_name from city_mstr order by city_id");
						else {
							if (key1.length() >= 7)// if(huma_id2!="")
								// key1 = key1.substring(0, 7);//
								// substring(0,1);
								key1 = key1
										.substring(key1.lastIndexOf("-") + 1);
							ps = con.prepareStatement("select state_id,city_id,city_name from city_mstr where city_id='"
									+ key1 + "' order by city_id");
						}
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of city master");
						while (rs.next()) { // //System.out.println("inside the main while loop of the city master");
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "//////";// System.out.println("end of city while loop,the data is ="+data);
						}// while
					}// else if(decide=="city_mstr")
					else if (decide.equals("city_id")) { // System.out.println("hey inside the if for the city_id");
						ps = con.prepareStatement("select city_id from city_mstr where city_id=(select max(city_id) from city_mstr)");
						rs = ps.executeQuery();
						if (rs == null) {
							// System.out.println("rs is null");
						}
						if (rs.next()) {
							do {
								String s = (rs.getString(1)).substring(0, 2);
								int n = Integer.parseInt((rs.getString(1))
										.substring(2));
								n = n + 1;
								if (n <= 9)
									data = s + "0000" + n;
								if ((n >= 10) && (n <= 99))
									data = s + "000" + n;
								if ((n >= 100) && (n <= 999))
									data = s + "00" + n;
								if ((n >= 1000) && (n <= 9999))
									data = s + "0" + n;
								if ((n >= 10000) && (n <= 99999))
									data = s + n;
								if (n > 99999)
									throw new Exception();
							} while (rs.next());// while
						}// if
						else
							data = "CT00001";
					}// else if(decide ="city_id")
					else if (decide.equals("head_mstr"))// querying for the head
														// master data
					{ // System.out.println("hey inside the else if for the head_mstr");
						if (key1 == "")
							ps = con.prepareStatement("select head_id,head_name,head_client,head_service from head_mstr order by head_id");
						else {
							if (key1.length() >= 4)// if(head_id!="")
								key1 = key1.substring(0, 4);// substring(0,4);
							ps = con.prepareStatement("select head_id,head_name,head_client,head_service from head_mstr where head_id='"
									+ key1 + "' order by head_id");
						}
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of head master");
						while (rs.next()) {
							String data2 = "";
							rs2 = null;

							ps2 = con
									.prepareStatement("select head_enable,head_id,area_id from headbind_mstr where head_id='"
											+ rs.getString(1)
											+ "' order by area_id");

							rs2 = ps2.executeQuery();
							while (rs2.next())
								data2 = data2 + rs2.getString(1) + "::::::";
							if (data2.length() > 5)
								data2 = data2.substring(0, data2.length() - 6);
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::" + data2
									+ "//////";
						}// while
					}// else if(decide ="head_mstr")
					else if (decide.equals("head_id")) { // System.out.println("hey inside the if for the head_id");
						ps = con.prepareStatement("select head_id from head_mstr where head_id=(select max(head_id) from head_mstr)");
						rs = ps.executeQuery();
						if (rs == null) {
							// System.out.println("rs is null");
						}
						if (rs.next()) {
							do {
								int n = Integer.parseInt(rs.getString(1));
								n = n + 1;
								if (n <= 9)
									data = "000" + n;
								if ((n >= 10) && (n <= 99))
									data = "00" + n;
								if ((n >= 100) && (n <= 999))
									data = "0" + n;
								if ((n >= 1000) && (n <= 9999))
									data = "" + n;
								if (n > 9999)
									throw new Exception();
							} while (rs.next());// while
						}// if

						else
							data = "0001";
					}// else if(decide ="head_id")
					else if (decide.equals("users"))// querying for the head
													// master data
					{ // System.out.println("hey inside the else if for the users");
						if (key1 == "")
							ps = con.prepareStatement("select user_id,pswd,logtype from users order by user_id");
						else {
							if (key1.length() >= 4)// if(eno!="")
								key1 = key1.substring(0, 4);// substring(0,1);
							ps = con.prepareStatement("select user_id,pswd,logtype from users where user_id='"
									+ key1 + "' order by user_id");
						}
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of head master");
						while (rs.next())
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "//////";
					}// else if(decide ="users")
					else if (decide.equals("holiday_mstr"))// querying for the
															// holiday master
															// data
					{ // System.out.println("hey inside the else if for the holiday_mstr :getuseruupdate.jsp");
						if (key1 == "")
							ps = con.prepareStatement("select holiday_id,weekoff_id,to_char(holiday_date,'dd-mm-yyyy'),holiday_title from holiday_mstr order by holiday_id");
						else {
							if (key1.length() >= 4)// if(huma_id2!="")
								key1 = key1.substring(0, 4);// substring(0,1);
							ps = con.prepareStatement("select holiday_id,weekoff_id,to_char(holiday_date,'dd-mm-yyyy'),holiday_title from holiday_mstr where holiday_id='"
									+ key1 + "' order by holiday_id");
						}
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of hiliday master");
						while (rs.next()) { // //System.out.println("inside the main while loop of the holiday master");
							String data2 = "";
							rs2 = null;

							// System.out.println("hey the holiday_id="+rs.getString(1));
							ps2 = con
									.prepareStatement("select holiday_enable,holiday_id,state_id from holidaybind_mstr where holiday_id='"
											+ rs.getString(1)
											+ "' order by state_id");

							rs2 = ps2.executeQuery();
							while (rs2.next()) {
								data2 = data2 + rs2.getString(1) + "::::::";
								// System.out.println("hey inside the inner while here the holiday-id,state_id are="+rs2.getString(2)+":"+rs2.getString(3));
							}// System.out.println("the data2.length is :"+data2.length());
							if (data2.length() > 5)
								data2 = data2.substring(0, data2.length() - 6);
							// System.out.println("the visit data2 is :"+data2);
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::" + data2
									+ "//////";
							// System.out.println("end of holiday while loop,the data is ="+data);
						}// while
					}// else if(decide=="holiday_mstr")
					else if (decide.equals("weekoff_mstr"))// querying for the
															// weekoff master
															// data
					{ // System.out.println("hey inside the else if for the weekoff_mstr :getuseruupdate.jsp");
						if (key1 == "")
							ps = con.prepareStatement("select weekoff_id,comp_id,to_char(weekoff_fdate,'dd-mm-yyyy'),to_char(weekoff_tdate,'dd-mm-yyyy') from weekoff_mstr order by weekoff_id"); // we
																																																	// just
																																																	// get
																																																	// the
																																																	// single
																																																	// row
																																																	// of
																																																	// each
																																																	// common
																																																	// fields
																																																	// because
																																																	// we
																																																	// dint
																																																	// use
																																																	// seperate
																																																	// binding
																																																	// table
						else {
							if (key1.length() >= 2)// if(huma_id2!="")
								key1 = key1.substring(0, 2);// substring(0,1);
							ps = con.prepareStatement("select weekoff_id,comp_id,to_char(weekoff_fdate,'dd-mm-yyyy'),to_char(weekoff_tdate,'dd-mm-yyyy') from weekoff_mstr where weekoff_id='"
									+ key1 + "' order by weekoff_id");
						}
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of weekoff master");
						while (rs.next()) { // //System.out.println("inside the main while loop of the weekoff master");

							String data2 = "";
							rs2 = null;

							// System.out.println("hey the weekoff_id="+rs.getString(1));
							ps2 = con
									.prepareStatement("select weekoff_enable,weekoff_id,weekoff_day,weekoffbind_seqid from weekoffbind_mstr where weekoff_id='"
											+ rs.getString(1)
											+ "' order by weekoffbind_seqid");

							rs2 = ps2.executeQuery();
							while (rs2.next()) {
								data2 = data2 + rs2.getString(1) + "::::::";
							}
							if (data2.length() > 5)
								data2 = data2.substring(0, data2.length() - 6);

							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::" + data2
									+ "//////";
							// System.out.println("end of weekoff while loop,the data is ="+data);
						}// while
					}// else if(decide=="weekoff_mstr")
					else if (decide.equals("weekoff_id")) { // System.out.println("hey inside the if for the weekoff_id");

						ps = con.prepareStatement("select weekoff_id from weekoff_mstr where weekoff_id=(select max(weekoff_id) from weekoff_mstr)");
						rs = ps.executeQuery();
						if (rs == null) {
							// System.out.println("rs is null");
						}
						if (rs.next()) {
							do {
								int n = Integer.parseInt(rs.getString(1));
								n = n + 1;
								if (n <= 9)
									data = "0" + n;
								if ((n >= 10) && (n <= 99))
									data = "" + n;
								if (n > 99)
									throw new Exception();
							} while (rs.next());// while
						}// if
						else
							data = "01";
					}// else if(decide=="weekoff_id")
					else if (decide.equals("holiday_id")) { // System.out.println("hey inside the if for the holiday_id");
						ps = con.prepareStatement("select holiday_id from holiday_mstr where holiday_id=(select max(holiday_id) from holiday_mstr)");
						rs = ps.executeQuery();
						if (rs == null) {
							// System.out.println("rs is null");
						}
						if (rs.next()) { // System.out.println("hey inside the re.next if");
							do {// System.out.println("hey inside the while loop");
								int n = Integer.parseInt(rs.getString(1));
								n = n + 1;
								if (n <= 9)
									data = "000" + n;
								if ((n >= 10) && (n <= 99))
									data = "00" + n;
								if ((n >= 100) && (n <= 999))
									data = "0" + n;
								if ((n >= 1000) && (n <= 9999))
									data = "" + n;
								if (n > 9999)
									throw new Exception();
							} while (rs.next());// while
						}// if
						else
							data = "0001";
					}// else if(decide=="holiday_id")
					else if (decide.equals("visit_mstr"))// querying for the
															// visit master data
					{ // System.out.println("hey inside the else if for the visit_mstr");
						if (key1 == "" && key2 == "")
							ps = con.prepareStatement("select visit_seqid,(select client_id||' '||client_name from client_mstr where client_id=visit_mstr.client_id )as client_id,(select prod_id||' '||prod_name from prod_mstr where prod_id=visit_mstr.prod_id )as prod_id,to_char(visit_date,'dd-mm-yyyy'),(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=visit_mstr.visit_by )as visit_by,visit_narration,visit_potential,visit_mis,visit_customization,visit_remarks,to_char(visit_nvdate,'dd-mm-yyyy') from visit_mstr order by client_id,prod_id,to_char(visit_date,'yyyy-mm-dd')");
						else if (key1 != "" && key2 == "") {
							if (key1.length() >= 7)// if(client_id!="")
								key1 = key1.substring(0, 7);// substring(0,1);
							ps = con.prepareStatement("select visit_seqid,(select client_id||' '||client_name from client_mstr where client_id=visit_mstr.client_id )as client_id,(select prod_id||' '||prod_name from prod_mstr where prod_id=visit_mstr.prod_id )as prod_id,to_char(visit_date,'dd-mm-yyyy'),(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=visit_mstr.visit_by )as visit_by,visit_narration,visit_potential,visit_mis,visit_customization,visit_remarks,to_char(visit_nvdate,'dd-mm-yyyy') from visit_mstr where client_id='"
									+ key1
									+ "' order by client_id,prod_id,to_char(visit_date,'yyyy-mm-dd')");
						} else if (key1 == "" && key2 != "") {
							if (key2.length() >= 5)// if(prod_id!="")
								key2 = key2.substring(0, 5);// substring(0,1);
							ps = con.prepareStatement("select visit_seqid,(select client_id||' '||client_name from client_mstr where client_id=visit_mstr.client_id )as client_id,(select prod_id||' '||prod_name from prod_mstr where prod_id=visit_mstr.prod_id )as prod_id,to_char(visit_date,'dd-mm-yyyy'),(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=visit_mstr.visit_by )as visit_by,visit_narration,visit_potential,visit_mis,visit_customization,visit_remarks,to_char(visit_nvdate,'dd-mm-yyyy') from visit_mstr where prod_id='"
									+ key2
									+ "' order by client_id,prod_id,to_char(visit_date,'yyyy-mm-dd')");
						} else if (key1 != "" && key2 != "") {
							if (key1.length() >= 7)// if(client_id!="")
								key1 = key1.substring(0, 7);// substring(0,1);
							if (key2.length() >= 5)// if(prod_id!="")
								key2 = key2.substring(0, 5);// substring(0,1);
							ps = con.prepareStatement("select visit_seqid,(select client_id||' '||client_name from client_mstr where client_id=visit_mstr.client_id )as client_id,(select prod_id||' '||prod_name from prod_mstr where prod_id=visit_mstr.prod_id )as prod_id,to_char(visit_date,'dd-mm-yyyy'),(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=visit_mstr.visit_by )as visit_by,visit_narration,visit_potential,visit_mis,visit_customization,visit_remarks,to_char(visit_nvdate,'dd-mm-yyyy') from visit_mstr where client_id='"
									+ key1
									+ "' and prod_id='"
									+ key2
									+ "' order by client_id,prod_id,to_char(visit_date,'yyyy-mm-dd')");
						}
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of visit master");
						while (rs.next())
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "//////";
					}// else if(decide=="visit_mstr")
					else if (decide.equals("EOIprop_mstr"))// querying for the
															// EOIprop_mstr data
					{/*
					 * //System.out.println(
					 * "hey inside the else if for the EOIprop_mstr");//order by
					 * prop_id,substr(EOIprop_id,14,7),EOIprop_date
					 * if(!((String)
					 * session.getAttribute("userType")).equals("SUPER"))//the
					 * querying person is emp { if(key1=="") ps =
					 * con.prepareStatement(
					 * "select (select area_id||' '||area_name from area_mstr where area_id=EOIprop_mstr.area_id)as area_id,EOIprop_nature,EOIprop_bidnature,EOIprop_id,EOIprop_idd,EOIprop_name,(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=EOIprop_respersn )as EOIprop_respersn,client_mstr.client_id||' '||client_name as client_id,to_char(EOIprop_announcedate,'dd-mm-yyyy'),to_char(EOIprop_deadline,'dd-mm-yyyy'),to_char(EOIprop_trackdate,'dd-mm-yyyy'),to_char(EOIprop_goaheaddate,'dd-mm-yyyy'),to_char(EOIprop_internalsubmitdate,'dd-mm-yyyy'),(select country_id||' '||country_currency from country_mstr where country_id=EOIprop_mstr.country_id )as country_id,EOIprop_amount,to_char(EOIprop_submitdate,'dd-mm-yyyy')from EOIprop_mstr,client_mstr where client_mstr.client_id=EOIprop_mstr.client_id and EOIprop_mstr.EOIprop_respersn in ('"
					 * +session.getAttribute("user")+"') order by EOIprop_id");
					 * else//if(key1!="") {
					 * //if(key1.length()>=11)//if(EOIprop_id!="")
					 * //key1=key1.substring(0,11);//substring(0,1); ps =
					 * con.prepareStatement(
					 * "select (select area_id||' '||area_name from area_mstr where area_id=EOIprop_mstr.area_id)as area_id,EOIprop_nature,EOIprop_bidnature,EOIprop_id,EOIprop_idd,EOIprop_name,(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=EOIprop_respersn )as EOIprop_respersn,client_mstr.client_id||' '||client_name as client_id,to_char(EOIprop_announcedate,'dd-mm-yyyy'),to_char(EOIprop_deadline,'dd-mm-yyyy'),to_char(EOIprop_trackdate,'dd-mm-yyyy'),to_char(EOIprop_goaheaddate,'dd-mm-yyyy'),to_char(EOIprop_internalsubmitdate,'dd-mm-yyyy'),(select country_id||' '||country_currency from country_mstr where country_id=EOIprop_mstr.country_id )as country_id,EOIprop_amount,to_char(EOIprop_submitdate,'dd-mm-yyyy')from EOIprop_mstr,client_mstr where client_mstr.client_id=EOIprop_mstr.client_id and EOIprop_id='"
					 * +
					 * key1+"' and EOIprop_mstr.EOIprop_respersn in ('"+session.
					 * getAttribute("user")+"') order by EOIprop_id");
					 * }//substr(
					 * EOIprop_id,14,7)==EOIprop_idd==EOIprop_id.substring
					 * (13,20) }//the querying person is emp else//the querying
					 * person is super {
					 */// if(key1=="")
						if ((key2 != "") && (key2.length() >= 7))// if(client_id!="")
							key2 = key2.substring(0, 7);// substring(0,1);

						ps = con.prepareStatement("select area_id,EOIprop_nature,EOIprop_bidnature,EOIprop_id,EOIprop_idd,EOIprop_name,(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=EOIprop_respersn )as EOIprop_respersn,c.client_id||' '||client_name as client_id,to_char(EOIprop_announcedate,'dd-mm-yyyy'),to_char(EOIprop_deadline,'dd-mm-yyyy'),to_char(EOIprop_trackdate,'dd-mm-yyyy'),to_char(EOIprop_goaheaddate,'dd-mm-yyyy'),to_char(EOIprop_internalsubmitdate,'dd-mm-yyyy'),(select country_id||' '||country_currency from country_mstr where country_id=e.country_id )as country_id,EOIprop_amount,to_char(EOIprop_submitdate,'dd-mm-yyyy'),null as EOIprop_status from EOIprop_mstr e,client_mstr c where c.client_id=e.client_id and EOIprop_status is null and e.EOIprop_id=nvl('"
								+ key1
								+ "',e.EOIprop_id) and e.client_id=nvl('"
								+ key2
								+ "',e.client_id) order by e.EOIprop_id,e.client_id");

						rs = ps.executeQuery();// System.out.println("Hey going for while loop of EOIprop_mstr in getuserupdate");
						while (rs.next()) { // System.out.println("hey inside the while loop");
							String data2 = "";
							rs2 = null;

							ps2 = con
									.prepareStatement("select huma_id from EOIteam_mstr where EOIprop_idd='"
											+ rs.getString(5)
											+ "' order by EOIteam_id");
							// System.out.println("Hey going to execute the data2 query");
							rs2 = ps2.executeQuery();
							while (rs2.next())
								data2 = data2 + rs2.getString(1) + "::::::"; // System.out.println("Hey just going to finilize data2 ");
							if (data2.length() > 5)
								data2 = data2.substring(0, data2.length() - 6);// System.out.println("hey the data2="+data2);

							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "::::::"
									+ rs.getString(12) + "::::::"
									+ rs.getString(13) + "::::::"
									+ rs.getString(14) + "::::::"
									+ rs.getString(15) + "::::::"
									+ rs.getString(16) + "::::::"
									+ rs.getString(17) + "::::::" + data2
									+ "//////";
						}// while(rs.next())
					}// else if(decide=="EOIprop_mstr")
					else if (decide.equals("prop_id"))// new prop_id whan click
														// on new without
														// refreshment
					{ // System.out.println("hey inside the if for the prop_id");
						// getting area_id,huma_id,country_id to assign in their
						// fields automatically in enable() function
						int x2 = 0;
						String curbusiid = "", curpropid = "";
						ps2 = con
								.prepareStatement("select (select area_id from area_mstr where area_id=h.area_id) as area_id,huma_id||' '||huma_fname||' '||huma_lname as huma_id,(select country_id from country_mstr where country_currency=c1.country_currency and rownum=1 group by country_id) as country_id,(select to_char(sysdate,'mm-yyyy')||'/P'||nvl((select to_char(to_number(substr(prop_idd,2)+1),'FM000000') from prop_mstr where  prop_idd=(select max(prop_idd) from prop_mstr)),'000001') as prop_idd from dual) as curpropid from huma_mstr h,country_mstr c1,state_mstr s,city_mstr c where h.city_id=c.city_id and c.state_id=s.state_id and s.country_id=c1.country_id and huma_id=?");
						ps2.setString(1, (String) session.getAttribute("user"));
						rs2 = ps2.executeQuery();
						if (rs2 == null)
							System.out
									.println("result set1 is null in valid.jsp");
						if ((rs2.next()) == false)
							x2 = x2 + 1; // System.out.println("hey after the next on resultset and string="+rs1.getString(1));
						if (x2 == 0)// entered field exists in huma_mstr
						{// session.setAttribute("area_id",rs1.getString(1));
							curbusiid = rs2.getString(1);
							rs2.getString(2);
							rs2.getString(3);
							curpropid = curbusiid + "/" + rs2.getString(4);
						}
						// System.out.println("Hey the curcountryid="+curcountryid);

						data = curpropid;
					}// else if(decide=="prop_id")
					else if (decide.equals("prop_mstr"))// querying for the
														// prop_mstr data
					{
						// String key3 =
						// request.getParameter("key3").toString();
						key3 = request.getParameter("key3").toString();
						if ((key2 != "") && (key2.length() >= 7))// if(client_id!="")
							key2 = key2.substring(0, 7);// substring(0,1);
						if ((key3.length() >= 20))// if(prop_id!="")
							key3 = key3.substring(0, 20);// substring(0,1);

						ps = con.prepareStatement("select area_id,EOIprop_nature,EOIprop_bidnature,EOIprop_id,e.EOIprop_idd,EOIprop_name,(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=EOIprop_respersn )as EOIprop_respersn,c.client_id||' '||client_name as client_id,to_char(EOIprop_announcedate,'dd-mm-yyyy'),to_char(EOIprop_deadline,'dd-mm-yyyy'),to_char(EOIprop_trackdate,'dd-mm-yyyy'),to_char(EOIprop_goaheaddate,'dd-mm-yyyy'),to_char(EOIprop_internalsubmitdate,'dd-mm-yyyy'),(select country_id||' '||country_currency from country_mstr where country_id=e.country_id )as country_id,EOIprop_amount,to_char(EOIprop_submitdate,'dd-mm-yyyy'),EOIprop_status,prop_id,prop_ho,prop_brn,prop_status from prop_mstr p,EOIprop_mstr e,client_mstr c where e.EOIprop_idd=p.EOIprop_idd and c.client_id=e.client_id and p.prop_id=nvl('"
								+ key3
								+ "',p.prop_id) and e.EOIprop_id=nvl('"
								+ key1
								+ "',e.EOIprop_id) and e.client_id=nvl('"
								+ key2
								+ "',e.client_id) order by p.prop_idd,e.EOIprop_id,e.client_id");

						rs = ps.executeQuery();// System.out.println("Hey going for while loop of EOIprop_mstr in getuserupdate");
						while (rs.next()) { // System.out.println("hey inside the while loop");
							String data2 = "";
							rs2 = null;

							ps2 = con
									.prepareStatement("select huma_id from EOIteam_mstr where EOIprop_idd='"
											+ rs.getString(5)
											+ "' order by EOIteam_id");
							// System.out.println("Hey going to execute the data2 query");
							rs2 = ps2.executeQuery();
							while (rs2.next())
								data2 = data2 + rs2.getString(1) + "::::::"; // System.out.println("Hey just going to finilize data2 ");
							if (data2.length() > 5)
								data2 = data2.substring(0, data2.length() - 6);// System.out.println("hey the data2="+data2);

							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "::::::"
									+ rs.getString(12) + "::::::"
									+ rs.getString(13) + "::::::"
									+ rs.getString(14) + "::::::"
									+ rs.getString(15) + "::::::"
									+ rs.getString(16) + "::::::"
									+ rs.getString(17) + "::::::"
									+ rs.getString(18) + "::::::"
									+ rs.getString(19) + "::::::"
									+ rs.getString(20) + "::::::"
									+ rs.getString(21) + "::::::" + data2
									+ "//////";
						}// while(rs.next())
					}// else if(decide=="prop_mstr")
					else if (decide.equals("prop_id.cont_id")) { // System.out.println("hey inside the if for the prop_id.cont_id");
						if (key1.length() >= 20)// if(prop_id>=20)
							key1 = key1.substring(0, 20);// substring(0,5);
						// if(key2.length()>=20)//if(prop_id>=20)
						// key2=key2.substring(0,20);//substring(0,20);
						ps2 = con
								.prepareStatement("select e.EOIprop_id,p.prop_id,null as prod_id,(select comp_id||' '||comp_name from comp_mstr where comp_id=(select comp_id from area_mstr where area_id=e.area_id))as prop_party1,c.client_id||' '||client_name as prop_party2,(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=e.EOIprop_respersn )as prop_by,(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=(select huma_reporting from huma_mstr where huma_id=e.EOIprop_respersn))as prop_rincharge from prop_mstr p,EOIprop_mstr e,client_mstr c where c.client_id=e.client_id and e.EOIprop_idd=p.EOIprop_idd and p.prop_status='W' and p.prop_idd=nvl(substr('"
										+ key1
										+ "',14,7),p.prop_idd) and e.eoiprop_id=nvl('"
										+ key2 + "',e.eoiprop_id)");
						rs2 = ps2.executeQuery(); // System.out.println("hey aftert execution of the query and going to make the data string");
						while (rs2.next())
							data = data + rs2.getString(1) + "::::::"
									+ rs2.getString(2) + "::::::"
									+ rs2.getString(3) + "::::::"
									+ rs2.getString(4) + "::::::"
									+ rs2.getString(5) + "::::::"
									+ rs2.getString(6) + "::::::"
									+ rs2.getString(7);
						// System.out.println("the assignment of data string happened");
					}// if(decide=="prop_id.cont_id")
					else if (decide.equals("cont_id"))// new cont_id whan click
														// on new without
														// refreshment
					{ // System.out.println("hey inside the if for the cont_id");
						// getting area_id,huma_id,country_id to assign in their
						// fields automatically in enable() function
						int x2 = 0;
						String curbusiid = "", curcontid = "";
						ps2 = con
								.prepareStatement("select (select area_id from area_mstr where area_id=h.area_id) as area_id,huma_id||' '||huma_fname||' '||huma_lname as huma_id,(select country_id from country_mstr where country_currency=c1.country_currency and rownum=1 group by country_id) as country_id,(select to_char(sysdate,'mm-yyyy')||'/C'||nvl((select to_char(to_number(substr(cont_idd,2)+1),'FM000000') from cont_mstr where cont_idd=(select max(cont_idd) from cont_mstr)),'000001') as cont_idd from dual) as curcontid from huma_mstr h,country_mstr c1,state_mstr s,city_mstr c where h.city_id=c.city_id and c.state_id=s.state_id and s.country_id=c1.country_id and huma_id=?");
						ps2.setString(1, (String) session.getAttribute("user"));
						rs2 = ps2.executeQuery();
						if (rs2 == null)
							System.out
									.println("result set1 is null in valid.jsp");
						if ((rs2.next()) == false)
							x2 = x2 + 1; // System.out.println("hey after the next on resultset and string="+rs1.getString(1));
						if (x2 == 0)// entered field exists in huma_mstr
						{// session.setAttribute("area_id",rs1.getString(1));
							curbusiid = rs2.getString(1);
							rs2.getString(2);
							rs2.getString(3);
							curcontid = curbusiid + "/" + rs2.getString(4);
						}
						// System.out.println("Hey the curcountryid="+curcountryid);

						data = curcontid;
					}// else if(decide=="cont_id")
					else if (decide.equals("cont_mstr"))// querying for the
														// cont_mstr data
					{ // System.out.println("hey inside the else if for the cont_mstr");//order
						// by prop_id,substr(cont_id,14,7),cont_date
						/*
						 * String key3 =
						 * request.getParameter("key3").toString(); String key4
						 * = request.getParameter("key4").toString();
						 */
						key3 = request.getParameter("key3").toString();
						key4 = request.getParameter("key4").toString();
						// key1-EOIprop_id
						if (key2.length() >= 20)// if(prop_id!="")
							key2 = key2.substring(0, 20);// substring(0,20);
						if (key3.length() >= 20)// if(cont_id!="")
							key3 = key3.substring(0, 20);// substring(0,20);
						if (key4.length() >= 7)// if(client_id!="")
							key4 = key4.substring(0, 7);// substring(0,7);

						ps = con.prepareStatement("select p.prop_id as prop_id,c2.cont_id as cont_id,c2.cont_title,e.EOIprop_id,(select comp_id||' '||comp_name from comp_mstr where comp_id=(select comp_id from area_mstr where area_id=e.area_id))as prop_party1,c.client_id||' '||client_name as prop_party2,(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=e.EOIprop_respersn )as prop_by,(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=(select huma_reporting from huma_mstr where huma_id=e.EOIprop_respersn))as prop_rincharge,to_char(cont_date,'dd-mm-yyyy'),to_char(cont_sdate,'dd-mm-yyyy'),to_char(cont_edate,'dd-mm-yyyy'),cont_amount,c2.country_id,(select taxterm_id||' '||taxterm_desc from taxterm_mstr where taxterm_id=c2.taxterm_id) as taxterm_id,cont_brn,cont_extendDate,cont_reportFrequency,cont_respersn,(select state_name from state_mstr where state_id=c2.state_id) as state_id,cont_district,cont_block,cont_opsRegion from cont_mstr c2,prop_mstr p,EOIprop_mstr e,client_mstr c where c.client_id=e.client_id and e.EOIprop_idd=p.EOIprop_idd and c2.prop_idd=p.prop_idd and e.EOIprop_id=nvl('"
								+ key1
								+ "',e.EOIprop_id) and p.prop_id=nvl('"
								+ key2
								+ "',p.prop_id) and c2.cont_id=nvl('"
								+ key3
								+ "',c2.cont_id) and c.client_id=nvl('"
								+ key4
								+ "',c.client_id) and e.EOIprop_respersn=nvl('"
								+ curhuma_id
								+ "',e.EOIprop_respersn) order by c2.cont_idd");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of prop_mstr");
						while (rs.next()) {
							String data2 = "";
							rs2 = null;

							ps2 = con
									.prepareStatement("select schedule_deliverables,to_char(schedule_period,'dd-mm-yyyy'),schedule_percentage,schedule_amount,to_char(schedule_deliverdate,'dd-mm-yyyy'),schedule_deliverables2,to_char(schedule_periodStartdate,'dd-mm-yyyy'),to_char(schedule_deliverStartdate,'dd-mm-yyyy'),schedule_status,schedule_remarks,schedule_uploadFile,schedule_donorFeedback,schedule_communityFeedback,schedule_roadBlock,to_char(schedule_trackDate1,'dd-mm-yyyy'),schedule_trackPercentage1,schedule_trackRemarks1,to_char(schedule_trackDate2,'dd-mm-yyyy'),schedule_trackPercentage2,schedule_trackRemarks2,to_char(schedule_trackDate3,'dd-mm-yyyy'),schedule_trackPercentage3,schedule_trackRemarks3 from schedule_mstr where cont_idd='"
											+ rs.getString(2).substring(13, 20)
											+ "' order by to_number(schedule_id)");

							rs2 = ps2.executeQuery();
							while (rs2.next())
								data2 = data2 + rs2.getString(1) + "::::::"
										+ rs2.getString(2) + "::::::"
										+ rs2.getString(3) + "::::::"
										+ rs2.getString(4) + "::::::"
										+ rs2.getString(5) + "::::::"
										+ rs2.getString(6) + "::::::"
										+ rs2.getString(7) + "::::::"
										+ rs2.getString(8) + "::::::"
										+ rs2.getString(9) + "::::::"
										+ rs2.getString(10) + "::::::"
										+ rs2.getString(11) + "::::::"
										+ rs2.getString(12) + "::::::"
										+ rs2.getString(13) + "::::::"
										+ rs2.getString(14) + "::::::"
										+ rs2.getString(15) + "::::::"
										+ rs2.getString(16) + "::::::"
										+ rs2.getString(17) + "::::::"
										+ rs2.getString(18) + "::::::"
										+ rs2.getString(19) + "::::::"
										+ rs2.getString(20) + "::::::"
										+ rs2.getString(21) + "::::::"
										+ rs2.getString(22) + "::::::"
										+ rs2.getString(23) + "::::::";
							if (data2.length() > 23)
								data2 = data2.substring(0, data2.length() - 6);// System.out.println("hey the data2="+data2);

							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "::::::"
									+ rs.getString(12) + "::::::"
									+ rs.getString(13) + "::::::"
									+ rs.getString(14) + "::::::"
									+ rs.getString(15) + "::::::"
									+ rs.getString(16) + "::::::"
									+ rs.getString(17) + "::::::"
									+ rs.getString(18) + "::::::"
									+ rs.getString(19) + "::::::"
									+ rs.getString(20) + "::::::"
									+ rs.getString(21) + "::::::"
									+ rs.getString(22) + "::::::" + data2
									+ "//////";
						}// while(rs.next())
					}// else if(decide=="cont_mstr")
					else if (decide.equals("taxterm_mstr"))// querying for the
															// taxterm_mstr data
					{ // System.out.println("hey inside the else if for the taxterm_mstr");//order
						// by prop_id,substr(taxterm_id,14,7),taxterm_date
						if (key1 == "")
							ps = con.prepareStatement("select taxterm_id,taxterm_desc,(select country_id||' '||country_name from country_mstr where country_id=taxterm_mstr.country_id) as country_id,taxterm_contdate from taxterm_mstr order by taxterm_id");
						else// if(key1!="")
						{
							if (key1.length() >= 3)// if(taxterm_id!="")
								key1 = key1.substring(0, 3);// substring(0,1);
							ps = con.prepareStatement("select taxterm_id,taxterm_desc,(select country_id||' '||country_name from country_mstr where country_id=taxterm_mstr.country_id) as country_id,taxterm_contdate from taxterm_mstr where taxterm_id='"
									+ key1 + "' order by taxterm_id");
						}// substr(taxterm_id,14,7)==taxterm_idd==taxterm_id.substring(13,20)
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of prop_mstr");
						while (rs.next()) {
							String data2 = "";
							rs2 = null;

							ps2 = con
									.prepareStatement("select taxtermrev_percentage,to_char(taxtermrev_effectdate,'dd-mm-yyyy') from taxtermrev_mstr where taxterm_id='"
											+ rs.getString(1)
											+ "' order by taxtermrev_id");

							rs2 = ps2.executeQuery();
							while (rs2.next())
								data2 = data2 + rs2.getString(1) + "::::::"
										+ rs2.getString(2) + "::::::";
							if (data2.length() > 5)
								data2 = data2.substring(0, data2.length() - 6);// System.out.println("hey the data2="+data2);

							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::" + data2
									+ "//////";
						}// while(rs.next())
					}// else if(decide=="taxterm_mstr")
					else if (decide.equals("taxterm_id")) { // System.out.println("hey inside the if for the taxterm_id");
						ps = con.prepareStatement("select taxterm_id from taxterm_mstr where taxterm_id=(select max(taxterm_id) from taxterm_mstr)");
						rs = ps.executeQuery();
						if (rs == null) {
							System.out
									.println("rs is null in getuserupate and in taxterm_id");
						}
						if (rs.next()) { // System.out.println("hey inside the if part of the taxterm_id and max(taxterm_id)="+rs.getString(1));
							// do
							// {
							// String s=(rs.getString(1)).substring(0,1);
							int n = Integer.parseInt((rs.getString(1))
									.substring(1));
							n = n + 1;
							if (n <= 9)
								data = "00" + n;
							if ((n >= 10) && (n <= 99))
								data = "0" + n;
							if ((n >= 100) && (n <= 999))
								data = Integer.toString(n);
							if (n > 999)
								throw new Exception();
							// }while(rs.next());//while
						}// if
						else {// System.out.println("hey inside the else paret of the taxterm_id");
							data = "001";
						}
					}// else if(decide="taxterm_id")
					else if (decide.equals("bank_mstr")) { // System.out.println("hey inside the if for the bank_mstr");
						if (key1 == "")
							ps = con.prepareStatement("select (select comp_id||' '||comp_name from comp_mstr where comp_id=bank_mstr.comp_id) as comp_id,bank_id,bank_bnfryname,bank_accno,bank_name,bank_branchname,bank_branchaddr,bank_ifsccode,bank_swiftcode,bank_panno,bank_staxno from bank_mstr order by bank_id");
						else {
							if (key1.length() >= 3)// if(bank_id2!="")
								key1 = key1.substring(0, 3);// substring(0,3);

							ps = con.prepareStatement("select (select comp_id||' '||comp_name from comp_mstr where comp_id=bank_mstr.comp_id) as comp_id,bank_id,bank_bnfryname,bank_accno,bank_name,bank_branchname,bank_branchaddr,bank_ifsccode,bank_swiftcode,bank_panno,bank_staxno from bank_mstr where bank_id='"
									+ key1 + "' order by bank_id");
						}
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "//////";
					}// if(decide=="bank_mstr")
					else if (decide.equals("bank_id")) { // System.out.println("hey inside the if for the bank_id");
						ps = con.prepareStatement("select bank_id from bank_mstr where bank_id=(select max(bank_id) from bank_mstr)");
						rs = ps.executeQuery();
						if (rs == null) {
							System.out
									.println("rs is null in getuserupate and in bank_id");
						}
						if (rs.next()) { // System.out.println("hey inside the if part of the bank_id and max(bank_id)="+rs.getString(1));
							// do
							// {
							// String s=(rs.getString(1)).substring(0,1);
							int n = Integer.parseInt((rs.getString(1))
									.substring(1));
							n = n + 1;
							if (n <= 9)
								data = "00" + n;
							if ((n >= 10) && (n <= 99))
								data = "0" + n;
							if ((n >= 100) && (n <= 999))
								data = Integer.toString(n);
							if (n > 999)
								throw new Exception();
							// }while(rs.next());//while
						}// if
						else {// System.out.println("hey inside the else paret of the bank_id");
							data = "001";
						}
					}// else if(decide="bank_id")
					else if (decide.equals("grade_mstr")) { // System.out.println("hey inside the if for the grade_mstr");
						if (key1 == "")
							ps = con.prepareStatement("select grade_id,grade_name,grade_abbreviation,GRADE_LEVEL from grade_mstr order by grade_id");
						else {
							if (key1.length() >= 3)// if(huma_id2!="")
								// key1 = key1.substring(0, 3);//
								// substring(0,1);
								key1 = key1
										.substring(key1.lastIndexOf("-") + 1);

							ps = con.prepareStatement("select grade_id,grade_name,grade_abbreviation,GRADE_LEVEL from grade_mstr where grade_id='"
									+ key1 + "' order by grade_id");
						}
						rs = ps.executeQuery();// System.out.println("Hey going for while loop");
						while (rs.next())
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "//////";
					}// if(decide=="grade_mstr")
					else if (decide.equals("grade_id")) { // System.out.println("hey inside the if for the grade_id");

						ps = con.prepareStatement("select grade_id from grade_mstr where grade_id=(select max(grade_id) from grade_mstr)");
						rs = ps.executeQuery();
						if (rs == null) {
							System.out
									.println("rs is null in getuserupate and in grade_id");
						}
						if (rs.next()) {
							do {
								String s = (rs.getString(1)).substring(0, 1);
								int n = Integer.parseInt((rs.getString(1))
										.substring(1));
								n = n + 1;
								if (n <= 9)
									data = s + "0" + n;
								if ((n >= 10) && (n <= 99))
									data = s + n;
								if (n > 99)
									throw new Exception();
							} while (rs.next());// while
						}// if
						else
							data = "G01";
					}// else if(decide="grade_id")
					else if (decide.equals("cont_id.invo_id")) { // System.out.println("hey inside the if for the ");
						if (key1.length() >= 20)// if(cont_id>=20)
							key1 = key1.substring(0, 20);// substring(0,5);
						if (key2.length() >= 25)// if(invo_id>=25)
							key2 = key2.substring(0, 25);// substring(0,25);

						ps2 = con
								.prepareStatement("select substr(p.prop_id,1,4)||'/'||(select to_char(sysdate,'mm-yyyy')||'/INV'||nvl((select to_char(to_number(substr(invo_idd,4)+1),'FM000000000') from invo_mstr where invo_idd=(select max(invo_idd) from invo_mstr)),'000000001') as invo_idd from dual) as invo_id,(select comp_id||' '||comp_name from comp_mstr where comp_id=(select comp_id from area_mstr where area_id=e.area_id))as prop_party1,c.client_id||' '||client_name as prop_party2,(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=e.EOIprop_respersn )as prop_by,to_char(cont_date,'dd-mm-yyyy') as cont_date,cont_amount,cont_grossdueamt(c2.cont_idd,to_char(c2.cont_date,'dd-mm-yyyy')) as cont_dueamount,(select taxterm_id||'  '||taxterm_desc from taxterm_mstr where taxterm_id=c2.taxterm_id) as taxterm_id from cont_mstr c2,prop_mstr p,EOIprop_mstr e,client_mstr c where c.client_id=e.client_id and e.EOIprop_idd=p.EOIprop_idd and c2.prop_idd=p.prop_idd and c2.cont_id='"
										+ key1 + "'");
						rs2 = ps2.executeQuery();
						while (rs2.next())// means a proper cont_id have a
											// prop_id and that have a prod_id
						{ // below 11 lines of code to retrieve the cont_id
							// corresponding all schedule_ids
							String data3 = "";
							rs3 = null;
							ps3 = con
									.prepareStatement("select schedule_id||' - '||schedule_percentage||'%' from schedule_mstr where cont_idd='"
											+ key1.substring(13, 20)
											+ "' order by schedule_id");

							rs3 = ps3.executeQuery();
							while (rs3.next())
								data3 = data3 + rs3.getString(1) + "::::::";
							if (data3.length() > 5)
								data3 = data3.substring(0, data3.length() - 6);// System.out.println("hey the data3="+data3);

							data = data + rs2.getString(1) + "::::::"
									+ rs2.getString(2) + "::::::"
									+ rs2.getString(3) + "::::::"
									+ rs2.getString(4) + "::::::"
									+ rs2.getString(5) + "::::::"
									+ rs2.getString(6) + "::::::"
									+ rs2.getString(7) + "::::::"
									+ rs2.getString(8) + "::::::" + data3
									+ "::::::";
						}// while(rs2.next())
					}// if(decide=="cont_id.invo_id")
					else if (decide.equals("invo_mstr"))// querying for the
														// invo_mstr data
					{ // System.out.println("hey inside the else if for the invo_mstr");//order
						// by cont_id,substr(invo_id,16,7),invo_date
						// String key3 =
						// request.getParameter("key3").toString();
						key3 = request.getParameter("key3").toString();
						if (key1.length() >= 20)// if(cont_id!="")
							key1 = key1.substring(0, 20);// substring(0,20);
						if (key2.length() >= 25)// if(invo_id!="")
							key2 = key2.substring(0, 25);// substring(0,1);
						if (key3.length() >= 7)// if(client_id!="")
							key3 = key3.substring(0, 7);// substring(0,7);

						ps = con.prepareStatement("select c2.cont_id||' '||client_name as cont_id,i.invo_id||' '||client_name as invo_id,(select comp_id||' '||comp_name from comp_mstr where comp_id=(select comp_id from area_mstr where area_id=e.area_id))as prop_party1,c.client_id||' '||client_name as prop_party2,(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=e.EOIprop_respersn )as prop_by,to_char(cont_date,'dd-mm-yyyy') as cont_date,cont_amount,cont_grossdueamt(c2.cont_idd,to_char(c2.cont_date,'dd-mm-yyyy')) as cont_dueamount,to_char(invo_date,'dd-mm-yyyy') as invo_date,(select schedule_id||' - '||schedule_percentage||'%' from schedule_mstr where cont_idd=i.cont_idd and schedule_id=i.schedule_id) as schedule_id,(select taxterm_id||'  '||taxterm_desc from taxterm_mstr where taxterm_id=c2.taxterm_id) as taxterm_id,(select bank_id||' '||bank_name from bank_mstr where bank_id=i.bank_id) as bank_id,invo_remarks from invo_mstr i, cont_mstr c2,prop_mstr p,EOIprop_mstr e,client_mstr c where i.cont_idd=c2.cont_idd and c.client_id=e.client_id and e.EOIprop_idd=p.EOIprop_idd and c2.prop_idd=p.prop_idd and c2.cont_id=nvl('"
								+ key1
								+ "',c2.cont_id) and i.invo_id=nvl('"
								+ key2
								+ "',i.invo_id) and c.client_id=nvl('"
								+ key3
								+ "',c.client_id) and e.EOIprop_respersn=nvl('"
								+ curhuma_id
								+ "',e.EOIprop_respersn) order by i.invo_idd");// substr(invo_id,16,7)==invo_idd==invo_id.substring(13,25)

						rs = ps.executeQuery();// System.out.println("Hey going for while loop of cont_mstr");
						while (rs.next()) {
							String data3 = "";
							rs3 = null;
							ps3 = con
									.prepareStatement("select schedule_id||' - '||schedule_percentage||'%' from schedule_mstr where cont_idd='"
											+ rs.getString(1).substring(13, 20)
											+ "' order by schedule_id");

							rs3 = ps3.executeQuery();
							while (rs3.next())
								data3 = data3 + rs3.getString(1) + "::::::";
							if (data3.length() > 5)
								data3 = data3.substring(0, data3.length() - 6);// System.out.println("hey the data3="+data3);

							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "::::::"
									+ rs.getString(12) + "::::::"
									+ rs.getString(13) + "::::::" + data3
									+ "//////";
						}// while(rs.next())
					}// else if(decide=="invo_mstr")
					else if (decide.equals("paym_id")) { // System.out.println("hey inside the if for the paym_id");
						ps = con.prepareStatement("select paym_id from paym_mstr where paym_id=(select max(paym_id) from paym_mstr)");
						rs = ps.executeQuery();
						if (rs == null) {
							System.out
									.println("rs is null in getuserupate and in paym_id");
						}
						if (rs.next()) { // System.out.println("hey inside the if part of the paym_id and max(paym_id)="+rs.getString(1));
							// do
							// {
							// String s=(rs.getString(1)).substring(0,1);
							int n = Integer.parseInt((rs.getString(1)));// .substring(1));
							n = n + 1; // System.out.println("hey just before going to change increment the id");
							data = Integer.toString(n);// System.out.println("the data incremet and the data assingment over="+data);
							if (n > 999999999)
								throw new Exception();
							// }while(rs.next());//while
						}// if
						else
							data = Integer.toString(1);
					}// else if(decide="paym_id")
					else if (decide.equals("cont_id.paym_id")) { // System.out.println("hey inside the if for the d.invo_id");
																	// //key2 is
																	// paym_id
																	// in the
																	// paym
																	// forms
						if (key1.length() >= 20)// if(cont_id>=20)
							key1 = key1.substring(0, 20);// substring(0,5);
						// if(key2.length()>=25)//if(invo_id>=25)
						// key2=key2.substring(0,25);//substring(0,25);
						// start id code(business
						// line)----------------------------------------------------------------------------------------
						ps2 = con
								.prepareStatement("select null as prod_id,(select comp_id||' '||comp_name from comp_mstr where comp_id=(select comp_id from area_mstr where area_id=e.area_id))as prop_party1,c.client_id||' '||client_name as prop_party2,(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=e.EOIprop_respersn )as prop_by,to_char(cont_date,'dd-mm-yyyy') as cont_date,cont_amount,cont_grossdueamt(c2.cont_idd,to_char(c2.cont_date,'dd-mm-yyyy')) as cont_dueamount,(select taxterm_id||'  '||taxterm_desc from taxterm_mstr where taxterm_id=c2.taxterm_id) as taxterm_id from cont_mstr c2,prop_mstr p,EOIprop_mstr e,client_mstr c where c.client_id=e.client_id and e.EOIprop_idd=p.EOIprop_idd and c2.prop_idd=p.prop_idd and c2.cont_id='"
										+ key1 + "'");
						rs2 = ps2.executeQuery();
						while (rs2.next())// means a proper cont_id have a
											// prop_id and that have a prod_id
						{
							// below 11 lines of code to retrieve the cont_id
							// corresponding all invo_ids
							String data3 = "";
							rs3 = null;
							ps3 = con
									.prepareStatement("select cont_id,invo_id,((select taxtermrev_percentage(taxterm_id,to_char(cont_date,'dd-mm-yyyy'),to_char(invo_date,'dd-mm-yyyy')) from dual)*(select schedule_amount from schedule_mstr where cont_idd=cont_mstr.cont_idd and schedule_id=invo_mstr.schedule_id)/100)+(select schedule_amount from schedule_mstr where cont_idd=cont_mstr.cont_idd and schedule_id=invo_mstr.schedule_id) as invo_amount,(select paym_enable from paymbind_mstr where cont_idd=cont_mstr.cont_idd and invo_idd=invo_mstr.invo_idd and paym_id='"
											+ key2
											+ "') as paym_enable from cont_mstr,invo_mstr where cont_mstr.cont_idd=invo_mstr.cont_idd and cont_mstr.cont_idd='"
											+ key1.substring(13, 20)
											+ "' order by invo_idd");

							rs3 = ps3.executeQuery();
							while (rs3.next())
								data3 = data3 + rs3.getString(1) + "::::::"
										+ rs3.getString(2) + "::::::"
										+ rs3.getString(3) + "::::::"
										+ rs3.getString(4) + "::::::";
							if (data3.length() > 23)// 6*4
								data3 = data3.substring(0, data3.length() - 6);// System.out.println("hey the data3="+data3);

							data = data + "::::::" + rs2.getString(2)
									+ "::::::" + rs2.getString(3) + "::::::"
									+ rs2.getString(4) + "::::::"
									+ rs2.getString(5) + "::::::"
									+ rs2.getString(6) + "::::::"
									+ rs2.getString(7) + "::::::"
									+ rs2.getString(8) + "::::::" + data3
									+ "::::::";
						}// while(rs2.next())
					}// if(decide=="cont_id.paym_id")
					else if (decide.equals("paym_mstr"))// querying for the
														// paym_mstr data
					{ // System.out.println("hey inside the else if for the paym_mstr in getuserupdate");//order
						// by cont_idd
						if (key1.length() >= 20)// if(cont_id!="")
							key1 = key1.substring(0, 20);// substring(0,1
						if (key2.length() >= 7)// if(client_id!="")
							key2 = key2.substring(0, 7);// substring(0,7);
						ps = con.prepareStatement("select cont_id||' '||client_name as cont_id,(select comp_id||' '||comp_name from comp_mstr where comp_id=(select comp_id from area_mstr where area_id=e.area_id))as prop_party1,c.client_id||' '||client_name as prop_party2,(select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=e.EOIprop_respersn )as prop_by,to_char(cont_date,'dd-mm-yyyy') as cont_date,cont_amount,cont_grossdueamt(c2.cont_idd,to_char(c2.cont_date,'dd-mm-yyyy')) as cont_dueamount,to_char(paym_rdate,'dd-mm-yyyy') as paym_rdate,paym_type,to_char(paym_ddate,'dd-mm-yyyy') as paym_ddate,paym_bank,paym_remarks,paym_amount,taxterm_deduction,(select taxterm_id||'  '||taxterm_desc from taxterm_mstr where taxterm_id=p2.taxterm_id) as taxterm_id,paym_cddno,paym_bounce,paym_reason,paym_status,to_char(paym_cldate,'dd-mm-yyyy') as paym_cldate,p2.paym_id,p2.cont_idd from paym_mstr p2,cont_mstr c2,prop_mstr p,EOIprop_mstr e,client_mstr c where c.client_id=e.client_id and e.EOIprop_idd=p.EOIprop_idd and c2.prop_idd=p.prop_idd and c2.cont_idd=p2.cont_idd and c2.cont_id=nvl('"
								+ key1
								+ "',c2.cont_id) and c.client_id=nvl('"
								+ key2
								+ "',c.client_id) and e.EOIprop_respersn=nvl('"
								+ curhuma_id
								+ "',e.EOIprop_respersn) order by p2.cont_idd,p2.paym_rdate");

						rs = ps.executeQuery();// System.out.println("Hey going for while loop of cont_mstr");
						while (rs.next()) {
							String data3 = "";
							rs3 = null;
							ps3 = con
									.prepareStatement("select cont_id,invo_id,((select taxtermrev_percentage(taxterm_id,to_char(cont_date,'dd-mm-yyyy'),to_char(invo_date,'dd-mm-yyyy')) from dual)*(select schedule_amount from schedule_mstr where cont_idd=cont_mstr.cont_idd and schedule_id=invo_mstr.schedule_id)/100)+(select schedule_amount from schedule_mstr where cont_idd=cont_mstr.cont_idd and schedule_id=invo_mstr.schedule_id) as invo_amount,(select paym_enable from paymbind_mstr where cont_idd=cont_mstr.cont_idd and invo_idd=invo_mstr.invo_idd and paym_id='"
											+ rs.getString("paym_id")
											+ "') as paym_enable from cont_mstr,invo_mstr where cont_mstr.cont_idd=invo_mstr.cont_idd and cont_mstr.cont_idd='"
											+ rs.getString("cont_idd")
											+ "' order by invo_idd"); // System.out.println("hey the ps3 = con.prepareStatement("+query3);

							rs3 = ps3.executeQuery();
							while (rs3.next())
								data3 = data3 + rs3.getString(1) + "::::::"
										+ rs3.getString(2) + "::::::"
										+ rs3.getString(3) + "::::::"
										+ rs3.getString(4) + "::::::";
							if (data3.length() > 23)// 6*4
								data3 = data3.substring(0, data3.length() - 6);

							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "::::::"
									+ rs.getString(12) + "::::::"
									+ rs.getString(13) + "::::::"
									+ rs.getString(14) + "::::::"
									+ rs.getString(15) + "::::::"
									+ rs.getString(16) + "::::::"
									+ rs.getString(17) + "::::::"
									+ rs.getString(18) + "::::::"
									+ rs.getString(19) + "::::::"
									+ rs.getString(20) + "::::::"
									+ rs.getString(21) + "::::::" + data3
									+ "//////";
						}// while(rs.next())
					}// else if(decide=="paym_mstr")
					else if (decide.equals("invo_scheduleper")) { // System.out.println("hey inside the if for invo_scheduleper & key2="+key2);
						if (key1.length() >= 20)// if(cont_id!="")//substr(cont_id,14,7)==cont_idd==cont_id.substring(13,20)
							key1 = key1.substring(0, 20);// substring(0,1);
						if (key2.length() >= 2)// if(schedule_id>=3)
							key2 = key2.substring(0, 2);// substring(0,2).trim();//removes
														// the space at end if
														// id is of 1 digit
						ps = con.prepareStatement("select schedule_percentage from schedule_mstr where cont_idd=? and schedule_id=?");
						ps.setString(1, key1.substring(13, 20));// retrieving
																// cont_idd from
																// cont_id
						ps.setString(2, key2);
						rs = ps.executeQuery();
						while (rs.next())
							data = data + rs.getString(1) + "::::::";
					}// if(decide=="invo_scheduleper")
					else if (decide.equals("invo_taxper")) { // System.out.println("hey inside the if for the invo_taxper & key2="+key2);
						if (key2.length() >= 3)// if(area_id>=3)
							key2 = key2.substring(0, 3);// substring(0,3);
						String cont_date = request.getParameter("cont_date")
								.toString(); // System.out.println("cont_date="+cont_date);
						String invo_date = request.getParameter("invo_date")
								.toString(); // System.out.println("invo_date="+invo_date);
						ps = con.prepareStatement("select taxtermrev_percentage('"
								+ key2
								+ "','"
								+ cont_date
								+ "','"
								+ invo_date
								+ "') from dual");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key2="+key2);
						while (rs.next())
							data = data + rs.getString(1) + "::::::";
					}// if(decide=="invo_taxper")
					else if (decide.equals("log_changeHead"))// log_changeHead
																// block for the
																// log master
																// data
					{ // System.out.println("hey inside the else if for the log_mstr");

						ps = con.prepareStatement("select head_client,head_service,head_name from head_mstr where head_id='"
								+ key2 + "' order by head_seqid");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of cont_mstr");
						while (rs.next()) {
							data = rs.getString(1) + "::::::" + rs.getString(2)
									+ "::::::" + rs.getString(3);
						}// while(rs.next())
					}// else if(decide=="log_changeHead")
					else if (decide.equals("log_mstr"))// querying for the log
														// master data
					{ // System.out.println("hey inside the else if for the log_mstr");
						if (key1.length() >= 4)// if(huma_id!="")
							key1 = key1.substring(0, 4);// substring(0,4);
						ps = con.prepareStatement("select (select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=l.huma_id )as huma_id,to_char(l.log_date,'dd-mm-yyyy') as log_date,l.log_head,substr(l.client_nameid,1,7) as client_id,substr(l.bsflunit_ucode,1,3) as bsflunit_ucode,l.log_services,l.log_controleno,l.log_time,l.log_place,l.log_billable,l.log_narration from log_mstr l,huma_mstr h where l.huma_id=h.huma_id and l.huma_id=nvl('"
								+ key1
								+ "',l.huma_id) and l.log_date=nvl(to_date('"
								+ key2
								+ "','dd-mm-yyyy'),l.log_date) and l.huma_id=nvl('"
								+ curhuma_id
								+ "',l.huma_id) order by h.huma_id,l.log_date,l.log_seqid");

						rs = ps.executeQuery();// System.out.println("Hey going for while loop of cont_mstr");
						while (rs.next()) {
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "//////";
						}// while(rs.next())
					}// else if(decide=="log_mstr")
					else if (decide.equals("plan_table"))// querying for the
															// plan master month
															// evaluating data
					{ // System.out.println("hey inside the else if for the plan_table");
						String huma_id = request.getParameter("huma_id")
								.toString();
						String weekoff_id = "";// request.getParameter("weekoff_id").toString();
						String dates[];
						if (huma_id.length() >= 4)// if(huma_id!="")
							huma_id = huma_id.substring(0, 4);// substring(0,1);
						/*
						 * if(weekoff_id.length()>=2)//if(huma_id!="")
						 * weekoff_id
						 * =weekoff_id.substring(0,2);//substring(0,1);
						 */

						if ((!((String) session.getAttribute("userType"))
								.equals("SUPER"))
								&& (!(session.getAttribute("user"))
										.equals(huma_id.substring(0, 4))))// if
																			// to
																			// check
																			// the
																			// emplouyee
																			// accessing
																			// other
																			// employee
																			// plan
																			// details//stop
																			// execution
																			// here
																			// if
																			// true
							data = "NOSorry you connot enter others plan";
						else {// else to check the emplouyee proccessing no
								// other employee plan details so
								// continue//'"+huma_id+"'

							// =========Newly added code to remove weekoff_id
							// feils in plan.jsp============================
							dates = new String[2];
							dates[0] = key1;
							dates[1] = key2; // System.out.println("dates.length="+dates.length);
							int x1 = 0;// to checke the all 2 dates entered are
										// whether having/refering any
										// weekoff_id/holiday year or not
							ps1 = con
									.prepareStatement("select weekoff_id from weekoff_mstr w1 where to_date(?,'dd-mm-yyyy') between (select min(weekoff_fdate) from weekoff_mstr where weekoff_id=w1.weekoff_id) and (select max(weekoff_tdate) from weekoff_mstr where weekoff_id=w1.weekoff_id)");// query
																																																																									// to
																																																																									// checke
																																																																									// the
																																																																									// all
																																																																									// 4
																																																																									// dates
																																																																									// entered
																																																																									// are
																																																																									// whether
																																																																									// having/refering
																																																																									// any
																																																																									// weekoff_id/holiday
																																																																									// year
																																																																									// or
																																																																									// not
							for (int i = 0; i < dates.length; i++) { // System.out.println("het inside the for loop and the dates[i]="+dates[i]);
								ps1.setString(1, dates[i]);
								rs1 = ps1.executeQuery();
								if (rs1 == null)
									System.out
											.println("result set1 is null in geruserupdate(plan_table)");
								if ((rs1.next()) == false)
									x1 = x1 + 1;
							}// for(int i=0; i<2; i++)
								// System.out.println("hey outside the for loop and the x1="+x1);
							if (x1 != 0) {// data="NOYou conn't select a date that does not comes under any of holiday year period");
								out.println("NOYou conn't select a date that does not comes under any of holiday year period");
								return;
								// System.exit(0);
								// break;//weekoff master existed for all the
								// dates selected
								// above line: instead of using 'else' for whole
								// remaining inner block break will take
								// controle to out of inner block
							}
							weekoff_id = rs1.getString(1); // System.out.println("Hey required weekoff_id for plan's fdate & tdate="+weekoff_id);
							// ===================================================================================================
							int x = 0;// going to check for the availability of
										// entered weekoff_id in holiday_mstr.
							ps = con.prepareStatement("select weekoff_id from holiday_mstr where weekoff_id='"
									+ weekoff_id + "'");
							// ps =
							// con.prepareStatement("select weekoff_id from holiday_mstr where weekoff_id='"+weekoff_id+"'");

							rs = ps.executeQuery();
							if (rs == null)
								System.out
										.println("result set is null in the plan_table in getuserupdate");
							if ((rs.next()) == false)
								x = x + 1;
							// System.out.println("Hey the x value="+x);
							if (x == 0) // means the specified weekoff_id is
										// available in the holiday_mstr
							{
								ps2 = con
										.prepareStatement("select distinct huma_id from plan_mstr,table(WorkingDays(huma_id,'"
												+ key1
												+ "','"
												+ key2
												+ "')) where huma_id='"
												+ huma_id
												+ "' and plan_date=to_date(dates)");// select
																					// distinct
																					// huma_id
																					// from
																					// plan_mstr,table(WorkingDays(huma_id,'01-04-2012','15-04-2012'))
																					// where
																					// huma_id='0001'
																					// and
																					// plan_date=to_date(dates)
								// "select huma_id from plan_mstr p1 where to_date('"+key1+"','dd-mm-yyyy') between (select min(plan_fdate) from plan_mstr where huma_id='"+huma_id+"') and (select max(plan_tdate) from plan_mstr where huma_id='"+huma_id+"') or to_date('"+key2+"','dd-mm-yyyy') between (select min(plan_fdate) from plan_mstr where huma_id='"+huma_id+"') and (select max(plan_tdate) from plan_mstr where huma_id='"+huma_id+"')");//select
								// * from plan_mstr p1 where
								// to_date('01-06-2011','dd-mm-yyyy') between
								// (select min(plan_fdate) from plan_mstr where
								// huma_id='0003') and (select max(plan_tdate)
								// from plan_mstr where huma_id='0003') or
								// to_date('30-06-2011','dd-mm-yyyy') between
								// (select min(plan_fdate) from plan_mstr where
								// huma_id='0003') and (select max(plan_tdate)
								// from plan_mstr where huma_id='0003')
								// ps3 =
								// con.prepareStatement("select huma_id from plan_mstr p1 where to_date('"+key1+"','dd-mm-yyyy')<=(select min(plan_fdate) from plan_mstr where huma_id='"+huma_id+"') and to_date('"+key2+"','dd-mm-yyyy')>=(select max(plan_tdate) from plan_mstr where huma_id='"+huma_id+"')");//select
								// * from plan_mstr p1 where
								// to_date('01-06-2011','dd-mm-yyyy')<=(select
								// min(plan_fdate) from plan_mstr where
								// huma_id='0003') and
								// to_date('30-06-2011','dd-mm-yyyy')>=(select
								// max(plan_tdate) from plan_mstr where
								// huma_id='0003')

								rs2 = ps2.executeQuery();
								if (rs2 == null)
									System.out
											.println("result set is null(in getuserupdate)");
								/*
								 * rs3 = ps3.executeQuery(); if(rs3==null)
								 * System.out.println(
								 * "result set3 is null(in getuserupdate)");
								 */

								if ((rs2.next()) == false)// no plan exsist with
															// that dates//
															// checking the
															// weekoff
															// availability
								{
									ps4 = con
											.prepareStatement("select weekoff_id from weekoff_mstr where to_date('"
													+ key1
													+ "','dd-mm-yyyy')<(select weekoff_fdate from weekoff_mstr where weekoff_id='"
													+ weekoff_id
													+ "') or to_date('"
													+ key2
													+ "','dd-mm-yyyy')>(select weekoff_tdate from weekoff_mstr where weekoff_id='"
													+ weekoff_id + "')");// select
																			// *
																			// from
																			// weekoff_mstr
																			// where
																			// to_date('01-06-2011','dd-mm-yyyy')<(select
																			// weekoff_fdate
																			// from
																			// weekoff_mstr
																			// where
																			// weekoff_id='01')
																			// or
																			// to_date('31-12-2011','dd-mm-yyyy')>(select
																			// weekoff_tdate
																			// from
																			// weekoff_mstr
																			// where
																			// weekoff_id='01')

									rs4 = ps4.executeQuery();
									if (rs4 == null)
										System.out
												.println("result set4 is null(in getuserupdate)");
									if ((rs4.next()) == false)// the boundaries
																// are not
																// exceeding
																// weekoff id
																// boundaries
									{

										ps1 = con
												.prepareStatement("SELECT rownum as sno,to_char(dates,'dd-mm-yyyy') as plandates,TO_CHAR(dates, 'day') as day,(select holiday_title from holiday_mstr where holiday_date=dates) as title,state_id as state,(select state_name from state_mstr where state_id=s.state_id) as state_name FROM (SELECT TO_DATE('"
														+ key1
														+ "','dd-mm-yyyy') + LEVEL - 1 dates FROM DUAL CONNECT BY LEVEL <= (TO_DATE('"
														+ key2
														+ "','dd-mm-yyyy') - TO_DATE('"
														+ key1
														+ "','dd-mm-yyyy')) + 1) inner,state_mstr s WHERE s.state_id=(select state_id from city_mstr where city_id=(select city_id from huma_mstr where huma_id='"
														+ huma_id
														+ "')) and trim(TO_CHAR(inner.dates, 'day')) not in (select weekoff_enable from weekoffbind_mstr where weekoff_enable is not null and weekoff_id='"
														+ weekoff_id
														+ "') and inner.dates not in (select holiday_date from holiday_mstr where weekoff_id='"
														+ weekoff_id
														+ "' and holiday_id in (select holiday_id from holidaybind_mstr where state_id=s.state_id and holiday_enable is not null)) order by to_char(dates,'yyyy-mm-dd')");

										rs1 = ps1.executeQuery();// System.out.println("Hey going for while loop of plan table");
										while (rs1.next()) { // //System.out.println("inside the main while loop of the plan_table and taking non holiday dates in month");
											data = data + rs1.getString(2)
													+ "//////";// System.out.println("end of plan_table while loop,the data is ="+data);
										}// while
									}// if plan dates(boundaries) are not
										// exceeding weekoff_id boundaries
									else
										// else checking the planning dates are
										// exceeding weekoff_id boundaries
										data = "NOPlanning dates are exceeding weekoff boundaries";// NOPlanning
																									// dates
																									// are
																									// exceeding
																									// weekoff
																									// Id
																									// boundaries
								}// if no plan exsist with that dates
								else
									// else checking the plan existance in
									// plan_mstr
									data = "NOAlready existing plan for selected dates";
							}// if(x==0) //means the specified weekoff_id is
								// available in the holiday_mstr
							else
								data = "NOHoliday master does not exist for selected dates";// NOHoliday
																							// master
																							// does
																							// not
																							// exist
																							// for
																							// entered
																							// weekoff
																							// Id
						}// else to check the emplouyee proccessing no other
							// employee plan details so continue
					}// else if(decide=="plan_table")
					else if (decide.equals("plan_mstr"))// querying for the plan
														// master data
					{ // System.out.println("hey inside the else if for the plan_mstr");
						if (key1.length() >= 4)// if(huma_id!="")
							key1 = key1.substring(0, 4);// substring(0,4);
						if (key2.length() >= 2)// if(weekoff_id!="")
							key2 = key2.substring(0, 2);// substring(0,2);

						if (!((String) session.getAttribute("userType"))
								.equals("SUPER"))// the querying person is emp
						{
							if (key2 == "")
								ps = con.prepareStatement("select p.huma_id,p.weekoff_id,to_char(p.plan_fdate,'dd-mm-yyyy'),to_char(p.plan_tdate,'dd-mm-yyyy'),p.plan_controleno,to_char(p.plan_date,'dd-mm-yyyy'),p.plan_head,p.plan_place,p.plan_services from plan_mstr p,huma_mstr h where p.huma_id=h.huma_id and p.huma_id in ('"
										+ session.getAttribute("user")
										+ "') order by h.huma_id,to_char(p.plan_date,'yyyy-mm-dd')");// just
																										// use
																										// the
																										// current
																										// emp
							else
								ps = con.prepareStatement("select p.huma_id,p.weekoff_id,to_char(p.plan_fdate,'dd-mm-yyyy'),to_char(p.plan_tdate,'dd-mm-yyyy'),p.plan_controleno,to_char(p.plan_date,'dd-mm-yyyy'),p.plan_head,p.plan_place,p.plan_services from plan_mstr p,huma_mstr h where p.huma_id=h.huma_id and p.huma_id in ('"
										+ session.getAttribute("user")
										+ "') and p.weekoff_id='"
										+ key2
										+ "' order by h.huma_id,to_char(p.plan_date,'yyyy-mm-dd')"); // use
																										// the
																										// weekoff_id
																										// &
																										// current
																										// emp//
						} else// the querying person is super
						{
							if (key1 == "" && key2 == "") {// System.out.println("hey inside the both null quwery");
								ps = con.prepareStatement("select p.huma_id,p.weekoff_id,to_char(p.plan_fdate,'dd-mm-yyyy'),to_char(p.plan_tdate,'dd-mm-yyyy'),p.plan_controleno,to_char(p.plan_date,'dd-mm-yyyy'),p.plan_head,p.plan_place,p.plan_services from plan_mstr p,huma_mstr h where p.huma_id=h.huma_id  order by h.huma_id,to_char(p.plan_date,'yyyy-mm-dd')");
							}// dont use both in the sql query
							else if (key1 != "" && key2 != "") {
								ps = con.prepareStatement("select p.huma_id,p.weekoff_id,to_char(p.plan_fdate,'dd-mm-yyyy'),to_char(p.plan_tdate,'dd-mm-yyyy'),p.plan_controleno,to_char(p.plan_date,'dd-mm-yyyy'),p.plan_head,p.plan_place,p.plan_services from plan_mstr p,huma_mstr h where p.huma_id=h.huma_id and p.huma_id in ('"
										+ key1
										+ "') and p.weekoff_id='"
										+ key2
										+ "' order by h.huma_id,to_char(p.plan_date,'yyyy-mm-dd')"); // System.out.println("hey you are in the else query =if(key1==' ' && key2==' ')");
							}// use both
							else if (key1 == "" && key2 != "")
								ps = con.prepareStatement("select p.huma_id,p.weekoff_id,to_char(p.plan_fdate,'dd-mm-yyyy'),to_char(p.plan_tdate,'dd-mm-yyyy'),p.plan_controleno,to_char(p.plan_date,'dd-mm-yyyy'),p.plan_head,p.plan_place,p.plan_services from plan_mstr p,huma_mstr h where p.huma_id=h.huma_id and p.weekoff_id='"
										+ key2
										+ "' order by h.huma_id,to_char(p.plan_date,'yyyy-mm-dd')");// use
																									// only
																									// key2(weekoff_id)
																									// in
																									// the
																									// sql
																									// query
							else {
								ps = con.prepareStatement("select p.huma_id,p.weekoff_id,to_char(p.plan_fdate,'dd-mm-yyyy'),to_char(p.plan_tdate,'dd-mm-yyyy'),p.plan_controleno,to_char(p.plan_date,'dd-mm-yyyy'),p.plan_head,p.plan_place,p.plan_services from plan_mstr p,huma_mstr h where p.huma_id=h.huma_id and p.huma_id in ('"
										+ key1
										+ "') order by h.huma_id,to_char(p.plan_date,'yyyy-mm-dd')"); // System.out.println("hey you are in the else query =if(key1==' ' && key2!=' ')");
							}// use only key1(huma_id) in the sql query
						}
						// st =
						// con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						rs = ps.executeQuery();
						while (rs.next()) { // //System.out.println("inside the while loop");
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "//////";// //System.out.println("inside the while loop,the data is ="+data);
						}// while
					}// else if(decide=="plan_mstr")
					else if (decide.equals("Timesheet"))// validation for the
														// Timesheet report to
														// check plan and log
														// entries existance
					{ // System.out.println("hey inside the else if for the Timesheet");
						String huma_id = request.getParameter("huma_id")
								.toString(); // key1=workdone_fdate,key2=workdone_tdate
						String plan_fdate = request.getParameter("plan_fdate")
								.toString(); // System.out.println("plan_fdate="+plan_fdate);
						String plan_tdate = request.getParameter("plan_tdate")
								.toString(); // System.out.println("plan_tdate="+plan_tdate);
						if (huma_id.length() >= 4)// if(huma_id!="")
							huma_id = huma_id.substring(0, 4);// substring(0,1);
						String dates[];
						if ((plan_fdate == "") && (plan_tdate == "")) {
							dates = new String[2];
							dates[0] = key1;
							dates[1] = key2;
						}// if
						else {
							dates = new String[4];
							dates[0] = key1;
							dates[1] = key2;
							dates[2] = plan_fdate;
							dates[3] = plan_tdate;
						}// else

						// String[] dates = { key1, key2};
						// System.out.println("dates.length="+dates.length);
						int x3 = 0;// to checke the all 4 dates entered are
									// whether having/refering any
									// weekoff_id/holiday year or not
						ps = con.prepareStatement("select weekoff_id from weekoff_mstr w1 where to_date(?,'dd-mm-yyyy') between (select min(weekoff_fdate) from weekoff_mstr where weekoff_id=w1.weekoff_id) and (select max(weekoff_tdate) from weekoff_mstr where weekoff_id=w1.weekoff_id)");// query
																																																																								// to
																																																																								// checke
																																																																								// the
																																																																								// all
																																																																								// 4
																																																																								// dates
																																																																								// entered
																																																																								// are
																																																																								// whether
																																																																								// having/refering
																																																																								// any
																																																																								// weekoff_id/holiday
																																																																								// year
																																																																								// or
																																																																								// not
						for (int i = 0; i < dates.length; i++) { // System.out.println("het inside the for loop and the dates[i]="+dates[i]);
							ps.setString(1, dates[i]);
							rs = ps.executeQuery();
							if (rs == null)
								System.out
										.println("result set is null in geruserupdate(Timesheet)");
							if ((rs.next()) == false)
								x3 = x3 + 1;
						}// for(int i=0; i<4; i++)
							// System.out.println("hey outside the for loop and the x3="+x3);
						if (x3 != 0)
							data = "NOYou conn't select a date that does not comes under any of holiday year period";
						else// weekoff master existed for all the dates selected
						{ // System.out.println("hey inside the else means the x3==0");
							int x = 0;// to check for the availability of
										// entered weekoff_id in holiday_mstr.
							ps = con.prepareStatement("SELECT to_date(dates) from table (WorkingDays('"
									+ huma_id
									+ "','"
									+ key1
									+ "','"
									+ key2
									+ "'))");
							// System.out.println("hey query made &stmt is also created");
							rs = ps.executeQuery();// System.out.println("query executed successfully");
							if (rs == null)
								System.out
										.println("result set is null in the Timesheet in getuserupdate");
							ps2 = con
									.prepareStatement("select huma_id from log_mstr where huma_id=? and log_date=?");// qery
																														// toCheck
																														// log
																														// entered
																														// fully
							// System.out.println("hey preperedStatement is also made");
							while (rs.next())// ==false)
							{
								ps2.setString(1, huma_id);
								ps2.setDate(2, rs.getDate(1));
								rs2 = ps2.executeQuery();
								if (rs2 == null)
									System.out
											.println("result set2 is null in geruserupdate(Timesheet)");
								if ((rs2.next()) == false)
									x = x + 1;
							}// while((rs.next())
								// System.out.println("Hey the x value="+x);
							int x2 = 0;// to check for the availability of
										// entered plan in plan_mstr.
							ps3 = con
									.prepareStatement("SELECT to_date(dates) from table (WorkingDays('"
											+ huma_id
											+ "','"
											+ plan_fdate
											+ "','" + plan_tdate + "'))");

							rs3 = ps3.executeQuery();
							if (rs3 == null)
								System.out
										.println("result set 3 is null in the Timesheet in getuserupdate");
							ps4 = con
									.prepareStatement("select huma_id from plan_mstr where huma_id=? and plan_date=?");// qery
																														// toCheck
																														// plan
																														// entered
																														// fully
							while (rs3.next())// ==false)
							{
								ps4.setString(1, huma_id);
								ps4.setDate(2, rs3.getDate(1));
								rs4 = ps4.executeQuery();
								if (rs4 == null)
									System.out
											.println("result set 4 is null in geruserupdate(Timesheet)");
								if ((rs4.next()) == false)
									x2 = x2 + 1;
							}// while((rs.next())
								// System.out.println("Hey the x2 value="+x2);
							if ((x != 0) && (x2 != 0))// The Log & Plan are not
														// completed fully for
														// the respective
														// periods
								data = "NOThe Log & Plan are not completed fully for the respective periods";
							else if ((x == 0) && (x2 != 0))// Plan is not
															// completed fully
															// for the
															// respective
															// periods
								data = "NOThe Plan is not completed fully for the respective periods";
							else if ((x != 0) && (x2 == 0))// Log is not
															// completed fully
															// for the
															// respective
															// periods
								data = "NOThe Log is not completed fully for the respective periods";
							else if ((x == 0) && (x2 == 0))// Log & Plana are
															// completed fully
															// for the
															// respective
															// periods
								data = "OKThe Log & Plan are completed fully for the respective periods";
						}// else//weekoff master existed for all the dates
							// selected
					}// else if(decide=="Timesheet")
						// =======UPBSN module related code starts
						// here===============================================================================================\
					// --------district_mstr related code starts
					// here---------------------------------------------------------------------------
					else if (decide.equals("client_id.district_id")) { // System.out.println("hey inside the if for the client_id.district_id");
						if (key1.length() >= 7)// if(prop_id>=7)
							key1 = key1.substring(0, 7);// substring(0,5);
						// if(key2.length()>=7)//if(prop_id>=7)
						// key2=key2.substring(0,7);//substring(0,7);
						ps2 = con
								.prepareStatement("select client_id||' '||client_name as client_id,client_phone,client_email,client_ceo from client_mstr where client_id='"
										+ key1 + "'");
						rs2 = ps2.executeQuery(); // System.out.println("hey aftert execution of the query and going to make the data string");
						while (rs2.next())
							data = data + rs2.getString(1) + "::::::"
									+ rs2.getString(2) + "::::::"
									+ rs2.getString(3) + "::::::"
									+ rs2.getString(4) + "::::::";
						// System.out.println("the assignment of data string happened");
					}// if(decide=="client_id.district_id")
					else if (decide.equals("district_id"))// new district_id
															// when click on new
															// without
															// refreshment
					{ // System.out.println("hey inside the if for the district_id");
						// getting state_id,huma_id,country_id to assign in
						// their fields automatically in enable() function
						int x2 = 0;
						String curdistrictid = "";
						ps2 = con
								.prepareStatement("select 'DI'||nvl((select to_char(to_number(substr(district_id,3)+1),'FM00000') from district_mstr where  district_id=(select max(district_id) from district_mstr)),'00001') as district_id from dual");
						rs2 = ps2.executeQuery();
						if (rs2 == null)
							System.out
									.println("result set1 is null in valid.jsp");
						if ((rs2.next()) == false)
							x2 = x2 + 1; // System.out.println("hey after the next on resultset and string="+rs1.getString(1));
						if (x2 == 0)// entered field exists in huma_mstr
						{// session.setAttribute("state_id",rs1.getString(1));
							curdistrictid = rs2.getString(1);
						}
						// System.out.println("Hey the curcountryid="+curcountryid);

						data = curdistrictid;
					}// else if(decide=="district_id")
					else if (decide.equals("district_mstr"))// querying for the
															// district_mstr
															// data
					{
						if ((key1.length() >= 3))// if(state_id!="")
							key1 = key1.substring(0, 3);
						if ((key2.length() >= 7))// if(district_id!="")
							key2 = key2.substring(0, 7);
						// String key3 =
						// request.getParameter("key3").toString();
						key3 = request.getParameter("key3").toString();
						if ((key3.length() >= 7))// if(client_id!="")
							key3 = key3.substring(0, 7);

						ps = con.prepareStatement("select d.state_id||' '||s.state_name as state_id,d.district_id,d.district_name,d.district_collector,d.district_dbm,d.district_dbmcontact1,d.district_dbmcontact2,d.district_dpm,d.district_dpmcontact1,d.district_dpmcontact2,d.district_dc,d.district_dccontact1,d.district_dccontact2,d.district_dtc,d.district_dtccontact1,d.district_dtccontact2,(select client_id||' '||client_name from client_mstr where client_id=d.client_id) as client_id,(select client_ceo from client_mstr where client_id=d.client_id) as client_ceo,d.district_pf,d.district_pfcontact1,d.district_pfcontact2,(select client_phone from client_mstr where client_id=d.client_id) as client_phone,(select client_email from client_mstr where client_id=d.client_id) as client_email from state_mstr s,district_mstr d where s.state_id=d.state_id and d.state_id=nvl('"
								+ key1
								+ "',d.state_id) and d.district_id=nvl('"
								+ key2
								+ "',d.district_id) and (d.client_id=nvl('"
								+ key3
								+ "',d.client_id) or (d.client_id is null and '"
								+ key3
								+ "' is null)) order by d.district_id,d.state_id,d.client_id");// "+key3+"
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of district_mstr in getuserupdate");
						while (rs.next()) { // System.out.println("hey inside the while loop");
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "::::::"
									+ rs.getString(12) + "::::::"
									+ rs.getString(13) + "::::::"
									+ rs.getString(14) + "::::::"
									+ rs.getString(15) + "::::::"
									+ rs.getString(16) + "::::::"
									+ rs.getString(17) + "::::::"
									+ rs.getString(18) + "::::::"
									+ rs.getString(19) + "::::::"
									+ rs.getString(20) + "::::::"
									+ rs.getString(21) + "::::::"
									+ rs.getString(22) + "::::::"
									+ rs.getString(23) + "//////";
						}// while(rs.next())
					}// else if(decide=="district_mstr")
						// --------district_mstr related code ends
						// here-----------------------------------------------------------------------------
					// --------subunit_mstr related code starts
					// here---------------------------------------------------------------------------
					else if (decide.equals("subunit_id"))// new subunit_id when
															// click on new
															// without
															// refreshment
					{ // System.out.println("hey inside the if for the subunit_id");
						// getting district_id,huma_id,country_id to assign in
						// their fields automatically in enable() function
						int x2 = 0;
						String cursubunitid = "";
						ps2 = con
								.prepareStatement("select 'SU'||nvl((select to_char(to_number(substr(subunit_id,3)+1),'FM00000') from subunit_mstr where  subunit_id=(select max(subunit_id) from subunit_mstr)),'00001') as subunit_id from dual");
						rs2 = ps2.executeQuery();
						if (rs2 == null)
							System.out
									.println("result set1 is null in valis.jsp");
						if ((rs2.next()) == false)
							x2 = x2 + 1; // System.out.println("hey after the next on resultset and string="+rs1.getString(1));
						if (x2 == 0)// entered field exists in huma_mstr
							cursubunitid = rs2.getString(1);
						data = cursubunitid;
					}// else if(decide=="subunit_id")
					else if (decide.equals("subunit_mstr"))// querying for the
															// subunit_mstr data
					{
						if ((key1.length() >= 7))// if(district_id!="")
							key1 = key1.substring(0, 7);
						if ((key2.length() >= 7))// if(subunit_id!="")
							key2 = key2.substring(0, 7);

						ps = con.prepareStatement("select s.district_id||' '||d.district_name as district_id,s.subunit_id,s.subunit_name,s.subunit_tehsildar,s.subunit_dm,s.subunit_dmcontact1,s.subunit_dmcontact2,s.llimitfpo,s.ulimitfpo,s.llimitpg,s.ulimitpg from district_mstr d,subunit_mstr s where d.district_id=s.district_id and s.district_id=nvl('"
								+ key1
								+ "',s.district_id) and s.subunit_id=nvl('"
								+ key2
								+ "',s.subunit_id) order by s.subunit_id,s.district_id");// "+key3+"
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of subunit_mstr in getuserupdate");
						while (rs.next()) { // System.out.println("hey inside the while loop");
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "//////";
						}// while(rs.next())
					}// else if(decide=="subunit_mstr")
						// --------subunit_mstr related code ends
						// here-----------------------------------------------------------------------------
					// --------block_mstr related code starts
					// here---------------------------------------------------------------------------
					else if (decide.equals("block_id"))// new block_id when
														// click on new without
														// refreshment
					{ // System.out.println("hey inside the if for the block_id");
						// getting subunit_id,huma_id,country_id to assign in
						// their fields automatically in enable() function
						int x2 = 0;
						String curblockid = "";
						ps2 = con
								.prepareStatement("select 'BL'||nvl((select to_char(to_number(substr(block_id,3)+1),'FM00000') from block_mstr where  block_id=(select max(block_id) from block_mstr)),'00001') as block_id from dual");
						rs2 = ps2.executeQuery();
						if (rs2 == null)
							System.out
									.println("result set1 is null in valis.jsp");
						if ((rs2.next()) == false)
							x2 = x2 + 1; // System.out.println("hey after the next on resultset and string="+rs1.getString(1));
						if (x2 == 0)// entered field exists in huma_mstr
							curblockid = rs2.getString(1);
						data = curblockid;
					}// else if(decide=="block_id")
					else if (decide.equals("block_mstr"))// querying for the
															// block_mstr data
					{
						if ((key1.length() >= 7))// if(subunit_id!="")
							key1 = key1.substring(0, 7);
						if ((key2.length() >= 7))// if(block_id!="")
							key2 = key2.substring(0, 7);

						ps = con.prepareStatement("select s.subunit_id||' '||d.subunit_name as subunit_id,s.block_id,s.block_name,s.block_incharge,s.block_bdo,s.block_bdocontact1,s.block_bdocontact2 from subunit_mstr d,block_mstr s where d.subunit_id=s.subunit_id and s.subunit_id=nvl('"
								+ key1
								+ "',s.subunit_id) and s.block_id=nvl('"
								+ key2
								+ "',s.block_id) order by s.block_id,s.subunit_id");// "+key3+"
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of block_mstr in getuserupdate");
						while (rs.next()) { // System.out.println("hey inside the while loop");
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "//////";
						}// while(rs.next())
					}// else if(decide=="block_mstr")
						// --------block_mstr related code ends
						// here-----------------------------------------------------------------------------
					// --------grampanchayat_mstr related code starts
					// here---------------------------------------------------------------------------
					else if (decide.equals("grampanchayat_id"))// new
																// grampanchayat_id
																// when click on
																// new without
																// refreshment
					{ // System.out.println("hey inside the if for the grampanchayat_id");
						// getting block_id,huma_id,country_id to assign in
						// their fields automatically in enable() function
						int x2 = 0;
						String curgrampanchayatid = "";
						ps2 = con
								.prepareStatement("select 'GP'||nvl((select to_char(to_number(substr(grampanchayat_id,3)+1),'FM00000') from grampanchayat_mstr where  grampanchayat_id=(select max(grampanchayat_id) from grampanchayat_mstr)),'00001') as grampanchayat_id from dual");
						rs2 = ps2.executeQuery();
						if (rs2 == null)
							System.out
									.println("result set1 is null in valis.jsp");
						if ((rs2.next()) == false)
							x2 = x2 + 1; // System.out.println("hey after the next on resultset and string="+rs1.getString(1));
						if (x2 == 0)// entered field exists in huma_mstr
							curgrampanchayatid = rs2.getString(1);
						data = curgrampanchayatid;
					}// else if(decide=="grampanchayat_id")
					else if (decide.equals("grampanchayat_mstr"))// querying for
																	// the
																	// grampanchayat_mstr
																	// data
					{
						if ((key1.length() >= 7))// if(block_id!="")
							key1 = key1.substring(0, 7);
						if ((key2.length() >= 7))// if(grampanchayat_id!="")
							key2 = key2.substring(0, 7);

						ps = con.prepareStatement("select s.block_id||' '||d.block_name as block_id,s.grampanchayat_id,s.grampanchayat_name,s.grampanchayat_incharge from block_mstr d,grampanchayat_mstr s where d.block_id=s.block_id and s.block_id=nvl('"
								+ key1
								+ "',s.block_id) and s.grampanchayat_id=nvl('"
								+ key2
								+ "',s.grampanchayat_id) order by s.grampanchayat_id,s.block_id");// "+key3+"
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of grampanchayat_mstr in getuserupdate");
						while (rs.next()) { // System.out.println("hey inside the while loop");
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "//////";
						}// while(rs.next())
					}// else if(decide=="grampanchayat_mstr")
						// --------grampanchayat_mstr related code ends
						// here-----------------------------------------------------------------------------
					// --------village_mstr related code starts
					// here---------------------------------------------------------------------------
					else if (decide.equals("village_id"))// new village_id when
															// click on new
															// without
															// refreshment
					{ // System.out.println("hey inside the if for the village_id");
						// getting grampanchayat_id,huma_id,country_id to assign
						// in their fields automatically in enable() function
						int x2 = 0;
						String curvillageid = "";
						ps2 = con
								.prepareStatement("select 'VI'||nvl((select to_char(to_number(substr(village_id,3)+1),'FM00000') from village_mstr where  village_id=(select max(village_id) from village_mstr)),'00001') as village_id from dual");
						rs2 = ps2.executeQuery();
						if (rs2 == null)
							System.out
									.println("result set1 is null in valis.jsp");
						if ((rs2.next()) == false)
							x2 = x2 + 1; // System.out.println("hey after the next on resultset and string="+rs1.getString(1));
						if (x2 == 0)// entered field exists in huma_mstr
							curvillageid = rs2.getString(1);
						data = curvillageid;
					}// else if(decide=="village_id")
					/*
					 * else if (decide.equals("village_mstr"))// querying for
					 * the // village_mstr data { if ((key1.length() >= 7))//
					 * if(grampanchayat_id!="") key1 = key1.substring(0, 7); if
					 * ((key2.length() >= 7))// if(village_id!="") key2 =
					 * key2.substring(0, 7);
					 * 
					 * ps = con.prepareStatement(
					 * "select s.grampanchayat_id||' '||d.grampanchayat_name as grampanchayat_id,s.village_id,s.village_name,s.village_incharge from grampanchayat_mstr d,village_mstr s where d.grampanchayat_id=s.grampanchayat_id and s.grampanchayat_id=nvl('"
					 * + key1 + "',s.grampanchayat_id) and s.village_id=nvl('" +
					 * key2 +
					 * "',s.village_id) order by s.village_id,s.grampanchayat_id"
					 * );// "+key3+" rs = ps.executeQuery();//
					 * System.out.println
					 * ("Hey going for while loop of village_mstr in getuserupdate"
					 * ); while (rs.next()) { //
					 * System.out.println("hey inside the while loop"); data =
					 * data + rs.getString(1) + "::::::" + rs.getString(2) +
					 * "::::::" + rs.getString(3) + "::::::" + rs.getString(4) +
					 * "//////"; }// while(rs.next()) }// else
					 * if(decide=="village_mstr") // --------village_mstr
					 * related code ends //
					 * here----------------------------------
					 * ------------------------------------------- //
					 * --------assocomp_mstr related code starts //
					 * here----------
					 * --------------------------------------------
					 * ---------------------
					 */else if (decide.equals("assocomp_id"))// new assocomp_id
																// when click on
																// new
																// without
																// refreshment
					{ // System.out.println("hey inside the if for the assocomp_id");
						// getting client_id,huma_id,country_id to assign in
						// their fields automatically in enable() function
						int x2 = 0;
						String curassocompid = "";
						ps2 = con
								.prepareStatement("select 'AC'||nvl((select to_char(to_number(substr(assocomp_id,3)+1),'FM00000') from assocomp_mstr where  assocomp_id=(select max(assocomp_id) from assocomp_mstr)),'00001') as assocomp_id from dual");
						rs2 = ps2.executeQuery();
						if (rs2 == null)
							System.out
									.println("result set1 is null in valis.jsp");
						if ((rs2.next()) == false)
							x2 = x2 + 1; // System.out.println("hey after the next on resultset and string="+rs1.getString(1));
						if (x2 == 0)// entered field exists in huma_mstr
							curassocompid = rs2.getString(1);
						data = curassocompid;
					}// else if(decide=="assocomp_id")
					else if (decide.equals("assocomp_mstr"))// querying for the
															// assocomp_mstr
															// data
					{
						if ((key1.length() >= 7))// if(assocomp_id!="")
							key1 = key1.substring(0, 7);
						if ((key2.length() >= 7))// if(client_id!="")
							key2 = key2.substring(0, 7);
						// String key3 =
						// request.getParameter("key3").toString();
						key3 = request.getParameter("key3").toString();
						if ((key3.length() >= 4))// if(area_id!="")
							key3 = key3.substring(0, 4);
						ps = con.prepareStatement("select s.assocomp_id,s.assocomp_name,s.client_id||' '||d.client_name as client_id,b.area_id||' '||b.area_name as area_id,s.assocomp_incharge from assocomp_mstr s,client_mstr d,area_mstr b where d.client_id=s.client_id and b.area_id=s.area_id and s.assocomp_id=nvl('"
								+ key1
								+ "',s.assocomp_id) and s.client_id=nvl('"
								+ key2
								+ "',s.client_id) and b.area_id=nvl('"
								+ key3
								+ "',b.area_id) order by s.assocomp_id,s.client_id,b.area_id");// "+key3+"
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of assocomp_mstr in getuserupdate");
						while (rs.next()) { // System.out.println("hey inside the while loop");
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "//////";
						}// while(rs.next())
					}// else if(decide=="assocomp_mstr")
						// --------assocomp_mstr related code ends
						// here-----------------------------------------------------------------------------
					// --------activityfl_mstr related code starts
					// here---------------------------------------------------------------------------
					else if (decide.equals("activityfl_id"))// new activityfl_id
															// when click on new
															// without
															// refreshment
					{ // System.out.println("hey inside the if for the activityfl_id");
						// getting activityfl_abbr,huma_id,country_id to assign
						// in their fields automatically in enable() function
						int x2 = 0;
						String curactivityflid = "";
						ps2 = con
								.prepareStatement("select nvl((select to_char(to_number(substr(activityfl_id,3)+1),'FM0000') from activityfl_mstr where  activityfl_id=(select max(activityfl_id) from activityfl_mstr)),'0001') as activityfl_id from dual");
						rs2 = ps2.executeQuery();
						if ((rs2.next()) == false)
							x2 = x2 + 1; // System.out.println("hey after the next on resultset and string="+rs1.getString(1));
						if (x2 == 0)// entered field exists in huma_mstr
							curactivityflid = rs2.getString(1);
						data = curactivityflid;
					}// else if(decide=="activityfl_id")
					else if (decide.equals("activityfl_mstr"))// querying for
																// the
																// activityfl_mstr
																// data
					{
						// String key3 =
						// request.getParameter("key3").toString();
						key3 = request.getParameter("key3").toString();
						ps = con.prepareStatement("select s.activityfl_id,s.activityfl_name,s.activityfl_abbr,s.activityfl_type from activityfl_mstr s where s.activityfl_name=nvl('"
								+ key1
								+ "',s.activityfl_name) and s.activityfl_abbr=nvl('"
								+ key2
								+ "',s.activityfl_abbr) and s.activityfl_type=nvl('"
								+ key3
								+ "',s.activityfl_type) order by s.activityfl_name,s.activityfl_abbr,s.activityfl_type");// "+key3+"
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of activityfl_mstr in getuserupdate");
						while (rs.next()) { // System.out.println("hey inside the while loop");
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "//////";
						}// while(rs.next())
					}// else if(decide=="activityfl_mstr")
						// --------activityfl_mstr related code ends
						// here-----------------------------------------------------------------------------
					// --------activitysl_mstr related code starts
					// here---------------------------------------------------------------------------
					else if (decide.equals("ACTIVITY_ID"))// new ACTIVITY_ID
															// when click on new
															// without
															// refreshment
					{ // System.out.println("hey inside the if for the ACTIVITY_ID");
						// getting ACTIVITY_ID,huma_id,country_id to assign in
						// their fields automatically in enable() function
						int x2 = 0;
						String curactivityslid = "";
						ps2 = con
								.prepareStatement("select nvl((select to_char(to_number(substr(ACTIVITY_ID,3)+1),'FM0000') from activitysl_mstr where  ACTIVITY_ID=(select max(ACTIVITY_ID) from activitysl_mstr)),'0001') as ACTIVITY_ID from dual");
						rs2 = ps2.executeQuery();
						if (rs2 == null)
							System.out
									.println("result set1 is null in valis.jsp");
						if ((rs2.next()) == false)
							x2 = x2 + 1; // System.out.println("hey after the next on resultset and string="+rs1.getString(1));
						if (x2 == 0)// entered field exists in huma_mstr
							curactivityslid = rs2.getString(1);
						data = curactivityslid;
					}// else if(decide=="ACTIVITY_ID")
					else if (decide.equals("activitysl_mstr"))// querying for
																// the
																// activitysl_mstr
																// data
					{
						if ((key1.length() >= 4))// if(ACTIVITY_ID!="")
							key1 = key1.substring(0, 4);
						if ((key2.length() >= 4))// if(area_id!="")
							key2 = key2.substring(0, 4);
						// String key3 =
						// request.getParameter("key3").toString();
						key3 = request.getParameter("key3").toString();
						ps = con.prepareStatement("select s.activityfl_id,s.ACTIVITY_ID,s.activitysl_name,s.area_id||' '||d.area_name as area_id,s.activitysl_QuantityYN,s.activitysl_fpoYN,s.activitysl_QuantityIn,s.activitysl_Quantitytype,s.activitysl_onlydescYN,s.activitysl_estblYN,s.activitysl_pgYN,s.activitysl_PriceYN,s.activitysl_PriceIn,s.activitysl_AreaYN from area_mstr d,activitysl_mstr s where d.area_id=s.area_id and s.ACTIVITY_ID=nvl('"
								+ key1
								+ "',s.ACTIVITY_ID) and s.area_id=nvl('"
								+ key2
								+ "',s.area_id) and s.activityfl_id=nvl('"
								+ key3
								+ "',s.activityfl_id) order by s.ACTIVITY_ID,s.activityfl_id,s.area_id");// "+key3+"
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of activitysl_mstr in getuserupdate");
						while (rs.next()) { // System.out.println("hey inside the while loop");
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "::::::"
									+ rs.getString(12) + "::::::"
									+ rs.getString(13) + "::::::"
									+ rs.getString(14) + "//////";
						}// while(rs.next())
					}// else if(decide=="activitysl_mstr")
						// --------activitysl_mstr related code ends
						// here-----------------------------------------------------------------------------
					// --------uplan_mstr related code starts
					// here---------------------------------------------------------------------------
					else if (decide.equals("uplan_mstr"))// querying for the
															// uplan data
					{ // System.out.println("hey inside the else if for the uplan_mstr");
						if (key1.length() >= 4)// if(huma_id!="")
							key1 = key1.substring(0, 4);// substring(0,4);
						ps = con.prepareStatement("select (select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=l.huma_id )as huma_id,l.uplan_desc,to_char(l.uplan_fdate,'dd-mm-yyyy') as uplan_fdate,to_char(l.uplan_tdate,'dd-mm-yyyy') as uplan_tdate,l.uplan_controleno,l.ACTIVITY_ID,(select l.village_id||' '||v.village_name from village_mstr v where v.village_id=l.village_id) as village_id,(select l.block_id||' '||b.block_name from block_mstr b where b.block_id=l.block_id) as block_id,(select l.subunit_id||' '||s.subunit_name from subunit_mstr s where s.subunit_id=l.subunit_id) as subunit_id,l.uplan_services from uplan_mstr l,huma_mstr h where l.huma_id=h.huma_id and l.huma_id=nvl('"
								+ key1
								+ "',l.huma_id) and l.uplan_desc=nvl('"
								+ key2
								+ "',l.uplan_desc) and l.huma_id=nvl('"
								+ curhuma_id
								+ "',l.huma_id) order by h.huma_id,l.uplan_fdate,l.uplan_seqid");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of cont_mstr");
						while (rs.next()) {
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "//////";
						}// while(rs.next())
					}// else if(decide=="uplan_mstr")
						// --------uplan_mstr related code ends
						// here-----------------------------------------------------------------------------
						// --------UHLOG_SERVICE related code starts
						// here---------------------------------------------------------------------------
					else if (decide.equals("ops_changeActivitysl"))// ops_changeActivitysl
																	// block for
																	// the
																	// UHLOG_SERVICE,svre_mstr
																	// data upon
																	// chage of
																	// Activities
					{ // System.out.println("hey inside the else if for the UHLOG_SERVICE");
						ps = con.prepareStatement("select s.activitysl_fpoYN,s.activitysl_onlydescYN,s.activitysl_name as activitysl_name,s.activitysl_QuantityYN,s.activitysl_estblYN,s.activitysl_pgYN,s.activitysl_priceYN,s.activitysl_AreaYN from activitysl_mstr s,activityfl_mstr f where s.activityfl_id=f.activityfl_id and s.ACTIVITY_ID='"
								+ key2 + "' order by s.activitysl_seqid");// select
																			// head_client,head_services,head_name
																			// from
																			// head_mstr
																			// where
																			// head_id='"+key2+"'
																			// order
																			// by
																			// head_seqid");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of cont_mstr");
						while (rs.next())
							data = rs.getString(1) + "::::::" + rs.getString(2)
									+ "::::::" + rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8);
					}// else if(decide=="ops_changeActivity")

					// msr
					else if (decide.equals("UHLOG_SERVICE"))// querying for the
															// fae
					// data
					{ // System.out.println("hey inside the else if for the UHLOG_SERVICE");
						// Connection areacon=new JdbcConnect().getConnection();
						ResultSet arears;
						Statement areast = con.createStatement();
						String area1 = null;
						String area2 = null;
						String result = "false";
						String unitno = key1
								.substring(key1.lastIndexOf('-') + 1);

						// if(userrole.equals("unithead"))
						// {
						// arears=areast.executeQuery("select bsflunit_ucode from bsflunit_mstr where huma_id='"+huma_idd+"'");
						// if(arears!=null)
						// {
						// System.out.println("rs is not null");
						// while(arears.next())
						// {
						//
						// area1=arears.getString(1);
						// if(area1.equals(unitno))
						// {
						// result="true";
						// break;
						// }//if
						// else
						// {
						// result="false";
						// }//else
						// }//while
						// }//if
						// //********************************
						// //if(!area1.equals(unitno))
						// if(!result.equals("true"))
						// {
						// data=null;
						// return;
						// }
						// }//if(userrole.equals("unithead"))

						if (userrole.equals("unithead")) {
							arears = areast
									.executeQuery("select bsflunit_ucode from bsflunit_mstr where huma_id='"
											+ huma_idd + "'");
							if (arears != null) {
								// System.out.println("rs is not null");
								while (arears.next()) {

									area1 = arears.getString(1);
									if (area1.equals(unitno)) {
										result = "true";
										break;
									}// if
									else {
										result = "false";
									}// else

								}// while
								arears.close();
							}// if
								// ********************************
								// if(!area1.equals(unitno))
							if (!result.equals("true")) {
								data = null;
								return;
							}
						}// if(role.equals("unithead"))

						if (userrole.equals("areahead")) {
							/*
							 * arears=areast.executeQuery(
							 * "select area_id from area_mstr where huma_id='"
							 * +huma_idd+"'"); if(arears!=null) {
							 * while(arears.next()) {
							 * 
							 * area1=arears.getString(1); }//while }
							 */// if
								// ********************************
							arears = areast
									.executeQuery("select area_id from bsflunit_mstr where bsflunit_ucode='"
											+ unitno + "'");
							if (arears != null) {
								while (arears.next()) {

									area2 = arears.getString(1);
								}// while
							}// if
							arears = areast
									.executeQuery("select area_id from area_mstr where huma_id='"
											+ ' ' + "'");
							if (arears != null) {
								while (arears.next()) {

									area1 = arears.getString(1);
									if (area1.equals(area2)) {
										result = "true";
										break;
									}// if
									else {
										result = "false";
									}// else
								}// while
							}// if
								// if(!area1.equals(area2))
							if (!result.equals("true")) {
								data = null;
								return;
							}
						}

						// key1 denotes the unitNo
						if (key1.length() >= 3)// if(huma_id!="")
							key1 = key1.substring(key1.lastIndexOf('-') + 1);// key1.substring(0,
																				// 3);//
																				// substring(0,4);
						// System.out.println("hey inside the else if for the UHLOG_SERVICE key1="+key1);
						ps = con.prepareStatement("select l.UHLOG_CONTROLENO,"
								+ "(select  BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE from bsflunit_mstr where BSFLUNIT_UCODE=l.BSFLUNIT_UCODE )as BSFLUNIT_UCODE,"
								+ "(select area_name from area_mstr where area_id=(select area_id from bsflunit_mstr where BSFLUNIT_UCODE=l.BSFLUNIT_UCODE)) as area_name,"
								+ "to_char(l.UHLOG_DATE,'dd-mm-yyyy') as UHLOG_DATE,"
								+ "(select v.VNAME||'-'||l.VCODE from village_mstr v where v.VCODE=l.VCODE and v.BSFLUNIT_UCODE=l.BSFLUNIT_UCODE) as VCODE,"
								+ "(select ACTIVITY_NAME||'-'||ACTIVITY_ID from ACTIVITY_MSTR a where a.ACTIVITY_ID=l.ACTIVITY_ID) as activity_id,"
								+ "(select ACTCAT_NAME||'-'||ACTCAT_ID from ACTCAT_MSTR ac where ac.ACTCAT_ID=(select ACTCAT_ID from ACTIVITY_MSTR a where a.ACTIVITY_ID=l.ACTIVITY_ID)) as ACTCAT_ID,"
								+ "l.UHLOG_AMTSPENT,"
								+ "l.UHLOG_OUTREACH,"
								+ "l.UHLOG_ODCUST,"
								+ "l.UHLOG_ODAMT,"
								+ "l.UHLOG_REMARKS from UHLOG_SERVICE l,BSFLUNIT_MSTR h where l.BSFLUNIT_UCODE=h.BSFLUNIT_UCODE and l.BSFLUNIT_UCODE=nvl('"
								+ key1
								+ "',l.BSFLUNIT_UCODE) "
								+ "and l.UHLOG_date=nvl(to_date('"
								+ key2
								+ "','dd-mm-yyyy'),l.UHLOG_date) order by h.BSFLUNIT_UCODE,l.UHLOG_date,l.UHLOG_seqid");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of cont_mstr");
						while (rs.next()) {
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "::::::"
									+ rs.getString(12) + "//////";
							System.out.println(data);

						}// while(rs.next())
					}// else if(decide=="UHLOG_SERVICE")
					else if (decide.equals("war_period"))// querying for
															// war_period
					{
						String huma_id = request.getParameter("huma_id").trim();
						huma_id = huma_id
								.substring(huma_id.lastIndexOf('-') + 1);
						/*
						 * String fdate = request.getParameter("key1").trim();
						 * String tdate = request.getParameter("key2").trim();
						 * SimpleDateFormat format = new
						 * SimpleDateFormat("dd-MM-yyyy"); Date d1 = null; Date
						 * d2 = null; d1 = format.parse(fdate); d2 =
						 * format.parse(tdate); long diff = d2.getTime() -
						 * d1.getTime(); long diffDays = diff / (24 * 60 * 60 *
						 * 1000);
						 * System.out.println("difference between the date is : "
						 * +diffDays); ps = con.prepareStatement(
						 * "select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY where  HUMA_ID=? and TARGET_FRS_DATE IN (TO_DATE(?,'DD-MM-YYYY'))"
						 * ); long totpod=0; for(int i=0;i<=diffDays;i++){
						 * Calendar c = Calendar.getInstance(); c.setTime(d1);
						 * c.add(Calendar.DATE, i); String
						 * date=format.format(c.getTime()); ps.setString(1,
						 * huma_id); ps.setString(2, date); rs1 =
						 * ps.executeQuery(); if (rs1.next()) {
						 * System.out.println(date+"\t"+rs1.getLong(1)); //data
						 * = data + rs1.getLong(1) + "::::::";
						 * totpod=totpod+rs1.getLong(1); } }
						 * //data=data.substring(0, data.length()-6);
						 * System.out.println("Jsp sending value is : "+data);
						 * System.out.println("Total POD value is : "+totpod);
						 */Boolean status = false;
						Boolean humaStatus = false;
						// Role check implemented by Rajesh
						String login_huma_id = request.getSession()
								.getAttribute("huma_id").toString();
						String entered_huma_id = huma_id;
						String sql1;
						String sql2;
						String areano1 = null;
						String areano2 = null;
						String result2 = "false";
						// System.out.println("Role Checked By Rajesh and the role is = "+userrole);
						// System.out.println("Entered huma_id= "+entered_huma_id);

						String svsql1 = "select * from HUMA_MSTR where HUMA_ID='"
								+ huma_id + "'";
						Statement svst1 = con.createStatement();
						ResultSet svrs1 = svst1.executeQuery(svsql1);
						/*
						 * if (svrs1.next()) { status=true; }
						 */if (!svrs1.next()) {
							humaStatus = true;
							// data
							// ="Entered Field Staff code does not Exist :"+
							// huma_id;
						}// if
						/*
						 * if(status){ data
						 * ="Entered Field Staff code does not Exist :"+
						 * huma_id; return;}
						 */
						// Boolean status = false;

						if (userrole.equals("areahead")) {
							try {
								// System.out.println("inside the areahead block");
								// logged in user belongs to which area
								sql1 = "select area_id from area_mstr where huma_id in('"
										+ login_huma_id + "')";
								// The user to be deleted belongs to which area
								sql2 = "select area_id from bsflunit_mstr where bsflunit_ucode=(select bsflunit_ucode from huma_mstr where huma_id='"
										+ entered_huma_id + "')";

								Statement st2 = con.createStatement();
								ResultSet rs2 = st2.executeQuery(sql2);
								System.out.println("sql2 executed");
								if (rs2 != null) {
									System.out.println("rs2 not null");
									while (rs2.next()) {
										areano2 = rs2.getString(1);
									}// while
								}// if
									// Connection con1=jc.getConnection();
								Statement st1 = con.createStatement();
								ResultSet rs1 = st1.executeQuery(sql1);
								System.out.println("sql1 executed");
								if (rs1 != null) {
									while (rs1.next()) {
										areano1 = rs1.getString(1);
										if (areano1.equals(areano2)) {
											result2 = "true";
											break;
										}// if
										else {
											result2 = "false";
										}// else
									}// while
								}// if
								System.out.println("areano1= " + areano1);

								// if(!areano1.equals(areano2))
								if (!result2.equals("true")) {
									// out.println("You are not allowed to delete other Region's user");
									// return;
									// failrd.include(request, response);
									System.out.println("Areas are not Same");
									// out.println("You are not allowed to work on another Region");
									// return;
									status = true;
								}

							} catch (Exception e) {
								e.getMessage();
								e.printStackTrace();
							}
						}// if(role.equals("areahead"))

						if (userrole.equals("unithead")) {

							try {
								sql1 = "select bsflunit_ucode from bsflunit_mstr where huma_id in('"
										+ login_huma_id + "')";
								// The user to be deleted belongs to which area
								sql2 = "select bsflunit_ucode from huma_mstr where huma_id='"
										+ entered_huma_id + "'";

								Statement st2 = con.createStatement();
								ResultSet rs2 = st2.executeQuery(sql2);
								System.out.println("sql2 executed");
								if (rs2 != null) {
									System.out.println("rs2 not null");
									while (rs2.next()) {
										areano2 = rs2.getString(1);
									}// while
								}// if
									// Connection con1=jc.getConnection();
								Statement st1 = con.createStatement();
								ResultSet rs1 = st1.executeQuery(sql1);
								System.out.println("sql1 executed");
								if (rs1 != null) {
									while (rs1.next()) {
										areano1 = rs1.getString(1);
										if (areano1.equals(areano2)) {
											result2 = "true";
											break;
										}// if
										else {
											result2 = "false";
										}// else
									}// while
								}// if
								System.out.println("areano1= " + areano1);

								// if(!areano1.equals(areano2))
								if (!result2.equals("true")) {
									// out.println("You are not allowed to delete other Region's user");
									// return;
									// failrd.include(request, response);
									// out.println("You are not allowed to work on another Unit");
									// return;
									status = true;
								}

							} catch (Exception e) {
								e.getMessage();
							}
						}// if(role.equals("unithead"))

						if (userrole.equals("user")) {
							// System.out.println("inside the user role");
							if (!entered_huma_id.equals(login_huma_id)) {

								// out.println("You are not allowed to work for other User");
								// return;
								status = true;
							}

						}

						// Role check Completed
						if (status) {
							data = "NOYou are not allowed to enter for other Region / Unit / Field Staff";
						} else if (humaStatus) {
							data = "NOEntred Field Staff does not exists, pls check!";
						} else {
							status = false;
							humaStatus = false;
							ps2 = con
									.prepareStatement("select distinct huma_id from war_target,(SELECT TO_DATE('"
											+ key1
											+ "','dd-mm-yyyy') + LEVEL - 1 dates FROM DUAL CONNECT BY LEVEL <= (TO_DATE('"
											+ key2
											+ "','dd-mm-yyyy') - TO_DATE('"
											+ key1
											+ "','dd-mm-yyyy')) + 1) where huma_id='"
											+ huma_id
											+ "' and war_date=to_date(dates)");// select
																				// distinct
																				// huma_id
																				// from
																				// war_target,table(WorkingDays(huma_id,'01-04-2012','15-04-2012'))
																				// where
																				// huma_id='0001'
																				// and
																				// war_date=to_date(dates)
							rs2 = ps2.executeQuery();
							if (rs2 == null)
								System.out
										.println("result set is null(in getuserupdate)");
							if ((rs2.next()) == false)// no plan exsist with
														// that dates//
														// checking the
														// weekoff
														// availability
							{
								// select TARGET_FRS_OD_AMT from
								// TARGET_FRS_RECOVERY where HUMA_ID=? and
								// TARGET_FRS_DATE IN (TO_DATE(?,'DD-MM-YYYY'))
								ps1 = con
										.prepareStatement("select to_char(dates,'dd-mm-yyyy')||' '||TO_CHAR(dates, 'Day') as day	FROM (SELECT TO_DATE('"
												+ key1
												+ "','dd-mm-yyyy') + LEVEL - 1 dates FROM DUAL CONNECT BY LEVEL <= (TO_DATE('"
												+ key2
												+ "','dd-mm-yyyy') - TO_DATE('"
												+ key1
												+ "','dd-mm-yyyy')) + 1) inner");
								/*
								 * ps1 = con.prepareStatement(
								 * "select to_char(dates,'dd-mm-yyyy')||' '||TO_CHAR(dates, 'Day') from TARGET_FRS_RECOVERY where  HUMA_ID='"
								 * +huma_id+
								 * "' and TARGET_FRS_DATE IN (TO_DATE(to_char(dates,'dd-mm-yyyy'),'DD-MM-YYYY'))) as day	FROM (SELECT TO_DATE('"
								 * + key1 +
								 * "','dd-mm-yyyy') + LEVEL - 1 dates FROM DUAL CONNECT BY LEVEL <= (TO_DATE('"
								 * + key2 + "','dd-mm-yyyy') - TO_DATE('" + key1
								 * + "','dd-mm-yyyy')) + 1) inner");
								 */
								rs1 = ps1.executeQuery();// System.out.println("Hey going for while loop of plan table");
								while (rs1.next()) { // //System.out.println("inside the main while loop of the war_period and taking non holiday dates in month");
									data = data + rs1.getString(1) + "//////";// System.out.println("end of war_period while loop,the data is ="+data);
								}// while
							}// if no plan exsist with that dates
							else
								// else checking the plan existance in
								// war_target
								data = "NOAlready existing plan for selected dates";
						}// else if(decide=="war_period")
					} else if (decide.equals("war_target"))// querying for the
															// fae
					// data
					{ // System.out.println("hey inside the else if for the UHLOG_SERVICE");
						// key1 denotes the unitNo
						if (key1.length() >= 3)// if(huma_id!="")
							key1 = key1.substring(key1.lastIndexOf('-') + 1);// key1.substring(0,
																				// 3);//
																				// substring(0,4);
						key2 = key2.substring(key2.lastIndexOf('-') + 1);
						if ((userrole.equals("admin"))
								|| (userrole.equals("audit"))) {
							// System.out.println("Admin / adit");
							// ||
							// role.equals("areahead")
							// ||
							// role.equals("unithead"))
							// ps =
							// con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE huma_DESIGNATION IN (select grade_id from grade_mstr where GRADE_level in ('Executive','Associate')) and huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) order by huma_id");
							ps = con.prepareStatement("select "
									+ "w.WAR_CONTROLENO,"
									+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
									+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
									+ " to_char(w.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,"
									+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,"
									+ "w.WAR_CUST_COUNT,"
									+ "w.WAR_SDRCUST_COUNT,"
									+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=w.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(w.WAR_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
									+ "w.WAR_villages,"
									+ "to_char(w.WAR_DATE,'Day') as WAR_DAY"
									// + "0 as WAR_POD"
									/*
									 * + "w.war_reason,"//WAR_Lsrbacklogs +
									 * "w.WAR_Lsrbacklogs"
									 */+ " from WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where w.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
									+ "and h.bsflunit_ucode=nvl('"
									+ key1
									+ "',h.bsflunit_ucode) and w.huma_id=nvl('"
									+ key2
									+ "',w.huma_id) order by b.area_id, b.bsflunit_ucode, w.huma_id, w.WAR_SEQID");

						} else if (userrole.equals("areahead")) {
							// System.out.println("areahead");
							// ps =
							// con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE huma_DESIGNATION IN (select grade_id from grade_mstr where GRADE_level in ('Executive','Associate')) and huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where area_id in (select area_id from area_mstr where huma_id='"+huma_id+"')) order by huma_id");
							ps = con.prepareStatement("select "
									+ "w.WAR_CONTROLENO,"
									+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
									+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
									+ " to_char(w.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,"
									+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,w.WAR_CUST_COUNT,w.WAR_SDRCUST_COUNT,"
									+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=w.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(w.WAR_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
									+ "w.WAR_villages,"
									+ "to_char(w.WAR_DATE,'Day') as WAR_DAY"
									// + "0 as WAR_POD"
									/*
									 * + "w.war_reason," + "w.WAR_Lsrbacklogs"
									 */+ " from WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where w.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
									+ "and h.bsflunit_ucode=nvl('"
									+ key1
									+ "',h.bsflunit_ucode) and w.huma_id=nvl('"
									+ key2
									+ "',w.huma_id) and b.bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where area_id in (select area_id from area_mstr where huma_id='"
									+ huma_idd
									+ "')) order by b.area_id, b.bsflunit_ucode, w.huma_id, w.WAR_SEQID");
						} else if (userrole.equals("unithead")) {
							// System.out.println("unithead");
							// ps =
							// con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE huma_DESIGNATION IN (select grade_id from grade_mstr where GRADE_level in ('Executive','Associate')) and huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where  huma_id='"+huma_id+"') order by huma_id");
							ps = con.prepareStatement("select "
									+ "w.WAR_CONTROLENO,"
									+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
									+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
									+ " to_char(w.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,"
									+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,w.WAR_CUST_COUNT,w.WAR_SDRCUST_COUNT,"
									+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=w.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(w.WAR_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
									+ "w.WAR_villages,"
									+ "to_char(w.WAR_DATE,'Day') as WAR_DAY"
									// + "0 as WAR_POD"
									/*
									 * + "w.war_reason,"//WAR_Lsrbacklogs +
									 * "w.WAR_Lsrbacklogs"
									 */+ " from WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where w.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
									+ "and h.bsflunit_ucode=nvl('"
									+ key1
									+ "',h.bsflunit_ucode) and w.huma_id=nvl('"
									+ key2
									+ "',w.huma_id) and b.bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where huma_id='"
									+ huma_idd
									+ "') order by b.area_id, b.bsflunit_ucode, w.huma_id, w.WAR_SEQID");
						} else
							// ps =
							// con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE huma_DESIGNATION IN (select grade_id from grade_mstr where GRADE_level in ('Executive','Associate')) and huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and huma_id ='"+huma_id+"'");
							ps = con.prepareStatement("select "
									+ "w.WAR_CONTROLENO,"
									+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
									+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
									+ " to_char(w.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,"
									+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,"
									+ "w.WAR_CUST_COUNT,"
									+ "w.WAR_SDRCUST_COUNT,"
									+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=w.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(w.WAR_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
									+ "w.WAR_villages,"
									+ "to_char(w.WAR_DATE,'Day') as WAR_DAY"
									// + "0 as WAR_POD"
									/*
									 * + "w.war_reason,"//WAR_Lsrbacklogs +
									 * "w.WAR_Lsrbacklogs"
									 */+ " from WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where w.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
									+ "and h.bsflunit_ucode=nvl('"
									+ key1
									+ "',h.bsflunit_ucode) and w.huma_id=nvl('"
									+ key2
									+ "',w.huma_id) and w.huma_id ='"
									+ huma_idd
									+ "' order by b.area_id, b.bsflunit_ucode, w.huma_id, w.WAR_SEQID");

						rs = ps.executeQuery();// System.out.println("Hey going for while loop of cont_mstr");
						ps1 = con
								.prepareStatement("select VNAME from village_mstr where vcode=? and BSFLUNIT_UCODE=?");
						while (rs.next()) {
							// System.out.println("target amount is :"+rs.getLong(8));
							StringTokenizer st = null;
							String vl = rs.getString(9);
							cache = "";
							String un = rs.getString(2);
							// System.out.println("unit name and code "+un );
							int i1 = un.lastIndexOf('-');
							String ucode = un.substring(i1 + 1);
							// System.out.println("unit code is "+ucode);
							String cache1 = "";
							// if (vl.contains("::")) {
							st = new StringTokenizer(vl, "::");
							while (st.hasMoreElements()) {
								String temp = (String) st.nextElement();
								ps1.setString(1, temp);
								ps1.setString(2, ucode);
								rs1 = ps1.executeQuery();
								if (rs1.next()) {
									cache1 = rs1.getString(1) + "-";
								}
								// below else black added by Rajashekhar to
								// avoid wrong village names if village not
								// found in village master
								else {
									cache1 = "";
								}// while
									// System.out.println(cache1+"-"+temp+"::");
								cache += cache1 + temp + "::";
								// System.out.println("cache="+cache);
							}// while
							int length = cache.length();
							cache = cache.substring(0, length - 2);
							// System.out.println("cache values are : "+cache);
							// System.out.println(data);
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::" + cache
									+ "::::::"
									// + rs.getString(10)+ "::::::"
									+ rs.getString(10) + "//////";
									System.out.println(data);
							/*
							 * + rs.getString(12) + "::::::" + rs.getString(13)
							 * +
							 */;
						}// while(rs.next())
					}// else if(decide=="war_target")
					
					
else if (decide.equals("plan_actual")) { // System.out.println("hey inside the if for the huma_mstr");
						
						key1 = key1.substring(key1.lastIndexOf('-') + 1);// huma_id
						System.out.println("key1 = "+key1);
						ps = con.prepareStatement("select b.war_controlno,b.name_sh,b.region,b.activity_id,to_char(b.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,to_char(b.WAR_DATE,'Day') as WAR_DAY,b.unit_visited,work_plannew b  where  a.huma_id='E811' and b.bsflunit_ucode='"+key1+"'");
						rs = ps.executeQuery();
						//System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next()) {
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "//////";
									//+ rs.getString(11) + "::::::"
									//+ rs.getString(12) + "//////";
							System.out.println(data);

						}// while(// + rs.getString(17) + "//////"; Hide By Rajesh
						 // Added by Rajesh
					}
else if (decide.equals("plan_work")) { // System.out.println("hey inside the if for the huma_mstr");
						
						key1 = key1.substring(key1.lastIndexOf('-') + 1);// huma_id
						System.out.println("key1 = "+key1);
						ps = con.prepareStatement("select b.war_controlno,b.huma_id,a.city_id,a.huma_fname||'-'||a.huma_lname as huma_fullname,c.grade_abbreviation,to_char(b.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,to_char(b.WAR_DATE,'Day') as WAR_DAY,b.unit_visited,b.activities_planned,b.act_id from huma_mstr a,work_plannew b,grade_mstr c,bsflunit_mstr s where  a.huma_id='E811' and a.huma_designation=c.grade_id and s.bsflunit_ucode='"+key1+"' order by b.WAR_DATE");
						rs = ps.executeQuery();
						//System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next()) {
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "//////";
									//+ rs.getString(11) + "::::::"
									//+ rs.getString(12) + "//////";
							System.out.println(data);

						}// while(// + rs.getString(17) + "//////"; Hide By Rajesh
						 // Added by Rajesh
					}
					
					else if(decide.equals("work_plan")){
						if (key1.length() >= 3)// if(huma_id!="")
							key1 = key1.substring(key1.lastIndexOf('-') + 1);
						System.out.println("came here");
						
						ps=con.prepareStatement("select" 
								 +"w.WAR_CONTROLNO,"
								+" b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
								 +"(select city_id from huma_mstr where city_id=h.city_id) as  place_of_posting,"
								  +"to_char(w.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,"
								  +"(select huma_designation from huma_mstr where huma_id=w.huma_id) as designation,"
								 +"(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,"
								 +"w.WITH_UH,w.NAMES,to_char(w.WAR_DATE,'Day') as WAR_DAY"
								 +"from work_plan w ,bsflunit_mstr b, huma_mstr h where w.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode"
								 +"and h.bsflunit_ucode=nvl('123',h.bsflunit_ucode) and w.huma_id=nvl('E811',w.huma_id) and b.bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where huma_id='E811') order by b.area_id, b.bsflunit_ucode, w.huma_id, w.WAR_SEQID");
					rs=ps.executeQuery();
					while(rs.next()){
						data = data + rs.getString(1) + "::::::"
                                + rs.getString(2) + "::::::"
                                + rs.getString(3) + "::::::"
                                + rs.getString(4) + "::::::"
                                + rs.getString(5) + "::::::"
                                + rs.getString(6) + "::::::"
                                + rs.getString(7) + "::::::"
                                + rs.getString(8) + "::::::"
                                + rs.getString(9) + "//////";
						System.out.println("data is " +data);
					}
					}

					
					else if (decide.equals("actual_plan"))// querying for the
						// fae
// data
{ // System.out.println("hey inside the else if for the UHLOG_SERVICE");
// key1 denotes the unitNo
if (key1.length() >= 3)// if(huma_id!="")
key1 = key1.substring(key1.lastIndexOf('-') + 1);// key1.substring(0,
											// 3);//
											// substring(0,4);
key2 = key2.substring(key2.lastIndexOf('-') + 1);
if ((userrole.equals("admin"))
|| (userrole.equals("audit"))) {
// System.out.println("Admin / adit");
// ||
// role.equals("areahead")
// ||
// role.equals("unithead"))
// ps =
// con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE huma_DESIGNATION IN (select grade_id from grade_mstr where GRADE_level in ('Executive','Associate')) and huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) order by huma_id");
ps = con.prepareStatement("select "
+ "w.WAR_CONTROLNO,"
+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
+ " to_char(w.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,"
+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,"
+ "w.WITH_UH,"
+ "w.WAR_CUST_COUNT,"
+ "w.WAR_SDRCUST_COUNT,"
+ "w.WAR_STAY,"
+ "w.WAR_OBSERVATIONS,"

+ "w.WAR_villages,"
+ "to_char(w.WAR_DATE,'Day') as WAR_DAY"
// + "0 as WAR_POD"
/*
 * + "w.war_reason,"//WAR_Lsrbacklogs +
 * "w.WAR_Lsrbacklogs"
 */+ " from ACTUAL_PLAN w ,bsflunit_mstr b, huma_mstr h where w.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
+ "and h.bsflunit_ucode='123' and w.huma_id='E811'order by b.area_id, b.bsflunit_ucode, w.huma_id, w.WAR_SEQID");

} else if (userrole.equals("areahead")) {
// System.out.println("areahead");
// ps =
// con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE huma_DESIGNATION IN (select grade_id from grade_mstr where GRADE_level in ('Executive','Associate')) and huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where area_id in (select area_id from area_mstr where huma_id='"+huma_id+"')) order by huma_id");
ps = con.prepareStatement("select "
+ "w.WAR_CONTROLENO,"
+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
+ " to_char(w.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,"
+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,w.WAR_CUST_COUNT,w.WAR_SDRCUST_COUNT,"
+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=w.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(w.WAR_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
+ "w.WAR_villages,"
+ "to_char(w.WAR_DATE,'Day') as WAR_DAY"
// + "0 as WAR_POD"
/*
 * + "w.war_reason," + "w.WAR_Lsrbacklogs"
 */+ " from WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where w.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
+ "and h.bsflunit_ucode=nvl('"
+ key1
+ "',h.bsflunit_ucode) and w.huma_id=nvl('"
+ key2
+ "',w.huma_id) and b.bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where area_id in (select area_id from area_mstr where huma_id='"
+ huma_idd
+ "')) order by b.area_id, b.bsflunit_ucode, w.huma_id, w.WAR_SEQID");
} else if (userrole.equals("unithead")) {
// System.out.println("unithead");
// ps =
// con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE huma_DESIGNATION IN (select grade_id from grade_mstr where GRADE_level in ('Executive','Associate')) and huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where  huma_id='"+huma_id+"') order by huma_id");
ps = con.prepareStatement("select "
+ "w.WAR_CONTROLENO,"
+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
+ " to_char(w.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,"
+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,w.WAR_CUST_COUNT,w.WAR_SDRCUST_COUNT,"
+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=w.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(w.WAR_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
+ "w.WAR_villages,"
+ "to_char(w.WAR_DATE,'Day') as WAR_DAY"
// + "0 as WAR_POD"
/*
 * + "w.war_reason,"//WAR_Lsrbacklogs +
 * "w.WAR_Lsrbacklogs"
 */+ " from WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where w.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
+ "and h.bsflunit_ucode=nvl('"
+ key1
+ "',h.bsflunit_ucode) and w.huma_id=nvl('"
+ key2
+ "',w.huma_id) and b.bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where huma_id='"
+ huma_idd
+ "') order by b.area_id, b.bsflunit_ucode, w.huma_id, w.WAR_SEQID");
} else
// ps =
// con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE huma_DESIGNATION IN (select grade_id from grade_mstr where GRADE_level in ('Executive','Associate')) and huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and huma_id ='"+huma_id+"'");
ps = con.prepareStatement("select "
+ "w.WAR_CONTROLENO,"
+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
+ " to_char(w.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,"
+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,"
+ "w.WAR_CUST_COUNT,"
+ "w.WAR_SDRCUST_COUNT,"
+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=w.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(w.WAR_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
+ "w.WAR_villages,"
+ "to_char(w.WAR_DATE,'Day') as WAR_DAY"
// + "0 as WAR_POD"
/*
 * + "w.war_reason,"//WAR_Lsrbacklogs +
 * "w.WAR_Lsrbacklogs"
 */+ " from WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where w.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
+ "and h.bsflunit_ucode=nvl('"
+ key1
+ "',h.bsflunit_ucode) and w.huma_id=nvl('"
+ key2
+ "',w.huma_id) and w.huma_id ='"
+ huma_idd
+ "' order by b.area_id, b.bsflunit_ucode, w.huma_id, w.WAR_SEQID");

rs = ps.executeQuery();// System.out.println("Hey going for while loop of cont_mstr");
ps1 = con
.prepareStatement("select VNAME from village_mstr where vcode=? and BSFLUNIT_UCODE=?");
while (rs.next()) {
// System.out.println("target amount is :"+rs.getLong(8));
StringTokenizer st = null;
String vl = rs.getString(11);
cache = "";
String un = rs.getString(2);
// System.out.println("unit name and code "+un );
int i1 = un.lastIndexOf('-');
String ucode = un.substring(i1 + 1);
// System.out.println("unit code is "+ucode);
String cache1 = "";
// if (vl.contains("::")) {
st = new StringTokenizer(vl, "::");
while (st.hasMoreElements()) {
String temp = (String) st.nextElement();
ps1.setString(1, temp);
ps1.setString(2, ucode);
rs1 = ps1.executeQuery();
if (rs1.next()) {
cache1 = rs1.getString(1) + "-";
}
// below else black added by Rajashekhar to
// avoid wrong village names if village not
// found in village master
else {
cache1 = "";
}// while
// System.out.println(cache1+"-"+temp+"::");
cache += cache1 + temp + "::";
// System.out.println("cache="+cache);
}// while
int length = cache.length();
cache = cache.substring(0, length - 2);
// System.out.println("cache values are : "+cache);
// System.out.println(data);
data = data + rs.getString(1) + "::::::"
+ rs.getString(2) + "::::::"
+ rs.getString(3) + "::::::"
+ rs.getString(4) + "::::::"
+ rs.getString(5) + "::::::"
+ rs.getString(6) + "::::::"
+ rs.getString(7) + "::::::"
+ rs.getString(8) + "::::::"
+ rs.getString(9) + "::::::"
+ rs.getString(10) + "::::::"+ cache
+ "::::::"
// + rs.getString(10)+ "::::::"
+ rs.getString(12) + "//////";
System.out.println(data);
/*
* + rs.getString(12) + "::::::" + rs.getString(13)
* +
*/;
}// while(rs.next())
}// else if(decide=="war_target")
	
					else if (decide.equals("actual_pl"))// querying for the
						// fae
// data
{ // System.out.println("hey inside the else if for the UHLOG_SERVICE");
// key1 denotes the unitNo
if (key1.length() >= 3)// if(huma_id!="")
key1 = key1.substring(key1.lastIndexOf('-') + 1);// key1.substring(0,
											// 3);//
											// substring(0,4);
System.out.println("the key is "+key1);
key2 = key2.substring(key2.lastIndexOf('-') + 1);
if ((userrole.equals("admin"))
|| (userrole.equals("audit"))) {
// System.out.println("Admin / adit");
// ||
// role.equals("areahead")
// ||
// role.equals("unithead"))
// ps =
// con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE huma_DESIGNATION IN (select grade_id from grade_mstr where GRADE_level in ('Executive','Associate')) and huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) order by huma_id");
/*ps = con.prepareStatement("select "
+ "w.WAR_CONTROLNO,"
+ "w.NAME_SH,"
+ "w.REGION,"
+ "w.ACTIVITY_ID,"
+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
+ " to_char(w.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,"
+ "w.WITH_UH,"
+ "w.UNIT_VISITED,"
+ "w.WAR_CUST_COUNT,"
+ "w.WAR_SDRCUST_COUNT,"
+ "w.WAR_OBSERVATIONS,"
+ "w.WAR_villages,"
+ "to_char(w.WAR_DATE,'Day') as WAR_DAY"
+ " from ACTUAL_PLANNEW w ,bsflunit_mstr b, huma_mstr h where w.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
+ "and h.bsflunit_ucode=nvl('"
+ key1
+ "',h.bsflunit_ucode) and w.huma_id=nvl('"
+ key2
+ "',w.huma_id) order by b.bsflunit_ucode");
*/
	ps=con.prepareStatement("select w.WAR_CONTROLNO,w.NAME_SH,w.REGION,w.ACTIVITY_ID,b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,to_char(w.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,w.WITH_UH,w.UNIT_VISITED,w.WAR_CUST_COUNT,w.WAR_SDRCUST_COUNT,w.WAR_OBSERVATIONS,w.WAR_villages,to_char(w.WAR_DATE,'Day') as WAR_DAY  from ACTUAL_PLANNEW w ,bsflunit_mstr b, huma_mstr h where w.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode  and h.bsflunit_ucode='123' and w.huma_id='E811'order by b.bsflunit_ucode,w.WAR_DATE");
} else if (userrole.equals("areahead")) {
// System.out.println("areahead");
// ps =
// con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE huma_DESIGNATION IN (select grade_id from grade_mstr where GRADE_level in ('Executive','Associate')) and huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where area_id in (select area_id from area_mstr where huma_id='"+huma_id+"')) order by huma_id");
ps = con.prepareStatement("select "
+ "w.WAR_CONTROLNO,"
+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
+ " to_char(w.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,"
+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,w.WAR_CUST_COUNT,w.WAR_SDRCUST_COUNT,"
+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=w.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(w.WAR_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
+ "w.WAR_villages,"
+ "to_char(w.WAR_DATE,'Day') as WAR_DAY"
// + "0 as WAR_POD"
/*
 * + "w.war_reason," + "w.WAR_Lsrbacklogs"
 */+ " from WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where w.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
+ "and h.bsflunit_ucode=nvl('"
+ key1
+ "',h.bsflunit_ucode) and w.huma_id=nvl('"
+ key2
+ "',w.huma_id) and b.bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where area_id in (select area_id from area_mstr where huma_id='"
+ huma_idd
+ "')) order by b.area_id, b.bsflunit_ucode, w.huma_id, w.WAR_SEQID");
} else if (userrole.equals("unithead")) {
// System.out.println("unithead");
// ps =
// con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE huma_DESIGNATION IN (select grade_id from grade_mstr where GRADE_level in ('Executive','Associate')) and huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where  huma_id='"+huma_id+"') order by huma_id");
ps = con.prepareStatement("select "
+ "w.WAR_CONTROLENO,"
+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
+ " to_char(w.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,"
+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,w.WAR_CUST_COUNT,w.WAR_SDRCUST_COUNT,"
+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=w.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(w.WAR_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
+ "w.WAR_villages,"
+ "to_char(w.WAR_DATE,'Day') as WAR_DAY"
// + "0 as WAR_POD"
/*
 * + "w.war_reason,"//WAR_Lsrbacklogs +
 * "w.WAR_Lsrbacklogs"
 */+ " from WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where w.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
+ "and h.bsflunit_ucode=nvl('"
+ key1
+ "',h.bsflunit_ucode) and w.huma_id=nvl('"
+ key2
+ "',w.huma_id) and b.bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where huma_id='"
+ huma_idd
+ "') order by b.area_id, b.bsflunit_ucode, w.huma_id, w.WAR_SEQID");
} else
// ps =
// con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE huma_DESIGNATION IN (select grade_id from grade_mstr where GRADE_level in ('Executive','Associate')) and huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and huma_id ='"+huma_id+"'");
ps = con.prepareStatement("select "
+ "w.WAR_CONTROLENO,"
+ "b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE as BSFLUNIT_UCODE,"
+ "(select area_name from area_mstr where area_id=b.area_id) as area_name,"
+ " to_char(w.WAR_DATE,'dd-mm-yyyy') as WAR_DATE,"
+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id) as huma_id2,"
+ "w.WAR_CUST_COUNT,"
+ "w.WAR_SDRCUST_COUNT,"
+ "(select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY t where  t.HUMA_ID=w.huma_id and t.TARGET_FRS_DATE IN (TO_DATE(to_char(w.WAR_DATE,'dd-mm-yyyy'),'DD-MM-YYYY'))),"
+ "w.WAR_villages,"
+ "to_char(w.WAR_DATE,'Day') as WAR_DAY"
// + "0 as WAR_POD"
/*
 * + "w.war_reason,"//WAR_Lsrbacklogs +
 * "w.WAR_Lsrbacklogs"
 */+ " from WAR_TARGET w ,bsflunit_mstr b, huma_mstr h where w.huma_id=h.huma_id and h.bsflunit_ucode=b.bsflunit_ucode "
+ "and h.bsflunit_ucode=nvl('"
+ key1
+ "',h.bsflunit_ucode) and w.huma_id=nvl('"
+ key2
+ "',w.huma_id) and w.huma_id ='"
+ huma_idd
+ "' order by b.area_id, b.bsflunit_ucode, w.huma_id, w.WAR_SEQID");

rs = ps.executeQuery();// System.out.println("Hey going for while loop of cont_mstr");
ps1 = con.prepareStatement("select VNAME from village_mstr where vcode=? and BSFLUNIT_UCODE=?");
while (rs.next()) {
// System.out.println("target amount is :"+rs.getLong(8));
StringTokenizer st = null;
String vl = rs.getString(12);
cache = "";
String un = rs.getString(8);
System.out.println("village and code "+vl );
System.out.println("unit name and code "+un );
if(un!=null){
//int i1 = un.lastIndexOf('-');
ucode = un.substring(un.lastIndexOf('-')+1);
System.out.println("unit code is "+ucode);
}else{
		ucode="";
}
String cache1 = "";
// if (vl.contains("::")) {
if(vl!=null){
st = new StringTokenizer(vl, "::");
while (st.hasMoreElements()) {
String temp = (String) st.nextElement();
ps1.setString(1, temp);
ps1.setString(2, ucode);
rs1 = ps1.executeQuery();
if (rs1.next()) {
cache1 = rs1.getString(1) + "-";
System.out.println("the vname is "+cache1);
}
// below else black added by Rajashekhar to
// avoid wrong village names if village not
// found in village master
else {
cache1 = "";
}// while
// System.out.println(cache1+"-"+temp+"::");
cache += cache1 + temp + "::";
// System.out.println("cache="+cache);
}// while
}
else{
	String s="";
	cache +=s;
}
try{
int length = cache.length();
cache = cache.substring(0, length - 2);
}catch(StringIndexOutOfBoundsException a){
	a.printStackTrace();
}
// System.out.println("cache values are : "+cache);
// System.out.println(data);
data = data + rs.getString(1) + "::::::"
+ rs.getString(2) + "::::::"
+ rs.getString(3) + "::::::"
+ rs.getString(4) + "::::::"
+ rs.getString(5) + "::::::"
+ rs.getString(6) + "::::::"
+ rs.getString(7) + "::::::"
+ rs.getString(8) + "::::::"
+ rs.getString(9) + "::::::"
+ rs.getString(10) + "::::::"
+ rs.getString(11) + "::::::"+ cache
+ "::::::"
//+ rs.getString(10) + "::::::"+ cache
//+ "::::::"
// + rs.getString(10)+ "::::::"
+ rs.getString(13) + "//////";
System.out.println(data);
/*
* + rs.getString(12) + "::::::" + rs.getString(13)
* +
*/;
}// while(rs.next())
}// else if(decide=="war_target")
					
					
					else if (decide.equals("war_remarks"))// querying for the
					{
						if (key1.length() >= 3)// if(huma_id!="")
							// System.out.println("Key1 : "+key1);
							key1 = key1.substring(key1.lastIndexOf('-') + 1);

						// System.out.println("unit code is  :"+key1);
						// key2 = request.getParameter("key2").toString();
						// System.out.println(key2);
						key3 = request.getParameter("key3").toString();
						// System.out.println(key3);

						if ((userrole.equals("admin"))
								|| (userrole.equals("audit"))) {
							ps = con.prepareStatement("select "
									+ "to_char(REMARKS_FDATE,'dd-mm-yyyy') as REMARKS_FDATE,"
									+ "to_char(REMARKS_TDATE,'dd-mm-yyyy') as REMARKS_TDATE,"
									+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id),"
									+ "REMARKS_Backlogs_HO,"
									+ "REMARKS_REASON_HO,"
									+ "REMARKS_CONTROLENO,"
									+
									// "(select sum(w1.REMARKS_ACTPOD) from (select REMARKS_ACTPOD,bsflunit_ucode,REMARKS_FDATE from WAR_REMARKS where (bsflunit_ucode ,REMARKS_FDATE) in(select bsflunit_ucode, max(REMARKS_FDATE) from BSFLUNIT_MSTR group by bsflunit_ucode) order by remarks_fdate desc) w1 where w1.REMARKS_FDATE<=w.REMARKS_FDATE  ) as WAR_POD,"+
									"(select Actual_pod(w.REMARKS_FDATE,'HO','') from dual) war_pod,"
									+ "(select sum(fr.FRS_OD_AMT) FROM FRS_RECOVERY fr where fr.FRS_DATE between w.REMARKS_FDATE and w.REMARKS_TDATE),"
									+ "(select sum(tfr.TARGET_FRS_OD_AMT) FROM TARGET_FRS_RECOVERY tfr  where tfr.TARGET_FRS_DATE between w.REMARKS_FDATE and w.REMARKS_TDATE) "
									+ " from WAR_REMARKS w "
									+ "where  w.huma_id=nvl('"
									+ key1
									+ "',w.huma_id) and  "
									+ "w.REMARKS_FDATE <= nvl(TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy'),w.REMARKS_FDATE) and  w.REMARKS_TDATE >= nvl(TO_DATE('"
									+ key3
									+ "','dd-mm-yyyy'),w.REMARKS_TDATE) and"
									+ " ((w.REMARKS_FDATE between nvl(TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy'),w.REMARKS_FDATE) and nvl(TO_DATE('"
									+ key3
									+ "','dd-mm-yyyy'),w.REMARKS_TDATE))or(w.REMARKS_TDATE between nvl(TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy'),w.REMARKS_FDATE) and nvl(TO_DATE('"
									+ key3
									+ "','dd-mm-yyyy'),w.REMARKS_TDATE))) and w.REMARKS_FS_HO='HO' order by w.REMARKS_FDATE");
						} else if (userrole.equals("user")) {
							// con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE huma_DESIGNATION IN (select grade_id from grade_mstr where GRADE_level in ('Executive','Associate')) and huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where area_id in (select area_id from area_mstr where huma_id='"+huma_id+"')) order by huma_id");
							// System.out.println("Key1 retriving :"+key1);
							ps = con.prepareStatement("select "
									+ "to_char(REMARKS_FDATE,'dd-mm-yyyy') as REMARKS_FDATE,"
									+ "to_char(REMARKS_TDATE,'dd-mm-yyyy') as REMARKS_TDATE,"
									+ "(select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=w.huma_id),"
									+ "REMARKS_BACKLOGS_FS,"
									+ "REMARKS_BACKLOGS_HO,"
									+ "REMARKS_CONTROLENO,"
									+
									// "(select REMARKS_ACTPOD from WAR_REMARKS w where REMARKS_FDATE=TO_DATE('"+key1+"','dd-mm-yyyy') and REMARKS_TDATE=TO_DATE('"+key2+"','dd-mm-yyyy') and w.bsflunit_ucode =(select BSFLUNIT_UCODE from HUMA_MSTR where HUMA_ID='"+key3+"')),"+
									// "(select REMARKS_ACTPOD from WAR_REMARKS w1 where w1.REMARKS_FDATE<=w.REMARKS_FDATE AND w.REMARKS_FS_HO='FS' and BSFLUNIT_UCODE in (select bsflunit_ucode from HUMA_MSTR where huma_id=nvl('"+key1+"',w.huma_id))),"+
									// "(select SUM(REMARKS_ACTPOD)  from WAR_REMARKS w where w.BSFLUNIT_UCODE NOT IN ('NA') and "
									// +
									// "w.REMARKS_FS_HO='FS' and w.BSFLUNIT_UCODE=nvl((select bsflunit_ucode from HUMA_MSTR where huma_id=nvl('"+key1+"',w.huma_id)),w.BSFLUNIT_UCODE) and  "
									// +
									// "w.REMARKS_FDATE <= w.REMARKS_FDATE and  w.REMARKS_TDATE >= w.REMARKS_TDATE and "
									// +
									// "((w.REMARKS_FDATE between w.REMARKS_FDATE and  w.REMARKS_TDATE)or(w.REMARKS_TDATE between w.REMARKS_FDATE and w.REMARKS_TDATE))),"+
									"(select Actual_pod(w.REMARKS_FDATE,'FS',w.BSFLUNIT_UCODE) from dual)as REMARKS_ACTPOD,"
									+
									// "REMARKS_ACTPOD," +
									"(select sum(fr.FRS_OD_AMT) FROM FRS_RECOVERY fr where fr.FRS_DATE between w.REMARKS_FDATE and w.REMARKS_TDATE and fr.huma_id=nvl('"
									+ key1
									+ "',w.huma_id)),"
									+ "(select sum(tfr.TARGET_FRS_OD_AMT) FROM TARGET_FRS_RECOVERY tfr  where tfr.huma_id=nvl('"
									+ key1
									+ "',w.huma_id) and tfr.TARGET_FRS_DATE between w.REMARKS_FDATE and w.REMARKS_TDATE) "
									+ " from WAR_REMARKS w where HUMA_ID NOT IN ('NA') and w.huma_id=nvl('"
									+ huma_idd
									+ "','"+huma_idd+"') and  w.REMARKS_FDATE <= nvl(TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy'),w.REMARKS_FDATE) and  w.REMARKS_TDATE >= nvl(TO_DATE('"
									+ key3
									+ "','dd-mm-yyyy'),w.REMARKS_TDATE) and "
									+ "((w.REMARKS_FDATE between nvl(TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy'),w.REMARKS_FDATE) and nvl(TO_DATE('"
									+ key3
									+ "','dd-mm-yyyy'),w.REMARKS_TDATE))or(w.REMARKS_TDATE between nvl(TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy'),w.REMARKS_FDATE) and nvl(TO_DATE('"
									+ key3
									+ "','dd-mm-yyyy'),w.REMARKS_TDATE)))  and w.REMARKS_FS_HO='FS' ");
						} else if (userrole.equals("areahead")) {
							// System.out.println("areahead");
							// System.out.println(key1);
							// System.out.println(key2);
							// System.out.println(key3);
							ps = con.prepareStatement("select "
									+ "to_char(REMARKS_FDATE,'dd-mm-yyyy') as REMARKS_FDATE,"
									+ "to_char(REMARKS_TDATE,'dd-mm-yyyy') as REMARKS_TDATE,"
									+ "(select area_name||'-'||area_id from area_mstr where area_id=w.area_id),"
									+ "REMARKS_BACKLOGS_AH,"
									+ "REMARKS_REASON_AH,"
									+ "REMARKS_CONTROLENO," +
									// "(select sum(w1.REMARKS_ACTPOD) from (select REMARKS_ACTPOD,bsflunit_ucode,REMARKS_FDATE,area_id from WAR_REMARKS where (bsflunit_ucode ,REMARKS_FDATE) in(select bsflunit_ucode, max(REMARKS_FDATE) from BSFLUNIT_MSTR group by bsflunit_ucode) order by remarks_fdate desc) w1 where w1.REMARKS_FDATE<=w.REMARKS_FDATE and w1.area_id=w.area_id ) as WAR_POD,"+
									"(select Actual_pod(w.REMARKS_FDATE,'AH','"
									+ key1
									+ "') from dual) war_pod,"
									+ "(select sum(fr.FRS_OD_AMT) FROM FRS_RECOVERY fr where  fr.HUMA_ID in (select huma_id from huma_mstr where bsflunit_ucode in( select bsflunit_ucode from BSFLUNIT_MSTR where area_id=nvl('"
									+ key1
									+ "',w.area_id))) and fr.FRS_DATE between TO_DATE(w.REMARKS_FDATE,'dd-mm-yyyy') and TO_DATE(w.REMARKS_TDATE,'dd-mm-yyyy')),"
									+ "(select sum(tfr.TARGET_FRS_OD_AMT) FROM TARGET_FRS_RECOVERY tfr  where tfr.HUMA_ID in (select huma_id from huma_mstr where bsflunit_ucode in(select distinct  bsflunit_ucode from BSFLUNIT_MSTR where area_id=nvl('"
									+ key1
									+ "',w.area_id))) and tfr.TARGET_FRS_DATE between w.REMARKS_FDATE and w.REMARKS_TDATE) "
									+ " from WAR_REMARKS w where w.area_id in (select area_id from area_mstr where huma_id='"+huma_idd+"') and w.area_id=nvl('"
									+ key1
									+ "',w.area_id)  and w.REMARKS_FS_HO='AH' and  w.REMARKS_FDATE <= nvl(TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy'),w.REMARKS_FDATE) and  (w.REMARKS_TDATE >= nvl(TO_DATE('"
									+ key3
									+ "','dd-mm-yyyy'),w.REMARKS_TDATE) ) and "
									+ " ((w.REMARKS_FDATE between nvl(TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy'),w.REMARKS_FDATE) and nvl(TO_DATE('"
									+ key3
									+ "','dd-mm-yyyy'),w.REMARKS_TDATE))or(w.REMARKS_TDATE between nvl(TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy'),w.REMARKS_FDATE) and nvl(TO_DATE('"
									+ key3
									+ "','dd-mm-yyyy'),w.REMARKS_TDATE)) )");
						} else if (userrole.equals("unithead")) {
							// System.out.println("unithead");
							ps = con.prepareStatement("select "
									+ "to_char(REMARKS_FDATE,'dd-mm-yyyy') as REMARKS_FDATE,"
									+ "to_char(REMARKS_TDATE,'dd-mm-yyyy') as REMARKS_TDATE,"
									+ "(select BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE from BSFLUNIT_MSTR where BSFLUNIT_UCODE=w.BSFLUNIT_UCODE),"
									+ "REMARKS_Backlogs_UH,"
									+ "REMARKS_Reason_UH,"
									+ "REMARKS_CONTROLENO,"
									+ "(select Actual_pod(w.REMARKS_FDATE,'FS',w.BSFLUNIT_UCODE) from dual)as REMARKS_ACTPOD,"
									+ "(select sum(fr.FRS_OD_AMT) FROM FRS_RECOVERY fr where fr.HUMA_ID in (select huma_id from huma_mstr where BSFLUNIT_UCODE=nvl('"
									+ key1
									+ "',w.BSFLUNIT_UCODE)) and  fr.FRS_DATE between TO_DATE(w.REMARKS_FDATE,'dd-mm-yyyy') and TO_DATE(w.REMARKS_TDATE,'dd-mm-yyyy')),"
									+ "(select sum(tfr.TARGET_FRS_OD_AMT) FROM TARGET_FRS_RECOVERY tfr  where tfr.HUMA_ID in (select huma_id from huma_mstr where BSFLUNIT_UCODE=nvl('"
									+ key1
									+ "',w.BSFLUNIT_UCODE)) and  tfr.TARGET_FRS_DATE between w.REMARKS_FDATE and w.REMARKS_TDATE) "
									+ " from WAR_REMARKS w where w.BSFLUNIT_UCODE in (select BSFLUNIT_UCODE from BSFLUNIT_mstr where huma_id='"+huma_idd+"') and w.BSFLUNIT_UCODE NOT IN ('NA') and w.REMARKS_FS_HO='UH' and w.BSFLUNIT_UCODE=nvl('"
									+ key1
									+ "',w.BSFLUNIT_UCODE) and  w.REMARKS_FDATE <= nvl(TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy'),w.REMARKS_FDATE) and  w.REMARKS_TDATE >= nvl(TO_DATE('"
									+ key3
									+ "','dd-mm-yyyy'),w.REMARKS_TDATE) and "
									+ "((w.REMARKS_FDATE between nvl(TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy'),w.REMARKS_FDATE) and nvl(TO_DATE('"
									+ key3
									+ "','dd-mm-yyyy'),w.REMARKS_TDATE))or(w.REMARKS_TDATE between nvl(TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy'),w.REMARKS_FDATE) and nvl(TO_DATE('"
									+ key3
									+ "','dd-mm-yyyy'),w.REMARKS_TDATE)))");
						} else
							// ps =
							// con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE huma_DESIGNATION IN (select grade_id from grade_mstr where GRADE_level in ('Executive','Associate')) and huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and huma_id ='"+huma_id+"'");
							ps = con.prepareStatement("");

						rs = ps.executeQuery();// System.out.println("Hey going for while loop of cont_mstr");
						ps1 = con
								.prepareStatement("select VNAME from village_mstr where vcode=? and BSFLUNIT_UCODE=?");
						while (rs.next()) {

							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ currenceyformat(rs.getLong(7)) + "::::::"
									+ currenceyformat(rs.getLong(8)) + "::::::"
									+ currenceyformat(rs.getLong(9)) + "//////";
							// System.out.println(data);
						}// while(rs.next())
					}// else if(decide=="war_target")
						// war_remarks_pods
					else if (decide.equals("war_remarks_pods"))// querying for
																// the
					{
						key3 = request.getParameter("key3").toString();
						// System.out.println("before splitting :"+key3);
						key3 = key3.substring(key3.lastIndexOf('-') + 1);
						// System.out.println("after splitting :"+key3);
						// System.out.println(key2);
						// System.out.println(key1);
						if ((userrole.equals("admin"))
								|| (userrole.equals("audit"))) {
							ps = con.prepareStatement("select "
									+
									"(select Actual_pod(TO_DATE('"
									+ key1
									+ "','dd-mm-yyyy'),'HO','') from dual) war_pod,"
									+
									"(select sum(fr.FRS_OD_AMT) FROM FRS_RECOVERY fr where fr.FRS_DATE between TO_DATE('"
									+ key1
									+ "','dd-mm-yyyy') and TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy')),"
									+ "(select sum(tfr.TARGET_FRS_OD_AMT) FROM TARGET_FRS_RECOVERY tfr  where tfr.TARGET_FRS_DATE between TO_DATE('"
									+ key1 + "','dd-mm-yyyy') and TO_DATE('"
									+ key2 + "','dd-mm-yyyy'))" + " from dual");
						} else if (userrole.equals("user")) {
							// where area_id=
							ps = con.prepareStatement("select "
									+ "(select Actual_pod(TO_DATE('"
									+ key1
									+ "','dd-mm-yyyy'),'FS',"
									+"(select bsflunit_ucode FROM huma_mstr where huma_id='"+ key3
									+ "')) from dual) war_pod,"
									+
									"(select sum(fr.FRS_OD_AMT) FROM FRS_RECOVERY fr where fr.huma_id='"
									+ key3
									+ "' and fr.FRS_DATE between TO_DATE('"
									+ key1
									+ "','dd-mm-yyyy') and TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy')),"
									+ "(select sum(tfr.TARGET_FRS_OD_AMT) FROM TARGET_FRS_RECOVERY tfr  where tfr.huma_id='"
									+ key3
									+ "' and  tfr.TARGET_FRS_DATE between TO_DATE('"
									+ key1 + "','dd-mm-yyyy') and TO_DATE('"
									+ key2 + "','dd-mm-yyyy'))" + " from dual");
						} else if (userrole.equals("areahead")) {
							// System.out.println("areahead");
							ps = con.prepareStatement("select "
									+
									"(select Actual_pod(TO_DATE('"
									+ key1
									+ "','dd-mm-yyyy'),'AH','"
									+ key3
									+ "') from dual) war_pod,"
									+ "(select sum(fr.FRS_OD_AMT) FROM FRS_RECOVERY fr where  fr.HUMA_ID in (select huma_id from huma_mstr where bsflunit_ucode in( select bsflunit_ucode from BSFLUNIT_MSTR where AREA_ID='"
									+ key3
									+ "')) and fr.FRS_DATE between TO_DATE('"
									+ key1
									+ "','dd-mm-yyyy') and TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy')),"
									+ "(select sum(tfr.TARGET_FRS_OD_AMT) FROM TARGET_FRS_RECOVERY tfr  where tfr.HUMA_ID in (select huma_id from huma_mstr where bsflunit_ucode in(select distinct  bsflunit_ucode from BSFLUNIT_MSTR where AREA_ID in ('"
									+ key3
									+ "'))) and tfr.TARGET_FRS_DATE between TO_DATE('"
									+ key1 + "','dd-mm-yyyy') and TO_DATE('"
									+ key2 + "','dd-mm-yyyy'))" + " from dual");
						} else if (userrole.equals("unithead")) {
							ps = con.prepareStatement("select "
									+
									"(select Actual_pod(TO_DATE('"
									+ key1
									+ "','dd-mm-yyyy'),'UH','"
									+ key3
									+ "') from dual) war_pod,"
									+ "(select sum(fr.FRS_OD_AMT) FROM FRS_RECOVERY fr where fr.HUMA_ID in (select huma_id from huma_mstr where bsflunit_ucode in('"
									+ key3
									+ "')) and  fr.FRS_DATE between TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy') and TO_DATE('"
									+ key2
									+ "','dd-mm-yyyy')) recovery,"
									+ "(select sum(tfr.TARGET_FRS_OD_AMT) FROM TARGET_FRS_RECOVERY tfr  where tfr.HUMA_ID in (select huma_id from huma_mstr where bsflunit_ucode in('"
									+ key3
									+ "')) and  tfr.TARGET_FRS_DATE between TO_DATE('"
									+ key1 + "','dd-mm-yyyy') and TO_DATE('"
									+ key2 + "','dd-mm-yyyy')) target"
									+ " from dual");
							} else
							// ps =
							// con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE huma_DESIGNATION IN (select grade_id from grade_mstr where GRADE_level in ('Executive','Associate')) and huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and huma_id ='"+huma_id+"'");
							ps = con.prepareStatement("");

						rs = ps.executeQuery();// System.out.println("Hey going for while loop of cont_mstr");
						ps1 = con
								.prepareStatement("select VNAME from village_mstr where vcode=? and BSFLUNIT_UCODE=?");
						while (rs.next()) {

							data = data + currenceyformat(rs.getLong(1))
									+ "::::::" + currenceyformat(rs.getLong(2))
									+ "::::::" + currenceyformat(rs.getLong(3))
									+ "//////";
							// System.out.println(data);
						}// while(rs.next())
					}// else if(decide=="war_target")

					else if (decide.equals("village_mstr")) { // System.out.println("hey inside the if for the village_mstr");
						// Connection areacon=new JdbcConnect().getConnection();
						ResultSet arears;
						Statement areast = con.createStatement();
						String area1 = null;
						String area2 = null;
						String result = "false";
						String unitno = key1
								.substring(key1.lastIndexOf('-') + 1);
						if (userrole.equals("unithead")) {
							arears = areast
									.executeQuery("select bsflunit_ucode from bsflunit_mstr where huma_id='"
											+ ' ' + "'");
							if (arears != null) {
								System.out.println("rs is not null");
								while (arears.next()) {

									area1 = arears.getString(1);
									if (area1.equals(unitno)) {
										result = "true";
										break;
									}// if
									else {
										result = "false";
									}// else
								}// while
							}// if
								// ********************************
								// if(!area1.equals(unitno))
							if (!result.equals("true")) {
								data = null;
								return;
							}
						}// if(userrole.equals("unithead"))

						if (userrole.equals("areahead")) {
							// ********************************
							arears = areast
									.executeQuery("select area_id from bsflunit_mstr where bsflunit_ucode='"
											+ unitno + "'");
							if (arears != null) {
								while (arears.next()) {

									area2 = arears.getString(1);
								}// while
							}// if
							arears = areast
									.executeQuery("select area_id from area_mstr where huma_id='"
											+ ' ' + "'");
							if (arears != null) {
								while (arears.next()) {

									area1 = arears.getString(1);
									if (area1.equals(area2)) {
										result = "true";
										break;
									}// if
									else {
										result = "false";
									}// else

								}// while
								arears.close();
							}// if

							// if(!area1.equals(area2))
							if (!result.equals("true")) {
								data = null;
								return;
							}
						}

						// ******************************************************

						ps = con.prepareStatement("select vname,"
								+ "vcode,"
								+

								"(select BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE from bsflunit_mstr where BSFLUNIT_UCODE=l.BSFLUNIT_UCODE )as BSFLUNIT_UCODE,"
								+

								"DISTRICT," + "VILLAGE_SEQID "
								+ "from VILLAGE_MSTR l where " +

								"vcode=nvl('"
								+ key2.substring(key2.lastIndexOf('-') + 1)
								+ "',vcode) and " + "BSFLUNIT_UCODE=nvl('"
								+ key1.substring(key1.lastIndexOf('-') + 1)
								+ "',BSFLUNIT_UCODE)"
								+ " order by BSFLUNIT_UCODE,vcode");

						// }
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "//////";

					}// if(decide=="village_mstr")
						// --------UHLOG_SERVICE related code ends
						// here-----------------------------------------------------------------------------
						// --------svre_mstr related code starts
						// here---------------------------------------------------------------------------
					else if (decide.equals("UHLOG_SERVICE_old"))// querying for
																// the fae
					// data
					{
						// //System.out.println("Msr debug");
						if (key1.length() >= 4)// if(huma_id!="")
							key1 = key1.substring(0, 4);// substring(0,4);
						ps = con.prepareStatement("select (select huma_id||' '||huma_fname||' '||huma_lname from huma_mstr where huma_id=l.huma_id )as huma_id,to_char(l.UHLOG_DATE,'dd-mm-yyyy') as UHLOG_DATE,l.UHLOG_CONTROLENO,l.ACTIVITY_ID,(select l.fpo_id||' '||f.fpo_name from fpo_mstr f where f.fpo_id=l.fpo_id) as fpo_id,'t' as ops_time,l.UHLOG_ODCUST,(select l.village_id||' '||v.village_name from village_mstr v where v.village_id=l.village_id) as village_id,(select l.block_id||' '||b.block_name from block_mstr b where b.block_id=l.block_id) as block_id,(select l.subunit_id||' '||s.subunit_name from subunit_mstr s where s.subunit_id=l.subunit_id) as subunit_id,l.UHLOG_REMARKS,(select l.estbl_id||' '||f.estbl_name from estbl_mstr f where f.estbl_id=l.estbl_id) as estbl_id,(select l.pg_id||' '||f.pg_name from pg_mstr f where f.pg_id=l.pg_id) as pg_id,l.UHLOG_ODAMT,l.UHLOG_OUTREACH from UHLOG_SERVICE l,huma_mstr h where l.huma_id=h.huma_id and l.huma_id=nvl('"
								+ key1
								+ "',l.huma_id) and l.UHLOG_DATE=nvl(to_date('"
								+ key2
								+ "','dd-mm-yyyy'),l.UHLOG_DATE) and l.huma_id=nvl('"
								+ curhuma_id
								+ "',l.huma_id) order by h.huma_id,l.UHLOG_DATE,l.ops_seqid");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of cont_mstr");
						while (rs.next()) {
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "::::::"
									+ rs.getString(12) + "::::::"
									+ rs.getString(13) + "::::::"
									+ rs.getString(14) + "::::::"
									+ rs.getString(15) + "//////";
						}// while(rs.next())
					}// else if(decide=="UHLOG_SERVICE")

					else if (decide.equals("svre_id"))// new svre_id when click
														// on new without
														// refreshment
					{ // System.out.println("hey inside the if for the svre_id");
						// getting fpo_id,huma_id,country_id to assign in their
						// fields automatically in enable() function
						int x2 = 0;// String
									// curhuma_id="",cursvreid="",curcountryid="";
						ps2 = con
								.prepareStatement("select 'VR'||nvl((select to_char(to_number(substr(svre_id,3)+1),'FM00000') from svre_mstr where  svre_id=(select max(svre_id) from svre_mstr)),'00001') as svre_id from dual");
						rs2 = ps2.executeQuery();
						if ((rs2.next()) == false)
							x2 = x2 + 1; // System.out.println("hey after the next on resultset and string="+rs1.getString(1));
						if (x2 == 0)// entered field exists in huma_mstr
							data = rs2.getString(1);
					}// else if(decide=="svre_id")
					else if (decide.equals("svre_mstr"))// querying for the
														// svre_mstr data
					{
						if ((key1.length() >= 7))
							key1 = key1.substring(0, 7);// key1=svre_id
														// //key2=ACTIVITY_ID
						/*
						 * String key3 =
						 * request.getParameter("key3").toString();//
						 * key3=fpo_id String key4 =
						 * request.getParameter("key4").toString();//
						 * key4=huma_id String key5 =
						 * request.getParameter("key5").toString();//
						 * key5=svre_date String key6 =
						 * request.getParameter("key6").toString();//
						 * key6=svre_type
						 */
						key3 = request.getParameter("key3").toString();// key3=fpo_id
						key4 = request.getParameter("key4").toString();// key4=huma_id
						key5 = request.getParameter("key5").toString();// key5=svre_date
						// String key6 =
						// request.getParameter("key6").toString();//
						// key6=svre_type
						key6 = request.getParameter("key6").toString();// key6=svre_type
						if ((key3.length() >= 7))
							key3 = key3.substring(0, 7);
						if ((key4.length() >= 4))
							key4 = key4.substring(0, 4);

						ps = con.prepareStatement("select s.svre_id,s.svre_name,s.ACTIVITY_ID,(select s.fpo_id||' '||d.fpo_name from fpo_mstr d where s.fpo_id=d.fpo_id) as fpo_id,s.huma_id||' '||h.huma_fname||' '||h.huma_lname as huma_id,to_char(s.svre_date,'dd-mm-yyyy'),(select s.village_id||' '||v.village_name from village_mstr v where v.village_id=s.village_id) as village_id,(select s.block_id||' '||b.block_name from block_mstr b where b.block_id=s.block_id) as block_id,(select s.subunit_id||' '||ss.subunit_name from subunit_mstr ss where ss.subunit_id=s.subunit_id) as subunit_id,s.svre_type,s.svre_scan,s.svre_objective,s.svre_participants,s.svre_pointsDiscuss,s.svre_eventProcess,s.svre_pointsNextAction from svre_mstr s,huma_mstr h where s.huma_id=h.huma_id and s.svre_id=nvl('"
								+ key1
								+ "',s.svre_id)  and s.ACTIVITY_ID=nvl('"
								+ key2
								+ "',s.ACTIVITY_ID) and (s.fpo_id=nvl('"
								+ key3
								+ "',s.fpo_id) or (s.fpo_id is null and '"
								+ key3
								+ "' is null)) and s.huma_id=nvl('"
								+ key4
								+ "',s.huma_id) and s.svre_date=nvl(to_date('"
								+ key5
								+ "','dd-mm-yyyy'),s.svre_date) and s.svre_type=nvl('"
								+ key6
								+ "',s.svre_type) order by s.svre_id,s.fpo_id");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of svre_mstr in getuserupdate");
						while (rs.next()) { // System.out.println("hey inside the while loop");
							String data2 = "";
							rs2 = null;

							ps2 = con
									.prepareStatement("select huma_id from SVREteam_mstr where SVRE_id='"
											+ rs.getString(1)
											+ "' order by SVREteam_id");
							// System.out.println("Hey going to execute the data2 query");
							rs2 = ps2.executeQuery();
							while (rs2.next())
								data2 = data2 + rs2.getString(1) + "::::::"; // System.out.println("Hey just going to finilize data2 ");
							if (data2.length() > 5)
								data2 = data2.substring(0, data2.length() - 6);// System.out.println("hey the data2="+data2);

							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "::::::"
									+ rs.getString(12) + "::::::"
									+ rs.getString(13) + "::::::"
									+ rs.getString(14) + "::::::"
									+ rs.getString(15) + "::::::"
									+ rs.getString(16) + "::::::" + data2
									+ "//////";
						}// while(rs.next())
					}// else if(decide=="svre_mstr")
						// --------svre_mstr related code ends
						// here-----------------------------------------------------------------------------
					// --------fpo_mstr related code starts
					// here---------------------------------------------------------------------------
					else if (decide.equals("fpo_id"))// new fpo_id when click on
														// new without
														// refreshment
					{ // System.out.println("hey inside the if for the fpo_id");
						// getting fpo_id,huma_id,country_id to assign in their
						// fields automatically in enable() function
						int x2 = 0;// String
									// curhuma_id="",curfpoid="",curcountryid="";
						ps2 = con
								.prepareStatement("select 'PO'||nvl((select to_char(to_number(substr(fpo_id,3)+1),'FM00000') from fpo_mstr where  fpo_id=(select max(fpo_id) from fpo_mstr)),'00001') as fpo_id from dual");
						rs2 = ps2.executeQuery();
						if ((rs2.next()) == false)
							x2 = x2 + 1; // System.out.println("hey after the next on resultset and string="+rs1.getString(1));
						if (x2 == 0)// entered field exists in huma_mstr
							data = rs2.getString(1);
					}// else if(decide=="fpo_id")
					else if (decide.equals("fpo_mstr"))// querying for the
														// fpo_mstr data
					{
						if ((key1.length() >= 7))
							key1 = key1.substring(0, 7);// key1=fpo_id
						request.getParameter("key3").toString();
						/*
						 * String key4 =
						 * request.getParameter("key4").toString();//
						 * key4=huma_id String key5 =
						 * request.getParameter("key5").toString();//
						 * key5=fpo_date
						 */
						key4 = request.getParameter("key4").toString();// key4=huma_id
						key5 = request.getParameter("key5").toString();// key5=fpo_date

						request.getParameter("key6").toString();
						// if((key3.length()>=7)) key3=key3.substring(0,7);
						if ((key4.length() >= 4))
							key4 = key4.substring(0, 4);
						ps = con.prepareStatement("select s.fpo_id,s.fpo_name,s.fpo_status,s.huma_id||' '||h.huma_fname||' '||h.huma_lname as huma_id,to_char(s.fpo_date,'dd-mm-yyyy'),(select s.village_id||' '||v.village_name from village_mstr v where v.village_id=s.village_id) as village_id,(select s.block_id||' '||b.block_name from block_mstr b where b.block_id=s.block_id) as block_id,(select s.subunit_id||' '||ss.subunit_name from subunit_mstr ss where ss.subunit_id=s.subunit_id) as subunit_id,s.fpo_membersCount,s.fpo_accessCount,s.fpo_membershipFee,s.fpo_shareCapital,s.fpo_savings,s.fpo_commodityCount,s.fpo_inputTradeVolume,s.fpo_outputTradeVolume,s.fpo_loanCapitalAccess,s.fpo_loanOrRevFundCapitalAccess,s.fpo_hrCount,s.fpo_hrCountWomen,s.fpo_hrCountTraining from fpo_mstr s,huma_mstr h where s.huma_id=h.huma_id and s.fpo_id=nvl('"
								+ key1
								+ "',s.fpo_id) and s.fpo_status=nvl('"
								+ key2
								+ "',s.fpo_status) and s.huma_id=nvl('"
								+ key4
								+ "',s.huma_id) and s.fpo_date=nvl(to_date('"
								+ key5
								+ "','dd-mm-yyyy'),s.fpo_date) order by s.fpo_id");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of fpo_mstr in getuserupdate");
						while (rs.next()) { // System.out.println("hey inside the while loop");
							String data2 = "";
							rs2 = null;

							ps2 = con
									.prepareStatement("select ACTIVITY_ID from FPOactivities_mstr where FPO_id='"
											+ rs.getString(1)
											+ "' order by FPOactivities_id");
							// System.out.println("Hey going to execute the data2 query");
							rs2 = ps2.executeQuery();
							while (rs2.next())
								data2 = data2 + rs2.getString(1) + "::::::"; // System.out.println("Hey just going to finilize data2 ");
							if (data2.length() > 5)
								data2 = data2.substring(0, data2.length() - 6);// System.out.println("hey the data2="+data2);

							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "::::::"
									+ rs.getString(12) + "::::::"
									+ rs.getString(13) + "::::::"
									+ rs.getString(14) + "::::::"
									+ rs.getString(15) + "::::::"
									+ rs.getString(16) + "::::::"
									+ rs.getString(17) + "::::::"
									+ rs.getString(18) + "::::::"
									+ rs.getString(19) + "::::::"
									+ rs.getString(20) + "::::::"
									+ rs.getString(21) + "::::::" + data2
									+ "//////";
						}// while(rs.next())
					}// else if(decide=="fpo_mstr")
						// --------fpo_mstr related code ends
						// here-----------------------------------------------------------------------------
					// --------pg_mstr related code starts
					// here---------------------------------------------------------------------------
					else if (decide.equals("fpo_id.pg_id")) { // System.out.println("hey inside the if for the fpo_id.pg_id");
						if (key1.length() >= 7)// if(fpo_id>=7)
							key1 = key1.substring(0, 7);// substring(0,5);
						ps2 = con
								.prepareStatement("select (select sum(p.pg_memberCount) from pg_mstr p where p.fpo_id=f.fpo_id) as fpo_memberCount,(select count(p.pg_memberCount) from pg_mstr p where p.fpo_id=f.fpo_id) as fpo_pgCount,(select count(p.estbl_id) from estbl_mstr p where p.fpo_id=f.fpo_id) as fpo_estblCount from fpo_mstr f where f.fpo_id=nvl('"
										+ key1 + "',f.fpo_id)");
						rs2 = ps2.executeQuery(); // System.out.println("hey aftert execution of the query and going to make the data string");
						while (rs2.next())
							data = data + rs2.getString(1) + "::::::"
									+ rs2.getString(2) + "::::::"
									+ rs2.getString(3);
						// System.out.println("the assignment of data string happened");
					}// if(decide=="fpo_id.pg_id")
					else if (decide.equals("pg_id"))// new pg_id when click on
													// new without refreshment
					{ // System.out.println("hey inside the if for the pg_id");
						// getting pg_id,huma_id,country_id to assign in their
						// fields automatically in enable() function
						int x2 = 0;// String
									// curhuma_id="",curfpoid="",curcountryid="";
						ps2 = con
								.prepareStatement("select 'PG'||nvl((select to_char(to_number(substr(pg_id,3)+1),'FM0000000') from pg_mstr where  pg_id=(select max(pg_id) from pg_mstr)),'0000001') as pg_id from dual");
						rs2 = ps2.executeQuery();
						if ((rs2.next()) == false)
							x2 = x2 + 1; // System.out.println("hey after the next on resultset and string="+rs1.getString(1));
						if (x2 == 0)// entered field exists in huma_mstr
							data = rs2.getString(1);
					}// else if(decide=="pg_id")
					else if (decide.equals("pg_mstr"))// querying for the
														// pg_mstr data
					{
						if ((key1.length() >= 7))
							key1 = key1.substring(0, 7);// key1=fpo_id //key2=''
						/*
						 * String key3 =
						 * request.getParameter("key3").toString();// key3=pg_id
						 * String key4 =
						 * request.getParameter("key4").toString();//
						 * key4=huma_id String key5 =
						 * request.getParameter("key5").toString();//
						 * key5=pg_date
						 */
						key3 = request.getParameter("key3").toString();// key3=pg_id
						key4 = request.getParameter("key4").toString();// key4=huma_id
						key5 = request.getParameter("key5").toString();// key5=pg_date

						request.getParameter("key6").toString();
						if ((key3.length() >= 9))
							key3 = key3.substring(0, 9);
						if ((key4.length() >= 4))
							key4 = key4.substring(0, 4);

						ps = con.prepareStatement("select (select s.fpo_id||' '||d.fpo_name from fpo_mstr d where s.fpo_id=d.fpo_id) as fpo_id,(select sum(p.pg_memberCount) from pg_mstr p where p.fpo_id=s.fpo_id) as fpo_memberCount,(select count(p.pg_memberCount) from pg_mstr p where p.fpo_id=s.fpo_id) as fpo_pgCount,s.pg_id,s.pg_name,s.huma_id||' '||h.huma_fname||' '||h.huma_lname as huma_id,to_char(s.pg_date,'dd-mm-yyyy'),(select s.village_id||' '||v.village_name from village_mstr v where v.village_id=s.village_id) as village_id,(select s.block_id||' '||b.block_name from block_mstr b where b.block_id=s.block_id) as block_id,(select s.subunit_id||' '||ss.subunit_name from subunit_mstr ss where ss.subunit_id=s.subunit_id) as subunit_id,s.pg_totalLand,s.pg_hamlet,s.pg_bankAccno,s.pg_bankName,s.pg_monthlySavings,s.pg_memberCount,s.pg_memberScCount,s.pg_memberStCount,s.pg_memberMinorityCount,s.pg_memberObcCount,s.pg_memberGenCount,s.pg_memberCountWomen,s.pg_memberCountTraining,s.pg_marketLinkage,s.pg_rating,s.pg_remarks from pg_mstr s,huma_mstr h where s.huma_id=h.huma_id and s.pg_id=nvl('"
								+ key3
								+ "',s.pg_id) and (s.fpo_id=nvl('"
								+ key1
								+ "',s.fpo_id) or (s.fpo_id is null and '"
								+ key1
								+ "' is null)) and s.huma_id=nvl('"
								+ key4
								+ "',s.huma_id) and s.pg_date=nvl(to_date('"
								+ key5
								+ "','dd-mm-yyyy'),s.pg_date) order by s.pg_id,s.fpo_id");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of pg_mstr in getuserupdate");
						while (rs.next()) { // System.out.println("hey inside the while loop");
							String data2 = "";
							rs2 = null;

							ps2 = con
									.prepareStatement("select ACTIVITY_ID from PGactivities_mstr where PG_id='"
											+ rs.getString(4)
											+ "' order by PGactivities_id");
							// System.out.println("Hey going to execute the data2 query");
							rs2 = ps2.executeQuery();
							while (rs2.next())
								data2 = data2 + rs2.getString(1) + "::::::"; // System.out.println("Hey just going to finilize data2 ");
							if (data2.length() > 5)
								data2 = data2.substring(0, data2.length() - 6);// System.out.println("hey the data2="+data2);

							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "::::::"
									+ rs.getString(12) + "::::::"
									+ rs.getString(13) + "::::::"
									+ rs.getString(14) + "::::::"
									+ rs.getString(15) + "::::::"
									+ rs.getString(16) + "::::::"
									+ rs.getString(17) + "::::::"
									+ rs.getString(18) + "::::::"
									+ rs.getString(19) + "::::::"
									+ rs.getString(20) + "::::::"
									+ rs.getString(21) + "::::::"
									+ rs.getString(22) + "::::::"
									+ rs.getString(23) + "::::::"
									+ rs.getString(24) + "::::::"
									+ rs.getString(25) + "::::::"
									+ rs.getString(26) + "::::::" + data2
									+ "//////";
						}// while(rs.next())
					}// else if(decide=="pg_mstr")
						// --------pg_mstr related code ends
						// here-----------------------------------------------------------------------------
					// --------estbl_mstr related code starts
					// here---------------------------------------------------------------------------
					else if (decide.equals("estbl_id"))// new estbl_id when
														// click on new without
														// refreshment
					{ // System.out.println("hey inside the if for the estbl_id");
						// getting estbl_id,huma_id,country_id to assign in
						// their fields automatically in enable() function
						int x2 = 0;// String
									// curhuma_id="",curfpoid="",curcountryid="";
						ps2 = con
								.prepareStatement("select 'E'||nvl((select to_char(to_number(substr(estbl_id,3)+1),'FM000000') from estbl_mstr where  estbl_id=(select max(estbl_id) from estbl_mstr)),'000001') as estbl_id from dual");
						rs2 = ps2.executeQuery();
						if ((rs2.next()) == false)
							x2 = x2 + 1; // System.out.println("hey after the next on resultset and string="+rs1.getString(1));
						if (x2 == 0)// entered field exists in huma_mstr
							data = rs2.getString(1);
					}// else if(decide=="estbl_id")
					else if (decide.equals("estbl_mstr"))// querying for the
															// estbl_mstr data
					{
						if ((key1.length() >= 7))
							key1 = key1.substring(0, 7);// key1=fpo_id //key2=''
						/*
						 * String key3 =
						 * request.getParameter("key3").toString();//
						 * key3=estbl_id String key4 =
						 * request.getParameter("key4").toString();//
						 * key4=huma_id String key5 =
						 * request.getParameter("key5").toString();//
						 * key5=estbl_date
						 */
						key3 = request.getParameter("key3").toString();// key3=estbl_id
						key4 = request.getParameter("key4").toString();// key4=huma_id
						key5 = request.getParameter("key5").toString();// key5=estbl_date

						request.getParameter("key6").toString();
						if ((key3.length() >= 7))
							key3 = key3.substring(0, 7);
						if ((key4.length() >= 4))
							key4 = key4.substring(0, 4);

						ps = con.prepareStatement("select (select s.fpo_id||' '||d.fpo_name from fpo_mstr d where s.fpo_id=d.fpo_id) as fpo_id,(select sum(p.pg_memberCount) from pg_mstr p where p.fpo_id=s.fpo_id) as fpo_memberCount,(select count(p.pg_memberCount) from pg_mstr p where p.fpo_id=s.fpo_id) as fpo_estblCount,(select count(p.estbl_id) from estbl_mstr p where p.fpo_id=s.fpo_id) as fpo_estblCount,s.estbl_id,s.estbl_name,s.huma_id||' '||h.huma_fname||' '||h.huma_lname as huma_id,to_char(s.estbl_date,'dd-mm-yyyy'),(select s.village_id||' '||v.village_name from village_mstr v where v.village_id=s.village_id) as village_id,(select s.block_id||' '||b.block_name from block_mstr b where b.block_id=s.block_id) as block_id,(select s.subunit_id||' '||ss.subunit_name from subunit_mstr ss where ss.subunit_id=s.subunit_id) as subunit_id,s.estbl_type,s.estbl_address,s.estbl_contactPerson,s.estbl_rates,s.estbl_termsConditions,s.estbl_feedback from estbl_mstr s,huma_mstr h where s.huma_id=h.huma_id and s.estbl_id=nvl('"
								+ key3
								+ "',s.estbl_id) and (s.fpo_id=nvl('"
								+ key1
								+ "',s.fpo_id) or (s.fpo_id is null and '"
								+ key1
								+ "' is null)) and s.huma_id=nvl('"
								+ key4
								+ "',s.huma_id) and s.estbl_date=nvl(to_date('"
								+ key5
								+ "','dd-mm-yyyy'),s.estbl_date) order by s.estbl_id,s.fpo_id");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop of estbl_mstr in getuserupdate");
						while (rs.next()) { // System.out.println("hey inside the while loop");
							String data2 = "";
							rs2 = null;

							ps2 = con
									.prepareStatement("select ACTIVITY_ID from ESTBLactivities_mstr where ESTBL_id='"
											+ rs.getString(5)
											+ "' order by ESTBLactivities_id");
							// System.out.println("Hey going to execute the data2 query");
							rs2 = ps2.executeQuery();
							while (rs2.next())
								data2 = data2 + rs2.getString(1) + "::::::"; // System.out.println("Hey just going to finilize data2 ");
							if (data2.length() > 5)
								data2 = data2.substring(0, data2.length() - 6);// System.out.println("hey the data2="+data2);

							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "::::::"
									+ rs.getString(12) + "::::::"
									+ rs.getString(13) + "::::::"
									+ rs.getString(14) + "::::::"
									+ rs.getString(15) + "::::::"
									+ rs.getString(16) + "::::::"
									+ rs.getString(17) + "::::::" + data2
									+ "//////";
						}// while(rs.next())
					}// else if(decide=="estbl_mstr")
					else if (decide.equals("unit_id.area_id.comp_id")) { // System.out.println("hey inside the if for the area_id.comp_id");
						if (key1.length() >= 3)// if(area_id>=4)
							// key1 = key1.substring(0, 3);// substring(0,4);
							key1 = key1.substring(key1.lastIndexOf("-") + 1);
						ps = con.prepareStatement("select b.area_name||'-'||b.area_id,c.comp_name||'-'||c.comp_id from comp_mstr c, area_mstr b,bsflunit_mstr u where c.comp_id=b.comp_id and b.area_id=u.area_id and u.bsflunit_ucode='"
								+ key1 + "'");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::";

					}// if(decide=="area_id.comp_id")

					// Added By Rajesh

					else if (decide.equals("transferedUnit")) { // System.out.println("hey inside the if for the area_id.comp_id");
						// if (key1.length() >= 3)// if(area_id>=4)
						// key1 = key1.substring(key1.lastIndexOf("-") + 1);
						// System.out
						// .println("Inside the transferedUnit by Rajesh ank key is "
						// + key1);
						ps = con.prepareStatement("select hrstatus_transfer from hrstatus_mstr where hrstatus_id="
								+ key1);
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::";
					}// if (decide.equals("transferedUnit"))
					else if (decide.equals("st_name")) { // System.out.println("hey inside the if for the area_id.comp_id");
						// if (key1.length() >= 3)// if(area_id>=4)
						// key1 = key1.substring(key1.lastIndexOf("-") + 1);
						// System.out
						// .println("Inside the transferedUnit by Rajesh ank key is "
						// + key1);
						ps = con.prepareStatement("select s_name from stationary_mstr where s_id="
								+ key1);
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::";
					}// if (decide.equals("transferedUnit"))
					else if (decide.equals("act_name")) { // System.out.println("hey inside the if for the area_id.comp_id");
						// if (key1.length() >= 3)// if(area_id>=4)
						// key1 = key1.substring(key1.lastIndexOf("-") + 1);
						// System.out
						// .println("Inside the transferedUnit by Rajesh ank key is "
						// + key1);
						ps = con.prepareStatement("select act_id from statehead_activities where act_id='"+key1+"'");
						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next())
							data = data + rs.getString(1) + "::::::";
						System.out.println("Data is"+data);
					}// if (decide.equals("transferedUnit"))

					else if (decide.equals("huma_mstr")) { // System.out.println("hey inside the if for the huma_mstr");
						// String key3="", key4="",key5="";
						key3 = request.getParameter("key3").toString();
						key4 = request.getParameter("key4").toString();
						key5 = request.getParameter("key5").toString();
						key6 = request.getParameter("key6").toString();
						key7 = request.getParameter("key7").toString();
						key1 = key1.substring(key1.lastIndexOf('-') + 1);// huma_id
						key2 = key2.substring(key2.lastIndexOf('-') + 1);// unit_id
						key3 = key3.substring(key3.lastIndexOf('-') + 1);// area_id
						key4 = key4.substring(key4.lastIndexOf('-') + 1);// Grade
						key5 = key5.substring(key5.lastIndexOf('-') + 1);// Reporting_Officer
						// System.out.println("Key1=" + key1 + " key2=" + key2+
						// " key3=" + key3+ " key4=" + key4+ " key5=" + key5);
						/*
						 * ps = con.prepareStatement(
						 * "select (select comp_name||'-'||comp_id from comp_mstr where comp_id=(select comp_id from area_mstr where area_id=(select area_id from bsflunit_mstr where bsflunit_ucode=h.bsflunit_ucode))) as comp_id,(select area_name||'-'||area_id from area_mstr where area_id=(select area_id from bsflunit_mstr where bsflunit_ucode=h.bsflunit_ucode)) as area_id,huma_id,huma_fname,huma_lname,'NO' as huma_freeze,(select grade_abbreviation||'-'||grade_id from grade_mstr where grade_id=huma_designation) as huma_designation,(select h2.huma_fname||' '||h2.huma_lname||'-'||h2.huma_id from huma_mstr h2 where h2.huma_id=h.huma_reporting) as huma_reporting,(select city_name||'-'||city_id from city_mstr where city_id=h.city_id) as city_id,to_char(huma_dob,'dd-mm-yyyy') as huma_dob,to_char(huma_doj,'dd-mm-yyyy') as huma_doj,huma_address,huma_pin,huma_email,huma_phone,huma_mobile,HUMA_STATUS,(select bsflunit_name||'-'||bsflunit_ucode from bsflunit_mstr where bsflunit_ucode=h.bsflunit_ucode) as bsflunit_UCODE from huma_mstr h where h.huma_id=nvl('"
						 * + key1 + "',h.huma_id) and h.bsflunit_ucode=nvl('" +
						 * key2 + "',h.bsflunit_ucode) order by h.huma_id");
						 */
						// ps =
						// con.prepareStatement("select (select comp_name||'-'||comp_id from comp_mstr where comp_id=(select comp_id from area_mstr where area_id=(select area_id from bsflunit_mstr where bsflunit_ucode=h.bsflunit_ucode))) as comp_id,(select area_name||'-'||area_id from area_mstr where area_id=(select area_id from bsflunit_mstr where bsflunit_ucode=h.bsflunit_ucode)) as area_id,h.huma_id,h.huma_fname,h.huma_lname,'NO' as huma_freeze,(select grade_abbreviation||'-'||grade_id from grade_mstr where grade_id=h.huma_designation) as huma_designation,(select h2.huma_fname||' '||h2.huma_lname||'-'||h2.huma_id from huma_mstr h2 where h2.huma_id=h.huma_reporting) as huma_reporting,(select city_name||'-'||city_id from city_mstr where city_id=h.city_id) as city_id,to_char(h.huma_dob,'dd-mm-yyyy') as huma_dob,to_char(h.huma_doj,'dd-mm-yyyy') as huma_doj,h.huma_address,h.huma_pin,h.huma_email,h.huma_phone,h.huma_mobile,h.HUMA_STATUS,(select bsflunit_name||'-'||bsflunit_ucode from bsflunit_mstr where bsflunit_ucode=h.bsflunit_ucode) as bsflunit_UCODE from huma_mstr h,bsflunit_mstr u,area_mstr a where h.bsflunit_ucode=u.bsflunit_ucode and u.area_id=a.area_id and h.huma_id=nvl('',h.huma_id) and h.bsflunit_ucode=nvl('',h.bsflunit_ucode) and h.huma_designation=nvl('',h.huma_designation) and a.area_id=nvl('',a.area_id) and h.huma_reporting=nvl('',h.huma_reporting) order by h.huma_id");
						/*
						 * ps = con.prepareStatement(
						 * "select (select comp_name||'-'||comp_id from comp_mstr where comp_id=(select comp_id from area_mstr where area_id=(select area_id from bsflunit_mstr where bsflunit_ucode=h.bsflunit_ucode))) as comp_id,(select area_name||'-'||area_id from area_mstr where area_id=(select area_id from bsflunit_mstr where bsflunit_ucode=h.bsflunit_ucode)) as area_id,h.huma_id,h.huma_fname,h.huma_lname,'NO' as huma_freeze,(select grade_abbreviation||'-'||grade_id from grade_mstr where grade_id=h.huma_designation) as huma_designation,(select h2.huma_fname||' '||h2.huma_lname||'-'||h2.huma_id from huma_mstr h2 where h2.huma_id=h.huma_reporting) as huma_reporting,(select city_name||'-'||city_id from city_mstr where city_id=h.city_id) as city_id,to_char(h.huma_dob,'dd-mm-yyyy') as huma_dob,to_char(h.huma_doj,'dd-mm-yyyy') as huma_doj,h.huma_address,h.huma_pin,h.huma_email,h.huma_phone,h.huma_mobile,h.HUMA_STATUS,(select bsflunit_name||'-'||bsflunit_ucode from bsflunit_mstr where bsflunit_ucode=h.bsflunit_ucode) as bsflunit_UCODE from huma_mstr h,bsflunit_mstr u,area_mstr a "
						 * +
						 * "where h.bsflunit_ucode=u.bsflunit_ucode and u.area_id=a.area_id and "
						 * + "h.huma_id=nvl('"+key1+"',h.huma_id) and " +
						 * "h.bsflunit_ucode=nvl('"
						 * +key2+"',h.bsflunit_ucode) and " +
						 * "h.huma_designation=nvl('"
						 * +key4+"',h.huma_designation) and " +
						 * "a.area_id=nvl('"+key3+"',a.area_id) and " +
						 * "h.huma_reporting=nvl('"
						 * +key5+"',h.huma_reporting) order by h.huma_id");
						 */

						// By rajesh Query to fetch the Records even if some Key
						// fields having null values
						ps = con.prepareStatement("select (select comp_name||'-'||comp_id from comp_mstr where comp_id=(select comp_id from area_mstr where area_id=(select area_id from bsflunit_mstr where bsflunit_ucode=h.bsflunit_ucode))) as comp_id,(select area_name||'-'||area_id from area_mstr where area_id=(select area_id from bsflunit_mstr where bsflunit_ucode=h.bsflunit_ucode)) as area_id,h.huma_id,h.huma_fname,h.huma_lname,'NO' as huma_freeze,(select grade_abbreviation||'-'||grade_id from grade_mstr where grade_id=h.huma_designation) as huma_designation,(select h2.huma_fname||' '||h2.huma_lname||'-'||h2.huma_id from huma_mstr h2 where h2.huma_id=h.huma_reporting) as huma_reporting,(select city_name||'-'||city_id from city_mstr where city_id=h.city_id) as city_id,to_char(h.huma_dob,'dd-mm-yyyy') as huma_dob,to_char(h.huma_doj,'dd-mm-yyyy') as huma_doj,h.huma_address,h.huma_pin,h.huma_email,h.huma_phone,h.huma_mobile,h.HUMA_STATUS,(select bsflunit_name||'-'||bsflunit_ucode from bsflunit_mstr where bsflunit_ucode=h.bsflunit_ucode) as bsflunit_UCODE from huma_mstr h,bsflunit_mstr u,area_mstr a "
								+ "where h.bsflunit_ucode=u.bsflunit_ucode and u.area_id=a.area_id and "
								+ "h.huma_id=nvl('"
								+ key1
								+ "',h.huma_id) and "
								+ "(h.bsflunit_ucode=nvl('"
								+ key2
								+ "',h.bsflunit_ucode) or (h.bsflunit_ucode is null and '"
								+ key2
								+ "' is null)) and "
								+
								// "h.bsflunit_ucode=nvl('"+key2+"',h.bsflunit_ucode) and "
								// +
								"h.huma_designation=nvl('"
								+ key4
								+ "',h.huma_designation) and "
								+ "a.area_id=nvl('"
								+ key3
								+ "',a.area_id) and "
								+ "(h.huma_reporting=nvl('"
								+ key5
								+ "',h.huma_reporting) or (h.huma_reporting is null and '"
								+ key5
								+ "' is null)) and "
								+ "(h.HUMA_STATUS=nvl('"
								+ key6
								+ "',h.HUMA_STATUS) or (h.HUMA_STATUS is null and '"
								+ key6
								+ "' is null)) "
								+
								// h.HUMA_STATUS
								" and h.huma_id in nvl2('"
								+ key7
								+ "',(select distinct huma_ID from hrstatusrev_tab rev1 where ((rev1.HRSTATUS_ID=nvl('"
								+ key7
								+ "',rev1.HRSTATUS_ID) or (rev1.HRSTATUS_ID is null and '"
								+ key7
								+ "' is null))) and hrstatusrev_effectdate=(select max(hrstatusrev_effectdate) from hrstatusrev_tab rev2 where rev2.huma_id=rev1.huma_id and rev2.huma_id=h.huma_id)),h.huma_id) "
								+ "order by h.huma_id");
						// "h.huma_reporting=nvl('"+key5+"',h.huma_reporting) order by h.huma_id");

						rs = ps.executeQuery();// System.out.println("Hey going for while loop and key1="+key1);
						ps2 = con
								.prepareStatement("select HRSTATUSREV_ID,"
										+ "HRSTATUS_ID,"
										+ "to_char(HRSTATUSREV_EFFECTDATE,'dd-mm-yyyy'),"
										+ "HRSTATUSREV_DESCRIPTION,"
										+ "HRSTATUSREV_SEQID,HRSTATUSREV_CURRENT_UNIT,HRSTATUSREV_TRANSFERED_UNIT from HRSTATUSREV_TAB where HUMA_ID=? "
										// + " and (HRSTATUS_ID=nvl('"
										// + key7
										// +
										// "',HRSTATUS_ID) or (HRSTATUS_ID is null and '"
										// + key7 + "' is null))"
										+ "order by HRSTATUSREV_ID");

						while (rs.next()) {
							// Added by Rajesh
							String data2 = "";
							System.out.println(" in huma_mstr huma_id= "+rs.getString(3));
							// String huma_id = rs.getString(3);
							rs2 = null;
							ps2.setString(1, rs.getString(3));
							rs2 = ps2.executeQuery();
							while (rs2.next())
								data2 = data2 + rs2.getString(1) + "::::::"
										+ rs2.getString(2) + "::::::"
										+ rs2.getString(3) + "::::::"
										+ rs2.getString(4) + "::::::"
										+ rs2.getString(5) + "::::::"
										+ rs2.getString(6) + "::::::"
										+ rs2.getString(7) + "::::::";
							System.out.println(data2.length());
							if (data2.length() > 7)
								data2 = data2.substring(0, data2.length() - 6);// System.out.println("hey the data2="+data2);
                            
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "::::::"
									+ rs.getString(11) + "::::::"
									+ rs.getString(12) + "::::::"
									+ rs.getString(13) + "::::::"
									+ rs.getString(14) + "::::::"
									+ rs.getString(15) + "::::::"
									+ rs.getString(16) + "::::::"
									+ rs.getString(17) + "::::::" // Added by Rajesh for
												// Huma_status
									+ rs.getString(18) + "::::::" + data2
									+ "//////";
									System.out.println(data);
							// + rs.getString(17) + "//////"; Hide By Rajesh
						} // Added by Rajesh
					}// else if(decide=="huma_mstr")
						// Added By Rajesh
					else if (decide.equals("unit_indent")) { // System.out.println("hey inside the if for the huma_mstr");
						
						key1 = key1.substring(key1.lastIndexOf('-') + 1);// huma_id
						System.out.println("key1 = "+key1);
						ps = con.prepareStatement("select i.controlno, (select bsflunit_ucode from bsflunit_mstr where bsflunit_ucode=i.bsflunit_ucode) unit_code,(select bsflunit_name from bsflunit_mstr where bsflunit_ucode=i.bsflunit_ucode) unit_name,to_char(i.date_of_indent,'dd-mm-yyyy'),i.in_by,i.pre_by,(select s_id from stationary_mstr where s_id=i.s_id) statId,(select s_name from stationary_mstr where s_id=i.s_id)sttNAme,i.clo_stock,i.new_stock from stockindent i,bsflunit_mstr a where i.bsflunit_ucode=A.BSFLUNIT_UCODE and i.bsflunit_ucode='"+key1+"'"); 
						rs = ps.executeQuery();
						//System.out.println("Hey going for while loop and key1="+key1);
						while (rs.next()) {
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::"
									+ rs.getString(9) + "::::::"
									+ rs.getString(10) + "//////";
									//+ rs.getString(11) + "::::::"
									//+ rs.getString(12) + "//////";
							System.out.println(data);

						}// while(// + rs.getString(17) + "//////"; Hide By Rajesh
						 // Added by Rajesh
					}// else if(decide=="huma_mstr")

					if (decide.equals("huma_dtl")) { // System.out.println("hey inside the if for the huma_dtl");

						// if (key1.length() >= 4)// if(comp_id2!="")
						// key1 = key1.substring(0, 4);// substring(0,4);
						key1 = key1.substring(key1.lastIndexOf('-') + 1);
						// System.out.println("For Testing huma_id="+key1);
						ps = con.prepareStatement("select HUMA_FNAME||' '||huma_lname,HUMA_MOBILE,HUMA_ID from HUMA_MSTR where HUMA_ID='"
								+ key1 + "'");

						rs = ps.executeQuery();// System.out.println("Hey going for while loop");
						/*
						 * if ((rs.next()) == false) { System.out.println(
						 * "Tested by Rajesh In huma_dtl and the rs is null");
						 * out.println(
						 * "Entered huma_id is not exist. Please entered exist Employee ID"
						 * ); }
						 */
						while (rs.next()) { // //System.out.println("inside the main while loop");
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "//////";
							// //System.out.println("inside the while loop,the data is ="+data);
						}// while
					}

					// Added by Rajesh
					if (decide.equals("frs_usr_dtls")) { // System.out.println("hey inside the if for the frs_usr_dtls and key1="+key1.substring(key1.lastIndexOf('-')+1));

						key2 = key2.substring(key2.lastIndexOf('-') + 1);
						key3 = request.getParameter("key3").toString(); // role
						key4 = request.getParameter("key4").toString(); // freeze
						// System.out.println("key1="+key1+" key2="+key2+" key3="+key3+" key4="+key4);
						/*
						 * ps = con.prepareStatement(
						 * "select HUMA_ID,(select HUMA_FNAME||' '||huma_lname from huma_mstr where huma_id=u.huma_id) as NAME,(select HUMA_MOBILE from huma_mstr where huma_id=u.huma_id) as MOBILENO,USERNAME,USERROLE,PASSWORD,USER_FREEZE,USER_SEQID from FRS_USER u where USERNAME=nvl('"
						 * + key1.substring(key1.lastIndexOf('-') + 1) +
						 * "',username) and (huma_id=nvl('" + key2 +
						 * "',huma_id) or (huma_id is null and '" + key2 +
						 * "' is null)) order by huma_id,username");
						 */

						ps = con.prepareStatement("select HUMA_ID,(select HUMA_FNAME||' '||huma_lname from huma_mstr where huma_id=u.huma_id) as NAME,(select HUMA_MOBILE from huma_mstr where huma_id=u.huma_id) as MOBILENO,USERNAME,USERROLE,PASSWORD,USER_FREEZE,USER_SEQID from FRS_USER u where  USERNAME=nvl('"
								+ key1.substring(key1.lastIndexOf('-') + 1)
								+ "',username) and (USERROLE=nvl('"
								+ key3
								+ "',USERROLE)) and(USER_FREEZE=nvl('"
								+ key4
								+ "',USER_FREEZE)) and (huma_id=nvl('"
								+ key2
								+ "',huma_id) or (huma_id is null and '"
								+ key2
								+ "' is null)) order by huma_id,username");

						rs = ps.executeQuery();// System.out.println("Hey going for while loop");
						while (rs.next()) { // //System.out.println("inside the main while loop");
							data = data + rs.getString(1) + "::::::"
									+ rs.getString(2) + "::::::"
									+ rs.getString(3) + "::::::"
									+ rs.getString(4) + "::::::"
									+ rs.getString(5) + "::::::"
									+ rs.getString(6) + "::::::"
									+ rs.getString(7) + "::::::"
									+ rs.getString(8) + "::::::" + "//////";
							// //System.out.println("inside the while loop,the data is ="+data);
						}// while
					}// else if("frs_usr_details")
						// --------estbl_mstr related code ends
						// here-----------------------------------------------------------------------------

					// =======UPBSN module related code Ends
					// here=================================================================================================

					data = data.replace("null", "");
					out.println(data);// mandatery...here works as return
										// responseObject;s
					// System.out.println("the responce data is :"+data);
				}// try
				catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (con != null)
							con.close();

						if (rs != null)
							rs.close();
						if (st != null)
							st.close();
						if (ps != null)
							ps.close();
						if (rs1 != null)
							rs1.close();
						if (st1 != null)
							st1.close();
						if (ps1 != null)
							ps1.close();
						if (rs2 != null)
							rs2.close();
						if (st2 != null)
							st2.close();
						if (ps2 != null)
							ps2.close();
						if (rs3 != null)
							rs3.close();
						if (st3 != null)
							st3.close();
						if (ps3 != null)
							ps3.close();
						if (rs4 != null)
							rs4.close();
						if (st4 != null)
							st4.close();
						if (ps4 != null)
							ps4.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}// finally

			}// authorised acess else
				// //------------------------------------------------------------------------------------

		} catch (Throwable t) {
			if (!(t instanceof SkipPageException)) {
				out = _jspx_out;
				if (out != null && out.getBufferSize() != 0)
					out.clearBuffer();
				if (_jspx_page_context != null)
					_jspx_page_context.handlePageException(t);
			}
		} finally {
			if (_jspxFactory != null)
				_jspxFactory.releasePageContext(_jspx_page_context);
		}
	}

	public String currenceyformat(long amt) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale(
				"en", "IN"));
		String moneyString = formatter.format(amt);
		moneyString = moneyString.substring(3);// " \\-";
		return moneyString;
	}

}