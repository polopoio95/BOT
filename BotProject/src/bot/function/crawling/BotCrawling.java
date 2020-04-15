package bot.function.crawling;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import bot.main.db.DBConn;
import bot.main.db.DBinsert;
import bot.main.db.DBselect;

public class BotCrawling {

	private WebDriver driver;
	private String url;
	private WebElement element;

	public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static String WEB_DRIVER_PATH = "C:\\Users\\kiwon\\NewJDK\\Selenium\\chromedriver_win32\\chromedriver.exe";

	// �����Ͽ� ����� �� �ʿ��� �ɼǵ�
	public BotCrawling() {
		super();
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.setCapability("ignoreProtectedModeSettings", true);

		driver = new ChromeDriver(options);
		url = "https://stdict.korean.go.kr/search/searchResult.do";

	}

	public void crawling(String[] list, String sql) {

		try {
			driver.get(url);

			crawlingWeb(list, sql);

		} catch (Exception e) {

			System.out.println(e);

		} finally {
			System.out.println("\n");
			System.out.println("ũ�Ѹ��� ���½��ϴ�");
			driver.quit();

		}

	}

	public void crawlingWeb(String[] list, String sql)

			throws InterruptedException, ClassNotFoundException, SQLException {
		// �����Ͽ��� �̿��ؼ� �ش� �������� html�� �̾ƿ´�

		DBinsert dp = new DBinsert(sql);
		List<String> crawlingword = new ArrayList<String>();

		if (sql.contains("(?)")) {

			for (int i = 0; i < list.length; i++) {

				Thread.sleep(1000);
				element = driver.findElement(By.id("n_input"));
				element.clear();
				element.sendKeys(list[i], Keys.ENTER);

				Thread.sleep(1000);
				// �� ������Ʈ���� ������ ���� ���ϴ� ������ �����ϴ°� (69~72)
				element = driver.findElement(By.className("result"));
				String strm = element.getText().replaceAll("[123456789]", "");
				String str = strm.replace("����", "").replace("��", "��");
				String[] strcut = str.split("��ü ����");

				System.out.printf("%n   %s�� ���� �����Դϴ� %n", list[i]);

				for (int j = 0; j < strcut.length; j++) {

					// ���ڿ����� ���� �߽����� ���ڿ��� �ڸ���
					String[] sscut = strcut[j].split("��");

					crawlingword.add(sscut[0]);

					System.out.println(sscut[0]);

				}

				// System.out.printf("%n %s�� ���� �����Դϴ� %n " + str, list[i]);
				// �˻��ϴ� �˻������ ���� ��츦 ����ؼ� if�� ���� �˻������ ���°�� �׳� ���������� ����� ���

			}

			String[] wordListS = crawlingword.toArray(new String[crawlingword.size()]);
			dp.insertOne(wordListS);

		} else if (sql.contains("(?,?)")) {

		} else if (sql.contains("(?,?,?)")) {

			for (int i = 0; i < list.length; i++) {

				try {

					Thread.sleep(1000);
					element = driver.findElement(By.id("n_input"));
					element.clear();
					element.sendKeys(list[i], Keys.ENTER);

					Thread.sleep(1000);
					// �� ������Ʈ���� ������ ���� ���ϴ� ������ �����ϴ°� (69~72)
					WebElement elements = driver.findElement(By.className("result"));
					String strm = elements.getText().replaceAll("[123456789]", "");
					String str = strm.replace("����", "").replace("��", "��");
					String[] strcut = str.split("��ü ����");

					System.out.printf("%n   %s�� ���� �����Դϴ� %n", list[i]);

					for (int j = 0; j < strcut.length; j++) {
						System.out.println(strcut[j]);
						crawlingword.add(strcut[j]);

					}

				} catch (Exception e) {
					System.out.println("\n �˻� ����� �����ϴ�");
					continue;

				}

			}

			String[] wordListS = crawlingword.toArray(new String[crawlingword.size()]);
			dp.insertThree(wordListS);

		} else {
			System.out.println("DB ��ɹ��� �ٽ� �� �� Ȯ�����ּ���");
		}
	}
	
	private void matchWord() throws InterruptedException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT WORD, ACCURACY FROM NOUN";
		DBselect ts = new DBselect(sql);
		String[] wordList = ts.selectWordStorage();
		//���͵� �ܾ�
		
		
		Thread.sleep(1000);
		// �� ������Ʈ���� ������ ���� ���ϴ� ������ �����ϴ°� (69~72)
		element = driver.findElement(By.id("articleWrap"));
		String art = element.getText();
		System.out.println(art);
		
		for(int i = 0; i < wordList.length; i++) {
			
			if(art.contains(wordList[i])) {
				System.out.printf("%s�� ������ֽ��ϴ�\n", wordList[i]);
			
				
			} else {
				System.out.printf("%s�� ��������� �ʽ��ϴ�\n", wordList[i]);
			}
			
			
		}
		
		
	}

}
