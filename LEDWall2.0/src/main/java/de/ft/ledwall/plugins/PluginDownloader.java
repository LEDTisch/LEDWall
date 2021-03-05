/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.ledwall.plugins;


import de.ft.ledwall.data.DataManager;
import de.ft.ledwall.utils.CountingInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class PluginDownloader {

    public static HttpURLConnection openConnection(String fileURL) throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDefaultUseCaches(false);
        httpConn.setIfModifiedSince(-1);
        // int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        // if (responseCode == HttpURLConnection.HTTP_OK) {

        return httpConn;

        // } else {
        //    Program.logger.config("No file to download. Server replied HTTP code: " + responseCode);
        //    return null;
        //}


    }

    public static void downloadPlugin(String url, String name) throws IOException {

        File newPlugin = new File(DataManager.folder_apps.getAbsolutePath() +"/"+ name + ".lwa");
        FileOutputStream fileOutputStream = new FileOutputStream(newPlugin);
        HttpURLConnection connection;
        try {

            connection = openConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fehler beim connectionen");
            return;
        }

        long totalSize = connection.getContentLengthLong();

        if (totalSize < 0) {
            System.out.println("fehler beim herunterladen");
            return;
        }

        CountingInputStream countingInputStream = new CountingInputStream(connection.getInputStream());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println((int) (((float) countingInputStream.getBytesRead() / (float) totalSize) * 100f));
            }
        }, 0, 10);

        byte[] downloadedFile = countingInputStream.readAllBytes();
        timer.cancel();
        timer.purge();


        fileOutputStream.write(downloadedFile);
        System.out.println("plugin installiert");

        if (PluginLoader.loadPlugin(newPlugin)) {


        }else{
            System.out.println("Something went wrrroooonnggn");
        }


    }

}
