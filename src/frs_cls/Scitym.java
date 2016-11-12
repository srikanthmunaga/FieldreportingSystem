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

public final class Scitym extends HttpServlet {

	Connection con = null;
	PreparedStatement ps_id = null, ps = null, ps1 = null, ps2 = null;
	ResultSet rs_id = null, rs = null, rs1 = null, rs2 = null;
	int f = 0;

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
			String ur=(String)request.getSession().getAttribute("userrole");

			if (((HttpServletRequest) request).getSession()
					.getAttribute("username") == null) {
				response.sendRedirect("frslogin.jsp"); // Not logged in, redirect to
													// login page.
			}
			else if (ur.equals("user") || ur.equals("fs") || ur.equals("fx")) {
				out.print("You are not authorised to Create a City");									// login page.
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
				String city_cdate = dd + "-" + mm + "-" + yyyy;

				String state_id = request.getParameter("state_id").trim();
				state_id = state_id.substring(state_id.lastIndexOf("-")+1);
				String city_id = "";// request.getParameter("city_id");
				String city_name = request.getParameter("city_name").trim();
				String curhuma_id = (String) ((HttpServletRequest) request)
						.getSession().getAttribute("username");
				try {
					// Class.forName(driver);
					con = dbConn.getConnection();
					// con = DriverManager.getConnection(url,user,pwd);

					// Automatic id generation code starts here to work with the
					// multiuser on this form @ same time-----------------------
					ps_id = con
							.prepareStatement("select city_id from city_mstr where city_id=(select max(city_id) from city_mstr)");
					rs_id = ps_id.executeQuery();
					if (rs_id == null) {
						System.out.println("rs is null");
					}
					if (rs_id.next()) {
						do {
							String s = (rs_id.getString(1)).substring(0, 2);
							int n = Integer.parseInt((rs_id.getString(1))
									.substring(2));
							n = n + 1;
							if (n <= 9)
								city_id = s + "0000" + n;
							if ((n >= 10) && (n <= 99))
								city_id = s + "000" + n;
							if ((n >= 100) && (n <= 999))
								city_id = s + "00" + n;
							if ((n >= 1000) && (n <= 9999))
								city_id = s + "0" + n;
							if ((n >= 10000) && (n <= 99999))
								city_id = s + n;
							if (n > 99999)
								throw new Exception();
						} while (rs_id.next());// while
					}// if
					else
						city_id = "CT00001";
					// Automatic id generation code ends here
					// ------------------------------------------------------------------------------

					ps = con.prepareStatement("select city_id from city_mstr where city_id=?");
					ps.setString(1, city_id);

					rs = ps.executeQuery();
					if (rs == null)
						System.out.println("result set is null");
					if ((rs.next()) == false)// city_id is not already exist
					{
						int x = 0;// going to check for the availability of
									// entered state in state_mstr.
						ps2 = con
								.prepareStatement("select state_id||' '||state_name from state_mstr where state_id=?");
						ps2.setString(1, state_id.substring(0, 3));
						rs2 = ps2.executeQuery();
						if (rs2 == null)
							System.out.println("result set is null");
						if ((rs2.next()) == false)
							x = x + 1;

						if (x == 0) // means the specified state_id is available
									// in the state_mstr
						{
							ps1 = con
									.prepareStatement("insert into city_mstr values(?,city_seqid.nextval,?,to_date(?,'dd-mm-rrrr'),to_date(?,'dd-mm-rrrr'),?,?,?)");
							// ps1.setString(1,state_id.substring(0,3));
							ps1.setString(1, city_id);
							ps1.setString(2, city_name);
							ps1.setString(3, city_cdate);
							ps1.setString(4, null);
							ps1.setString(5, state_id.substring(0, 3));
							ps1.setString(6, curhuma_id);
							ps1.setString(7, null);
							f = ps1.executeUpdate();
							if (f != 0) {
								out.println("OKCity master created successfully & City id= "
										+ city_id);// City master created
													// successfully
							} else {
								out.println("City master is not created for some reasons");
							}

							ps1.close();
						}// if((x==0) //means the specified state_id is
							// available in the state_mstr
						else // if((x!=0) //means the specified state_id is not
								// available in the state_mstr
						{
							out.println("Entered State does not Exist");
						}// else if((x!=0) //means the specified state_id is not
							// available in the state_mstr
					}// if that checks the existingness of the city_id
					else {
						out.println("Already existing one");
					}

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
