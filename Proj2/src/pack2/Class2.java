package pack2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Class2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		System.out.println("Craft Maven");
		
		System.setProperty("webdriver.chrome.driver", "D:\\GTM Full\\JARS\\ChromeLatestDriver\\chromedriver.exe");
		WebDriver w = new ChromeDriver();
		w.get("https://google.com");
		Thread.sleep(3000);
		w.quit();
		}catch(Exception e) {
			System.out.println("Craft Maven "+e.getMessage());
		}
	}
	
	public void fetch()
	{
		System.out.println("second project123456");
		System.out.println("naseer");
		System.out.println("naseer");
		
	}

}
