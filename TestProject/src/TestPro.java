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

	private List<String> sarr = new ArrayList<String>();

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		TestPro tw = new TestPro();
		TestCrawling tc = new TestCrawling();
		/*
		String[] list = {"사랑", "쥐다", "분노", "쥐어박다","콩팥", "크나크다", "분노", "타닥거리다","햇수", "탄탄하다", "후미지다", "파괴"};
		String insertsql = "INSERT INTO TESTTABLE VALUES(?)";
		tc.crawling(list, insertsql);*/
		
		String selectsql = "SELECT WORD FROM TESTTABLE";
		TestSelect ts = new TestSelect(selectsql);
		String[] dsp = ts.selectfilterString();
		for(int i = 0; i < dsp.length; i++) {
			System.out.println(dsp[i]);
		}
		
		String insertsql = "INSERT INTO TESTTABLEMAIN VALUES(?,?,?)";
		tc.crawling(dsp, insertsql);
		

	}

	public TestPro() {
		super();
		
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

	// INSERT문 단어 저장 테이블 : WORDSTORAGE 현재 total_word.txt를 넣기 위해 사용중
	public void insertWordStorage(String[] str) throws ClassNotFoundException, SQLException {

		int linecount = 0;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");
		System.out.println("연결 성공");

		String sql = "INSERT INTO WORDSTORAGE VALUES(?)";
		PreparedStatement st = con.prepareStatement(sql);
		System.out.println("연결 성공");

		for (int i = 0; i < str.length; i++) {
			st.setString(1, str[i]);
			st.executeUpdate();
			System.out.println(str[i]);
			linecount++;
		}

		System.out.println("저장을 완료하였습니다. 저장된 단어는 총 " + linecount + "개 입니다");

		st.close();
		con.close();

	}
	
	// INSERT문 단어 저장 테이블 : MAINWORDSTORAGE
	public void insertMainWordStorage(String word, String attr, String content) throws ClassNotFoundException, SQLException {

		int linecount = 0;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");

		String sql = "INSERT INTO MAINWORDSTORAGE VALUES(?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);

		//for (int i = 0; i < str.length; i++) {
		//	st.setString(1, str[i]);
		//	st.executeUpdate();
		//	System.out.println(str[i]);
		//	linecount++;
		//}

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
			String sstr = str.replaceAll("[^\\uAC00-\\uD7A3]", "");
			String ssstr = sstr.replace("하다하다", "하다");

			try {
		
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
	public String[] selectWordStorage() throws ClassNotFoundException, SQLException {
		
		List<String> wordList = new ArrayList<String>();
		int lineCount = 0;

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");
		String sql = "SELECT WORD FROM WORDSTORAGE";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			try {

				String word = rs.getString("WORD");
				wordList.add(word);
				lineCount++;

			} catch (Exception e) {
				System.out.println(e);
				continue;
			} finally {

			}

		}
		System.out.println("\n데이터를 가져왔습니다.");
		System.out.println("데이터는 총 " + lineCount + "개 입니다.\n");
		
		String[] wordListS = wordList.toArray(new String[wordList.size()]);

		rs.close();
		st.close();
		con.close();

		return wordListS;

	}

	// SELECT문 어미 가져오기 테이블 : JOSAWORD - > WORDSTORAGE에 있는 단어들과 합쳐서 단어를 만들 때 쓰임
	public String[] selectJosaWord() throws ClassNotFoundException, SQLException {

		List<String> josaList = new ArrayList<String>();
		int lineCount = 0;

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");
		String sql = "SELECT WORD FROM JOSAWORD";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			try {
				String word = rs.getString("WORD");
				josaList.add(word);
				//System.out.println(word);
				lineCount++;

			} catch (Exception e) {
				System.out.println(e);
			} finally {

			}

		}
		System.out.println("\n데이터를 가져왔습니다.");
		System.out.println("데이터는 총 " + lineCount + "개 입니다.\n");
		String[] josaListS = josaList.toArray(new String[josaList.size()]);

		rs.close();
		st.close();
		con.close();

		return josaListS;

	}

	// 텍스트 파일을 url 파라미터 입력한 후 가져오는것
	public static String[] fileRead(String url) throws IOException {
		
		List<String> arrList = new ArrayList<String>();

		// 메소드 가져올  추후 까먹었을 때를 대비하여 링크 달아놓을 것
		BufferedReader br = new BufferedReader(new FileReader(url));
		String s;

		while ((s = br.readLine()) != null) {
			arrList.add(s);

		}
		br.close();

		// 리스트를 스트링배열로 바꾸는 법
		String[] strList = arrList.toArray(new String[arrList.size()]);

		return strList;

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
