package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class HRuserlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcConnect jc=new JdbcConnect();
	Connection con=null;
	private loginuser LoginUser;
	private Logger log = Logger.getLogger(JdbcConnect.class);

	
	
    public HRuserlist() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Below five lines code is to session checking & login redirect
		//System.out.println("inside the HRuserlist servlet");
				if (request.getSession().getAttribute("username") == null ) {
					response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
					}
				else
				{
		
		//System.out.println("hello sreenivas");
		//response.getWriter().println("welcome to sreenivas");
		//String username=request.getParameter("username");   
		//RequestDispatcher successrd=getServletContext().getRequestDispatcher("/createHRDetails.jsp");
		RequestDispatcher successrd=getServletContext().getRequestDispatcher("/createHRDetails.jsp");
		try {
			con=jc.getConnection();
			//log.info("JDBC Connection was created");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			log.warn(e1);
			e1.printStackTrace();
		}
		try {
			
			String username=request.getParameter("username");
			System.out.println("User name: "+username);
			String sql="select * from huma_mstr where huma_id='"+username+"'";
			//String sql="select * from frs_user where USERNAME='"+username+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			HttpSession hs=request.getSession();
			//hs.setAttribute("mun", username);
			while(rs.next()){
				//if(rs.getString(1)!=null)hs.setAttribute("eid",rs.getString(1)); else hs.setAttribute("eid","");
				if(rs.getString(1)!=null)request.setAttribute("eid",rs.getString(1)); else request.setAttribute("eid","");
				System.out.println((String)rs.getString(2));
				if(rs.getString(2)!=null)request.setAttribute("fname",rs.getString(2)); else request.setAttribute("fname","");
				if(rs.getString(3)!=null)request.setAttribute("lname",rs.getString(3)); else request.setAttribute("lname","");
				if(rs.getString(4)!=null)request.setAttribute("desig",rs.getString(4)); else request.setAttribute("desig","");
				if(rs.getString(5)!=null)request.setAttribute("area_name",rs.getString(5)); else request.setAttribute("area_name","");
				if(rs.getString(6)!=null)request.setAttribute("BSFLUNIT_UCODE",rs.getString(6)); else request.setAttribute("BSFLUNIT_UCODE",""); 
				if(rs.getString(7)!=null)request.setAttribute("BSFLUNIT_NAME",rs.getString(7)); else request.setAttribute("BSFLUNIT_NAME","");
				if(rs.getString(8)!=null)request.setAttribute("mno1",rs.getString(8)); else request.setAttribute("mno1",""); 
				if(rs.getString(9)!=null)request.setAttribute("mno2",rs.getString(9)); else request.setAttribute("mno2",""); 
				if(rs.getString(10)!=null)request.setAttribute("Emialid",rs.getString(10)); else request.setAttribute("Emialid",""); 
				
				/*if(rs.getString(9)!=null)hs.setAttribute("pc",rs.getString(9)); else hs.setAttribute("pc",""); 
				if(rs.getString(10)!=null)hs.setAttribute("un",rs.getString(10)); else hs.setAttribute("un",""); 
				if(rs.getString(12)!=null)hs.setAttribute("ur",rs.getString(12)); else hs.setAttribute("ur",""); 
				if(rs.getString(17)!=null)hs.setAttribute("user_freeze",rs.getString(17)); else hs.setAttribute("user_freeze","");
				*/
			}
				//user_freeze
				//System.out.println("the freeze="+rs.getString(17));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn("",e);
			//e.printStackTrace();
		}finally
		{ 
			
			 if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
		}//finally
		successrd.include(request, response);
		
				}		

	}


}
