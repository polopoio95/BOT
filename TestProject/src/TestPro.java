import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestPro {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
		String url = "http://www.hani.co.kr/arti/politics/assembly/936619.html";
		String sql =  "SELECT WORD, ACCURACY FROM NOUN";
		TestSelect ts = new TestSelect(sql);
		String[] nounList = ts.selectNoun();
		
		TestCrawling tc = new TestCrawling();
		tc.crawlingMatch(nounList, sql, url);

	}

	// txtreader는 텍스트파일에서 잘 가져오나 확인하는 용도
	public static void txtreader(String url) throws IOException {


		int linecount = 0;
		// String url =
		// "C:\\Users\\kiwon\\Desktop\\koreadic\\KorStems\\WordList\\test_word.txt";

		// 파일 url에서 한줄한줄 읽어오는것
		String[] str = fileRead(url);
		for (int i = 0; i < str.length; i++) {

			System.out.println(str[i]);
			linecount++;

		}

		System.out.println("\n완료되었습니다");
		System.out.println("단어는 총 " + linecount + "개.");

	}

	// 텍스트파일에서 배열로 반환하는것
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

}
