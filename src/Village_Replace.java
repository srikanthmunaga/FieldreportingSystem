import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import frs_cls.JdbcConnect;

public class Village_Replace {
	public static String vilages = "";

	public static void main(String[] args) {
		try {
			StringTokenizer st = null;
			Connection con = new JdbcConnect().getConnection();
			Connection con1 = new JdbcConnect().getConnection();
			int res = 0;
			PreparedStatement ps3 = con
					.prepareStatement("update WAR_TARGET set WAR_VILLAGES=? where WAR_SEQID=?");
			PreparedStatement ps1 = con1
					.prepareStatement("select TOUNIT, NEW_CODE from MERGE_JAVA_VILLAGES where FRUNIT=? and OLD_CODE=?");
			PreparedStatement ps = con
					.prepareStatement("select WAR_VILLAGES,(select h.Old_unit from TRANSFER_LSR h where HUMA_ID=w.HUMA_ID),WAR_SEQID from war_target w where w.HUMA_ID in (select distinct huma_id from TRANSFER_LSR)");
			int count = 0;
			ResultSet rs = ps.executeQuery();
			String tot_villages;
			String ucode;
			while (rs.next()) {
				count++;
				ucode = rs.getString(2);
				System.out.print("!!!!! Old_unit=" + ucode);
				tot_villages = rs.getString(1);
				System.out.print("===Old_villages=" + tot_villages);
				String new_vill = "";
				// System.out.println(ucode+"\t"+tot_villages);
				ResultSet rs1 = null;
				// if (tot_villages.contains("::")) {
				st = new StringTokenizer(tot_villages, "::");
				while (st.hasMoreElements()) {
					System.out.println("");
					String tockn=st.nextToken();
					//System.out.println("inside while loop");
					System.out.println("Checking entry Unit code and tockn="+ucode+"--"+tockn);
					ps1.setString(1, ucode);
					ps1.setString(2, tockn);
					rs1 = ps1.executeQuery();
					while (rs1.next()) {
						System.out.println("found entry for Unit code and tockn="+ucode+"--"+tockn);
						new_vill = new_vill + rs1.getString(2) + "::";
						
						System.out.print(" New_unit=" + rs1.getString(1));
						System.out.println("new_vill=" + rs1.getString(2));
						
					}
				}
				// }
				//System.out.println("new_vill=" + new_vill);
				if (new_vill.equals("")) {
					 } else {
						 
					new_vill = new_vill.substring(0, (new_vill.length()) - 2);
					System.out.println("new_vill2=" + new_vill);
					// rs1.getString(2)
					//System.out.print(" New_unit=" + rs1.getString(1));
					// tot_villages = rs.getString(1);
					System.out.print("===New_villages=" + new_vill);
					System.out.println("");
					ps3.setString(1, new_vill);
					ps3.setString(2, rs.getString(3));
					ps3.executeUpdate();
					res++;
				}

			}
			System.out.println("");
			System.out.println("count of while loop= : " + count);
			System.out.println("No of rows updated : " + res);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}