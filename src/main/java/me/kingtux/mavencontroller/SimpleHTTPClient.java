package me.kingtux.mavencontroller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/***
 * This is just and internal library for mavencontroller
 *
 * @author KingTux
 */
public class SimpleHTTPClient {
    private HttpURLConnection urlConnection;
    private boolean ableToConnect=true;

    private SimpleHTTPClient() {

    }

    public static SimpleHTTPClient create(String url) {
        try {
            return create(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SimpleHTTPClient create(URL url) {
        SimpleHTTPClient simpleHTTPClient = new SimpleHTTPClient();
        try {
            simpleHTTPClient.urlConnection = (HttpURLConnection) url.openConnection();
            simpleHTTPClient.urlConnection.setRequestMethod("GET");
            simpleHTTPClient.urlConnection.setConnectTimeout(5000);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            simpleHTTPClient.urlConnection.connect();
            if (simpleHTTPClient.urlConnection.getResponseCode() != 200) {
                simpleHTTPClient.ableToConnect = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            simpleHTTPClient.ableToConnect = false;
        }
        return simpleHTTPClient;
    }

    public boolean download(File file) {
        if(getResponseCode()!=200){
            System.out.println("getResponseCode() = " + getResponseCode());
            return false;
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            ReadableByteChannel rbc = Channels.newChannel(urlConnection.getInputStream());
            FileOutputStream fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public HttpURLConnection getUrlConnection() {
        return urlConnection;
    }

    public boolean isAbleToConnect() {
        return ableToConnect;
    }
    public int getResponseCode(){
        try {
            return urlConnection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
            return 500;
        }
    }
}
