import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CrawlingMatch {

	private WebDriver driver;
	private String url;
	private WebElement element;

	public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static String WEB_DRIVER_PATH = "C:\\Users\\kiwon\\NewJDK\\Selenium\\chromedriver_win32\\chromedriver.exe";

	public CrawlingMatch() {

		super();
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.setCapability("ignoreProtectedModeSettings", true);

		driver = new ChromeDriver(options);
		url = "https://www.yna.co.kr/view/AKR20200410072000062";

	}

	public void crawling() {

		try {

			driver.get(url);

			matchWord();

		} catch (Exception e) {

			System.out.println(e);

		} finally {
			System.out.println("\n");
			System.out.println("공부 다 했습니다");
			driver.quit();

		}

	}

	//id=articleWrap
	
	private void matchWord() throws InterruptedException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT WORD, ACCURACY FROM NOUN";
		TestSelect ts = new TestSelect(sql);
		String[] wordList = ts.selectString();
		
		
		Thread.sleep(1000);
		// 이 웹사이트상의 구조를 내가 원하는 것으로 정리하는거 (69~72)
		element = driver.findElement(By.id("articleWrap"));
		String art = element.getText();
		System.out.println(art);
		
		for(int i = 0; i < wordList.length; i++) {
			
			if(art.contains(wordList[i])) {
				System.out.printf("%s는 담겨져있습니다\n", wordList[i]);
			
				
			} else {
				System.out.printf("%s는 담겨져있지 않습니다\n", wordList[i]);
			}
			
			
		}
		
		
	}

}
