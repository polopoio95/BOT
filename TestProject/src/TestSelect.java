import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestSelect {

	private List<String> wordList = new ArrayList<String>();
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private int linecount = 0;

	// String selectsql = "SELECT WORD FROM TESTTABLE";

	public TestSelect(String sql) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		conn = DriverManager.getConnection(url, "KIWEON", "260307");
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery(sql);

	}

	// 새로운 검색을 하기 위해서 중복 제거
	public void selectFilter() throws ClassNotFoundException, SQLException {

		while (rs.next()) {
			// WORD 열에서 한글만 파싱해오는것
			String word = rs.getString("WORD");
			String filtedWord = filter(word);

			try {

				if (!wordList.contains(filtedWord)) {
					wordList.add(filtedWord);
					linecount++;
				}

			} catch (Exception e) {
				// System.out.println(e);
				continue;
			} finally {

			}

		}
		System.out.println("");
		System.out.println(wordList);
		System.out.println("\n데이터를 가져왔습니다.");
		System.out.println("데이터는 총 " + linecount + "개 입니다.\n");

		rs.close();
		pst.close();
		conn.close();

	}

	public void select() throws ClassNotFoundException, SQLException {

		while (rs.next()) {
			// WORD 열에서 한글만 파싱해오는것
			String word = rs.getString("WORD");
			String attr = rs.getString("ATTR");
			String content = rs.getString("MEAN");

			System.out.println(word);
			System.out.println(attr);
			System.out.println(content);

			try {

				if (!wordList.contains(word)) {
					wordList.add(word);
					linecount++;
				}

			} catch (Exception e) {
				// System.out.println(e);
				continue;
			} finally {

			}

		}
		System.out.println("");
		System.out.println(wordList);
		System.out.println("\n데이터를 가져왔습니다.");
		System.out.println("데이터는 총 " + linecount + "개 입니다.\n");

		rs.close();
		pst.close();
		conn.close();

	}

	// 반환 값이 String 배열
	public String[] selectfilterString() throws ClassNotFoundException, SQLException {

		while (rs.next()) {
			// WORD 열에서 한글만 파싱해오는것
			String str = rs.getString("WORD");
			String[] sstr = str.split("\\(");
			String ssstr = sstr[0].replaceAll("[^\\uAC00-\\uD7A3]", "");
			String sssstr = ssstr.replace("하다하다", "하다");

			try {

				if (!wordList.contains(sssstr)) {
					wordList.add(sssstr);
					linecount++;
				}

			} catch (Exception e) {
				// System.out.println(e);
				continue;
			} finally {

			}

		}
		System.out.println(wordList);
		System.out.println("\n데이터를 가져왔습니다.");
		System.out.println("데이터는 총 " + linecount + "개 입니다.\n");

		String[] wordListS = wordList.toArray(new String[wordList.size()]);

		rs.close();
		pst.close();
		conn.close();

		return wordListS;

	}

	public String[] selectString() throws ClassNotFoundException, SQLException {

		while (rs.next()) {
			// WORD 열에서 한글만 파싱해오는것
			String word = rs.getString("WORD");
			String wordFilt = filter(word);

			try {

				if (!wordList.equals(wordFilt)) {
					wordList.add(wordFilt);
					linecount++;
				}

			} catch (Exception e) {
				continue;

			}

		}
		System.out.println("\n데이터를 가져왔습니다.");
		System.out.println("데이터는 총 " + linecount + "개 입니다.\n");

		String[] wordListS = wordList.toArray(new String[wordList.size()]);

		rs.close();
		pst.close();
		conn.close();

		return wordListS;

	}

	public String[] selectMatch() throws ClassNotFoundException, SQLException {

		List<String> wordList = new ArrayList<String>();
		List<String> countList = new ArrayList<String>();
		List<String> totalList = new ArrayList<String>();

		while (rs.next()) {
			// WORD 열에서 한글만 파싱해오는것
			String word = rs.getString("WORD");
			wordList.add(word);
			String count = rs.getString("ACCURACY");
			countList.add(count);

		}

		String[][] wordCount = new String[wordList.size()][2];
		for (int i = 0; i < wordCount.length; i++) {
			
		}

		System.out.println("\n데이터를 가져왔습니다.");

		String[] wordListS = wordList.toArray(new String[wordList.size()]);

		rs.close();
		pst.close();
		conn.close();

		return wordListS;

	}

	public String filter(String word) {

		String filterOne = word.replaceAll("[^\\uAC00-\\uD7A3]", "");
		String filterWord = filterOne.replace("하다하다", "하다");

		return filterWord;

	}

}
