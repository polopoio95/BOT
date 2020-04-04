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

public class TestPro {
	
	private static List<String> sarr = new ArrayList<String>();

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		TestPro tw = new TestPro();
		String url = "C:\\Users\\kiwon\\Desktop\\koreadic\\KorStems\\WordList\\total_word.txt";
		
		String[] str = tw.fileRead(url);
		tw.insert(str);
		
		

	}
	
	// INSERT문 저장 테스트용 테이블 : WORDSTORAGE, MAINWORDSTORAGE
		public void insert() throws ClassNotFoundException, SQLException {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
			Connection con = DriverManager.getConnection(url, "KIWEON", "260307");

			String sql = "INSERT INTO WORDSTORAGE VALUES(?)";
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, "아니");
			// st.setString(2, "봇에선");
			// st.setString(3, "왜 안되는거냐");

			int count = st.executeUpdate();
			System.out.println(count);

			st.close();
			con.close();

		}

		// INSERT문 단어 저장 테이블 : WORDSTORAGE 현재 total_word를 넣기 위해 사용중
		public void insert(String[] str) throws ClassNotFoundException, SQLException {

			int linecount = 0;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
			Connection con = DriverManager.getConnection(url, "KIWEON", "260307");
			System.out.println("연결 성공");

			String sql = "INSERT INTO WORDSTORAGE VALUES(?)";
			PreparedStatement st = con.prepareStatement(sql);
			System.out.println("연결 성공");
			
			for(int i = 0; i < str.length; i++) {
				st.setString(1, str[i]);
				st.executeUpdate();
				System.out.println(str[i]);
				linecount++;
			}
			
			System.out.println("저장을 완료하였습니다. 저장된 단어는 총 " + linecount + "개 입니다");

			st.close();
			con.close();

		}

		// INSERT문 어미 저장 테이블 : JOSAWORD
		public void insert(String strs, String strn) throws ClassNotFoundException, SQLException {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
			Connection con = DriverManager.getConnection(url, "KIWEON", "260307");
			System.out.println("연결성공");

			String sql = "INSERT INTO JOSAWORD VALUES(?, ?)";
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, strs);
			st.setString(2, strn);

			st.executeUpdate();

			st.close();
			con.close();

		}

		// SELECT문 명사, 동사, 형용사 별로 단어 가져오기 테이블 : MAINWORDSTORAGE - > 문장을 구사할 때 쓰임 - > 추후
		// NUR + JOSA 한 단어 테이블에서 파싱할 것
		public void selectparse() throws ClassNotFoundException, SQLException {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
			Connection con = DriverManager.getConnection(url, "KIWEON", "260307");
			// String sql = "SELECT * FROM KIWEONTEST";
			String sql = "SELECT WORD FROM MAINWORDSTORAGE WHERE ATTR = '형용사'";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				String str = rs.getString("WORD");
				// str.replaceAll("[^\\uAC00-\\uD7A3]", ""); 한극제외 모든 문자 삭제
				// String clean = "^[가-R]*$"; 
				String sstr = str.replaceAll("[^\\uAC00-\\uD7A3]", "");
				String ssstr = sstr.replace("하다하다", "하다");

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

		// SELECT문 단어 가져오기 테이블 : WORDSTORAGE - > JOSAWORD에 있는 단어들과 합쳐서 단어를 만들 때 쓰임
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
				// str.replaceAll("[^\\uAC00-\\uD7A3]", ""); 한극제외 모든 문자 삭제
				// String clean = "^[가-R]*$";
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

		// SELECT문 어미 가져오기 테이블 : JOSAWORD - > WORDSTORAGE에 있는 단어들과 합쳐서 단어를 만들 때 쓰임
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
				// str.replaceAll("[^\\uAC00-\\uD7A3]", ""); 한극제외 모든 문자 삭제
				// String clean = "^[가-R]*$";
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
	
	// 텍스트 파일을 url 파라미터 입력한 후 가져오는것
		public static String[] fileRead(String url) throws IOException {

			// 메소드 가져올  추후 까먹었을 때를 대비하여 링크 달아놓을 것
			BufferedReader br = new BufferedReader(new FileReader(url));
			String s;

			while ((s = br.readLine()) != null) {
				sarr.add(s);

			}
			br.close();

			// 리스트를 스트링배열로 바꾸는 법
			String[] starr = sarr.toArray(new String[sarr.size()]);

			return starr;

		}

		// 텍스트 파일 데이터 읽기
		public void txtreader(String url) throws IOException {
			int linecount = 0;
			// 파일 url에서 한줄한줄 읽어오는것
			String[] str = fileRead(url);
			for (int i = 0; i < str.length; i++) {

				System.out.println(str[i]);
				linecount++;

			}
			
			System.out.println("\n완료되었습니다");
			System.out.println("단어는 총 " + linecount + "개.");

		}
		
		public void parseinsert() {
			
		}


}
