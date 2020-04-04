
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest {

	private WebDriver driver;
	private String url;
	private WebElement element;

	public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static String WEB_DRIVER_PATH = "C:\\Users\\kiwon\\NewJDK\\Selenium\\chromedriver_win32\\chromedriver.exe";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeleniumTest sl = new SeleniumTest();
		sl.oper();

	}

	public SeleniumTest() {
		super();

		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		options.setCapability("ignoreProtectedModeSettings", true);

		driver = new ChromeDriver(options);
		url = "https://ko.wiktionary.org/wiki/%EB%B6%80%EB%A1%9D:%EC%9E%90%EC%A3%BC_%EC%93%B0%EC%9D%B4%EB%8A%94_%ED%95%9C%EA%B5%AD%EC%96%B4_%EB%82%B1%EB%A7%90_5800";

	}

	public void oper() {

		try {
			driver.get(url);
			Thread.sleep(1000);

			String[] list = { "것", "하다", "있다", "되다", "수", "나", "그", "없다", "않다", "사람", "우리", 
					"이", "아니다", "보다", "등", "떄", "거", "보다", "같다", "주다", "대하다" };

			for (int i = 0; i < list.length; i++) {

				element = driver.findElement(By.id("searchInput"));
				Thread.sleep(500);
				element.sendKeys(list[i]);
				element.sendKeys(Keys.ENTER);

				Thread.sleep(500);
				String pUrl = driver.getCurrentUrl();
				Document doc = Jsoup.connect(pUrl).get();
				Elements elem = doc.select("ul");
				String detailtext = elem.text();
				System.out.printf("\"%s\"에 대한 정보입니다 \n" + detailtext, list[i]);
				System.out.println("\n");
				
				/*Thread.sleep(500);
				System.out.println("*****" + elem);*/
				//String text = doc.text();
				//System.out.println(text);

			}

			System.out.println("");
			

		} catch (Exception e) {
			
			System.out.println("***************************************************************\n");
			e.printStackTrace();
			System.out.println("***************************************************************\n");

		} finally {
			System.out.println("공부 다 했습니다");
			driver.quit();

		}
	}

}
