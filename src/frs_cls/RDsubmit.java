package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import smscls.SMSreceiver;
/**
	 * Servlet implementation class RDsubmit
 */
public class RDsubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String FRS_date;
	private String huma_id;
	private int FRS_village_count;
	private int FRS_Cust_count;
	private int FRS_total_amt;
	private int FRS_total_accounts;
	private int FRS_od_amt;
	private int FRS_od_accounts;

	private int FRS_SDRCUST_COUNT;

	java.sql.Date sd1;
	Connection con=null;
	private Logger log = Logger.getLogger("LOGFILE");
	
	JdbcConnect js=new JdbcConnect();
    public RDsubmit() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				if (request.getSession().getAttribute("username") == null ) {
					response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
					}
				else
				{
		////System.out.println("inside the doPost() of RDsubmit.java file");
		//System.out.println("request object = "+request+" response object is = "+response);
		FRS_Cust_count=Integer.parseInt(request.getParameter("FRS_Cust_count"));
		FRS_date=request.getParameter("FRS_date");
		FRS_total_amt=Integer.parseInt(request.getParameter("FRS_total_amt"));
		huma_id=request.getParameter("huma_id");
		FRS_village_count=Integer.parseInt(request.getParameter("FRS_village_count"));
		FRS_total_accounts=Integer.parseInt(request.getParameter("FRS_total_accounts"));
		FRS_od_amt=Integer.parseInt(request.getParameter("FRS_od_amt"));
		FRS_od_accounts=Integer.parseInt(request.getParameter("FRS_od_accounts"));
		//System.out.println("Collecting all te values");
		
		if(request.getParameter("FRS_SDRCUST_COUNT").trim()!="")
		FRS_SDRCUST_COUNT=Integer.parseInt(request.getParameter("FRS_SDRCUST_COUNT"));
		else
			FRS_SDRCUST_COUNT=0;
		
		RequestDispatcher successrd=getServletContext().getRequestDispatcher("/persist.jsp");
		RequestDispatcher usersuccerd=getServletContext().getRequestDispatcher("/userpersist.jsp");
		////System.out.println("Msr debug 1");
		RequestDispatcher failrd=getServletContext().getRequestDispatcher("/failrdsubmit.jsp");
		RequestDispatcher failuserrd=getServletContext().getRequestDispatcher("/failrdsubmit.jsp");
		////System.out.println("Msr debug 2"+failrd);

		//System.out.println("Before executing the query");
		String sql="insert into FRS_RECOVERY(huma_id ,FRS_DATE ,FRS_VILLAGE_COUNT ,FRS_CUST_COUNT ,FRS_TOTAL_AMT ,FRS_TOTAL_ACCOUNTS ," +

				"FRS_OD_AMT ,FRS_OD_ACCOUNTS,frs_seqid,frs_cby,sender_number,sent_dt,sms_count,FRS_SDRCUST_COUNT)" +
				" values(?,to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,user_seqid.nextval,?,?,?,0,?)";	

/*		String sql="insert into FRS_RECOVERY " +"" +
				"(huma_id ,FRS_VILLAGE_COUNT ,FRS_CUST_COUNT ,FRS_TOTAL_AMT ,FRS_TOTAL_ACCOUNTS ,FRS_OD_AMT ,FRS_OD_ACCOUNTS)"+
				"values(?,?,?,?,?,?,?)";*/
		try {
			con=js.getConnection();
			////log.info("JDBC Connection was created");
			////System.out.println("Msr debug 3");
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,huma_id);
			ps.setString(2,FRS_date);
			ps.setInt(3,FRS_village_count);
			ps.setInt(4,FRS_Cust_count);
			ps.setInt(5,FRS_total_amt);
			ps.setInt(6, FRS_total_accounts);
			ps.setInt(7,FRS_od_amt);
			ps.setInt(8,FRS_od_accounts);
			ps.setString(9,new String((String) request.getSession().getAttribute("username")));
			ps.setString(10,null);//sender_number
			ps.setString(11,null);//sent_dt

			ps.setInt(12,FRS_SDRCUST_COUNT);
			////System.out.println("Msr debug 4");
			Boolean status=ps.execute();
			if(status!=true)
			{
					
				HttpSession ses=request.getSession(false);
				String userrole=(String)ses.getAttribute("userrole");
				if(userrole.equals("admin") || userrole.equals("areahead") || userrole.equals("unithead"))
				{
					System.out.println("ONE RECOERD WAS INSERTED from admin user" );
					log.info("Admin user submitted Details");
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Date date = new Date();
					String Today = dateFormat.format(date);
					String sms_text1="ID."+huma_id+",DT."+FRS_date+",VL."+FRS_village_count+",CUS."+FRS_Cust_count+",TAC."+FRS_total_accounts+",TAMT."+FRS_total_amt+",ODAMT."+FRS_od_amt+",ODCL."+FRS_od_accounts+",SDR."+FRS_SDRCUST_COUNT+"";
					String sender_number="0";
					String sent_dt1=Today;
					String logmsg = "; PORTAL=" + sms_text1 + "; SENDER=" + sender_number
							+ "; SENT DT=" + sent_dt1 + "; ACKNL=";
					log.info(logmsg + "The details saved Successfully for DT."
							+ FRS_date + " of " + huma_id);
					successrd.include(request, response);
				}
				else 
				{
					System.out.println("ONE RECOERD WAS INSERTED from lsr user" );
					log.info("LSR submitted Details");
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Date date = new Date();
					String Today = dateFormat.format(date);
					//String sms_text1="ID.E4724,DT.2611,VL.0,CUS.0,TAC.0,TAMT.0,ODAMT.0,ODCL.0,SDR.0";
					String sms_text1="ID."+huma_id+",DT."+FRS_date+",VL."+FRS_village_count+",CUS."+FRS_Cust_count+",TAC."+FRS_total_accounts+",TAMT."+FRS_total_amt+",ODAMT."+FRS_od_amt+",ODCL."+FRS_od_accounts+",SDR."+FRS_SDRCUST_COUNT+"";
					String sender_number="0";
					String sent_dt1=Today;
					String logmsg = "; PORTAL=" + sms_text1 + "; SENDER=" + sender_number
							+ "; SENT DT=" + sent_dt1 + "; ACKNL=";
					log.info(logmsg + "The details saved Successfully for DT."
							+ FRS_date + " of " + huma_id);
					
					log.info("LSR submitted Details");
					
					usersuccerd.include(request, response);
				}
				//below lines code for sendTargetVsAchievementSMS starts----------------------  
				PreparedStatement ps2 = con
						.prepareStatement("select distinct huma_id,huma_fname,huma_mobile from huma_mstr where huma_id=?");
				//ps2.setString(1, huma_designation.substring(0, 3));
				ps2.setString(1, huma_id);
				ResultSet rs2 = ps2.executeQuery();
				if (rs2 == null)
					System.out.println("result set is null in RDsubmit");
				String huma_mobile="";
				if (rs2.next()){
					huma_mobile=rs2.getString("huma_mobile");
					if((huma_mobile!=null)&&(huma_mobile!=""))
					{
					//SMSreceiver smsRec= new SMSreceiver();
					//System.out.println(con+huma_id+ rs2.getString("huma_mobile")+FRS_date.split("-")[0]+FRS_date.split("-")[1]+FRS_od_amt+huma_mobile);
					SMSreceiver.targetVsAchievement_call(con, huma_id, rs2.getString("huma_fname"), FRS_date.split("-")[0]+FRS_date.split("-")[1]+FRS_date.split("-")[2], FRS_od_amt, huma_mobile);
					}
				}
				//above lines code for sendTargetVsAchievementSMS ends----------------------		
			}//Successful insertion of records
			else
			{
				log.info("Submitting failed");
				//System.out.println("debug 5");
				failrd.include(request, response);
			//	System.out.println("debug 6");
				//failrd.forward(request, response);
			}
			ps.close();
		} catch (SQLException e) {
			//log.error("please cjheck :",e);
			//System.out.println("Debug MSR 7");
			failrd.include(request, response);
			
			////System.out.println("Msr debug after include 8"+failrd);
			e.getMessage();//printStackTrace();
		} catch (Exception e) {
			log.warn(e);
			e.printStackTrace();
		} finally 
		{ 
			 if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		
		
		
		
	}

				}}}
