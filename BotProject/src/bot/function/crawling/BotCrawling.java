package bot.function.crawling;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import bot.main.db.DBConn;

public class BotCrawling {
	
	private WebDriver driver;
	private String url;
	private WebElement element;

	public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static String WEB_DRIVER_PATH = "C:\\Users\\kiwon\\NewJDK\\Selenium\\chromedriver_win32\\chromedriver.exe";

	public BotCrawling() {
		super();
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("headless");
		options.setCapability("ignoreProtectedModeSettings", true);

		driver = new ChromeDriver(options);
		url = "https://stdict.korean.go.kr/search/searchResult.do";

		// https://stdict.korean.go.kr/main/main.do
		//�����Ͽ� ����� �� �ʿ��� �ɼǵ�
	}

	public void study() {

		try {
			DBConn dp = new DBConn();

			//MAINWORDSTORAGE�� �ִ� �ܾ�� �迭�� ��������
			String[] list = dp.selectdata();
			

			driver.get(url);

			// �� �Ǿ��� ������ element.getText()�� ���߰� ������ ������ ���ؼ� �׷��� ������ ����
			//arrangetext - > �����Ͽ��� ���ؼ� ũ�Ѹ��ؿ��� ��
			//arrangetext �ӿ� interdata �޼ҵ�� ũ�Ѹ��Ѱ��� DB�� �������ִ� ���
			arrangetext(list);

		} catch (Exception e) {

			System.out.println(e);

		} finally {
			System.out.println("\n");
			System.out.println("���� �� �߽��ϴ�");
			driver.quit();

		}

	}

	public void arrangetext(String[] list) throws InterruptedException, ClassNotFoundException, SQLException {
		//�����Ͽ��� �̿��ؼ� �ش� �������� html�� �̾ƿ´�
		
		DBConn dp = new DBConn();

		for (int i = 0; i < list.length; i++) {

			Thread.sleep(1000);
			element = driver.findElement(By.id("n_input"));
			element.clear();
			element.sendKeys(list[i], Keys.ENTER);

			Thread.sleep(1000);
			//�� ������Ʈ���� ������ ���� ���ϴ� ������ �����ϴ°� (81~85)
			WebElement elements = driver.findElement(By.className("result"));
			String strm = elements.getText().replaceAll("[123456789]", "");
			String str = strm.replace("����", "").replace("��", "��");
			String[] strcut = str.split("��ü ����");

			System.out.printf("%n   %s�� ���� �����Դϴ� %n", list[i]);

			for (int j = 0; j < strcut.length; j++) {

				// ���ڿ����� ���� �߽����� ���ڿ��� �ڸ���
				String[] sscut = strcut[j].split("��");

				dp.interdata(sscut[0], sscut[1], sscut[2]);

				for (int k = 0; k < sscut.length; k++) {

					System.out.println(sscut[k]);

				}

			}

			// System.out.printf("%n %s�� ���� �����Դϴ� %n " + str, list[i]);
			// �˻��ϴ� �˻������ ���� ��츦 ����ؼ� if�� ���� �˻������ ���°�� �׳� ���������� ����� ���

		}

	}

}
