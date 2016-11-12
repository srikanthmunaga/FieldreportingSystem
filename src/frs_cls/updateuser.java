package frs_cls;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jasypt.digest.StandardStringDigester;

/**
 * Servlet implementation class updateuser
 */
public class updateuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	JdbcConnect jc=new JdbcConnect();
	Connection con=null;
	private Logger log = Logger.getLogger("LOGFILE");

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateuser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Below five lines code is to session checking & login redirect
				if (request.getSession().getAttribute("username") == null ) {
					response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
					}
				else
				{
		
		StandardStringDigester digester = new StandardStringDigester();
		digester.setAlgorithm("SHA-1");   // optionally set the algorithm
		digester.setIterations(50000);  // increase security by performing 50000 hashing iterations
		String password=request.getParameter("password");
		//System.out.println("By Rajesh The Password is "+password);
		//System.out.println("By Rajesh The Seq_id="+request.getParameter("seqid"));

		RequestDispatcher successrd=getServletContext().getRequestDispatcher("/modifyusersuccess.jsp");
		RequestDispatcher failrd=getServletContext().getRequestDispatcher("/modifyuserfail.jsp");
		String digest = digester.digest(password);
		HttpSession hs=request.getSession(false);
		String modifyun=(String) hs.getAttribute("mun"); 

		String sql="UPDATE FRS_USER SET NAME=?,EMAILID=?,MOBILENO=?,HNO=?,LOCALITY=?," +
				"CITY=?,DISTRICT=?,STATE=?,COUNTRY=?,POSTALCODE=?,USERNAME=?,PASSWORD=?," +
				"USERROLE=?,LANDNO=?,EPASSWORD=?,user_freeze=?,user_mdate=sysdate,user_mby=? WHERE USERNAME =?";
		try {
			con=jc.getConnection();
			////log.info("JDBC Connection was created");
		//} catch (ClassNotFoundException e1) {
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			log.warn("",e1);
			e1.printStackTrace();
		}
		try {
			
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, request.getParameter("name"));
			ps.setString(2, request.getParameter("emailid"));
			ps.setString(3, request.getParameter("mobileno"));
			ps.setString(4, request.getParameter("houseno"));
			ps.setString(5, request.getParameter("locality"));
			ps.setString(6, request.getParameter("city"));
			ps.setString(7, request.getParameter("district"));
			ps.setString(8, request.getParameter("state"));
			ps.setString(9, request.getParameter("country"));
			ps.setString(10, request.getParameter("pcode"));
			ps.setString(11, request.getParameter("username"));
			ps.setString(12, request.getParameter("password"));
			ps.setString(13, request.getParameter("userrole"));
			ps.setString(14, request.getParameter("landno"));
			ps.setString(15,new String(digest));
			ps.setString(16, request.getParameter("user_freeze"));
			ps.setString(17,(String)(request.getSession().getAttribute("username")));//Added by Rajashekhar for mby field
			ps.setString(18,modifyun);
			
			HttpSession hs1=request.getSession();
			hs1.setAttribute("un",request.getParameter("username"));
			hs1.setAttribute("ur",request.getParameter("userrole"));
			//Boolean status=ps.execute();
			//System.out.println("updating");
			ps.executeUpdate();
			//System.out.println("ONE RECOERD WAS INSERTED" );
			con.commit();
			successrd.include(request, response);
			ps.close();

			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn("",e);
			e.printStackTrace();
		}    	finally
		{ //System.out.println("inside finally of checkhuma_id");
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
}