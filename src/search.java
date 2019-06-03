import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class search {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        WebElement search = driver.findElement(By.className("search-input"));
        search.click();
    }
}
