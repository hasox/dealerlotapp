package tk.dealerlot.pages;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import tk.dealerlot.common.Driver;

public class AddCarPage {

    private By yearInput = By.id("year");
    private By makeSelect = By.id("make");
    private By modelInput = By.id("model");
    private By colorInput = By.id("color");
    private By stockInput = By.id("stock");
    private By imageInput = By.id("image");
    private By addCarButton = By.cssSelector("button[type='submit']");


            public void goToPage(){
                Driver.getDriver().get("http://www.dealerlot.tk/add");
            }

            public void enterYear(int year){
                Driver.getDriver().findElement(yearInput).sendKeys(year + "");
            }
            public void enterModel(String model){
                Driver.getDriver().findElement(modelInput).sendKeys(model);
            }
             public void enterColor(String color){
        Driver.getDriver().findElement(colorInput).sendKeys(color);
    }
         public void enterStock(int stock){
        Driver.getDriver().findElement(stockInput).sendKeys(stock + "");
    }
         public void enterImage(String image){
        Driver.getDriver().findElement(imageInput).sendKeys(image);
    }
    public void selectMake(String make){
        WebElement selectEl = Driver.getDriver().findElement(makeSelect);
        Select makeSelect = new Select(selectEl);
       try {
           String makeString = make.substring(0, 1).toUpperCase() + make.substring(1).toLowerCase();
           makeSelect.selectByVisibleText(makeString);
       }
           catch(IllegalArgumentException e){
           e.printStackTrace();
           }
    }
    public void clickAddCar(){
                Driver.getDriver().findElement(addCarButton).click();
    }

}
