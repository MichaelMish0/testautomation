import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class Task2Tests {
    WebDriver driver;
    public Task2Tests() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }
    @Test
    public void FirstTask(){
        driver.get("https://demoqa.com/progress-bar");
        driver.manage().window().maximize();
        WebElement startStopButton = driver.findElement(By.id("startStopButton"));
        startStopButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id=\"progressBar\"]/div"),"100%"));
        System.out.println("100%");
        driver.close();
        driver.quit();

    }
    @Test
    public void SecondTask(){
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
        driver.manage().window().maximize();
        WebElement programmingDropdown = driver.findElement(By.id("dropdowm-menu-1"));
        Select programmingLanguage = new Select(programmingDropdown);
        String myLang="C#";
        programmingLanguage.selectByVisibleText(myLang);
        String selectedLanguage = programmingLanguage.getFirstSelectedOption().getText();
        Assert.assertEquals(myLang,selectedLanguage);


        List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for(WebElement ck:allCheckboxes){
            if(!ck.isSelected())
                ck.click();
        }

        List<WebElement> radiobtns = driver.findElements(By.xpath("//input[@name='color']"));
        for (WebElement r:radiobtns){
            if(r.getAttribute("value").equals("purple"))
                r.click();
        }


        WebElement orange = driver.findElement(By.xpath("//option[@value='orange']"));
        Assert.assertFalse(orange.isEnabled());

        driver.close();
        driver.quit();

    }

}

