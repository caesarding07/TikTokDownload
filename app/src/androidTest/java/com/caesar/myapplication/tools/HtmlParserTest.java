package com.caesar.myapplication.tools;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;

public class HtmlParserTest {

    @Test
    public void htmlURL() throws IOException {
        String htmlURL = "https://v.douyin.com/d8p1a4o/";
        HtmlParser htmlParser = new HtmlParser();
        htmlParser.parser(htmlURL);
    }
}