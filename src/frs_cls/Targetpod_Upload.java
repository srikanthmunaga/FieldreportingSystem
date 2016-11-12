package frs_cls;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import au.com.bytecode.opencsv.CSVReader;

public class Targetpod_Upload extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	JdbcConnect jc = new JdbcConnect();
	Connection conn = null;
	PreparedStatement ps1 = null, sql_statement1 = null, sql_statement2 = null;
	ResultSet rs1 = null;

	public Targetpod_Upload() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("Msr debug ");
		if (request.getSession().getAttribute("username") == null ) {
			response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
			}
		else
		{
		try {
			HttpSession ses=request.getSession(false);
			RequestDispatcher successrd = getServletContext().getRequestDispatcher("/CSVUploadSuccess.jsp");
			RequestDispatcher huma_id_notExist = getServletContext().getRequestDispatcher("/CSVUploadunitcodeNotExist.jsp");
			RequestDispatcher Wrong_format = getServletContext().getRequestDispatcher("/CSVUploadWrongFormat.jsp");
			int res = 0;
			int ins = 0;
			int update = 0;
			try {
				String jdbc_insert_sql = "INSERT INTO UNIT_POD"
						+ "(BSFLUNIT_UCODE, UNIT_POD_AMT, UNIT_SEQID, "
						+ "UNIT_CBY,UNIT_DATE) values(?,?,UNIT_SEQID.nextval,?,?)";

				String jdbc_update_sql = "update UNIT_POD set UNIT_POD_AMT=?, "
						+ "UNIT_MBY=?,UNIT_MDATE=sysdate where BSFLUNIT_UCODE=? and UNIT_DATE=?";
				conn = jc.getConnection();
				sql_statement1 = conn.prepareStatement(jdbc_insert_sql);
				sql_statement2 = conn.prepareStatement(jdbc_update_sql);
				String inputCSVFile = ses.getAttribute("inputCSVFile").toString();
				CSVReader reader = new CSVReader(new FileReader(inputCSVFile));
				String[] nextLine;
				int lnNum = 0;

				String Username = request.getSession().getAttribute("username")
						.toString();
				HashSet<String> NonExist_EmpList = new HashSet<String>();
				nextLine = reader.readNext();
				ps1 = conn.prepareStatement("select UNIT_POD_AMT from UNIT_POD where BSFLUNIT_UCODE=? and UNIT_DATE=?");
				NonExist_EmpList.clear();
				while ((nextLine = reader.readNext()) != null) {
					lnNum++;
					ps1.setString(1, nextLine[0]);
					ps1.setString(2, nextLine[2]);
					rs1 = ps1.executeQuery();
					if (!rs1.next()) {
						// System.out.println("result set is null in shm.jsp");
						sql_statement1.setString(1, nextLine[0]);
						sql_statement1.setDouble(2,Double.parseDouble(nextLine[1]));// NumberFormatException
						sql_statement1.setString(3, Username);
						sql_statement1.setString(4, nextLine[2]);
						int count=0;
						try{
						count = sql_statement1.executeUpdate();
						} catch (SQLException e1) {
							int errcode = e1.getErrorCode();
							if (errcode == 2291) {//Unit Code not exist in Huma_mstr
								//System.out.println("Unit does not exists");
								//e1.printStackTrace();
								NonExist_EmpList.add(nextLine[0]);
							} else {
								e1.printStackTrace();
							}
						}
						res = res + count;
						ins = ins + 1;
					} else {
						// System.out.println("Duplicate data found");
						sql_statement2.setDouble(1,Double.parseDouble(nextLine[1]));
						sql_statement2.setString(2, Username);
						// sql_statement2.setString(3, dateNow);
						sql_statement2.setString(3, nextLine[0]);
						sql_statement2.setString(4, nextLine[2]);
						int count2 = sql_statement2.executeUpdate();
						res = res + count2;
						update = update + 1;
					}

				}// While
				request.setAttribute("tot_no_of_csv_record", res);
				request.setAttribute("csv_inserted", ins);
				request.setAttribute("csv_updated", update);
				if (NonExist_EmpList.isEmpty())
					successrd.include(request, response);// out.println("OK WAR Room Data Entry updated successfully");
				else { // if(smsDates!=null)
					request.setAttribute("NonExist_EmpList", NonExist_EmpList);
					huma_id_notExist.include(request, response);//successrd.include(request, response);// out.println("OK WAR Room Data Entry updated successfully. Cannot change WAR Room target for "+
				}
			} catch (SQLException e1) {
				int errcode = e1.getErrorCode();
				 System.out.println("The Error code was "+errcode);
				if (errcode == 2291) {//This block already handled
					System.out.println("Entered Unit Code not exist at Final");//smsDates.add(war_date[i]);
					//e1.printStackTrace();
					huma_id_notExist.include(request, response);
				} else {
					System.out.println("Got some other Software(DB) error");
					//e1.printStackTrace();
					Wrong_format.include(request, response);
				}
			} catch (Exception e) {
				e.getMessage();
				// System.out.println("separate");
				e.printStackTrace();
				Wrong_format.include(request, response);
			}
			// conn.commit();
			/* Close connection */
			if (conn != null)
				conn.close();
			if (sql_statement1 != null)
				sql_statement1.close();
			if (sql_statement2 != null)
				sql_statement2.close();
			if (rs1 != null)
				rs1.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		}
}