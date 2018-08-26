package PackageOne;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ClassOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			System.out.println("Executed Mvn Git Jenkins");

			try {
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			}catch(Exception e) {
				System.setProperty("webdriver.chrome.driver", "MvnGitJenkins\\chromedriver.exe");
			}

			WebDriver w = new ChromeDriver();
			w.get("https://www.youtube.com/watch?v=dZ0fwJojhrs");
			
		} catch (Exception e) {
			System.out.println("Exception Start");
			System.out.println(e.getMessage());
			System.out.println("Exception End");
		}
	}
	
	@Test
	public void valTestNG()
	{
		System.out.println("TestNG Test Executed");
		System.out.println(System.getProperty("user.dir")+"\\chromedriver.exe");
	}

}
