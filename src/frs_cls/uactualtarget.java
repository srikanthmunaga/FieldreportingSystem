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
public class uactualtarget extends HttpServlet {
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

	public uactualtarget() {
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

			String username = (String) request.getSession().getAttribute("username");
			HashSet<String> smsDates = new HashSet<String>();
			String BSFLUNIT_UCODE = request.getParameter("BSFLUNIT_UCODE").trim();
			String controlno=request.getParameter("UHLOG_CONTROLENO2").trim();
			String war_date[] = request.getParameterValues("war_date");
			String huma_id[] = request.getParameterValues("huma_id");
			String huma_id21 = huma_id[0];
			huma_id21 = huma_id21.substring(huma_id21.lastIndexOf('-') + 1);
			String uhfxlsr[] = request.getParameterValues("uhfxlsr");
			String no_villages[]=request.getParameterValues("no_villages");
			String odftod[]=request.getParameterValues("odftod");
			String customers[]=request.getParameterValues("customers");
			String stay_unit[]=request.getParameterValues("stay_unit");
			String observations[]=request.getParameterValues("observations");
			String war_villages[] = request.getParameterValues("war_villages");
			
			for (int i = 0; i < war_date.length; i++) {
				war_date[i] = war_date[i].trim();
				uhfxlsr[i] = uhfxlsr[i].trim();
				no_villages[i]=no_villages[i].trim();
				odftod[i]=odftod[i].trim();
				customers[i]=customers[i].trim();
				stay_unit[i]=stay_unit[i].trim();
				observations[i]=observations[i].trim();
			} 
			
			for (int i = 0; i < war_date.length; i++) {
				String svsql1 = "select huma_id from HUMA_MSTR where HUMA_ID='"
						+ huma_id21 + "' and BSFLUNIT_UCODE='"+BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-') + 1)+"'";
				Statement svst1 = con.createStatement();
				ResultSet svrs1 = svst1.executeQuery(svsql1);
				if (!svrs1.next()) {
					out.println("Entered Emp Id does not Exist / does not belongs to particular Unit:"	+ huma_id21);
					return;
				}
	
			} 
						
			/*for (int i = 0; i < war_villages.length; i++) {
				String village = war_villages[i];
				String cache = "";
				st = new StringTokenizer(village, "::");
				if (village.contains("::")) {
					while (st.hasMoreElements()) {
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
					svsql1 = "select vcode from village_mstr where vcode='"
							+ vcode
							+ "' and BSFLUNIT_UCODE = '"
							+ BSFLUNIT_UCODE.substring(
									BSFLUNIT_UCODE.lastIndexOf('-') + 1,
									BSFLUNIT_UCODE.length()) + "'";
					ResultSet svrs1 = svst1.executeQuery(svsql1);
					if ((vcode.trim().length() == 0)||(!svrs1.next())) {
						out.println("Entered Village code [" + vcode + "] does not Exist");
						return;
					}
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
			try {
				ps1 = con.prepareStatement("update WORK_PLAN1 set VILLAGE_NAMES=?,"
								+ "UHFXLSR=?,"
								+ "NO_VILLAGES=?,"
								+ "ODFTOD=?,"
								+ "CUSTOMERS=?, "
								+ "STAY_UNIT=?,"
								+ "OBSERVATIONS=?,"
								+ "WORK_MBY=?,"
								+ "WORK_MDATE=sysdate ,huma_id=?  where WORK_CONTROLENO=? and WORK_DATE=to_date(?,'dd-mm-yyyy')");
				smsDates.clear();
				for (int i = 0; i < war_date.length; i++) {
					int x4 = 0;
					ps4 = con.prepareStatement("select distinct huma_id from frs_recovery r where huma_id='"
									+ huma_id21
									+ "' and frs_date=to_date('"
									+ war_date[i] + "','dd-mm-yyyy')");
					rs4 = ps4.executeQuery();
					if ((rs4.next()) == false)
						x4 = x4 + 1;
					if (x4 == 0) {
						smsDates.add(war_date[i]);
					}
					System.out.println(war_villages[i]);
					ps1.setString(1, war_villages[i]);
					ps1.setString(2, uhfxlsr[i]);
					ps1.setInt(3,Integer.parseInt(no_villages[i]));
					ps1.setInt(4,Integer.parseInt(odftod[i]));
					ps1.setInt(5,Integer.parseInt(customers[i]));
					ps1.setString(6,stay_unit[i]);
					ps1.setString(7,observations[i]);
					ps1.setString(8, username);
					ps1.setString(9, huma_id21);
					ps1.setLong(10, Long.parseLong(controlno));
					ps1.setString(11, war_date[i]);
					
					f = ps1.executeUpdate();
					
				} 
					
				if (f != 0) {
					out.println("OK Actual Entry updated successfully");
				}
				else {
					out.println("Actual Entry is not updated for some reasons");
				}
				ps1.close();
			}
			catch (SQLException sqle) {
				if (sqle.getErrorCode() == 2292)
					out.println("Actual Entry cannot be Updated : Child record found for the Actual Entry id");
				else if (sqle.getErrorCode() == 1) { 
					out.println("Duplicate Actual Entry found,Pls differ either Emp Id or Date");
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