package frs_cls;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class uwarRemarks extends HttpServlet {
       
	private static final long serialVersionUID = 1L;
	Connection con = null;
	private JdbcConnect jc = new JdbcConnect();
	//private StringTokenizer st = null;
	PreparedStatement ps1 = null,ps2 = null, ps4 = null, ps6 = null;
	ResultSet rs2 = null,rs4 = null, rs6 = null;
	private String HOssql;
	private String UHsql;
	private String AHssql;
	//private String sql;
	int f = 0, e = 0;
	ArrayList<String> al;
	ArrayList<String> al1;
    public uwarRemarks() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String role=(String)request.getSession().getAttribute("userrole");
			con = jc.getConnection();
			PrintWriter out = response.getWriter();
			Calendar ca1 = Calendar.getInstance();// from here four lines
			String username = (String) request.getSession().getAttribute("username");
			ca1.get(Calendar.DATE);
			ca1.get(Calendar.YEAR);
			
			String huma_idd = request.getSession().getAttribute("huma_id")
					.toString();
			
			String BSFLUNIT_UCODE="";
			String REMARKS_Backlogs_HO="";
			String REMARKS_Reason_HO="";
			String REMARKS_UH_Backlogs="";
			String REMARKS_FS_Backlogs="";
			String REMARKS_Reason_UH="";
			String REMARKS_AREA_ID="";
			String REMARKS_AH_Backlogs="";
			String REMARKS_Reason_AH="";
			String huma_id="";
			String REMARKS_FS_HO="";			
			String REMARKS_FDATE=request.getParameter("war_fdate").trim();
			String REMARKS_TDATE=request.getParameter("war_tdate").trim();
			int REMARKS_ACTPOD=0;

			String REMARKS_CONTROLENO=request.getParameter("REMARKS_CONTROLENO").trim();
			//System.out.println("control number is : "+REMARKS_CONTROLENO);
			
			
			if(role.equals("admin"))
			{
				
				String Old_fdate=request.getParameter("ofd").trim();
				String Old_tdate=request.getParameter("otd").trim();
				System.out.println("fdate :"+Old_fdate);
				System.out.println("tdate :"+Old_tdate);
				huma_id=request.getParameter("huma_id").trim();
				REMARKS_Backlogs_HO=request.getParameter("HO_Backlogs").trim();
				REMARKS_Reason_HO=request.getParameter("Reason_HO").trim();
				////System.out.println(REMARKS_Backlogs_HO);
				////System.out.println(REMARKS_Reason_HO);
				////System.out.println(huma_id.substring(huma_id.lastIndexOf('-') + 1));
				//System.out.println("REMARKS_FDATE"+REMARKS_FDATE);
				//System.out.println("REMARKS_TDATE"+REMARKS_TDATE);
				
				ps2 = con.prepareStatement("select distinct 'Check' from war_remarks,(SELECT TO_DATE('"+ REMARKS_FDATE+ "','dd-mm-yyyy') + LEVEL - 1 " +
						"dates FROM DUAL CONNECT BY LEVEL <= (TO_DATE('"+ REMARKS_TDATE+ "','dd-mm-yyyy') - TO_DATE('"+ REMARKS_FDATE+ "','dd-mm-yyyy')) " +
								"+ 1) where REMARKS_FS_HO='HO' and huma_id='"+huma_id.substring(huma_id.lastIndexOf('-') + 1)+"'  and" +" (remarks_fdate=to_date(dates) or (remarks_tdate=to_date(dates)))");// +
								//"and (dates !=TO_DATE('"+Old_fdate+"','dd-mm-yyyy') and dates !=TO_DATE('"+Old_tdate+"','dd-mm-yyyy'))");//and remarks_fdate!=TO_DATE('"+Old_fdate+"','dd-mm-yyyy') and remarks_tdate!=TO_DATE('"+Old_tdate+"','dd-mm-yyyy')");
				//System.out.println("ps2="+ps2.toString());
				
				rs2 = ps2.executeQuery();
				if (rs2 == null){
					////System.out.println("DEBUG CHECK RS2 NULL");
				}
				//if ((rs2.next()) == true)//else // Checking the entry exist
				if((!REMARKS_FDATE.equals(Old_fdate))||(!REMARKS_TDATE.equals(Old_tdate)))
				if ((rs2.next()) == true)//else // Checking the entry exist
				{
					////System.out.println("DEBUG CHECK 1");
					out.println("Already existing entry for selected dates");
					return;
				}
				
				String svsql1 = "select huma_id from HUMA_MSTR where HUMA_ID='" + huma_id.substring(huma_id.lastIndexOf('-') + 1) + "'";
				PreparedStatement svst1 = con.prepareStatement(svsql1);
				ResultSet svrs1 = svst1.executeQuery();
				if (!svrs1.next()) {
					out.println("Entered Field Staff code does not Exist :"+ huma_id);
					return;
				}
				HOssql="update WAR_REMARKS set REMARKS_FDATE=to_date(?,'dd-mm-yyyy'),REMARKS_TDATE =to_date(?,'dd-mm-yyyy'),REMARKS_Backlogs_HO=?,REMARKS_Reason_HO=?,REMARKS_MDATE=sysdate,REMARKS_MBY=?,HUMA_ID=? where REMARKS_CONTROLENO=?";
				ps1 = con.prepareStatement(HOssql);
				ps1.setString(1,REMARKS_FDATE);
				ps1.setString(2,REMARKS_TDATE );
				ps1.setString(3,REMARKS_Backlogs_HO );
				//System.out.println(REMARKS_Backlogs_HO);
				ps1.setString(4,REMARKS_Reason_HO);
				//System.out.println(REMARKS_Reason_HO);
				ps1.setString(5,username);
				ps1.setString(6,huma_id.substring(huma_id.lastIndexOf('-') + 1));
				ps1.setLong(7,Integer.parseInt(REMARKS_CONTROLENO));
			}
			if(role.equals("user"))
			{
				REMARKS_FS_Backlogs=request.getParameter("REMARKS_Backlogs_FS").trim();
				huma_id=request.getParameter("huma_id").trim();
				REMARKS_FS_HO="FS";
				String svsql1 = "select huma_id from HUMA_MSTR where HUMA_ID='" + huma_id.substring(huma_id.lastIndexOf('-') + 1) + "'";
				PreparedStatement svst1 = con.prepareStatement(svsql1);
				ResultSet svrs1 = svst1.executeQuery();
				if (!svrs1.next()) {
					out.println("Entered Field Staff code does not Exist :"+ huma_id);
					return;
				}
				HOssql="update WAR_REMARKS set REMARKS_FDATE=to_date(?,'dd-mm-yyyy'),REMARKS_TDATE =to_date(?,'dd-mm-yyyy'),REMARKS_BACKLOGS_FS=?,REMARKS_MDATE=sysdate,REMARKS_MBY=? where REMARKS_CONTROLENO=?";
				ps1 = con.prepareStatement(HOssql);
				ps1.setString(1,REMARKS_FDATE);
				ps1.setString(2,REMARKS_TDATE );
				ps1.setString(3,REMARKS_FS_Backlogs);
				ps1.setString(4,username);
				ps1.setLong(5,Integer.parseInt(REMARKS_CONTROLENO));
			}

			if(role.equals("unithead"))
			{
				//BigDecimal bd = new BigDecimal(request.getParameter("pod"));
				BSFLUNIT_UCODE = request.getParameter("BSFLUNIT_UCODE").trim();
				REMARKS_UH_Backlogs=request.getParameter("UH_Backlogs").trim();
				//REMARKS_ACTPOD=bd.intValue();
				String svsql1 = "select bsflunit_ucode from bsflunit_mstr where huma_id='"+huma_idd+"' and bsflunit_ucode='" + BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-') + 1)+ "'";
				PreparedStatement svst1 = con.prepareStatement(svsql1);
				ResultSet svrs1 = svst1.executeQuery();
				if (!svrs1.next()) {
					//out.println("Entered Unit code does not Exist :"+ BSFLUNIT_UCODE);
					out.println("Sorry Other's Unit code or Unit does not Exist :"+ BSFLUNIT_UCODE);
					return;
				}
				
				REMARKS_Reason_UH=request.getParameter("Reason_UH").trim();
				UHsql="update WAR_REMARKS set REMARKS_FDATE=to_date(?,'dd-mm-yyyy'),REMARKS_TDATE =to_date(?,'dd-mm-yyyy'),REMARKS_Backlogs_UH=?,REMARKS_Reason_UH=?,REMARKS_MDATE=sysdate,REMARKS_MBY=?,REMARKS_ACTPOD=?,BSFLUNIT_UCODE=? where REMARKS_CONTROLENO=?";
				ps1 = con.prepareStatement(UHsql);
				ps1.setString(1,REMARKS_FDATE);
				ps1.setString(2,REMARKS_TDATE );
				ps1.setString(3,REMARKS_UH_Backlogs );
				ps1.setString(4,REMARKS_Reason_UH);
				ps1.setString(5,username);
				ps1.setLong(6,REMARKS_ACTPOD);
				ps1.setString(7,BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-') + 1));
				ps1.setLong(8,Integer.parseInt(REMARKS_CONTROLENO));

			}
			if(role.equals("areahead"))
			{
				REMARKS_AREA_ID=request.getParameter("area_name").trim();
				REMARKS_AH_Backlogs=request.getParameter("AH_Backlogs").trim();
				REMARKS_Reason_AH=request.getParameter("Reason_AH").trim();
				String svsql1 = "select AREA_ID from AREA_MSTR where huma_id='"+huma_idd+"' and AREA_ID='" + REMARKS_AREA_ID.substring(REMARKS_AREA_ID.lastIndexOf('-') + 1)+ "'";
				PreparedStatement svst1 = con.prepareStatement(svsql1);
				ResultSet svrs1 = svst1.executeQuery();
				if (!svrs1.next()) {
					//out.println("Entered Area code does not Exist :"+ REMARKS_AREA_ID);
					out.println("Sorry Other's Area code or Area does not Exist :"+ REMARKS_AREA_ID);
					return;
				}
				AHssql="update WAR_REMARKS set REMARKS_FDATE=to_date(?,'dd-mm-yyyy'),REMARKS_TDATE =to_date(?,'dd-mm-yyyy'),REMARKS_Backlogs_AH=?,REMARKS_Reason_AH=?,REMARKS_MDATE=sysdate,REMARKS_MBY=?,area_id=? where REMARKS_CONTROLENO=?" ;
				ps1 = con.prepareStatement(AHssql);
				ps1.setString(1,REMARKS_FDATE);
				ps1.setString(2,REMARKS_TDATE );
				ps1.setString(3,REMARKS_AH_Backlogs);
				ps1.setString(4,REMARKS_Reason_AH);
				ps1.setString(5,username);
				ps1.setString(6,REMARKS_AREA_ID.substring(REMARKS_AREA_ID.lastIndexOf('-') + 1));
				ps1.setLong(7,Integer.parseInt(REMARKS_CONTROLENO));
				
			}
			
			String huma_id2=huma_id.substring(huma_id.lastIndexOf('-') + 1);
			String REMARKS_AREA_ID2=REMARKS_AREA_ID.substring(REMARKS_AREA_ID.lastIndexOf('-') + 1);
			String BSFLUNIT_UCODE2=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-') + 1);

			//----Existing entry checking code starts----------
			ps2 = con.prepareStatement("select distinct 'Check' from war_remarks,(SELECT TO_DATE('"+ REMARKS_FDATE+ "','dd-mm-yyyy') + LEVEL - 1 " +
					"dates FROM DUAL CONNECT BY LEVEL <= (TO_DATE('"+ REMARKS_TDATE+ "','dd-mm-yyyy') - TO_DATE('"+ REMARKS_FDATE+ "','dd-mm-yyyy')) " +
							"+ 1) where ((REMARKS_FS_HO='"+REMARKS_FS_HO+"' and huma_id='"+huma_id2+"') or area_id='"+REMARKS_AREA_ID2+"' or bsflunit_ucode='"+BSFLUNIT_UCODE2+"') and" +
													" (remarks_fdate=to_date(dates) or (remarks_tdate=to_date(dates)))");
			rs2 = ps2.executeQuery();
			if (rs2 == null)
			if ((rs2.next()) == true)//else // Checking the entry exist
			{
				out.println("Already existing entry for selected dates");
				return;
			}
			//----Existing entry checking code ends----------
		f = ps1.executeUpdate();
	if (f != 0)
		out.println("OK WAR Room Remarks Entry updated successfully");
	else
		out.println("WAR Room Remarks Entry is not updated for some reasons");
	ps1.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			
			try {
				if (con != null)
					con.close();
				if (rs2 != null)
					rs2.close();
				if (rs4 != null)
					rs4.close();
				if (rs6 != null)
					rs6.close();
				if (ps1 != null)
					ps1.close();
				if (ps2 != null)
					ps2.close();
				if (ps4 != null)
					ps4.close();
				if (ps6 != null)
					ps6.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
		
		
		
	}

}
