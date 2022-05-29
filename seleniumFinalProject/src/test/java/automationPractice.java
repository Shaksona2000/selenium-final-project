import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;


public class automationPractice {
    public WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void CrossBrowserTest(String browser)throws Exception{
        if(browser.equalsIgnoreCase("Edge")){
            WebDriverManager.edgedriver().setup();
            driver= new EdgeDriver();
            driver.manage().window().maximize();

        } else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();


        }
    }


    @Test
    public void WomanTshirt() throws IOException {
        driver.get("http://automationpractice.com/index.php");
        Date currentdate = new Date() ;
        String screenshotfile=currentdate.toString().replace(" ","-").replace(":","-");
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
       FileUtils.copyFile(source,new File(".//SeleniumScreenshots/"+screenshotfile+".png"));

// this action hovers the elemnt and clicks
        WebElement elem = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a"));
        Actions act = new Actions(driver);
        act.moveToElement(elem).perform();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[1]/ul/li[1]/a"))).click();
        // this action finds the specific item and clicks it
        WebElement picture = driver.findElement(By.cssSelector("#center_column > ul > li > div > div.left-block > div > a.product_img_link > img"));


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", picture);
        picture.click();
        // this action looking for small images, and adding 2 M size in cart
        driver.switchTo().frame(0);
        WebElement bigpic = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#bigpic")));
        WebElement elem1 = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#thumb_2")));
        elem1.click();
        WebElement elem2 = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#thumb_3")));
        elem2.click();
        WebElement elem3 = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#thumb_4")));
        elem3.click();
        WebElement bigpic1 = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#bigpic")));
        if (bigpic.getAttribute("src") == bigpic1.getAttribute("src")) {
            System.out.println("picture did not change");
        } else {
            System.out.println("picture have been changed");
        }
        js.executeScript("document.getElementById('quantity_wanted').value='2';");
        driver.findElement(By.xpath("//*[@id=\"uniform-group_1\"]")).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"group_1\"]/option[2]"))).click();
        driver.findElement(By.className("exclusive")).click();
