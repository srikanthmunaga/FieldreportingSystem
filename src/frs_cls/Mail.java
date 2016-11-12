package frs_cls;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import com.sun.xml.internal.ws.api.pipe.NextAction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 *
 * @author www.codejava.net
 *
 */
 public class Mail extends HttpServlet {
    private String host;
    private String port;
    private String user;
    private String pass;
    Statement st1=null,st2=null;
    ResultSet rs=null,rs1=null,rs2=null;
    JdbcConnect jc=new JdbcConnect();
    Connection con;
 
    public void init() {       // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
    
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
    	String stid=null;
    	String couname=null;
    	String huma_id=request.getSession().getAttribute("huma_id").toString();
    	System.out.println(huma_id);
    	String unitid=request.getParameter("bsflunit_ucode");
    	System.out.println(unitid);
    	String st_id=request.getParameter("st_id");
    	System.out.println(st_id);
    	String date=request.getParameter("d_oi");
    	System.out.println(date);
    	String email=null; 
        String subject = "Indent Reply";
        
        
        String resultMessage = "";
       
        PrintWriter out=response.getWriter();
        
        try{
        	con=jc.getConnection();
        	System.out.println("connected succesfully");
        	String sql="select huma_email from huma_mstr where huma_id='"+huma_id+"'";
        	Statement st=con.createStatement();
        	rs=st.executeQuery(sql);
        	while(rs.next()){
        		email=rs.getString(1);
        		System.out.println(email);
        	}
        	
        	String sql1="select S_NAME,COU_NAME FROM ISSUE_INDENT WHERE BSFLUNIT_UCODE='"+unitid+"'AND S_ID='"+st_id+"'";
        	Statement st1=con.createStatement();
        	rs1=st1.executeQuery(sql1);
        	while(rs1.next()){
        		stid=rs1.getString(1);
        		System.out.println(stid);
        		couname=rs1.getString(2);
        		System.out.println(couname);
        	}
        	
        }catch(Exception e){
        	e.printStackTrace();
        }    
        //String recipient=email;
        
        StringBuffer content = new StringBuffer("<br><b>Dear Unit,</b><br><br>"
				+ "<font color='#0B4599'>The following indent for  "+stid+" has been succesfully sent\n"
				+"through courier "
				+ couname+"<br>");
        int sno=1;
        try{
        	content.append("<table border><TR bgcolor='#C2DFFF'><th>Sno</th><th>UnitId</th><th>Unit Name</th><th>Stationary Id</th><th>Stationary Name</th><th>Courier Id</th></tr>");// border
        	String sql2="select BSFLUNIT_UCODE,BSFLUNIT_NAME,S_ID,S_NAME,COU_ID FROM ISSUE_INDENT WHERE BSFLUNIT_UCODE='"+unitid+"'AND S_ID='"+st_id+"' and DATE_OF_COU=to_date('"+date+"','dd-mm-yyyy')";
        	Statement st2=con.createStatement();
        	rs2=st2.executeQuery(sql2);
        	while(rs2.next()){
        	content.append("<tr><td>" + (sno++) + "</td>" + "<td>"
				 + rs2.getString(1) + "</td>" + "<td>" + rs2.getString(2)
				 + "</td>" + "<td>" + rs2.getString(3) + "</td>" + "<td>"
				 + rs2.getString(4) + "</td>" + "<td>" + rs2.getString(5) + "</td>"
				 + "</td></tr>");
        	}
        	content.append("</table>");
        	content.append("<br><br><font color='#0B4599'>Note: This is system generated mail. Please do not reply to this mail, In case of any clarification please contact helpdesk@basixindia.com  / IT support team at HYD.<BR><BR>Best Regards,<BR>System Development Team,<br>HYD.</font>");
        }catch(Exception ss){
        	ss.printStackTrace();
        }
        if(email!=null){
        try {
            EmailUtility.sendEmail(host, port, user, pass, email, subject,
                    content);
            resultMessage = "The e-mail was sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/issuesubmit.jsp").forward(
                    request, response);
        }
    }else{
    	out.println("mail doesnot exixt");
    }
}
 }