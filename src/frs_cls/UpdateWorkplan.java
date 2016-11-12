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

public final class UpdateWorkplan extends HttpServlet {

	   Connection con=null;
	   PreparedStatement ps=null,ps1=null,ps2=null,ps3=null,ps4=null,ps5=null,ps6=null,ps7=null,ps8=null,ps9=null,ps10=null;
	   ResultSet rs=null,rs2=null,rs3=null,rs4=null,rs5=null,rs6=null,rs7=null,rs8=null,rs9=null,rs10=null;
	   int f=0,e=0; boolean f2=false;

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
		       	String username=(String) request.getSession().getAttribute("username");
				String BSFLUNIT_UCODE = request.getParameter("BSFLUNIT_UCODE").trim();			//System.out.println(BSFLUNIT_UCODE);
				//String UHLOG_DATE = request.getParameter("UHLOG_DATE").trim(); 
				//String area_name = request.getParameter("area_name").trim(); 
				//String VCODE[] = request.getParameterValues("VCODE");
				String humaid[]=request.getParameterValues("emp_no");
				String ACTIVITY_ID[] = request.getParameterValues("act_id");
				//String UHLOG_AMTSPENT[] = request.getParameterValues("UHLOG_AMTSPENT");
				String unit_visited[] = request.getParameterValues("visited");    
				String activities_planned[] = request.getParameterValues("activities_planned"); 
				String date[]=request.getParameterValues("war_date");
				//String UHLOG_ODAMT[] = request.getParameterValues("UHLOG_ODAMT");    
				//String UHLOG_REMARKS[] = request.getParameterValues("UHLOG_REMARKS"); 
				for(int i=0;i<date.length;i++)
				{
					//-------------------------trim() for loop parameters--------------------
					//VCODE[i] = VCODE[i].trim();
					humaid[0]=humaid[0].trim();
					ACTIVITY_ID[i] = ACTIVITY_ID[i].trim();
					unit_visited[i] = unit_visited[i].trim();
					activities_planned[i] = activities_planned[i].trim();
					//UHLOG_ODAMT[i] = UHLOG_ODAMT[i].trim();
					//UHLOG_ODCUST[i] = UHLOG_ODCUST[i].trim();
					//UHLOG_REMARKS[i] =  UHLOG_REMARKS[i].trim();
					//-------------------------trim() for loop parameters--------------------
				}	
				
				/*
				 * Added by rajesh 
				 * code for server side validation to implement user roles 
				 * 
				 * 
				 */
				
				String huma_id=request.getParameter("emp_no");
			//	String Username=request.getSession().getAttribute("username").toString();
				String role=request.getSession().getAttribute("userrole").toString();
				//System.out.println("In suhlogm.java");
				
