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

public final class Scm extends HttpServlet {

	Connection con = null;
	Statement st = null, st1 = null;
	PreparedStatement ps_id = null, ps = null, ps1 = null;
	ResultSet rs_id = null, rs = null, rs1 = null;
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
				//response.sendRedirect("frslogin.jsp"); // Not logged in, redirect to
				out.print("You are not authorised to create a Company");									// login page.
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
				String comp_cdate = dd + "-" + mm + "-" + yyyy;

				String comp_id = "";// request.getParameter("comp_id");
				String comp_name = request.getParameter("comp_name").trim();
				String curhuma_id = (String) ((HttpServletRequest) request)
						.getSession().getAttribute("username");

				try {
					// Class.forName(driver);
					con = dbConn.getConnection();
					// con = DriverManager.getConnection(url,user,pwd);

					// Automatic id Generation code to allow multiuser to work
					// with the form @ same time----------
					ps_id = con
							.prepareStatement("select comp_id from comp_mstr where comp_id=(select max(comp_id) from comp_mstr)");
					rs_id = ps_id.executeQuery();
					if (rs_id == null) {
						System.out.println("rs is null in scm and in comp_id");
					}
					if (rs_id.next()) {
						do {
							String s = (rs_id.getString(1)).substring(0, 1);
							int n = Integer.parseInt((rs_id.getString(1))
									.substring(1));
							n = n + 1;
							if (n <= 9)
								comp_id = s + "00" + n;
							if ((n >= 10) && (n <= 99))
								comp_id = s + "0" + n;
							if ((n >= 100) && (n <= 999))
								comp_id = s + n;
							if (n > 999)
								throw new Exception();
						} while (rs_id.next());// while
					}// if
					else
						comp_id = "C001";
					// Automatic id Generation code ends
					// here--------------------------------------------------------

					ps = con.prepareStatement("select comp_id from comp_mstr where comp_id=?");
					ps.setString(1, comp_id);

					rs = ps.executeQuery();
					if (rs == null)
						System.out.println("result set is null");
					if ((rs.next()) == false) {

						ps1 = con
								.prepareStatement("insert into comp_mstr values(?,?,to_date(?,'dd-mm-rrrr'),to_date(?,'dd-mm-rrrr'),?,?)");
						ps1.setString(1, comp_id);
						ps1.setString(2, comp_name);
						ps1.setString(3, comp_cdate);
						ps1.setString(4, null);
						ps1.setString(5, curhuma_id);
						ps1.setString(6, null);

						f = ps1.executeUpdate();
						if (f != 0) {
							out.print("OKCompany master created successfully & company id= "
									+ comp_id);
						} else {
							out.print("Company master is not created for some reasons");
						}

						// ps1.close();
					}// already existing if
					else {
						out.print("Already existing one");
					}

				}// try
				catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (ps_id != null)
						ps_id.close();
					if (rs_id != null)
						rs_id.close();
					if (st != null)
						st.close();
					if (ps != null)
						ps.close();
					if (rs != null)
						rs.close();
					if (st1 != null)
						st1.close();
					if (ps1 != null)
						ps1.close();
					if (rs1 != null)
						rs1.close();
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
