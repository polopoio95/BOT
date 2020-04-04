package bot.main.brain;

import java.util.Random;

import bot.main.comm.Botcomm;
import bot.main.study.Botstudy;

public class Braincontrol {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Random random = new Random();
		int a = random.nextInt(100);

		System.out.println(a);

		if (a < 1) {

			Botcomm bc = new Botcomm();
			bc.botheltalk();

		} else if (1 <= a && a <= 2) {
			// 향후 사람이 반응을 주면 작동하는 구조로 바꾸기
			System.out.println("이용자가 봇에게 말걸 때");
			Botcomm bc = new Botcomm();
			bc.humantalk();

		} else if (2 < a && a <= 99) {
			// 웹 파싱 기능 추가
			System.out.println("공부하러 갈게요");
			Botstudy bs = new Botstudy();
			bs.study();

		} else if (100 < a) {
			// 향후 기능 추가
			System.out.println("장기자랑");

		}

	}

}
