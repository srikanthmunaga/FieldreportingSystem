package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class deleteuser
 */
public class deleteuserUnit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcConnect jc=new JdbcConnect();
	Connection con=null;
	private Logger log = Logger.getLogger(deleteuser.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteuserUnit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Below five lines code is to session checking & login redirect
				if (request.getSession().getAttribute("username") == null ) {
					response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
					}
				else
				{	
		RequestDispatcher successrd = getServletContext().getRequestDispatcher(
				"/deleteuserunitsuccess.jsp");
		RequestDispatcher failrd = getServletContext().getRequestDispatcher(
				"/deleteuserunitfail.jsp");
		// String
		// sql="DELETE FROM FRS_USER  WHERE USERNAME ='"+request.getParameter("username")+"'";
		String sql = "update frs_user set user_freeze='Y',user_mdate=sysdate,user_mby='"
				+ (String) (request.getSession().getAttribute("username"))
				+ "' where USERNAME ='"
				+ request.getParameter("username")
				+ "'";
		// update frs_user set user_freeze='N' where username='basix'

		HttpSession hs = request.getSession();
		hs.setAttribute("un", request.getParameter("username"));

		try {
			con=jc.getConnection();
			//log.info("JDBC Connection was created");
			Statement st=con.createStatement();
			st.executeUpdate(sql);
			con.commit();
			successrd.include(request, response);
			/*
			 * }else { failrd.include(request, response); }
			 */

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn("",e);
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.warn("",e);
			//e.printStackTrace();
		}

	}

}
}