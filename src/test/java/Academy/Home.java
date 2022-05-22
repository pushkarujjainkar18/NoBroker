package Academy;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import pageObjects.propertyPage;
import resources.Base;

import java.io.FileInputStream;
import java.io.IOException;

public class Home extends Base {

    @Test
    public void VerifySearchFunctionalityForTheMultipleLocalityAndDescriptionTagForTheProperty() throws IOException, InterruptedException {
        driver = initializeDriver();
        FileInputStream fis = new FileInputStream("src\\main\\java\\resources\\data.properties");
        props.load(fis);
        String url= props.getProperty("url");
        driver.manage().window().maximize();
        driver.get(url);
        LandingPage l = new LandingPage(driver);
        l.webClickOnBuyNowOption().click();
        l.webClickOnCityDropdown().click();
        l.webSelectCity();
        l.webEnterAreaName().sendKeys(props.getProperty("area"));
        l.webSelectFirstArea();
        l.webEnterAreaName().click();
        l.webEnterSecondAreaName().sendKeys(props.getProperty("area"));
        l.webSelectSecondArea();
        l.webClickApartmentField().click();
        l.webSelectApartmentType();
        l.webClickOnSearchButton().click();

        propertyPage p= new propertyPage(driver);
        p.selectProperty();
        p.goToChildPage();
        p.checkIfDescriptionFieldIsPresent();
        p.clickOnShowMoreToExpandDescriptionField().click();
        p.getDescriptionFieldText();
    }
}


