package frs_cls;

import java.io.IOException;
import java.io.PrintWriter;
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
public class Issue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection con;
	PreparedStatement ps = null;
	ResultSet rs = null;
	ResultSet rs6=null;
	private JdbcConnect jc = new JdbcConnect();
	
	private IssueBean ib;
	private Logger log = Logger.getLogger("LOGFILE");
	String unit_id = null, unit_name = null, st_id = null,iss_stock=null,
			st_name = null,closing_stock=null,available_stock=null,req_stock=null,date_of_inden=null,indent_by=null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Issue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boolean checkunit_id(String unit_id) throws ClassNotFoundException {
		// String
		// sql="select distinct huma_id from huma_mstr where huma_id='"+huma_id+"'";//select
		// * from huma_mstr where huma_id='E2317'
		// String
		// sql="select distinct USERNAME from FRS_USER where USERNAME='"+huma_id+"'";
		String sql = "select bsflunit_ucode from stockindent where bsflunit_ucode='"
				+ unit_id + "'";
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

	public IssueBean getIssueBean(String unit_id) throws SQLException,
			ClassNotFoundException {
		String sql = "select * from stockindent where bsflunit_ucode='" + unit_id + "'";// select
																				// *
																				// from
																				// huma_mstr
																				// where
																				// huma_id='E2317'
		con = jc.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			ib = new IssueBean();
			// System.out.println(rs.getString(2));
			// System.out.println(rs.getString(3));
			// System.out.println(rs.getString(6));
			// System.out.println(rs.getString(7));

			ib.setUnit_id(rs.getString(1));
			ib.setUnit_name(rs.getString(2));
			ib.setSt_id(rs.getString(3));
			ib.setSt_name(rs.getString(4));
			ib.setLast_issued(rs.getString(5));
			ib.setClosing_stock(rs.getString(6));
			ib.setAvailable_stock(rs.getString(7));
			ib.setIssue_stock(rs.getString(8));
			ib.setDate_of_issue(rs.getString(9));
			ib.setCou_id(rs.getString(10));
			ib.setCou_name(rs.getString(11));
		}
		return ib;
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
			String huma_id2 = (String) request.getSession().getAttribute("huma_id");
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
				String unit_id1 = request.getParameter("id");
				System.out.println("unit_id1"+unit_id1);
				
				//int i = huma_id1.lastIndexOf('-');
				unit_id = unit_id1;
				
				String stt=request.getParameter("id2");
				System.out.println(stt);
				st_id=stt;
				// System.out.println("huma_id="+huma_id);
				try {
					// //System.out.println("inside the try block");
					String qry = "select bsflunit_ucode from stockindent where bsflunit_ucode='"
							+ unit_id + "'";
					// System.out.println("The query is "+qry);
					

					ps = con.prepareStatement(qry);
					rs = ps.executeQuery();

					if (!rs.next()) {
						getServletContext().getRequestDispatcher(
								"/unit_id not exist.jsp").include(request,
								response);
						// System.out.println("rs is empty");
					} else {
						successrd = getServletContext().getRequestDispatcher(
								"/issue3.jsp");
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
					Status = checkunit_id(unit_id);
					// System.out.println("status was "+Status);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println("huma_id is ="+huma_id);
				
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
				
				
				if (checkunit_id(unit_id)){
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
					//String ss=request.getParameter("st_id");
					String sql = "select bsflunit_name,s_id,s_name,new_stock,to_char(date_of_indent,'dd-mm-yyyy'),in_by from stockindent where bsflunit_ucode='"
							+ unit_id + "' and S_ID='"+st_id+"'";
					// Connection con2=jc.getConnection();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					if (rs != null) {
						while (rs.next()) {
							unit_name = rs.getString(1);
							st_id=rs.getString(2);
							System.out.println(st_id);
							st_name = rs.getString(3);
							req_stock=rs.getString(4);
							date_of_inden=rs.getString(5);
							indent_by=rs.getString(6);
						}
						

					}
					int iss_stock1=0;
					String is2="select sum(iss_stock) from issue_indent where bsflunit_ucode='"+unit_id+"' and s_id='"+st_id+"'";
					Statement ss2=con.createStatement();
					ResultSet rs7=ss2.executeQuery(is2);
					if(rs7!=null){
						while(rs7.next()){
							try{
							iss_stock=rs7.getString(1);
							iss_stock1=Integer.parseInt(iss_stock);
							}catch(NumberFormatException ex){
								ex.printStackTrace();
							}
						}
					}
					int closing_stock1=0;
					String is3="select sum(clo_stock) from stockindent where bsflunit_ucode='"+unit_id+"' and s_id='"+st_id+"'";
					Statement ss3=con.createStatement();
					ResultSet rs4=ss3.executeQuery(is3);
					if(rs4!=null){
						while(rs4.next()){
							try{
							closing_stock=rs4.getString(1);
							closing_stock1=Integer.parseInt(closing_stock);
							}catch(NumberFormatException ex){
								ex.printStackTrace();
							}
						}
					}
					
					int available_stock=iss_stock1-closing_stock1;
					System.out.println("available stock is " +available_stock);
					request.setAttribute("unit_id", unit_id);
					// ses.setAttribute("huma_id",huma_id);
					request.setAttribute("unit_name", unit_name);
					request.setAttribute("st_id",st_id);
					request.setAttribute("st_name",st_name);
					request.setAttribute("iss_stock", iss_stock1);
					request.setAttribute("closing_stock", closing_stock1);
					request.setAttribute("available_stock", available_stock);
					request.setAttribute("req_stock",req_stock );
					request.setAttribute("date_of_inden", date_of_inden);
					request.setAttribute("indent_by", indent_by);
//					int x=0;		
//					ps= con.prepareStatement("select iss_stock from issue_indent where bsflunit_ucode='"
//							+ unit_id + "' and s_id='"+st_id+"'");
//					ResultSet rs6 = ps.executeQuery();
//					if (rs6 == null)
//						System.out.println("result set is null in ubm.jsp");
//					if ((rs6.next()) == false)
//						x = x + 1; System.out.println("hey the x="+x);
//						String iss_stock="0";
//						if (x != 0) {
//							ps = con.prepareStatement("insert into issue_indent(bsflunit_ucode,bsflunit_name,s_id,s_name,iss_stock) values(?,?,?,?,?)");
//							ps.setString(1, unit_id);
//							ps.setString(2, unit_name);
//							ps.setString(3, st_id);
//							ps.setString(4, st_name);
//							ps.setString(5, iss_stock);
//							int f=ps.executeUpdate();
//							if(f>0){
//								String is="select sum(iss_stock) from issue_indent where bsflunit_ucode='"+unit_id+"' and s_id='"+st_id+"'";
//								Statement ss3=con.createStatement();
//								ResultSet rs8=ss3.executeQuery(is);
//								if(rs8!=null){
//									while(rs8.next()){
//										iss_stock=rs8.getString(1);
//									}
//								}
//						
//						}
//							
//							else{
//								System.out.println("problem in updating");
//							}
//							
//						}
//					
//					
//						request.setAttribute("last_issued", iss_stock);
//					
//					
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