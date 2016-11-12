package frs_cls;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.StringTokenizer;

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

public final class Sbm extends HttpServlet {

	Connection con = null;
	Statement st = null, st1 = null, st2 = null, st3 = null;
	PreparedStatement ps_id = null, ps = null, ps1 = null, ps2 = null,
			ps3 = null;
	ResultSet rs_id = null, rs = null, rs1 = null, rs2 = null, rs3 = null;
	int f1 = 0, f2 = 0, f3 = 0;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// //System.out.println("inside the doGet method");
		//System.out.println("inside the doGet() of Sbm in FRS");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// //System.out.println("inside the doPost method");
		//System.out.println("inside the doPost() of Sbm in FRS");
		JspFactory _jspxFactory = null;
		PageContext pageContext = null;
		HttpSession session = null;
		ServletContext application = null;
		ServletConfig config = null;
		JspWriter out = null;
		Object page = this;
		JspWriter _jspx_out = null;
		PageContext _jspx_page_context = null;

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
			String ur=(String)request.getSession().getAttribute("userrole");

			if (((HttpServletRequest) request).getSession()
					.getAttribute("username") == null) {
				response.sendRedirect("frslogin.jsp"); // Not logged in, redirect to
													// login page.
			}
			else if (ur.equals("user") || ur.equals("fs") || ur.equals("fx")) {
				out.print("You are not authorised to Create a Region");									// login page.
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
				String area_cdate = dd + "-" + mm + "-" + yyyy;

				String comp_id = request.getParameter("comp_id");
				
				//String area_id = "";
				String area_id =request.getParameter("area_id").trim();
				String area_name = request.getParameter("area_name").trim();
				String area_lang ="N";
				area_lang=request.getParameter("area_lang").trim();
				System.out.println("language is :"+area_lang);
				// String area_type = request.getParameter("area_type").trim();
				String area_remarks = request.getParameter("area_remarks")
						.trim();
				String area_strategy = request.getParameter("area_strategy")
						.trim();
				String huma_id1=request.getParameter("huma_id");
				System.out.println("huma_id before process="+huma_id1);
				/*System.out.println("huma_id before process="+huma_id1);
				int i = huma_id1.lastIndexOf('-');  
				String huma_id=huma_id1.substring(i+1);
				*/

				String huma_id=null;
				comp_id =comp_id.substring(comp_id.lastIndexOf("-")+1);
				huma_id=huma_id1.substring(huma_id1.lastIndexOf("-")+1);
				/*StringTokenizer stkz = new StringTokenizer(empid1);

				while (stkz.hasMoreElements()) {
					huma_id=stkz.nextElement().toString();
				}*/
				System.out.print("huma_id="+huma_id);
				//System.out.println("LSR");
				//String Region_inchrge=request.getParameter("area_inchrg");
				//System.out.println("Region_inchrge before processing= "+Region_inchrge);
				/*int i = Region_inchrge.lastIndexOf('-');  
				Region_inchrge=Region_inchrge.substring(i+1);
				System.out.print("Region_inchrge by Rajesh="+Region_inchrge);*/
				String curhuma_id = (String) ((HttpServletRequest) request)
						.getSession().getAttribute("username");

				try {
					// Class.forName(driver);
					//System.out.println("inside the try block"); 
					con = dbConn.getConnection();
					// con = DriverManager.getConnection(url,user,pwd);

					int x = 0;
					ps = con.prepareStatement("select comp_id from comp_mstr where comp_id=?");
					//ps.setString(1, comp_id.substring(0, 4));
					ps.setString(1, comp_id);
					rs = ps.executeQuery();
					if (rs == null)
						System.out.println("result set is null in ubm.jsp");
					if ((rs.next()) == false)
						x = x + 1; System.out.println("hey the x="+x);
                     
					//Added By Rajesh
					int y = 0;
					//ps = con.prepareStatement("select USERNAME from FRS_USER where USERNAME=?");
					ps = con.prepareStatement("select HUMA_ID from HUMA_MSTR where HUMA_ID=?");
					ps.setString(1, huma_id);
					rs = ps.executeQuery();
					if (rs == null)
						System.out.println("result set is null in ubm.jsp");
					if ((rs.next()) == false)
						y = y + 1;
					
					System.out.println("Value of y="+y);
					
					//Rajesh complete
					// Automatic id generation code starts here to work with the
					// multiuser on this form @ same time-----------------------
					
					//Hiden by Rajesh
					/*
					ps_id = con
							.prepareStatement("select area_id from area_mstr where area_id=(select max(area_id) from area_mstr)");
					rs_id = ps_id.executeQuery();
					if (rs_id == null) {
						System.out
								.println("rs is null in getuserupate and in area_id");
					}
					if (rs_id.next()) {
						do {
							String s = (rs_id.getString(1)).substring(0, 1);
							int n = Integer.parseInt((rs_id.getString(1))
									.substring(1));
							n = n + 1;
							if (n <= 9)
								area_id = s + "00" + n;
							if ((n >= 10) && (n <= 99))
								area_id = s + "0" + n;
							if ((n >= 100) && (n <= 999))
								area_id = s + n;
							if (n > 999)
								throw new Exception();
						} while (rs_id.next());// while
					}// if
					else
						area_id = "B001";
					*/
					// Automatic id generation code ends here
					// ------------------------------------------------------------------------------

					if ((x == 0)&&(y == 0)) {
						ps1 = con
								.prepareStatement("insert into area_mstr values(?,?,?,?,to_date(?,'dd-mm-rrrr'),to_date(?,'dd-mm-rrrr'),?,?,?,?,?)");
						//ps1.setString(1, comp_id.substring(0, 4));
						ps1.setString(1, comp_id);
						ps1.setString(2, area_id);
						ps1.setString(3, area_name);
						// ps1.setString(4,area_type);
						ps1.setString(4, area_remarks);
						ps1.setString(5, area_cdate);
						ps1.setString(6, null);
						ps1.setString(7, curhuma_id);
						ps1.setString(8, null);
						ps1.setString(9, area_strategy);
						ps1.setString(10, huma_id);
						ps1.setString(11,area_lang);
						f1 = ps1.executeUpdate();

					/*	if (f1 != 0) { // System.out.println("hey inside the if this means the area_mstr created successfully");
							String query2 = null;
							query2 = "select head_id from head_mstr order by head_id";
							st2 = con.createStatement();
							rs2 = st2.executeQuery(query2);
							if (rs2 == null)
								System.out.println("Result set is null");

							ps3 = con
									.prepareStatement("insert into headbind_mstr values(?,?,?,to_date(?,'dd-mm-yyyy'))");

							while (rs2.next())// for(int i=0;
												// i<subordinateCheck.length;
												// i++)
							{
								// rs2.next();
								ps3.setString(1, rs2.getString(1));
								ps3.setString(2, area_id);
								ps3.setString(3, null);
								ps3.setString(4, null);
								// System.out.println("hey the PreparedStatement made");
								f3 = ps3.executeUpdate();
							}// while(rs2.next())//for(int i=0;
								// i<subordinateCheck.length; i++)

						}*/
						// inserting the null rights for the new created
							// huma_id .
					//	if (f3 != 0)// if f3!=0 then the all the f1!=0 and the
									// selection from the rs2.next!=false.means
									// after the successful insertion.
							 if (f1 != 0)
							out.println("OKRegion master created successfully & Region id= "
									+ area_id);// Region master created
												// successfully
						else
							out.println("Region master is not created for some reasons");

					}// if(x==0)
					else if(x!=0)
						// entered company not exits
						out.println("Entered Company does not Exist");
					else if(y!=0)
							// entered company not exits
							out.println("Entered Region Incharge ID does not Exist");


				}// try
				catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs_id != null)
						rs_id.close();
					if (ps_id != null)
						ps_id.close();
					if (rs != null)
						rs.close();
					if (ps != null)
						ps.close();
					if (st != null)
						st.close();
					if (rs1 != null)
						rs1.close();
					if (ps1 != null)
						ps1.close();
					if (st1 != null)
						st1.close();
					if (rs2 != null)
						rs2.close();
					if (ps2 != null)
						ps2.close();
					if (st2 != null)
						st2.close();
					if (rs3 != null)
						rs3.close();
					if (ps3 != null)
						ps3.close();
					if (st3 != null)
						st3.close();
					if (con != null)
						con.close();
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
