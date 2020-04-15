package bot.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBselect {

	private List<String> wordList = new ArrayList<String>();
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private int linecount = 0;

	public DBselect(String sql) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		conn = DriverManager.getConnection(url, "KIWEON", "260307");
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery(sql);

	}

	// word �ϳ�¥��
	public void select() throws ClassNotFoundException, SQLException {

		while (rs.next()) {
			String str = rs.getString("WORD");

			try {

				if (!wordList.contains(str)) {
					wordList.add(str);
					linecount++;
				}

			} catch (Exception e) {
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

	public void selectfilter() throws ClassNotFoundException, SQLException {

		while (rs.next()) {
			// WORD ������ �ѱ۸� �Ľ��ؿ��°�
			String str = rs.getString("WORD");
			String sstr = str.replaceAll("[^\\uAC00-\\uD7A3]", "");
			String ssstr = sstr.replace("�ϴ��ϴ�", "�ϴ�");

			try {

				if (!wordList.contains(ssstr)) {
					wordList.add(ssstr);
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

	// WORDSTORAGE ���̺��� ��Ʈ�� �迭�� ��ȯ
	public String[] selectWordStorage() throws ClassNotFoundException, SQLException {

		while (rs.next()) {
			try {

				String word = rs.getString("WORD");
				wordList.add(word);
				linecount++;

			} catch (Exception e) {
				System.out.println(e);
				continue;
			} finally {

			}

		}
		System.out.println("\n�����͸� �����Խ��ϴ�.");
		System.out.println("�����ʹ� �� " + linecount + "�� �Դϴ�.\n");

		String[] wordListS = wordList.toArray(new String[wordList.size()]);

		rs.close();
		pst.close();
		conn.close();

		return wordListS;

	}
	
	public void selectThree() throws ClassNotFoundException, SQLException {

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
	
	public void selectThrees() throws ClassNotFoundException, SQLException {

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

	
	//�ܾ� ����
	public String filter(String word) {

		String filterOne = word.replaceAll("[^\\uAC00-\\uD7A3]", "");
		String filterWord = filterOne.replace("�ϴ��ϴ�", "�ϴ�");

		return filterWord;

	}

}
