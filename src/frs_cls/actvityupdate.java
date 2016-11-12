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
 * Servlet implementation class actvityupdate
 */
public class actvityupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   Connection con=null;
	   Statement st=null,st1=null;
	   PreparedStatement ps=null,ps1=null;
	   ResultSet rs=null,rs1=null;
	   int f=0;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public actvityupdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DEbug doget");
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("DEbug dopost");
		
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

				String driver = application.getInitParameter("driver");
				String url = application.getInitParameter("url");
				String user = application.getInitParameter("user");
				String pwd = application.getInitParameter("pwd");
				Calendar ca1 =Calendar.getInstance();//from here four lines are the system date selection code
		          int dd=ca1.get(Calendar.DATE);
		          int mm=ca1.get(Calendar.MONTH)+1; // In Current date Add 1 in month
		          int yyyy=ca1.get(Calendar.YEAR);
		       String comp_mdate=dd+"-"+mm+"-"+yyyy;

				String cat= request.getParameter("cat_id");
				int i = cat.lastIndexOf('-');  
				String cat_id=cat.substring(i+1);

				System.out.println("cat id      ="+cat_id);
				String a_id1 = request.getParameter("a_id");
				i = a_id1.lastIndexOf('-');  
				String a_id=a_id1.substring(i+1);
				System.out.println("cat id      ="+a_id);

//Connection svcon=new JdbcConnect().getConnection();
String svsql="select * from actcat_mstr where actcat_id='"+cat_id+"'";
Statement svst=con.createStatement();
ResultSet svrs=svst.executeQuery(svsql);
if(!svrs.next())
{
	out.println("Entered Activity Category ID does not Exist");
	return;
}

				String a_name1= request.getParameter("a_name").trim();
/*				int i1 = a_name1.lastIndexOf('-');  
				String a_name=a_name1.substring(0,i1);
				System.out.println("Activity name was : "+a_name);
*/
/*				System.out.println(a_name);
*/				String curhuma_id=(String)((HttpServletRequest) request).getSession().getAttribute("username");
				System.out.println("user name  :"+curhuma_id);
				System.out.println(comp_mdate);
				try
				{
		        //Class.forName(driver);
				con= dbConn;
		//con = DriverManager.getConnection(url,user,pwd); 
				//System.out.println("Msr debug 1");
				//update activity_name set ACTCAT_ID=?,ACTIVITY_ID=?,activity_name=?,ACTIVITY_MBY=?,,comp_mdate=to_date(?,'dd-mm-rrrr') where ACTIVITY_ID=?");
			    ps1= con.prepareStatement("update ACTIVITY_MSTR set ACTCAT_ID=?,ACTIVITY_NAME=?,ACTIVITY_MBY=?,ACTIVITY_MDATE=to_date(?,'dd-mm-yyyy') where ACTIVITY_ID=?");
				//update comp_mstr set comp_name=?,comp_mdate=to_date(?,'dd-mm-rrrr'),comp_bnfryname=?,comp_accno=?,comp_bank=?,comp_bankbranch=?,comp_bankbranchaddr=?,comp_ifsccode=?,comp_swiftcode=?,comp_panno=?,comp_staxno=?,comp_mby=?, where comp_id=?
				ps1.setString(1,cat_id);
				//ps1.setString(2,a_id);
				ps1.setString(2,a_name1);
				ps1.setString(3,curhuma_id);
				ps1.setString(4,comp_mdate);
				ps1.setString(5,a_id);
				//System.out.println("Msr debug 2"+f);
				f=ps1.executeUpdate();
				if(f!=0)
				{
					//System.out.println("Msr debug 3");
				 out.print("OK Activity was updated successfully");
				}
				else
				{
				 //System.out.println("Msr debug 4");
				 out.print("Activity is not updated for some reasons");
				}
			    	
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
