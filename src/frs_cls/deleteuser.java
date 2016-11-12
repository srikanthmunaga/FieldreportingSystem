package frs_cls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.groovy.transform.sc.transformers.CompareIdentityExpression;

/**
 * Servlet implementation class deleteuser
 */
public class deleteuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcConnect jc=new JdbcConnect();
	Connection con=null;
	String sql=null;
	String sql1=null;
	String sql2=null;
	String sql3=null;
	String areano1=null;
	String areano2=null;
	ResultSet rs10=null, rs = null;
	PreparedStatement ps = null;
	
	private Logger log = Logger.getLogger("LOGFILE");
       
    public deleteuser() {
        super();
    }

		protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String role=(String)request.getSession().getAttribute("userrole");
		String deleteuser=request.getParameter("username").trim();//To get the username to be deleted
		deleteuser=deleteuser.substring(deleteuser.lastIndexOf('-')+1);
		String username=(String)request.getSession().getAttribute("username");//To get the logged in user
		String huma_id=(String)request.getSession().getAttribute("huma_id");//To get the logged in userid
		System.out.println("userName="+username+" userRole="+role+" huma_id="+huma_id+" deleteuser="+deleteuser);
		RequestDispatcher successrd = getServletContext().getRequestDispatcher(
				"/deleteusersuccess.jsp");
		RequestDispatcher failrd = getServletContext().getRequestDispatcher(
				"/deleteuserfail.jsp");
		RequestDispatcher humaIDNotExist = getServletContext().getRequestDispatcher(
				"/Freeze Huma_id not exist.jsp");
		HttpSession hs = request.getSession();
		System.out.println("The user to be deleted "+deleteuser);
		hs.setAttribute("un", request.getParameter("username"));
				if (request.getSession().getAttribute("username") == null ) {
					response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
					}
				
				else if (role.equals("user") || role.equals("fs") || role.equals("fx")) {
					out.print("You are not authorised to Modify the Region Details");									// login page.
				}
				
				
				
				
			else
			{
				try{
					//System.out.println("inside the try block");
					String qry = "select huma_id from huma_mstr where huma_id='"+deleteuser+"'";
					//System.out.println("The query is "+qry);
					con=jc.getConnection();
					ps = con.prepareStatement(qry);
				    rs = ps.executeQuery();
				
					
				if (!rs.next()){
					//getServletContext().getRequestDispatcher("/Huma_id not exist.jsp").include(request, response);
				    humaIDNotExist.include(request, response);
				    return;
				    
				    //failrd.include(request, response);
					//return;
					//System.out.println("rs is empty");
				    
				}/*else{
					successrd=getServletContext().getRequestDispatcher("/LsrDetailsSubmit.jsp");
					System.out.println("rs not empty");
				}*/
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					try{
					if(con!=null)
						con.close();
					if(rs!=null)
						rs.close();
					if(ps!=null)
						ps.close();
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
				//For AreaHead
				if(role.equals("areahead")){
					//logged in user belongs to which area
					sql1="select area_id from area_mstr where huma_id='"+huma_id+"'";
					//The user to be deleted belongs to which area
					sql2="select area_id from bsflunit_mstr " +
							"where  bsflunit_ucode=(select bsflunit_ucode from huma_mstr " +
							"where huma_id=(select huma_id from frs_user where username='"+deleteuser+"'))";
					try{
					Connection con=jc.getConnection();
					Statement st1=con.createStatement();
					ResultSet rs1=st1.executeQuery(sql1);
					if(rs1!=null)
					  {
						while(rs1.next())
					       {
						      areano1=rs1.getString(1);
					       }//while
					  }//if
					System.out.println("areano1= "+areano1);
					
					//Connection con=jc.getConnection();
					Statement st2=con.createStatement();
					ResultSet rs2=st2.executeQuery(sql2);
					if(rs2!=null)
					  {
					    System.out.println("rs2 not null"); 
						while(rs2.next())
					       {
						      areano2=rs2.getString(1);
					       }//while
					  }//if
					if(!areano1.equals(areano2))
					{
						//out.println("You are not allowed to delete other Region's user");
						//return;
						failrd.include(request, response);
						return;
					}
					
					}catch(Exception e)
					{
						e.getMessage();
					}finally
					{ 
						
						 if(con!=null)
							try {
								con.close();
							} catch (SQLException e) {
								e.printStackTrace();
							} 
					}//finally
					
				}//if(role.equals("areahead"))
				
				if(role.equals("unithead")){
					//logged in user belongs to which area
					sql1="select bsflunit_ucode from huma_mstr where huma_id='"+huma_id+"'";
					//The user to be deleted belongs to which area
					sql2="select bsflunit_ucode from huma_mstr " +
							"where huma_id=(select huma_id from frs_user where username='"+deleteuser+"')";
							
							
					try{
					Connection con=jc.getConnection();
					Statement st1=con.createStatement();
					ResultSet rs1=st1.executeQuery(sql1);
					if(rs1!=null)
					  {
						while(rs1.next())
					       {
						      areano1=rs1.getString(1);
					       }//while
					  }//if
					System.out.println("areano1= "+areano1);
					
					//Connection con=jc.getConnection();
					Statement st2=con.createStatement();
					ResultSet rs2=st2.executeQuery(sql2);
					if(rs2!=null)
					  {
					    System.out.println("rs2 not null"); 
						while(rs2.next())
					       {
						      areano2=rs2.getString(1);
					       }//while
					  }//if
					if(!areano1.equals(areano2))
					{
						//out.println("You are not allowed to delete other Unit's user");
						//return;
						failrd.include(request, response);
						return;
					}
					
					}catch(Exception e)
					{
						e.getMessage();
					}finally
					{ 
						
						 if(con!=null)
							try {
								con.close();
							} catch (SQLException e) {
								e.printStackTrace();
							} 
					}//finally
					
				}//if(role.equals("unithead"))
				
				
			 sql = "update frs_user set user_freeze='Y',user_mdate=sysdate,user_mby='"
				+ (String) (request.getSession().getAttribute("username"))
				+ "' where USERNAME ='"
				+ deleteuser
				+ "'";
		// update frs_user set user_freeze='N' where username='basix'

		//hs.setAttribute("un", request.getParameter("username"));

		try {
			con=jc.getConnection();
			Statement st=con.createStatement();
			st.executeUpdate(sql);
			con.commit();
			successrd.include(request, response);
			

		} catch (SQLException e) {
			log.warn("",e);
		//} catch (ClassNotFoundException e) {
		} catch (Exception e) {
			log.warn("",e);
		}finally
		{ 
			
		 if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	}//finally


	}//else
		//}//else of rs10==null			

}//do post
}//class