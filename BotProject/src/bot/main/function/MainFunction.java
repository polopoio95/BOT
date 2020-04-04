package bot.main.function;

import java.util.Random;

import bot.function.crawling.BotCrawling;
import bot.main.comm.MainComm;

public class MainFunction {

	public static void main(String[] args) {
		

		Random random = new Random();
		int a = random.nextInt(100);

		System.out.println(a);

		if (a < 1) {

			MainComm mc = new MainComm();
			mc.botheltalk();

		} else if (1 <= a && a <= 2) {
			// 향후 사람이 입력하면 작동하는 구조로 바꾸기
			System.out.println("이용자가 봇에게 말걸 때");
			MainComm bc = new MainComm();
			bc.humantalk();

		} else if (2 < a && a <= 99) {
			// 웹 파싱 기능 추가
			System.out.println("공부하러 갈게요");
			BotCrawling bs = new BotCrawling();
			bs.study();

		} else if (100 < a) {
			// 향후 기능 추가
			System.out.println("장기자랑");

		}

	}

}
