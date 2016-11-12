package frs_cls;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class uh_connect
 */
public class uh_connect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcConnect jc=new JdbcConnect();
    Connection con=null;
    int id;
    String type;
    String sql;
    String sqla;
    Integer uhid;
    int b;
    
    public uh_connect() {
    	
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try{
		String activity[]=(String[])request.getParameterValues("dom");
		RequestDispatcher successrd=getServletContext().getRequestDispatcher("/uhenterysuccess.jsp");
		RequestDispatcher failrd=getServletContext().getRequestDispatcher("/uhentryfail.jsp");
		RequestDispatcher upsuccessrd=getServletContext().getRequestDispatcher("/uhupdatesuccess.jsp");
		RequestDispatcher upfailrd=getServletContext().getRequestDispatcher("/uhupdatefail.jsp");
		con = jc.getConnection();
		//"select custconn.nextval from dual";
		try {
			HttpSession ses=request.getSession(false);
			System.out.println("uhid=   "+uhid);
			String a="select custconn.nextval as sid from dual";
			type=request.getParameter("cc");
			System.out.println("which form is calling"+type);

			if(type.equals("scc"))
			{
			//con =jc.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(a);
			while(rs.next())
			{
				id=rs.getInt(1);
			}
			//id = st.executeUpdate(sql1);
			System.out.println("sequence id="+id );
			}
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//} catch (ClassNotFoundException e1) {
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*String sql="insert into FRS_RECOVERY(huma_id ,FRS_DATE ,FRS_VILLAGE_COUNT ,FRS_CUST_COUNT ,FRS_TOTAL_AMT ,FRS_TOTAL_ACCOUNTS ," +
				"FRS_OD_AMT ,FRS_OD_ACCOUNTS,frs_seqid,frs_cby,sender_number,sent_dt,sms_count)" +
				" values(?,to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,user_seqid.nextval,?,?,?,0)";*/	
			try {
				HttpSession ses=request.getSession(false);
/*				RequestDispatcher successrd=getServletContext().getRequestDispatcher("/uhenterysuccess.jsp");
				RequestDispatcher failrd=getServletContext().getRequestDispatcher("/uhentryfail.jsp");
				RequestDispatcher upsuccessrd=getServletContext().getRequestDispatcher("/uhupdatesuccess.jsp");
				RequestDispatcher upfailrd=getServletContext().getRequestDispatcher("/uhupdatefail.jsp");
*/				
				ses.setAttribute("uname",request.getParameter("BSFLUNIT_NAME"));
				ses.setAttribute("uhdate",request.getParameter("fdate"));
				ses.setAttribute("oda",request.getParameter("UHLOG_ODAMT"));
				ses.setAttribute("odc",request.getParameter("UHLOG_ODCUST"));
				ses.setAttribute("or",request.getParameter("outreach"));
				ses.setAttribute("an",request.getParameter("area_name"));
				

				if(type.equals("scc"))
				{
				sql="insert into uh_cust_conn(UNAME,UHDATE,UHLOG_ODCUST,UHLOG_ODAMT,OUTREACH,cby,uhid)"+"values(?,to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?)";
				//Connection con=jc.getConnection();
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1,request.getParameter("BSFLUNIT_NAME"));
				ps.setString(2,request.getParameter("fdate"));
				//ps.setString(3,request.getParameter("area_name"));
				ps.setInt(3,Integer.parseInt(request.getParameter("UHLOG_ODCUST")));
				ps.setInt(4,Integer.parseInt(request.getParameter("UHLOG_ODAMT")));
				ps.setInt(5,Integer.parseInt(request.getParameter("outreach")));
				ps.setString(6,new String((String) request.getSession().getAttribute("username")));
				ps.setInt(7,id);
				//String res=Activity(activity, request);
				for(int i=0;i<activity.length;i++)
				{
					sqla="insert into uh_cust_conn_activity(UNAME,ACTIVITY,FDATE,uha_id)"+"values(?,?,to_date(?,'dd-mm-yyyy'),?)";
					System.out.println(activity[i]);
					//Connection con1=jc.getConnection();
					////log.info("JDBC Connection was created");
					//System.out.println("Msr debug 3");
					PreparedStatement ps1;
					try {
						ps1 = con.prepareStatement(sqla);
						ps1.setString(1,request.getParameter("BSFLUNIT_NAME"));
						ps1.setString(2,activity[i]);
						ps1.setString(3,request.getParameter("fdate"));
						ps1.setInt(4,id);
						ps1.execute();

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						failrd.include(request, response);
						e.printStackTrace();
					}
					//System.out.println("one row inserted for acivity");				
				}


				Boolean status=ps.execute();
				//System.out.println(res);
				if(status!=true)
				{
					successrd.include(request, response);
				}else
				{
					failrd.include(request, response);
				}
				System.out.println("one row inserted for unit head");
				ps.close();

				}
				if(type.equals("mcc"))
				{
					uhid=(Integer) ses.getAttribute("uhid");
					b=uhid;
				sql="UPDATE UH_CUST_CONN SET UNAME=?,UHDATE=to_date(?,'dd-mm-yyyy'),area_name=?,UHLOG_ODCUST=?, UHLOG_ODAMT=?,OUTREACH=?,mdate=sysdate,mby=?,UHID=? WHERE UHDATE=to_date(?,'dd-mm-yyyy') and UNAME =?";

				//Connection con=jc.getConnection();
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1,request.getParameter("BSFLUNIT_NAME"));
				ps.setString(2,request.getParameter("fdate"));
				ps.setString(3,request.getParameter("area_name"));
				ps.setInt(4,Integer.parseInt(request.getParameter("UHLOG_ODCUST")));
				ps.setInt(5,Integer.parseInt(request.getParameter("UHLOG_ODAMT")));
				ps.setInt(6,Integer.parseInt(request.getParameter("outreach")));
				ps.setString(7,new String((String) request.getSession().getAttribute("username")));
				ps.setInt(8,b);
				ps.setString(9,(String)request.getSession().getAttribute("uhdate"));//request.getParameter("fdate"));
				ps.setString(10,request.getParameter("BSFLUNIT_NAME"));
				System.out.println("updating ....");
				int c=ps.executeUpdate();
				System.out.println("updatdated ...." +c);
				String sqld="DELETE FROM uh_cust_conn_activity WHERE uha_id="+uhid;
				Connection cond=ps.getConnection();
				Statement st=cond.createStatement();
				int a=st.executeUpdate(sqld);
				System.out.println("Total rows was deleted with the uh id  "+a);
				for(int i=0;i<activity.length;i++)
				{
					sqla="insert into uh_cust_conn_activity(UNAME,ACTIVITY,FDATE,uha_id)"+"values(?,?,to_date(?,'dd-mm-yyyy'),?)";
					System.out.println(activity[i]);
					//Connection con1=jc.getConnection();
					////log.info("JDBC Connection was created");
					//System.out.println("Msr debug 3");
					PreparedStatement ps1;
					try {
						ps1 = con.prepareStatement(sqla);
						ps1.setString(1,request.getParameter("BSFLUNIT_NAME"));
						ps1.setString(2,activity[i]);
						ps1.setString(3,request.getParameter("fdate").toString());
						ps1.setInt(4,b);
						ps1.execute();

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("one row inserted for acivity");				
				}


				
				//boolean status;
				Boolean status=ps.execute();
				if(status!=true)
				{
					upsuccessrd.include(request, response);
				}else
				{
					upfailrd.include(request, response);
				}
				System.out.println("one row updated for unit head");
				ps.close();

				}

			
		} catch (SQLException e) {
			upfailrd.include(request, response);
			e.printStackTrace();
		//} catch (ClassNotFoundException e) {
		} catch (Exception e) {
			//log.warn(e);
			upfailrd.include(request, response);
			e.printStackTrace();
		} 
			
	}//try
	catch(Exception e){
	e.printStackTrace();	
	}
		finally
		{ try{
			 if(con!=null)con.close();
		}
				 catch (SQLException e) {
					e.printStackTrace();
				} 
		}//finally
	}

