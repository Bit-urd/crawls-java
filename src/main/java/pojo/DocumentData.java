package pojo;

import java.util.List;
import java.util.Map;

/**
 * @Program: crawlsProject
 * @Description: DocumentData
 * @Author: BitterGourd
 * @Date: 2020-05-08 19:34
 */
public class DocumentData {  // 封装一下每张页面的数据,[文本、文件的地址信息、]
    private List<String> pics;  // 我感觉也可以像根据正则区分类型，每一种正则
    private List<String> musics;
    private List<String> videos;
    private List<String> links;
    // 也可以再定义多种想要的数据  比如天气数据、土壤数据

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public List<String> getMusics() {
        return musics;
    }

    public void setMusics(List<String> musics) {
        this.musics = musics;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public DocumentData() {
    }
}
