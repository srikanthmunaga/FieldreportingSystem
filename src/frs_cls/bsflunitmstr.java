package frs_cls;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class bsflunitmstr {
	

	String BSFLUNIT_UCODE;
	String BSFLUNIT_NAME;
	String AREA_ID;
	String StriHUMA_ID;
	String BSFLUNIT_EMAIL;
	String BSFLUNIT_PHONE;
	String BSFLUNIT_MOBILE;
	Boolean b=false;
	public String getBSFLUNIT_UCODE() {
		return BSFLUNIT_UCODE;
	}
	public void setBSFLUNIT_UCODE(String bSFLUNIT_UCODE) {
		BSFLUNIT_UCODE = bSFLUNIT_UCODE;
	}
	public String getBSFLUNIT_NAME() {
		return BSFLUNIT_NAME;
	}
	public void setBSFLUNIT_NAME(String bSFLUNIT_NAME) {
		BSFLUNIT_NAME = bSFLUNIT_NAME;
	}
	public String getAREA_ID() {
		return AREA_ID;
	}
	public void setAREA_ID(String aREA_ID) {
		AREA_ID = aREA_ID;
	}
	public String getStriHUMA_ID() {
		return StriHUMA_ID;
	}
	public void setStriHUMA_ID(String striHUMA_ID) {
		StriHUMA_ID = striHUMA_ID;
	}
	public String getBSFLUNIT_EMAIL() {
		return BSFLUNIT_EMAIL;
	}
	public void setBSFLUNIT_EMAIL(String bSFLUNIT_EMAIL) {
		BSFLUNIT_EMAIL = bSFLUNIT_EMAIL;
	}
	public String getBSFLUNIT_PHONE() {
		return BSFLUNIT_PHONE;
	}
	public void setBSFLUNIT_PHONE(String bSFLUNIT_PHONE) {
		BSFLUNIT_PHONE = bSFLUNIT_PHONE;
	}
	public String getBSFLUNIT_MOBILE() {
		return BSFLUNIT_MOBILE;
	}
	public void setBSFLUNIT_MOBILE(String bSFLUNIT_MOBILE) {
		BSFLUNIT_MOBILE = bSFLUNIT_MOBILE;
	}
	
	public boolean validateUNIT(String Unitcode,Connection con)
	{	
		String sql="select * from bsflunit_mstr where bsflunit_ucode='"+Unitcode+"'";
		Statement svst1;
		try {
			svst1 = con.createStatement();
			ResultSet svrs1 = svst1.executeQuery(sql);
			if (!svrs1.next()) {
				b=false;
			}//if
			else {
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	
	}
	
	
}
