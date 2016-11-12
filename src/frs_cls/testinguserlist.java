package frs_cls;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;



public class testinguserlist {

	JdbcConnect jc=new JdbcConnect();
	static Connection con=null;
    static String data="";
    private static Logger log = Logger.getLogger("LOGFILE");

    
    
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		String sql="select huma_id from huma_mstr";
		try {
			System.out.println("creating statement..");
			JdbcConnect jc=new JdbcConnect();
			Connection con=jc.getConnection();
			////log.info("JDBC Connection was created");
			Statement st=con.createStatement();
			System.out.println("statement created...");
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			//System.out.println(rs.getString(1));
			data=data+rs.getString(1)+"::::::";
			System.out.println(data);
			 	//response.getWriter().println(data);
			con.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn("",e);
			//e.printStackTrace();
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
