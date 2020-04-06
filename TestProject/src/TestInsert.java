import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestInsert {
	
	private Connection conn;
	private PreparedStatement pst;
	private int linecount = 0;
	
	public TestInsert(String sql) throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		conn = DriverManager.getConnection(url, "KIWEON", "260307");
		System.out.println("���� ����");

		pst = conn.prepareStatement(sql);
		System.out.println("���� ����");
		
		//ResultSet rs = pst.executeQuery(sql);
		
	}
	
	//INSERT INTO TABLENAME VALUES(?)
	public void insert(String[] str) throws ClassNotFoundException, SQLException {
		
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
	
	//INSERT INTO TABLENAME VALUES(?,?)
	public void insertTwo(String[] str) throws ClassNotFoundException, SQLException {

		
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

	}
	
	//INSERT INTO JOSAWORD VALUES(?,?,?)
	public void insertthree(String[] wordLists) throws ClassNotFoundException, SQLException {

		for(int i = 0; i < wordLists.length; i++) {
			
			String[] sscut = wordLists[i].split("��");
			
			pst.setString(1, sscut[0]);
			pst.setString(2, sscut[1]);
			pst.setString(3, sscut[2]);

			pst.executeUpdate();
			
		}
		
		pst.close();
		conn.close();
		
		System.out.println("����Ǿ����ϴ�");
	

	}
	


}
