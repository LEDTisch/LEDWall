package de.ft.ledwall;

import de.ft.ledwall.api.ServerConnection;
import de.ft.ledwall.data.DataManager;
import de.ft.ledwall.plugins.PluginDownloader;
import de.ft.ledwall.plugins.PluginManager;
import de.ft.ledwall.utils.DownloadFile;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WebSocketClient extends org.java_websocket.client.WebSocketClient {

    public WebSocketClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public WebSocketClient(URI serverURI) {
        super(serverURI);
    }

    public WebSocketClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("opened connection");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
        try {
            analyseMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);

        Main.serverConnection.startpollconnection();


    }

    @Override
    public void onError(Exception ex) {
        System.out.println("Error");
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    public static void analyseMessage(String message) throws IOException {

        JSONObject receivedobject=new JSONObject(message);

        if(!receivedobject.has("message") || !receivedobject.has("content"))return;
        JSONObject content=receivedobject.getJSONObject("content");

        switch (receivedobject.getString("message")){
            case "configChange":
                if(content.has("runningapp")) {

                    if(PluginManager.apps.containsKey(content.getString("runningapp")))
                        Main.applicationManager.setApplication(PluginManager.apps.get(content.getString("runningapp")));
                    System.out.println(content.getString("runningapp"));
                }
                break;
            case "deviceuuid":
                Main.deviceuuid = content.getString("deviceuuid");


                break;
            case "syncApps":
                System.out.println("syncapps: ");
                    JSONArray apps = content.getJSONArray("apps");
                System.out.println(apps.toString());

                ArrayList<String> listdata = new ArrayList<String>();
                if (apps != null) {
                    for (int i=0;i<apps.length();i++){
                        listdata.add(apps.getString(i));
                    }
                }


              File[] files =  DataManager.folder_apps.listFiles();

                ArrayList<String> installedApps = new ArrayList<>();
                for (File file : files) {
                    installedApps.add(file.getName().split("\\.")[0]);
                    if(!listdata.contains(file.getName().split("\\.")[0])) {
                        file.delete();
                        System.out.println("uninstalled app: "+file.getName());
                    }
                }


                listdata.forEach(s -> {

                    String repoString = null;
                    try {
                        repoString = Main.serverConnection.getHTML("http://"+Main.serverConnection.server + "/app/getInstallURL?session=" + Main.serverConnection.apiKey + "&appuuid=" + s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    repoString = new JSONObject(repoString).getJSONObject("success").getString("url");

                    String releases = "";
                    System.out.println(repoString);

                    try {
                        releases = DownloadFile.downloadFile("https://api.github.com/repos" + repoString+"/releases");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println("https://api.github.com/repos" + repoString+"/releases");

                    JSONArray ReleasesJson = new JSONArray(releases);

                    JSONObject newestRelease = null;

                    newestRelease = ReleasesJson.getJSONObject(0);

                    if(!installedApps.contains(s)) {
                       try {
                           PluginDownloader.downloadPlugin("https://github.com"+repoString+"/releases/download/" + newestRelease.getString("tag_name") + "/app.lwa",s);
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }

                    //Check for outdated versions
                    if(newestRelease.getFloat("tag_name") > DataManager.getVersionOf(s)){
                        //Do update
                        System.out.println("Doing Update");
                        try {
                            PluginDownloader.downloadPlugin("https://github.com"+repoString+"/releases/download/" + newestRelease.getString("tag_name") + "/app.lwa",s);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                });


                //Laden der plugins
                PluginManager.loadplugins();
               // Datamanger verisonen aufschreiben
                DataManager.saveAppVersions();
                //Get Current Config
                try {
                    JSONObject jsonCurrentData = new JSONObject(Main.serverConnection.getHTML("http://"+Main.serverConnection.server+"/device/getDeviceConfig?session="+Main.serverConnection.apiKey+"&device="+Main.deviceuuid));
                    jsonCurrentData.put("message","configChange");
                    jsonCurrentData.put("content",new JSONObject(jsonCurrentData.getString("data")));
                    jsonCurrentData.remove("data");
                    System.out.println(jsonCurrentData.toString());
                    analyseMessage(jsonCurrentData.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            default:
                break;
        }

    }

}
