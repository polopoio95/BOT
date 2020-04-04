package bot.main.comm;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//����� �Է��� ���� �о� ���ų� ���� ���ϴ� ���� ����
public class MainComm {
	
	String[] hellos = { "�ȳ��ϼ���", "�� ���¾��?", "�������̳׿�", "�ȳ�", "����", "Guten Tag" };
	String[] normals = { "���� ������ ����", "�� ������ ����", "���Ŀ� �� �Ұž�?", "���ÿ� ���� ��" };
	String[] byes = { "�߰�", "���߿� ����", "����", "���߿� ������" };

	//ù �λ�
	public void botheltalk() {

		Random rand = new Random();
		int value = rand.nextInt(hellos.length);

		System.out.println(hellos[value]);
		humantalk();

	}

	//����� ��ȭ
	public void botnortalk() {

		Random rand = new Random();
		int value = rand.nextInt(normals.length);

		System.out.println(normals[value]);
		humantalk();
	}

	//�ۺ� �λ�
	public void botbyetalk() {

		Random rand = new Random();
		int value = rand.nextInt(byes.length);

		System.out.println(byes[value]);
		System.out.println("�ý����� �����մϴ�");
	}

	//����� �Է��� ���� �ɷ����� ���� �� ����ϴ� ��������
	public void humantalk() {

		Scanner sc = new Scanner(System.in);

		// ���� ������ ��ĳ�ʿ��� nextline�� ����Ѵ�
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
			System.out.println("�̰� �����Դϴ�");

		}

	}

	//����� �Է��� ���� �ɷ����� ����
	public int inputwordtest(String inputword) {

		// ���� DB���� ��ġ�ϴ��� Ȯ���ϴ� ���� ����
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