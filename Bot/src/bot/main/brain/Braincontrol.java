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
			// ���� ����� ������ �ָ� �۵��ϴ� ������ �ٲٱ�
			System.out.println("�̿��ڰ� ������ ���� ��");
			Botcomm bc = new Botcomm();
			bc.humantalk();

		} else if (2 < a && a <= 99) {
			// �� �Ľ� ��� �߰�
			System.out.println("�����Ϸ� ���Կ�");
			Botstudy bs = new Botstudy();
			bs.study();

		} else if (100 < a) {
			// ���� ��� �߰�
			System.out.println("����ڶ�");

		}

	}

}
