package frs_cls;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.TimerTask;

import autoMail.MailCompose;
public class MailToStatehead extends TimerTask {
    JdbcConnect jc=new JdbcConnect();
    Connection con;
   	MailCompose m=new MailCompose();
	String area_name;
	String uhemailid;
	String uname;
	String shemailid;
	String[] un;
	HashSet<String> hs=new HashSet<String>();;
	public void run()
	{
	Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    cal.add(Calendar.DATE, -1);
    String CurrentDate = sdf.format(cal.getTime());
    int a=cal.get(Calendar.DAY_OF_WEEK);
    System.out.println("week day number "+a);
    System.out.println(CurrentDate);
    cal.add(Calendar.DATE, -6);
    String PreviousDate=sdf.format(cal.getTime());
    System.out.println(PreviousDate);
    try {
    	if(a==1)  
    	{
		con=jc.getConnection();
		String ssql="select  distinct(AREA_ID) as area_id,(select HUMA_EMAIL from HUMA_MSTR where HUMA_ID=am.HUMA_ID) as HUMA_EMAIL,AREA_NAME ,am.HUMA_ID FROM AREA_MSTR am";
		/*Connection scon=jc.getConnection(); 
		Statement sst=scon.createStatement();*/
		con=jc.getConnection(); 
		Statement sst=con.createStatement();
		ResultSet srs=sst.executeQuery(ssql);
		while(srs.next())
		{
		String area_id=srs.getString(1);
		String area_huma_email=srs.getString(2);
		String area_name=srs.getString(3);
		String sid=srs.getString(4);
		String usql="select  BSFLUNIT_UCODE,BSFLUNIT_NAME,(select HUMA_EMAIL from HUMA_MSTR where HUMA_ID=bm.HUMA_ID) as HUMA_EMAIL,BSFLUNIT_MOBILE from BSFLUNIT_MSTR bm where AREA_ID='"+area_id+"' order by BSFLUNIT_NAME";
		/*Connection con1=jc.getConnection();
		Statement ust=con1.createStatement();
		*/
		con=jc.getConnection();
		Statement ust=con.createStatement();
		ResultSet urs=ust.executeQuery(usql);
		Statement st=con.createStatement();
		hs.clear();
		while(urs.next())
		{
			String BSFLUNIT_UCODE=urs.getString(1);
			uname=urs.getString(2);
			uhemailid=urs.getString(3);
			String sql="select BSFLUNIT_UCODE from UHLOG_SERVICE where BSFLUNIT_UCODE='"+BSFLUNIT_UCODE+"'and UHLOG_DATE  BETWEEN  TO_DATE('"+PreviousDate+"','DD-MM-YYYY') AND TO_DATE('"+CurrentDate+"','DD-MM-YYYY')";
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()==true){}
			else
			hs.add(BSFLUNIT_UCODE);
		}
		if(area_huma_email!=null)
		m.m1(hs,CurrentDate,PreviousDate,area_name,area_huma_email,sid); 
		}
    	}else{
		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}finally
	{ 
		
		 if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	}//finally

}
}
