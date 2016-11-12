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
import java.util.StringTokenizer;

public final class UnitUpdate extends HttpServlet {

	Connection con = null;
	PreparedStatement ps = null, ps1 = null;;
	// Statement st=null;
	ResultSet rs = null, rs1 = null;
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
				out.print("You are not authorised to Modify the Region Details");									// login page.
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
				String area_mdate = dd + "-" + mm + "-" + yyyy;
				
				
				String unit_code=request.getParameter("bsflunit_ucode");
				unit_code = unit_code.substring(unit_code.lastIndexOf("-")+1);
				System.out.println("unit code is"+unit_code);
				String unit_namee=request.getParameter("unit_name");
				System.out.println(unit_namee);
				String sta_id = request.getParameter("st_id");
				System.out.println(sta_id);
				
				String stat_name = request.getParameter("sta_name").trim();
				System.out.println(stat_name);
                String date_of_indent=request.getParameter("date_oi").trim();
                System.out.println(date_of_indent);
                //                String open_stock=request.getParameter("o_stock");
                String close_stock=request.getParameter("c_stock");
                System.out.println(close_stock);
                String new_stock=request.getParameter("n_stock");
                System.out.println(new_stock);
                String inde_by=request.getParameter("in_by");
                System.out.println(inde_by);
                String pre_by=request.getParameter("p_by");
                System.out.println(pre_by);
	      		//String huma_id1=request.getParameter("huma_id");
				
				/*int i = huma_id1.lastIndexOf('-');  
				String huma_id=huma_id1.substring(i+1);
				*/
				
			//	String huma_id=null;
				//StringTokenizer stkz = new StringTokenizer(huma_id1);
				//while (stkz.hasMoreElements()) {
					//huma_id=stkz.nextElement().toString();
				//}
				//System.out.println("huma_id= "+huma_id);
			//	comp_id = comp_id.substring(comp_id.lastIndexOf("-")+1);
				//area_id = area_id.substring(area_id.lastIndexOf("-")+1);
				//huma_id = huma_id1.substring(huma_id1.lastIndexOf("-")+1);
				//String curhuma_id = (String) ((HttpServletRequest) request)
					//	.getSession().getAttribute("username");

				try {// System.out.println("hey inside the try and going to check the availability of the comp_id");
						// Class.forName(driver);
					con = dbConn.getConnection();
					// con = DriverManager.getConnection(url,user,pwd);
					System.out.println("connection succesfull");

					int x = 0;
					ps = con.prepareStatement("select s_id from stationary_mstr where s_id=?");
					ps.setString(1, sta_id);
					rs = ps.executeQuery();
					if (rs == null)
						System.out.println("result set is null in ubm.jsp");
					if ((rs.next()) == false)
						x = x + 1; // System.out.println("hey the x="+x);

					if (x == 0) {
						ps1 = con
								.prepareStatement("update UNIT_INDE set bsflunit_name=?,s_id=?,s_name=?,date_of_indent=to_date(?,'dd-mm-rrrr'),clos_stock=?,new_stock=?,in_by=?,pre_by=? where bsflunit_ucode=?");
						//ps1.setString(1, comp_id.substring(0, 4));
						ps1.setString(1, unit_namee);
						ps1.setString(2, sta_id);
						ps1.setString(3, stat_name);
						ps1.setString(4, date_of_indent);
//						ps1.setString(5, open_stock);
						ps1.setString(5, close_stock);
						ps1.setString(6, new_stock);
						ps1.setString(7, inde_by);
						ps1.setString(8, pre_by);
						ps1.setString(9, unit_code);
						//ps1.setString(6, area_strategy);
//						ps1.setString(7, huma_id);
//						ps1.setString(8, area_lang);
//						ps1.setString(9, area_id);

						f = ps1.executeUpdate();
						if (f != 0)
							out.println("OKStationary master Updated successfully");
						else
							out.println("stationary master is not Updated for some reasons");
					}// if((x==0)
					else
						// entered company not exits
						out.println("Entered stationaryid does not Exist");
				}// try
				catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs != null)
						rs.close();
					if (ps != null)
						ps.close();
					if (rs1 != null)
						rs1.close();
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
