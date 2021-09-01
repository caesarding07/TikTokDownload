package com.caesar.myapplication.tools;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadTest {

    @Test
    public void download() throws IOException {
        String shareURL = "7.46 caa:/ 调色的意义是什么？下期更新pp值设置%摄影%仪式感 @DOU+小助手  https://v.douyin.com/d8p1a4o/ 复制此鏈接，打开Dou音搜索，矗接观看视频！";
        String pattern = "https.*?(?= )";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(shareURL);
        m.find();
        String htmlURL = m.group(0);

        String testHtmlURL = "https://v.douyin.com/d8p1a4o/";
        HtmlParser htmlParser = new HtmlParser();
        String videoURL = htmlParser.parser(htmlURL);

        String testVideoURL = "https://vd4.bdstatic.com/mda-mhv3t1ibn9c57c0d/540p/h264_cae/1630291246757788108/mda-mhv3t1ibn9c57c0d.mp4";
        Downloader downloader = new Downloader();
        downloader.download(videoURL);
    }
}