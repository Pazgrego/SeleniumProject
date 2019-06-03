import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class FirstTest {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C://drivers//chromedriver.exe");
        driver.get("https://github.com/");
        Thread.sleep(2000);

          List<WebElement> search = driver.findElements(By.className("header-search-input"));
            search.get(0).click();
            search.get(0).sendKeys("security");
            search.get(0).sendKeys(Keys.ENTER);

        List<WebElement> Title = driver.findElements(By.xpath("//*[@class='repo-list']//h3"));
        System.out.println(Title.get(0).getText());

    }
}

