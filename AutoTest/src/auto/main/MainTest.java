package auto.main;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import comm.Function;

public class MainTest {
	
	//C:\\Users\\kiwon\\Desktop\\개발툴\\Project\\TextStorage\\english.txt
	//C:\\Users\\kiwon\\Desktop\\개발툴\\Project\\TextStorage\\korean.txt

	public static void main(String[] args) throws IOException, InterruptedException {



		MainTest mt = new MainTest();
		mt.communicate();

	}
	
	public void communicate() throws InterruptedException, IOException {
		
		System.out.println("안녕 바꿀 파일을 입력해줘");
		Scanner sc = new Scanner(System.in);

		String inUrl = sc.next();
		String outUrl = "C:\\\\Users\\\\kiwon\\\\Desktop\\\\개발툴\\\\Project\\\\TextStorage\\\\Function.txt";
		String finalUrl = "C:\\\\Users\\\\kiwon\\\\eclipse-workspace\\\\AutoTest\\\\src\\\\comm\\\\Function.java";


		Thread.sleep(500);

		Random random = new Random();
		int a = random.nextInt(100);

		System.out.println(a);
		
		TextControl tc = new TextControl();
		tc.textControl(inUrl, outUrl, finalUrl);
		
		System.out.println("파일 변경은 끝났어");
		Scanner sc1 = new Scanner(System.in);
		
		if (a < 2) {
			System.out.println("제발요");
			
			
		} else {

			Function fc = new Function();
			fc.random();

		}

	}

}
