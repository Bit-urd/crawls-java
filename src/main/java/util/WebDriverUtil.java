package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @Program: crawlsProject
 * @Description: WebDriver
 * @Author: BitterGourd
 * @Date: 2020-05-08 19:27
 */
public class WebDriverUtil {
    private static WebDriver webDriver;

    private WebDriverUtil() {
    }

    public static void waitDriver(WebDriver driver){

//        WebDriverWait driverWait = new WebDriverWait(driver,1);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public static WebDriver getChromeDriver(){
        if (webDriver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenovo\\AppData\\Local\\Google\\Chrome\\Application" +
                            "\\chromedriver.exe");
            // FirefoxDriver()            火狐浏览器
            //谷歌浏览器
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("-headless");
            webDriver = new ChromeDriver(chromeOptions);
        }
        return webDriver;
    }
}
