package frs_cls;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
public class JdbcConnect { 


       //comment added by Rajesh
       Connection con=null;
       private Logger log = Logger.getLogger("LOGFILE");
       private Properties configProp = new Properties();
       public Connection getConnection() throws ClassNotFoundException
       {
              try {
                     
                    // //System.out.println("inside the JdbcConnect java file");   
            	     
                     //InputStream in = this.getClass().getResourceAsStream("/prop/Credentials.properties");
                     InputStream in = this.getClass().getResourceAsStream("/prop/Credentials.properties");
                     try {
                         configProp.load(in);
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                    /*
                     System.out.println("From the propertis file "+configProp.getProperty("hello.world"));
                     System.out.println("driver = "+configProp.getProperty("hello.world"));
                    */ 
                    String driver = configProp.getProperty("jdbc.driver");
                     String url = configProp.getProperty("jdbc.url");
                     String username = configProp.getProperty("jdbc.username");
                     String password = configProp.getProperty("jdbc.password");
                     
                   /*  System.out.println("driver="+driver);
                     System.out.println("url="+url);
                     System.out.println("username="+username);
                     System.out.println("Password="+password);*/
                     
                     BasicDataSource bds=new BasicDataSource();
                     
     /*                bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
                     bds.setUrl("jdbc:oracle:thin:@172.16.1.94:1521:XE");
                     bds.setUsername("frs_test2");
                     bds.setPassword("frs_test2");*/
                     
                     
                     bds.setDriverClassName(driver);
                     bds.setUrl(url); 
                     bds.setUsername(username);
                     bds.setPassword(password);
                     
                     con=bds.getConnection();
                     /*
                     InputStream in = this.getClass().getResourceAsStream("/frs_cls/DBDetails.properties");
                     try {
                         configProp.load(in);
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                     System.out.println("From the propertis file "+configProp.getProperty("hello.world"));
                     */
                     
                     
              } catch (SQLException e) {
                     log.warn("",e);
                     e.printStackTrace();
              }
              return con;
       }
}
