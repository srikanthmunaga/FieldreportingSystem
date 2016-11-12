package frs_cls;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class swarRemarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection con = null;
	private JdbcConnect jc = new JdbcConnect();
	PreparedStatement ps1 = null,ps2 = null, ps4 = null, ps6 = null;
	ResultSet rs2 = null,rs4 = null, rs6 = null;
	int f = 0, e = 0;
	ArrayList<String> al;
	ArrayList<String> al1;

    public swarRemarks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		////System.out.println("in side the get method");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		////System.out.println("in post method");
		try {
			String role=(String)request.getSession().getAttribute("userrole");
			//if(role.equals("admin") || role.equals("audit") ||role.equals("areahead") || role.equals("unithead") || role.equals("user"))

			con = jc.getConnection();
			PrintWriter out = response.getWriter();
			Calendar ca1 = Calendar.getInstance();// from here four lines
			ca1.get(Calendar.DATE);
			ca1.get(Calendar.YEAR);
			
			String huma_idd = request.getSession().getAttribute("huma_id")
					.toString();
//			String BSFLUNIT_UCODE;
//			String REMARKS_Backlogs_HO="NA";
//			String REMARKS_Reason_HO="NA";
//			String REMARKS_UH_Backlogs="NA";
//			String REMARKS_FS_Backlogs="NA";
//			String REMARKS_Reason_UH="NA";
//			String REMARKS_AREA_ID="NA";
//			String REMARKS_AH_Backlogs="NA";
//			String REMARKS_Reason_AH="NA";
//			String huma_id="NA";
//			String REMARKS_FS_HO="NA";

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
			String areaid="";
			String ucode="";

			String REMARKS_FDATE=request.getParameter("war_fdate").trim();
			String REMARKS_TDATE=request.getParameter("war_tdate").trim();
			////System.out.println(REMARKS_FDATE);
			////System.out.println(REMARKS_TDATE);
			int REMARKS_ACTPOD=0;

			if(role.equals("admin"))
				{
				huma_id=request.getParameter("huma_id").trim();
				REMARKS_Backlogs_HO=request.getParameter("HO_Backlogs").trim();
				REMARKS_Reason_HO=request.getParameter("Reason_HO").trim();
				REMARKS_FS_HO="HO";
				ps2 = con.prepareStatement("select distinct 'Check' from war_remarks,(SELECT TO_DATE('"+ REMARKS_FDATE+ "','dd-mm-yyyy') + LEVEL - 1 " +
						"dates FROM DUAL CONNECT BY LEVEL <= (TO_DATE('"+ REMARKS_TDATE+ "','dd-mm-yyyy') - TO_DATE('"+ REMARKS_FDATE+ "','dd-mm-yyyy')) " +
								"+ 1) where REMARKS_FS_HO='HO' and" +" (remarks_fdate=to_date(dates) or (remarks_tdate=to_date(dates)))");
				rs2 = ps2.executeQuery();
				if (rs2 == null){
					////System.out.println("DEBUG CHECK RS2 NULL");
				}
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
			}
			else if(role.equals("user"))
			{
				REMARKS_FS_Backlogs=request.getParameter("REMARKS_Backlogs_FS").trim();
				huma_id=request.getParameter("huma_id").trim();
				if(!huma_id.substring(huma_id.lastIndexOf('-') + 1).equals(huma_idd))
				{
					out.println("Sorry you canot enter others user remarks..!");
					return;
				}
				REMARKS_FS_HO="FS";
				//REMARKS_ACTPOD=Integer.parseInt(request.getParameter("pod").trim());
				String svsql1 = "select huma_id from HUMA_MSTR where HUMA_ID='" + huma_id.substring(huma_id.lastIndexOf('-') + 1) + "'";
				PreparedStatement svst1 = con.prepareStatement(svsql1);
				ResultSet svrs1 = svst1.executeQuery();
				String usql="SELECT HUMA_MSTR.BSFLUNIT_UCODE FROM HUMA_MSTR where  HUMA_MSTR.HUMA_ID='"+huma_id.substring(huma_id.lastIndexOf('-') + 1).trim()+"'";
				PreparedStatement usvst = con.prepareStatement(usql);
				ResultSet usvrs = usvst.executeQuery();
				if (usvrs.next()) {
					ucode=usvrs.getString(1);
					//System.out.println(" Area id is :"+ucode);
				}else{
					out.println("Entered Employee does not Assigned to any Unit :"+ BSFLUNIT_UCODE);
					return;
				}
				//ucode=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-') + 1);
				String asql="SELECT  BSFLUNIT_MSTR.AREA_ID FROM BSFLUNIT_MSTR where BSFLUNIT_MSTR.BSFLUNIT_UCODE='"+ucode+"'";
				PreparedStatement svst = con.prepareStatement(asql);
				ResultSet svrs = svst.executeQuery();
				if (svrs.next()) {
					areaid=svrs.getString(1);
					//System.out.println(" Area id is :"+areaid);
				}else{
					out.println("Entered Employee does not Assigned to any Area :"+ BSFLUNIT_UCODE);
					return;
				}
				
				if (!svrs1.next()) {
					out.println("Entered Field Staff code does not Exist :"+ huma_id);
					return;
				}
				}

			else if(role.equals("unithead"))
			{
				//DecimalFormat df = new DecimalFormat("0.##");
				//BigDecimal bd = new BigDecimal(request.getParameter("pod"));
				BSFLUNIT_UCODE = request.getParameter("BSFLUNIT_UCODE").trim();
				REMARKS_UH_Backlogs=request.getParameter("UH_Backlogs").trim();
				REMARKS_Reason_UH=request.getParameter("Reason_UH").trim();
				//if(request.getParameter("pod").trim()!="")
				//REMARKS_ACTPOD=bd.intValue();
				ucode=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-') + 1);
				String asql="SELECT  BSFLUNIT_MSTR.AREA_ID FROM BSFLUNIT_MSTR where BSFLUNIT_MSTR.BSFLUNIT_UCODE='"+ucode+"'";
				REMARKS_FS_HO="UH";
				PreparedStatement svst = con.prepareStatement(asql);
				ResultSet svrs = svst.executeQuery();
				if (svrs.next()) {
					areaid=svrs.getString(1);
					//System.out.println(" Area id is :"+areaid);
				}else{
					out.println("Entered Unit code does not Assigned to any Area :"+ BSFLUNIT_UCODE);
					//out.println("Sorry Other's Unit code or Unit does not Exist :"+ BSFLUNIT_UCODE);
					return;
				}
				String svsql1 = "select bsflunit_ucode from bsflunit_mstr where huma_id='"+huma_idd+"' and bsflunit_ucode='" + BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-') + 1)+ "'";
				PreparedStatement svst1 = con.prepareStatement(svsql1);
				ResultSet svrs1 = svst1.executeQuery();
				if (!svrs1.next()) {
					//out.println("Entered Unit code does not Exist :"+ BSFLUNIT_UCODE);
					out.println("Sorry Other's Unit code or Unit does not Exist :"+ BSFLUNIT_UCODE);
					return;
				}
			}
			else if(role.equals("areahead"))
			{
				REMARKS_AREA_ID=request.getParameter("area_name").trim();
				REMARKS_AH_Backlogs=request.getParameter("AH_Backlogs").trim();
				REMARKS_Reason_AH=request.getParameter("Reason_AH").trim();
				areaid=REMARKS_AREA_ID.substring(REMARKS_AREA_ID.lastIndexOf('-') + 1);
				REMARKS_FS_HO="AH";
				String svsql1 = "select AREA_ID from AREA_MSTR where huma_id='"+huma_idd+"' and AREA_ID='" + REMARKS_AREA_ID.substring(REMARKS_AREA_ID.lastIndexOf('-') + 1)+ "'";
				PreparedStatement svst1 = con.prepareStatement(svsql1);
				ResultSet svrs1 = svst1.executeQuery();
				if (!svrs1.next()) {
					//out.println("Entered Area code does not Exist :"+ REMARKS_AREA_ID);
					out.println("Sorry Other's Area code or Area does not Exist :"+ REMARKS_AREA_ID);
					return;
				}
			}
			//----Existing entry checking code starts----------
			/*		ps2 = con.prepareStatement("select distinct 'Check' from war_remarks,(SELECT TO_DATE('"+ REMARKS_FDATE+ "','dd-mm-yyyy') + LEVEL - 1 " +
					"dates FROM DUAL CONNECT BY LEVEL <= (TO_DATE('"+ REMARKS_TDATE+ "','dd-mm-yyyy') - TO_DATE('"+ REMARKS_FDATE+ "','dd-mm-yyyy')) " +
							"+ 1) where (huma_id=nvl('"+huma_id.substring(huma_id.lastIndexOf('-') + 1)+"',huma_id) or area_id=nvl" +
									"('"+REMARKS_AREA_ID.substring(REMARKS_AREA_ID.lastIndexOf('-') + 1)+"',area_id) or bsflunit_ucode=nvl" +
											"('"+BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-') + 1)+"',bsflunit_ucode)) and" +
													" (remarks_fdate=to_date(dates) or (remarks_tdate=to_date(dates)))");
			*/			
			String huma_id2=huma_id.substring(huma_id.lastIndexOf('-') + 1);
			String REMARKS_AREA_ID2=REMARKS_AREA_ID.substring(REMARKS_AREA_ID.lastIndexOf('-') + 1);
			String BSFLUNIT_UCODE2=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-') + 1);
			//System.out.println("huma_id2="+huma_id2);
			//System.out.println("Remarks_area_id2="+REMARKS_AREA_ID2);
			//System.out.println("bsflunit_ucode2="+BSFLUNIT_UCODE2);
			//System.out.println("REMARKS_FS_HO2="+REMARKS_FS_HO);
//			ps2 = con.prepareStatement("select distinct 'Check' from war_remarks,(SELECT TO_DATE('"+ REMARKS_FDATE+ "','dd-mm-yyyy') + LEVEL - 1 " +
//					"dates FROM DUAL CONNECT BY LEVEL <= (TO_DATE('"+ REMARKS_TDATE+ "','dd-mm-yyyy') - TO_DATE('"+ REMARKS_FDATE+ "','dd-mm-yyyy')) " +
//							"+ 1) where (huma_id='"+huma_id2+"' or area_id='"+REMARKS_AREA_ID2+"' or bsflunit_ucode='"+BSFLUNIT_UCODE2+"') and" +
//													" (remarks_fdate=to_date(dates) or (remarks_tdate=to_date(dates)))");
			
			ps2 = con.prepareStatement("select distinct 'Check' from war_remarks,(SELECT TO_DATE('"+ REMARKS_FDATE+ "','dd-mm-yyyy') + LEVEL - 1 " +
					"dates FROM DUAL CONNECT BY LEVEL <= (TO_DATE('"+ REMARKS_TDATE+ "','dd-mm-yyyy') - TO_DATE('"+ REMARKS_FDATE+ "','dd-mm-yyyy')) " +
							"+ 1) where ((REMARKS_FS_HO='"+REMARKS_FS_HO+"' and huma_id='"+huma_id2+"') or (REMARKS_FS_HO='"+REMARKS_FS_HO+"' and area_id='"+REMARKS_AREA_ID2+"') or (REMARKS_FS_HO='"+REMARKS_FS_HO+"' and bsflunit_ucode='"+BSFLUNIT_UCODE2+"')) and" +
													" (remarks_fdate=to_date(dates) or (remarks_tdate=to_date(dates)))");
			rs2 = ps2.executeQuery();
			//if (rs2 == null) 
			if ((rs2.next()) == true)//else // Checking the entry exist
			{	out.println("Already existing entry for selected dates");
				return;
			}
				////System.out.println("perform insert operation");
			//----Existing entry checking code ends----------
			
			String username = (String) request.getSession().getAttribute("username");
			////System.out.println(REMARKS_Reason_HO);
			////System.out.println(REMARKS_Backlogs_HO);
			////System.out.println(REMARKS_AH_Backlogs);
			////System.out.println(REMARKS_AREA_ID);
			////System.out.println(REMARKS_UH_Backlogs);
			////System.out.println(REMARKS_Reason_UH);
			ps6 = con.prepareStatement("select nvl((select distinct to_number(REMARKS_CONTROLENO+1) from WAR_REMARKS where REMARKS_CONTROLENO=(select max(REMARKS_CONTROLENO) from WAR_REMARKS)),1) as REMARKS_CONTROLENO from dual");
			rs6 = ps6.executeQuery();
			rs6.next();
			String REMARKS_CONTROLENO = Integer.toString(rs6.getInt(1));
			ps1 = con.prepareStatement("insert into WAR_REMARKS(" +
						"REMARKS_SEQID,REMARKS_FDATE,REMARKS_TDATE," +
						"HUMA_ID,BSFLUNIT_UCODE,AREA_ID," +
						"REMARKS_Backlogs_HO,REMARKS_Reason_HO,REMARKS_Backlogs_UH," +
						"REMARKS_Reason_UH,REMARKS_Backlogs_AH,REMARKS_Reason_AH," +
						"REMARKS_Backlogs_FS,REMARKS_CONTROLENO,REMARKS_CBY,REMARKS_ACTPOD,REMARKS_FS_HO) "
								+ "values(WAR_REMARKS1.nextval,to_date(?,'dd-mm-yyyy'),to_date(?,'dd-mm-yyyy')," +
								"?,?,?," +
								"?,?,?," +
								"?,?,?," +
								"?,?,?,?,?)");
		ps1.setString(1,REMARKS_FDATE);
		System.out.println(REMARKS_AREA_ID.substring(REMARKS_AREA_ID.lastIndexOf('-') + 1).trim());
		ps1.setString(2,REMARKS_TDATE );
		ps1.setString(3,huma_id.substring(huma_id.lastIndexOf('-') + 1).trim());
		ps1.setString(4,ucode);
		ps1.setString(5,areaid);
		ps1.setString(6,REMARKS_Backlogs_HO );
		ps1.setString(7,REMARKS_Reason_HO);
		ps1.setString(8,REMARKS_UH_Backlogs);
		ps1.setString(9,REMARKS_Reason_UH);
		ps1.setString(10,REMARKS_AH_Backlogs);
		ps1.setString(11,REMARKS_Reason_AH);
		ps1.setString(12,REMARKS_FS_Backlogs);
		ps1.setLong(13,Integer.parseInt(REMARKS_CONTROLENO));
		ps1.setString(14,username);
		ps1.setLong(15,REMARKS_ACTPOD);
		ps1.setString(16,REMARKS_FS_HO);
		f = ps1.executeUpdate();
	// ////System.out.println("No of Records are inserted are " +
	// count);
	if (f != 0)
		out.println("OKWAR Room Remarks Entry created successfully");
	else
		out.println("WAR Room Remarks Entry is not created for some reasons");
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