package bot.data.textfile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextFile {

	// txtreader는 텍스트파일에서 잘 가져오나 확인하는 용도
	public void txtreader(String url) throws IOException {

		int linecount = 0;
		// String url =
		// "C:\\Users\\kiwon\\Desktop\\koreadic\\KorStems\\WordList\\test_word.txt";

		// 파일 url에서 한줄한줄 읽어오는것
		String[] str = returnArrange(url);
		for (int i = 0; i < str.length; i++) {

			System.out.println(str[i]);
			linecount++;

		}

		System.out.println("\n완료되었습니다");
		System.out.println("단어는 총 " + linecount + "개 입니다.");

	}

	// 텍스트파일에서 배열로 반환 하는 것
	public String[] returnArrange(String url) throws IOException {

		List<String> arrList = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new FileReader(url));
		String s;

		while ((s = br.readLine()) != null) {
			arrList.add(s);

		}
		br.close();

		// 리스트를 스트링배열로 바꾸는 법
		String[] strList = arrList.toArray(new String[arrList.size()]);

		// String str = Arrays.toString(tf.fileRead(url));

		return strList;

	}

	public String excepteKorean(String url) throws IOException {

		List<String> arrList = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new FileReader(url));
		String s;

		while ((s = br.readLine()) != null) {
			arrList.add(s.replaceAll("[^\\uAC00-\\uD7AF\\u1100-\\u11FF\\u3130-\\u318F]", ""));

		}
		br.close();

		// 리스트를 스트링배열로 바꾸는 법
		String[] strList = arrList.toArray(new String[arrList.size()]);
		String text = Arrays.toString(strList); 
		//스트링 배열을 스트링으로 바꾸는법
		return text;


	}

	/*
	 * String text = Arrays.toString(tf.returnArrange(url)); // 텍스트 파일을 한뭉탱이로 가져오기
	 */}
