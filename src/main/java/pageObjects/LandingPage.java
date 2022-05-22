package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.Base;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class LandingPage extends Base {

    public WebDriver driver;

    By clickOnBuyPropertyOption = By.xpath("//div[text()='Buy']");
    By clickOnCityDropdownOption = By.xpath("(//div[@id='searchCity'])[1]");
    By enterAreaNameInSearchField = By.id("listPageSearchLocality");
    By enterSecondAreaNameInSearchField = By.xpath("(//input[@id='listPageSearchLocality'])[1]");
    By clickOnApartmentTypeDropdownField = By.xpath("//div[@class='nb-select__placeholder']");
    By clickOnSearchButton = By.xpath("(//button[normalize-space()='Search'])");
    By checkAreaNameField = By.cssSelector("div[class^='suggestion-item']");
    By checkApartmentTypeField = By.cssSelector("div[id^='react-select-7-']");

    public LandingPage(WebDriver driver) throws IOException {
        FileInputStream fis = new FileInputStream("src\\main\\java\\resources\\data.properties");
        props.load(fis);
        this.driver = driver;
    }

    public WebElement webClickOnBuyNowOption() {
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(clickOnBuyPropertyOption));
        boolean elementSelected = driver.findElement(clickOnBuyPropertyOption).isSelected();
        return driver.findElement(clickOnBuyPropertyOption);
    }

    public WebElement webClickOnCityDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(clickOnCityDropdownOption));
        return driver.findElement(clickOnCityDropdownOption);
    }

    public void webSelectCity(){
        String cityName = props.getProperty("city");
        List<WebElement> cities = driver.findElements(By.cssSelector("div[id^='react-select-2-option-']"));
        for (WebElement webElement : cities) {
            if (webElement.getText().equalsIgnoreCase(cityName)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
                webElement.click();
                break;
            }
        }
    }

    public WebElement webEnterAreaName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterAreaNameInSearchField));
        return driver.findElement(enterAreaNameInSearchField);
    }

    public WebElement webEnterSecondAreaName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterSecondAreaNameInSearchField));
        return driver.findElement(enterSecondAreaNameInSearchField);
    }

    public void webSelectFirstArea(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkAreaNameField));
        String areaNameOne = props.getProperty("selectAreaOne");
        List<WebElement> Areas = driver.findElements(By.cssSelector("div[class^='suggestion-item']"));
        for (WebElement Area : Areas) {
            if (Area.getText().equalsIgnoreCase(areaNameOne)) {
                Area.click();
                break;
            }
        }
    }

    public void webSelectSecondArea() throws InterruptedException {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(checkAreaNameField));
        String areaNameTwo = props.getProperty("selectAreaTwo");
        List<WebElement> Areas = driver.findElements(By.cssSelector("div[class^='suggestion-item']"));
        for (WebElement Area : Areas) {
            if (Area.getText().equalsIgnoreCase(areaNameTwo)) {
                Area.click();
                break;
            }
        }
    }

    public WebElement webClickApartmentField() throws InterruptedException {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(clickOnApartmentTypeDropdownField)));
        return driver.findElement(clickOnApartmentTypeDropdownField);
    }

    public void webSelectApartmentType(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(checkApartmentTypeField));
        List<WebElement> Checkboxes = driver.findElements(By.cssSelector(("div[id^='react-select-7-']")));
        for (int i = 2; i < 4; i++) {
            Checkboxes.get(i).click();
        }
    }

    public WebElement webClickOnSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(clickOnSearchButton));
        return driver.findElement(clickOnSearchButton);
    }

    }






