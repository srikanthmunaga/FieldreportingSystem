package frs_cls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class unittransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	private JdbcConnect jc = new JdbcConnect();
	PreparedStatement ps = null;
	int count;
	boolean f=false;
	bsflunitmstr bsflunitmstr;
    public unittransfer() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			con = jc.getConnection();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		System.out.println("In Unit Trasfer POST Method");
		String destarea=request.getParameter("areaname2");
		String sourcearea=request.getParameter("BSFLUNIT_UCODE");
		System.out.println("destination unit is "+destarea);
		System.out.println("source unit is "+sourcearea);
		String args[] =request.getParameterValues("unitnames");
		String svsql1 = "select * from area_mstr where AREA_ID='"+destarea.substring(destarea.lastIndexOf('-')+1, destarea.length())+"'";
		Statement svst1;
		try {
			svst1 = con.createStatement();
			ResultSet svrs1 = svst1.executeQuery(svsql1);
			if (!svrs1.next()) {
				out.println("Entered Destination AREA does not Exist :"+destarea);
				return;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		String svsql2 = "select * from area_mstr where AREA_ID='"+sourcearea.substring(sourcearea.lastIndexOf('-')+1, sourcearea.length())+"'";
		Statement svst2;
		try {
			svst2 = con.createStatement();
			ResultSet svrs2 = svst2.executeQuery(svsql2);
			if (!svrs2.next()) {
				out.println("Entered Destination AREA does not Exist :"+sourcearea);
				return;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		bsflunitmstr=new bsflunitmstr();
		for (String string : args) {
		f=bsflunitmstr.validateUNIT(string.substring(string.lastIndexOf('-')+1, string.length()), con);
		if(!f)
		{
			out.println("Entered UNIT does not Exist"+string);
		}
		}
		
		String sql="UPDATE BSFLUNIT_MSTR SET AREA_ID =? WHERE BSFLUNIT_UCODE=?";
		try {

			ps=con.prepareStatement(sql);
			count=0;
			for (String string : args) {
				count=count+1;
					System.out.println("UHNIT ID IS :"+string.substring(string.lastIndexOf('-')+1, string.length()));
					ps.setString(1,destarea.substring(destarea.lastIndexOf('-')+1, destarea.length()));
					ps.setString(2, string.substring(string.lastIndexOf('-')+1, string.length()));
					ps.executeUpdate();
			}
			if(count>0){
			out.println("OK"+count+" Units transferred from "+sourcearea+" to "+destarea+" Successfully");
			return;
			}
			System.out.println(count+"Units transferred from "+sourcearea+" to "+destarea+" Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
