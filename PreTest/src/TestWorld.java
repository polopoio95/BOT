import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestWorld {
	// TestWorld tw = new TestWorld();
	// C:\Users\kiwon\Desktop\koreadic\KorStems\WordList\\word_???.txt;

	private static List<String> sarr = new ArrayList<String>();

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

		TestWorld tw = new TestWorld();
		String url = "C:\\Users\\kiwon\\Desktop\\koreadic\\KorStems\\WordList\\test_word.txt;";
		tw.txtreader(url);;

	}

	// INSERT�� ���� �׽�Ʈ�� ���̺� : WORDSTORAGE, MAINWORDSTORAGE
	public void insert() throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");

		String sql = "INSERT INTO WORDSTORAGE VALUES(?)";
		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, "�ƴ�");
		// st.setString(2, "������");
		// st.setString(3, "�� �ȵǴ°ų�");

		int count = st.executeUpdate();
		System.out.println(count);

		st.close();
		con.close();

	}

	// INSERT�� �ܾ� ���� ���̺� : WORDSTORAGE
	public void insert(String str) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");

		String sql = "INSERT INTO WORDSTORAGE VALUES(?)";
		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, str);

		st.executeUpdate();

		st.close();
		con.close();

	}

	// INSERT�� ��� ���� ���̺� : JOSAWORD
	public void insert(String strs, String strn) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");
		System.out.println("���Ἲ��");

		String sql = "INSERT INTO JOSAWORD VALUES(?, ?)";
		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, strs);
		st.setString(2, strn);

		st.executeUpdate();

		st.close();
		con.close();

	}

	// SELECT�� ���, ����, ����� ���� �ܾ� �������� ���̺� : MAINWORDSTORAGE - > ������ ������ �� ���� - > ����
	// NUR + JOSA �� �ܾ� ���̺��� �Ľ��� ��
	public void selectparse() throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");
		// String sql = "SELECT * FROM KIWEONTEST";
		String sql = "SELECT WORD FROM MAINWORDSTORAGE WHERE ATTR = '�����'";
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
				// System.out.println(ssstr);
				if (!sarr.contains(ssstr)) {
					sarr.add(ssstr);
				}

			} catch (Exception e) {
				// System.out.println(e);
				continue;
			} finally {

			}

		}
		System.out.println(sarr);

		rs.close();
		st.close();
		con.close();

	}

	// SELECT�� �ܾ� �������� ���̺� : WORDSTORAGE - > JOSAWORD�� �ִ� �ܾ��� ���ļ� �ܾ ���� �� ����
	public String[] selectwordstorage() throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");
		// String sql = "SELECT * FROM KIWEONTEST";
		String sql = "SELECT WORD FROM WORDSTORAGE";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String str = rs.getString("WORD");
			// str.replaceAll("[^\\uAC00-\\uD7A3]", ""); �ѱ����� ��� ���� ����
			// String clean = "^[��-�R]*$";
			String sstr = str.replaceAll("[^\\uAC00-\\uD7A3]", "");

			try {
				// System.out.println(sstr);
				// System.out.println(ssstr);
				String strs = rs.getString("WORD");
				sarr.add(strs);
				System.out.println(strs);

			} catch (Exception e) {
				// System.out.println(e);
				continue;
			} finally {

			}

		}
		System.out.println(sarr);
		String[] starr = sarr.toArray(new String[sarr.size()]);

		rs.close();
		st.close();
		con.close();

		return starr;

	}

	// SELECT�� ��� �������� ���̺� : JOSAWORD - > WORDSTORAGE�� �ִ� �ܾ��� ���ļ� �ܾ ���� �� ����
	public String[] selectjosaword() throws ClassNotFoundException, SQLException {

		List<String> satrr = new ArrayList<String>();

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");
		// String sql = "SELECT * FROM KIWEONTEST";
		String sql = "SELECT WORD FROM JOSAWORD";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String str = rs.getString("WORD");
			// str.replaceAll("[^\\uAC00-\\uD7A3]", ""); �ѱ����� ��� ���� ����
			// String clean = "^[��-�R]*$";
			String sstr = str.replaceAll("[^\\uAC00-\\uD7A3]", "");

			try {
				// System.out.println(sstr);
				// System.out.println(ssstr);
				String strs = rs.getString("WORD");
				satrr.add(strs);
				System.out.println(strs);

			} catch (Exception e) {
				System.out.println(e);
			} finally {

			}

		}
		System.out.println(satrr);
		String[] starr = satrr.toArray(new String[satrr.size()]);

		rs.close();
		st.close();
		con.close();

		return starr;

	}

	// �ؽ�Ʈ ������ url �Ķ���� �Է��� �� �������°�
	public static String[] fileRead(String name) throws IOException {

		// �޼ҵ� ������ �� ���� ��Ծ��� ���� ����Ͽ� ��ũ �޾Ƴ��� ��
		BufferedReader br = new BufferedReader(new FileReader(name));
		String s;

		while ((s = br.readLine()) != null) {
			sarr.add(s);

		}
		br.close();

		// ����Ʈ�� ��Ʈ���迭�� �ٲٴ� ��
		String[] starr = sarr.toArray(new String[sarr.size()]);

		return starr;

	}

	// �ؽ�Ʈ ���� ������ �б�
	public void txtreader(String url) throws IOException {

		// ���� url���� �������� �о���°�
		String[] str = fileRead(url);
		for (int i = 0; i < str.length; i++) {

			System.out.println(str[i]);

		}
		
		System.out.println("������ �Ϸ�Ǿ����ϴ�");

	}

	private void savemethod() {

		/*
		 * ��� + ���� String[] snw = tw.selectwordstorage(); ��� String[] sjw =
		 * tw.selectjosaword(); ����
		 * 
		 * for(int i = 0; i < snw.length; i++) { for(int j = 0; j < sjw.length; j++) {
		 * System.out.println(snw[i] + sjw[j]);
		 * 
		 * }
		 * 
		 * }
		 */

		/*
		 * ���� ���Ͽ��� ���� �����ϱ� String strtrim = ret[i].trim(); Ʈ������ �յ� ���� ���� String clear
		 * = strtrim.replaceFirst(" ", "/"); ù��° ���鸸 /�� ����� String clearr =
		 * clear.replace(" ", ""); ������ ������ ���� String[] splitcle = clearr.split("/"); /��
		 * �������� ���ڿ� �ڸ��� tw.insert(splitcle[0], splitcle[1]); �߸� ���ڿ� �迭
		 */

		/*
		 * // str.replaceAll("[^\\uAC00-\\uD7A3]", ""); �ѱ����� ��� ���� ���� �����ڵ�����
			// String clean = "^[��-�R]*$"; �ѱ�� ���� 
		 */
	}

}
