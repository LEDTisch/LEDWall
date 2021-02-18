package de.ft.ledwall.nativeapps.licht;

import de.ft.ledwall.Application;
import de.ft.ledwall.SystemInterface;
import org.jetbrains.annotations.NotNull;

public class Licht implements Application {
    @Override
    public void onCreate() {
        SystemInterface.table.clear();
    }

    @Override
    public void onDraw() {
        SystemInterface.table.setColor(255,0,0);
        SystemInterface.table.clear();
        SystemInterface.table.drawPixel(SystemInterface.camera.getXposition(),SystemInterface.camera.getYposition());
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
        return "Licht";
    }

    @Override
    public void onStop() {

    }

    @Override
    public int getVersion() {
        return 0;
    }

    @NotNull
    @Override
    public String getUUID() {
        return "ad86fc0a-7056-11eb-8638-0242ac110002";
    }
}
