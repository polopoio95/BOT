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

	// 셀레니움 사용할 때 필요한 옵션들
	public TestCrawling() {
		super();
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.setCapability("ignoreProtectedModeSettings", true);

		driver = new ChromeDriver(options);
		url = "https://stdict.korean.go.kr/search/searchResult.do";

		// https://stdict.korean.go.kr/main/main.do - 원래 메인홈페이지

	}
	
	

	public void crawlingSave(String[] list, String sql) {

		try {

			driver.get(url);

			saveWord(list, sql);

		} catch (Exception e) {

			System.out.println(e);

		} finally {
			System.out.println("\n");
			System.out.println("공부 다 했습니다");
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
			System.out.println("공부 다 했습니다");
			driver.quit();

		}

	}

	public void saveWord(String[] list, String sql) throws InterruptedException, ClassNotFoundException, SQLException {
		// 셀레니움을 이용해서 해당 페이지의 html를 뽑아온다

		TestInsert dp = new TestInsert(sql);

		if (sql.contains("(?)")) {
			List<String> crawlingword = new ArrayList<String>();

			for (int i = 0; i < list.length; i++) {

				Thread.sleep(1000);
				element = driver.findElement(By.id("n_input"));
				element.clear();
				element.sendKeys(list[i], Keys.ENTER);

				Thread.sleep(1000);
				// 이 웹사이트상의 구조를 내가 원하는 것으로 정리하는거 (69~72)
				element = driver.findElement(By.className("result"));
				String strm = element.getText().replaceAll("[123456789]", "");
				String str = strm.replace("「」", "").replace("」", "「");
				String[] strcut = str.split("전체 보기");

				System.out.printf("%n   %s에 대한 정보입니다 %n", list[i]);

				for (int j = 0; j < strcut.length; j++) {

					// 문자열에서 「을 중심으로 문자열을 자른다
					String[] sscut = strcut[j].split("「");

					crawlingword.add(sscut[0]);

					System.out.println(sscut[0]);

				}

				// System.out.printf("%n %s에 대한 정보입니다 %n " + str, list[i]);
				// 검색하다 검색결과가 없는 경우를 대비해서 if문 삽입 검색결과가 없는경우 그냥 빠져나가는 예약어 사용

			}

			String[] wordListS = crawlingword.toArray(new String[crawlingword.size()]);
			dp.insert(wordListS);

		} else if (sql.contains("(?,?)")) {
			System.out.println("아직 구현하지 않았습니다");

		} else if (sql.contains("(?,?,?)")) {

			List<String> crawlingword = new ArrayList<String>();

			for (int i = 0; i < list.length; i++) {

				try {

					Thread.sleep(1000);
					element = driver.findElement(By.id("n_input"));
					element.clear();
					element.sendKeys(list[i], Keys.ENTER);

					Thread.sleep(1000);
					// 이 웹사이트상의 구조를 내가 원하는 것으로 정리하는거 (69~72)
					element = driver.findElement(By.className("result"));
					String strm = element.getText().replaceAll("[123456789]", "");
					String str = strm.replace("「」", "").replace("」", "「");

					String[] strcut = str.split("전체 보기");

					System.out.printf("%n   %s에 대한 정보입니다 %n", list[i]);

					for (int j = 0; j < strcut.length; j++) {

						crawlingword.add(strcut[j]);

					}

				} catch (Exception e) {
					System.out.println("\n 검색 결과가 없습니다");
					continue;

				}

			}

			String[] wordListS = crawlingword.toArray(new String[crawlingword.size()]);
			System.out.println(wordListS.length);
			
			dp.insertthree(wordListS);

		} else {
			System.out.println("DB 명령문을 다시 한 번 확인해주세요");
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
				System.out.printf("%s는 담겨져있습니다\n", word[0]);
				int acc = Integer.parseInt(word[1]) + 1;
				System.out.println(acc);
				
			
				
			} else {
				System.out.printf("%s는 담겨져있지 않습니다\n", word[0]);
				int acc = Integer.parseInt(word[1]) - 1;
				System.out.println(acc);
			}
			
			
		}
		
		
	}

}
