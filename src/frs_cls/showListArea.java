package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class GetuserList
 */
public class showListArea extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcConnect jc=new JdbcConnect();
	Connection con=null;
	PreparedStatement ps=null;
	private Logger log = Logger.getLogger(JdbcConnect.class);


	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showListArea() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	////System.out.println("inside the doGet method");
		//System.out.println("Msr debug 1");
	doPost(request, response);
	//System.out.println("Msr debug 2");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		//Below five lines code is to session checking & login redirect
			//System.out.println("inside the showListArea servlet");	
			
		           if (request.getSession().getAttribute("username") == null ) {
					response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
					}
				else
				{		
		////System.out.println("inside the doPost method");
					String  data ="";
					String parameter = request.getParameter("parameter").toString().trim(); 
					//String sql="select huma_id from huma_mstr";
					try {
					con=jc.getConnection();
					//log.info("JDBC Connection was created");
					//Below two lines code will be differ for each LOV code
						//for distinct huma_id list
						if(parameter.equals("huma_id")) 
							ps = con.prepareStatement("select firstname||' '||lastname||'-'||huma_id from huma_mstr order by huma_id");
						//for distinct BSFLUNIT_UCODE list
						if(parameter.equals("BSFLUNIT_UCODE"))//for BSFLUNIT_UCODE list 
							ps = con.prepareStatement("select distinct BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE from huma_mstr order by BSFLUNIT_UCODE");
						//for distinct username list
						if(parameter.equals("username"))//for username list
							//System.out.println("Msr debug");
							ps = con.prepareStatement("select distinct username from frs_user order by username");
						if(parameter.equals("BSFLUNIT_NAME"))//for username list 
							ps = con.prepareStatement("select distinct BSFLUNIT_NAME from huma_mstr order by BSFLUNIT_NAME");
						if(parameter.equals("area_name"))//for username list 
							System.out.println("Rajesh Das");
							ps = con.prepareStatement("select distinct area_name from huma_mstr order by area_name");
						
					//System.out.print("Inside try and before connection obj");
					ResultSet rs = ps.executeQuery();//System.out.print("before while loop");
					while(rs.next())//System.out.println("First string="+rs.getString(1));
					data =data+rs.getString(1)+"::::::";//System.out.println("data="+data.length());
					response.getWriter().println(data);
					rs.close();
					if(ps!=null)ps.close();
				     if(con!=null)con.close(); 
					} 
					
					catch (SQLException e) {
						// TODO Auto-generated catch block
						log.warn("",e);
						//e.printStackTrace();
					
					//} catch (ClassNotFoundException e) {
					} catch (Exception e) {
						// TODO Auto-generated catch block
						log.warn("",e);
						//e.printStackTrace();
					}
		}	
}
}