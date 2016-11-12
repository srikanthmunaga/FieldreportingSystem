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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public final class Uvillagem extends HttpServlet {

	Connection con = null;
	PreparedStatement ps = null;
	PreparedStatement ps1 = null;
	ResultSet rs = null;
	int f = 0;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("inside the doGet method of Uvillagem");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("Msr debug INSIDE Uvillagem");

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

			if (((HttpServletRequest) request).getSession().getAttribute(
					"username") == null) {
				response.sendRedirect("frslogin.jsp"); // Not logged in,
														// redirect to
														// login page.
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
				con = dbConn.getConnection();
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
				String village_mdate = dd + "-" + mm + "-" + yyyy;
				try {
					String vcode = request.getParameter("vcode").trim();
					if (vcode.length() > 10) {
						out.println("Village Code is too large, pls check!.. ");
						return;
					}
					String vname = request.getParameter("vname").trim();
					String District = request.getParameter("DISTRICT").trim();
					int VILLAGE_SEQID = Integer.parseInt(request.getParameter(
							"VILLAGE_SEQID").trim());
					System.out.println(VILLAGE_SEQID);
					String curhuma_id = (String) ((HttpServletRequest) request)
							.getSession().getAttribute("username");

					String unit1 = request.getParameter("BSFLUNIT_UCODE")
							.trim();

					String uno = unit1.substring(unit1.lastIndexOf('-') + 1);
					String uname = "";
					// Connection svcon=new JdbcConnect().getConnection();
					String svsql = "select distinct BSFLUNIT_NAME from BSFLUNIT_MSTR where BSFLUNIT_UCODE='"
							+ uno + "'";
					Statement svst = con.createStatement();
					ResultSet svrs = svst.executeQuery(svsql);
					if (svrs.next()) {
						uname = svrs.getString(1);
					}

					/*
					 * int i = unit1.lastIndexOf('-'); String
					 * BSFLUNIT_NAME=unit1.substring(0,i); String
					 * BSFLUNIT_UCODE=unit1.substring(i+1);
					 */
					String unt = unit1.substring(unit1.lastIndexOf('-') + 1);
					String svsql1 = "select 'Exist' from BSFLUNIT_MSTR where BSFLUNIT_UCODE='"
							+ unt + "'";

					Statement svst1 = con.createStatement();
					ResultSet svrs1 = svst1.executeQuery(svsql1);
					if (!svrs1.next()) {
						out.println("Entered Unit does not Exist ");
						return;
					}

					// Connection svcon11=new JdbcConnect().getConnection();
					/*String svsql11 = "select 'Exist' from VILLAGE_MSTR where VCODE='"
							+ vcode + "' and bsflunit_ucode='" + unt + "'";
					Statement svst11 = con.createStatement();
					ResultSet svrs11 = svst11.executeQuery(svsql11);
					if (svrs11.next()) {
						out.println("Entered Village code already Exist ");
						return;
					}*/

					// try
					// {
					// Class.forName(driver);
					// con= dbConn.getConnection();
					// con = DriverManager.getConnection(url,user,pwd);

					// ps =
					// con.prepareStatement("select * from comp_mstr where comp_id=?");
					// ps.setString(1,comp_id);

					// rs = ps.executeQuery();
					// if(rs==null)
					// System.out.println("result set is null");
					// if((rs.next())==false)
					// {

					ps1 = con
							.prepareStatement("update VILLAGE_MSTR set vname=?,BSFLUNIT_UCODE=?,DISTRICT=?,village_mdate=to_date(?,'dd-mm-rrrr'),village_mby=?,vcode=? where VILLAGE_SEQID=?");
					ps1.setString(1, vname);
					// ps1.setString(2, uname);
					ps1.setString(2,
							unit1.substring(unit1.lastIndexOf('-') + 1));
					ps1.setString(3, District);
					ps1.setString(4, village_mdate);
					ps1.setString(5, curhuma_id);
					ps1.setString(6, vcode);
					ps1.setInt(7, VILLAGE_SEQID);
					// ps1.setString(5,huma_id);
					// ps1.setString(6,email);
					// ps1.setString(7,village_ph);
					// ps1.setString(8,village_mob);
					// ps1.setString(9,vcode.substring(0,3));
					f = ps1.executeUpdate();
					if (f != 0)
						out.println("OKBSFL Village master Updated successfully");// BSFL
																					// Village
																					// master
																					// Updated
																					// successfully
					else
						out.println("BSFL Village master is not Updated for some reasons");

				}// try 3
				catch (SQLException sqle) {
					//log.warn("", sqle);
					/*if (sqle.getErrorCode() == 2292)
						out.println("FRS User Entry master cannot be Updated : Child record found for the -FRS User name");
					else*/ if (sqle.getErrorCode() == 1) {
						sqle.printStackTrace();
						out.println("Entered Village code already Exist ");

					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs != null)
						rs.close();
					if (ps != null)
						ps.close();
					if (ps1 != null)
						ps1.close();
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
