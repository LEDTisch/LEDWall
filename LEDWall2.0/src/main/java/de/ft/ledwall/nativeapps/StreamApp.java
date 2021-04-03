package de.ft.ledwall.nativeapps;

import de.ft.ledwall.Application;
import org.jetbrains.annotations.NotNull;

public class StreamApp implements Application {
    final String appuuid;
    public StreamApp(String uuid){
        this.appuuid=uuid;
    }
    @Override
    public void onCreate() {

    }

    @Override
    public void onDraw() {
System.out.println("streaming");
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

    }

    @NotNull
    @Override
    public String getVersion() {
        return null;
    }

    @NotNull
    @Override
    public String getUUID() {
        return null;
    }
}
