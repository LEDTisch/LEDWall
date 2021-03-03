package de.ft.ledwall;

import de.ft.ledwall.api.ServerConnection;
import de.ft.ledwall.data.DataManager;
import de.ft.ledwall.nativeapps.licht.Licht;
import de.ft.ledwall.plugins.PluginManager;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static de.ft.ledwall.Main.LedWalluuid;

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
       analyseMessage(message);

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

    public static void analyseMessage(String message) {

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
