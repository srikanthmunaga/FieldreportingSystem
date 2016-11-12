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

public final class Issueindent extends HttpServlet {

	   Connection con=null;
	   PreparedStatement ps=null;
	   PreparedStatement ps1=null,ps2=null;
	   ResultSet rs=null,rs2=null;
	   int f=0;
	   int g=0;

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
				out.print("You are not authorised to Create a Unit");									// login page.
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
		       String bsflunit_cdate=dd+"-"+mm+"-"+yyyy;
		        
				String bsflunit_ucode = request.getParameter("bsflunit_ucode").trim(); //common for each insertion
				String unit_name = request.getParameter("unit_name");
				String lis=request.getParameter("last_issued");
				String clostock=request.getParameter("closing_stock");
				String available=request.getParameter("available_stock");
				String issue=request.getParameter("issue_stock");
				String dis=request.getParameter("date_is");
				String couid=request.getParameter("courier_id");
				String cname=request.getParameter("courier_name");
				
//				String sta_id ="Y";
//				sta_id=request.getParameter("st_id").trim();
//				System.out.println("stationary id is :"+sta_id);
//				String sta_name = request.getParameter("sta_name");
//				String doe = request.getParameter("date_oe");
//				String open_stock = request.getParameter("o_stock");
//				String close_stock = request.getParameter("c_stock");
//				String new_stock = request.getParameter("n_stock");
//				String inde_by = request.getParameter("in_by");
//				String pre_by = request.getParameter("p_by");
				//String huma_id1=request.getParameter("huma_id").trim();
				/*int j = huma_id1.lastIndexOf('-');  
				String huma_id=huma_id1.substring(j+1);*/
			String unit_code=null;
				String unitcode=null;
				
