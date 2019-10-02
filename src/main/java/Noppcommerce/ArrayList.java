package Noppcommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ArrayList extends Utils {

    LoadProps loadProps = new LoadProps();
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void openbrowser() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\BrowserDriver\\chromedriver.exe");

        //open the browser
        driver = new ChromeDriver();

        //open the website
        driver.get(loadProps.getProperty("url"));
    }
    @AfterMethod
    public void closeBrowser()
     {driver.quit();}

      @Test

    public void userShouldBeAbleToAddProductToCompareListAndClearItSuccessfully() {

          //add to compare list Build your own computer
          clickElement(By.xpath("//input[@onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/1\"),!1']"));

          //wait for element
          waitForElementVisible(By.xpath("//p/a[@href=\"/compareproducts\"]"), 10);

          //assert
          softAssert.assertTrue(driver.findElement(By.xpath("//p/a[@href=\"/compareproducts\"]")).isDisplayed());

          //add to compare list $25 Virtual Gift Card
          clickElement(By.xpath("//input[@onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/43\"),!1']"));

          ////wait for element
          waitForElementVisible(By.linkText("product comparison"), 10);

          //assert
          softAssert.assertTrue(driver.findElement(By.xpath("//p/a[@href=\"/compareproducts\"]")).isDisplayed());

          //click on compare list
          clickElement(By.linkText("product comparison"));

          //wait for element
          waitForelementClickable(By.linkText("Compare products list"), 10);

          //click on compare list
          clickElement(By.linkText("Compare products list"));

          //compare product 1
          String expectedProduct1 = "Build your own computer";
          String actualProduct1 = getTextFromElement(By.linkText("Build your own computer"));

          Assert.assertEquals(expectedProduct1, actualProduct1);
          //compare product 2
          String expectedProduct2 = "$25 Virtual Gift Card";
          String actualProduct2 = getTextFromElement(By.linkText("$25 Virtual Gift Card"));

          Assert.assertEquals(expectedProduct2, actualProduct2);

          //click on clear list
          driver.findElement(By.xpath("//a[@class=\"clear-list\"]")).click();

          //assert 3
          String expectedResult3 ="You have no items to compare.";
          String actualResult3 = getTextFromElement(By.xpath("//div[@class=\"no-data\"]"));

          Assert.assertEquals(expectedResult3,actualResult3);
      }

@Test
        public void userShoulsBeAbleToPutCommentOnBottomSuccessfully(){

        //click on details
        clickElement(By.linkText("New online store is open!"));

        //enter title
        enterText(By.xpath("//input[@class=\"enter-comment-title\"]"),loadProps.getProperty("Title"));

        //enter comment
        enterText(By.xpath("//textarea[@id=\"AddNewComment_CommentText\"]"),loadProps.getProperty("Comment"));

        //click on Add comment
       clickElement(By.xpath("//input[@name=\"add-comment\"]"));

       String expectedResult1 = "News comment is successfully added.";
       String actualResult1 = getTextFromElement(By.className("result"));

       Assert.assertEquals(expectedResult1,actualResult1);

       //list of all comment
         List<WebElement> allcommentlist  = driver.findElements(By.xpath("//strong[@class=\"comment-text\"]"));

         //storing last comment
         WebElement lastElement = allcommentlist.get(allcommentlist.size()-1);
         String lastCommentText = lastElement.getText();

       softAssert.assertEquals(lastElement,lastCommentText);

}
@Test
    public void userShouldBeAbleToSeachProductByNameInSearchButton(){

        //enter search keyword
        enterText(By.xpath("//input[@class=\"search-box-text ui-autocomplete-input\"]"),loadProps.getProperty("Search"));

        //click on search
         clickElement(By.xpath("//input[@type=\"submit\"]"));

         //list of searced keyword
         List<WebElement> al = driver.findElements(By.xpath("//div[@class=\"details\"]"));

         //size of list
         System.out.println(al.size());
         int count = 0;
         for(WebElement e :al) {

        if (e.getAttribute("outerHTML").contains("Nike")) {
            count++;
        } else {
            System.out.println("No Add to cart button " + e.getText());
        }
         }System.out.println(count);

       //enter search keyword
       enterText(By.xpath("//input[@class=\"search-box-text ui-autocomplete-input\"]"),loadProps.getProperty("find"));

       //click on search
       clickElement(By.xpath("//input[@type=\"submit\"]"));

       String expectedResult = "No products were found that matched your criteria.";
       String actualResult =getTextFromElement(By.xpath("//div[@class=\"no-result\"]"));

        Assert.assertEquals(expectedResult,actualResult);


    }
}













