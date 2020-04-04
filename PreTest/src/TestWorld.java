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

public class TestWorld {
	// TestWorld tw = new TestWorld();
	// C:\Users\kiwon\Desktop\koreadic\KorStems\WordList\\word_???.txt;

	private static List<String> sarr = new ArrayList<String>();

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

		TestWorld tw = new TestWorld();
		String url = "C:\\Users\\kiwon\\Desktop\\koreadic\\KorStems\\WordList\\test_word.txt;";
		tw.txtreader(url);;

	}

	// INSERT¹® ÀúÀå Å×½ºÆ®¿ë Å×ÀÌºí : WORDSTORAGE, MAINWORDSTORAGE
	public void insert() throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");

		String sql = "INSERT INTO WORDSTORAGE VALUES(?)";
		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, "¾Æ´Ï");
		// st.setString(2, "º¿¿¡¼±");
		// st.setString(3, "¿Ö ¾ÈµÇ´Â°Å³Ä");

		int count = st.executeUpdate();
		System.out.println(count);

		st.close();
		con.close();

	}

	// INSERT¹® ´Ü¾î ÀúÀå Å×ÀÌºí : WORDSTORAGE
	public void insert(String str) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");

		String sql = "INSERT INTO WORDSTORAGE VALUES(?)";
		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, str);

		st.executeUpdate();

		st.close();
		con.close();

	}

	// INSERT¹® ¾î¹Ì ÀúÀå Å×ÀÌºí : JOSAWORD
	public void insert(String strs, String strn) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");
		System.out.println("¿¬°á¼º°ø");

		String sql = "INSERT INTO JOSAWORD VALUES(?, ?)";
		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, strs);
		st.setString(2, strn);

		st.executeUpdate();

		st.close();
		con.close();

	}

	// SELECT¹® ¸í»ç, µ¿»ç, Çü¿ë»ç º°·Î ´Ü¾î °¡Á®¿À±â Å×ÀÌºí : MAINWORDSTORAGE - > ¹®ÀåÀ» ±¸»çÇÒ ¶§ ¾²ÀÓ - > ÃßÈÄ
	// NUR + JOSA ÇÑ ´Ü¾î Å×ÀÌºí¿¡¼­ ÆÄ½ÌÇÒ °Í
	public void selectparse() throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Connection con = DriverManager.getConnection(url, "KIWEON", "260307");
		// String sql = "SELECT * FROM KIWEONTEST";
		String sql = "SELECT WORD FROM MAINWORDSTORAGE WHERE ATTR = 'Çü¿ë»ç'";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String str = rs.getString("WORD");
			// str.replaceAll("[^\\uAC00-\\uD7A3]", ""); ÇÑ±ØÁ¦¿Ü ¸ðµç ¹®ÀÚ »èÁ¦
			// String clean = "^[°¡-ÆR]*$"; 
			String sstr = str.replaceAll("[^\\uAC00-\\uD7A3]", "");
			String ssstr = sstr.replace("ÇÏ´ÙÇÏ´Ù", "ÇÏ´Ù");

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

	// SELECT¹® ´Ü¾î °¡Á®¿À±â Å×ÀÌºí : WORDSTORAGE - > JOSAWORD¿¡ ÀÖ´Â ´Ü¾îµé°ú ÇÕÃÄ¼­ ´Ü¾î¸¦ ¸¸µé ¶§ ¾²ÀÓ
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
			// str.replaceAll("[^\\uAC00-\\uD7A3]", ""); ÇÑ±ØÁ¦¿Ü ¸ðµç ¹®ÀÚ »èÁ¦
			// String clean = "^[°¡-ÆR]*$";
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

	// SELECT¹® ¾î¹Ì °¡Á®¿À±â Å×ÀÌºí : JOSAWORD - > WORDSTORAGE¿¡ ÀÖ´Â ´Ü¾îµé°ú ÇÕÃÄ¼­ ´Ü¾î¸¦ ¸¸µé ¶§ ¾²ÀÓ
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
			// str.replaceAll("[^\\uAC00-\\uD7A3]", ""); ÇÑ±ØÁ¦¿Ü ¸ðµç ¹®ÀÚ »èÁ¦
			// String clean = "^[°¡-ÆR]*$";
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

	// ÅØ½ºÆ® ÆÄÀÏÀ» url ÆÄ¶ó¹ÌÅÍ ÀÔ·ÂÇÑ ÈÄ °¡Á®¿À´Â°Í
	public static String[] fileRead(String name) throws IOException {

		// ¸Þ¼Òµå °¡Á®¿Ã ‹š ÃßÈÄ ±î¸Ô¾úÀ» ¶§¸¦ ´ëºñÇÏ¿© ¸µÅ© ´Þ¾Æ³õÀ» °Í
		BufferedReader br = new BufferedReader(new FileReader(name));
		String s;

		while ((s = br.readLine()) != null) {
			sarr.add(s);

		}
		br.close();

		// ¸®½ºÆ®¸¦ ½ºÆ®¸µ¹è¿­·Î ¹Ù²Ù´Â ¹ý
		String[] starr = sarr.toArray(new String[sarr.size()]);

		return starr;

	}

	// ÅØ½ºÆ® ÆÄÀÏ µ¥ÀÌÅÍ ÀÐ±â
	public void txtreader(String url) throws IOException {

		// ÆÄÀÏ url¿¡¼­ ÇÑÁÙÇÑÁÙ ÀÐ¾î¿À´Â°Í
		String[] str = fileRead(url);
		for (int i = 0; i < str.length; i++) {

			System.out.println(str[i]);

		}
		
		System.out.println("ÀúÀåÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù");

	}

	private void savemethod() {

		/*
		 * ¸í»ç + Á¶»ç String[] snw = tw.selectwordstorage(); ¸í»ç String[] sjw =
		 * tw.selectjosaword(); Á¶»ç
		 * 
		 * for(int i = 0; i < snw.length; i++) { for(int j = 0; j < sjw.length; j++) {
		 * System.out.println(snw[i] + sjw[j]);
		 * 
		 * }
		 * 
		 * }
		 */

		/*
		 * Á¶»ç ÆÄÀÏ¿¡¼­ ¶ç¾î¾²±â »èÁ¦ÇÏ±â String strtrim = ret[i].trim(); Æ®¸²À¸·Î ¾ÕµÚ °ø¹é »èÁ¦ String clear
		 * = strtrim.replaceFirst(" ", "/"); Ã¹¹øÂ° °ø¹é¸¸ /·Î ¸¸µé±â String clearr =
		 * clear.replace(" ", ""); ³ª¸ÓÁö °ø¹éÀº »èÁ¦ String[] splitcle = clearr.split("/"); /¸¦
		 * ±âÁØÀ¸·Î ¹®ÀÚ¿­ ÀÚ¸£±â tw.insert(splitcle[0], splitcle[1]); Àß¸° ¹®ÀÚ¿­ ¹è¿­
		 */

		/*
		 * // str.replaceAll("[^\\uAC00-\\uD7A3]", ""); ÇÑ±ØÁ¦¿Ü ¸ðµç ¹®ÀÚ »èÁ¦ À¯´ÏÄÚµåÇü½Ä
			// String clean = "^[°¡-ÆR]*$"; ÇÑ±¹¾î¸¸ °í¸£±â 
		 */
	}

}
