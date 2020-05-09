package Wechat;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import parse.BaseService;
import parse.ParseByJsoupService;
import parse.ParseByRegexService;
import pojo.DocumentData;
import util.DownloaderUtil;
import util.WebDriverUtil;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Program: crawlsProject
 * @Description: ImageParse
 * @Author: BitterGourd
 * @Date: 2020-05-08 21:21
 */
public class WechatImageParse {
    // 获取所有的需要爬的网页链接列表
    public static DocumentData parseAllLinks(String doc){
        DocumentData docData = new DocumentData();
        List<String> links = null;
        links = ParseByRegexService.baseParse(doc, "<a class=\"list_item js_post\" href=\"(.*?)\">",1);
        docData.setLinks(links);
        return docData;
    }

    // 需要一个文档、一个格式，返回一个图片列表
    public static DocumentData parseImageByRe(String doc){
        DocumentData docData = new DocumentData();
        List<String> pics = null;
        pics = ParseByRegexService.baseParse(doc, "<img(.*?)data-src=\"(.*?)\"",2);
        docData.setPics(pics);
        return docData;
    }

    public static void main(String[] args) {
        // 1. 先调用driver得到文本页面
        // 2. 得到所有链接
        // 3. 得到所有图片链接
        // 4. 下载图片
        String document = BaseService.getDocument("http://mp.weixin.qq.com/mp/homepage?__biz=MzI2MzE2NDczMw==&hid=1" +
                "&sn=82535620c0eac83006257862b2d9ebd8&scene=18#wechat_redirect",true);
        DocumentData documentData = parseAllLinks(document);
        List<String> links = documentData.getLinks();
        List<List<String >> picsStrLists = new ArrayList<>();
        links.forEach(s -> picsStrLists.add(parseImageByRe(BaseService.getDocument(s )).getPics()));
//        picsStrLists.forEach(lists -> lists.forEach(picstr -> DownloaderUtil.downloadPic(picstr, ".")));
        try {
            PrintStream ps = new PrintStream("log.txt");
            picsStrLists.forEach(lists -> lists.forEach(picStr -> {ps.println(picStr);
                try {
                    DownloaderUtil.downloadPic(picStr, UUID.randomUUID().toString(),".\\jpg\\");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("图片保存失败");
                }
            }));
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
