package com.caesar.myapplication.tools;

import android.os.Environment;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {
    private static final int MAX_BUFFER_SIZE = 1000000;
    public void download(String videoURL) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        RandomAccessFile randomAccessFile = null;
        try {
            //1.获取链接对象
            URL url = new URL(videoURL);
            Log.w("TAG_Download","获取链接对象："+url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Range","bytes=0-");
            connection.connect();
            Log.i("TAG_Download", String.valueOf(connection.getResponseCode()));
            if (connection.getResponseCode()/100!=2){
                System.out.println("链接资源失败……");
                Log.w("TAG_Download","链接资源失败……");
                return;
            }else {
                Log.i("TAG_Download","资源链接成功……");
            }
            //2.
            Log.w("TAG_Download","获取对象数据");
            inputStream = connection.getInputStream();
            int downloaded = 0;
            int fileSize = connection.getContentLength();
            File extDir = Environment.getExternalStorageDirectory();
            Log.i("TAG_Download","下载目录："+extDir);
            String fileName = url.getFile();
            fileName = fileName.substring(fileName.lastIndexOf("/")+1);
            File fullFileName = new File(extDir,fileName);
            //3.
            randomAccessFile = new RandomAccessFile(fullFileName,"rw");
            while(downloaded<fileSize){
                byte[] buffer = null;
                if (fileSize - downloaded >= MAX_BUFFER_SIZE){
                    buffer = new byte[MAX_BUFFER_SIZE];
                }else {
                    buffer = new byte[fileSize - downloaded];
                }
                int read = -1;
                int currentDownload = 0;
                long startTime = System.currentTimeMillis();
                while(currentDownload < buffer.length){
                    read = inputStream.read();
                    buffer[currentDownload ++ ] = (byte) read;
                }
                long endTime = System.currentTimeMillis();
                double speed = 0.0;
                if (endTime - startTime > 0){
                    speed = currentDownload / 1024.0 /((double)(endTime- startTime));
                }
                randomAccessFile.write(buffer);
                downloaded += currentDownload;
                randomAccessFile.seek(downloaded);
                Log.i("TAG_Download","下载进度："+downloaded*1.0/fileSize*10000/100+"下载速度："+speed+"kb/s");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                connection.disconnect();
                Log.i("TAG_Download","资源下载结束……");
                inputStream.close();
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

