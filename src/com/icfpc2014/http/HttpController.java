package com.icfpc2014.http;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luba on 7/24/2014.
 */
public class HttpController {
    private HttpClient client = new DefaultHttpClient();
    private HttpPost post = new HttpPost("http://torgi.minjust.gov.ua/inc/php/content.php");

    {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("stan", "0"));
        nvps.add(new BasicNameValuePair("add_info", ""));

        try {
            post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        post.addHeader("X-Requested-With", "XMLHttpRequest");
        //post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36");
        //post.addHeader("Origin", "http://torgi.minjust.gov.ua");
        post.addHeader("Referer", "http://torgi.minjust.gov.ua/");
        //post.addHeader("Cookie", "PHPSESSID=qjf9rtiug3mkva1adjiahhemc0; _ga=GA1.3.855529154.1399448065");
    }

    public void post() throws IOException {
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = rd.readLine();
        EntityUtils.consumeQuietly(response.getEntity());
        System.out.println(line);
    }

    public void get() throws IOException {
        HttpGet get = new HttpGet("http://torgi.minjust.gov.ua/inc/php/content.php");
        HttpResponse response = client.execute(get);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        System.out.println(rd.readLine());
    }

}
