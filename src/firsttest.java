import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;


public class FirstTest {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C://drivers//chromedriver.exe");
        driver.get("https://github.com/");
        Thread.sleep(3000);

        WebElement search = driver.findElements(By.className("header-search-input")).get(0);
        search.click();
        search.sendKeys("security");
        search.sendKeys(Keys.ENTER);

        List <WebElement> searchResults = driver.findElements(By.xpath("//li[contains(@class, 'repo-list-item')]"));

        for (WebElement elementResult : searchResults) {
            String title = elementResult.findElement(By.tagName("h3")).getText();

            String descriptionText = "No Description";
            List <WebElement> descriptionList = elementResult.findElements(By.cssSelector(".col-12.col-md-9.d-inline-block.text-gray.mb-2.pr-4"));

            if(descriptionList.size() > 0){
              descriptionText = descriptionList.get(0).getText();
             }
           

            System.out.println("title: " + title + " description: " + descriptionText);
        }

        Thread.sleep(2000);
        driver.quit();
    }
}

