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
			//WORD 열에서 한글만 파싱해오는것
			String str = rs.getString("WORD");
			String sstr = str.replaceAll("[^\\uAC00-\\uD7A3]", "");
			String ssstr = sstr.replace("하다하다", "하다");

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
		System.out.println("\n데이터를 가져왔습니다.");
		System.out.println("데이터는 총 " + linecount + "개 입니다.\n");
		
		rs.close();
		pst.close();
		conn.close();

	}
	
	public void select() throws ClassNotFoundException, SQLException {


		while (rs.next()) {
			//WORD 열에서 한글만 파싱해오는것
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
		System.out.println("\n데이터를 가져왔습니다.");
		System.out.println("데이터는 총 " + linecount + "개 입니다.\n");
		
		rs.close();
		pst.close();
		conn.close();

	}
	
	//
	public String[] selectfilterString() throws ClassNotFoundException, SQLException {
		
		while (rs.next()) {
			//WORD 열에서 한글만 파싱해오는것
			String str = rs.getString("WORD");
			String sstr = str.replaceAll("[^\\uAC00-\\uD7A3]", "");
			String ssstr = sstr.replace("하다하다", "하다");

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
		System.out.println("\n데이터를 가져왔습니다.");
		System.out.println("데이터는 총 " + linecount + "개 입니다.\n");
		
		String[] wordListS = wordList.toArray(new String[wordList.size()]);

		rs.close();
		pst.close();
		conn.close();

		return wordListS;

	}

}