/*	public String Activity(String[] activity,HttpServletRequest request,Integer uhid) throws ClassNotFoundException
	{
		for(int i=0;i<activity.length;i++)
		{
			if(type.equals("scc"))
			{
			sqla="insert into uh_cust_conn_activity(UNAME,ACTIVITY,FDATE,uha_id)"+"values(?,?,to_date(?,'dd-mm-yyyy'),?)";
			}
			if(type.equals("mcc"))
			{
			String sqld="DELETE FROM uh_cust_conn_activity WHERE uha_id="+uhid;
			System.out.println("Total rows was deleted with the uh id");
			sqla="insert into uh_cust_conn_activity(UNAME,ACTIVITY,FDATE,uha_id)"+"values(?,?,to_date(?,'dd-mm-yyyy'),?)";
			}
			System.out.println(activity[i]);
			Connection con1=jc.getConnection();
			////log.info("JDBC Connection was created");
			//System.out.println("Msr debug 3");
			PreparedStatement ps1;
			try {
				ps1 = con1.prepareStatement(sqla);
				ps1.setString(1,request.getParameter("BSFLUNIT_NAME"));
				ps1.setString(2,activity[i]);
				ps1.setString(3,request.getParameter("fdate"));
				ps1.setInt(4,id);
				ps1.execute();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("one row inserted for acivity");				
		}

		return activity.length+" rows inserted";
	}
*/
}
