package frs_cls;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public class FRSReports {
	private Connection con;   
    @SuppressWarnings("unused")
	private Logger log = Logger.getLogger("LOGFILE");

	
	
	@SuppressWarnings("deprecation")
	public void FRSReports(HttpServletRequest request, HttpServletResponse response,String fdate,String tdate,String format,String report_name,String huma_id[])
	{
		response.setContentType("application/pdf");
		JdbcConnect jc=new JdbcConnect();
		try {
		con=jc.getConnection();
		jmp: if (report_name != null) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("fdate",fdate);
		parameters.put("tdate",tdate);
		parameters.put("format",format);
		parameters.put("report_name",report_name);
		//parameters.put("emp_id",huma_id);
		//------------New added block for multiple selections-----------
		String emp_id[] = huma_id;//request.getParameterValues("emp_id");// System.out.println("hey parameter from on submit ="+emp_id);
		if (emp_id == null)
			break jmp;// breaks the jmp: block and sends
						// controle to }//try stmt
		String emplist = "";
		if (emp_id != null && emp_id.length != 0) {
			for (int i = 0; i < emp_id.length; i++)
				emplist = emplist + "'" + emp_id[i] + "'"
						+ ",";// data
								// =rs1.getString(1)+":"+rs1.getString(2)+":";
		}
		emplist = emplist
				.substring(0, emplist.length() - 1);
		//System.out.println(emplist);
		parameters.put("emp_id", emplist);// //putting
											// emplist in
											// emp_id
											// parameter
		//System.out.println("emplist="+emplist);
		//---------------------------------------------------------
		File rf = new File(request.getRealPath("/")+"reports\\"+report_name+".jrxml");
		String repPath=rf.getAbsolutePath();
		//System.out.println(repPath);
		System.out.println("repPath="+repPath);
		
		//JasperReport jasperReport = JasperCompileManager.compileReport(repPath);
		
		//code to remoty outOfMemoryError instead of above line starts -----------------------
		JasperReport jasperReport = JasperCompileManager.compileReport(repPath);
		//code to remoty outOfMemoryError instead of above line  ends-----------------------
		System.gc();
		JasperPrint  jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,con);
		
		JRPdfExporter pdfexporter = new JRPdfExporter();
		ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
		//pdfexporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		pdfexporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfReport);
		
		JRXlsExporter xlsexporter = new JRXlsExporter();
		ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
		//xlsexporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		xlsexporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
		
		 //xlsexporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
		 xlsexporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);//remove spaces b/w columns
		 xlsexporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);//remove spaces b/w rows
		 //xlsexporter.setParameter(JExcelApiExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);//IS_WHITE_PAGE_BACKGROUND
		 xlsexporter.setParameter(JExcelApiExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);//IS_DETECT_CELL_TYPE
		 xlsexporter.setParameter(JExcelApiExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);//IS_IGNORE_GRAPHICS
		 xlsexporter.setParameter(JExcelApiExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);//IS_COLLAPSE_ROW_SPAN
		 //xlsexporter.setParameter(JRExporterParameter.OUTPUT_FILE, "C:\\JSP\\");
		 //xlsexporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "sample.xls"); 
		//System.out.println("hey end of the decide checking & next common code starts again here");
		
		/*byte bytes[] = new byte[10];
		pdfexporter.exportReport();
		bytes = pdfReport.toByteArray();
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		pdfReport.close(); 
		OutputStream ouputStream = response.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		 ouputStream.close();
		}*/	
		 byte bytes[] = new byte[10];
		   //String result = JasperRunManager.runReportToHtmlFile(request.getRealPath("/")+"/REPORTS/subreporttwo2excel.jasper" , parameterMap, con);
		    pdfexporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			xlsexporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			System.out.println("format="+format);
			System.gc();
		 if(format.contains("PDF"))
		  {
			pdfexporter.exportReport();
			bytes = pdfReport.toByteArray();
			response.setContentType("application/pdf");
		  }
		 if(format.contains("XLS"))
		  {
			xlsexporter.exportReport();
			bytes = xlsReport.toByteArray();
			response.setContentType("application/vnd.ms-excel");
		   }
		 response.setContentLength(bytes.length);
		 //xlsReport.close();
		 pdfReport.close(); 

		 OutputStream ouputStream = response.getOutputStream();
		 ouputStream.write(bytes, 0, bytes.length);
		 ouputStream.flush();
		 ouputStream.close();
		 ouputStream=null;
		 jasperPrint=null;
		 jasperReport=null;
		}//if (report_name != null)
		}//try
		catch(Exception e){
			e.printStackTrace();
		}finally
		{ 
			 if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

					
	}
	
}

