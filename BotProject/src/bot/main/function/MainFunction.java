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
			// ���� ����� �Է��ϸ� �۵��ϴ� ������ �ٲٱ�
			System.out.println("�̿��ڰ� ������ ���� ��");
			MainComm bc = new MainComm();
			bc.humantalk();

		} else if (2 < a && a <= 99) {
			// �� �Ľ� ��� �߰�
			System.out.println("�����Ϸ� ���Կ�");
			BotCrawling bs = new BotCrawling();
			bs.study();

		} else if (100 < a) {
			// ���� ��� �߰�
			System.out.println("����ڶ�");

		}

	}

}
