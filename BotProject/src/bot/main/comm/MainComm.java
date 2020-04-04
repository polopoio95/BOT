package bot.main.comm;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//사람이 입력한 것을 읽어 오거나 봇이 말하는 것을 구현
public class MainComm {
	
	String[] hellos = { "안녕하세요", "잘 지냈어요?", "오랜만이네요", "안녕", "ㅎㅇ", "Guten Tag" };
	String[] normals = { "점심 먹으러 가자", "뭐 먹으러 갈래", "오후에 뭐 할거야?", "평상시에 쓰는 말" };
	String[] byes = { "잘가", "나중에 보자", "ㅂㅇ", "나중에 만나자" };

	//첫 인사
	public void botheltalk() {

		Random rand = new Random();
		int value = rand.nextInt(hellos.length);

		System.out.println(hellos[value]);
		humantalk();

	}

	//평범한 대화
	public void botnortalk() {

		Random rand = new Random();
		int value = rand.nextInt(normals.length);

		System.out.println(normals[value]);
		humantalk();
	}

	//작별 인사
	public void botbyetalk() {

		Random rand = new Random();
		int value = rand.nextInt(byes.length);

		System.out.println(byes[value]);
		System.out.println("시스템을 종료합니다");
	}

	//사람이 입력한 것을 걸러내는 과정 후 대답하는 최종과정
	public void humantalk() {

		Scanner sc = new Scanner(System.in);

		// 띄어쓰기 때문에 스캐너에선 nextline을 써야한다
		String word = sc.nextLine();
		int a = inputwordtest(word);

		switch (a) {

		case 1:
			botheltalk();
			break;
		case 2:
			botnortalk();
			break;
		case 3:
			botbyetalk();
			break;
		case 0:
			System.out.println("이건 에러입니다");

		}

	}

	//사람이 입력한 것을 걸러내는 과정
	public int inputwordtest(String inputword) {

		// 추후 DB에서 일치하는지 확인하는 과정 구현
		boolean helcheck = Arrays.asList(hellos).contains(inputword);
		boolean norcheck = Arrays.asList(normals).contains(inputword);
		boolean byecheck = Arrays.asList(byes).contains(inputword);

		if (helcheck == true) {
			return 1;
		} else if (norcheck == true) {
			return 2;
		} else if (byecheck == true) {
			return 3;
		} else {
			return 0;

		}

	}


}
