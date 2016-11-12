package frs_cls;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.apache.log4j.Logger;
public class montotsummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = null;
	private Connection con;  
    private Logger log = Logger.getLogger(montotsummary.class);
    
    public montotsummary() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				if (request.getSession().getAttribute("username") == null ) {
					response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
					}
				else
				{
		String format=request.getParameter("format");			
		String fromdate=request.getParameter("fdate");
		String todate=request.getParameter("todate");
		//String format=request.getParameter("");
		String dwr=request.getParameter("dwr");
		try {
			if(dwr.equals("Area"))
			{
				String area_name=null;
				area_name=request.getParameter("area_name");
				String reportname="daywisetotalsummary";
				areaWiseProductWiseSummeryXLS(response,fromdate,todate,area_name,request,reportname,format);
			}else  if(dwr.equals("Unit"))
			{
				String BSFLUNIT_NAME=null;
				BSFLUNIT_NAME=request.getParameter("BSFLUNIT_NAME");
				String reportname="daywiseunitsummary";
				areaWiseProductWiseSummeryXLS(response,fromdate,todate,BSFLUNIT_NAME,request,reportname,format);
				
			}else if(dwr.equals("lsr"))
			{
				String huma_id1=request.getParameter("huma_id");
				String reportname="daywiselsrsummary";
				int i = huma_id1.lastIndexOf('-');  
				String huma_id=null;
				huma_id=huma_id1.substring(i+1);
				System.out.print("huma_id="+huma_id);
				System.out.println("LSR");
				
				//System.out.println("Msr debug 1");
				areaWiseProductWiseSummeryXLS(response,fromdate,todate,huma_id,request,reportname,format);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn("",e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.warn("",e);
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
	public String areaWiseProductWiseSummeryXLS(HttpServletResponse res,String fromdate,String todate,String area_name,HttpServletRequest req,String reportname,String format) throws IOException, SQLException, ClassNotFoundException {
		res.setContentType("application/pdf");
		JdbcConnect jc=new JdbcConnect();
		try {
		con=jc.getConnection();
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("fdate",fromdate);
		parameters.put("tdate",todate);
		parameters.put("format",format);
		parameters.put("report_name",reportname);
		parameters.put("emp_id",area_name);
		System.out.println("Single parameter "+area_name);
		
		File rf = new File(req.getRealPath("/")+"reports\\"+reportname+".jrxml");
		String repPath=rf.getAbsolutePath();
		System.out.println(repPath);
		System.out.println("repPath="+repPath);
		
		JasperReport jasperReport = JasperCompileManager.compileReport(repPath);
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
		 if(format.contains("PDF"))
		  {
			pdfexporter.exportReport();
			bytes = pdfReport.toByteArray();
			res.setContentType("application/pdf");
		  }
		 if(format.contains("XLS"))
		  {
			xlsexporter.exportReport();
			bytes = xlsReport.toByteArray();
			res.setContentType("application/vnd.ms-excel");
		   }
		 res.setContentLength(bytes.length);
		 //xlsReport.close();
		 pdfReport.close(); 

		 OutputStream ouputStream = res.getOutputStream();
		 ouputStream.write(bytes, 0, bytes.length);
		 ouputStream.flush();
		 ouputStream.close();
		
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
		return SUCCESS;
	}
}
