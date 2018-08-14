package pack1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Class1 {
	public static void main(String[] args) {
		try {
			System.out.println("First Project");

			System.setProperty("webdriver.chrome.driver", "D:\\GTM Full\\JARS\\ChromeLatestDriver\\chromedriver.exe");
			WebDriver w = new ChromeDriver();
			w.get("https://google.com");
			Thread.sleep(3000);
			w.quit();
			System.out.println("git");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void tc1() {
		try {
			System.out.println("TestNG TC1");

			System.setProperty("webdriver.chrome.driver", "D:\\GTM Full\\JARS\\ChromeLatestDriver\\chromedriver.exe");
			WebDriver w = new ChromeDriver();
			w.get("https://google.com");
			Thread.sleep(3000);
			w.quit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void tc2() {
		try {
			System.out.println("TestNG TC1");

			System.setProperty("webdriver.chrome.driver", "D:\\GTM Full\\JARS\\ChromeLatestDriver\\chromedriver.exe");
			WebDriver w = new ChromeDriver();
			w.get("https://google.com");
			Thread.sleep(3000);
			w.quit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
