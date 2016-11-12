package frs_cls;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class sactualtarget extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	private JdbcConnect jc = new JdbcConnect();
	private StringTokenizer st = null;
	PreparedStatement ps1 = null, ps4 = null, ps6 = null;
	ResultSet rs4 = null, rs6 = null;
	int f = 0, e = 0;
	ArrayList<String> al;
	ArrayList<String> al1;

	public sactualtarget() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
		try {
			con = jc.getConnection();
			PrintWriter out = response.getWriter();
			Calendar ca1 = Calendar.getInstance();// from here four lines
			ca1.get(Calendar.DATE);
			ca1.get(Calendar.YEAR);

			String username = (String) request.getSession().getAttribute("username");
			String BSFLUNIT_UCODE = request.getParameter("BSFLUNIT_UCODE").trim();
			System.out.println("BSFLUNIT_UCODE:"+BSFLUNIT_UCODE);
			String war_date[] = request.getParameterValues("war_date");
			String huma_id[] = request.getParameterValues("huma_id");
			String uhfxlsr[] = request.getParameterValues("uhfxlsr");
			String no_villages[]=request.getParameterValues("no_villages");
			String odftod[]=request.getParameterValues("odftod");
			String customers[]=request.getParameterValues("customers");
			String stay_unit[]=request.getParameterValues("stay_unit");
			String observations[]=request.getParameterValues("observations");
			String war_villages[] = request.getParameterValues("war_villages");
			
			for (int i = 0; i < war_date.length; i++) {
				huma_id[0] = huma_id[0].trim();
				uhfxlsr[i] = uhfxlsr[i].trim();
				no_villages[i]=no_villages[i].trim();
				odftod[i]=odftod[i].trim();
				customers[i]=customers[i].trim();
				stay_unit[i]=stay_unit[i].trim();
				observations[i]=observations[i].trim();
			}
			String login_huma_id = request.getSession().getAttribute("huma_id").toString();
			request.getSession().getAttribute("username").toString();
			HttpSession ses = request.getSession(false);
			String userrole = (String) ses.getAttribute("userrole");
			String sql1;
			String sql2;
			String areano1 = null;
			String areano2 = null;
			String result2 = "false";
			String entered_huma_id = huma_id[0].substring(huma_id[0]
					.lastIndexOf('-') + 1);

			if (userrole.equals("areahead")) {
				try {
					sql1 = "select area_id from area_mstr where huma_id in('"
							+ login_huma_id + "')";
					sql2 = "select area_id from bsflunit_mstr where bsflunit_ucode=(select bsflunit_ucode from huma_mstr where huma_id='"
							+ entered_huma_id + "')";
					Statement st2 = con.createStatement();
					ResultSet rs2 = st2.executeQuery(sql2);
					if (rs2 != null) {
						while (rs2.next()) {
							areano2 = rs2.getString(1);
						}
					}
					Statement st1 = con.createStatement();
					ResultSet rs1 = st1.executeQuery(sql1);
					if (rs1 != null) {
						while (rs1.next()) {
							areano1 = rs1.getString(1);
							if (areano1.equals(areano2)) {
								result2 = "true";
								break;
							}
							else {
								result2 = "false";
							}
						}
					}
					if (!result2.equals("true")) {
						out.println("You are not allowed to work on another Region");
						return;
					}
				} catch (Exception e) {
					e.getMessage();
					e.printStackTrace();
				}
			}
			if (userrole.equals("unithead")) {
				try {
					sql1 = "select bsflunit_ucode from bsflunit_mstr where huma_id in('"
							+ login_huma_id + "')";
					sql2 = "select bsflunit_ucode from huma_mstr where huma_id='"
							+ entered_huma_id + "'";
					Statement st2 = con.createStatement();
					ResultSet rs2 = st2.executeQuery(sql2);
					if (rs2 != null) {
						while (rs2.next()) {
							areano2 = rs2.getString(1);
						}
					}
					Statement st1 = con.createStatement();
					ResultSet rs1 = st1.executeQuery(sql1);
					if (rs1 != null) {
						while (rs1.next()) {
							areano1 = rs1.getString(1);
							if (areano1.equals(areano2)) {
								result2 = "true";
								break;
							}
							else {
								result2 = "false";
							}
						}
					}
					if (!result2.equals("true")) {
						out.println("You are not allowed to work on another Unit");
						return;
					}
				} catch (Exception e) {
					e.getMessage();
				}
			}
			if (userrole.equals("user")) {
				if (!entered_huma_id.equals(login_huma_id)) {
					out.println("You are not allowed to work for other User");
					return;
				}
			}
			/*for (int i = 0; i < war_villages.length; i++) {
				String village = war_villages[i];
				st = new StringTokenizer(village, "::");
				while (st.hasMoreElements()) {
					String txt = (String) st.nextElement();
					String vcode = txt.trim().substring(
							txt.lastIndexOf('-') + 1);
					
					Statement svst1 = con.createStatement();
					String svsql1 = "";
					svsql1 = "select vcode from village_mstr where vcode='"
							+ vcode
							+ "' and BSFLUNIT_UCODE = '"
							+ BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-') + 1,
									BSFLUNIT_UCODE.length()) + "'";
					ResultSet svrs1 = svst1.executeQuery(svsql1);
					if ((vcode.trim().length() == 0) || (!svrs1.next())) {
						out.println("Entered Village code [" + vcode
								+ "] does not Exist");
						return;
					}
				}
			}
			for (int i = 0; i < war_villages.length; i++) {
				String village = war_villages[i];
				String cache = "";
				if (village.contains("::")) {
					st = new StringTokenizer(village, "::");
					while (st.hasMoreElements()) {
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
				}
			}*/
			for (int i = 0; i < war_villages.length; i++) {
				String village = war_villages[i];
				st = new StringTokenizer(village, "::");
				while (st.hasMoreElements()) {
					String txt = (String) st.nextElement();
					String vcode = txt.trim().substring(
							txt.lastIndexOf('-'));
					String vname=txt.trim().replaceAll(vcode,"");
					Statement svst1 = con.createStatement();
					String svsql1 = "";
					svsql1 = "select vname from village_mstr where vname='"
							+ vname
							+ "' and BSFLUNIT_UCODE = '"
							+ BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-') + 1,
									BSFLUNIT_UCODE.length()) + "'";
					ResultSet svrs1 = svst1.executeQuery(svsql1);
					if ((vname.trim().length() == 0) || (!svrs1.next())) {
						out.println("Entered Village vname [" + vname
								+ "] does not Exist");
						return;
					}
				}
			}
			for (int i = 0; i < war_villages.length; i++) {
				String village = war_villages[i];
				String cache = "";
				if (village.contains("::")) {
					st = new StringTokenizer(village, "::");
					while (st.hasMoreElements()) {
						String txt = (String) st.nextElement();
						int i1 = txt.lastIndexOf('-');
						String vcode = txt.substring(i1 + 1);
						String vname=txt.replaceAll(vcode,"");
						cache += vname + "::";
					}
					int length = cache.length();
					war_villages[i] = cache;
				} else {
					/*int i1 = village.lastIndexOf('-');
					String vcode = village.substring(i1 + 1);*/
					war_villages[i] = village;
				}
			}
			
			
			for (int i = 0; i < war_date.length; i++) {
				String huma_id21 = huma_id[0];
				huma_id21 = huma_id21.substring(huma_id21.lastIndexOf('-') + 1);
				String svsql1 = "select huma_id from HUMA_MSTR where HUMA_ID='"
						+ huma_id21 + "'";
				Statement svst1 = con.createStatement();
				ResultSet svrs1 = svst1.executeQuery(svsql1);
				if (!svrs1.next()) {
					out.println("Entered Emp Id does not Exist :"
							+ huma_id21);
					return;
				}
			}
			try {
				ps6 = con
						.prepareStatement("select nvl((select distinct to_number(WORK_CONTROLENO+1) from WORK_PLAN1 where WORK_CONTROLENO=(select max(WORK_CONTROLENO) from WORK_PLAN1)),1) as WORK_CONTROLENO from dual");
				rs6 = ps6.executeQuery();
				rs6.next();
				String WAR_CONTROLENO = Integer.toString(rs6.getInt(1));
				for (int i = 0; i < war_date.length; i++) {
					int x4 = 0;
					ps4 = con
							.prepareStatement("select distinct 'EntryExist' from WORK_PLAN1 w,bsflunit_mstr b,huma_mstr h where h.huma_id=w.huma_id "
									+ "and b.bsflunit_ucode=h.bsflunit_ucode and w.huma_id='"
									+ huma_id[0].substring(huma_id[0]
											.lastIndexOf('-') + 1)
									+ "' and w.WORK_DATE=to_date('"
									+ war_date[i] + "','dd-mm-yyyy')");
					rs4 = ps4.executeQuery();
					if ((rs4.next()) == false)
						x4 = x4 + 1;
					if (x4 == 0) {
						out.println("Already existing date for Emp Id : "
								+ huma_id[0]);
						return;
					}
				}
				/*ps1 = con.prepareStatement("insert into WAR_TARGET(WAR_SEQID,WAR_DATE,HUMA_ID,WAR_VILLAGES,WAR_CUST_COUNT,WAR_SDRCUST_COUNT,WAR_CBY,WAR_CONTROLENO) "//,WAR_POD
								+ "values(WAR_SEQID.nextval,to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?)");*/
				
				ps1 = con.prepareStatement("insert into WORK_PLAN1(WORK_SEQID,WORK_DATE,HUMA_ID,VILLAGE_NAMES,UHFXLSR,NO_VILLAGES,ODFTOD,CUSTOMERS,STAY_UNIT,OBSERVATIONS,WORK_CBY,WORK_CONTROLENO) "//,WAR_POD
						+ "values(WORK_SEQID1.nextval,to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,?,?,?,?)");
				
				for (int i = 0; i < war_date.length; i++) {
					ps1.setString(1, war_date[i]);
					ps1.setString(2, huma_id[0].substring(huma_id[0].lastIndexOf('-') + 1));
					ps1.setString(3, war_villages[i]);
					ps1.setString(4, uhfxlsr[i]);
					ps1.setInt(5,Integer.parseInt(no_villages[i]));
					ps1.setInt(6,Integer.parseInt(odftod[i]));
					ps1.setInt(7,Integer.parseInt(customers[i]));
					ps1.setString(8,stay_unit[i]);
					ps1.setString(9,observations[i]);
					ps1.setString(10,username);
					ps1.setInt(11, Integer.parseInt(WAR_CONTROLENO));
					
					f = ps1.executeUpdate();
				}
				
				if (f != 0)
					out.println("OK Actual Entry created successfully");
				else
					out.println("Actual Entry is not created for some reasons");
				ps1.close();
			}
			catch (SQLException sqle) {
				if (sqle.getErrorCode() == 2292)
					out.println("Actual Entry cannot be Updated : Child record found for the -Actual Entry id");
				else if (sqle.getErrorCode() == 1) { 
					out.println("Duplicate Farmer Activity Entries found,Pls differ either Activity or Place");
				}
				else if (sqle.getErrorCode() == 2291) {
					sqle.printStackTrace();
					if (sqle.getMessage().split("_")[1].trim().equals(
							"HUMA) violated - parent key not found"))
						out.println("Entered Employee id does not Exist");
					else
						out.println("Other integrity constraint related exception or error");
				}
				else
					sqle.printStackTrace();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (rs4 != null)
					rs4.close();
				if (rs6 != null)
					rs6.close();
				if (ps1 != null)
					ps1.close();
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