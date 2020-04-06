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
	
	
	public TestSelect(String sql) throws ClassNotFoundException, SQLException {
		

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		conn = DriverManager.getConnection(url, "KIWEON", "260307");
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery(sql);
		
	}
	
	
	
	public void selectfilter() throws ClassNotFoundException, SQLException {


		while (rs.next()) {
			//WORD ������ �ѱ۸� �Ľ��ؿ��°�
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
	
	public void select() throws ClassNotFoundException, SQLException {


		while (rs.next()) {
			//WORD ������ �ѱ۸� �Ľ��ؿ��°�
			String str = rs.getString("WORD");
			
			try {
		
				if (!wordList.contains(str)) {
					wordList.add(str);
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
	
	//
	public String[] selectfilterString() throws ClassNotFoundException, SQLException {
		
		while (rs.next()) {
			//WORD ������ �ѱ۸� �Ľ��ؿ��°�
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
		System.out.println("\n�����͸� �����Խ��ϴ�.");
		System.out.println("�����ʹ� �� " + linecount + "�� �Դϴ�.\n");
		
		String[] wordListS = wordList.toArray(new String[wordList.size()]);

		rs.close();
		pst.close();
		conn.close();

		return wordListS;

	}

}
