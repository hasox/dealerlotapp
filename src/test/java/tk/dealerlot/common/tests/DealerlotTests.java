package tk.dealerlot.common.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.dom.ParentSetter;
import tk.dealerlot.common.Driver;
import tk.dealerlot.pages.AddCarPage;
import tk.dealerlot.pages.EditCarPage;
import tk.dealerlot.pages.HomePage;
import tk.dealerlot.utils.DBUtil;

public class DealerlotTests {

        @Test
        public void verifyNumberOfCarsOnPageMatchNumberOfCarsInDatabase() {
            HomePage homePage = new HomePage();
            homePage.goToPage();
            int numberOfCarsOnPage = homePage.getNumberOfCarsOnPage();
            System.out.println("There are " + numberOfCarsOnPage + " on HomePage");
            int numberOfCarsDb = DBUtil.getNumberOfCarsFromDb();
            System.out.println("There are " + numberOfCarsDb + " in Database");
            Assert.assertEquals(numberOfCarsDb,numberOfCarsOnPage);

        }
        @Test
        public void verifyAddingNewCarCreatesRowForNewCarInDatabase(){
            AddCarPage addCarPage = new AddCarPage();
            addCarPage.goToPage();
            int stock = 576;
            addCarPage.enterYear(1976);
            addCarPage.selectMake("Honda");
            addCarPage.enterImage("https://secure4.motionfuze.com/assets/global/VTO/1248/SHHFK7H46KU416190-2.jpg");
            addCarPage.enterStock(stock);
            addCarPage.enterModel("Civicc");
            addCarPage.enterColor("HasanIsTestingThis");
            addCarPage.clickAddCar();
            Assert.assertTrue(DBUtil.doesStockExistInDb(stock));
        }
        @Test
        public void verifyDeletingNewCarDeletesRowInDatabase(){
            verifyAddingNewCarCreatesRowForNewCarInDatabase();
            HomePage homePage = new HomePage();
            homePage.goToPage();
            homePage.deleteCar(576);
            Assert.assertFalse(DBUtil.doesStockExistInDb(576));
            Driver.closeDriver();
        }
        @Test
        public void verifyEditingCarModel(){
            EditCarPage editCarPage = new EditCarPage();
            int stockNo = 576;
            editCarPage.goToPage(stockNo);
            String editedModel = "Accorddd";
            editCarPage.updateModel(editedModel);
            editCarPage.clickUpdateCar();
            String databaseModel = DBUtil.getModelNameByStockNumber(stockNo);
            Assert.assertEquals(databaseModel,editedModel);
            Driver.closeDriver();

        }
    }

