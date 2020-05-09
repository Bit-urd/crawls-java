package parse;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Program: crawlsProject
 * @Description: service.ParseService
 * @Author: BitterGourd
 * @Date: 2020-05-08 19:29
 */
public class ParseByRegexService {

    // 可能需要捕获组的方法，传入一个数组
    public static List<String> baseParse(String document, String re,int... arr) {
        List<String> result = new ArrayList<>();
        Pattern compile = Pattern.compile(re);
        Matcher matcher = compile.matcher(document);

        // <img.*data-src="(.*?)"
        //  \< *[img][^\\>]*[src] *= *[\"\']{0,1}([^\\"\\'\\ >]*)
        while (matcher.find()) {
            if(arr != null){
                try {
                    for (int i = 0; i < arr.length; i++) {
                        result.add(matcher.group(arr[i]));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println(matcher.group());
                result.add(matcher.group());
            }
        }
        return result;
    }

    @Test
    public void unitTest() {
        baseParse("ne-block;line-height: 0;\"><img         class=\"raw-image img_l" +
                "oading\" data-ratio=\"0.7777778\"         data-src=\"https://mmbiz.qpic.cn/mmb" +
                "iz_jpg/3Xbx2HV4F3dAuwAO4p8a5BbYIpLK3l1RAYQ2MI5RBFfodxVUnNxJfNdibhudSuA5KQJO82Nh0" +
                "HmbR4ibOUZ9GNgQ/640?wx_fmt=jpeg\"         data-type=\"jpeg\" data-w=\"198\" st"
                , "<img.*data-src=\"(.*?)\"",1);
    }

}
