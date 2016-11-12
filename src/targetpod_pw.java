

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import au.com.bytecode.opencsv.CSVReader;

import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class a1
 */
public class targetpod_pw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public targetpod_pw() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("inside the doget() of CSVUpload.java 1");
		doPost(request, response);
		//System.out.println("inside the doget() of CSVUpload.java");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
		} else {
			//System.out.println("inside the dopost() of CSVPleasewait.java 1");
			try {
				/*
				 * RequestDispatcher successrd = getServletContext()
				 * .getRequestDispatcher("/CSVUploadSuccess.jsp");
				 * RequestDispatcher huma_id_notExist = getServletContext()
				 * .getRequestDispatcher("/CSVUploadHumaIDNotExist.jsp");
				 * RequestDispatcher Wrong_format = getServletContext()
				 * .getRequestDispatcher("/CSVUploadWrongFormat.jsp");
				 */
				HttpSession ses = request.getSession(false);
				// //System.out.println("inside the CsvPleaseWait java file");
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
						;
						// System.out.println("Directory is not created");
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
					// System.out.println("The file name is" + filename);
				}
				 //System.out.println("The Path is "+path);
				String inputCSVFile = FileStoredLocation + "/" + filename;
				// //System.out.println("inside csvpleaseWait and inputCSVFile="+
				// inputCSVFile);
				//System.out.println("Msr debug ");
				ses.setAttribute("inputCSVFile", inputCSVFile);
			}// try
			catch (Exception e) {
				e.printStackTrace();
			}

			// Real code
			RequestDispatcher rd = request
					.getRequestDispatcher("/targetpod_pleasewait.jsp");
			// System.out.println("before farwording....");
			rd.forward(request, response);
			// System.out.println("after farwording....");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
