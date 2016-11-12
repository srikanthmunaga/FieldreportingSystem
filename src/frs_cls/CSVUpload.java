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

public class CSVUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcConnect jc = new JdbcConnect();
	Connection conn = null;
	PreparedStatement ps1 = null, sql_statement1 = null, sql_statement2 = null;
	ResultSet rs1 = null;

	public CSVUpload() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		// //System.out.println("inside the doget() of CSVUpload.java");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null ) {
			response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
			}
		else
		{
		// //System.out.println("inside the doPost() of CSVUpload.java");
		try {
			// String path=request.getParameter("file").trim();
			HttpSession ses=request.getSession(false);
			RequestDispatcher successrd = getServletContext()
					.getRequestDispatcher("/CSVUploadSuccess.jsp");
			RequestDispatcher huma_id_notExist = getServletContext()
					.getRequestDispatcher("/CSVUploadHumaIDNotExist.jsp");
			RequestDispatcher Wrong_format = getServletContext()
					.getRequestDispatcher("/CSVUploadWrongFormat.jsp");
			// System.out.println("getServletName()=" + getServletName());
			// System.out.println("content type=" + request.getContentType());
			// Code Starts for Dynamically Creating the Folder
			/*
			String path = System.getProperty("user.dir");
			// System.out.println("The Path is " + path);
			path = path.substring(0, path.lastIndexOf(":"));
			// System.out.println("The Drive is " + path);
			// Folder Creation Code
			// String org_folder_name = "CSVFileUploadFolder";
			String FolderName = ":\\CSVFileUploadFolder";
			String FolderName_Loc = path + FolderName;
			// System.out.println("The FolderName_Loc is="
			// + FolderName_Loc.toString());

			// File f = new File("C:\\RajeshKumarDas");
			// File f = new File(FolderName.toString());
			File f = new File(FolderName_Loc);
			// File f = new File("C:\\CSVFileUploadFolder");
			try {
				if (f.exists() == false) {
					f.mkdir();
					System.out.println("Directory Created");
				} else {
					System.out.println("Directory is not created");
				}
			} catch (Exception e) {
				// System.out.println("Exception Occured");
				e.printStackTrace();
			}
			// Code ends here
			// String FileStoredLocation = "C:/FRS_Recovery_Target_uploads";
			String FileStoredLocation = FolderName_Loc;
			// MultipartRequest mrequest = new MultipartRequest(request,
			// "C:/uploads");
			MultipartRequest mrequest = new MultipartRequest(request,
					FileStoredLocation);
			Enumeration files = mrequest.getFileNames();
			String filename = null;
			while (files.hasMoreElements()) {
				String upload = (String) files.nextElement();
				filename = mrequest.getFilesystemName(upload);
				System.out.println(filename);
			}
			
			*/
			// System.out.println("The Path is "+path);
			int res = 0;
			int ins = 0;
			int update = 0;
			try {
				String jdbc_insert_sql = "INSERT INTO TARGET_FRS_RECOVERY"
						+ "(HUMA_ID, TARGET_FRS_OD_AMT, TARGET_FRS_SEQID, "
						+ "TARGET_FRS_CBY,TARGET_FRS_DATE) values(?,?,TARGET_FRS_SEQID.nextval,?,?)";

				String jdbc_update_sql = "update TARGET_FRS_RECOVERY set TARGET_FRS_OD_AMT=?, "
						+ "TARGET_FRS_MBY=?,TARGET_FRS_MDATE=sysdate where HUMA_ID=? and TARGET_FRS_DATE=?";
				conn = jc.getConnection();
				sql_statement1 = conn.prepareStatement(jdbc_insert_sql);
				sql_statement2 = conn.prepareStatement(jdbc_update_sql);
				//String inputCSVFile = FileStoredLocation + "/" + filename;
				String inputCSVFile = ses.getAttribute("inputCSVFile").toString();
				//System.out.println("The File Path = " + inputCSVFile);
				// String inputCSVFile = "C:\\Raj\\Test.csv";
				CSVReader reader = new CSVReader(new FileReader(inputCSVFile));
				String[] nextLine;
				int lnNum = 0;

				String Username = request.getSession().getAttribute("username")
						.toString();
				HashSet<String> NonExist_EmpList = new HashSet<String>();
				// System.out.println("The user name = " + Username);

				// Calendar currentDate = Calendar.getInstance(); // Get the
				// SimpleDateFormat formatter = new SimpleDateFormat(
				// "yyyy/MMM/dd HH:mm:ss");
				// String dateNow = formatter.format(currentDate.getTime());

				// System.out.println("Now the date is :=>  " + dateNow);
				// To omit the 1st Row of the .csv file
				nextLine = reader.readNext();
				ps1 = conn.prepareStatement("select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY where HUMA_ID=? and TARGET_FRS_DATE=?");
				NonExist_EmpList.clear();
				while ((nextLine = reader.readNext()) != null) {
					lnNum++;
					//System.out.println(lnNum);

					// System.out.println("The humaid is "+nextLine[0]);
					// System.out.println("The Date is "+nextLine[2]);
					// sql_statement.addBatch();
					// ps =
					// conn.prepareStatement("select TARGET_FRS_OD_AMT from TARGET_FRS_RECOVERY where HUMA_ID=? and TARGET_FRS_DATE=?");
					ps1.setString(1, nextLine[0]);
					ps1.setString(2, nextLine[2]);
					rs1 = ps1.executeQuery();
					// System.out.println("Duplicate or not checked");
					if (!rs1.next()) {
						// System.out.println("result set is null in shm.jsp");
						sql_statement1.setString(1, nextLine[0]);
						sql_statement1.setDouble(2,
								Double.parseDouble(nextLine[1]));// NumberFormatException
						sql_statement1.setString(3, Username);
						sql_statement1.setString(4, nextLine[2]);
						int count=0;
						try{
						count = sql_statement1.executeUpdate();
						} catch (SQLException e1) {
							int errcode = e1.getErrorCode();
							if ((errcode == 2291)||(errcode ==12899)) {//Huma_id not exist in Huma_mstr
							//if (errcode == 2291) {//Huma_id not exist in Huma_mstr
								//System.out.println("Employee does not exists");
								NonExist_EmpList.add(nextLine[0]);
								//e1.printStackTrace();
								//huma_id_notExist.include(request, response);
							} else {
								//System.out.println("Got some other Software(DB) error");
								e1.printStackTrace();
								//Wrong_format.include(request, response);
							}
						}
						res = res + count;
						ins = ins + 1;
					} else {
						// System.out.println("Duplicate data found");
						sql_statement2.setDouble(1,
								Double.parseDouble(nextLine[1]));
						sql_statement2.setString(2, Username);
						// sql_statement2.setString(3, dateNow);
						sql_statement2.setString(3, nextLine[0]);
						sql_statement2.setString(4, nextLine[2]);
						int count2 = sql_statement2.executeUpdate();
						res = res + count2;
						update = update + 1;
					}

				}// While
					// System.out.println("res="+res+" ins="+ins+" update="+update);
				request.setAttribute("tot_no_of_csv_record", res);
				request.setAttribute("csv_inserted", ins);
				request.setAttribute("csv_updated", update);
				// System.out.println("res="+request.getAttribute("tot_no_of_csv_record"));
				// --------success redirect block starts
				if (NonExist_EmpList.isEmpty())
					successrd.include(request, response);// out.println("OK WAR Room Data Entry updated successfully");
				else { // if(smsDates!=null)
					//System.out.println("NonExist_EmpList="+NonExist_EmpList);
					request.setAttribute("NonExist_EmpList", NonExist_EmpList);
					//NonExist_EmpList.clear();
					huma_id_notExist.include(request, response);//successrd.include(request, response);// out.println("OK WAR Room Data Entry updated successfully. Cannot change WAR Room target for "+
															// smsDates+
															// ", as your FRS Recovery already received");
				}
				// ----------success redirect block ends
				// successrd.include(request, response);
			} catch (SQLException e1) {
				int errcode = e1.getErrorCode();
				// System.out.println("The Error code was "+errcode);
				if ((errcode == 2291)||(errcode ==12899)) {//Huma_id not exist in Huma_mstr
				//if (errcode == 2291) {//This block already handled
					System.out.println("Entered Huma_id not exist at Final");//smsDates.add(war_date[i]);
					e1.printStackTrace();
					huma_id_notExist.include(request, response);
				} else {
					System.out.println("Got some other Software(DB) error");
					e1.printStackTrace();
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