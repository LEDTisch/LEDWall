package de.ft.ledwall;

import de.ft.ledwall.api.ServerConnection;
import de.ft.ledwall.data.DataManager;
import de.ft.ledwall.nativeapps.licht.Licht;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

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

}
