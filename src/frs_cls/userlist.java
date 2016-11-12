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

public class userlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcConnect jc=new JdbcConnect();
	Connection con=null;
	private loginuser LoginUser;
	private Logger log = Logger.getLogger("LOGFILE");

	
	
    public userlist() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Below five lines code is to session checking & login redirect
				if (request.getSession().getAttribute("username") == null ) {
					response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
					}
				else
				{
		
		//System.out.println("hello sreenivas");
		//response.getWriter().println("welcome to sreenivas");
		String username=request.getParameter("username");
		RequestDispatcher successrd=getServletContext().getRequestDispatcher("/modifyuser.jsp");
		try {
			con=jc.getConnection();
			////log.info("JDBC Connection was created");
		//} catch (ClassNotFoundException e1) {
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			log.warn(e1);
			e1.printStackTrace();
		}
		try {
			String sql="select * from frs_user where USERNAME='"+username+"'";
			/*String sql="select" +
					"nvl2(name,name,'') as name," +
					"nvl2(emailid,emailid,'') as emailid," +
					"nvl2(hno,hno,'') as hno," +
					"nvl2(locality,locality,'') as locality," +
					"nvl2(city,city,'') as city," +
					"nvl2(district,district,'') as disctict," +
					"nvl2(state,state,'') as state," +
					"nvl2(country,country,'') as country," +
					"nvl2(postalcode,postalcode,'') as postalcode," +
					"nvl2(username,username,'') as username," +
					"nvl2(password,password,'') as password," +
					"nvl2(userrole,userrole,'') as userrole," +
					"nvl2(landno,landno,'') as landno," +
					"nvl2(epassword,epassword,'') as epassword," +
					"nvl2(mobileno,mobileno,'') as mobileno," +
					"nvl2(user_seqid,user_seqid,'') as user_seqid," +
					"nvl2(user_freeze,user_freeze,'') as user_freese" +
					"from frs_user where USERNAME='"+username+"'";*/
			//String sql="select nvl2(name,name,'') as name,nvl2(emailid,emailid,'') as emailid,nvl2(hno,hno,'') as hno,nvl2(locality,locality,'') as locality,nvl2(city,city,'') as city,nvl2(district,district,'') as disctict,nvl2(state,state,'') as state,nvl2(country,country,'') as country,nvl2(postalcode,postalcode,'') as postalcode,nvl2(username,username,'') as username,nvl2(password,password,'') as password,nvl2(userrole,userrole,'') as userrole,nvl2(landno,landno,'') as landno,nvl2(epassword,epassword,'') as epassword,nvl2(mobileno,mobileno,'') as mobileno,nvl2(user_seqid,user_seqid,'') as user_seqid,nvl2(user_freeze,user_freeze,'') as user_freese from frs_user where username='"+username+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			HttpSession hs=request.getSession();
			hs.setAttribute("mun", username);
			while(rs.next()){
				if(rs.getString(1)!=null)hs.setAttribute("name",rs.getString(1)); else hs.setAttribute("name","");
				System.out.println((String)rs.getString(2));
				if(rs.getString(2)!=null)hs.setAttribute("emailid",rs.getString(2)); else hs.setAttribute("emailid","");
				if(rs.getString(15)!=null)hs.setAttribute("mno",rs.getString(15)); else hs.setAttribute("mno","");
				if(rs.getString(13)!=null)hs.setAttribute("lno",rs.getString(13)); else hs.setAttribute("lno","");
				if(rs.getString(3)!=null)hs.setAttribute("hno",rs.getString(3)); else hs.setAttribute("hno","");
				if(rs.getString(4)!=null)hs.setAttribute("locality",rs.getString(4)); else hs.setAttribute("locality",""); 
				if(rs.getString(5)!=null)hs.setAttribute("city",rs.getString(5)); else hs.setAttribute("city","");
				if(rs.getString(6)!=null)hs.setAttribute("dist",rs.getString(6)); else hs.setAttribute("dist",""); 
				if(rs.getString(7)!=null)hs.setAttribute("state",rs.getString(7)); else hs.setAttribute("state",""); 
				if(rs.getString(8)!=null)hs.setAttribute("country",rs.getString(8)); else hs.setAttribute("country",""); 
				if(rs.getString(9)!=null)hs.setAttribute("pc",rs.getString(9)); else hs.setAttribute("pc",""); 
				if(rs.getString(10)!=null)hs.setAttribute("un",rs.getString(10)); else hs.setAttribute("un",""); 
				if(rs.getString(12)!=null)hs.setAttribute("ur",rs.getString(12)); else hs.setAttribute("ur",""); 
				if(rs.getString(17)!=null)hs.setAttribute("user_freeze",rs.getString(17)); else hs.setAttribute("user_freeze","");
				//user_freeze
				//System.out.println("the freeze="+rs.getString(17));
			}
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}

		successrd.include(request, response);
		
				}		

	}


}
