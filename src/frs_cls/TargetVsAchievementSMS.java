package frs_cls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TargetVsAchievementSMS {

	/*
	 * //http://sms.sperrysms.in/WebServiceSMS.aspx?User=Basix &passwd=22285224&
	 * mobilenumber=9396812884& message=hi-hello5& sid=BASIXN&mtype=N
	 */
	//private Properties configProp = new Properties();
	private static Properties configProp = new Properties();
	/*public static final String domainNameUrl = "http://sms.sperrysms.in/WebServiceSMS.aspx";
	public static final String user = "Basix-testing";//Basix
	public static final String passwd = "22285224";
	public static final String sid = "BASIXN";
	public static final String mtype = "N";
	
	public static final String domainNameUrl = configProp.getProperty("sms.domainNameUrl");
	public static final String user = configProp.getProperty("sms.user");
	public static final String passwd = configProp.getProperty("sms.passwd");
	public static final String sid = configProp.getProperty("sms.sid");
	public static final String mtype = configProp.getProperty("sms.mtype");
*/
	private Log smslog = LogFactory.getLog("SMSTRACK1");

	public void sendTargetVsAchievementSMS(String mobilenumber, String message, String whoseSms) {
		//message="Hi_SMSreceiver_";
		//message= URLEncoder.encode(message, "UTF-8");
		
		//Getting the Details from the Properties file
		InputStream inn = this.getClass().getResourceAsStream("/prop/Credentials.properties");
        try {
        	//System.out.println("configProp="+configProp);
        	//System.out.println("inn="+inn);
            configProp.load(inn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String domainNameUrl = configProp.getProperty("sms.domainNameUrl");
        String user = configProp.getProperty("sms.user");
        String passwd = configProp.getProperty("sms.passwd");
        String sid = configProp.getProperty("sms.sid");
        String mtype = configProp.getProperty("sms.mtype");
        
        
		String urlString;
		try {
			urlString = domainNameUrl+"?User="+user+"&passwd="+passwd+"&mobilenumber="+mobilenumber.trim()+"&message="+URLEncoder.encode(message, "UTF-8")+"&sid="+sid+"&mtype="+mtype;
		
		//System.out.println("urlString="+	urlString);
		URL url = new URL(urlString);

			URLConnection urlConn = url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));

			String line = "";
			while ((line = in.readLine()) != null) {
				
				//System.out.println(line);
				smslog.info(line + "; "+whoseSms+" Massage="
						+ message + "; Mobilenumber=" + mobilenumber);
				System.out.println(line + "; "+whoseSms+" Massage="
						+ message + "; Mobilenumber=" + mobilenumber);
//				System.out.println(line + "; Massage="
//						+ message + "; Mobilenumber=" + mobilenumber);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		TargetVsAchievementSMS tas = new TargetVsAchievementSMS();

		//tas.sendTargetVsAchievementSMS("8125822187","test this sms is goingor not");
		

		//tas.sendTargetVsAchievementSMS(" 9396812884", "hellow_ map7");

	}
}
