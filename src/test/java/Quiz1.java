import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Quiz1 {

    WebDriver driver;
   public Quiz1(){
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
    }
    @Test
    public void QuizSteps()  {
        driver.get("https://demoqa.com/login");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement userName = driver.findElement(By.id("userName"));
        WebElement pwd = driver.findElement(By.id("password"));
        WebElement login = driver.findElement(By.id("login"));
        userName.sendKeys("mikh123");
        pwd.sendKeys("Mikheil@123");
        login.click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id=\"submit\"]"),"Log out"));

        WebElement bookStore = driver.findElement(By.id("gotoStore"));
        bookStore.click();
        List<WebElement> books = driver.findElements(By.className("action-buttons"));
        Assert.assertEquals(books.size() , 8);
        WebElement gitGuide = driver.findElement(By.id("see-book-Git Pocket Guide"));
        gitGuide.click();
        WebElement title = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/label"));
        Assert.assertEquals(title.getText(), "Git Pocket Guide");

        WebElement addCollection = driver.findElement(By.id("addNewRecordButton"));
        addCollection.click();
        String msg = driver.switchTo().alert().getText();
        Assert.assertEquals(msg,"Book already present in the your collection!");

        bookStore.click();
        Assert.assertEquals(books.size() , 8);

        WebElement logout = driver.findElement(By.id("submit"));
        logout.click();

        WebElement h2 = driver.findElement(By.xpath("//*[@id=\"userForm\"]/div[1]/h2"));
        Assert.assertEquals(h2.getText(), "Welcome,");
        WebElement h5 = driver.findElement(By.xpath("//*[@id=\"userForm\"]/div[1]/h5"));
        Assert.assertEquals(h5.getText(), "Login in Book Store");

        driver.close();
        driver.quit();
}
}
