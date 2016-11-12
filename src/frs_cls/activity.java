package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

/**
 * Servlet implementation class activity
 */
public class activity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	Connection con = null;
	Statement st = null, st1 = null;
	PreparedStatement ps_id = null, ps = null, ps1 = null;
	ResultSet rs_id = null, rs = null, rs1 = null;
	int f = 0;
	
    public activity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
			

			if (((HttpServletRequest) request).getSession()
					.getAttribute("username") == null) {
				response.sendRedirect("frslogin.jsp"); // Not logged in, redirect to
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
				String comp_cdate = dd + "-" + mm + "-" + yyyy;

				String ACTCAT_ID = "";// request.getParameter("ACTCAT_ID");
				String actcat= request.getParameter("cat_id").trim();
				System.out.println("before Splitting Category id was :"+actcat);
				int i = actcat.lastIndexOf('-');  
				String cat=actcat.substring(i+1);
				System.out.println("Splitted category id was :"+cat);
				
				
				String svsql="select * from actcat_mstr where actcat_id='"+cat+"'";
				Statement svst=con.createStatement();
				ResultSet svrs=svst.executeQuery(svsql);
				if(!svrs.next())
				{
					out.println("Entered Activity Category ID does not Exist");
					return;
				}

				
				//String value=actcat.substring(i+1);

				String ac_name = request.getParameter("a_name").trim();
				String user_name = (String)((HttpServletRequest) request).getSession().getAttribute("username");
				
				
//				server side validations
				

				try {
					// Class.forName(driver);
					con = dbConn.getConnection();
					// con = DriverManager.getConnection(url,user,pwd);

					// Automatic id Generation code to allow multiuser to work
					// with the form @ same time----------
					ps_id = con.prepareStatement("select ACTIVITY_ID from ACTIVITY_MSTR where ACTIVITY_ID=(select max(ACTIVITY_ID) from ACTIVITY_MSTR)");
					rs_id = ps_id.executeQuery();
					if (rs_id == null) {
						System.out.println("rs is null in actcat and in ACTCAT_ID");
					}
					if (rs_id.next()) {
						do {
							String s = (rs_id.getString(1)).substring(0, 1);
							int n = Integer.parseInt((rs_id.getString(1)).substring(1));
							n = n + 1;
							if (n <= 9)
								ACTCAT_ID = s + "00" + n;
							if ((n >= 10) && (n <= 99))
								ACTCAT_ID = s + "0" + n;
							if ((n >= 100) && (n <= 999))
								ACTCAT_ID = s + n;
							if (n > 999)
								throw new Exception();
						} while (rs_id.next());// while
					}// if
					else
						ACTCAT_ID = "A001";
					// Automatic id Generation code ends
					// here--------------------------------------------------------

					ps = con.prepareStatement("select ACTIVITY_ID from ACTIVITY_MSTR where ACTIVITY_ID=?");
					ps.setString(1, ACTCAT_ID);

					rs = ps.executeQuery();
					if (rs == null)
						System.out.println("result set is null");
					if ((rs.next()) == false) {
						System.out.println(ACTCAT_ID);
						ps1 = con.prepareStatement("insert into ACTIVITY_MSTR(ACTIVITY_ID,ACTIVITY_NAME,ACTCAT_ID,ACTIVITY_MDATE,ACTIVITY_CBY ,ACTIVITY_MBY ) values(?,?,?,to_date(?,'dd-mm-yyyy'),?,?)");
						ps1.setString(1, ACTCAT_ID);
						ps1.setString(2,ac_name );
						ps1.setString(3,cat);
						ps1.setString(4, null);
						//ps1.setString(4, comp_cdate);
						ps1.setString(5,user_name);
						ps1.setString(6, null);

						f = ps1.executeUpdate();
						if (f != 0) {
							out.print("OK Activity  Created successfully & category id = "+ ACTCAT_ID);
						} else {
							out.print("Activity is not created for some reasons");
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
					if(con!=null)con.close();

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
