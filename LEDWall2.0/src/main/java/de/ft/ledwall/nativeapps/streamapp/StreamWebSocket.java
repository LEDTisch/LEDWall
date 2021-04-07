package de.ft.ledwall.nativeapps.streamapp;

import de.ft.ledwall.data.DataManager;
import de.ft.ledwall.nativeapps.streamapp.StreamApp;
import de.ft.ledwall.plugins.PluginDownloader;
import de.ft.ledwall.plugins.PluginManager;
import de.ft.ledwall.utils.DownloadFile;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;


public class StreamWebSocket extends org.java_websocket.client.WebSocketClient {

    protected int[] data = null;

    public StreamWebSocket(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public StreamWebSocket(URI serverURI) {
        super(serverURI);
    }

    public StreamWebSocket(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("opened app stream connection");
    }

    @Override
    public void onMessage(String message) {

        try {

            JSONObject jsonObject = new JSONObject(message);
            JSONArray buffer = jsonObject.getJSONArray("buffer");

            List<Object> parsedBuffer =  buffer.toList();
            List<Integer> result = parsedBuffer.stream()
                    .map(Integer.class::cast)
                    .collect(Collectors.toList());

            data = result.stream().mapToInt(i->i).toArray();



         //TODO  analyseMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println(
                "Web socket stream dingensConnection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);


    }

    @Override
    public void onError(Exception ex) {
        System.out.println("Error");
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

}
