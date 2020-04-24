package bot.data.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBinsert {
	

	private Connection conn;
	private PreparedStatement pst;
	private int linecount = 0;

	public void TestInsert(String sql) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		conn = DriverManager.getConnection(url, "KIWEON", "260307");
		System.out.println("DB 접속 성공");

		pst = conn.prepareStatement(sql);
		System.out.println("DB 연결 완료");

		// ResultSet rs = pst.executeQuery(sql);

	}

	// INSERT INTO TABLENAME VALUES(?)
	public void insert(String[] str) throws ClassNotFoundException, SQLException {

		for (int i = 0; i < str.length; i++) {
			pst.setString(1, str[i]);
			pst.executeUpdate();
			System.out.println(str[i]);
			linecount++;
		}

		System.out.println("저장을 완료하였습니다. 저장된 단어는 총 " + linecount + "개 입니다");

		pst.close();
		conn.close();

	}

	// INSERT INTO TABLENAME VALUES(?,?)
	public void insertTwoCraw(String[] str) throws ClassNotFoundException, SQLException {

		for (int i = 0; i < str.length; i++) {
			pst.setString(i + 1, str[i]);
			pst.executeUpdate();
			System.out.println(str[i]);
			linecount++;
		}

		System.out.println("저장을 완료하였습니다. 저장된 단어는 총 " + linecount + "개 입니다");

		pst.close();
		conn.close();

	}

	public void insertTwo(String[] str) throws ClassNotFoundException, SQLException {

		for (int i = 0; i < str.length; i++) {
			pst.setString(1, str[i]);
			pst.setString(2, str[i]);
			pst.executeUpdate();
			System.out.println(str[i]);
			linecount++;
		}

		System.out.println("저장을 완료하였습니다. 저장된 단어는 총 " + linecount + "개 입니다");

		pst.close();
		conn.close();

	}

	public void insertTwoCode(String codeName, String code) throws ClassNotFoundException, SQLException {

		pst.setString(1, codeName);
		pst.setString(2, code);

		pst.close();
		conn.close();

	}

	// INSERT INTO JOSAWORD VALUES(?,?,?)
	public void insertthree(String[] wordLists) throws ClassNotFoundException, SQLException {
		System.out.println(wordLists.length);

		for (int i = 0; i < wordLists.length; i++) {

			String[] sscut = wordLists[i].split("「");
			System.out.println(wordLists[i]);

			for (int j = 0; j < sscut.length; j++) {

				pst.setString(j + 1, sscut[j]);

			}
			pst.executeUpdate();

		}

		pst.close();
		conn.close();

		System.out.println("저장되었습니다");

	}

}
