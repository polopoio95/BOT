package bot.main.talk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TalkSentence {

	private void wordset() throws SQLException, ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");
		String sql = "SELECT WORD FROM KIWEONTEST WHERE ATTR = '����'";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String str = rs.getString("ATTR");
			

			try {

			} catch (Exception e) {
				continue;

			}

		}

		rs.close();
		st.close();
		con.close();

	}
	
	public void talk() {
		  
	}
	
	//��� ����� ���� �࿡�� �ܾ �̾ƿ��� ��
	public void selectattr() throws ClassNotFoundException, SQLException {

		
		List<String> sarr = new ArrayList<String>();
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");
		//String sql = "SELECT * FROM KIWEONTEST";
		String sql = "SELECT WORD FROM KIWEONTEST WHERE ATTR = '�����'";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery(sql);

		

		while (rs.next()) {
			String str = rs.getString("WORD");
			// str.replaceAll("[^\\uAC00-\\uD7A3]", ""); �ѱ����� ��� ���� ����
			// String clean = "^[��-�R]*$";
			String sstr = str.replaceAll("[^\\uAC00-\\uD7A3]", "");
			String ssstr = sstr.replace("�ϴ��ϴ�", "�ϴ�");

			try {
				// System.out.println(sstr);
				//System.out.println(ssstr);
				if(!sarr.contains(ssstr)) {
					sarr.add(ssstr);
				}
				
				
			} catch (Exception e) {
				//System.out.println(e);
				continue;
			} finally {

			}

		}
		System.out.println(sarr);


		rs.close();
		st.close();
		con.close();

		
		
		
		
	}

}
