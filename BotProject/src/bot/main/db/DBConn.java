package bot.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConn {
	
	// INSERT문 크롤링 저장 테이블 : KIWEONTEST
	public void interdata(String word, String attr, String content) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");

		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");

		String sql = "INSERT INTO MAINWORDSTORAGE VALUES(?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, word);
		st.setString(2, attr);
		st.setString(3, content);

		st.executeUpdate();
		st.close();
		con.close();

	}

	// SELECT문 데이터 가져오기 테이블 : MAINWORDSTORAGE
	public String[] selectdata() throws ClassNotFoundException, SQLException {

		List<String> sarr = new ArrayList<String>();

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");
		String sql = "SELECT * FROM MAINWORDSTORAGE";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			//한글을 제외한 나머지 것들 빈칸 처리 후 하다하다를 하다로 바꾸기
			String str = rs.getString("WORD");
			String sstr = str.replaceAll("[^\\uAC00-\\uD7A3]", "");
			String ssstr = sstr.replace("하다하다", "하다");

			try {

				if (!sarr.contains(ssstr)) {
					sarr.add(ssstr);
				}

			} catch (Exception e) {
				continue;

			}

		}
		String[] strarr = sarr.toArray(new String[sarr.size()]);

		rs.close();
		st.close();
		con.close();
		return strarr;

	}

}
