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
 * Servlet implementation class actcatupdate
 */
public class actcatupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   Connection con=null;
	   Statement st=null,st1=null;
	   PreparedStatement ps=null,ps1=null;
	   ResultSet rs=null,rs1=null;
	   int f=0;

   
    public actcatupdate() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	System.out.println("msr");
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
			//System.out.println("Msr debug OUT SIDE ELSE SESSION CHECKING");
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
//System.out.println("Msr debug IN SIDE ELSE SESSION CHECKING");
				Connection dbConn = null;
				synchronized (request) {
					dbConn = (Connection) _jspx_page_context.getAttribute(
							"dbConn", PageContext.REQUEST_SCOPE);
					if (dbConn == null) {
						dbConn = new JdbcConnect().getConnection();
						_jspx_page_context.setAttribute("dbConn", dbConn,
								PageContext.REQUEST_SCOPE);
					}
				}

				/*String driver = application.getInitParameter("driver");
				String url = application.getInitParameter("url");
				String user = application.getInitParameter("user");
				String pwd = application.getInitParameter("pwd");
				*/Calendar ca1 =Calendar.getInstance();//from here four lines are the system date selection code
		          int dd=ca1.get(Calendar.DATE);
		          int mm=ca1.get(Calendar.MONTH)+1; // In Current date Add 1 in month
		          int yyyy=ca1.get(Calendar.YEAR);
		       String comp_mdate=dd+"-"+mm+"-"+yyyy;

				String cat_id = request.getParameter("cat_id");
/*				int i = cat.lastIndexOf('-');  
				String cat_id=cat.substring(0, i);
*/
				//String a_id = request.getParameter("a_id");
				String a_name= request.getParameter("ac_name").trim();
				String curhuma_id=(String)((HttpServletRequest) request).getSession().getAttribute("username");

				try
				{
		        //Class.forName(driver);
				con= dbConn;
		//con = DriverManager.getConnection(url,user,pwd); 
				System.out.println("updating");
				//update activity_name set ACTCAT_ID=?,ACTIVITY_ID=?,activity_name=?,ACTIVITY_MBY=?,,comp_mdate=to_date(?,'dd-mm-rrrr') where ACTIVITY_ID=?");
			    ps1= con.prepareStatement("update ACTCAT_MSTR set ACTCAT_NAME=?,ACTCAT_MBY=?,ACTCAT_MDATE=to_date(?,'dd-mm-yyyy') where ACTCAT_ID=?");
				ps1.setString(1,a_name);
				ps1.setString(2,curhuma_id);
				ps1.setString(3,comp_mdate);
				ps1.setString(4,cat_id);
						
				f=ps1.executeUpdate();
				if(f!=0)
				 out.print("OKActivity category was updated successfully");
				else
				 out.print("Activity category is not updated for some reasons");
			    	
			 }//try 
		   catch(Exception e) { e.printStackTrace();} 
		   finally 
			{
			 if(st!=null)st.close();
			 if(ps!=null)ps.close();
			 if(rs!=null)rs.close();
			 if(st1!=null)st1.close();
			 if(ps1!=null)ps1.close();
			 if(rs1!=null)rs1.close();
		     if(con!=null)con.close(); 
		     if(dbConn!=null)dbConn.close();

		    }
		   }//authorised acess else

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
