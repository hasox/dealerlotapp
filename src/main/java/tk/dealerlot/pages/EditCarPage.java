package tk.dealerlot.pages;

import org.openqa.selenium.By;
import tk.dealerlot.common.Driver;

public class EditCarPage {
    private By yearInput = By.id("year");
    private By makeSelect = By.id("make");
    private By modelInput = By.id("model");
    private By colorInput = By.id("color");
    private By stockInput = By.id("stock");
    private By imageInput = By.id("image");
    private By updateButton = By.cssSelector("button[type='submit']");

    public void goToPage(int stockNo){
        Driver.getDriver().get("http://dealerlot.tk/edit/"+stockNo);
    }

    public void updateModel(String model){
        Driver.getDriver().findElement(modelInput).clear();
        Driver.getDriver().findElement(modelInput).sendKeys(model);
    }
    public void clickUpdateCar(){
        Driver.getDriver().findElement(updateButton).click();
    }

}
