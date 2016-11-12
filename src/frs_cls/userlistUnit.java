package frs_cls;

import java.io.IOException;
import java.sql.Connection;
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

public class userlistUnit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcConnect jc=new JdbcConnect();
	Connection con=null;
	private loginuser LoginUser;
	private Logger log = Logger.getLogger(JdbcConnect.class);

	
	
    public userlistUnit() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Below five lines code is to session checking & login redirect
				if (request.getSession().getAttribute("username") == null ) {
					response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
					}
				else
				{
		
		//System.out.println("hello sreenivas");
		//response.getWriter().println("welcome to sreenivas");
		//String username=request.getParameter("username");
		String dwr=request.getParameter("dwr");
		String fromdate=request.getParameter("fdate");
		String todate=request.getParameter("todate");
		String username=null;
		RequestDispatcher successrd=getServletContext().getRequestDispatcher("/modifyuserUnit.jsp");
		/*try {
			con=jc.getConnection();
		} catch (Exception e1) {
			log.warn(e1);
			e1.printStackTrace();
		}*/
		/*if(dwr.equals("Area"))
		{
			//System.out.println("Msr debug 1");
		
			//areaWiseProductWiseSummeryXLS(response,fromdate,todate,area_name);
		}else */ 
		if(dwr.equals("Unit"))
		{
			System.out.println("unit is selected");
			username=request.getParameter("BSFLUNIT_NAME");
			//unitWiseProductWiseSummeryXLS(response, fromdate, todate, BSFLUNIT_NAME);
		}else if(dwr.equals("lsr"))
		{
			System.out.println("lsr is selected");
			username=request.getParameter("huma_id");
			//String huma_id1=request.getParameter("huma_id");
			int i = username.lastIndexOf('-');  
			username=username.substring(i+1);
			System.out.print("huma_id="+username);
			System.out.println("LSR");
			
			//lsrWiseProductWiseSummeryXLS(response,fromdate,todate,huma_id);
		}
		DisplayData(request,username);
        successrd.include(request, response);
		
				}		

	} //doGet()

/*
}

		try {
		*/
		public void DisplayData(HttpServletRequest request,String username){
			try{
			con=jc.getConnection();
			String sql="select * from frs_user where USERNAME='"+username+"'";
			System.out.println("Query is "+sql);
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
			}
			}catch(Exception e){
				e.getMessage();
			}
			finally
			{ try{
				 if(con!=null)con.close();
			}
					 catch (SQLException e) {
						e.printStackTrace();
					} 
			}//finally
			
			//user_freeze
				//System.out.println("the freeze="+rs.getString(17));
			}
			}//DisplayData()

			/*
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn("",e);
			//e.printStackTrace();
		}
		successrd.include(request, response);
		
				}		

	}


}
*/