//        this action goes to main frame and adding "printed  cloth" in to the cart
        driver.switchTo().parentFrame();
        WebElement elementii = driver.findElement(By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > span"));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(elementii)).click();
        WebElement refresh = driver.findElement(By.xpath("//*[@id=\"header_logo\"]/a/img"));
        new WebDriverWait(driver, 10);
        js.executeScript("arguments[0].scrollIntoView();", refresh);
        refresh.click();
        WebElement dress = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a"));

        act.moveToElement(dress).perform();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#block_top_menu > ul > li:nth-child(2) > ul > li:nth-child(1) > a"))).click();
        WebElement add = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li"));
        act.moveToElement(add).perform();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#center_column > ul > li > div > div.right-block > div.button-container > a.button.ajax_add_to_cart_button.btn.btn-default"))).click();

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span"))).click();
        WebElement chekout = driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a"));
        act.moveToElement(chekout).perform();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"button_order_cart\"]/span/i"))).click();
        new WebDriverWait(driver, 10);
       WebElement table=driver.findElement(By.xpath("//*[@id=\"cart_summary\"]/tbody/tr[1]"));
       js.executeScript("arguments[0].scrollIntoView();",table);
      List<WebElement>ele=table.findElements(By.tagName("td"));
              for(int j=0; j<ele.size();j++){
                  List<WebElement>ele1=ele.get(j).findElements(By.className("cart_description"));
                      for(WebElement el:ele1){
                         if(ele1.contains(driver.findElement(By.tagName("a")))){
                             System.out.println(el.getAttribute("href"));
                               if(el.getAttribute("href").equals(picture.getAttribute("href"))){
                                 System.out.println("pictures are same");
                               }

                         }

                      }
              }

        WebElement table1=driver.findElement(By.xpath("//*[@id=\"cart_summary\"]/tbody/tr[2]"));
        js.executeScript("arguments[0].scrollIntoView();",table);
        List<WebElement>ele12=table1.findElements(By.tagName("td"));
        for(int j=0; j<ele12.size();j++){
            List<WebElement> ele1=ele.get(j).findElements(By.className("cart_description"));
            for (WebElement el:ele1){
                 if(ele1.contains(driver.findElement(By.tagName("a")))){
                    System.out.println(el.getAttribute("href"));
                    if(el.getAttribute("href").equals(picture.getAttribute("href"))){
                        System.out.println("pictures are same");
                    }

                }
            }

        }


        WebElement scroll = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]"));
        js.executeScript("arguments[0].scrollIntoView();", scroll);
        WebElement total = driver.findElement(By.xpath("//*[@id=\"total_price\"]"));
        String price=total.getText();
        scroll.click();
        WebElement scroll1 = driver.findElement(By.cssSelector("#SubmitCreate"));
        js.executeScript("arguments[0].scrollIntoView();", scroll1);
        //comparing prices
        String generatedstrings = RandomStringUtils.randomAlphabetic(8);
        String email = generatedstrings + "@gmail.com";
        driver.findElement(By.xpath("//*[@id=\"email_create\"]")).sendKeys(email);
        scroll1.click();
     // sign-in action
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"id_gender1\"]"))).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("shako");
        driver.findElement(By.id("customer_lastname")).sendKeys("beridze");
        driver.findElement(By.id("passwd")).sendKeys("shako123");
        driver.findElement(By.xpath("//*[@id=\"days\"]")).sendKeys("3");
        driver.findElement(By.xpath("//*[@id=\"months\"]")).sendKeys("m");
        driver.findElement(By.xpath("//*[@id=\"years\"]")).sendKeys("2000");
        try {
            WebElement submit = driver.findElement(By.xpath("//*[@id=\"submitAccount\"]"));
            js.executeScript("arguments[0].scrollIntoView();", submit);
            submit.click();
            js.executeScript("window.scrollBy(0,900)");

        } catch (UnhandledAlertException e){
            System.out.println(e.getMessage()+ "you must fill Adress");
        }
        driver.findElement(By.id("address1")).sendKeys("sdss");
        driver.findElement(By.id("city")).sendKeys("luisiana");
        js.executeScript("document.getElementById('id_state').value='2';");
        driver.findElement(By.id("postcode")).sendKeys("45444");
        js.executeScript("document.getElementById('id_country').value='21';");
        driver.findElement(By.id("phone_mobile")).sendKeys("574875252");
        driver.findElement(By.id("alias")).sendKeys("wasfdd");
        WebElement pass= driver.findElement(By.id("passwd"));
        pass.sendKeys("shako123");

        WebElement submit11 = driver.findElement(By.xpath("//*[@id=\"submitAccount\"]"));
        js.executeScript("arguments[0].scrollIntoView();", submit11);
        submit11.click();

        WebElement submit2 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button"));
        js.executeScript("arguments[0].scrollIntoView();", submit2);
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(submit2)).click();
        WebElement submit3 = driver.findElement(By.xpath("//*[@id=\"form\"]/p/button"));
        js.executeScript("arguments[0].scrollIntoView();", submit3);
        //this action chatches exeption and handles it
        try {
            new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(submit3)).click();
            new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"order\"]/div[2]/div/div/a"))).click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"form\"]/div/p[2]/label"))).click();
            submit3.click();
        } catch (ElementNotInteractableException e) {
            System.out.println(e.getMessage() + "you must accept therms and conditions");
        }

        WebElement check = driver.findElement(By.className("cheque"));
        js.executeScript("arguments[0].scrollIntoView();", check);
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(check)).click();
        WebElement submit4 = driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button"));
        js.executeScript("arguments[0].scrollIntoView();", submit4);
        WebElement total1=driver.findElement(By.xpath("//*[@id=\"amount\"]"));
        String price1=total1.getText();
        if (price1.equals(price)){
         System.out.println("the price is equal");
        }
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(submit4)).click();
        WebElement conditions = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/a"));
        js.executeScript("arguments[0].scrollIntoView();",conditions);
//this action Chooses heading and order reference, uploads file, add message text and Send
        new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(conditions)).click();
        WebElement drop = driver.findElement(By.id("id_contact"));
        Select dropdown= new Select(drop);
        dropdown.selectByValue("2");
        WebElement reference = driver.findElement(By.cssSelector("#center_column > form > fieldset > div.clearfix > div.col-xs-12.col-md-3 > div:nth-child(6) > div > select"));
        js.executeScript("arguments[0].scrollIntoView();",reference);
        Select order=new Select(reference);
        order.selectByIndex(1);
        WebElement uploadFile=driver.findElement(By.xpath("//*[@id=\"uniform-fileUpload\"]/span[1]"));
        js.executeScript("arguments[0].scrollIntoView();",uploadFile);
        new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(uploadFile));
        WebElement txt= driver.findElement(By.cssSelector("#message"));
        txt.sendKeys("iyo arabets rostevan");
        try {
            
            uploadFile.sendKeys("C:\\Users\\Lchubinidze\\IdeaProjects\\seleniumFinalProject\\src\\test\\java\\shako");
        }catch (ElementNotInteractableException e){
            System.out.println("element not interactable");
        }
        WebElement send= driver.findElement(By.id("submitMessage"));
        js.executeScript("arguments[0].scrollIntoView();",send);
        send.click();


        TakesScreenshot screenshot1 = (TakesScreenshot)driver;
        File source1 = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source1,new File(".//SeleniumScreenshots/"+screenshotfile+".png"));
    }
}