				String area1="";
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
				String UHLOG_CONTROLENO2 = request.getParameter("UHLOG_CONTROLENO2");
				//dynamic cields need to checked for their existiance but the common fields can be directly caught in exeception
				String dates[];//used to get the UHLOG_desc
				String curBSFLUNIT_UCODE=(String)((HttpServletRequest) request).getSession().getAttribute("user");
				//System.out.println("hey after reading each insertion repeated parameters");
				/*
				int n1=activitysl_id.length;
				int n2=UHLOG_services.length;
				int n3=UHLOG_place.length;//here n1=n2=n3
				//System.out.println("hey the activitysl_id length length is="+n1);
				//System.out.println("hey the UHLOG_place length length is="+n1);*/
				String uno=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-')+1);
			//	Connection svcon=new JdbcConnect().getConnection();
				String svsql="select * from BSFLUNIT_MSTR where BSFLUNIT_UCODE='"+uno+"'";
				Statement svst=con.createStatement();
				ResultSet svrs=svst.executeQuery(svsql);
				if(!svrs.next())
				{
					out.println("Entered Unit does not Exist ");
					return;
				}

				
				
				

				
				try
				{//System.out.println("het inside the try");	
				//Class.forName(driver);
				//	con= dbConn;//con= ((Statement) dbConn).getConnection();
		//con = DriverManager.getConnection(url,user,pwd); 
		 
		/*		//---------------------------------------controleno generation
				ps6 = con.prepareStatement("select nvl((select to_number(UHLOG_controleno+1) from uhlog_service where UHLOG_controleno=(select max(UHLOG_controleno) from uhlog_service)),1) as UHLOG_controleno from dual");
				rs6 = ps6.executeQuery();	
				rs6.next();
		String UHLOG_controleno =Integer.toString(rs6.getInt(1));// request.getParameter("UHLOG_controleno"); //common for each insertion
		//System.out.println("hey the e(UHLOG_controleno)="+UHLOG_controleno);
				//end of UHLOG_controleno generation code--------------------------------------------------------------*/
		//=========Newly added code to get UHLOG_desc feils dynamically fae.jsp============================================
		    //dates=new String[2];  
			 //dates[0] =  UHLOG_DATE; dates[1]= UHLOG_tdate; //System.out.println("dates.length="+dates.length); 
				/*int x4=0;// to checke the all 2 dates entered are whether having/refering any UHLOG_desc/holiday year or not
				ps4 = con.prepareStatement("select distinct BSFLUNIT_UCODE from uhlog_service p1 where BSFLUNIT_UCODE='"+BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-')+1)+"' and UHLOG_controleno!="+UHLOG_CONTROLENO2+" and UHLOG_DATE=to_date('"+UHLOG_DATE+"','dd-mm-yyyy')");
				   //for(int i=0; i<dates.length; i++)
				    // { //System.out.println("het inside the for loop and the dates[i]="+dates[i]);
					  //	 ps4.setString(1,dates[i]);
				  		 rs4 = ps4.executeQuery();
				   		if((rs4.next())==false)
				    	 x4=x4+1;
					  //}//for(int i=0; i<2; i++)
				  //System.out.println("hey outside the for loop and the x4="+x4);
				if(x4==0)
				  {//data="NOYou conn't select a date that does not comes under any of holiday year period";
				   out.println("Already existing date ");
				   return;
				  }*/
		//==========================================================================================================
		//Checking UHLOG_desc availability
		/*	  int x2=0;
			  ps2 = con.prepareStatement("select distinct UHLOG_desc from uhlog_service where UHLOG_desc=? and UHLOG_controleno!=?");
			  ps2.setString(1,UHLOG_desc);
			  ps2.setString(2,UHLOG_CONTROLENO2);
			  rs2 = ps2.executeQuery();
			  if((rs2.next())==false)
			    x2=x2+1;
			  if(x2==0)//entered field exists
			  {
			   out.println("Already existing UH Activity Entry description,pls change..");
			   return;//stops exeution here
			  }*/
		//Checking vbs_place(village,block,subunit) availability
		/*	int x2=0,x3=0,x5=0,x7=0,x9=0,x10=0;
			ps2 = con.prepareStatement("select fpo_id from fpo_mstr where fpo_id=?");
			ps10 = con.prepareStatement("select pg_id from pg_mstr where pg_id=?");		
			ps9 = con.prepareStatement("select estbl_id from estbl_mstr where estbl_id=?");	
			ps3 = con.prepareStatement("select village_id from village_mstr where village_id=?");
			ps5 = con.prepareStatement("select block_id from block_mstr where block_id=?");
			ps7 = con.prepareStatement("select subunit_id from subBSFLUNIT_MSTR where subunit_id=?");
			for(int i=0; i<UHLOG_place.length; i++)
				{//System.out.println("hey inside the for loop");
				//fpo_id existance checking
				if(fpo_id[i]!="")
				 { ps2.setString(1,fpo_id[i].substring(0,7));
				   rs2 = ps2.executeQuery();
				   if((rs2.next())==false) x2=x2+1;
				  }
				//pg_id existance checking
				if(pg_id[i]!="")
				 { ps10.setString(1,pg_id[i].substring(0,9));
				   rs10 = ps10.executeQuery();
				   if((rs10.next())==false) x10=x10+1;
				  }
				//estbl_id existance checking
				if(estbl_id[i]!="")
				 { ps9.setString(1,estbl_id[i].substring(0,7));
				   rs9 = ps9.executeQuery();
				   if((rs9.next())==false) x9=x9+1;
				  }
				//UHLOG_place existing checking
				if(UHLOG_place[i]!="")
				if(UHLOG_place[i].substring(0,2).equals("VI"))
				 { ps3.setString(1,UHLOG_place[i].substring(0,7));
				   rs3 = ps3.executeQuery();
				   if((rs3.next())==false) x3=x3+1;
				  }
				else if(UHLOG_place[i].substring(0,2).equals("BL"))
				 { ps5.setString(1,UHLOG_place[i].substring(0,7));
				   rs5 = ps5.executeQuery();
				   if((rs5.next())==false) x5=x5+1;
				  }
				else if(UHLOG_place[i].substring(0,2).equals("SU"))
				 { ps7.setString(1,UHLOG_place[i].substring(0,7));
				   rs7 = ps7.executeQuery();
				   if((rs7.next())==false) x7=x7+1;
				  }
				}//for(int i=0; i<UHLOG_place.length; i++)
			  if(x2!=0)//entered field not exists
			  {
			   out.println("Entered FPO id does not Exist"); 
			   return;//stops exeution here
			  }
			  if(x10!=0)//entered field not exists
			  {
			   out.println("Entered PG id does not Exist"); 
			   return;//stops exeution here
			  }
			  if(x9!=0)//entered field not exists
			  {
			   out.println("Entered Establishment (Business Agencies) id does not Exist"); 
			   return;//stops exeution here
			  }
			  if(x3!=0)//entered field not exists
			  {
			   out.println("Entered Village id does not Exist"); 
			   return;//stops exeution here
			  }
			  if(x5!=0)//entered field not exists
			  {
			   out.println("Entered Block id does not Exist"); 
			   return;//stops exeution here
			  }
			  if(x7!=0)//entered field not exists
			  {
			   out.println("Entered Subunit id does not Exist"); 
			   return;//stops exeution here
			  }*/
		//Checking date
					for(int s=0;s<date.length;s++)
					{
						
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			        	Date date1 = sdf.parse(date[s]);
			        	Date date2 = sdf.parse(war_mdate);
			        	System.out.println(sdf.format(date1));
			        	System.out.println(sdf.format(date2));
						//-------------------------trim() for loop parameters--------------------
						//VCODE[i] = VCODE[i].trim();
						if(date1.compareTo(date2)<0){
					    out.println("the workplan date for previous day can not be updated");
							return;
						//UHLOG_ODAMT[i] = UHLOG_ODAMT[i].trim();
						//UHLOG_ODCUST[i] = UHLOG_ODCUST[i].trim();
						//UHLOG_REMARKS[i] =  UHLOG_REMARKS[i].trim();
						//-------------------------trim() for loop parameters--------------------
					}	
					}
		//Getting cdate,cby fields for current use		
				ps8 = con.prepareStatement("select distinct to_char(war_cdate,'dd-mm-yyyy hh:mi:ss am'),war_cby from work_plannew where war_controlno=?");//getting the cdate,cby  from previus rows
				ps8.setString(1,UHLOG_CONTROLENO2);
				rs8 = ps8.executeQuery();//System.out.println("hey going to next the rs8 and controleno="+UHLOG_CONTROLENO2);		
				rs8.next();
		//Query to delete all the existing rows with current UHLOG_controleno
		        ps6= con.prepareStatement("delete from work_plannew where war_controlno=?");//delete from uhlog_service where BSFLUNIT_UCODE=? and UHLOG_DATE=to_date(?,'dd-mm-yyyy')/deleting existing rows.
				ps6.setString(1,UHLOG_CONTROLENO2);//ps6.setString(2,UHLOG_DATE);
				f2=ps6.execute();//deleting the previous rows of corrspnding person & UHLOG_DATE(here fdate/tdate can be used,both are uniques)
				/*
				
				
				if(!f2)
				 //System.out.println("Deleted Successfully");		
		    	else
			     //System.out.println("Deletion failed in  ");*/
				 
				/* ps1= con.prepareStatement("insert into uhlog_service values(?,UHLOG_seqid.nextval,?,to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,?,?,?,to_date(?,'dd-mm-yyyy hh:mi:ss am'),nvl(sysdate,to_date(?,'dd-mm-yyyy')),?,?,?,?,?,?)");//System.out.println("hey the PreparedStatement1 made");
				for(int i=0; i<UHLOG_place.length; i++)
				{//System.out.println("hey inside the for loop");
				ps1.setString(1,BSFLUNIT_UCODE.substring(0,4));//ps1.setString(2,UHLOG_desc); //System.out.println("After assigning BSFLUNIT_UCODE and UHLOG_desc");
				ps1.setString(2,UHLOG_CONTROLENO2);
				ps1.setString(3,UHLOG_DATE);//ps1.setString(5,UHLOG_tdate);
				ps1.setString(4,UHLOG_sno[i]);	
				ps1.setString(5,activitysl_id[i]);
				if(fpo_id[i]!="") ps1.setString(6,fpo_id[i].substring(0,7)); else ps1.setString(6,fpo_id[i]);
				ps1.setString(7,null);
				ps1.setString(8,UHLOG_Quantity[i]);		
				if(UHLOG_place[i]!=""){
				if(UHLOG_place[i].substring(0,2).equals("VI")){ps1.setString(9,UHLOG_place[i].substring(0,7)); ps1.setString(10,null); ps1.setString(11,null);}
				else if(UHLOG_place[i].substring(0,2).equals("BL")){ps1.setString(9,null); ps1.setString(10,UHLOG_place[i].substring(0,7)); ps1.setString(11,null);}
				else if(UHLOG_place[i].substring(0,2).equals("SU")){ps1.setString(9,null); ps1.setString(10,null); ps1.setString(11,UHLOG_place[i].substring(0,7));}}
				else {ps1.setString(9,UHLOG_place[i]); ps1.setString(10,UHLOG_place[i]); ps1.setString(11,UHLOG_place[i]);}		
				ps1.setString(12,UHLOG_services[i]);
				
				ps1.setString(13,rs8.getString(1));//UHLOG_cdate parameter got from using last controleno
				ps1.setString(14,UHLOG_cdate);//UHLOG_mdate
				ps1.setString(15,rs8.getString(2));//Got cby using last controle no
				ps1.setString(16,curBSFLUNIT_UCODE);//UHLOG_mby
				if(estbl_id[i]!="") ps1.setString(17,estbl_id[i].substring(0,7)); else ps1.setString(17,estbl_id[i]);		
				if(pg_id[i]!="") ps1.setString(18,pg_id[i].substring(0,9)); else ps1.setString(18,pg_id[i]);		
				ps1.setString(19,UHLOG_Price[i].replaceAll(",", ""));
				ps1.setString(20,UHLOG_Area[i]);		
				 f=ps1.executeUpdate();
				}//for(int i=0; i<UHLOG_place.length; i++)
				*/
				ps1= con.prepareStatement("insert into WORK_PLANNEW(WAR_SEQID,WAR_DATE,HUMA_ID,UNIT_VISITED,ACTIVITIES_PLANNED,WAR_CBY,WAR_CONTROLNO,ACT_ID,WAR_MBY,WAR_CDATE,WAR_MDATE) "//,WAR_POD
						+ "values(WAR_SEQID.nextval,to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,?,to_date(?,'dd-mm-yyyy hh:mi:ss am'),to_date(?,'dd-mm-yyyy'))");
				for(int i=0; i<date.length; i++)
				{
				ps1.setString(1,date[i]);//ps1.setString(2,ops_desc); //System.out.println("After assigning huma_id and ops_desc");
				ps1.setString(2,humaid[0]);
				ps1.setString(3,unit_visited[i]);
				ps1.setString(4,activities_planned[i]);
				//ps1.setString(5,username);
				ps1.setString(5,rs8.getString(2));
				ps1.setString(6,UHLOG_CONTROLENO2);
				ps1.setString(7,ACTIVITY_ID[i]);
			
				//System.out.println(BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-')+1));
				//ps1.setString(9,BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-')+1));
			//	ps1.setString(10,UHLOG_REMARKS[i]);
				ps1.setString(8,username);//mby
				ps1.setString(9,rs8.getString(1));//cdate
				ps1.setString(10,war_mdate);//mdate
		//		ps1.setString(14,UHLOG_AMTSPENT[i]);
				////System.out.println("Msr debug beforer executing-uuhlogm");
				f=ps1.executeUpdate();
				System.out.println("the f si " +f);
				////System.out.println("Msr debug after executing-uuhlogm");

				}

				if(f!=0)
					out.println("OK Workplan Entry updated successfully");//fae master created successfully
				else
				    out.println("Work Plan Activity Entry is not updated for some reasons");
									
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
