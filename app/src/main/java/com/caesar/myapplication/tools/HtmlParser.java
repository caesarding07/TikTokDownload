package com.caesar.myapplication.tools;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Html 分析程序
public class HtmlParser {

    //cookie
    public HashMap<String,String> convertCookie(String cookie) {
        HashMap<String,String> cookiesMap = new HashMap<String ,String>();
        String[] items = cookie.trim().split(";");
        for (String item:items){
            cookiesMap.put(item.split("=")[0],item.split("=")[1]);
        }
        return cookiesMap;
    };

    public String parser(String htmlURL) throws IOException {
        LogUtil logUtil = new LogUtil();
        String cookieTest = "MONITOR_WEB_ID=73bcdea6-c51c-4e98-b4c6-afce6e91517d; passport_csrf_token_default=82340d6338c7c7e0696164f2467d8da0; passport_csrf_token=82340d6338c7c7e0696164f2467d8da0; ttcid=ec7822f6f65b469bbecbf0425043e18642; s_v_web_id=verify_575d17696c712129940573a3945daa77; MONITOR_DEVICE_ID=f342e5b4-a906-46b6-bdfa-817c186b0a46; tt_scid=FTLgq3-6VK.DtqmwkN0EN3fshCxRm68ANnQh44VMtP5nQCy9x0WIf-E6pwXN1KRReb1c; msToken=54klm1pGNDcyS4T4ICSO1nxpTZCCtQMCZbxj-83_LcQc2Qn_WdJ74wW1k_bWrso6AtEkEVHS9FFnytzqjhwK3jfwL9HylfX6ReqeUHrO6luQwyj8fKSjWNSbYQ==";
        HashMap<String,String> myCookie = convertCookie(cookieTest);
        String html = Jsoup.connect(htmlURL)
                .ignoreContentType(true)
                .cookies(myCookie)
                .execute()
                .body();
        //String deHtml = URLDecoder.decode(html,"UTF-8");
        //logUtil.v("TAG",html);
        //String testHtml = "playAddrhhhhhhhmime_type";
        // (?<=exp2)exp1 匹配exp2后面的exp1
        // 匹配 playAddr和mime_type之间的字符
        String pattern = "(?<=playAddr).*?(?=mime_type)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(html);
        m.find();
        String videoURL = "https://"+URLDecoder.decode(m.group(0)).split("//")[1];
        Log.i("TAG_PARSER", "videoURL = "+videoURL);
        return videoURL;

        /***
        Document document = Jsoup.connect(htmlURL).data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(3000)
                .post();
        logUtil.v("TAG",String.valueOf(document));
         ***/

//
//        Elements link = document.getElementsByTag("video");


    }
}
