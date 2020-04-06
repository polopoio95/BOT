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

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		//TestPro tw = new TestPro();
		//TestCrawling tc = new TestCrawling();
		//
		//String[] list = {"사랑", "쥐다", "분노", "쥐어박다","콩팥", "크나크다", "분노", "타닥거리다","햇수", "탄탄하다", "후미지다", "파괴"};
		//String insertsql = "INSERT INTO TESTTABLE VALUES(?)";
		//tc.crawling(list, insertsql);
		
		//String selectsql = "SELECT WORD FROM TESTTABLE";
		//TestSelect ts = new TestSelect(selectsql);
		//ts.selectFilter();
		
		
		//String selectsqlmain = "SELECT WORD, ATTR, CONTENT FROM TESTTABLEMAIN";
		//TestSelect tsmain = new TestSelect(selectsqlmain);
		//tsmain.select();
		
		String url = "C:\\Users\\kiwon\\Desktop\\koreadic\\KorStems\\WordList\\test_word";
		txtreader(url);
		
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
	public static void txtreader(String url) throws IOException {
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
