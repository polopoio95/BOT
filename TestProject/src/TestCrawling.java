import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestCrawling {

	private WebDriver driver;
	private String url;
	private WebElement element;

	public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static String WEB_DRIVER_PATH = "C:\\Users\\kiwon\\NewJDK\\Selenium\\chromedriver_win32\\chromedriver.exe";

	// �����Ͽ� ����� �� �ʿ��� �ɼǵ�
	public TestCrawling() {
		super();
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.setCapability("ignoreProtectedModeSettings", true);

		driver = new ChromeDriver(options);
		url = "https://stdict.korean.go.kr/search/searchResult.do";

		// https://stdict.korean.go.kr/main/main.do - ���� ����Ȩ������

	}
	
	

	public void crawlingSave(String[] list, String sql) {

		try {

			driver.get(url);

			saveWord(list, sql);

		} catch (Exception e) {

			System.out.println(e);

		} finally {
			System.out.println("\n");
			System.out.println("���� �� �߽��ϴ�");
			driver.quit();

		}

	}
	
	public void crawlingMatch(String[] list, String sql, String url) {

		try {

			driver.get(url);

			matchWord(list, sql, url);

		} catch (Exception e) {

			System.out.println(e);

		} finally {
			System.out.println("\n");
			System.out.println("���� �� �߽��ϴ�");
			driver.quit();

		}

	}

	public void saveWord(String[] list, String sql) throws InterruptedException, ClassNotFoundException, SQLException {
		// �����Ͽ��� �̿��ؼ� �ش� �������� html�� �̾ƿ´�

		TestInsert dp = new TestInsert(sql);

		if (sql.contains("(?)")) {
			List<String> crawlingword = new ArrayList<String>();

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
			dp.insert(wordListS);

		} else if (sql.contains("(?,?)")) {
			System.out.println("���� �������� �ʾҽ��ϴ�");

		} else if (sql.contains("(?,?,?)")) {

			List<String> crawlingword = new ArrayList<String>();

			for (int i = 0; i < list.length; i++) {

				try {

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

						crawlingword.add(strcut[j]);

					}

				} catch (Exception e) {
					System.out.println("\n �˻� ����� �����ϴ�");
					continue;

				}

			}

			String[] wordListS = crawlingword.toArray(new String[crawlingword.size()]);
			System.out.println(wordListS.length);
			
			dp.insertthree(wordListS);

		} else {
			System.out.println("DB ��ɹ��� �ٽ� �� �� Ȯ�����ּ���");
		}

	}

	private void matchWord(String[] wordList, String sql, String url) throws InterruptedException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		this.url = url;
		
		TestSelect ts = new TestSelect(sql);
		
		element = driver.findElement(By.id("container"));
		String art = element.getText();
		System.out.println(art);
		
		for(int i = 0; i < wordList.length; i++) {
			String[] word = wordList[i].split("/");
		
			if(art.contains(word[0])) {
				System.out.printf("%s�� ������ֽ��ϴ�\n", word[0]);
				int acc = Integer.parseInt(word[1]) + 1;
				System.out.println(acc);
				
			
				
			} else {
				System.out.printf("%s�� ��������� �ʽ��ϴ�\n", word[0]);
				int acc = Integer.parseInt(word[1]) - 1;
				System.out.println(acc);
			}
			
			
		}
		
		
	}

}
