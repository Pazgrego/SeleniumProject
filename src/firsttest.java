import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FirstTest {
    public static void main(String[] args) throws Exception {
        WebDriver driver = OpenWebsiteSession();

        String currentTime = getCurrentTime();
        FileWriter writer = new FileWriter("SecurityResultGitHub " +currentTime+ ".txt");
        BufferedWriter bw = new BufferedWriter(writer);

        SearchKeyword(driver, "security");

        List<repoObj> jsonObj = new ArrayList<>();

        final int maxPage = 5;

        for(int index = 0; index < maxPage; index++) {

            List<WebElement> searchResults = driver.findElements(By.xpath("//li[contains(@class, 'repo-list-item')]"));

            for (WebElement elementResult : searchResults) {
                repoObj obj = CreateRepoObj(elementResult);

                WriteToFile(bw, jsonObj, obj);
            }

            NavigateToNextPage(driver);
        }

        bw.flush();
        bw.close();

        Thread.sleep(10000);
        driver.quit();
    }

    private static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH mm");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    private static void WriteToFile(BufferedWriter bw, List<repoObj> jsonObj, repoObj obj) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        jsonObj.add(obj);
        bw.write(gson.toJson(obj) + '\n');
    }

    private static repoObj CreateRepoObj(WebElement elementResult) {
        String title = getTitle(elementResult);
        String descriptionText = getDescription(elementResult);
        List<String> tagsList = getTags(elementResult);
        String time = getTime(elementResult, By.tagName("relative-time"), "innerHTML");
        String language = getLanguage(elementResult);
        String stars = getStars(elementResult);

        return new repoObj(title, descriptionText, tagsList, time, language, stars);
    }

    private static void NavigateToNextPage(WebDriver driver) throws InterruptedException {
        WebElement nextPage = driver.findElement(By.className("next_page"));
        nextPage.click();
        Thread.sleep(10000);
    }

    private static String getStars(WebElement elementResult) {
        return elementResult.findElement(By.cssSelector(".pl-2.pl-md-0.text-right.flex-auto.min-width-0")).getAttribute("innerText");
    }

    private static String getLanguage(WebElement elementResult) {
        String language = "No Language";
        List<WebElement> elementLanguageList = elementResult.findElements(By.cssSelector("[itemprop = 'programmingLanguage']"));
        if (elementLanguageList.size() > 0) {
            language = elementLanguageList.get(0).getText();
        }
        return language;
    }

    private static String getTime(WebElement elementResult, By by, String innerHTML) {
        return elementResult.findElement(by).getAttribute(innerHTML);
    }

    private static List<String> getTags(WebElement elementResult) {
        String tagsText = "No Tags";
        List<String> tagsList = new ArrayList<>();
        List<WebElement> elementsTagsList = elementResult.findElements(By.xpath(".//a[contains(@class, 'topic-tag')]"));

        if (elementsTagsList.size() > 0) {
            for (WebElement elementTags : elementsTagsList) {
                tagsList.add(elementTags.getAttribute("innerText"));
            }
        }
        else {
            tagsList.add(tagsText);
        }

        return tagsList;
    }

    private static String getDescription(WebElement elementResult) {
        String descriptionText = "No Description";
        List<WebElement> descriptionList = elementResult.findElements(By.xpath(".//p[contains(@class, 'col-md-9')]"));
        if (descriptionList.size() > 0) {
            descriptionText = descriptionList.get(0).getText();
        }
        return descriptionText;
    }

    private static String getTitle(WebElement elementResult) {
        return elementResult.findElement(By.tagName("h3")).getText();
    }

    private static void SearchKeyword(WebDriver driver, String keyword) {
        WebElement search = driver.findElements(By.className("header-search-input")).get(0);
        search.click();
        search.sendKeys(keyword);
        search.sendKeys(Keys.ENTER);
    }

    private static WebDriver OpenWebsiteSession() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C://drivers//chromedriver.exe");
        driver.get("https://github.com/");
        Thread.sleep(3000);

        return driver;
    }


}

