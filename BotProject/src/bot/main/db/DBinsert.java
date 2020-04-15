package bot.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBinsert {

	private Connection conn;
	private PreparedStatement pst;
	private int linecount = 0;

	public DBinsert(String sql) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		conn = DriverManager.getConnection(url, "KIWEON", "260307");
		System.out.println("DB ���� ����");

		pst = conn.prepareStatement(sql);
		System.out.println("���� �غ� �Ϸ�");

		// ResultSet rs = pst.executeQuery(sql);

	}

	// INSERT INTO TABLENAME VALUES(?)
	public void insertOne(String[] str) throws ClassNotFoundException, SQLException {

		for (int i = 0; i < str.length; i++) {
			pst.setString(1, str[i]);
			pst.executeUpdate();
			System.out.println(str[i]);
			linecount++;
		}

		System.out.println("������ �Ϸ��Ͽ����ϴ�. ����� �ܾ�� �� " + linecount + "�� �Դϴ�");

		pst.close();
		conn.close();

	}

	
	/*public void insertTwo(String[] str) throws ClassNotFoundException, SQLException {

		for (int i = 0; i < str.length; i++) {
			pst.setString(1, str[i]);
			pst.setString(2, str[i]);
			pst.executeUpdate();
			System.out.println(str[i]);
			linecount++;
		}

		System.out.println("������ �Ϸ��Ͽ����ϴ�. ����� �ܾ�� �� " + linecount + "�� �Դϴ�");

		pst.close();
		conn.close();

	}*/

	// INSERT INTO JOSAWORD VALUES(?, ?, ?)
	public void insertThree(String[] wordLists) throws ClassNotFoundException, SQLException {

		for (int i = 0; i < wordLists.length; i++) {

			String[] sscut = wordLists[i].split("��");
			System.out.println(wordLists[i]);

			for (int j = 0; j < sscut.length; j++) {

				pst.setString(j + 1, sscut[j]);

			}
			pst.executeUpdate();

		}
		pst.close();
		conn.close();

		System.out.println("����Ǿ����ϴ�");

	}

}
