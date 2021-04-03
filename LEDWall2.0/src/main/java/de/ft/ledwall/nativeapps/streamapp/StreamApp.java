package de.ft.ledwall.nativeapps.streamapp;

import de.ft.ledwall.Application;
import de.ft.ledwall.Main;
import de.ft.ledwall.SystemInterface;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;

public class StreamApp implements Application {
    final String appuuid;
    StreamWebSocket socket;
    public StreamApp(String uuid){
        this.appuuid=uuid;
    }
    @Override
    public void onCreate() {


        String url = "ws://stream.arnold-tim.de/startLiveApp?appuuid="+this.appuuid+"&session="+Main.serverConnection.apiKey;
        try {
            socket = new StreamWebSocket(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        socket.connect();


    }

    @Override
    public void onDraw() {
        if(this.socket.data!=null) {
            SystemInterface.table.drawStripBuffer(socket.data);
        }
    }

    @Override
    public void onRun() {

    }

    @Override
    public void onDataReceive(@NotNull String data, int playerID) {

    }

    @NotNull
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void onStop() {
        this.socket.close();
    }

    @NotNull
    @Override
    public String getVersion() {
        return null;
    }

    @NotNull
    @Override
    public String getUUID() {
        return "-1";
    }
}
