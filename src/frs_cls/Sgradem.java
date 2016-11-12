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

public final class Sgradem extends HttpServlet {

	   Connection con=null;
	   PreparedStatement ps_id=null,ps=null,ps1=null,ps2=null;
	   ResultSet rs_id=null,rs=null,rs1=null,rs2=null;
	   int f=0;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 //System.out.println("inside the doGet method of Sgradem");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 //System.out.println("inside the doPost method of Sgradem");

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
				out.print("You are not authorised to Create a Grade ");	
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
				Calendar ca1 =Calendar.getInstance();//from here four lines are the system date selection code
		          int dd=ca1.get(Calendar.DATE);
		          int mm=ca1.get(Calendar.MONTH)+1; // In Current date Add 1 in month
		          int yyyy=ca1.get(Calendar.YEAR);
		       String grade_cdate=dd+"-"+mm+"-"+yyyy;

				String grade_id = "";//request.getParameter("grade_id"); 
				String grade_name = request.getParameter("grade_name").trim();
				//grade_name = grade_name.substring(grade_name.lastIndexOf("-")+1);
				//System.out.println("Grade name="+grade_name);
				
				String grade_abbreviation = request.getParameter("grade_abbreviation").trim();
				String curhuma_id=(String)((HttpServletRequest) request).getSession().getAttribute("username");
				String area_strategy = request.getParameter("area_strategy").trim();
				try
				{
		        //Class.forName(driver);
				con= dbConn.getConnection();
		//con = DriverManager.getConnection(url,user,pwd); 
		 
				
				//Automatic id generation code starts here to work with the multiuser on this form @ same time-----------------------	
				ps_id = con.prepareStatement("select grade_id from grade_mstr where grade_id=(select max(grade_id) from grade_mstr)");
				rs_id = ps_id.executeQuery();		
				if(rs_id==null) {System.out.println("rs is null in sgradem.jsp and in grade_id");}
				if(rs_id.next()){ 
				do
				{
				 String s=(rs_id.getString(1)).substring(0,1);
				 int n=Integer.parseInt((rs_id.getString(1)).substring(1));
				 n=n+1;
				 if(n<=9)
					 grade_id=s+"0"+n;
				 if((n>=10) && (n<=99))
					 grade_id=s+n; 
				 if(n>99)
					 throw new Exception(); 
				 }while(rs_id.next());//while
				}//if
				else 
				  grade_id="G01";
				//Automatic id generation code ends here ------------------------------------------------------------------------------
				
				//System.out.println("hey the grade_id="+grade_id);
				ps = con.prepareStatement("select grade_id from grade_mstr where grade_id=?");
				ps.setString(1,grade_id);
				
				rs = ps.executeQuery();
				if(rs==null)
		         System.out.println("result set is null");
		   		if((rs.next())==false)
				{   
		       System.out.println("grade_id="+grade_id+" grade_name="+grade_name+" grade_abbreviation="+grade_abbreviation+" grade_cdate="+grade_cdate+" curhuma_id="+curhuma_id);
			    ps1= con.prepareStatement("insert into grade_mstr values(?,grade_seqid.nextval,?,?,?,to_date(?,'dd-mm-rrrr'),to_date(?,'dd-mm-rrrr'),?,?)");
		       //ps1= con.prepareStatement("insert into grade_mstr " +"(GRADE_ID,GRADE_SEQID,GRADE_NAME,GRADE_ABBREVIATION,GRADE_LEVEL,GRADE_CDATE,GRADE_MDATE,GRADE_CBY,GRADE_MBY )"+
		       	//	"values(?,grade_seqid.nextval,?,?,to_date(?,'dd-mm-rrrr'),to_date(?,'dd-mm-rrrr'),?,?,?)");
			    ps1.setString(1,grade_id);
				ps1.setString(2,grade_name);
				ps1.setString(3,grade_abbreviation);
				ps1.setString(4,area_strategy);
				ps1.setString(5,grade_cdate);
				ps1.setString(6,null);
				ps1.setString(7,curhuma_id);
				ps1.setString(8,null);
		       
				f=ps1.executeUpdate();
				if(f!=0)
					out.println("OKGrade master created successfully & Grade id= "+grade_id);//Grade master created successfully
				else
					out.println("Grade master is not created for some reasons");
						
		     	ps1.close();
				}
				else
					out.println("Already existing one.To differ press refresh and continue");
			
			 }//try 
		   catch(Exception e) { e.printStackTrace();} 
		   finally 
			{
			 if(ps_id!=null)ps_id.close();
			 if(rs_id!=null)rs_id.close();
			 if(ps!=null)ps.close();
			 if(rs!=null)rs.close();
			 if(ps1!=null)ps1.close();
			 if(rs1!=null)rs1.close();
			 if(ps2!=null)ps2.close();
			 if(rs2!=null)rs2.close();
		     if(con!=null)con.close();  
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
