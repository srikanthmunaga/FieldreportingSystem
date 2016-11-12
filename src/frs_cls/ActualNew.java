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

public class ActualNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	private JdbcConnect jc = new JdbcConnect();
	private StringTokenizer st = null;
	PreparedStatement ps1 = null, ps4 = null, ps6 = null;
	ResultSet rs4 = null, rs6 = null;
	int f = 0, e = 0;
	ArrayList<String> al;
	ArrayList<String> al1;

	public ActualNew() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		// ////System.out.println("inside post method");
		try {
			con = jc.getConnection();
			System.out.println("came here");
			PrintWriter out = response.getWriter();
			Calendar ca1 = Calendar.getInstance();// from here four lines
			ca1.get(Calendar.DATE);
			ca1.get(Calendar.YEAR);

			String username = (String) request.getSession().getAttribute(
					"username");
			System.out.println("the username is "+username);
			String BSFLUNIT_UCODE = request.getParameter("BSFLUNIT_UCODE")
					.trim();
			System.out.println("the unitcode is "+BSFLUNIT_UCODE);
			String unitcode=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf("-")+1);
			System.out.println("the unit code is"+unitcode);
			String statehead=request.getParameter("name_sh");
			System.out.println("the name of statehead is"+statehead);
			String region=request.getParameter("reg");
			System.out.println("the region is"+region);
			//String WAR_POD = request.getParameter("pod").trim();
			//reason
	/*		String war_reason  = request.getParameter("war_reason").trim();
			//WAR_Lsrbacklogs 
			String WAR_Lsrbacklogs  = request.getParameter("WAR_Lsrbacklogs").trim();
	*/		// ////System.out.println(BSFLUNIT_UCODE);
			String war_date[] = request.getParameterValues("war_date");
			// //System.out.println(war_date);
			String huma_id[] = request.getParameterValues("huma_id");
			String category[]=request.getParameterValues("act_id");
			//String activity= category.substring(category.lastIndexOf('-')+1);
			String unit_visited[]=request.getParameterValues("unit_visited");
			
			// //System.out.println("LSR field legth  :" + war_date.length);
			String war_uh[]=request.getParameterValues("visited_with");
			String war_cust_count[] = request
					.getParameterValues("war_cust_count");
			String war_sdrcust_count[] = request
					.getParameterValues("war_sdrcust_count");
			//String sty[]=request.getParameterValues("stay");
			String obs[]=request.getParameterValues("observations");
			String war_villages[] = request.getParameterValues("war_villages");
			System.out.println("Rajesh Checked war_villages Length = "+war_villages.length);
			
			
			//String war_sdrcust_count_opted[] = request.getParameterValues("war_sdrcust_count_opted");
			for (int i = 0; i < war_date.length; i++) {
				huma_id[0] = huma_id[0].trim();
				System.out.println("the huma_id is "+huma_id[0]);
				category[i]=category[i].trim();
				System.out.println(category[i]);
				//category[i]= category[i].substring(category[i].lastIndexOf('-')+1);
				System.out.println("the category is "+category[i]);
				unit_visited[i]=unit_visited[i].trim();
				System.out.println(unit_visited[i]);
				war_cust_count[i] = war_cust_count[i].trim();
				System.out.println("the cust count"+war_cust_count[i]);
				war_sdrcust_count[i] = war_sdrcust_count[i].trim();
				System.out.println("the sdrcust count is "+war_sdrcust_count[i]);
				war_uh[i]=war_uh[i].trim();
				System.out.println("visited with "+war_uh[i]);
				//sty[i]=sty[i].trim();
				obs[i]=obs[i].trim();
				System.out.println("The observations are "+obs[i]);
				//war_sdrcust_count_opted[i] = war_sdrcust_count_opted[i].trim();
			}
			
			String login_huma_id = request.getSession().getAttribute("huma_id")
					.toString();
			request.getSession().getAttribute("username").toString();
			// String role =
			// request.getSession().getAttribute("userrole").toString();
			HttpSession ses = request.getSession(false);
			String userrole = (String) ses.getAttribute("userrole");
			// Role Implemented By Rajesh
			String sql1;
			String sql2;
			String areano1 = null;
			String areano2 = null;
			String result2 = "false";
			// System.out.println("Role Checked By Rajesh and the role is = "+
			// userrole);
			// System.out.println("The log in huma_id= " + login_huma_id);
			String entered_huma_id = huma_id[0].substring(huma_id[0]
					.lastIndexOf('-') + 1);

			// System.out.println("Entered huma_id= " + entered_huma_id);
			if (userrole.equals("areahead")) {
				try {
					// //System.out.println("inside the areahead block");
					// logged in user belongs to which area
					sql1 = "select area_id from area_mstr where huma_id in('"
							+ login_huma_id + "')";
					// The user to be deleted belongs to which area
					sql2 = "select area_id from bsflunit_mstr where bsflunit_ucode=(select bsflunit_ucode from huma_mstr where huma_id='"
							+ entered_huma_id + "')";
					Statement st2 = con.createStatement();
					ResultSet rs2 = st2.executeQuery(sql2);
					// System.out.println("sql2 executed");
					if (rs2 != null) {
						// System.out.println("rs2 not null");
						while (rs2.next()) {
							areano2 = rs2.getString(1);
						}// while
					}// if
					Statement st1 = con.createStatement();
					ResultSet rs1 = st1.executeQuery(sql1);
					// System.out.println("sql1 executed");
					if (rs1 != null) {
						while (rs1.next()) {
							areano1 = rs1.getString(1);
							if (areano1.equals(areano2)) {
								result2 = "true";
								break;
							}// if
							else {
								result2 = "false";
							}// else
						}// while
					}// if
						// System.out.println("areano1= " + areano1);
					// if(!areano1.equals(areano2))
					if (!result2.equals("true")) {
						out.println("You are not allowed to work on another Region");
						return;
					}
				} catch (Exception e) {
					e.getMessage();
					e.printStackTrace();
				}
			}// if(role.equals("areahead"))
			if (userrole.equals("unithead")) {
				try {
					sql1 = "select bsflunit_ucode from bsflunit_mstr where huma_id in('"
							+ login_huma_id + "')";
					// The user to be deleted belongs to which area
					sql2 = "select bsflunit_ucode from huma_mstr where huma_id='"
							+ entered_huma_id + "'";
					Statement st2 = con.createStatement();
					ResultSet rs2 = st2.executeQuery(sql2);
					// System.out.println("sql2 executed");
					if (rs2 != null) {
						// System.out.println("rs2 not null");
						while (rs2.next()) {
							areano2 = rs2.getString(1);
						}// while
					}// if
						// Connection con1=jc.getConnection();
					Statement st1 = con.createStatement();
					ResultSet rs1 = st1.executeQuery(sql1);
					// System.out.println("sql1 executed");
					if (rs1 != null) {
						while (rs1.next()) {
							areano1 = rs1.getString(1);
							if (areano1.equals(areano2)) {
								result2 = "true";
								break;
							}// if
							else {
								result2 = "false";
							}// else
						}// while
					}// if
						// System.out.println("areano1= " + areano1);
					// if(!areano1.equals(areano2))
					if (!result2.equals("true")) {
						out.println("You are not allowed to work on another Unit");
						return;
					}
				} catch (Exception e) {
					e.getMessage();
				}
			}// if(role.equals("unithead"))
			if (userrole.equals("user")) {
				// //System.out.println("inside the user role");
				if (!entered_huma_id.equals(login_huma_id)) {
					out.println("You are not allowed to work for other User");
					return;
				}
			}
			
			//Check It
			/*for (int i = 0; i < war_villages.length; i++) {
				String village = war_villages[i];
				System.out.println("by srikant"+village);
				String unit=unit_visited[i];
				unit=unit.substring(unit.lastIndexOf('-')+1);
				System.out.println("the unit is "+unit);
				st = new StringTokenizer(village, "::");
				while (st.hasMoreElements()) {
					// //System.out.println("Msr debug in loop");
					String txt = (String) st.nextElement();
					
					System.out.println("Msr debug vcode" + txt.trim());
					String vcode = txt.trim().substring(txt.lastIndexOf('-') + 1);
					System.out.println("the village code is "+vcode);
					Statement svst1 = con.createStatement();
					//String user_role = request.getSession().getAttribute("userrole").toString();
					String svsql1 = "";
					if(user_role.equals("unithead"))
					
						svsql1 = "select vcode from village_mstr where vcode='"+vcode+"' and BSFLUNIT_UCODE in(select bsflunit_ucode from bsflunit_mstr where huma_id='"+login_huma_id+"')";
					
					else
					
					svsql1 = "select vcode from village_mstr where vcode='"
							+ vcode
							+ "' and BSFLUNIT_UCODE = '"
							+ BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-') + 1,
									BSFLUNIT_UCODE.length()) + "'";
					
					// if (vcode.trim().length() == 0) {
					// out.println("Entered (" + vcode + ") does not Exist ");
					// return;
					// }// if
					ResultSet svrs1 = svst1.executeQuery(svsql1);
					if ((vcode.trim().length() == 0) || (!svrs1.next())) {
						out.println("Entered Village code [" + vcode
								+ "] does not Exist");
						return;
					}
				}// while
			}*/// for
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
			}// for
			for (int i = 0; i < war_date.length; i++) {
				String huma_id21 = huma_id[0];
				huma_id21 = huma_id21.substring(huma_id21.lastIndexOf('-') + 1);
				// Connection svcon1 = new JdbcConnect().getConnection();
				String svsql1 = "select huma_id from HUMA_MSTR where HUMA_ID='"
						+ huma_id21 + "'";
				Statement svst1 = con.createStatement();
				ResultSet svrs1 = svst1.executeQuery(svsql1);
				if (!svrs1.next()) {
					out.println("Entered Field Staff code does not Exist :"
							+ huma_id21);
					return;
				}
			}
			try {
				
				ps6 = con.prepareStatement("select nvl((select distinct to_number(WaR_CONTROLNO+1) from ACTUAL_PLANNEW where WAR_CONTROLNO=(select max(WAR_CONTROLNO) from ACTUAL_PLANNEW)),1) as WAR_CONTROLNO from dual");
				rs6 = ps6.executeQuery();
				rs6.next();
				String WAR_CONTROLENO = Integer.toString(rs6.getInt(1));
				for (int i = 0; i < war_date.length; i++) {
					int x4 = 0;// to check entry existance
					ps4 = con
							.prepareStatement("select distinct 'EntryExist' from ACTUAL_PLANNEW w,bsflunit_mstr b,huma_mstr h where h.huma_id=w.huma_id "
									+ "and b.bsflunit_ucode=h.bsflunit_ucode and w.huma_id='"
									+ huma_id[0].substring(huma_id[0]
											.lastIndexOf('-') + 1)
									+ "' and w.WAR_DATE=to_date('"
									+ war_date[i] + "','dd-mm-yyyy')");// query
					// System.out.println("war_date= " + war_date +
					rs4 = ps4.executeQuery();
					if ((rs4.next()) == false)
						x4 = x4 + 1;
					if (x4 == 0) {
						out.println("Already existing date for Field Staff : "
								+ huma_id[0]);
						return;
					}
				}
				//WAR_POD 
				ps1 = con.prepareStatement("insert into ACTUAL_PLANNEW(WAR_DATE,HUMA_ID,NAME_SH,REGION,ACTIVITY_ID,WITH_UH,WAR_VILLAGES,WAR_CUST_COUNT,WAR_SDRCUST_COUNT,BSFLUNIT_UCODE,WAR_OBSERVATIONS,WAR_CBY,WAR_CONTROLNO,UNIT_VISITED) "//,WAR_POD
								+ "values(to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,?,?,?,?,?,?,?)");
				// System.out.println("Total no of rows= " + war_date.length);
				// for (int i = 0; i < war_date.length; i++) {
				for (int i = 0; i < war_date.length; i++) {
					ps1.setString(1, war_date[i]);
					ps1.setString(2, huma_id[0]);
					ps1.setString(3,statehead);
					ps1.setString(4,region);
					ps1.setString(5, category[i]);
					
					ps1.setString(6,war_uh[i]);
					ps1.setString(7, war_villages[i]);// war_cust_count
					ps1.setString(8, war_cust_count[i]);
					ps1.setString(9, war_sdrcust_count[i]);
					ps1.setString(10,unitcode);
					//ps1.setString(7,sty[i]);
					ps1.setString(11,obs[i]);
					///ps1.setLong(6, Integer.parseInt(war_sdrcust_count_opted[i]));
					ps1.setString(12, username);
					ps1.setLong(13, Integer.parseInt(WAR_CONTROLENO));
					ps1.setString(14, unit_visited[i]);
					//System.out.println(WAR_POD);
					//ps1.setLong(8, Long.parseLong(WAR_POD));
					//System.out.println(war_reason);
					//ps1.setString(10, war_reason);//
					//ps1.setString(10, WAR_Lsrbacklogs);//
					// //////System.out.println("Msr debug beforer executing");
					f = ps1.executeUpdate();
				}
				// System.out.println("No of Records are inserted are " +
				// count);
				if (f != 0)
					out.println("OK Actual Plan Data Entry created successfully");
				else
					out.println("Actual Entry is not created for some reasons");
				ps1.close();
			}// try
			catch (SQLException sqle) {
				if (sqle.getErrorCode() == 2292)
					out.println("WAR Room Data Entry cannot be Updated : Child record found for the -WAR Room Data Entry id");
				else if (sqle.getErrorCode() == 1) { // ops_HUMA_ACTIVITYSL_PLACEUQ
					out.println("Duplicate Farmer Activity Entries found,Pls differ either Activity or Place");
				}// else if(sqle.getErrorCode()==1)
				else if (sqle.getErrorCode() == 2291) {
					sqle.printStackTrace();
					if (sqle.getMessage().split("_")[1].trim().equals(
							"HUMA) violated - parent key not found"))
						out.println("Entered Employee id does not Exist");
					else
						out.println("Other integrity constraint related exception or error");
				}// else if(sqle.getErrorCode()==2291)
				else
					sqle.printStackTrace();
			}// catch(SQLException sqle)
		}// try
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