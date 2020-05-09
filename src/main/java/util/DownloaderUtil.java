package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

/**
 * @Program: crawlsProject
 * @Description: DownloaderUtil
 * @Author: BitterGourd
 * @Date: 2020-05-09 01:15
 */
public class DownloaderUtil {

    public static void downloadPic(String urlString, String filename,String savePath) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5*1000);
        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf=new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename+".jpg");
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

    public static void main(String[] args) throws Exception {
        DownloaderUtil.downloadPic("https://mmbiz.qpic" +
                ".cn/mmbiz_jpg" +
                "/A9I9MQEZqv7HpRNfIsRTQJovrQZXvr2rb1pMXniaaBaNzictHt6R2IHBIT4o5mA7icNJroXFZ66EvjaX4uQciaF6SQ/640" +
                "?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1", String.valueOf(UUID.randomUUID()), ".\\jpg");
    }
}
