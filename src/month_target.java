import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import frs_cls.JdbcConnect;


public class month_target {
	public static BufferedWriter out = null;
	private static Properties configProp = new Properties();
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		JdbcConnect jc = new JdbcConnect();
		
		
		String msg = null;
		Date today = new Date();
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("ddMMyyyy");
		String date = DATE_FORMAT.format(today);
		String path="c:/";
		System.out.println("Today in dd-MM-yyyy format : " + date);
		String filename = "LSRMonthSMS" + date + ".log";
		File file1 = new File(path + "log");
		File file = new File(path + "log/" + filename);
		try {
			if (!file.exists()) {
				file1.mkdirs();
				file.createNewFile();

			}
			System.out.println("file is : "+path + "log/" + filename);
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path + "log/" + filename, true),"UTF-8");
			out = new BufferedWriter(writer);
			
			//out.write("hi");
			
			out.newLine();
			
			


		} catch (IOException e) {
			out.write(e.getMessage());
		}
		
		try {
			Connection con=jc.getConnection();
			
			//String sql = "select  HUMA_NAME , MOBILENUMBER ,WEEK_TAR ,MONTH_TAR  from MONTH_TARGET";
			PreparedStatement ps1 = con.prepareStatement("select  HUMA_NAME , MOBILENUMBER ,WEEK_TAR ,MONTH_TAR  from MONTH_TARGET");
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
			//System.out.println(rs.getString(1).trim()+"\t"+rs.getString(2).trim()+"\t"+rs.getString(3).trim()+"\t"+rs.getString(4).trim());
			//out.write("")
			String msg1="Dear "+rs.getString(2).trim()+" , your target for the month of Dec 2013" +" is Rs "+rs.getString(4).trim()+".00 and this week ending on Dec 7, 13 is Rs "+rs.getString(3).trim()+".00. " +
					"Please focus on OD collections from A & B category customers";
			System.out.println(msg1);
			String line="";
			sendTargetVsAchievementSMS(rs.getString(1).trim(),msg1,rs.getString(4).trim(),rs.getString(3).trim());
			//out.write("[" + new Date() + "]; " + line+ "; Mobile Number= "+ rs.getString(1).trim() + "; Message= " + msg1+ "; Month target= " + rs.getString(4).trim()+ "; week target= " +rs.getString(3).trim());
			//out.newLine();
			}
			
			out.close();
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
	}
	public static void sendTargetVsAchievementSMS(String mobilenumber,String message, String mt,String wt)
			throws IOException {
		/*//InputStream in = month_target..getResourceAsStream("/prop/Credentials.properties");
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
		String urlString;
        String domainNameUrl = "http://sms.sperrysms.in/WebServiceSMS.aspx";//configProp.getProperty("sms.domainNameUrl");
        String user ="Basix"; //configProp.getProperty("sms.user");
        String passwd ="22285224"; //configProp.getProperty("sms.passwd");
        String sid = "BASIXN";//configProp.getProperty("sms.sid");
        String mtype ="N"; //configProp.getProperty("sms.mtype");
		try {
			urlString = domainNameUrl + "?User=" + user + "&passwd=" + passwd
					+ "&mobilenumber=" + mobilenumber.trim() + "&message="
					+ URLEncoder.encode(message, "UTF-8") + "&sid=" + sid
					+ "&mtype=" + mtype;

			System.out.println("urlString=" + urlString);
			URL url = new URL(urlString);

			URLConnection urlConn = url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));

			String line = "";
			while ((line = in.readLine()) != null) {

				// System.out.println(line);
				// smslog.info(line + "; Massage="+ message + "; Mobilenumber="
				// + mobilenumber);
				// System.out.println("["+new
				// Date()+"]; "+line+"; Mobile Number= "+mobilenumber+"; Unit Name="+unitname+"-"+unitcide+"; Message= "+message);
				out.write("[" + new Date() + "]; " + line + "; Mobile Number= "+ mobilenumber + "; Message= " + message+ "; Month target= " + mt+ "; week target= " +wt);
				out.newLine();
				//out.close();
			}
		} catch (MalformedURLException e) {
			out.write(e.getMessage());
		} catch (IOException e) {
			out.write(e.getMessage());
		} catch (Exception e) {
			out.write(e.getMessage());
		}

	}

}
