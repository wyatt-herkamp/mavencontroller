package me.kingtux.mavencontroller;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * these methods are here to help maven controller and this class shouldn't be used
 *
 * @author KingTux
 */
public class SimpleUtils {
    public static <T> T notNull(final T object, final String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }

    public static void download(URL url, File file) throws Exception {
        if (!isInternetAvailable()) {
            throw new IllegalArgumentException("No internet connection found!");
        }
        if (!isSiteOnline(url)) {
            return;
        }
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    public static void download(String url, String file) {
        try {
            download(new URL(url), new File(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isInternetAvailable() {
        return isSiteOnline("https://www.google.com/");
    }

    public static boolean isSiteOnline(String url) {
        try {
            return isSiteOnline(new URL(fixWebsiteURL(url)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isSiteOnline(final URL url) {
        try {
            final URLConnection conn = url.openConnection();
            conn.setConnectTimeout(5000);
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String fixWebsiteURL(final String s) {
        if (!isValidURL(s)) {
            return "http://" + s;
        }

        return s;
    }

    public static boolean isValidURL(final String s) {
        try {
            new URL(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getPathToM2() {
        return System.getProperty("user.home") + File.separator + ".m2/";
    }

    public static String getPathToLocalRepo() {
        return getPathToM2() + File.separator + "repository";
    }
}
