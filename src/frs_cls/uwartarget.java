package frs_cls;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class uwartarget
 */
public class uwartarget extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	private StringTokenizer st = null;
	private JdbcConnect jc = new JdbcConnect();
	PreparedStatement ps1 = null, ps4 = null, ps6 = null;
	ResultSet rs4 = null, rs6 = null;
	int f = 0, e = 0;;
	boolean f2 = false;
	ArrayList<String> al;
	ArrayList<String> al1;

	public uwartarget() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			con = jc.getConnection();
			PrintWriter out = response.getWriter();
			// Connection Con = null;
			Calendar ca1 = Calendar.getInstance();// from here four lines
			ca1.get(Calendar.DATE);
			ca1.get(Calendar.YEAR);

			String username = (String) request.getSession().getAttribute(
					"username");
			HashSet<String> smsDates = new HashSet<String>();
			String BSFLUNIT_UCODE = request.getParameter("BSFLUNIT_UCODE").trim();
			//UHLOG_CONTROLENO2
			String controlno=request.getParameter("UHLOG_CONTROLENO2").trim();
			System.out.println("controlno is :"+controlno);
			//String WAR_POD = request.getParameter("pod").trim();
			//reason
			//String war_reason  = request.getParameter("war_reason").trim();

			// System.out.println("UNIT CODE " + ucode);
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
			
			
//userrole area head select employee id's based on the unitcode under area id
			//write the condition for that
			
			//Hidden By Rajesh
			/*
			for (int i = 0; i < war_villages.length; i++) {
				String village = war_villages[i];
				st = new StringTokenizer(village, "::");
				while (st.hasMoreElements()) {
					String txt = (String) st.nextElement();
					
					
					String vcode = txt.trim().substring(
							txt.lastIndexOf('-') + 1);
					Statement svst1 = con.createStatement();
					String user_role = request.getSession().getAttribute("userrole").toString();
					String login_huma_id = request.getSession().getAttribute("huma_id").toString();
					String svsql1 = "";
					if(user_role.equals("unithead"))
					{
						svsql1 = "select vcode from village_mstr where vcode='"+vcode+"' and BSFLUNIT_UCODE in(select bsflunit_ucode from bsflunit_mstr where huma_id='"+login_huma_id+"')";
					}
					else
					{
					svsql1 = "select vcode from village_mstr where vcode='"
							+ vcode
							+ "' and BSFLUNIT_UCODE = '"
							+ BSFLUNIT_UCODE.substring(
									BSFLUNIT_UCODE.lastIndexOf('-') + 1,
									BSFLUNIT_UCODE.length()) + "'";
					}
//					if (vcode.trim().length() == 0) {
//						out.println("Entered (" + vcode + ") does not Exist ");
//						return;
//					}// if
					ResultSet svrs1 = svst1.executeQuery(svsql1);
					if ((vcode.trim().length() == 0)||(!svrs1.next())) {
						out.println("Entered Village code [" + vcode + "] does not Exist");
						return;
					}
				}// while
			}// for
			*/
			
			

			for (int i = 0; i < war_villages.length; i++) {
				String village = war_villages[i];
				String cache = "";
				st = new StringTokenizer(village, "::");
				if (village.contains("::")) {
					//st = new StringTokenizer(village, "::");
					while (st.hasMoreElements()) {
						// //System.out.println("Msr debug in loop");
						String txt = (String) st.nextElement();
						int i1 = txt.lastIndexOf('-');
						String vcode = txt.substring(i1 + 1);
						cache += vcode + "::";
					}
					int length = cache.length();
					System.out.println(cache.substring(0, length - 2));
					war_villages[i] = cache.substring(0, length - 2);
				} else {
					int i1 = village.lastIndexOf('-');
					String vcode = village.substring(i1 + 1);
					war_villages[i] = vcode;
				}
				System.out.println(st);
				
				while (st.hasMoreElements()) {
					String txt = (String) st.nextElement();
					
					
					String vcode = txt.trim().substring(txt.lastIndexOf('-') + 1);
							
					Statement svst1 = con.createStatement();
					String svsql1 = "";
					/*
					String user_role = request.getSession().getAttribute("userrole").toString();
					String login_huma_id = request.getSession().getAttribute("huma_id").toString();
					if(user_role.equals("unithead"))
					{
						svsql1 = "select vcode from village_mstr where vcode='"+vcode+"' and BSFLUNIT_UCODE in(select bsflunit_ucode from bsflunit_mstr where huma_id='"+login_huma_id+"')";
					}
					else
					*/
					svsql1 = "select vcode from village_mstr where vcode='"
							+ vcode
							+ "' and BSFLUNIT_UCODE = '"
							+ BSFLUNIT_UCODE.substring(
									BSFLUNIT_UCODE.lastIndexOf('-') + 1,
									BSFLUNIT_UCODE.length()) + "'";
					
//					if (vcode.trim().length() == 0) {
//						out.println("Entered (" + vcode + ") does not Exist ");
//						return;
//					}// if
					ResultSet svrs1 = svst1.executeQuery(svsql1);
					if ((vcode.trim().length() == 0)||(!svrs1.next())) {
						out.println("Entered Village code [" + vcode + "] does not Exist");
						return;
					}
				}// while
			}// for
			
			
			
			try {
				ps1 = con.prepareStatement("update WAR_TARGET set WAR_VILLAGES=?,"
								+ "WAR_CUST_COUNT=?,"
								+ "WAR_SDRCUST_COUNT=?,"
								/*+ "WAR_SDRCUST_COUNT_OPTED=?,"*/
								+ "WAR_MBY=?, "
								//+ "WAR_POD=?,"
/*								+ "war_reason=?,"//
								+ "WAR_Lsrbacklogs=?,"
*/								+ "WAR_MDATE=sysdate ,huma_id=?  where WAR_CONTROLENO=? and WAR_DATE=to_date(?,'dd-mm-yyyy')");
				smsDates.clear();
				for (int i = 0; i < war_date.length; i++) {
					// Checking for Recovery entry exists in FRS for that date,
					int x4 = 0;
					ps4 = con
							.prepareStatement("select distinct huma_id from frs_recovery r where huma_id='"
									+ huma_id21
									+ "' and frs_date=to_date('"
									+ war_date[i] + "','dd-mm-yyyy')");
					rs4 = ps4.executeQuery();
					if ((rs4.next()) == false)
						x4 = x4 + 1;
					if (x4 == 0) {
						smsDates.add(war_date[i]);
						// return;
						//continue;
					}
					//System.out.println("The Village code is "+war_cust_count[i]);
					System.out.println(war_villages[i]);
					ps1.setString(1, war_villages[i]);// war_cust_count
					ps1.setLong(2, Integer.parseInt(war_cust_count[i]));
					ps1.setLong(3, Integer.parseInt(war_sdrcust_count[i]));
					//ps1.setLong(4, Integer.parseInt(war_sdrcust_count_opted[i]));
					ps1.setString(4, username);
					//ps1.setLong(5,Long.parseLong(WAR_POD));
					ps1.setString(5, huma_id21);
					//ps1.setString(8,WAR_Lsrbacklogs);
					ps1.setLong(6, Long.parseLong(controlno));
					// ps1.setString(6, huma_id[0].substring(huma_id[0]
					// .lastIndexOf('-') + 1));
					ps1.setString(7, war_date[i]);
					// //System.out.println("Msr debug beforer executing");
					f = ps1.executeUpdate();
					// //System.out.println("Msr debug after executing");
				} // for
					// System.out.println("outside of for recovery existinst and smsDates="+smsDates);
				if (f != 0) {
					//if (smsDates.isEmpty())
						out.println("OK WAR Room Data Entry updated successfully");
//					else
//						// if(smsDates!=null)
//						out.println("OK WAR Room Data Entry updated successfully. Cannot change WAR Room target for "
//								+ smsDates
//								+ ", as your FRS Recovery already received");
				} // master created successfully
				else {
					// con.rollback();
					out.println("WAR Room Data Entry is not updated for some reasons");
				}
				ps1.close();
			}// try
			catch (SQLException sqle) {
				if (sqle.getErrorCode() == 2292)
					out.println("WAR Room Data Entry cannot be Updated : Child record found for the -WAR Room Data Entry id");
				else if (sqle.getErrorCode() == 1) { // ops_HUMA_ACTIVITYSL_PLACEUQ
					out.println("Duplicate WAP plan found,Pls differ either Field Staff or Date");
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
			finally {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// doPost()
}// Class