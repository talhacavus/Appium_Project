import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BasePage extends BaseTest {

    Logger logger = LogManager.getLogger(BasePage.class);
    Wait wait = new FluentWait(appiumDriver)
            .withTimeout(10, TimeUnit.SECONDS)
            .pollingEvery(250, TimeUnit.MILLISECONDS)
            .ignoring(NoSuchElementException.class)
            .ignoring(TimeoutException.class);


    @Step("<wait> saniye kadar bekle")
    public void waitForseconds(int wait) throws InterruptedException {
        Thread.sleep(1000 * wait);
    }

    @Step("Uygulamanın açıldığı kontrol edilir")
    public void control() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.ozdilek.ozdilekteyim:id/tv_startShoppingStore")));
        String buildUptext = appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/tv_startShoppingStore")).getText();
        Assert.assertEquals("ALIŞVERİŞE BAŞLA", buildUptext);
        System.out.println("Giris yapildi " + buildUptext);
        logger.info("Giris yapildi " + buildUptext);


    }

    @Step("alışverişe başla <id> butonuna tıklandı")
    public void shoppingClickid(String id) {
        appiumDriver.findElement(By.id(id)).click();
        System.out.println(id + " tiklandi");
        logger.info(id + " tiklandi");


    }

    @Step("Alışveriş sayfasının açıldıgını doğrulama ")
    public void shoppingControl() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='android.widget.TextView' and @text='Mağaza']")));
        String shoppingText = appiumDriver.findElement(By.xpath("//*[@class='android.widget.TextView' and @text='Mağaza']")).getText();
        Assert.assertEquals("Mağaza", shoppingText);
        System.out.println("Alisveris sayfasina girildi " + shoppingText);
        logger.info("Alisveris sayfasina girildi " + shoppingText);

    }

    @Step("kategoriler <id> butonuna tıklandı")
    public void kategoriClickid(String id) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        appiumDriver.findElement(By.id(id)).click();
        System.out.println(id + " tiklandi");
        logger.info(id + " tiklandi");

    }

    @Step("Kategoriler sayfasının açıldıgını doğrulama")
    public void kategoriControl() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class= 'android.widget.TextView' and @text = 'Markalar']")));
        String kategoriText = appiumDriver.findElement(By.xpath("//*[@class= 'android.widget.TextView' and @text = 'Markalar']")).getText();
        Assert.assertEquals("Markalar", kategoriText);
        System.out.println("Kategoriler sayfasina girildi " + kategoriText);
        logger.info("Kategoriler sayfasina girildi " + kategoriText);

    }

    @Step("Kadın Sekmesi <id> butonuna tıklandı")
    public void kadınClickid(String xpath) {
        appiumDriver.findElement(By.xpath(xpath)).click();
        System.out.println(xpath + " tiklandı");
        logger.info(xpath + " tiklandı");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));


    }

    @Step("Pantolona sekmesine kaydırma")
        public void scrollOne()  {
            Dimension dimension = appiumDriver.manage().window().getSize();
            int start_x = (int) (dimension.width * 0.7);
            int start_y = (int) (dimension.height * 0.7);

            int end_x = (int) (dimension.width * 0.5);
            int end_y = (int) (dimension.height * 0.5);

            TouchAction touch = new TouchAction(appiumDriver);
            touch.press(PointOption.point(start_x, start_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(end_x, end_y)).release().perform();
    }

    @Step("Pantolon Sekmesi <xpath> butonuna tıklandı")
    public void pantolonClickid(String xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        appiumDriver.findElement(By.xpath(xpath)).click();
        System.out.println(xpath + " tiklandi");
        logger.info(xpath + " tiklandi");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

    }

    @Step("Sayfa sonuna iki defa üstüste kaydırma")
    public void scrollTwo() {
        for (int i = 1; i < 3; i++) {
            TouchAction action = new TouchAction(appiumDriver);
            action.press(PointOption.point(538, 1538)).moveTo(PointOption.point(538, 420)).release().perform();
        }
    }
    @Step("Rasgele ürün seçimi yapılır")
    public void random() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'androidx.recyclerview.widget.RecyclerView']//android.view.ViewGroup")));
        Random rnd = new Random();
        List<MobileElement> pd = appiumDriver.findElements(By.xpath("//*[@class = 'androidx.recyclerview.widget.RecyclerView']//android.view.ViewGroup"));
        MobileElement element = pd.get(rnd.nextInt(pd.size()));
        System.out.println("element" + element);
        logger.info("element" + element);
        element.click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'androidx.recyclerview.widget.RecyclerView']//android.view.ViewGroup")));
    }

    @Step("Ürün detay sayfası açıldığı konttol edildi")
    public void productDetailControl() {
        String productDetailText = appiumDriver.findElement(By.xpath("//*[@resource-id = 'com.ozdilek.ozdilekteyim:id/relLayAddCartBtn']//android.widget.TextView")).getText();
        Assert.assertEquals("SEPETE EKLE", productDetailText);
        System.out.println("Alisveris sayfasina girildi " + productDetailText);
        logger.info("Alisveris sayfasina girildi " + productDetailText);
    }

    @Step("Favoriler <id> butonuna tıklandı")
    public void favouriteClickid(String id) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        appiumDriver.findElement(By.id(id)).click();
        System.out.println(id + " tiklandi");
        logger.info(id + " tiklandi");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }

    @Step("Giriş yapma sayfasının açıldığının doğrulanması")
    public void loginPageControl() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.ozdilek.ozdilekteyim:id/btnLogin")));
        String loginText = appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/btnLogin")).getText();
        Assert.assertEquals("Giriş Yap", loginText);
        System.out.println("Login sayfasina gidildi " + loginText);
        logger.info("Login sayfasina gidildi " + loginText);
    }

    @Step("Kullanıcı adı texte gönderilir")
    public void sendUser(){

        appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/etEposta")).sendKeys("example@testinium.com");
    }

    @Step("Parola texte gönderilir")
    public void sendPassword(){

        appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/etPassword")).sendKeys("1q2w3e4r5t");
    }

    @Step("Geri tuşuna arka arkaya 2 defa basılır")
    public void backButton() throws InterruptedException {
        for (int i = 1; i < 3; i++) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.ozdilek.ozdilekteyim:id/ivBack")));
            appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/ivBack")).click();
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.ozdilek.ozdilekteyim:id/ivBack")));
        }
    }
    @Step("Sepete ürün ekleme")
    public void addCart(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'androidx.recyclerview.widget.RecyclerView']//android.widget.TextView")));
        appiumDriver.findElement(By.xpath("//*[@class = 'androidx.recyclerview.widget.RecyclerView']//android.widget.TextView")).click();
        appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/cardAddToCart")).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.ozdilek.ozdilekteyim:id/cardAddToCart")));


    }






}