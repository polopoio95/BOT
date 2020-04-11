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

	//������ String selectsql = "SELECT WORD FROM TESTTABLE";
	public TestSelect(String sql) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		conn = DriverManager.getConnection(url, "KIWEON", "260307");
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery(sql);

	}

	public void select() throws ClassNotFoundException, SQLException {

		while (rs.next()) {
			// WORD ������ �ѱ۸� �Ľ��ؿ��°�
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
		System.out.println("\n�����͸� �����Խ��ϴ�.");
		System.out.println("�����ʹ� �� " + linecount + "�� �Դϴ�.\n");

		rs.close();
		pst.close();
		conn.close();

	}

	// ��ȯ ���� String �迭
	public String[] selectString() throws ClassNotFoundException, SQLException {

		while (rs.next()) {
			// WORD ������ �ѱ۸� �Ľ��ؿ��°�
			String str = rs.getString("WORD");
			String[] sstr = str.split("\\(");
			String ssstr = sstr[0].replaceAll("[^\\uAC00-\\uD7A3]", "");
			String sssstr = ssstr.replace("�ϴ��ϴ�", "�ϴ�");

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
		System.out.println("\n�����͸� �����Խ��ϴ�.");
		System.out.println("�����ʹ� �� " + linecount + "�� �Դϴ�.\n");

		String[] wordListS = wordList.toArray(new String[wordList.size()]);

		rs.close();
		pst.close();
		conn.close();

		return wordListS;

	}

	public String[] selectNoun() throws ClassNotFoundException, SQLException {
		
		String slash = "/";

		while (rs.next()) {
			// WORD ������ �ѱ۸� �Ľ��ؿ��°�
			String word = rs.getString("WORD");
			String acc = rs.getString("ACCURACY");
			
			String nounAcc = word + slash + acc;
			
			wordList.add(nounAcc);
		}
		System.out.println("\n�����͸� �����Խ��ϴ�.");

		String[] wordListS = wordList.toArray(new String[wordList.size()]);
		
		for(int i = 0; i < wordListS.length; i++) {
			System.out.println(wordListS[i]);
		}

		rs.close();
		pst.close();
		conn.close();

		return wordListS;

	}

	//����
	public String filter(String word) {

		String filterOne = word.replaceAll("[^\\uAC00-\\uD7A3]", "");
		String filterWord = filterOne.replace("�ϴ��ϴ�", "�ϴ�");

		return filterWord;

	}

}
