package de.ft.ledwall.api;

import de.ft.ledwall.ApplicationManager;
import de.ft.ledwall.Main;
import de.ft.ledwall.WebSocketClient;
import de.ft.ledwall.data.DataManager;
import de.ft.ledwall.nativeapps.licht.Licht;
import de.ft.ledwall.nativeapps.registration.Registration;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static de.ft.ledwall.Main.LedWalluuid;

public class ServerConnection {
    public String server="217.160.175.116:8146";
    public String apiKey;

    private WebSocketClient webSocketClient;
    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            try {
                if(!Main.serverConnection.checkAPIKey()) {
                    Main.serverConnection.registrate(LedWalluuid);

                        //Check for current running app
                    //Main.applicationManager.setApplication(new Licht());
                }
                startSocketConnection();
                pollConnection.stop();

            } catch (Exception e) {
                System.out.println("failed to check APIKey that means, that the server connection is not possible");
                e.printStackTrace();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    Timer pollConnection = new Timer(1500 ,taskPerformer);

/*
    private Thread pollConnection = new Thread(() -> {
        boolean search=true;
       while (search){
           try {
               if(!Main.serverConnection.checkAPIKey()) {
                   Main.serverConnection.registrate(LedWalluuid);
                   Main.applicationManager.setApplication(new Licht());
                   search=false;
                   System.out.println("hör auf");
               }
               startSocketConnection();
           } catch (Exception e) {
               System.out.println("failed to check APIKey that means, that the server connection is not possible");
               e.printStackTrace();
           }
           try {
               TimeUnit.MILLISECONDS.sleep(5000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    });*/
    public void startpollconnection(){
        if(!pollConnection.isRunning()) {
            pollConnection.start();
        }
    }
    public ServerConnection() {
    }
    public void setApiKey(String apikey) throws Exception {
        if(!checkAPIKey()) {
            this.apiKey = apikey;
            if(webSocketClient!= null && webSocketClient.isOpen()) {
                closeSocketConnection();
            }
        }
    }

    public org.java_websocket.client.WebSocketClient getWebSocketifconnected(){
        if(this.webSocketClient==null)return null;
        if(this.webSocketClient.isOpen()) {
            return this.webSocketClient;
        }else{
            return null;
        }

    }

    public void startSocketConnection(){
        try {
            webSocketClient= new WebSocketClient(new URI("ws://"+this.server+"/device/liveconnection?session="+this.apiKey));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        webSocketClient.connect();
    }
    public void closeSocketConnection(){
        this.webSocketClient.close();
    }

    public String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
    public String executePost(String targetURL, String urlParameters) {
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    public void registrate(String device) throws Exception {
        Main.applicationManager.setApplication(new Registration());
        int registrationcode = new JSONObject(getHTML("http://"+server+"/device/getRegistrationCode")).getInt("token");
        Main.applicationManager.getCurrentApplication().onDataReceive(Integer.toString(registrationcode),-1);
        System.out.println("Registrationcode: "+registrationcode);
        String apikey=getHTML("http://"+server+"/device/waitForRegistration?regCode="+registrationcode+"&deviceUUID="+ Main.LedWalluuid+"&deviceName="+"Felixledwall");
        if(new JSONObject(apikey).getString("success").contentEquals("true")) {
            apikey=new JSONObject(apikey).getString("APIKey");
            System.out.println("APIKey: " + apikey);
            this.setApiKey(apikey);
            DataManager.save_APIKey();
        }else{
            System.out.println("failed");
        }
    }
    public boolean checkAPIKey() throws Exception {
        JSONObject obj = new JSONObject(getHTML("http://"+server+"/auth/validateSession?session="+Main.serverConnection.getApiKey()));
        System.out.println(getHTML("http://"+server+"/auth/validateSession?session="+this.getApiKey()));
        if(obj.getBoolean("success")){
            return true;
        }else{
            return false;
        }
    }

    public String getApiKey() {
        return this.apiKey;
    }
}
