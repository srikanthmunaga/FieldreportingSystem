package frs_cls;

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

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public final class Sstatem extends HttpServlet {

	Connection con = null;
	Statement st3 = null;
	PreparedStatement ps_id = null, ps = null, ps1 = null, ps2 = null,
			ps3 = null;
	ResultSet rs_id = null, rs = null, rs1 = null, rs2 = null, rs3 = null;
	int f = 0, f2 = 0, f3 = 0;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// //System.out.println("inside the doGet method");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// //System.out.println("inside the doPost method");

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
			String ur = (String) request.getSession().getAttribute("userrole");

			if (((HttpServletRequest) request).getSession().getAttribute(
					"username") == null) {
				response.sendRedirect("frslogin.jsp"); // Not logged in,
														// redirect to
														// login page.
			} else if (ur.equals("user") || ur.equals("fs") || ur.equals("fx")) {
				out.print("You are not authorised to Create a State"); // login
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
				String state_cdate = dd + "-" + mm + "-" + yyyy;

				String country_id = request.getParameter("country_id").trim();
				country_id = country_id
						.substring(country_id.lastIndexOf("-") + 1);
				String state_id = "";// request.getParameter("state_id");
				String state_name = request.getParameter("state_name").trim();
				String curhuma_id = (String) ((HttpServletRequest) request)
						.getSession().getAttribute("username");
				try {
					// Class.forName(driver);
					con = dbConn.getConnection();
					// con = DriverManager.getConnection(url,user,pwd);

					ps = con.prepareStatement("select state_id from state_mstr where state_id=?");
					ps.setString(1, state_id);

					rs = ps.executeQuery();
					if (rs == null)
						System.out.println("result set is null");
					if ((rs.next()) == false)// state_id is not already exist
					{
						int x = 0;// going to check for the availability of
									// entered country in country_mstr.
						ps2 = con
								.prepareStatement("select country_id||' '||country_name from country_mstr where country_id=?");
						// ps2.setString(1, country_id.substring(0, 3));
						ps2.setString(1, country_id);
						rs2 = ps2.executeQuery();
						if (rs2 == null)
							System.out.println("result set is null");
						if ((rs2.next()) == false)
							x = x + 1;

						// Automatic id generation code starts here to work with
						// the multiuser on this form @ same
						// time-----------------------
						ps_id = con
								.prepareStatement("select state_id from state_mstr where state_id=(select max(state_id) from state_mstr)");
						rs_id = ps_id.executeQuery();
						if (rs_id == null) {
							System.out.println("rs is null");
						}
						if (rs_id.next()) {
							do {
								int n = Integer.parseInt(rs_id.getString(1));
								n = n + 1;
								if (n <= 9)
									state_id = "00" + n;
								if ((n >= 10) && (n <= 99))
									state_id = "0" + n;
								if ((n >= 100) && (n <= 999))
									state_id = "" + n;
								if (n > 999)// if(n>9999)
									throw new Exception();
							} while (rs_id.next());// while
						}// if
						else
							state_id = "001";
						// Automatic id generation code ends here
						// ------------------------------------------------------------------------------

						if (x == 0) // means the specified country_id is
									// available in the country_mstr
						{
							ps1 = con
									.prepareStatement("insert into state_mstr values(?,?,state_seqid.nextval,?,to_date(?,'dd-mm-rrrr'),to_date(?,'dd-mm-rrrr'),?,?)");
							ps1.setString(1, country_id.substring(0, 3));
							ps1.setString(2, state_id);
							ps1.setString(3, state_name);
							ps1.setString(4, state_cdate);
							ps1.setString(5, null);
							ps1.setString(6, curhuma_id);
							ps1.setString(7, null);
							f = ps1.executeUpdate();

							/*
							 * if (f != 0) { // System.out.println(
							 * "hey inside the if this means the state_mstr created successfully and gona to insert null in the holidaybind"
							 * ); String query3 = null; query3 =
							 * "select holiday_id from holiday_mstr order by holiday_id"
							 * ; st3 = con.createStatement(); rs3 =
							 * st3.executeQuery(query3); if (rs3 == null)
							 * System.out.println("Result set is null");
							 * 
							 * ps3 = con .prepareStatement(
							 * "insert into holidaybind_mstr values(?,?,?,to_date(?,'dd-mm-yyyy'))"
							 * );
							 * 
							 * while (rs3.next())// for(int i=0; //
							 * i<subordinateCheck.length; // i++) { //
							 * rs3.next(); ps3.setString(1, rs3.getString(1));
							 * ps3.setString(2, state_id); ps3.setString(3,
							 * null); ps3.setString(4, null); //
							 * System.out.println
							 * ("hey the PreparedStatement made"); f3 =
							 * ps3.executeUpdate(); } //
							 * while(rs3.next())//for(int i=0; //
							 * i<subordinateCheck.length; i++)
							 * 
							 * }
							 */// inserting the null rights for the new created
								// state_id .
							if (f != 0)// if f3!=0 then the all the f1!=0 and
										// the selection from the
										// rs3.next!=false.means after the
										// successful insertion.
								out.println("OKState master created successfully & State id= "
										+ state_id);// State master created
													// successfully
							else
								out.println("State master is not created for some reasons");

							ps1.close();
						}// if((x==0) //means the specified country_id is
							// available in the country_mstr
						else
							// if((x!=0) //means the specified country_id is not
							// available in the country_mstr
							out.println("Entered Country does not Exist");
					}// if that checks the existingness of the state_id
					else
						out.println("Already existing one.To differ press refresh and continue");

				}// try
				catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (ps_id != null)
						ps_id.close();
					if (rs_id != null)
						rs_id.close();
					if (ps != null)
						ps.close();
					if (rs != null)
						rs.close();
					if (ps1 != null)
						ps1.close();
					if (rs1 != null)
						rs1.close();
					if (ps2 != null)
						ps2.close();
					if (rs2 != null)
						rs2.close();
					if (ps3 != null)
						ps3.close();
					if (rs3 != null)
						rs3.close();
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
