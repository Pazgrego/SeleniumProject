import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class firsttest {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C://drivers//chromedriver.exe");
        driver.get("https://github.com/");
        Thread.sleep(5000);

        /*WebElement search = driver.findElement(By.tagName("input"));*/
        WebElement search = driver.findElement(By.cssSelector("[name]= 'q'"));
        search.click();
    }
}

