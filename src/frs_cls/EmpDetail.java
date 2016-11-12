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

import org.apache.log4j.Logger;

/**
 * Servlet implementation class EmpDetail
 */
public class EmpDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection con;
	PreparedStatement ps = null;
	ResultSet rs = null;
	private JdbcConnect jc = new JdbcConnect();
	private lsrbean lsrdetails;
	private lsrbean lb;
	private Logger log = Logger.getLogger("LOGFILE");
	String huma_id = null, huma_fname = null, huma_lname = null,
			unitcode = null, BSFLUNIT_NAME = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boolean checkhuma_id(String huma_id) throws ClassNotFoundException {
		// String
		// sql="select distinct huma_id from huma_mstr where huma_id='"+huma_id+"'";//select
		// * from huma_mstr where huma_id='E2317'
		// String
		// sql="select distinct USERNAME from FRS_USER where USERNAME='"+huma_id+"'";
		String sql = "select distinct huma_fname from huma_mstr where huma_id='"
				+ huma_id + "'";
		con = jc.getConnection();
		Boolean res = false;
		try {
			Statement st = con.createStatement();
			// //log.info("JDBC Connection was created");
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				// if(huma_id.equals(rs.getString(1)))
				// {
				res = true;
				break;
				// }
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn("", e);
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} // finally

		// //System.out.println("inside the checkhuma_id method and res is "+res);
		return res;
	}

	public lsrbean getLsrBean(String huma_id) throws SQLException,
			ClassNotFoundException {
		String sql = "select * from huma_mstr where huma_id='" + huma_id + "'";// select
																				// *
																				// from
																				// huma_mstr
																				// where
																				// huma_id='E2317'
		con = jc.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			lb = new lsrbean();
			// System.out.println(rs.getString(2));
			// System.out.println(rs.getString(3));
			// System.out.println(rs.getString(6));
			// System.out.println(rs.getString(7));

			lb.sethuma_id(rs.getString(1));
			lb.setFname(rs.getString(2));
			lb.setLname(rs.getString(3));
			lb.setDesignation(rs.getString(4));
			lb.setarea_name(rs.getString(5));
			lb.setBSFLUNIT_UCODE(rs.getString(6));
			lb.setBSFLUNIT_NAME(rs.getString(7));
			lb.setMoblieno(rs.getString(8));
			lb.setMobileno2(rs.getString(9));
			lb.setEmail(rs.getString(9));
		}
		return lb;

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// //System.out.println("inside the doGet() of EmpDetail.java");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// //System.out.println("inside the doPost() of EmpDetail.java");
		// System.out.println("Tested by Rajesh");
		try {
			con = jc.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		// Below five lines code is to session checking & login redirect
		if (request.getSession().getAttribute("username") == null) {
			// System.out.println("Username is null");
			// System.out.println("Username= "+request.getSession().getAttribute("username"));
			response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
		} else {  
			// System.out.println("Username is not null");
			// System.out.println("Username= "+request.getSession().getAttribute("username"));
			// //System.out.println("inside the Else block of Empdetail.java");
			String huma_id = null;
			HttpSession ses = request.getSession(false);
			String userrole = (String) ses.getAttribute("userrole");
			String huma_id2 = (String) request.getSession().getAttribute(
					"huma_id");
			// System.out.println("user role "+userrole);
			RequestDispatcher lsrsuccessrd = null;
			RequestDispatcher successrd = null;
			RequestDispatcher huma_id_not_exist = null;
			RequestDispatcher failrd = null;
			Boolean Status = false;
			// RequestDispatcher
			// huma_id_not_exist=getServletContext().getRequestDispatcher("/Huma_id not exist.jsp");
			// below three lines of code to get just ID from LOV, as a parameter
			if (userrole.equals("admin") || userrole.equals("areahead")
					|| userrole.equals("unithead")) {
				// //System.out.println("inside the if block of Empdetail.java");
				String huma_id1 = request.getParameter("huma_id");
				int i = huma_id1.lastIndexOf('-');
				huma_id = huma_id1.substring(i + 1);
				// System.out.println("huma_id="+huma_id);
				try {
					// //System.out.println("inside the try block");
					String qry = "select huma_id from huma_mstr where huma_id='"
							+ huma_id + "'";
					// System.out.println("The query is "+qry);

					ps = con.prepareStatement(qry);
					rs = ps.executeQuery();

					if (!rs.next()) {
						getServletContext().getRequestDispatcher(
								"/Huma_id not exist.jsp").include(request,
								response);
						// System.out.println("rs is empty");
					} else {
						successrd = getServletContext().getRequestDispatcher(
								"/LsrDetailsSubmit.jsp");
						// System.out.println("rs not empty");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {

				huma_id = (String) ses.getAttribute("username");
				// //System.out.println("inside Empdetail and username="+huma_id);
				try {
					Status = checkhuma_id(huma_id);
					// System.out.println("status was "+Status);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println("huma_id is ="+huma_id);
				if (Status) {
					// System.out.println("Forwarding the control to lsrsuccessrd in if block inside the else block");
					lsrsuccessrd = getServletContext().getRequestDispatcher(
							"/LsrSubmit.jsp");
				} else {
					// System.out.println("Forwarding the control to failrd in else block of else block");
					failrd = getServletContext().getRequestDispatcher(
							"/failmpid.jsp");
				}

			}

			try {
				// lsrdetails=getLsrBean(huma_id);
				String sql1;
				String sql2;
				String areano1 = null;
				String areano2 = null;
				String result = "false";
				if (userrole.equals("areahead")) {

					// logged in user belongs to which area
					sql1 = "select area_id from area_mstr where huma_id in('"
							+ huma_id2 + "')";
					// The user to be deleted belongs to which area
					sql2 = "select area_id from bsflunit_mstr where bsflunit_ucode=(select bsflunit_ucode from huma_mstr where huma_id='"
							+ huma_id + "')";
					try {
						/*
						 * Connection con1=jc.getConnection(); Statement
						 * st1=con1.createStatement(); ResultSet
						 * rs1=st1.executeQuery(sql1); if(rs1!=null) {
						 * while(rs1.next()) { areano1=rs1.getString(1);
						 * }//while }//if
						 * System.out.println("areano1= "+areano1);
						 */
						Connection con2 = jc.getConnection();
						Statement st2 = con2.createStatement();
						ResultSet rs2 = st2.executeQuery(sql2);
						if (rs2 != null) {
							System.out.println("rs2 not null");
							while (rs2.next()) {
								areano2 = rs2.getString(1);
							}// while
						}// if
							// Connection con1=jc.getConnection();
						Statement st1 = con.createStatement();
						ResultSet rs1 = st1.executeQuery(sql1);
						if (rs1 != null) {
							while (rs1.next()) {
								areano1 = rs1.getString(1);
								if (areano1.equals(areano2)) {
									result = "true";
									break;
								}// if
								else {
									result = "false";
								}// else
							}// while
						}// if
							// System.out.println("areano1= "+areano1);

						// if(!areano1.equals(areano2))
						if (!result.equals("true")) {
							// out.println("You are not allowed to delete other Region's user");
							// return;
							// System.out.println("To failrd Forwarded");
							failrd.include(request, response);
							return;
						}

					} catch (Exception e) {
						e.getMessage();
					}
				}// if(role.equals("areahead"))

				if (userrole.equals("unithead")) {

					// logged in user belongs to which area
					// sql1="select bsflunit_ucode from huma_mstr where huma_id='"+huma_id2+"'";
					sql1 = "select bsflunit_ucode from bsflunit_mstr where huma_id in('"
							+ huma_id2 + "')";
					// The user to be deleted belongs to which area
					sql2 = "select bsflunit_ucode from huma_mstr where huma_id='"
							+ huma_id + "'";
					try {
						/*
						 * Connection con1=jc.getConnection(); Statement
						 * st1=con1.createStatement(); ResultSet
						 * rs1=st1.executeQuery(sql1); if(rs1!=null) {
						 * while(rs1.next()) { areano1=rs1.getString(1);
						 * }//while }//if
						 */// System.out.println("areano1= "+areano1);

						// Connection con2=jc.getConnection();
						Statement st2 = con.createStatement();
						ResultSet rs2 = st2.executeQuery(sql2);
						if (rs2 != null) {
							// System.out.println("rs2 not null");
							while (rs2.next()) {
								areano2 = rs2.getString(1);
							}// while
						}// if
							// Connection con1=jc.getConnection();
						Statement st1 = con.createStatement();
						ResultSet rs1 = st1.executeQuery(sql1);
						if (rs1 != null) {
							while (rs1.next()) {
								areano1 = rs1.getString(1);
								if (areano1.equals(areano2)) {
									result = "true";
									break;
								}// if
								else {
									result = "false";
								}// else
							}// while
						}// if

						// if(!areano1.equals(areano2))
						if (!result.equals("true")) {
							// out.println("You are not allowed to delete other Region's user");
							// return;
							// System.out.println("To failrd Forwarded");
							failrd.include(request, response);
							return;
						}

					} catch (Exception e) {
						e.getMessage();
					}
				}// if(role.equals("unithead"))

				if (checkhuma_id(huma_id)) {
					/*
					 * //System.out.println("huma_id is "+huma_id); String
					 * sql3="select HUMA_ID from FRS_USER where username='"
					 * +huma_id+"'"; //Connection con=jc.getConnection();
					 * con=jc.getConnection(); Statement
					 * st3=con.createStatement(); ResultSet
					 * rs3=st3.executeQuery(sql3); if(rs3!=null) {
					 * while(rs3.next()) { huma_id=rs3.getString(1);
					 * if(huma_id==null) { huma_id=""; }
					 * //System.out.println("In EmpDetail.java huma_id="
					 * +huma_id); } }
					 */
					con = jc.getConnection();
					String sql = "select HUMA_FNAME,HUMA_LNAME,bsflunit_UCODE from HUMA_MSTR where HUMA_ID='"
							+ huma_id + "'";
					// Connection con2=jc.getConnection();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					if (rs != null) {
						while (rs.next()) {
							huma_fname = rs.getString(1);
							huma_lname = rs.getString(2);
							unitcode = rs.getString(3);
						}
						// System.out.println("fname="+huma_fname+" lname="+huma_lname+" unitcode="+unitcode);

					}
					String sql5 = "select bsflunit_NAME from BSFLUNIT_MSTR where bsflunit_UCODE='"
							+ unitcode + "'";
					// Connection con3=jc.getConnection();
					Statement st2 = con.createStatement();
					ResultSet rs2 = st2.executeQuery(sql5);
					if (rs2 != null) {
						while (rs2.next()) {
							BSFLUNIT_NAME = rs2.getString(1);

							// System.out.println("In EmpDetail.java BSFLUNIT_NAME="+BSFLUNIT_NAME);
						}
					}
					request.setAttribute("huma_id", huma_id);
					// ses.setAttribute("huma_id",huma_id);
					request.setAttribute("fname", huma_fname);
					request.setAttribute("lname", huma_lname);
					request.setAttribute("BSFLUNIT_UCODE", unitcode);
					request.setAttribute("BSFLUNIT_NAME", BSFLUNIT_NAME);
					/*
					 * ses.setAttribute("huma_id",lsrdetails.gethuma_id());
					 * ses.setAttribute("fname", lsrdetails.getFname());
					 * ses.setAttribute("lname",lsrdetails.getLname());
					 * ses.setAttribute("BSFLUNIT_UCODE",
					 * lsrdetails.getBSFLUNIT_UCODE());
					 * ses.setAttribute("BSFLUNIT_NAME"
					 * ,lsrdetails.getBSFLUNIT_NAME());
					 */
					// ses.setAttribute("userrole",userrole);
					// System.out.println("user role was  .........:"+userrole);
					if (userrole.equals("admin") || userrole.equals("unithead")
							|| userrole.equals("areahead")) {
						successrd.include(request, response);
					} else if (userrole.equals("user") || userrole.equals("fs")
							|| userrole.equals("fx")) {
						lsrsuccessrd.include(request, response);
					} else {
						// System.out.println("To failrd Forwarded");
						failrd.include(request, response);
					}
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				log.warn("", e);
				// e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (con != null)
					try {
						con.close();
					}

					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

		} // else

	}
}