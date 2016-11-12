package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 * 
 * 
 * 
 * Servlet implementation class GetuserList
 */
public class GetuserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcConnect jc = new JdbcConnect();
	Connection con = null;
	PreparedStatement ps = null;
	private Logger log = Logger.getLogger(JdbcConnect.class);

	// fh
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetuserList() {

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
		// //System.out.println("inside the doGet method of getuserList");
		// //System.out.println("Msr debug 1");
		doPost(request, response);
		// //System.out.println("Msr debug 2");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Below five lines code is to session checking & login redirect
		// //System.out.println("inside the getUserList servlet dopost");
		String huma_id = request.getSession().getAttribute("huma_id")
				.toString();
		String Username = request.getSession().getAttribute("username")
				.toString();
		String role = request.getSession().getAttribute("userrole").toString();
		// //System.out.println("inside the GetUserList The role is "+role);

		if (request.getSession().getAttribute("username") == null) {
			response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
		} else {
			response.setHeader("Cache-Control", "no-cache");
			// //System.out.println("inside the doPost method");
			String data = "";
			String input1 = "";
			String parameter = request.getParameter("parameter").toString()
					.trim();
			input1 = request.getParameter("input1").toString().trim();
			

			try {
				con = jc.getConnection();
				// log.info("JDBC Connection was created");
				String curhuma_id = "";// if(parameter.equals("client_id2"))
				// below two lines code to take current user id, if
				// he/she is not a super user.
				/*
				 * if (!((String) request.getSession().getAttribute(
				 * "userType")).equals("SUPER")) curhuma_id = (String)
				 * ((HttpServletRequest) request)
				 * .getSession().getAttribute("user");
				 */// System.out.println("Current huma_id="+curhuma_id);
					// Below two lines code will be differ for each LOV code
					// System.out.println(" inside the try and before params blocks, parameter="+parameter);
					// for distinct huma_id list
				if (parameter.equals("huma_id"))
					ps = con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr order by huma_id desc");
				
	
				else if (parameter.equals("Allhuma_id")) {
					if ((role.equals("admin")) || (role.equals("audit")))
						ps = con.prepareStatement("select firstname||' '||lastname||'-'||huma_id from huma_mstr order by huma_id");
					if (role.equals("areahead"))
						ps = con.prepareStatement("select USERNAME||'-'||h.HUMA_ID from HUMA_MSTR h ,FRS_USER f where h.area_ID=(select area_ID from area_MSTR where  huma_id='"
								+ huma_id
								+ "') and h.HUMA_ID=F.HUMA_ID and h. HUMA_FNAME!='"
								+ Username
								+ "' and f.USERROLE!='admin' and f.USERROLE!='areahead' and f.USERROLE!='unithead'");
					if (role.equals("unithead"))
						ps = con.prepareStatement("select USERNAME||'-'||h.HUMA_ID from HUMA_MSTR h ,FRS_USER f  where h.bsflunit_UCODE=(select bsflunit_UCODE from HUMA_MSTR where  HUMA_ID='"
								+ huma_id
								+ "') and h.HUMA_ID=F.HUMA_ID and h. HUMA_FNAME!='"
								+ Username + "'");
				}
				// for distinct BSFLUNIT_UCODE list
				else if (parameter.equals("BSFLUNIT_UCODE")) {// for
																// BSFLUNIT_UCODE
																// list
					if ((role.equals("admin")) || (role.equals("audit")))
						ps = con.prepareStatement("select distinct BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE from BSFLUNIT_mstr");
					if (role.equals("areahead")) {
						System.out.println("Huma_id by Rajesh =" + huma_id);
						ps = con.prepareStatement("select distinct b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE from BSFLUNIT_mstr b, area_mstr a where  b.area_id in(select area_id from area_mstr where huma_id='"
								+ huma_id + "')");
					}
					if (role.equals("unithead"))
						ps = con.prepareStatement("select distinct b.BSFLUNIT_NAME||'-'||b.BSFLUNIT_UCODE from BSFLUNIT_mstr b, huma_mstr h where (select bsflunit_ucode from huma_mstr where huma_id='"
								+ huma_id + "')=b.bsflunit_ucode");
				}
				// for distinct username list
				else if (parameter.equals("ACTCAT_ID1"))// for company master
					// query
					ps = con.prepareStatement("select AC_NAME||'-'||AC_ID from AC_MSTR order by AC_ID desc");
				// By Rajesh to fetch the user of a specific unit
				else if (parameter.equals("huma_id_belongsto_unit"))
					ps = con.prepareStatement("select USERNAME||'-'||h.HUMA_ID from HUMA_MSTR h ,FRS_USER f  where h.bsflunit_UCODE=(select bsflunit_UCODE from HUMA_MSTR where  HUMA_ID='"
							+ huma_id
							+ "') and h.HUMA_ID=F.HUMA_ID and h. HUMA_FNAME!='"
							+ Username + "'");
				else if (parameter.equals("freeze_unit_user"))
					ps = con.prepareStatement("select USERNAME from FRS_USER f,HUMA_MSTR h where h.bsflunit_UCODE=(select bsflunit_UCODE from HUMA_MSTR where  HUMA_ID='"
							+ huma_id
							+ "') and h.HUMA_ID=F.HUMA_ID and h. HUMA_FNAME!='"
							+ Username + "'");
				else if (parameter.equals("freeze_area_user"))
					ps = con.prepareStatement("select USERNAME from FRS_USER f,HUMA_MSTR h where h.area_ID=(select area_ID from area_MSTR where  huma_id='"
							+ huma_id
							+ "') and h.HUMA_ID=F.HUMA_ID and h. HUMA_FNAME!='"
							+ Username
							+ "' and f.USERROLE!='admin' and f.USERROLE!='areahead';");
				else if (parameter.equals("huma_id_belongsto_area"))
					ps = con.prepareStatement("select USERNAME||'-'||HUMA_ID from FRS_USER where HUMA_ID='"
							+ huma_id + "'");

				// By rajesh To fetch the units belongs to a particular area
				else if (parameter.equals("BSFLUNIT_NAME_belongsto_area"))
					ps = con.prepareStatement("select USERNAME||'-'||HUMA_ID from FRS_USER where HUMA_ID='"
							+ huma_id + "'");

				else if (parameter.equals("unithead"))// for BSFLUNIT_UCODE list
					// ps =
					// con.prepareStatement("select NAME||'-'||HUMA_ID from FRS_USER where USERROLE='unithead' order by HUMA_ID");
					ps = con.prepareStatement("select NAME||'-'||HUMA_ID from FRS_USER order by HUMA_ID");
				// select AREA_NAME||'-'||AREA_ID from AREA_MSTR
				else if (parameter.equals("areahead"))// for BSFLUNIT_UCODE list
					ps = con.prepareStatement("select NAME||'-'||HUMA_ID from FRS_USER where USERROLE='areahead' order by HUMA_ID");
				else if (parameter.equals("AREA"))// for BSFLUNIT_UCODE list
					ps = con.prepareStatement("select AREA_NAME||'-'||AREA_ID from AREA_MSTR");
				else if (parameter.equals("empname-username"))// for username
																// list
				{ // //System.out.println("inside the username block");
					// System.out.println("role="+role);
					// System.out.println("under parameter username");
					if ((role.equals("admin")) || (role.equals("audit"))) {
						// //System.out.println("inside the admin module and parameter is empname-username");
						// ps =
						// con.prepareStatement("select distinct username from frs_user order by username");
						ps = con.prepareStatement("select distinct (select huma_fname||' '||huma_lname from HUMA_MSTR h where h.HUMA_ID=f.huma_id)||'-'||USERNAME from FRS_USER f");
						// System.out.println("end of admin block of empname_username");
					} else if (role.equals("areahead"))
						ps = con.prepareStatement("select huma_fname||' '||huma_lname||'-'||USERNAME from FRS_USER f,HUMA_MSTR h where h.BSFLUNIT_UCODE in (select BSFLUNIT_UCODE from BSFLUNIT_MSTR where AREA_ID in(select AREA_ID from AREA_MSTR where huma_id='"
								+ huma_id + "')) and h.HUMA_ID=F.HUMA_ID");
					else if (role.equals("unithead"))
						ps = con.prepareStatement("select huma_fname||' '||huma_lname||'-'||USERNAME from FRS_USER f,HUMA_MSTR h where h.bsflunit_UCODE=(select bsflunit_UCODE from HUMA_MSTR where  HUMA_ID='"
								+ huma_id + "') and h.HUMA_ID=F.HUMA_ID");
				} else if (parameter.equals("BSFLUNIT_NAME")) {
					if ((role.equals("admin")) || (role.equals("audit")))
						ps = con.prepareStatement("select distinct bsflunit_NAME from BSFLUNIT_MSTR order by bsflunit_NAME");
					else if (role.equals("areahead"))
						ps = con.prepareStatement("select distinct bsflunit_NAME from BSFLUNIT_MSTR  where area_ID=(select area_ID from area_MSTR where  huma_id='"
								+ huma_id + "') order by bsflunit_NAME");
				} else if (parameter.equals("area_name"))// for username list
					// System.out.println("Rajesh Das");
					ps = con.prepareStatement("select distinct area_name from huma_mstr order by area_name");
				else if (parameter.equals("cid"))// for username list
					ps = con.prepareStatement("select distinct COMP_ID from COMP_MSTR order by COMP_ID");
				else if (parameter.equals("busiid"))// for username list
				{
					if ((role.equals("admin")) || (role.equals("audit")))
						ps = con.prepareStatement("select area_NAME||'-'||area_ID from area_MSTR order by area_ID");
					else if (role.equals("areahead"))
						ps = con.prepareStatement("select area_NAME||'-'||area_ID from area_MSTR where huma_id='"
								+ huma_id + "'");

				} else if (parameter.equals("comp_id2"))// for company master
					// query
					ps = con.prepareStatement("select comp_name||'-'||comp_id from comp_mstr order by comp_id desc");

				// uhlog_service block starts
				// here--------------------------------------------
				else if (parameter.equals("VCODE"))// for company master{
				// query
				{
					if (input1 != "") {
						/*
						 * if(role.equals("unithead")) {
						 * System.out.println("The Role is UnitHead"); ps =
						 * con.prepareStatement(
						 * "select VNAME||'-'||VCODE from VILLAGE_MSTR where BSFLUNIT_UCODE in(select bsflunit_ucode from bsflunit_mstr where huma_id='"
						 * +huma_id+"') order by VNAME"); } else
						 */
						ps = con.prepareStatement("select VNAME||'-'||VCODE from VILLAGE_MSTR where BSFLUNIT_UCODE='"
								+ input1.substring(input1.lastIndexOf('-') + 1)
								+ "' order by VNAME");

					}

					else
						ps = con.prepareStatement("select '-- select Unit first --' from dual");
				} // else if (parameter.equals("VCODE"))
				
				else if (parameter.equals("NAME")){
					if (input1 != "") {
					ps = con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_designation  from huma_mstr where BSFLUNIT_UCODE='"+input1.substring(input1.lastIndexOf('-') + 1)+"' order by huma_id desc");
					}
				
					else
					ps = con.prepareStatement("select '-- select Unit first --' from dual");
			
				} 
				

				else if (parameter.equals("lsrcode"))// for company master{
				// query
				{// //System.out.println("inside the vode if codition");
					// System.out.println("input1="+input1);
					if (input1 != "") {
						input1 = input1.substring(input1.lastIndexOf('-') + 1);
						if ((role.equals("admin")) || (role.equals("audit")))// ||
																				// role.equals("areahead")
																				// ||
																				// role.equals("unithead"))
							//ps = con.prepareStatement("select HUMA_FNAME||' '||HUMA_LNAME||'-'||huma_id as huma_id  from huma_mstr WHERE huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and bsflunit_ucode='"
								//	+ input1 + "' order by huma_id");
							ps = con.prepareStatement("select HUMA_FNAME||' '||HUMA_LNAME||'-'||huma_id as huma_id  from huma_mstr WHERE upper(huma_status) LIKE '%ACTIVE%' and huma_DESIGNATION IN (select grade_id from grade_mstr where (GRADE_name like '%LSR%' or GRADE_name like '%FX%' or GRADE_name like '%FS%')) and bsflunit_ucode='"
									+ input1 + "' order by huma_id");
						else if (role.equals("areahead"))
//							ps = con.prepareStatement("select HUMA_FNAME||' '||HUMA_LNAME||'-'||huma_id as huma_id  from huma_mstr WHERE huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and bsflunit_ucode='"
//									+ input1
//									+ "' and bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where area_id in (select area_id from area_mstr where huma_id='"
//									+ huma_id + "')) order by huma_id");
							ps = con.prepareStatement("select HUMA_FNAME||' '||HUMA_LNAME||'-'||huma_id as huma_id  from huma_mstr WHERE upper(huma_status) LIKE '%ACTIVE%' and huma_DESIGNATION IN (select grade_id from grade_mstr where (GRADE_name like '%LSR%' or GRADE_name like '%FX%' or GRADE_name like '%FS%')) and bsflunit_ucode='"
									+ input1
									+ "' and bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where area_id in (select area_id from area_mstr where huma_id='"
									+ huma_id + "')) order by huma_id");
						else if (role.equals("unithead"))
//							ps = con.prepareStatement("select HUMA_FNAME||' '||HUMA_LNAME||'-'||huma_id as huma_id  from huma_mstr WHERE huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and bsflunit_ucode='"
//									+ input1
//									+ "' and bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where  huma_id='"
//									+ huma_id + "') order by huma_id");
							ps = con.prepareStatement("select HUMA_FNAME||' '||HUMA_LNAME||'-'||huma_id as huma_id  from huma_mstr WHERE upper(huma_status) LIKE '%ACTIVE%' and huma_DESIGNATION IN (select grade_id from grade_mstr where (GRADE_name like '%LSR%' or GRADE_name like '%FX%' or GRADE_name like '%FS%')) and bsflunit_ucode='"
									+ input1
									+ "' and bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where  huma_id='"
									+ huma_id + "') order by huma_id");
						else
//							ps = con.prepareStatement("select HUMA_FNAME||' '||HUMA_LNAME||'-'||huma_id as huma_id  from huma_mstr WHERE huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and bsflunit_ucode='"
//									+ input1
//									+ "' and huma_id ='"
//									+ huma_id
//									+ "'");
							ps = con.prepareStatement("select HUMA_FNAME||' '||HUMA_LNAME||'-'||huma_id as huma_id  from huma_mstr WHERE upper(huma_status) LIKE '%ACTIVE%' and huma_DESIGNATION IN (select grade_id from grade_mstr where (GRADE_name like '%LSR%' or GRADE_name like '%FX%' or GRADE_name like '%FS%')) and bsflunit_ucode='"
									+ input1
									+ "' and huma_id ='"
									+ huma_id
									+ "'");
					}
					else
						ps = con.prepareStatement("select '-- select Unit first --' from dual");
				}

				else if (parameter.equals("ACTIVITY_ID"))// for company master{
				// query
				{// //System.out.println("inside the vode if codition");
					// ses.getAttribute("uno");
					// System.out.println("input1="+input1);
					if (input1 != "")
						ps = con.prepareStatement("select ACTIVITY_NAME||'-'||ACTIVITY_ID from ACTIVITY_MSTR where ACTCAT_ID='"
								+ input1.substring(input1.lastIndexOf('-') + 1)
								+ "' order by ACTIVITY_NAME");
					else
						ps = con.prepareStatement("select '-- select Category first --' from dual");
				}

				else if (parameter.equals("VNAME"))// for company master{
				// query
				{
					// ses.getAttribute("uno");
					// System.out.println("input1="+input1);
					if (input1 != "")
						ps = con.prepareStatement("select VNAME from VILLAGE_MSTR where BSFLUNIT_UCODE='"
								+ input1.substring(input1.lastIndexOf('-') + 1)
								+ "' order by VNAME");
					else
						ps = con.prepareStatement("select '-- select Unit first --' from dual");
				}

				else if (parameter.equals("BSFLUNIT_UCODE"))// for
															// BSFLUNIT_UCODE
															// list
					ps = con.prepareStatement(""
							+ "select distinct BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE from BSFLUNIT_MSTR ");
				// activity
				else if (parameter.equals("BSFLUNIT_UCODE_roles")) {
					// //System.out.println("inside the BSFLUNIT_UCODE_roles");
					if ((role.equals("admin")) || (role.equals("audit")))
						ps = con.prepareStatement("select distinct BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE from BSFLUNIT_MSTR");
					// ps =
					// con.prepareStatement("select distinct BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE from BSFLUNIT_MSTR");
					if (role.equals("areahead"))
						// ps =
						// con.prepareStatement("select bsflunit_name||'-'||bsflunit_ucode from bsflunit_mstr where area_id in(select area_id from area_mstr where huma_id='"+huma_id+"')");
						ps = con.prepareStatement("select distinct BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE from BSFLUNIT_MSTR where area_id in (select distinct area_id from bsflunit_mstr where area_id in(select area_id from area_mstr where huma_id='"
								+ huma_id + "'))");
					if (role.equals("unithead"))
						// ps =
						// con.prepareStatement("select bsflunit_name||'-'||bsflunit_ucode from bsflunit_mstr where huma_id='"+huma_id+"'");
						ps = con.prepareStatement("select distinct BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE from BSFLUNIT_MSTR where huma_id='"
								+ huma_id + "'");
					if (role.equals("user"))
						ps = con.prepareStatement("select distinct BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE from BSFLUNIT_MSTR where bsflunit_ucode=(select bsflunit_ucode from huma_mstr where huma_id='"
								+ huma_id + "')");

				} else if (parameter.equals("BSFLUNITS")) {
					// //System.out.println("inside the BSFLUNIT_UCODE_roles");
					/*
					 * if((role.equals("admin"))||(role.equals("audit")))
					 */ps = con
							.prepareStatement("select distinct BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE from BSFLUNIT_MSTR");
					// ps =
					// con.prepareStatement("select distinct BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE from BSFLUNIT_MSTR");
					/*
					 * if(role.equals("areahead")) ps = con.prepareStatement(
					 * "select bsflunit_name||'-'||bsflunit_ucode from bsflunit_mstr where area_id in(select area_id from area_mstr where huma_id='"
					 * +huma_id+"')"); if(role.equals("unithead")) ps =
					 * con.prepareStatement(
					 * "select bsflunit_name||'-'||bsflunit_ucode from bsflunit_mstr where huma_id='"
					 * +huma_id+"'");
					 */
				}

				else if (parameter.equals("DISTRICT"))// for BSFLUNIT_UCODE list
					ps = con.prepareStatement("select distinct DISTRICT from VILLAGE_MSTR");
				else if (parameter.equals("ACTCAT_ID"))// for company master
					// query
					ps = con.prepareStatement("select ACTCAT_NAME||'-'||ACTCAT_ID from ACTCAT_MSTR order by ACTCAT_ID desc");
				else if (parameter.equals("a_id2"))// for company master
					// query
					ps = con.prepareStatement("select ACTIVITY_NAME||'-'||ACTIVITY_ID from ACTIVITY_MSTR order by ACTIVITY_ID desc");
				// uhlog_service block ends
				// here--------------------------------------------

				// Added by rajesh for getting the list from HR master
				else if (parameter.equals("Huma_huma_id"))// for company master
				{
					// //System.out.println("inside the Getuserlist and parameter is Huma_huma_id");
					if ((role.equals("admin")) || (role.equals("audit")))
						ps = con.prepareStatement("select HUMA_FNAME||' '||HUMA_LNAME||'-'||HUMA_ID from HUMA_MSTR order by HUMA_ID desc");
					if (role.equals("areahead"))
						ps = con.prepareStatement("select HUMA_FNAME||' '||HUMA_LNAME||'-'||HUMA_ID from HUMA_MSTR where BSFLUNIT_UCODE in (select BSFLUNIT_UCODE from BSFLUNIT_MSTR where AREA_ID=(select AREA_ID from AREA_MSTR where huma_id='"
								+ huma_id + "'))");
					if (role.equals("unithead"))
						ps = con.prepareStatement("select HUMA_FNAME||' '||HUMA_LNAME||'-'||HUMA_ID from HUMA_MSTR where BSFLUNIT_UCODE=(select BSFLUNIT_UCODE from HUMA_MSTR where HUMA_ID='"
								+ huma_id + "')");
				}
				// Added By Rajesh to get the huma_id list those are the area
				// incharge
				else if (parameter.equals("Area_huma_id")) {
					ps = con.prepareStatement("select h.HUMA_FNAME||' '||h.HUMA_LNAME||'-'||h.HUMA_ID as name from HUMA_MSTR h where h.huma_id in (select distinct huma_id from area_mstr) order by name desc");
				}
				// Added by Rajesh
				else if (parameter.equals("frs_user_name"))// for company master
				{ // query
					// //System.out.println("inside the frs_user_name of autosuggestion");
					// System.out.println("The role is "+role);
					// //System.out.println("inside the frs_user_name and humaId="+huma_id);
					if ((role.equals("admin")) || (role.equals("audit"))) {
						// ps =
						// con.prepareStatement("select NAME||'-'||USERNAME from FRS_USER order by NAME desc");
						ps = con.prepareStatement("select NAME||'-'||USERNAME from FRS_USER  where userrole in('user','fx','fs') order by NAME desc");
					}
					if (role.equals("areahead")) {
						// ps =
						// con.prepareStatement("select distinct f.NAME||'-'||f.USERNAME from FRS_USER f,HUMA_MSTR h,BSFLUNIT_MSTR b where H.BSFLUNIT_UCODE in(select bsflunit_ucode from BSFLUNIT_MSTR where area_id=(select area_id from area_mstr where huma_id='"+huma_id+"')) and h.HUMA_ID=F.HUMA_ID and h. HUMA_FNAME!='"+Username+"' and f.USERROLE!='admin' and f.USERROLE!='areahead'");
						ps = con.prepareStatement("select distinct f.NAME||'-'||f.USERNAME from FRS_USER f,HUMA_MSTR h,BSFLUNIT_MSTR b where H.BSFLUNIT_UCODE in(select bsflunit_ucode from BSFLUNIT_MSTR where area_id=(select area_id from area_mstr where huma_id='"
								+ huma_id
								+ "')) and h.HUMA_ID=F.HUMA_ID and h. HUMA_FNAME!='"
								+ Username
								+ "' and f.USERROLE in('user','fx','fs')");
					}
					if (role.equals("unithead")) {
						// //System.out.println("inside unithead block");

						ps = con.prepareStatement("select f.NAME||'-'||f.USERNAME from FRS_USER f,HUMA_MSTR h where h.bsflunit_UCODE=(select bsflunit_UCODE from HUMA_MSTR where  HUMA_ID='"
								+ huma_id
								+ "') and h.HUMA_ID=F.HUMA_ID and h. HUMA_FNAME!='"
								+ Username
								+ "' and f.USERROLE in('user','fx','fs')");
					}
					if (role.equals("user") || role.equals("fs")
							|| role.equals("fx")) {
						// //System.out.println("inside admin block");
						ps = con.prepareStatement("select NAME||'-'||USERNAME from FRS_USER where huma_id='"
								+ huma_id + "'");
					}

				}// frs_user_name

				else if (parameter.equals("lsr_fx_fs_userlist"))// for company
																// master
				{ // query
					// //System.out.println("inside the frs_user_name of autosuggestion");
					// System.out.println("The role is "+role);
					if ((role.equals("admin")) || (role.equals("audit"))) {
						// //System.out.println("inside admin block");
						ps = con.prepareStatement("select NAME||'-'||USERNAME from FRS_USER order by NAME desc");
					}
					if (role.equals("areahead")) {
						// //System.out.println("inside areahead block");
						ps = con.prepareStatement("select f.NAME||'-'||f.USERNAME from FRS_USER f,HUMA_MSTR h where h.area_ID=(select area_ID from area_MSTR where  huma_id='"
								+ huma_id
								+ "') and h.HUMA_ID=F.HUMA_ID and h. HUMA_FNAME!='"
								+ Username
								+ "' and f.USERROLE!='admin' and f.USERROLE!='areahead'");
					}
					if (role.equals("unithead")) {
						// //System.out.println("inside unithead block");
						ps = con.prepareStatement("select f.NAME||'-'||f.USERNAME from FRS_USER f,HUMA_MSTR h where h.bsflunit_UCODE=(select bsflunit_UCODE from HUMA_MSTR where  HUMA_ID='"
								+ huma_id
								+ "') and h.HUMA_ID=F.HUMA_ID and h. HUMA_FNAME!='"
								+ Username + "'");
					}
				}
				// if(role.equals("areahead"))

				// if(role.equals("unithead"))

				// }

				// else if (parameter.equals("user-name"))// for Business
				// ps =
				// con.prepareStatement("select NAME||'-'||USERNAME from FRS_USER order by NAME desc");
				else if (parameter.equals("area_id2"))// for Business
					// Line master
					// query
					ps = con.prepareStatement("select area_name||'-'||area_id from area_mstr order by area_id desc");

				else if (parameter.equals("unit_id2"))// for Business
					// Line master
					// query
					ps = con.prepareStatement("select bsflunit_NAME||'-'||bsflunit_UCODE from BSFLUNIT_MSTR order by bsflunit_UCODE desc");

				else if (parameter.equals("area_id"))// for Business
					ps = con.prepareStatement("select AREA_NAME||'-'||AREA_ID from AREA_MSTR order by AREA_ID desc");
				else if (parameter.equals("regoin_name"))// for Business
					ps = con.prepareStatement("SELECT  DISTINCT AREA_MSTR.AREA_NAME||'-'|| AREA_MSTR.AREA_ID FROM AREA_MSTR , FRS_USER WHERE AREA_MSTR.HUMA_ID = (SELECT HUMA_ID FROM FRS_USER FU WHERE FU.USERNAME='"
							+ request.getSession().getAttribute("username")
							+ "')");
				else if (parameter.equals("prod_id2"))// for Product and
					ps = con.prepareStatement("select prod_name||'-'||prod_id from prod_mstr order by prod_id desc");
				else if (parameter.equals("huma_id_recovery")) {
					if ((role.equals("admin")) || (role.equals("audit")))// ||
																			// role.equals("areahead")
																			// ||
																			// role.equals("unithead"))
						ps = con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE upper(huma_status) LIKE '%ACTIVE%' and huma_DESIGNATION IN (select grade_id from grade_mstr where (GRADE_name like '%LSR%' or GRADE_name like '%FX%' or GRADE_name like '%FS%')) order by huma_id");
					else if (role.equals("areahead"))
						ps = con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE upper(huma_status) LIKE '%ACTIVE%' and huma_DESIGNATION IN (select grade_id from grade_mstr where (GRADE_name like '%LSR%' or GRADE_name like '%FX%' or GRADE_name like '%FS%')) and bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where area_id in (select area_id from area_mstr where huma_id='"
								+ huma_id + "')) order by huma_id");
					else if (role.equals("unithead"))
						ps = con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE upper(huma_status) LIKE '%ACTIVE%' and huma_DESIGNATION IN (select grade_id from grade_mstr where (GRADE_name like '%LSR%' or GRADE_name like '%FX%' or GRADE_name like '%FS%')) and bsflunit_ucode in (select bsflunit_ucode from bsflunit_mstr where  huma_id='"
								+ huma_id + "') order by huma_id");
					else
						ps = con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr WHERE upper(huma_status) LIKE '%ACTIVE%' and huma_DESIGNATION IN (select grade_id from grade_mstr where (GRADE_name like '%LSR%' or GRADE_name like '%FX%' or GRADE_name like '%FS%')) and huma_id ='"
								+ huma_id + "'");
					// con.close();
				} else if (parameter.equals("client_id2"))// for client
					// master query
					ps = con.prepareStatement("select client_name||'-'||client_id from client_mstr order by client_id desc");
				else if (parameter.equals("head_name"))// for Head User
					// master query
					ps = con.prepareStatement("select head_name||'-'||head_id from head_mstr order by head_id desc");
				// Main menu
				else if (parameter.equals("client_id2Visit"))// for
					// Visit
					// or
					// Follow
					// up
					// master
					// query
					ps = con.prepareStatement("select v.client_id||' '||c.client_name from visit_mstr v,client_mstr c where c.client_id=v.client_id order by v.client_id desc");// "select distinct client_id from visit_mstr order by client_id");
				else if (parameter.equals("client_id2EOI"))// for
					// EOIProposal
					// master
					// query
					ps = con.prepareStatement("select distinct client_id from (select e.client_id||' '||c.client_name as client_id from EOIprop_mstr e,client_mstr c where c.client_id=e.client_id and EOIprop_status is null order by e.client_id desc) order by client_id desc");// "select distinct client_id from EOIprop_mstr order by client_id");
				else if (parameter.equals("client_id2Prop"))// for
					// Proposal
					// master
					// query
					ps = con.prepareStatement("select distinct client_id from (select e.client_id||' '||c.client_name as client_id from prop_mstr p,EOIprop_mstr e,client_mstr c where p.EOIprop_idd=e.EOIprop_idd and c.client_id=e.client_id order by e.client_id desc) order by client_id desc");// "select distinct client_id from EOIprop_mstr order by client_id");
				else if (parameter.equals("prop_id2"))// for Proposal
				// master query
				// with prop_id
				{
					/*
					 * if(!((String)session.getAttribute("userType")).equals
					 * ("SUPER"))//querying person is normal emp ps =
					 * con.prepareStatement(
					 * "select p.prop_id||' '||c.client_name from prop_mstr p,client_mstr c where c.client_id=p.prop_party2 and prop_by in ('"
					 * +session.getAttribute("user")+
					 * "') order by substr(p.prop_id,14,7)"); else
					 */// the querying person is SUPER
					ps = con.prepareStatement("select p.prop_id||' '||client_name from prop_mstr p,EOIprop_mstr e,client_mstr c where c.client_id=e.client_id and e.EOIprop_idd=p.EOIprop_idd order by p.prop_idd desc");
				}// proposal form query
				else if (parameter.equals("EOIprop_id2"))// for
				// EOIproposal
				// master
				// new
				{
					ps = con.prepareStatement("select p.EOIprop_id from EOIprop_mstr p,client_mstr c where c.client_id=p.client_id order by p.EOIprop_id desc");
				}// EOIproposal form new
				else if (parameter.equals("EOIprop_id2Working"))// for
				// EOIproposal
				// master
				// query
				{
					ps = con.prepareStatement("select p.EOIprop_id from EOIprop_mstr p,client_mstr c where c.client_id=p.client_id and EOIprop_status is null order by p.EOIprop_id desc");
				}// EOIproposal form query
				else if (parameter.equals("EOIprop_id3"))// for proposal
				// master
				// query
				// with
				// EOI/Proposal
				// code
				{
					ps = con.prepareStatement("select p.EOIprop_id from prop_mstr p2,EOIprop_mstr p,client_mstr c where c.client_id=p.client_id and p.EOIprop_idd=p2.EOIprop_idd order by p.EOIprop_id desc");
				}// proposal form query
				else if (parameter.equals("EOIprop_idWon"))// for
				// contract
				// master
				// new with
				// EOIProposal
				// code
				{
					ps = con.prepareStatement("select e.EOIprop_id from prop_mstr p,EOIprop_mstr e,client_mstr c where c.client_id=e.client_id and e.EOIprop_idd=p.EOIprop_idd and p.prop_status='W' and e.EOIprop_respersn=nvl('"
							+ curhuma_id
							+ "',e.EOIprop_respersn) and p.prop_idd not in (select prop_idd from cont_mstr) order by e.EOIprop_id desc");
				}// contract master new with EOIProposal code
				else if (parameter.equals("prop_idWon"))// for contract
				// master new
				// with Proposal
				// id
				{
					ps = con.prepareStatement("select p.prop_id||' '||c.client_name from prop_mstr p,EOIprop_mstr e,client_mstr c where c.client_id=e.client_id and e.EOIprop_idd=p.EOIprop_idd and p.prop_status='W' and e.EOIprop_respersn=nvl('"
							+ curhuma_id
							+ "',e.EOIprop_respersn) and p.prop_idd not in (select prop_idd from cont_mstr) order by p.prop_idd desc");
				}// contract master new with Proposal code
				else if (parameter.equals("EOIprop_idCont"))// for
				// contract
				// master
				// query
				// with
				// EOIProposal
				// code
				{
					ps = con.prepareStatement("select e.EOIprop_id from cont_mstr c2,prop_mstr p,EOIprop_mstr e,client_mstr c where c.client_id=e.client_id and e.EOIprop_idd=p.EOIprop_idd and c2.prop_idd=p.prop_idd and e.EOIprop_respersn=nvl('"
							+ curhuma_id
							+ "',e.EOIprop_respersn) order by e.EOIprop_id desc");
				}// contract master query with EOIProposal id
				else if (parameter.equals("prop_idCont"))// for contract
				// master
				// query
				// with
				// Proposal
				// id
				{
					ps = con.prepareStatement("select p.prop_id||' '||c.client_name from cont_mstr c2,prop_mstr p,EOIprop_mstr e,client_mstr c where c.client_id=e.client_id and e.EOIprop_idd=p.EOIprop_idd and c2.prop_idd=p.prop_idd and e.EOIprop_respersn=nvl('"
							+ curhuma_id
							+ "',e.EOIprop_respersn) order by p.prop_idd desc");
				}// contract master query with Proposal id
				else if (parameter.equals("client_idCont"))// for
				// contract
				// master
				// query
				// with
				// Client id
				{
					ps = con.prepareStatement("select distinct client_id from (select e.client_id||' '||c.client_name as client_id from cont_mstr c2, prop_mstr p,EOIprop_mstr e,client_mstr c where p.EOIprop_idd=e.EOIprop_idd and c.client_id=e.client_id and c2.prop_idd=p.prop_idd and e.EOIprop_respersn=nvl('"
							+ curhuma_id
							+ "',e.EOIprop_respersn) order by e.client_id desc)order by client_id desc");
				}// contract master query with Client id
				else if (parameter.equals("cont_idCont"))// for contract
				// master
				// query
				{
					ps = con.prepareStatement("select c2.cont_id||' '||client_name from cont_mstr c2,prop_mstr p,EOIprop_mstr e,client_mstr c where c.client_id=e.client_id and p.EOIprop_idd=e.EOIprop_idd and c2.prop_idd=p.prop_idd and e.EOIprop_respersn=nvl('"
							+ curhuma_id
							+ "',e.EOIprop_respersn) order by c2.cont_idd desc");
				}
				
				
				
				// contract form query
				else if (parameter.equals("cont_idInvo"))// for invoice
				// master
				// query
				{
					ps = con.prepareStatement("select distinct cont_id from (select c2.cont_idd as cont_idd,c2.cont_id||' '||client_name as cont_id from invo_mstr i,cont_mstr c2,prop_mstr p,EOIprop_mstr e,client_mstr c where i.cont_idd=c2.cont_idd and c.client_id=e.client_id and p.EOIprop_idd=e.EOIprop_idd and c2.prop_idd=p.prop_idd and e.EOIprop_respersn=nvl('"
							+ curhuma_id
							+ "',e.EOIprop_respersn) order by c2.cont_idd desc) order by substr(cont_id,14,7) desc");
				}// invoice form query
				else if (parameter.equals("invo_idInvo"))// for invoice
				// master
				// query
				{
					ps = con.prepareStatement("select i.invo_id||' '||client_name from invo_mstr i,cont_mstr c2,prop_mstr p,EOIprop_mstr e,client_mstr c where i.cont_idd=c2.cont_idd and c.client_id=e.client_id and p.EOIprop_idd=e.EOIprop_idd and c2.prop_idd=p.prop_idd and e.EOIprop_respersn=nvl('"
							+ curhuma_id
							+ "',e.EOIprop_respersn) order by substr(i.invo_id,16,10) desc");// order
																								// by
																								// substr(i.invo_id,16,10)
				}// invoice form query
				else if (parameter.equals("client_idInvo"))// for
				// invoice
				// master
				// query
				// with
				// Client id
				{
					ps = con.prepareStatement("select distinct client_id from (select e.client_id||' '||c.client_name as client_id from invo_mstr i, cont_mstr c2, prop_mstr p,EOIprop_mstr e,client_mstr c where i.cont_idd=c2.cont_idd and p.EOIprop_idd=e.EOIprop_idd and c.client_id=e.client_id and c2.prop_idd=p.prop_idd and e.EOIprop_respersn=nvl('"
							+ curhuma_id
							+ "',e.EOIprop_respersn) order by e.client_id desc) order by client_id desc");
				}// invoice master query with Client id
				else if (parameter.equals("client_idPaym"))// for
				// payment
				// master
				// query
				// with
				// Client id
				{
					ps = con.prepareStatement("select distinct client_id from (select e.client_id||' '||c.client_name as client_id from paym_mstr p2, cont_mstr c2, prop_mstr p,EOIprop_mstr e,client_mstr c where p2.cont_idd=c2.cont_idd and p.EOIprop_idd=e.EOIprop_idd and c.client_id=e.client_id and c2.prop_idd=p.prop_idd and e.EOIprop_respersn=nvl('"
							+ curhuma_id
							+ "',e.EOIprop_respersn) order by e.client_id desc) order by client_id desc");
				}// payment master query with Client id
				else if (parameter.equals("cont_idPaym"))// for payment
				// master
				// query
				// with
				// contract
				// id
				{
					ps = con.prepareStatement("select distinct cont_id from (select c2.cont_id||' '||c.client_name as cont_id from paym_mstr p2, cont_mstr c2, prop_mstr p,EOIprop_mstr e,client_mstr c where p2.cont_idd=c2.cont_idd and p.EOIprop_idd=e.EOIprop_idd and c.client_id=e.client_id and c2.prop_idd=p.prop_idd and e.EOIprop_respersn=nvl('"
							+ curhuma_id
							+ "',e.EOIprop_respersn) order by cont_id desc) order by substr(cont_id,14,7) desc");
				}// payment master query with contract id
				else if (parameter.equals("taxterm_id"))// for taxterm
					// master query
					ps = con.prepareStatement("select taxterm_id||' '||taxterm_desc as taxterm_id from taxterm_mstr order by taxterm_id desc");
				else if (parameter.equals("bank_id2"))// forBank a/c
					// master query
					ps = con.prepareStatement("select bank_id||' '||bank_name from bank_mstr order by bank_id desc");
				else if (parameter.equals("grade_id"))// for grade
					// master query

					ps = con.prepareStatement("select grade_name||'-'||grade_id from grade_mstr order by grade_id desc");

				else if (parameter.equals("grade_name"))// for grade
					// master query

					ps = con.prepareStatement("select grade_name||'-'||grade_id from grade_mstr order by grade_id desc");

				else if (parameter.equals("client_name"))// for Daily
					// Log
					// Report
					// master
					// query
					ps = con.prepareStatement("select client_name||'-'||client_id from client_mstr order by client_id desc"); // "select client_id||' '||client_name from client_mstr order by client_name");
				else if (parameter.equals("huma_idLog")) {
					ps = con.prepareStatement("select huma_fname||' '||huma_lname||'-'||huma_id from huma_mstr where huma_id=nvl('"
							+ curhuma_id + "',huma_id) order by huma_id desc");
				}// for Log.jsp master
				else if (parameter.equals("city_id"))// for Coty master
					// query
					ps = con.prepareStatement("select city_name||'-'||city_id from city_mstr order by city_id desc");
				else if (parameter.equals("city_name"))// for Daily Log
					// Report master
					// query
					ps = con.prepareStatement("select city_name||'-'||city_id from city_mstr order by city_id desc");
				else if (parameter.equals("bsflunit_ucode"))// for BSFL Unit
					// master query
					ps = con.prepareStatement("select bsflunit_name||'-'||bsflunit_ucode from BSFLUNIT_MSTR where bsflunit_name is not null order by bsflunit_ucode desc");
				else if (parameter.equals("st_id"))// for st_id
					ps = con.prepareStatement("select s_name||'-'||s_id from stationary_mstr order by s_id desc");
				else if (parameter.equals("country_id"))// for country
					ps = con.prepareStatement("select country_name||'-'||country_id from country_mstr order by country_id desc");

				else if (parameter.equals("HRStatus_id"))// for HR Status Master
					ps = con.prepareStatement("select hrstatus_name from hrstatus_mstr order by hrstatus_id");
				else if (parameter.equals("country_currency"))// for
					// EOIProposal
					// master
					// currency
					// LOV
					ps = con.prepareStatement("select (select country_id from country_mstr where country_currency=c1.country_currency and rownum=1 group by country_id)||' '||country_currency as country_currency from country_mstr c1 group by country_currency order by country_currency");
				else if (parameter.equals("state_id"))// for state
					// master query
					ps = con.prepareStatement("select state_name||'-'||state_id from state_mstr order by state_id desc");
				else if (parameter.equals("holiday_title"))// for
					// holiday
					// master
					// query
					ps = con.prepareStatement("select holiday_id||' '||holiday_title from holiday_mstr order by holiday_id desc");
				else if (parameter.equals("weekoff_mstr"))// for
					// weklyoff
					// master
					// query
					ps = con.prepareStatement("select weekoff_id||' ('||to_char(weekoff_fdate,'ddMONyyyy')||'->'||to_char(weekoff_tdate,'ddMONyyyy')||')' from weekoff_mstr order by weekoff_id desc");
				// =======UPBSN module related code starts
				// here===============================================================================================
				// --------district_mstr related code starts
				// here-----------------------------------------------------------------------------
				else if (parameter.equals("district_collector")
						|| parameter.equals("district_dbm")
						|| parameter.equals("district_dpm")
						|| parameter.equals("district_dc")
						|| parameter.equals("district_dtc")
						|| parameter.equals("district_pf")
						|| parameter.equals("subunit_tehsildar")
						|| parameter.equals("subunit_dm")
						|| parameter.equals("block_incharge")
						|| parameter.equals("block_bdo")
						|| parameter.equals("grampanchayat_incharge")
						|| parameter.equals("village_incharge")
						|| parameter.equals("assocomp_incharge")
						|| parameter.equals("activitysl_QuantityIn")
						|| parameter.equals("activitysl_PriceIn")
						|| parameter.equals("pg_hamlet")
						|| parameter.equals("pg_bankAccno")
						|| parameter.equals("pg_bankName")
						|| parameter.equals("pg_bankAccno")
						|| parameter.equals("estbl_contactPerson")
						|| parameter.equals("schedule_deliverables")
						|| parameter.equals("cont_district")
						|| parameter.equals("cont_block")
						|| parameter.equals("cont_opsArea"))// for
															// district_mstr,state_mstr,block_mstr,grampanchayat_mstr,village_mstr,assocomp_mstr,activitysl_mstr
															// new
				{
					String tab = parameter.split("_")[0];
					ps = con.prepareStatement("select distinct param from (select "
							+ parameter
							+ " as param from "
							+ tab
							+ "_mstr where "
							+ parameter
							+ " is not null) order by param");// "select distinct "+parameter+" from district_mstr order by client_id");
					// ps.setString(1,parameter.split("_")[0]);
				} else if (parameter.equals("state_id2district_mstr"))// for
					// district_mstr
					// master
					// query
					ps = con.prepareStatement("select distinct state_id from (select d.state_id||' '||s.state_name as state_id from district_mstr d,state_mstr s where s.state_id=d.state_id order by d.state_id desc) order by state_id desc");// "select distinct state_id from district_mstr order by state_id");
				else if (parameter.equals("district_id"))// for
					// district_mstr
					// master
					// query
					ps = con.prepareStatement("select d.district_name||'-'||d.district_id as district_id from district_mstr d order by d.district_id desc");// "select district_id from district_mstr order by district_id");
				else if (parameter.equals("client_id2district_mstr"))// for
					// district_mstr
					// master
					// query
					ps = con.prepareStatement("select distinct client_id from (select d.client_id||' '||c.client_name as client_id from district_mstr d,client_mstr c where c.client_id=d.client_id order by d.client_id desc) order by client_id desc");// "select distinct client_id from district_mstr order by client_id");
				// --------district_mstr related code ends
				// here-------------------------------------------------------------------------------
				// --------subBSFLUNIT_MSTR related code starts
				// here-----------------------------------------------------------------------------
				else if (parameter.equals("district_id2subBSFLUNIT_MSTR"))// for
					// subBSFLUNIT_MSTR
					// master
					// query
					ps = con.prepareStatement("select distinct district_id from (select s.district_id||' '||d.district_name as district_id from subBSFLUNIT_MSTR s,district_mstr d where d.district_id=s.district_id) order by district_id desc");
				else if (parameter.equals("subunit_id"))// for
					// subBSFLUNIT_MSTR
					// master query
					ps = con.prepareStatement("select subunit_id||' '||subunit_name as subunit_id from subBSFLUNIT_MSTR order by subunit_id desc");
				// --------subBSFLUNIT_MSTR related code ends
				// here-------------------------------------------------------------------------------
				// --------block_mstr related code starts
				// here-----------------------------------------------------------------------------
				else if (parameter.equals("subunit_id2block_mstr"))// for
					// block_mstr
					// master
					// query
					ps = con.prepareStatement("select distinct subunit_id from (select s.subunit_id||' '||d.subunit_name as subunit_id from block_mstr s,subBSFLUNIT_MSTR d where d.subunit_id=s.subunit_id) order by subunit_id desc");
				else if (parameter.equals("block_id"))// for block_mstr
					// master query
					ps = con.prepareStatement("select block_id||' '||block_name as block_id from block_mstr order by block_id desc");
				// --------block_mstr related code ends
				// here-------------------------------------------------------------------------------
				// --------grampanchayat_mstr related code starts
				// here-----------------------------------------------------------------------------
				else if (parameter.equals("block_id2grampanchayat_mstr"))// for
																			// grampanchayat_mstr
																			// master
																			// query
					ps = con.prepareStatement("select distinct block_id from (select s.block_id||' '||d.block_name as block_id from grampanchayat_mstr s,block_mstr d where d.block_id=s.block_id) order by block_id desc");
				else if (parameter.equals("grampanchayat_id"))// for
					// grampanchayat_mstr
					// master
					// query
					ps = con.prepareStatement("select grampanchayat_id||' '||grampanchayat_name as grampanchayat_id from grampanchayat_mstr order by grampanchayat_id desc");
				// --------grampanchayat_mstr related code ends
				// here-------------------------------------------------------------------------------
				// --------village_mstr related code starts
				// here-----------------------------------------------------------------------------
				else if (parameter.equals("grampanchayat_id2village_mstr"))// for
																			// village_mstr
																			// master
																			// query
					ps = con.prepareStatement("select distinct grampanchayat_id from (select s.grampanchayat_id||' '||d.grampanchayat_name as grampanchayat_id from village_mstr s,grampanchayat_mstr d where d.grampanchayat_id=s.grampanchayat_id) order by grampanchayat_id desc");
				else if (parameter.equals("village_id"))// for
					// village_mstr
					// master query
					ps = con.prepareStatement("select village_name||'-'||village_id as village_id from village_mstr order by village_id desc");
				// --------village_mstr related code ends
				// here-------------------------------------------------------------------------------
				// --------assocomp_mstr related code starts
				// here-----------------------------------------------------------------------------
				else if (parameter.equals("client_id_district_mstr"))// for
					// assocomp_mstr
					// master
					// new
					ps = con.prepareStatement("select distinct client_id from (select s.client_id||' '||d.client_name as client_id from district_mstr s,client_mstr d where d.client_id=s.client_id) order by client_id desc");
				else if (parameter.equals("client_id2assocomp_mstr"))// for
					// assocomp_mstr
					// master
					// query
					ps = con.prepareStatement("select distinct client_id from (select s.client_id||' '||d.client_name as client_id from assocomp_mstr s,client_mstr d where d.client_id=s.client_id) order by client_id desc");
				else if (parameter.equals("area_id2assocomp_mstr"))// for
					// assocomp_mstr
					// master
					// query
					ps = con.prepareStatement("select distinct area_id from (select s.area_id||' '||d.area_name as area_id from assocomp_mstr s,area_mstr d where d.area_id=s.area_id) order by area_id desc");
				else if (parameter.equals("assocomp_id"))// for
					// assocomp_mstr
					// master
					// query
					ps = con.prepareStatement("select assocomp_id||' '||assocomp_name as assocomp_id from assocomp_mstr order by assocomp_id desc");
				// --------assocomp_mstr related code ends
				// here-------------------------------------------------------------------------------
				// --------activityfl_mstr related code starts
				// here-----------------------------------------------------------------------------
				else if (parameter.equals("activityfl_abbr"))// for
					// activityfl_mstr
					// master
					// query
					ps = con.prepareStatement("select activityfl_abbr from activityfl_mstr order by activityfl_abbr");
				else if (parameter.equals("activityfl_name"))// for
					// activityfl_mstr
					// master
					// query
					ps = con.prepareStatement("select activityfl_name as activityfl_id from activityfl_mstr order by activityfl_id");
				// --------activityfl_mstr related code ends
				// here-------------------------------------------------------------------------------
				// --------activitysl_mstr related code starts
				// here-----------------------------------------------------------------------------
				else if (parameter.equals("area_id2activitysl_mstr"))// for
					// activitysl_mstr
					// master
					// query
					ps = con.prepareStatement("select distinct area_id from (select s.area_id||' '||d.area_name as area_id from activitysl_mstr s,area_mstr d where d.area_id=s.area_id) order by area_id desc");
				/*
				 * else if (parameter.equals("ACTIVITY_ID"))// for //
				 * activitysl_mstr // master // query ps = con.prepareStatement(
				 * "select ACTIVITY_ID||' '||activitysl_name as ACTIVITY_ID from activitysl_mstr order by ACTIVITY_ID desc"
				 * );
				 */// --------activitysl_mstr related code ends
					// here-------------------------------------------------------------------------------
					// --------uplan_mstr related code starts
					// here-----------------------------------------------------------------------------
				else if (parameter.equals("vbs_place"))// for place list
					// in complete
					// UPBSN
					ps = con.prepareStatement("select village_id||' '||village_name as vbs_place from village_mstr union (select block_id||' '||block_name as vbs_place from block_mstr union select subunit_id||' '||subunit_name vbs_place from subBSFLUNIT_MSTR) order by vbs_place desc");
				else if (parameter.equals("uplan_desc"))// for
					// uplan_mstr
					// master query
					ps = con.prepareStatement("select distinct uplan_desc as uplan_desc from uplan_mstr");
				// --------uplan_mstr related code ends
				// here-------------------------------------------------------------------------------
				// --------svre_mstr related code starts
				// here-----------------------------------------------------------------------------
				else if (parameter.equals("huma_id2svre_mstr"))// for
					// svre_mstr
					// master
					// query
					ps = con.prepareStatement("select distinct huma_id from (select s.huma_id||' '||d.huma_fname||' '||d.huma_lname as huma_id from svre_mstr s,huma_mstr d where d.huma_id=s.huma_id) order by huma_id desc");
				else if (parameter.equals("fpo_id2svre_mstr"))// for
					// svre_mstr
					// master
					// query
					ps = con.prepareStatement("select distinct fpo_id from (select s.fpo_id||' '||d.fpo_name as fpo_id from svre_mstr s,fpo_mstr d where d.fpo_id=s.fpo_id) order by fpo_id desc");
				else if (parameter.equals("svre_id"))// for svre_mstr
					// master query
					ps = con.prepareStatement("select svre_id||' '||svre_name as svre_id from svre_mstr order by svre_id desc");
				// --------svre_mstr related code ends
				// here-------------------------------------------------------------------------------
				// --------fpo_mstr related code starts
				// here-----------------------------------------------------------------------------
				/*
				 * else if(parameter.equals("vbs_place2fpo_mstr"))//for fpo_mstr
				 * master query ps = con.prepareStatement(
				 * "select distinct vbs_place from (select s.vbs_place||' '||d.grampanchayat_name as vbs_place from fpo_mstr s,grampanchayat_mstr d where d.vbs_place=s.vbs_place) order by vbs_place desc"
				 * );
				 */else if (parameter.equals("fpo_id"))// for fpo_mstr
					// master query
					ps = con.prepareStatement("select fpo_id||' '||fpo_name as fpo_id from fpo_mstr order by fpo_id desc");
				// --------fpo_mstr related code ends
				// here-------------------------------------------------------------------------------
				// --------pg_mstr related code starts
				// here-----------------------------------------------------------------------------
				/*
				 * else if(parameter.equals("huma_id2pg_mstr"))//for pg_mstr
				 * master query ps = con.prepareStatement(
				 * "select distinct huma_id from (select s.huma_id||' '||d.huma_fname||' '||d.huma_lname as huma_id from pg_mstr s,huma_mstr d where d.huma_id=s.huma_id) order by huma_id desc"
				 * );
				 */// Only SUPER can see other Emp details and normal
					// user any have see himself
				else if (parameter.equals("fpo_id2pg_mstr"))// for
					// pg_mstr
					// master
					// query
					ps = con.prepareStatement("select distinct fpo_id from (select s.fpo_id||' '||d.fpo_name as fpo_id from pg_mstr s,fpo_mstr d where d.fpo_id=s.fpo_id) order by fpo_id desc");
				else if (parameter.equals("pg_id"))// for pg_mstr master
					// query
					ps = con.prepareStatement("select pg_id||' '||pg_name as pg_id from pg_mstr order by pg_id desc");
				// --------pg_mstr related code ends
				// here-------------------------------------------------------------------------------
				// --------estbl_mstr related code starts
				// here-----------------------------------------------------------------------------
				else if (parameter.equals("fpo_id2estbl_mstr"))// for
					// estbl_mstr
					// master
					// query
					ps = con.prepareStatement("select distinct fpo_id from (select s.fpo_id||' '||d.fpo_name as fpo_id from estbl_mstr s,fpo_mstr d where d.fpo_id=s.fpo_id) order by fpo_id desc");

				else if (parameter.equals("HR_Status"))
					ps = con.prepareStatement("select distinct huma_status from huma_mstr");
				else if (parameter.equals("estbl_id"))// for estbl_mstr
					// master query

					ps = con.prepareStatement("select estbl_id||' '||estbl_name as estbl_id from estbl_mstr order by estbl_id desc");
				/*
				 * if(parameter.equals("BSFLUNIT_NAMEAreawise"))//for username
				 * list System.out.println("Rajesh Kumar Das");
				 * System.out.println
				 * ("Value is "+request.getParameterValues("field")); String
				 * v=(String)parameter.valueOf("field"); List list=new
				 * ArrayList(); System.out.println("value=  "+v);
				 */
				// System.out.println("Region name "+parameter("areaselected"));
				// ps =
				// con.prepareStatement("select distinct area_name from huma_mstr order by area_name");

				// //System.out.println("inside try and before connection obj, parameter="+parameter);
				ResultSet rs = ps.executeQuery();// System.out.println("before while loop");
				while (rs.next())
					// System.out.println("First string="+rs.getString(1));
					data = data + rs.getString(1) + "::::::";
				// System.out.println("data=" + data.length());
				response.getWriter().println(data);
				rs.close();
				con.close();
				ps.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.warn("", e);
				// e.printStackTrace();

				// } catch (ClassNotFoundException e) {
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.warn("", e);
				// e.printStackTrace();
			} finally {
				try {
					if (con != null)
						con.close();
					if (ps != null)
						ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}// finally
		}
	}
}