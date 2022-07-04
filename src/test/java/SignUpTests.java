import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTests {

    @Test
    public void zipCodeTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertFalse(driver.findElement(By.name("zip_code")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("[value='Register']")).isDisplayed());
        Thread.sleep(10000);
        driver.quit();
    }
    @Test
    public void incorrectZipCode() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("123");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        WebElement errorMassege = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertEquals(errorMassege.getText(), "Oops, error on page. ZIP code should have 5 digits");
        driver.quit();
    }
    @Test
    public void longZipCode() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("1234567");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        WebElement errorMassege = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertEquals(errorMassege.getText(), "Oops, error on page. ZIP code should have 5 digits");
        driver.quit();
    }
    @Test
    public void SignUpPositive() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        driver.findElement(By.name("first_name")).sendKeys("Pavel");
        driver.findElement(By.name("last_name")).sendKeys("Kulahin");
        driver.findElement(By.name("email")).sendKeys("kulahin.official@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("1234567");
        driver.findElement(By.name("password2")).sendKeys("1234567");
        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();
        WebElement confirmationMessage = driver.findElement(By.cssSelector(".confirmation_message"));
        Assert.assertEquals(confirmationMessage.getText(), "Account is created!");
        driver.quit();
    }
    @Test
    public void SignUpNegativeName() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        driver.findElement(By.name("first_name")).sendKeys("");
        driver.findElement(By.name("last_name")).sendKeys("Kulahin");
        driver.findElement(By.name("email")).sendKeys("kulahin.official@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("1234567");
        driver.findElement(By.name("password2")).sendKeys("1234567");
        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();
        WebElement errorMessage = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertEquals(errorMessage.getText(), "Oops, error on page. Some of your fields have invalid data or email was previously used");
        driver.quit();
    }
    @Test
    public void SignUpNegativePswrd() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        driver.findElement(By.name("first_name")).sendKeys("Pavel");
        driver.findElement(By.name("last_name")).sendKeys("Kulahin");
        driver.findElement(By.name("email")).sendKeys("kulahin.official@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("1234567");
        driver.findElement(By.name("password2")).sendKeys("");
        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();
        WebElement confirmationMessage = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertEquals(confirmationMessage.getText(), "Oops, error on page. Some of your fields have invalid data or email was previously used");
        driver.quit();
    }
}

