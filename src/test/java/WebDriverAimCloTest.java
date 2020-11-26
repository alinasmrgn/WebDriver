import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebDriverAimCloTest {
    public static String expectedResult ="Кардиган удлиненный, серый" ;
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver","D:\\WebDriver\\chromedriver.exe");
        driver = new ChromeDriver(); }

    @Test
    public void addToBagTest() {
        driver.get("https://aimclo.ru/shop/kardigany/kardigan-udlinennyy-seryy/");
        WebElement addToBagButton = waitForElementLocatedBy(driver,By.xpath("//*[@value='ДОБАВИТЬ В КОРЗИНУ']"));
        addToBagButton.click();
        WebElement goToBagButton = driver.findElement(By.xpath("//a[@href='/basket/']"));
        goToBagButton.click();
        WebElement goToLookBagButton = driver.findElement(By.xpath("//div[@class='add-basket__footer']"));
        goToLookBagButton.click();

        Assert.assertEquals(waitForElementLocatedBy(driver,By.xpath("//div[@class='tovar']//a")).getText(), expectedResult);
    }

    @AfterMethod(alwaysRun = true)
    public void driverShutDown(){
        driver.quit();
        driver=null;
    }
    private static WebElement waitForElementLocatedBy(WebDriver driver, By by){
        return new WebDriverWait(driver,4)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }


}

