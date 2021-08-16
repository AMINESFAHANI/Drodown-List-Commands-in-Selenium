
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DropDownListCommand {

    static File file;
    static WebDriver driver;
    static WebDriverWait wait;
    static WebElement dropDownList;
    static Select selectdropdown;
    static List<WebElement> selectableElements;
    static List<String> selectableValues = new ArrayList<>();
    static int size;

    public static void main(String[] args) throws InterruptedException {

            file = new File("src/main/resources/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            driver = new ChromeDriver();// ChomeDriver is an implementation of a web driver interface

            wait=new WebDriverWait(driver, 5);

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;

            // Launch Website

            driver.manage().window().maximize();
            driver.navigate().to("https://www.testandquiz.com/selenium/testing.html");
            driver.navigate().refresh();


            //Using Select class for selecting value from dropdown
            dropDownList = driver.findElement(new By.ById("testingDropdown"));
            if (dropDownList.isEnabled() && dropDownList.isDisplayed()){
                System.out.println("\n***  The Drodown list is ready to be selected  ***");
            }
            wait.until(ExpectedConditions.visibilityOf(dropDownList));


            selectdropdown = new Select(dropDownList);

            selectdropdown.selectByVisibleText("Database Testing");
            selectdropdown.selectByIndex(0);
            selectdropdown.selectByValue("Performance");

            selectableElements = selectdropdown.getOptions();

            // Iterating through the options by visible texts:
            for (WebElement selectedElement:selectableElements){
                System.out.println("\n\nVisible Text :"+selectedElement.getText());
                selectableValues.add(selectedElement.getAttribute("value"));
                System.out.println("Value :"+selectedElement.getAttribute("value"));
                selectdropdown.selectByVisibleText(selectedElement.getText());
                Thread.sleep(1000);
            }

            // Iterating through the options by values:
            for (String value:selectableValues){
                selectdropdown.selectByValue(value);
                Thread.sleep(1000);
            }

            // Iterating through the options by indexes:
            size = selectableElements.size();
            for (int i=0;i<size;i++){
                selectdropdown.selectByIndex(i);
                Thread.sleep(1000);
            }

            Thread.sleep(3000);
            // Close the Browser
            driver.close();

        }
    }