			unitcode=bsflunit_ucode.substring(bsflunit_ucode.lastIndexOf("-")+1);
				unit_code=unitcode;
//				System.out.println("unit code= "+unit_code);
//				String curhuma_id=(String)((HttpServletRequest) request).getSession().getAttribute("username");		
//				String area1=request.getParameter("busiid").trim();
//				int i = area1.lastIndexOf('-');  
//				String area=area1.substring(i+1);
//				System.out.println("Region Name="+area);
				//System.out.println("bsflunit_ucode="+bsflunit_ucode.substring(0,3));
				
//				String huma_id1=request.getParameter("huma_id").trim();
				/*int j = huma_id1.lastIndexOf('-');  
				String huma_id=huma_id1.substring(j+1);*/
//				String huma_id=null;
//				String humaid=null;
				/*StringTokenizer stkz = new StringTokenizer(huma_id1);
				while (stkz.hasMoreElements()) {
					humaid=stkz.nextElement().toString();
				}*/
//				humaid=huma_id1.substring(huma_id1.lastIndexOf("-")+1);
//				huma_id=humaid;
//				System.out.println("humaID="+humaid);
			
//				System.out.println("TEST checking");
//				String email=request.getParameter("unit_email").trim();
//				String unit_ph=request.getParameter("unit_phno").trim();
//				String unit_mob=request.getParameter("unit_mob").trim();
//				System.out.println("email="+email+" unit_ph="+unit_ph+" unit_mob="+unit_mob);
				try {
			        //Class.forName(driver);
					con= dbConn.getConnection();
			//con = DriverManager.getConnection(url,user,pwd); 
			 
					/*
					 * Codded by rajesh 
					 * server side validation to implement user roles
					 * 
					 */
					
//					String huma_id2=request.getSession().getAttribute("huma_id").toString();
//					String role=request.getSession().getAttribute("userrole").toString();
//					String areano1=null;
//					if(role.equals("areahead"))
//					{
						//To find out the area_id of the loggedin user
//					ps = con.prepareStatement("select area_id from area_mstr where huma_id='"+huma_id2+"'");
//					rs = ps.executeQuery();
//					if(rs!=null)
//					  {
//						while(rs.next())
//					       {
//						      areano1=rs.getString(1);
//					       }//while
//					  }//if
//					if(!areano1.equals(area))
//					{
//						out.println("You are not allowed to create unit of other Region");
//						return;
//						//failrd.include(request, response);
//						//return;
//					}
//					System.out.println("Region= "+area+" Region1= "+areano1);
//					}//if(role.equals("areahead"))
//					/*
//					 * Code completed
//					 * 
//					 *					Statement st = con.createStatement();
//					String sql="select bsflunit_name from bsflunit_mstr where bsflunit_ucode='"
//							+ unit_code + "'";
//					Statement st=con.createStatement();
//					ResultSet rs = st.executeQuery(sql);
//					if (rs != null) {
//						while (rs.next()) {
//							
//							bsflunit_name = rs.getString(1);
//						}
//						System.out.println("the name is"+ bsflunit_name);
//					ps = con.prepareStatement("select * from BSFLUNIT_MSTR where bsflunit_ucode=?");//select * from BSFLUNIT_MSTR where upper(bsflunit_ucode)= upper('business development')
//					ps.setString(1,bsflunit_ucode.substring(0,3));
//					
//					rs = ps.executeQuery();
//					if(rs==null)
//			         System.out.println("result set is null");
//					int r=1;
//					if ((rs.next()) == false)
//						//r = r + 1;
//						r=0;
//					System.out.println("R value="+r);
//					//Added By Rajesh
//					int p = 0;
//					ps = con.prepareStatement("select area_ID from area_MSTR where area_ID=?");
//					ps.setString(1, area);
//					rs = ps.executeQuery();
//					if (rs == null)
//						System.out.println("result set is null in ubm.jsp");
//					if ((rs.next()) == false)
//						p = p + 1;
//					
//					System.out.println("Value of P="+p);
//					
//					
//					int q = 0;
//					ps = con.prepareStatement("select USERNAME from FRS_USER where HUMA_ID=?");
//					//String humaaid=humaid.substring(humaid.lastIndexOf("-")+1);
//					ps.setString(1, humaid);
//					//ps.setString(1, humaaid);
//					rs = ps.executeQuery();
//					if (rs == null)
//						System.out.println("result set is null in ubm.jsp");
//					if ((rs.next()) == false)
//						q = q + 1;
//					
//					System.out.println("Value of q="+q);
//					
//					//Rajesh complete
//			   		//if(((rs.next())==false)&&(p == 0)&&(q == 0))
					int x = 0;
					ps = con.prepareStatement("select bsflunit_ucode from bsflunit_mstr where bsflunit_ucode=?");
					//ps.setString(1, comp_id.substring(0, 4));
					ps.setString(1, unit_code);
					rs = ps.executeQuery();
					if (rs == null)
						System.out.println("result set is null in ubm.jsp");
					if ((rs.next()) == false)
						x = x + 1; System.out.println("hey the x="+x);
					if(x==0)
					{
					con.setAutoCommit(false);
//			        System.out.println("HERE R P Q are zero");		
//					ps=con.prepareStatement("select unit_code from unit_msrt where ")
				    ps1= con.prepareStatement("insert into ISSUE_INDENT values(?,?,?,?,?,?,to_date(?,'dd-mm-rrrr'),?,?)");
//					ps1.setString(1,bsflunit_ucode.substring(0,3));
//					ps1.setString(2,bsflunit_name);
					ps1.setString(1,unit_code);
//					request.getSession().setAttribute("st_id",sta_id);
					ps1.setString(2,unit_name);
					ps1.setString(3,lis);
					ps1.setString(4,clostock);
					ps1.setString(5,available);
					ps1.setString(6,issue);
					ps1.setString(7,dis);
					ps1.setString(8,couid);
					ps1.setString(9,cname);
			
					//System.out.println("hey the PreparedStatement made");
					int f=ps1.executeUpdate();
					
					
					if(f!=0){  
						con.commit();
			            
			            out.println("OKindent for  " +couid+ " has been sucessfully sent");  


			          }  
			          else{  
			            
			            out.println("indent has not been placed for different reasons");
			           }  
//					ps1= con.prepareStatement("update huma_MSTR set bsflunit_ucode=? where huma_id=?");
//					ps1.setString(1,bsflunit_ucode);
////					ps1.setString(2,huma_id);
//					g=ps1.executeUpdate();
//					if(f!=0 && g!=0){
//						con.commit();
//						out.println("OKBSFL Unit master created successfully");//BSFL Unit master created successfully
//					}else{
//						con.rollback();
//						out.println("BSFL Unit master is not created for some reasons");
//					}		
			     	ps1.close();
					//}//count(*)>=12 else
					}
					else
						out.println("entered unit code doesnot exist");
					//main if((rs.next())==false)
					
							// entered company not exits
//							out.println("Entered Unit Incharge ID does not Exist");
				 
				}//try 
			   catch(Exception e) { e.printStackTrace();} 
			   finally 
				{
				 if(rs!=null)rs.close();
			     if(ps!=null)ps.close();
				 if(rs2!=null)rs2.close();
			     if(ps2!=null)ps2.close();
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
