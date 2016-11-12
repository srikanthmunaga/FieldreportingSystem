package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletConfig;
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

public final class Uhm extends HttpServlet {

	Connection con = null;// , con2 = null, con3 = null, con4 = null;
	PreparedStatement ps = null, ps1 = null, ps2 = null, ps3 = null,
			ps4 = null, ps5 = null, ps6 = null, ps10 = null, ps11 = null,
			ps44 = null, ps15 = null;
	ResultSet rs = null, rs1 = null, rs2 = null, rs4 = null, rs5 = null,
			rs6 = null, rs10 = null, rs11 = null, rs15 = null;
	int f = 0, f10 = 0, f11 = 0, f3 = 0, f4 = 0, prelength = 0, curlength = 0,
			errcode = 0;
	boolean f44 = false;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// //System.out.println("inside the doGet method");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		////System.out.println("inside the doPost method of Uhm.java");

		JspFactory _jspxFactory = null;
		PageContext pageContext = null;
		HttpSession session = null;
		ServletContext application = null;
		ServletConfig config = null;
		JspWriter out = null;
		Object page = this;
		JspWriter _jspx_out = null;
		PageContext _jspx_page_context = null;
		String username = (String) request.getSession()
				.getAttribute("username");

		try {
			_jspxFactory = JspFactory.getDefaultFactory();
			response.setContentType("text/html");
			pageContext = _jspxFactory.getPageContext(this, request, response,
					"Error.jsp", true, 8192, true);
			_jspx_page_context = pageContext;
			application = pageContext.getServletContext();
			config = pageContext.getServletConfig();
			session = pageContext.getSession();
			out = pageContext.getOut();
			_jspx_out = out;
			String ur = (String) request.getSession().getAttribute("userrole");

			if (((HttpServletRequest) request).getSession().getAttribute(
					"username") == null) {
				response.sendRedirect("frslogin.jsp"); // Not logged in,
														// redirect to
														// login page.
			} else if (ur.equals("user") || ur.equals("fs") || ur.equals("fx")) {
				out.print("You are not authorised to Modify the HR Details"); // login
																				// page.
			}

			else // if (((HttpServletRequest)
					// request).getSession().getAttribute("user") != null)
			{
				// chain.doFilter(request, response); // Logged in, so just
				// continue.

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

				String driver = application.getInitParameter("driver");
				String url = application.getInitParameter("url");
				String user = application.getInitParameter("user");
				String pwd = application.getInitParameter("pwd");
				Calendar ca1 = Calendar.getInstance();// from here four lines
														// are the system date
														// selection code
				int dd = ca1.get(Calendar.DATE);
				int mm = ca1.get(Calendar.MONTH) + 1; // In Current date Add 1
														// in month
				int yyyy = ca1.get(Calendar.YEAR);
				String huma_mdate = dd + "-" + mm + "-" + yyyy;
				Integer status_value = 0;
				String check_currentunitID = null;
				String check_transferunitID = null;
				String Change_Unit = null;
				String Change_AreaID = null;
				String Change_CompID = null;

				//System.out.println("Collecting the Huma_mstr related datas");
				String unit_id = request.getParameter("unit_id").trim();
				String area_id = request.getParameter("area_id").trim();
				String comp_id = request.getParameter("comp_id").trim();
				String huma_id = request.getParameter("huma_id").trim();
				String huma_fname = request.getParameter("huma_fname").trim();
				String huma_lname = request.getParameter("huma_lname").trim();
				String huma_freeze = request.getParameter("huma_freeze");
				String huma_designation = request.getParameter(
						"huma_designation").trim();
				String huma_doj = request.getParameter("huma_doj").trim();
				String huma_dob = request.getParameter("huma_dob").trim();
				String huma_reporting = request.getParameter("huma_reporting")
						.trim();
				String city_id = request.getParameter("city_id").trim();
				String huma_address = request.getParameter("huma_address")
						.trim();
				String huma_pin = request.getParameter("huma_pin").trim();
				String huma_email = request.getParameter("huma_email").trim();
				String huma_phone = request.getParameter("huma_phone").trim();
				String huma_mobile = request.getParameter("huma_mobile").trim();
				String huma_photo = request.getParameter("huma_photo");
				String HR_Status = request.getParameter("HR_Status").trim();
				String curhuma_id = (String) ((HttpServletRequest) request)
						.getSession().getAttribute("username");

				unit_id = unit_id.substring(unit_id.lastIndexOf("-") + 1);
				area_id = area_id.substring(area_id.lastIndexOf("-") + 1);
				comp_id = comp_id.substring(comp_id.lastIndexOf("-") + 1);
				huma_designation = huma_designation.substring(huma_designation
						.lastIndexOf("-") + 1);
				huma_reporting = huma_reporting.substring(huma_reporting
						.lastIndexOf("-") + 1);
				city_id = city_id.substring(city_id.lastIndexOf("-") + 1);
				// Added by Rajesh
				//System.out.println("Collecting the Dynamic row values");
				String sno[] = request.getParameterValues("sno");
				String status[] = request.getParameterValues("status");
				String effectdate[] = request.getParameterValues("effectdate");
				String transferedunit[] = request
						.getParameterValues("transferedunit");
				String currentunit[] = request
						.getParameterValues("currentunit");
				String Description[] = request
						.getParameterValues("Description");
				String seqid[] = request.getParameterValues("seqid");

				// System.out.println("Checked By rajesh in HR Master");
				/*System.out.println("Sno= " + sno + " status= " + status
						+ " Effect Date= " + effectdate + " Description= "
						+ Description);*/
				try {
				for (int i = 0; i < sno.length; i++) {
					////System.out.println("inside for loop and");
					sno[i] = sno[i].trim();
					status[i] = status[i].trim();
					effectdate[i] = effectdate[i].trim();
					transferedunit[i] = transferedunit[i].trim();
					currentunit[i] = currentunit[i].trim();
					Description[i] = Description[i].trim();
					// Integer stat = status[i].
					/*
					 * Integer stat = Integer.parseInt(status[i]);
					 * System.out.println("Status value ="+stat); if(stat == 1)
					 * { String cur_unit =
					 * currentunit[i].substring(currentunit[i].lastIndexOf("-")
					 * + 1); String tra_unit =
					 * transferedunit[i].substring(transferedunit
					 * [i].lastIndexOf("-") + 1); if(cur_unit == tra_unit) {
					 * out.println(
					 * "Please Check!\nCurrent unit and Transfered Unit should not be Same"
					 * ); } else {
					 * out.println("curunit and transunit are not same"); } }
					 * //if(stat == 1)
					 */
					////System.out.println("inside for loop and end");
				}
				// completed
				
					////System.out.println("inside try loop and");
					// Class.forName(driver);
					con = dbConn.getConnection();
					/*
					 * con2 = dbConn.getConnection(); con3 =
					 * dbConn.getConnection(); con4 = dbConn.getConnection();
					 * con2.setAutoCommit(false); con3.setAutoCommit(false);
					 */// con4.setAutoCommit(false);
						// con = DriverManager.getConnection(url,user,pwd);

					for (int i = 0; i < sno.length; i++) {
						// System.out.println("Sequence ID = "+seqid[i]);
						// ps10.setString(2,sno[i]);
					}

					int x = 0;
					ps = con.prepareStatement("select area_id from area_mstr where area_id=?");
					// ps.setString(1, area_id.substring(0, 4));
					ps.setString(1, area_id);
					rs = ps.executeQuery();
					// if (rs == null)
					// System.out.println("result set is null in spsm.jsp");
					if ((rs.next()) == false)
						x = x + 1; // System.out.println("hey the x="+x);
					if (x == 0) {
						// checking the availability of designation (grade_id)
						int x5 = 0;
						ps5 = con
								.prepareStatement("select grade_id from grade_mstr where grade_id=?");
						// ps5.setString(1, huma_designation.substring(0, 3));
						ps5.setString(1, huma_designation);
						rs5 = ps5.executeQuery();
						// if (rs5 == null)
						// System.out.println("result set is null in uhm.jsp");
						if ((rs5.next()) == false)
							x5 = x5 + 1; // System.out.println("hey the x5="+x5);
						if (x5 == 0) {
							// System.out.println("hey going to done the self reporting checking="+huma_id2.equals(huma_reporting.substring(0,4)));
							if (huma_id.equals(huma_reporting))// sefl
																// reporting
																// checking
																// if
								out.println("Self reporting is not possible");
							else// self reporting checking else
							{
								// ________________________
								int x2 = 0, x3 = 0, x6 = 0;
								// checking the availability of reporting
								// officer :x
								ps2 = con
										.prepareStatement("select huma_fname||'-'||huma_id from huma_mstr where huma_id=?");
								ps2.setString(1, huma_reporting);
								rs2 = ps2.executeQuery();
								if (rs2 == null)
									// System.out.println("result set is null");
									if ((rs2.next()) == false)
										x2 = x2 + 1;
								// checkin the city availability :x2
								ps4 = con
										.prepareStatement("select city_name||'-'||city_id from city_mstr where city_id=?");
								ps4.setString(1, city_id);
								rs4 = ps4.executeQuery();
								if (rs4 == null)
									System.out
											.println("result set4 is null in shm.jsp");
								if ((rs4.next()) == false)
									x3 = x3 + 1;
								// By Rajesh checking the entered unitId is
								// valid or not
								ps6 = con
										.prepareStatement("select bsflunit_NAME||'-'||bsflunit_UCODE from BSFLUNIT_MSTR where bsflunit_UCODE=?");
								ps6.setString(1, unit_id);
								rs6 = ps6.executeQuery();
								if (rs6 == null)
									// System.out.println("result set is null");
									if ((rs6.next()) == false)
										x6 = x6 + 1;
								/*System.out
										.println("Checking all the Conditions of x2, x3 and x6");
								System.out.println("x2=" + x2 + " x3=" + x3
										+ " x6=" + x6); */
								if ((x2 == 0) && (x3 == 0) && (x6 == 0)) {
									try {
										// Updating Huma_Mstr
										//System.out
											//	.println("Updating the huma_mstr related datas");
										ps1 = con
												.prepareStatement("update huma_mstr set huma_fname=?,huma_lname=?,huma_dob=to_date(?,'dd-mm-rrrr'),huma_address=?,huma_pin=?,huma_email=?,huma_phone=?,huma_mobile=?,huma_photo=?,huma_mdate=to_date(?,'dd-mm-rrrr'),huma_designation=?,huma_doj=to_date(?,'dd-mm-rrrr'),huma_reporting=?,city_id=?,huma_mby=?,bsflunit_UCODE=?,HUMA_STATUS=? where huma_id=?");
										ps1.setString(1, huma_fname);
										ps1.setString(2, huma_lname);
										ps1.setString(3, huma_dob);
										ps1.setString(4, huma_address);
										ps1.setString(5, huma_pin);
										ps1.setString(6, huma_email);
										ps1.setString(7, huma_phone);
										ps1.setString(8, huma_mobile);
										ps1.setString(9, huma_photo);
										ps1.setString(10, huma_mdate);
										ps1.setString(11, huma_designation);
										ps1.setString(12, huma_doj);
										ps1.setString(13, huma_reporting);
										ps1.setString(14, city_id);
										ps1.setString(15, curhuma_id);
										ps1.setString(16, unit_id);
										ps1.setString(17, HR_Status);
										ps1.setString(18, huma_id);
										f = ps1.executeUpdate();
										// Updating Huma_Mstr Completed

										// Calculating no. of Records in
										// HRSTATUSREV_TAB for the specified
										// muma_id
										ps2 = con
												.prepareStatement("select count(*) from HRSTATUSREV_TAB where huma_id='"
														+ huma_id + "'");
										rs2 = ps2.executeQuery();
										if (rs2 == null) {
											// System.out.println("result set2 is null in ucontm.jsp");
										}
										if (rs2.next()) {
											prelength = Integer.parseInt(rs2
													.getString(1));// System.out.println("the prelength="+prelength);
										}
										curlength = sno.length;
										// System.out.println("the curlength="+curlength);
										/*System.out.println("Prelength="
												+ prelength + " curlength="
												+ curlength);*/
										// No. of Records Calculating was
										// Completed

										//System.out
											//	.println("Updating the Status related datas");
										ps3 = con
												.prepareStatement("update hrstatusrev_tab set "
														+ "HRSTATUSREV_EFFECTDATE=to_date(?,'dd-mm-yyyy')"
														+ ",HRSTATUS_ID=?,"
														+ "HRSTATUSREV_MDATE=sysdate,"
														+ "HRSTATUSREV_MBY=?,"
														+
														// "HRSTATUSREV_SEQID=?,"
														// +
														"HRSTATUSREV_DESCRIPTION=?,"
														+ "HRSTATUSREV_CURRENT_UNIT=?,"
														+ "HRSTATUSREV_TRANSFERED_UNIT=? "
														+ "where huma_id=? "
														+ "and hrstatusrev_id=?");// updation
										// "where hrstatusrev_id=?");
										ps15 = con
												.prepareStatement("insert into HRSTATUSREV_TAB(huma_id,HRSTATUSREV_ID,"
														+ "HRSTATUS_ID,HRSTATUSREV_EFFECTDATE,HRSTATUSREV_CDATE,HRSTATUSREV_CBY,"
														+ "HRSTATUSREV_SEQID,HRSTATUSREV_DESCRIPTION,HRSTATUSREV_CURRENT_UNIT,HRSTATUSREV_TRANSFERED_UNIT) "
														+ "values (?,?,?,to_date(?,'dd-mm-yyyy'),sysdate,?,HRSTATUSREV_SEQID.nextval,?,?,?)");

										ps4 = con
												.prepareStatement("insert into HRSTATUSREV_TAB(huma_id,HRSTATUSREV_ID,"
														+ "HRSTATUS_ID,HRSTATUSREV_EFFECTDATE,HRSTATUSREV_CDATE,HRSTATUSREV_CBY,"
														+ "HRSTATUSREV_SEQID,HRSTATUSREV_DESCRIPTION,HRSTATUSREV_CURRENT_UNIT,HRSTATUSREV_TRANSFERED_UNIT) "
														+ "values (?,?,?,to_date(?,'dd-mm-yyyy'),sysdate,?,HRSTATUSREV_SEQID.nextval,?.?,?)");// insertion
										// Update Query Written By Rajesh
										ps10 = con
												.prepareStatement("update HRSTATUSREV_TAB hs set"
														+ "hs.HRSTATUS_ID=?,"
														+ "hs.HRSTATUSREV_EFFECTDATE=to_date(?,'dd-mm-yyyy'),"
														+ "hs.HRSTATUSREV_MDATE=SYSDATE"
														+ ",hs.HRSTATUSREV_MBY=?,"
														+ "hs.HRSTATUSREV_DESCRIPTION=?,"
														+ "hs.HRSTATUSREV_CURRENT_UNIT=?,"
														+ "hs.HRSTATUSREV_TRANSFERED_UNIT=?"
														+ "where (hs.huma_id='"
														+ huma_id
														+ "'"
														+ " and hs.hrstatusrev_id=?)");
										// System.out.println("ps10="+ps10.toString());
										// ps10=con.prepareStatement("update HRSTATUSREV_TAB SET "
										// +
										// "HRSTATUS_ID=1," +
										// "HRSTATUSREV_EFFECTDATE=to_date('12-12-2012','dd-mm-yyyy'),"
										// +
										// "HRSTATUSREV_MDATE=SYSDATE," +
										// "HRSTATUSREV_MBY='basix'," +
										// "HRSTATUSREV_DESCRIPTION='Executed from outside',"
										// +
										// "HRSTATUSREV_CURRENT_UNIT='966'," +
										// "HRSTATUSREV_TRANSFERED_UNIT='905' "
										// +
										// "where huma_id='E6065' " +
										// "and hrstatusrev_id='1'");

										ps44 = con
												.prepareStatement("delete from HRSTATUSREV_TAB where huma_id=? and hrstatusrev_id=?");// query
																																		// to
																																		// delete
																																		// some
																																		// rows
																																		// from
																																		// hrstatusrev_tab
										// ps44=
										// con.prepareStatement("delete from HRSTATUSREV_TAB where huma_id=?");//query
										// to delete some rows from
										// hrstatusrev_tab
										ps11 = con
												.prepareStatement("update huma_mstr set bsflunit_UCODE=? where huma_id=?");
										ps44.setString(1, huma_id);
										// ps44.setInt(2,i+1);//hrstatusrev_id[i]);
										// f44=ps44.execute();
										if (curlength == prelength) {
											//System.out
												//	.println("Setting the ps3 Parameters in prelen=curlength");
											for (int i = 0; i < curlength; i++) {
												ps3.setString(1, effectdate[i]);
												ps3.setString(2, status[i]);
												ps3.setString(3, username);
												ps3.setString(4, Description[i]);
												ps3.setString(5, currentunit[i]);
												ps3.setString(6,
														transferedunit[i]);
												ps3.setString(7, huma_id);
												ps3.setString(8, sno[i]);
												try {
													f10 = ps3.executeUpdate();
												} catch (SQLException e1) {
													// System.out.println("Exception occured in f10=ps3.executeUpdate() while curlen = prelen");
													errcode = 0;
													errcode = e1.getErrorCode();
													e1.printStackTrace();
												} catch (Exception e) {
													e.printStackTrace();
												}

												// If Transfered Update unitID,
												// AraeID and CompanyID in
												// Huma_mstr Added by Rajesh
												status_value = Integer
														.parseInt(status[i]);
												f11 = 1;
												if (status_value == 1) {
													// check_currentunitID =
													// currentunit[i].substring(currentunit[i].lastIndexOf("-")
													// + 1);
													check_transferunitID = transferedunit[i]
															.substring(transferedunit[i]
																	.lastIndexOf("-") + 1);
													if (!check_transferunitID
															.equals(unit_id)) {
														//System.out
															//	.println("Inside inner if block, updating the unit_id of huma_mstr ");

														Change_Unit = transferedunit[i]
																.substring(transferedunit[i]
																		.lastIndexOf("-") + 1);
														ps11.setString(1,
																Change_Unit);
														ps11.setString(2,
																huma_id);
														try {
															f11 = ps11
																	.executeUpdate();
														} catch (Exception e) {
															e.printStackTrace();
														}

													} // if(check_currentunitID.equals(unit_id))
												} // if(status_value == 1
											} // for
										}// if(curlength == prelength)
										else if (curlength > prelength) {
											// System.out.println("In the block where curlength > prelength");
											for (int i = 0; i < prelength; i++) {
												ps3.setString(1, effectdate[i]);
												ps3.setString(2, status[i]);
												ps3.setString(3, username);
												ps3.setString(4, Description[i]);
												ps3.setString(5, currentunit[i]);
												ps3.setString(6,
														transferedunit[i]);
												ps3.setString(7, huma_id);
												ps3.setString(8, sno[i]);
												// System.out.println("Before executing the Query");
												try {
													// f10=ps10.executeUpdate();
													f10 = ps3.executeUpdate();
												} catch (SQLException e1) {
													errcode = 0;
													errcode = e1.getErrorCode();
												} catch (Exception e) {
													e.printStackTrace();
												}
											} // for(int i=0; i<prelength; i++)
											for (int i = prelength; i < curlength; i++) {
												ps15.setString(1, huma_id);
												ps15.setString(2, sno[i]);
												ps15.setLong(3, Integer
														.parseInt(status[i]));
												ps15.setString(4, effectdate[i]);
												ps15.setString(5, username);
												ps15.setString(6,
														Description[i]);
												ps15.setString(
														7,
														currentunit[i]
																.substring(currentunit[i]
																		.lastIndexOf("-") + 1));
												ps15.setString(
														8,
														transferedunit[i]
																.substring(transferedunit[i]
																		.lastIndexOf("-") + 1));
												try {
													f10 = ps15.executeUpdate();
												} catch (SQLException e1) {
													errcode = 0;
													errcode = e1.getErrorCode();
												} catch (Exception e) {
													e.printStackTrace();
												}
											}
											for (int i = 0; i < curlength; i++) {
												status_value = Integer
														.parseInt(status[i]);
												f11 = 1;
												if (status_value == 1) {
													// check_currentunitID =
													// currentunit[i].substring(currentunit[i].lastIndexOf("-")
													// + 1);
													check_transferunitID = transferedunit[i]
															.substring(transferedunit[i]
																	.lastIndexOf("-") + 1);
													if (!check_transferunitID
															.equals(unit_id)) {
														//System.out
															//	.println("Inside inner if block, updating the unit_id of huma_mstr ");

														Change_Unit = transferedunit[i]
																.substring(transferedunit[i]
																		.lastIndexOf("-") + 1);
														ps11.setString(1,
																Change_Unit);
														ps11.setString(2,
																huma_id);
														try {
															f11 = ps11
																	.executeUpdate();
														} catch (Exception e) {
															e.printStackTrace();
														}

													} // if(check_currentunitID.equals(unit_id))
												} // if(status_value == 1

												// Completed
											}

										}// else if(curlength > prelength)

										else if (curlength < prelength) {
											// System.out.println("In the block where curlength < prelength");
											/*
											 * String qry =
											 * "UPDATE HRSTATUSREV_TAB SET HRSTATUSREV_EFFECTDATE=NULL WHERE HUMA_ID='"
											 * +huma_id+"'";
											 * System.out.println("The query is ="
											 * +qry); ps2 =
											 * con.prepareStatement(
											 * "UPDATE HRSTATUSREV_TAB SET HRSTATUSREV_EFFECTDATE=NULL WHERE HUMA_ID='"
											 * +"huma_id"+"'"); try{
											 * //f10=ps10.executeUpdate();
											 * ps2.executeUpdate();
											 * }catch(Exception e){
											 * System.out.println(
											 * "ERROR OCCURED WHEN SETTING EFFECT_DATE TO NULL"
											 * ); e.printStackTrace(); } for(int
											 * i=0; i<curlength; i++) {
											 * ps3.setString(1,effectdate[i]);
											 * ps3.setString(2,status[i]);
											 * ps3.setString(3,username);
											 * ps3.setString(4,Description[i]);
											 * ps3.setString(5,currentunit[i]);
											 * ps3
											 * .setString(6,transferedunit[i]);
											 * ps3.setString(7,huma_id);
											 * ps3.setString(8,sno[i]);
											 * 
											 * System.out.println(
											 * "Updating Other fields Before Deleting and the Parameter Values are:"
											 * );
											 * System.out.println("EffectDate="
											 * +effectdate
											 * [i]+" Status="+status[i]+
											 * " Description="
											 * +Description[i]+" CurrentUnit="
											 * +currentunit[i]+
											 * " TransferredUnit="
											 * +transferedunit
											 * [i]+" Huma_id="+huma_id
											 * +" sno="+sno[i]);
											 * 
											 * try{ //f10=ps3.executeUpdate();
											 * }catch(Exception e){
											 * e.printStackTrace(); }
											 * System.out.println(
											 * "The number of Records are Updated are :"
											 * +i); } //for(int i=0;
											 * i<prelength; i++)
											 * System.out.println
											 * ("Deletion Process Starts");
											 * System.out.println(""); for(int
											 * i=curlength; i<prelength; i++) {
											 * ps44.setString(1,huma_id);
											 * ps44.setInt(2,i+1); try{
											 * //f44=ps44.execute();
											 * }catch(Exception e){
											 * e.printStackTrace(); }
											 * System.out.println(
											 * "The number of Records deleted are :"
											 * +i);
											 * 
											 * }
											 */

											// System.out.println("Deletion Process Starts");
											String qry = "Delete from HRSTATUSREV_TAB where huma_id='"
													+ huma_id + "'";
											// System.out.println("The Deletion Query is:"+qry);
											ps44 = con.prepareStatement(qry);
											try {
												f44 = ps44.execute();
											} catch (SQLException e1) {
												errcode = 0;
												errcode = e1.getErrorCode();
											} catch (Exception e) {
												// System.out.println("Exception occured while deleting");
												e.printStackTrace();
											}
											// System.out.println("Inserting the Records");
											for (int i = 0; i < curlength; i++) {
												ps15.setString(1, huma_id);
												ps15.setString(2, sno[i]);
												ps15.setLong(3, Integer
														.parseInt(status[i]));
												ps15.setString(4, effectdate[i]);
												ps15.setString(5, username);
												ps15.setString(6,
														Description[i]);
												ps15.setString(
														7,
														currentunit[i]
																.substring(currentunit[i]
																		.lastIndexOf("-") + 1));
												ps15.setString(
														8,
														transferedunit[i]
																.substring(transferedunit[i]
																		.lastIndexOf("-") + 1));
												try {
													f10 = ps15.executeUpdate();
												} catch (SQLException e1) {
													errcode = 0;
													errcode = e1.getErrorCode();
												} catch (Exception e) {
													e.printStackTrace();
												}

												// If Transfered Update unitID,
												// AraeID and CompanyID in
												// Huma_mstr Added by Rajesh
												status_value = Integer
														.parseInt(status[i]);
												f11 = 1;
												if (status_value == 1) {
													// check_currentunitID =
													// currentunit[i].substring(currentunit[i].lastIndexOf("-")
													// + 1);
													check_transferunitID = transferedunit[i]
															.substring(transferedunit[i]
																	.lastIndexOf("-") + 1);
													if (!check_transferunitID
															.equals(unit_id)) {
														//System.out
															//	.println("Inside inner if block, updating the unit_id of huma_mstr ");

														Change_Unit = transferedunit[i]
																.substring(transferedunit[i]
																		.lastIndexOf("-") + 1);
														ps11.setString(1,
																Change_Unit);
														ps11.setString(2,
																huma_id);
														try {
															f11 = ps11
																	.executeUpdate();
														} catch (Exception e) {
															e.printStackTrace();
														}

													} // if(check_currentunitID.equals(unit_id))
												} // if(status_value == 1

												// Completed

											} // for

										}// else if(curlength < prelength)
										/*
										 * if(curlength == prelength) { //update
										 * the 0 to curlenth rows of
										 * hrstatusrev_tab where huma_id=?,f3
										 * for(int i=0; i<curlength; i++) {
										 * ps3.setString(1,effectdate[i]);
										 * ps3.setString(2,status[i]);
										 * ps3.setString(3,username);
										 * ps3.setString(4,seqid[i]);
										 * ps3.setString(5,Description[i]);
										 * ps3.setString(6,currentunit[i]);
										 * ps3.setString(7,transferedunit[i]);
										 * ps3.setString(8,huma_id);
										 * ps3.setString(9,sno[i]); //If
										 * Transfered Update unitID, AraeID and
										 * CompanyID in Huma_mstr Added by
										 * Rajesh status_value =
										 * Integer.parseInt(status[i]); try{
										 * if(status_value == 1){
										 * System.out.println("I value = "+i);
										 * check_currentunitID =
										 * currentunit[i].substring
										 * (currentunit[i].lastIndexOf("-") +
										 * 1);
										 * System.out.println("check_currentunitID="
										 * +
										 * check_currentunitID+" unit_id="+unit_id
										 * );
										 * if(check_currentunitID.equals(unit_id
										 * )) { System.out.println(
										 * "Inside inner if block, updating the unit_id of huma_mstr "
										 * );
										 * 
										 * Change_Unit =
										 * transferedunit[i].substring
										 * (transferedunit[i].lastIndexOf("-") +
										 * 1); ps11.setString(1, Change_Unit);
										 * ps11.setString(2, huma_id); //f11 =
										 * ps11.executeUpdate();
										 * System.out.println(
										 * "ps11 executed and unit id changed in huma_mstr"
										 * );
										 * 
										 * 
										 * }
										 * //if(check_currentunitID.equals(unit_id
										 * )) } //if(status_value == 1
										 * }catch(Exception e){
										 * e.printStackTrace(); } //Completed
										 * 
										 * f3=ps3.executeUpdate(); }//for(int
										 * i=0; i<curlenth; i++) f4=1;
										 * }//if(curlength == prelength)
										 */
										/*
										 * else if(curlength > prelength) {
										 * //update the 0 to prelenth rows of
										 * hrstatusrev_tab where huma_id=? and
										 * hrstatusrev_id=?,f3 for(int i=0;
										 * i<prelength; i++) {
										 * 
										 * ps3.setString(1,effectdate[i]);
										 * ps3.setString(2,status[i]);
										 * ps3.setString(3,username);
										 * ps3.setString(4,seqid[i]);
										 * ps3.setString(5,Description[i]);
										 * ps3.setString(6,currentunit[i]);
										 * ps3.setString(7,transferedunit[i]);
										 * ps3.setString(8,huma_id);
										 * ps3.setString(9,sno[i]);
										 * 
										 * //If Transfered Update unitID, AraeID
										 * and CompanyID in Huma_mstr Added by
										 * Rajesh status_value =
										 * Integer.parseInt(status[i]); try{
										 * if(status_value == 1){
										 * System.out.println("I value = "+i);
										 * check_currentunitID =
										 * currentunit[i].substring
										 * (currentunit[i].lastIndexOf("-") +
										 * 1);
										 * System.out.println("check_currentunitID="
										 * +
										 * check_currentunitID+" unit_id="+unit_id
										 * );
										 * if(check_currentunitID.equals(unit_id
										 * )) { System.out.println(
										 * "Inside inner if block, updating the unit_id of huma_mstr "
										 * );
										 * 
										 * Change_Unit =
										 * transferedunit[i].substring
										 * (transferedunit[i].lastIndexOf("-") +
										 * 1); ps11.setString(1, Change_Unit);
										 * ps11.setString(2, huma_id); //f11 =
										 * ps11.executeUpdate();
										 * System.out.println(
										 * "ps11 executed and unit id changed in huma_mstr"
										 * );
										 * 
										 * 
										 * }
										 * //if(check_currentunitID.equals(unit_id
										 * )) } //if(status_value == 1
										 * }catch(Exception e){
										 * e.printStackTrace(); } //Completed
										 * 
										 * f3=ps3.executeUpdate(); }//for(int
										 * i=0; i<prelenth; i++) //insert
										 * prelength to curlength rows in to
										 * hrstatusrev_tab, f4 for(int
										 * i=prelength; i<curlength; i++) {
										 * ps4.setString(1,huma_id);
										 * ps4.setString(2,sno[i]);
										 * ps4.setLong(3
										 * ,Integer.parseInt(status[i]));
										 * ps4.setString(4,effectdate[i]);
										 * ps4.setString(5,username);
										 * ps4.setString(6,Description[i]);
										 * ps4.setString(7,currentunit[i]);
										 * ps4.setString(8,transferedunit[i]);
										 * f4=ps4.executeUpdate(); }//for(int
										 * i=curlength-prelength; i<curlength;
										 * i++) //System.out.println(
										 * "hey the second if executionis over and f3="
										 * +f3+"and f4="+f4); }//else
										 * if(curlength > prelength)
										 */
										/*
										 * else if(curlength < prelength) {//Do
										 * the deletion first,because the
										 * Exception raising chance there in
										 * deletion,if so updation cant be
										 * executed. //delete curlength to
										 * prelength rows in to hrstatusrev_tab,
										 * f4 for(int i=curlength; i<prelength;
										 * i++) { ps44.setString(1,huma_id);
										 * ps44
										 * .setInt(2,i+1);//hrstatusrev_id[i]);
										 * f44=ps44.execute();//deleting all the
										 * rows in of correspoding contract
										 * if(!f44) f4=1;//deletion successfully
										 * hence made the }//for(int
										 * i=prelength-curlength; i<prelength;
										 * i++) //update the 0 to curlenth rows
										 * of hrstatusrev_tab where huma_id=?
										 * and hrstatusrev_id=?,f3 for(int i=0;
										 * i<curlength; i++) {
										 * ps3.setString(1,effectdate[i]);
										 * ps3.setString(2,status[i]);
										 * ps3.setString(3,username);
										 * ps3.setString(4,seqid[i]);
										 * ps3.setString(5,Description[i]);
										 * ps3.setString(6,currentunit[i]);
										 * ps3.setString(7,transferedunit[i]);
										 * ps3.setString(8,huma_id);
										 * ps3.setString(9,sno[i]);
										 * 
										 * //If Transfered Update unitID, AraeID
										 * and CompanyID in Huma_mstr Added by
										 * Rajesh status_value =
										 * Integer.parseInt(status[i]); try{
										 * if(status_value == 1){
										 * System.out.println("I value = "+i);
										 * check_currentunitID =
										 * currentunit[i].substring
										 * (currentunit[i].lastIndexOf("-") +
										 * 1);
										 * System.out.println("check_currentunitID="
										 * +
										 * check_currentunitID+" unit_id="+unit_id
										 * );
										 * if(check_currentunitID.equals(unit_id
										 * )) { System.out.println(
										 * "Inside inner if block, updating the unit_id of huma_mstr "
										 * ); Change_Unit =
										 * transferedunit[i].substring
										 * (transferedunit[i].lastIndexOf("-") +
										 * 1); ps11.setString(1, Change_Unit);
										 * ps11.setString(2, huma_id); //f11 =
										 * ps11.executeUpdate();
										 * System.out.println(
										 * "ps11 executed and unit id changed in huma_mstr"
										 * );
										 * 
										 * 
										 * }
										 * //if(check_currentunitID.equals(unit_id
										 * )) } //if(status_value == 1
										 * }catch(Exception e){
										 * e.printStackTrace(); } //Completed
										 * 
										 * f3=ps3.executeUpdate(); }//for(int
										 * i=0; i<curlenth; i++
										 * //System.out.println(
										 * "hey the third if execution is over and f3="
										 * +f3+"and f4="+f4); }//else
										 * if(curlength < prelength)
										 */
										// System.out.println("f="+f+" f3="+f3+" f4="+f4+" f11="+f11);
										// f11 = 1;
									} catch (Exception e) {
										e.printStackTrace();
									}
									// if ((f != 0) && (f3 != 0) && (f4 != 0) &&
									// (f11 != 0))
									if (errcode == 1) {
										// System.out.println("Rajesh Unique Constraint violation");
										errcode = 0;
										out.println("Please Check!\nDuplicate Date Found");
									} else if ((f != 0) && (f10 != 0)) {

										out.println("OKHuman resource master Updated successfully");
									} else {

										out.println("Human resource master is not Updated for some reasons");
									}
								}// if(x==0) checking the repotting officer
									// availability
								else if (x2 > 0) // checking of the
													// huma_reporting
													// availability
									out.println("Entered Reporting officer does not Exist");
								else if (x6 > 0) // checking of the city_id
									// availability
									out.println("Entered UnitID does not Exist");

								else if (x3 > 0) // checking of the city_id
													// availability
									out.println("Entered place of post(city) does not Exist");
							}// else for the self Reporting checking
						}// if((x5==0)//existing checking of entered
							// grade-designation
						else
							// entered Grade(Designation) not exits
							out.println("Entered Grade(Designation) does not Exist");
					}// if((x==0)//existing of entered Area
					else
						// entered Region not exits
						out.println("Entered Region not Exist");
				}// try
				catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (con != null)
						con.close();
					if (ps != null)
						ps.close();
					if (rs != null)
						rs.close();
					if (ps1 != null)
						ps1.close();
					if (rs1 != null)
						rs1.close();
					if (rs2 != null)
						rs2.close();
					if (ps2 != null)
						ps2.close();
					if (rs4 != null)
						rs4.close();
					if (ps4 != null)
						ps4.close();
					if (rs5 != null)
						rs5.close();
					if (ps5 != null)
						ps5.close();
					if (rs10 != null)
						rs10.close();
					if (ps10 != null)
						ps10.close();
					if (rs11 != null)
						rs11.close();
					if (ps11 != null)
						ps11.close();
					if (rs15 != null)
						rs15.close();
					if (ps15 != null)
						ps15.close();
				}
			}// authorised acess else

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
}
