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
		//셀레니움 사용할 때 필요한 옵션들
	}

	public void study() {

		try {
			DBConn dp = new DBConn();

			//MAINWORDSTORAGE에 있는 단어들 배열로 가져오기
			String[] list = dp.selectdata();
			

			driver.get(url);

			// 안 되었던 이유는 element.getText()를 안했고 쓰레드 슬립을 안해서 그랬던 것으로 추정
			//arrangetext - > 셀레니움을 통해서 크롤링해오는 것
			//arrangetext 속에 interdata 메소드는 크롤링한것을 DB에 저장해주는 기능
			arrangetext(list);

		} catch (Exception e) {

			System.out.println(e);

		} finally {
			System.out.println("\n");
			System.out.println("공부 다 했습니다");
			driver.quit();

		}

	}

	public void arrangetext(String[] list) throws InterruptedException, ClassNotFoundException, SQLException {
		//셀레니움을 이용해서 해당 페이지의 html를 뽑아온다
		
		DBConn dp = new DBConn();

		for (int i = 0; i < list.length; i++) {

			Thread.sleep(1000);
			element = driver.findElement(By.id("n_input"));
			element.clear();
			element.sendKeys(list[i], Keys.ENTER);

			Thread.sleep(1000);
			//이 웹사이트상의 구조를 내가 원하는 것으로 정리하는거 (81~85)
			WebElement elements = driver.findElement(By.className("result"));
			String strm = elements.getText().replaceAll("[123456789]", "");
			String str = strm.replace("「」", "").replace("」", "「");
			String[] strcut = str.split("전체 보기");

			System.out.printf("%n   %s에 대한 정보입니다 %n", list[i]);

			for (int j = 0; j < strcut.length; j++) {

				// 문자열에서 「을 중심으로 문자열을 자른다
				String[] sscut = strcut[j].split("「");

				dp.interdata(sscut[0], sscut[1], sscut[2]);

				for (int k = 0; k < sscut.length; k++) {

					System.out.println(sscut[k]);

				}

			}

			// System.out.printf("%n %s에 대한 정보입니다 %n " + str, list[i]);
			// 검색하다 검색결과가 없는 경우를 대비해서 if문 삽입 검색결과가 없는경우 그냥 빠져나가는 예약어 사용

		}

	}

}
