package comm;

import java.io.IOException;
import java.util.Random;
import auto.main.MainTest;

public class Function {
	public void random() throws InterruptedException, IOException {
		Random random = new Random();
		int a = random.nextInt(100);
		if (a < 10) {
			hi();
		} else if (20 < a && a <= 60) {
			bye();
		} else if (60 < a && a <= 95) {
			what();
		} else {
			onemore();
		}
		MainTest mt = new MainTest();
		mt.communicate();
	}

	public void hi() {
		System.out.println("Hi@@@@");
	}

	public void bye() {
		System.out.println("Bye@@@@");
	}

	public void say() {
		System.out.println("Hello@@@@");
	}

	public void what() {
		System.out.println("What@@@");
	}

	public void onemore() {
		System.out.println("onemore@@@");
	}
}