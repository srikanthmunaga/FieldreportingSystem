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

public final class sstatusmstr extends HttpServlet {

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
		//System.out.println("inside the sstatusmstr.java file checked by Rajesh");
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
				out.print("You are not authorised to Create a State");									// login page.
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

//				String driver = application.getInitParameter("driver");
//				String url = application.getInitParameter("url");
//				String user = application.getInitParameter("user");
//				String pwd = application.getInitParameter("pwd");
				Calendar ca1 = Calendar.getInstance();// from here four lines
														// are the system date
														// selection code
				int dd = ca1.get(Calendar.DATE);
				int mm = ca1.get(Calendar.MONTH) + 1; // In Current date Add 1
														// in month
				int yyyy = ca1.get(Calendar.YEAR);
				String statusmstr_cdate = dd + "-" + mm + "-" + yyyy;

				String ststus_name = request.getParameter("status_name").trim();
				//country_id = country_id.substring(country_id.lastIndexOf("-")+1);
				//String state_id = "";// request.getParameter("state_id");
				String freeze = request.getParameter("freeze");
				String transfer = request.getParameter("transfer");
				String statusmstr_cby = (String) ((HttpServletRequest) request)
						.getSession().getAttribute("username");
				
				System.out.println("The Details are status name="+ststus_name+" Freeze="+freeze+" Transfer="+transfer);
				try {
					// Class.forName(driver);
					con = dbConn.getConnection();
						//if (x == 0) // means the specified country_id is
									// available in the country_mstr
						//{
							//ps1 = con.prepareStatement("insert into state_mstr values(select max(hrstatus_id)+1 from hrstatus_mstr,?,?,?,state_seqid.nextval,?,to_date(?,'dd-mm-rrrr'),to_date(?,'dd-mm-rrrr'),?,?)");
							System.out.println("Query Prepared");		
							ps1 = con.prepareStatement("insert into hrstatus_mstr(hrstatus_id,hrstatus_name," +
									"hrstatus_freezed,hrstatus_transfer,hrstatus_cdate,hrstatus_cby," +
									"hrstatus_seqid) " +
									"values ((select max(hrstatus_id)+1 from hrstatus_mstr),?,?,?,to_date(?,'dd-mm-rrrr'),?,HRSTATUS_SEQID.nextval)");
							ps1.setString(1, ststus_name);
							ps1.setString(2, freeze);
							ps1.setString(3, transfer);
							ps1.setString(4, statusmstr_cdate);
							ps1.setString(5, statusmstr_cby);
							//ps1.setString(6, curhuma_id);
							//ps1.setString(7, null);
							System.out.println("Before executing the Query");
							try{
							f = ps1.executeUpdate();
							
							if (f != 0)// if f3!=0 then the all the f1!=0 and
										// the selection from the
										// rs3.next!=false.means after the
										// successful insertion.
								out.println("OKHR Status master created successfully with Status Name "
										+ ststus_name);// State master created
													// successfully
							else
								out.println("HR Status Master is not created for some reasons");
							}catch(SQLException e){
								if(e.getErrorCode() == 1)
								{
									out.println("Duplicate HR Status Name \n Please Check!");
								}
							}
							ps1.close();
						
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
