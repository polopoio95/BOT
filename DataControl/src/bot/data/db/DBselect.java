package bot.data.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBselect {

	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private int linecount = 0;

	// 생성자 String selectsql = "SELECT WORD FROM TABLENAME";
	public DBselect(String sql) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		conn = DriverManager.getConnection(url, "KIWEON", "260307");
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery(sql);

	}

	public void select() throws ClassNotFoundException, SQLException {
		List<String> lists = new ArrayList<String>();
		while (rs.next()) {
			// WORD 열에서 한글만 파싱해오는것
			String word = rs.getString("WORD");
			String attr = rs.getString("ATTR");
			String content = rs.getString("MEAN");

			System.out.println(word);
			System.out.println(attr);
			System.out.println(content);

			try {

				if (!lists.contains(word)) {
					lists.add(word);
					linecount++;
				}

			} catch (Exception e) {
				// System.out.println(e);
				continue;
			} finally {

			}

		}
		System.out.println("");
		System.out.println(lists);
		System.out.println("\n데이터를 가져왔습니다.");
		System.out.println("데이터는 총 " + linecount + "개 입니다.\n");

		rs.close();
		pst.close();
		conn.close();

	}

	// 반환 값이 String 배열
	public String[] selectWord() throws ClassNotFoundException, SQLException {
		List<String> lists = new ArrayList<String>();
		while (rs.next()) {
			// WORD 열에서 한글만 파싱해오는것
			String word = rs.getString("WORD");

			linecount++;
			lists.add(word);

		}
		System.out.println("데이터는 총 " + linecount + "개 입니다.\n");

		String[] wordList = lists.toArray(new String[lists.size()]);

		rs.close();
		pst.close();
		conn.close();

		return wordList;

	}

	public String[] selectAccuracy() throws ClassNotFoundException, SQLException {
		List<String> lists = new ArrayList<String>();

		while (rs.next()) {
			// WORD 열에서 한글만 파싱해오는것
			String account = rs.getString("ACCURACY");

			linecount++;
			lists.add(account);

		}
		System.out.println("데이터는 총 " + linecount + "개 입니다.\n");

		String[] accountList = lists.toArray(new String[lists.size()]);

		rs.close();
		pst.close();
		conn.close();

		return accountList;

	}

	public String[] selectNoun() throws ClassNotFoundException, SQLException {

		List<String> lists = new ArrayList<String>();
		String slash = "/";

		while (rs.next()) {
			// WORD 열에서 한글만 파싱해오는것
			String word = rs.getString("WORD");
			String acc = rs.getString("ACCURACY");

			String nounAcc = word + slash + acc;

			lists.add(nounAcc);
		}
		System.out.println("\n데이터를 가져왔습니다.");

		String[] wordListS = lists.toArray(new String[lists.size()]);

		for (int i = 0; i < wordListS.length; i++) {
			System.out.println(wordListS[i]);
		}

		rs.close();
		pst.close();
		conn.close();

		return wordListS;

	}

	// 필터
	public String filter(String word) {
		// String[] sstr = str.split("\\(");

		String filterOne = word.replaceAll("[^\\uAC00-\\uD7A3]", "");
		String filterWord = filterOne.replace("하다하다", "하다");

		return filterWord;

	}

}
