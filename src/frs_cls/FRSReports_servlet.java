package frs_cls;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class totalsummary
 */
@SuppressWarnings("unused")
public class FRSReports_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private Logger log = Logger.getLogger("LOGFILE");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FRSReports_servlet() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Below five lines code is to session checking & login redirect
		if (request.getSession().getAttribute("username") == null) {
			response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
		} else {
			response.setContentType("application/pdf");

			String fdate = request.getParameter("fdate");
			String tdate = request.getParameter("todate");
			String format = request.getParameter("format");
			String report_name = request.getParameter("report_name");
			// String huma_id =request.getParameter("huma_id");
			String huma_id[] = request.getParameterValues("huma_id");

			System.out.println("report_name=" + report_name);
			// System.out.println("huma_id="+huma_id);
			FRSReports pr = new FRSReports();
			pr.FRSReports(request, response, fdate, tdate, format, report_name,
					huma_id);

		}

	}
}