package auto.main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRead {
	
	public String[] fileRead(String url) throws IOException {

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
