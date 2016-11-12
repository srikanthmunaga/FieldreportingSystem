package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public final class Shm extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con = null;// , con2 = null, con3 = null;
	PreparedStatement ps = null, ps1 = null, ps2 = null, ps3 = null,
			ps4 = null, ps5 = null, ps10 = null, ps11 = null, ps12 = null;
	ResultSet rs = null, rs1 = null, rs2 = null, rs3 = null, rs4 = null,
			rs5 = null, rs10 = null, rs11 = null, rs12 = null;
	int f = 0, f3 = 0, f10 = 0, f11 = 0;
	JdbcConnect jc = new JdbcConnect();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// //System.out.println("inside the doGet method");
		// //System.out.println("inside doGet() of Shm servlet");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("inside the doPost method");
		// //System.out.println("inside doPost() of Shm servlet");
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
				out.print("You are not authorised to Create the HR Details"); // login
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
				String huma_cdate = dd + "-" + mm + "-" + yyyy;
				Integer status_value = 0;
				String check_currentunitID = null;
				String Change_Unit = null;
				String Change_AreaID = null;
				String Change_CompID = null;
				String unit_id = request.getParameter("unit_id").trim();
				String comp_id = request.getParameter("comp_id").trim();
				String area_id = request.getParameter("area_id").trim();
				String huma_id = request.getParameter("huma_id").trim();
				String huma_fname = request.getParameter("huma_fname").trim();
				String huma_lname = request.getParameter("huma_lname").trim();
				String huma_freeze = request.getParameter("huma_freeze");
				String huma_designation = request.getParameter(
						"huma_designation").trim();
				String huma_doj = request.getParameter("huma_doj").trim();
				String huma_dob = request.getParameter("huma_dob");
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
				/* By rajesh */
				unit_id = unit_id.substring(unit_id.lastIndexOf("-") + 1);
				area_id = area_id.substring(area_id.lastIndexOf("-") + 1);
				comp_id = comp_id.substring(comp_id.lastIndexOf("-") + 1);
				huma_designation = huma_designation.substring(huma_designation
						.lastIndexOf("-") + 1);
				huma_reporting = huma_reporting.substring(huma_reporting
						.lastIndexOf("-") + 1);
				city_id = city_id.substring(city_id.lastIndexOf("-") + 1);

				// Added by Rajesh
				// System.out.println("Values Collected in the Array");

				String sno[] = request.getParameterValues("sno");
				String status[] = request.getParameterValues("status");
				String effectdate[] = request.getParameterValues("effectdate");
				String transferedunit[] = request
						.getParameterValues("transferedunit");
				String currentunit[] = request
						.getParameterValues("currentunit");
				String Description[] = request
						.getParameterValues("Description");
				// System.out.println("Checked By Rajesh in HR Master");
				// System.out.println("Sno= "+sno+" status= "+status+" Effect Date= "+effectdate+" Description= "+Description);

				// System.out.println("/The");
				for (int i = 0; i < sno.length; i++) {
					sno[i] = sno[i].trim();
					status[i] = status[i].trim();
					transferedunit[i] = transferedunit[i].trim();
					currentunit[i] = currentunit[i].trim();
					effectdate[i] = effectdate[i].trim();
					Description[i] = Description[i].trim();

					/*
					 * String cur_unit =
					 * currentunit[i].substring(currentunit[i].lastIndexOf("-")
					 * + 1); String tra_unit =
					 * transferedunit[i].substring(transferedunit
					 * [i].lastIndexOf("-") + 1); if(cur_unit == tra_unit) {
					 * out.println(
					 * "Please Check!\nCurrent unit and Transfered Unit should not be Same"
					 * ); } else {
					 * out.println("curunit and transunit are not same"); }
					 */
				}
				// System.out.println("for(int i=0;i<sno.length;i++) completed");
				// completed
				try {
					// //System.out.println("inside the try block");
					// Class.forName(driver);
					// con = dbConn.getConnection();
					con = jc.getConnection();
					// System.out.println("The Connection is"+con);
					// con2 = dbConn.getConnection();
					// con3 = dbConn.getConnection();
					// con2.setAutoCommit(false);
					// con3.setAutoCommit(false);
					// con = DriverManager.getConnection(url,user,pwd);

					int x = 0;
					ps = con.prepareStatement("select area_id from area_mstr where area_id=?");
					// System.out.println("ps is executing");
					// ps.setString(1, area_id.substring(0, 4));
					ps.setString(1, area_id);

					rs = ps.executeQuery();
					// System.out.println("ps executed");
					if (rs == null)
						// System.out.println("result set is null in shm.jsp");
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
						if (rs5 == null)
							// System.out.println("result set is null in uhm.jsp");
							if ((rs5.next()) == false)
								x5 = x5 + 1; // System.out.println("hey the x5="+x5);
						if (x5 == 0) {
							// System.out.println("hey going to done the self reporting checking=");//+huma_id.equals(huma_reporting.substring(0,4)));
							// if (huma_id.equals(huma_reporting.substring(0,
							// 4)))// sefl
							if (huma_id.equals(huma_reporting))
								// reporting
								// checking
								// if
								out.println("Self reporting is not possible");
							else// self reporting checking else
							{
								// ________________________
								int x2 = 0, x3 = 0;
								// checking the availability of reporting
								// officer :x
								// System.out.println("Preparing ps2");
								ps2 = con
										.prepareStatement("select huma_fname||'-'||huma_id from huma_mstr where huma_id=?");
								// ps2.setString(1, huma_reporting.substring(0,
								// 4));
								// System.out.println("Rajesh testing huma_reporting="+huma_reporting);
								ps2.setString(1, huma_reporting);
								try {
									// System.out.println("Executing the ps2");
									rs2 = ps2.executeQuery();

									// System.out.println("Checked by Raja");
									if (rs2 == null)
										// System.out.println("result set is null");
										if ((rs2.next()) == false)
											x2 = x2 + 1;

									// checkin the city availability :x2
									ps4 = con
											.prepareStatement("select city_name||'-'||city_id from city_mstr where city_id=?");
									// ps4.setString(1, city_id.substring(0,
									// 7));
									ps4.setString(1, city_id);
									rs4 = ps4.executeQuery();
									// System.out.println("City Availability checking");
									if (rs4 == null)
										System.out
												.println("result set4 is null in shm.jsp");
									if ((rs4.next()) == false)
										x3 = x3 + 1;

									// _________________________

									ps = con.prepareStatement("select huma_id from huma_mstr where huma_id=?");
									ps.setString(1, huma_id);

									rs = ps.executeQuery();
									// System.out.println("ps executed");
									/*
									 * if (rs == null)
									 * System.out.println("result set is null");
									 */if ((rs.next()) == false) {
										// System.out.println("rs.next()) == false");
										// System.out.println("x2="+x2+" x3="+x3);
										if ((x2 == 0) && (x3 == 0)) {
											// con.setAutoCommit(false);
											// ps1 =
											// con.prepareStatement("insert into huma_mstr values(?,?,?,?,?,to_date(?,'dd-mm-rrrr'),?,?,?,?,?,?,to_date(?,'dd-mm-rrrr'),to_date(?,'dd-mm-rrrr'),?,?,?,?,?,?,to_date(?,'dd-mm-rrrr'),?)");
											// System.out.println("Prepared Statement for huma master");
											ps1 = con
													.prepareStatement("insert into huma_mstr"
															+ "(HUMA_ID,HUMA_FNAME,HUMA_LNAME,HUMA_DOB,HUMA_ADDRESS,HUMA_PIN,HUMA_EMAIL,"
															+ "HUMA_PHONE,HUMA_MOBILE,HUMA_PHOTO,HUMA_CDATE,HUMA_MDATE,"
															+ "HUMA_DESIGNATION,HUMA_REPORTING,CITY_ID,HUMA_CBY,HUMA_MBY,HUMA_DOJ,BSFLUNIT_UCODE,HUMA_STATUS) "
															+ "values(?,?,?,to_date(?,'dd-mm-rrrr'),?,?,?,?,?,?,to_date(?,'dd-mm-rrrr'),to_date(?,'dd-mm-rrrr'),?,?,?,?,?,to_date(?,'dd-mm-rrrr'),?,?)");
											// System.out.println("query preparation happened");
											// ps1.setString(1,comp_id.substring(0,
											// 4));
											// ps1.setString(1,comp_id);
											// ps1.setString(2,area_id.substring(0,
											// 4));
											// ps1.setString(2,area_id);
											ps1.setString(1, huma_id);
											ps1.setString(2, huma_fname);
											ps1.setString(3, huma_lname);
											ps1.setString(4, huma_dob);
											ps1.setString(5, huma_address);
											ps1.setString(6, huma_pin);
											ps1.setString(7, huma_email);
											ps1.setString(8, huma_phone);
											ps1.setString(9, huma_mobile);
											// ps1.setString(10, huma_photo);
											ps1.setString(10, null);
											ps1.setString(11, huma_cdate);
											ps1.setString(12, null);
											ps1.setString(13, huma_designation);
											ps1.setString(14, huma_reporting);
											ps1.setString(15, city_id);
											ps1.setString(16, curhuma_id);
											ps1.setString(17, null);
											ps1.setString(18, huma_doj);
											ps1.setString(19, unit_id);
											ps1.setString(20, HR_Status);

											// System.out.println("Before Data inserted into huma_mstr 2 and con="+con);
											//System.out
												//	.println("Before Data inserted into huma_mstr 2 and ps1="
													//		+ ps1);
											try {
												//System.out
													//	.println("just bfr executing query");
												f = ps1.executeUpdate();
												//System.out
													//	.println("After ps1 execute");
											} catch (Exception e) {
												System.out
														.println("Inside catch block of ps1 execute");
												e.printStackTrace();
											}

											//System.out
												//	.println("After Data inserted into huma_mstr");
											/*
											 * if (f != 0) { //
											 * System.out.println(
											 * "hey inside the if this means the huma_mstr created successfully"
											 * ); ps3 = con .prepareStatement(
											 * "insert into rights_mstr values(?,?,?,to_date(?,'dd-mm-yyyy'),?)"
											 * );
											 * 
											 * ps3.setString(1, huma_id);
											 * ps3.setString(2, null);
											 * ps3.setString(3, null);
											 * ps3.setString(4, null);
											 * ps3.setString(5, null); //
											 * System.out.println(
											 * "hey the PreparedStatement made"
											 * ); f3 = ps3.executeUpdate();
											 * 
											 * }// inserting the null rights for
											 * the // new created huma_id .
											 */
											// currentunit[i] =
											// currentunit[i].substring(currentunit[i].lastIndexOf("-")
											// + 1);
											// transferedunit[i] =
											// transferedunit[i].substring(transferedunit[i].lastIndexOf("-")
											// + 1);
											// Added by Rajesh for inserting
											// Status relates data
											//System.out
												//	.println("prepared Statement for status");
											ps10 = con
													.prepareStatement("insert into HRSTATUSREV_TAB(huma_id,HRSTATUSREV_ID,"
															+ "HRSTATUS_ID,HRSTATUSREV_EFFECTDATE,HRSTATUSREV_CDATE,HRSTATUSREV_CBY,"
															+ "HRSTATUSREV_SEQID,HRSTATUSREV_DESCRIPTION,HRSTATUSREV_CURRENT_UNIT,HRSTATUSREV_TRANSFERED_UNIT) "
															+ "values (?,?,?,to_date(?,'dd-mm-yyyy'),sysdate,?,HRSTATUSREV_SEQID.nextval,?,?,?)");
											/*
											 * ps11=con.prepareStatement(
											 * "select b.area_name||'-'||b.area_id,c.comp_name||'-'||"
											 * +
											 * "c.comp_id from comp_mstr c, area_mstr b,bsflunit_mstr u "
											 * +
											 * "where c.comp_id=b.comp_id and b.area_id=u.area_id "
											 * + "and u.bsflunit_ucode=?");
											 */
											ps11 = con
													.prepareStatement("update huma_mstr set bsflunit_UCODE=? where huma_id=?");
											for (int i = 0; i < sno.length; i++) {
												ps10.setString(1, huma_id);
												ps10.setString(2, sno[i]);
												ps10.setLong(3, Integer
														.parseInt(status[i]));
												ps10.setString(4, effectdate[i]);
												ps10.setString(5, username);
												ps10.setString(6,
														Description[i]);
												/*
												 * ps10.setString(7,currentunit[i
												 * ]);
												 * ps10.setString(8,transferedunit
												 * [i]);
												 */
												ps10.setString(
														7,
														currentunit[i]
																.substring(currentunit[i]
																		.lastIndexOf("-") + 1));
												ps10.setString(
														8,
														transferedunit[i]
																.substring(transferedunit[i]
																		.lastIndexOf("-") + 1));
												try {
													f10 = ps10.executeUpdate();
												} catch (Exception e) {
													// System.out.println("Error occured in ps10");
													e.printStackTrace();
												}
												// System.out.println("Data inserted into status");

												// If Transfered Update unitID,
												// AraeID and CompanyID in
												// Huma_mstr Added by Rajesh
												status_value = Integer
														.parseInt(status[i]);
												f11 = 1;
												if (status_value == 1) {
													// System.out.println("I value = "+i);
													check_currentunitID = currentunit[i]
															.substring(currentunit[i]
																	.lastIndexOf("-") + 1);
													// System.out.println("check_currentunitID="+check_currentunitID+" unit_id="+unit_id);
													if (check_currentunitID
															.equals(unit_id)) {
														//System.out
															//	.println("Inside inner if block, updating the unit_id of huma_mstr ");

														Change_Unit = transferedunit[i]
																.substring(transferedunit[i]
																		.lastIndexOf("-") + 1);
														/*
														 * ps11.setString(1,
														 * Change_Unit); rs11 =
														 * ps11
														 * .executeQuery();//
														 * System.out.println(
														 * "Hey going for while loop and key1="
														 * +key1); while
														 * (rs.next()) {
														 * 
														 * } data = data +
														 * rs.getString(1) +
														 * "::::::" +
														 * rs.getString(2) +
														 * "::::::";
														 */
														ps11.setString(1,
																Change_Unit);
														ps11.setString(2,
																huma_id);
														f11 = ps11
																.executeUpdate();
														//System.out
															//	.println("ps11 executed and unit id changed in huma_mstr");

													} // if(check_currentunitID.equals(unit_id))
												} // if(status_value == 1

												// Completed
											}

											// Completed Added by Rajesh for
											// inserting Status relates data

											if ((f != 0) && (f10 != 0)
													&& (f11 != 0))//
											{
												// System.out.println("Data Commited");
												// con.commit();
												out.println("OKHuman resource master created successfully");// Human
																											// resource
											} // master
												// created
												// successfully
											else {
												// System.out.println("Data rollback");
												// con.rollback();
												out.println("Human resource master is not created for some reasons");
											}
											ps1.close();
											// con.setAutoCommit(true);
										}// if(x==0)
										else if (x2 > 0) // checking of the
															// huma_reporting
															// availability
											out.println("Entered Reporting officer does not Exist");
										else if (x3 > 0) // checking of the
															// city_id
															// availability
											out.println("Entered place of post(city) does not Exist");
									}// if checking existance of the huma_mstr
										// for
										// the entered emp id
									else
										out.println("Already existing one(emp id)");
								} catch (Exception e) {
									// System.out.println("Catching the ps2.executeQuery() Exception");
									e.printStackTrace();
								}
							}// self reporting checking else
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
					/*
					 * if (con2 != null) con2.close(); if (con3 != null)
					 * con3.close();
					 */if (ps != null)
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
					if (rs3 != null)
						rs3.close();
					if (ps3 != null)
						ps3.close();
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
					if (ps11 != null)
						ps11.close();
					if (ps12 != null)
						ps12.close();
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
