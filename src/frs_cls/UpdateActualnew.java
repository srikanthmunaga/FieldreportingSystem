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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public final class UpdateActualnew extends HttpServlet {

	   Connection con=null;
	   PreparedStatement ps=null,ps1=null,ps2=null,ps3=null,ps4=null,ps5=null,ps6=null,ps7=null,ps8=null,ps9=null,ps10=null;
	   ResultSet rs=null,rs2=null,rs3=null,rs4=null,rs5=null,rs6=null,rs7=null,rs8=null,rs9=null,rs10=null;
	   int f=0,e=0; boolean f2=false;
		private StringTokenizer st = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// //System.out.println("inside the doGet method");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	System.out.println("inside the doPost method");

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

				/*Connection dbConn=null;
				synchronized (request) {
					dbConn = (Connection) _jspx_page_context.getAttribute("dbConn", PageContext.REQUEST_SCOPE);
					if (dbConn == null) {
						dbConn = new JdbcConnect().getConnection();
						_jspx_page_context.setAttribute("dbConn", dbConn,PageContext.REQUEST_SCOPE);
					}
				}*/
				
				con=new JdbcConnect().getConnection();
				String driver = application.getInitParameter("driver");
				String url = application.getInitParameter("url");
				String user = application.getInitParameter("user");
				String pwd = application.getInitParameter("pwd");
				Calendar ca1 =Calendar.getInstance();//from here four lines are the system date selection code
		          int dd=ca1.get(Calendar.DATE);
		          int mm=ca1.get(Calendar.MONTH)+1; // In Current date Add 1 in month
		          int yyyy=ca1.get(Calendar.YEAR);
		       String war_mdate=dd+"-"+mm+"-"+yyyy;
			//System.out.println("the parameters going to read");
		       String username = (String) request.getSession().getAttribute(
						"username");
				//HashSet<String> smsDates = new HashSet<String>();
				String BSFLUNIT_UCODE = request.getParameter("BSFLUNIT_UCODE").trim();
				//UHLOG_CONTROLENO2
				String controlno=request.getParameter("UHLOG_CONTROLENO2").trim();
				System.out.println("controlno is :"+controlno);
				String name_of_statehead=request.getParameter("name_sh");
				System.out.println("The name of state head is "+name_of_statehead);
				String region=request.getParameter("reg");
				System.out.println("the region is "+region);
				String category[]=request.getParameterValues("act_id");
				
				//String WAR_POD = request.getParameter("pod").trim();
				//reason
				//String war_reason  = request.getParameter("war_reason").trim();

				// System.out.println("UNIT CODE " + ucode);
				String name_of_unitvisited[]=request.getParameterValues("unit_visited");
				String visited_with[]=request.getParameterValues("visited_with");
				String remarks[]=request.getParameterValues("observations");
				String war_date[] = request.getParameterValues("war_date");
				String huma_id[] = request.getParameterValues("huma_id");
				String huma_id21 = huma_id[0];
				huma_id21 = huma_id21.substring(huma_id21.lastIndexOf('-') + 1);
				//WAR_Lsrbacklogs 
				//String WAR_Lsrbacklogs  = request.getParameter("WAR_Lsrbacklogs").trim();
			
				// System.out.println(war_date);
				// System.out.println("LSR field legth  :" + war_date.length);
				String war_cust_count[] = request
						.getParameterValues("war_cust_count");
				String war_sdrcust_count[] = request
						.getParameterValues("war_sdrcust_count");
				//String war_sdrcust_count_opted[] = request.getParameterValues("war_sdrcust_count_opted");
				String war_villages[] = request.getParameterValues("war_villages");
				for (int i = 0; i < war_date.length; i++) {
					war_date[i] = war_date[i].trim();
					war_cust_count[i] = war_cust_count[i].trim();
					war_sdrcust_count[i] = war_sdrcust_count[i].trim();
					category[i]=category[i].trim();
					name_of_unitvisited[i]=name_of_unitvisited[i].trim();
					System.out.println("name of unit visited is "+name_of_unitvisited[i]);
					//vis[i]=
					//System.out.println("the uni tis "+vis[i]);
					visited_with[i]=visited_with[i].trim();
					remarks[i]=remarks[i].trim();
					
					//war_sdrcust_count_opted[i] = war_sdrcust_count_opted[i].trim();
				} // for
				for (int i = 0; i < war_date.length; i++) {
					String svsql1 = "select huma_id from HUMA_MSTR where HUMA_ID='"
							+ huma_id21 + "' and BSFLUNIT_UCODE='"+BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-') + 1)+"'";
					Statement svst1 = con.createStatement();
					ResultSet svrs1 = svst1.executeQuery(svsql1);
					if (!svrs1.next()) {
						out.println("Entered Field Staff code does not Exist / does not belongs to particular Unit:"	+ huma_id21);
						return;
					}// if
		
				} // for

				/*
				 * Added by rajesh 
				 * code for server side validation to implement user roles 
				 * 
				 * 
				 */
				
				//String huma_id=request.getParameter("emp_no");
			//	String Username=request.getSession().getAttribute("username").toString();
				//String role=request.getSession().getAttribute("userrole").toString();
				//System.out.println("In suhlogm.java");
				
				/*String area1="";
				String area2="";
			//	String area3="";
				String area=request.getParameter("area_name").trim();
				area=area.substring(area.lastIndexOf("-")+1);
				String unit=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf("-")+1);
				//System.out.println("huma_id="+huma_id+" UserName="+Username+" role="+role+" area="+area+" unit="+unit);
				////System.out.println("inside the suhlogm.java and area= "+area);
				//Connection areacon=new JdbcConnect().getConnection();
				Statement areast=con.createStatement();
				//For AreaHead
				ResultSet arears;
				String result="false";
				if(role.equals("unithead")){
					//arears=areast.executeQuery("select area_id from area_mstr where huma_id='"+huma_id+"'");
					//System.out.println("In UnitHead role");
					arears=areast.executeQuery("select bsflunit_ucode from bsflunit_mstr where huma_id='"+huma_id+"'");
					if(arears!=null)
					  {
						//System.out.println("rs is not null");
					     while(arears.next())
					       {
					    	 
					    	 area1=arears.getString(1);
					    	 if(area1.equals(unit))
					    	 {
					    		 result="true";
					    		 break;
					    	 }//if
					    	 else
					    	 {
					    		 result="false";
					    	 }//else
					       }//while
					  }//if
					//********************************
					//System.out.println("area1="+area1);
					//if(!area1.equals(unit))
					if(!result.equals("true"))
					{
						//System.out.println("area3 and area2 are not equal");
						out.println("You are not allowed to Update the data of another unit");
						return;
					}
					
					
				}
				
				if(role.equals("areahead")){
					
					//********************************
					arears=areast.executeQuery("select area_id from bsflunit_mstr where bsflunit_ucode='"+unit+"'");
					if(arears!=null)
					  {
						//System.out.println("rs is not null");
					     while(arears.next())
					       {
					    	 
					    	 area2=arears.getString(1);
					       }//while
					  }//if
					arears=areast.executeQuery("select area_id from area_mstr where huma_id='"+huma_id+"'");
					if(arears!=null)
					  {
						//System.out.println("rs is not null");
					     while(arears.next())
					       {
					    	 
					    	 area1=arears.getString(1);
					    	 if(area1.equals(area2))
					    	 {
					    		 result="true";
					    		 break;
					    	 }//if
					    	 else
					    	 {
					    		 result="false";
					    	 }//else
					       }//while
					  }//if
					//if(!area1.equals(area2))
					if(!result.equals("true"))
					{
						//System.out.println("area3 and area2 are not equal");
						out.println("You are not allowed to Update the data of another Region");
						return;
					}
					
				}
				*/
				//*******************************************
				/*
				String huma_id=request.getSession().getAttribute("huma_id").toString();
				String Username=request.getSession().getAttribute("username").toString();
				String role=request.getSession().getAttribute("userrole").toString();
				String area1="";
				String area2="";
				String area3="";
				String area=request.getParameter("area_name").trim();
				area=area.substring(area.lastIndexOf("-")+1);
				////System.out.println("inside the suhlogm.java and area= "+area);
				Connection areacon=new JdbcConnect().getConnection();
				Statement areast=areacon.createStatement();
				//For AreaHead
				ResultSet arears=areast.executeQuery("select area_id from area_mstr where huma_id='"+huma_id+"'");
				if(arears!=null)
				  {
					System.out.println("rs is not null");
				     while(arears.next())
				       {
				    	 //System.out.println("inside the while loop");
				    	 area1=arears.getString(1);
				       }//while
				  }//if
				//for UnitHead
				
				arears=areast.executeQuery("select area_id from bsflunit_mstr where bsflunit_ucode=(select bsflunit_ucode from huma_mstr where huma_id='"+huma_id+"')");
				if(arears!=null)
				  {
					System.out.println("rs is not null");
				     while(arears.next())
				       {
				    	 //System.out.println("inside the while loop");
				    	 area3=arears.getString(1);
				       }//while
				  }//if
				
				//To get the area_id of the entered unitno
				String chkunit=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf("-")+1);
				arears=areast.executeQuery("select area_id from bsflunit_mstr where bsflunit_ucode='"+chkunit+"'");
				if(arears!=null)
				  {
				     while(arears.next())
				       {
				    	 area2=arears.getString(1);
				       }//while
				  }//if
				//System.out.println("inside the suhlogm and area1= "+area1+" area2= "+area2+" area3= "+area3);
				if(role.equals("areahead"))
				{
					////System.out.println("inside the areahead role");
				//if(area1!=area2)
					if(!area1.equals(area2))
				{
					//System.out.println("area1 and area2 are not equal");
					out.println("You are not allowed to do entry of another unit");
					return;
				}
				}//areahead
				if(role.equals("unithead"))
				{
					//System.out.println("inside the unithead role");
					if(!area3.equals(area2))
					{
						//System.out.println("area3 and area2 are not equal");
						out.println("You are not allowed to do entry of another unit");
						return;
					}
					}//unithead	
				*/
				
				
				//********************************************
				/*  
				 * code completed
				 * 
				 */
				
				for (int i = 0; i < war_villages.length; i++) {
					String village = war_villages[i];
					String cache = "";
					if (village.contains("::")) {
						st = new StringTokenizer(village, "::");
						while (st.hasMoreElements()) {
							// //System.out.println("Msr debug in loop");
							String txt = (String) st.nextElement();
							int i1 = txt.lastIndexOf('-');
							String vcode = txt.substring(i1 + 1);
							cache += vcode + "::";
						}
						int length = cache.length();
						war_villages[i] = cache.substring(0, length - 2);
					} else {
						int i1 = village.lastIndexOf('-');
						String vcode = village.substring(i1 + 1);
						war_villages[i] = vcode;
						System.out.println("the villages visited are"+war_villages[i]);
					}
				}
				//String UHLOG_CONTROLENO2 = request.getParameter("UHLOG_CONTROLENO2");
				//dynamic cields need to checked for their existiance but the common fields can be directly caught in exeception
				String dates[];//used to get the UHLOG_desc
				String curBSFLUNIT_UCODE=(String)((HttpServletRequest) request).getSession().getAttribute("user");
				
				String uno=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-')+1);
			
				String svsql="select * from BSFLUNIT_MSTR where BSFLUNIT_UCODE='"+uno+"'";
				Statement svst=con.createStatement();
				ResultSet svrs=svst.executeQuery(svsql);
				if(!svrs.next())
				{
					out.println("Entered Unit does not Exist ");
					return;
				}
				ps8 = con.prepareStatement("select distinct to_char(war_cdate,'dd-mm-yyyy hh:mi:ss am'),war_cby from actual_plannew where war_controlno=?");//getting the cdate,cby  from previus rows
				ps8.setString(1,controlno);
				rs8 = ps8.executeQuery();//System.out.println("hey going to next the rs8 and controleno="+UHLOG_CONTROLENO2);		
				rs8.next();
		//Query to delete all the existing rows with current UHLOG_controleno
		        ps6= con.prepareStatement("delete from actual_plannew where war_controlno=?");//delete from uhlog_service where BSFLUNIT_UCODE=? and UHLOG_DATE=to_date(?,'dd-mm-yyyy')/deleting existing rows.
				ps6.setString(1,controlno);//ps6.setString(2,UHLOG_DATE);
				f2=ps6.execute();//del
				try
				{
					ps1 = con.prepareStatement("insert into ACTUAL_PLANNEW(WAR_DATE,HUMA_ID,NAME_SH,REGION,ACTIVITY_ID,WITH_UH,WAR_VILLAGES,WAR_CUST_COUNT,WAR_SDRCUST_COUNT,BSFLUNIT_UCODE,WAR_OBSERVATIONS,WAR_CBY,WAR_CONTROLNO,UNIT_VISITED) "//,WAR_POD
							+ "values(to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,?,?,?,?,?,?,?)");
			// System.out.println("Total no of rows= " + war_date.length);
			// for (int i = 0; i < war_date.length; i++) {
			for (int i = 0; i < war_date.length; i++) {
				ps1.setString(1, war_date[i]);
				ps1.setString(2, huma_id21);
				ps1.setString(3,name_of_statehead);
				ps1.setString(4,region);
				ps1.setString(5, category[i]);
				
				ps1.setString(6,visited_with[i]);
				ps1.setString(7, war_villages[i]);// war_cust_count
				ps1.setString(8, war_cust_count[i]);
				ps1.setString(9, war_sdrcust_count[i]);
				ps1.setString(10,BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-')+1));
				//ps1.setString(7,sty[i]);
				ps1.setString(11,remarks[i]);
				///ps1.setLong(6, Integer.parseInt(war_sdrcust_count_opted[i]));
				ps1.setString(12, username);
				ps1.setString(13,controlno);
				ps1.setString(14,name_of_unitvisited[i]);
				//System.out.println(WAR_POD);
				//ps1.setLong(8, Long.parseLong(WAR_POD));
				//System.out.println(war_reason);
				//ps1.setString(10, war_reason);//
				//ps1.setString(10, WAR_Lsrbacklogs);//
				// //////System.out.println("Msr debug beforer executing");
				f = ps1.executeUpdate();
			}
				System.out.println("the f si " +f);
				////System.out.println("Msr debug after executing-uuhlogm");

				

				if(f!=0)
					out.println("OK Actual plan Entry updated successfully");//fae master created successfully
				else
				    out.println("Actual Plan Activity Entry is not updated for some reasons");
									
		     	ps1.close();
			 }//try 
		 catch(SQLException sqle) 
		 { 
		    if(sqle.getErrorCode()==2292)out.println("UH Activity Entry cannot be Updated : Child record found for the -UH Activity Entry id"); 
			else if(sqle.getErrorCode()==1)
			{ //UHLOG_HUMA_ACTIVITYSL_PLACEUQ
			/*	if(sqle.getMessage().split("_")[1].trim().equals("HUMA) violated - parent key not found"))
					out.println("Entered Employee id does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("ACTIVITYSL) violated - parent key not found"))
					out.println("Entered Activity does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("VILLAGE) violated - parent key not found"))
					out.println("Entered Village id does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("BLOCK) violated - parent key not found"))
					out.println("Entered Block id does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("SUBUNIT) violated - parent key not found"))
					out.println("Entered Subunit id does not Exist");
				else */out.println("Duplicate Farmer Activity Entries found,Pls differ either Activity or Place");
			}//else if(sqle.getErrorCode()==1)
			else if(sqle.getErrorCode()==2291)
			 { sqle.printStackTrace();
			 	out.println("the error msg="+sqle.getMessage()+"A");//.split(".")[1].trim(); out.println("the error msg="+sqle.getMessage().split(".")[1].trim()+"A");
				if(sqle.getMessage().split("_")[1].trim().equals("HUMA) violated - parent key not found"))
					out.println("Entered Employee id does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("ACTIVITYSL) violated - parent key not found"))
					out.println("Entered Activity does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("VILLAGE) violated - parent key not found"))
					out.println("Entered Village id does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("BLOCK) violated - parent key not found"))
					out.println("Entered Block id does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("SUBUNIT) violated - parent key not found"))
					out.println("Entered Subunit id does not Exist");
				else out.println("Other integrity constraint related exception or error");
			 }//else if(sqle.getErrorCode()==2291)
			else sqle.printStackTrace();
			}//catch(SQLException sqle) 
		   catch(Exception e) { e.printStackTrace();} 
		   finally 
			{
			 if(rs!=null)rs.close();
		     if(ps!=null)ps.close();
			 if(ps1!=null)ps1.close();
			 if(ps2!=null)ps2.close();
			 if(ps3!=null)ps3.close();
			 if(rs2!=null)rs2.close();
			 if(rs3!=null)rs3.close();
		     if(ps4!=null)ps4.close();
			 if(rs4!=null)rs4.close();
			 if(ps5!=null)ps5.close();
			 if(rs5!=null)rs5.close();
			 if(ps6!=null)ps6.close();
			 if(rs6!=null)rs6.close();
			 if(ps7!=null)ps7.close();
			 if(rs7!=null)rs7.close();
			 if(ps8!=null)ps8.close();
			 if(rs8!=null)rs8.close();
			 if(ps9!=null)ps9.close();
			 if(rs9!=null)rs9.close();
			 if(ps10!=null)ps10.close();
			 if(rs10!=null)rs10.close();
		     if(con!=null)con.close();
		     //if(dbConn!=null)dbConn.close();
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
