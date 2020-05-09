package parse;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
//import org.jsoup.nodes.Document;
import util.WebDriverUtil;

/**
 * @Program: crawlsProject
 * @Description: service.DocumentService
 * @Author: BitterGourd
 * @Date: 2020-05-08 19:29
 */
public class BaseService {
    public static final WebDriver webDriver = WebDriverUtil.getChromeDriver();
    public static String getDocument(String url){
//        Document doc = null;
        webDriver.get(url);
        JavascriptExecutor driver_js = (JavascriptExecutor) webDriver;
        driver_js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        String doc = webDriver.getPageSource();
//        doc = Jsoup.parse(webDriver.getPageSource());
        return doc;
    }
    public static String getDocument(String url,boolean needScroll){
//        Document doc = null;
        webDriver.get(url);
        JavascriptExecutor driver_js = (JavascriptExecutor) webDriver;
        if(needScroll){
            for(int i=0;i<3;i++) {
                driver_js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        String doc = webDriver.getPageSource();
//        doc = Jsoup.parse(webDriver.getPageSource());
        return doc;
    }

    @Test
    public void testDocument(){
        System.out.println(getDocument("http://mp.weixin.qq.com/mp/homepage?__biz=MzI2MzE2NDczMw==&hid=1&sn" +
                "=82535620c0eac83006257862b2d9ebd8&scene=18#wechat_redirect",false));
    }
}
