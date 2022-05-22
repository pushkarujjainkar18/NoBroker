package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import resources.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class propertyPage extends Base {

    public WebDriver driver;
    By selectPropertyField = By.cssSelector("div[class*='infinite-scroll-component__outerdiv']");
    By DescriptionID= By.id("description");
    By showMore= By.xpath("//span[normalize-space()='Show More']");
    By DescriptionSection= By.id("categoryAmenities");

    public propertyPage(WebDriver driver) throws IOException {
        FileInputStream fis = new FileInputStream("src\\main\\java\\resources\\data.properties");
        props.load(fis);
        this.driver=driver;
    }

    public void selectProperty() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectPropertyField));
        String selectProperty=props.getProperty("selectProperty");
        By article = By.xpath("//div[@id='listCardContainer']/descendant::article/descendant::h2/span[.='"+selectProperty+"']");
        Thread.sleep(5000);
        WebElement Artical= driver.findElement(article);
        scrollToElementUsingJS(Artical);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Artical);
    }

    public void goToChildPage(){
        Set<String> windows=driver.getWindowHandles();
        Iterator<String> it= windows.iterator();
        String parentID=it.next();
        String childID=it.next();
        driver.switchTo().window(childID);
    }

    public WebElement clickOnShowMoreToExpandDescriptionField(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(showMore));
        return driver.findElement(showMore);
    }

    public void checkIfDescriptionFieldIsPresent(){
       WebElement Description= driver.findElement(DescriptionID);
       scrollToElementUsingJS(Description);
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.visibilityOfElementLocated(DescriptionSection));
       Assert.assertTrue(driver.findElement(DescriptionSection).isDisplayed());
    }

    public void getDescriptionFieldText(){
        WebElement isDescriptionPresent= driver.findElement(DescriptionID);
        System.out.println(isDescriptionPresent.getText());
        Assert.assertTrue(isDescriptionPresent.isDisplayed());
        System.out.println("Description is present Test case passed");
        driver.quit();
    }

    private boolean isVisibleInViewport(WebElement element) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            "
                , element);
    }

    protected void scrollToElementUsingJS(WebElement webElement) {
        if (!isVisibleInViewport(webElement)) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
        }
    }
}
