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

	public void UpPointupdate(String[] list) throws ClassNotFoundException, SQLException {

		DBupdate update = new DBupdate();
		String sql = update.makeUpSQL(list);

		try {

			pst = conn.prepareStatement(sql);
			System.out.println("DB 연결 완료");

			int r = pst.executeUpdate(sql);

			if (r > 0) {
				System.out.println("삽입 성공");

			} else {
				System.out.println("삽입 실패");
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {

			pst.close();
			conn.close();

		}

		System.out.println("정확성 + 저장을 완료하였습니다");

	}

	public void DownPointupdate(String[] list) throws ClassNotFoundException, SQLException {

		DBupdate update = new DBupdate();
		String sql = update.makeDownSQL(list);

		try {

			pst = conn.prepareStatement(sql);
			System.out.println("DB 연결 완료");

			int r = pst.executeUpdate(sql);

			if (r > 0) {
				System.out.println("삽입 성공");

			} else {
				System.out.println("삽입 실패");
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {

			pst.close();
			conn.close();

		}

		System.out.println("정확성 - 저장을 완료하였습니다");

	}

	private String makeUpSQL(String[] upPoint) {

		StringBuilder appsql = new StringBuilder();

		appsql.append("UPDATE NOUN SET ACCURACY = ACCURACY + 1 ");
		appsql.append("WHERE WORD = '" + upPoint[0] + "'");

		for (int i = 1; i < upPoint.length; i++) {
			appsql.append(" OR WORD = '" + upPoint[i] + "'");

		}

		String sql = appsql.toString();

		return sql;

	}

	private String makeDownSQL(String[] downPoint) {

		StringBuilder appsql = new StringBuilder();

		appsql.append("UPDATE NOUN SET ACCURACY = ACCURACY - 1 ");
		appsql.append("WHERE WORD = '" + downPoint[0] + "'");

		for (int i = 1; i < downPoint.length; i++) {
			appsql.append(" OR WORD = '" + downPoint[i] + "'");

		}

		String sql = appsql.toString();
		System.out.println(sql);

		return sql;

	}

}
