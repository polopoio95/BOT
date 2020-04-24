package bot.data.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBupdate {

	private Connection conn;
	private PreparedStatement pst;

	public DBupdate() throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		conn = DriverManager.getConnection(url, "KIWEON", "260307");
		System.out.println("DB 접속 성공");

	}

	public void update(String[] wordList, String[] accList) throws ClassNotFoundException, SQLException {

		
		for(int i=0; i < wordList.length; i++) {
		
			StringBuilder appsql = new StringBuilder();

			appsql.append("UPDATE TEST SET COUNT = '")
			.append(accList[i])
			.append("' WHERE WORD = '")
			.append(wordList[i])
			.append("'");
			
			String sql = appsql.toString();

			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			System.out.println("DB 연결 완료");

			int r = pst.executeUpdate(sql);

			if (r > 0) {
				System.out.println("삽입 성공");
				
			} else {
				System.out.println("삽입 실패");
			}

			
		}
		
		
		pst.close();
		conn.close();
		
		System.out.println("저장을 완료하였습니다");

	}

}
