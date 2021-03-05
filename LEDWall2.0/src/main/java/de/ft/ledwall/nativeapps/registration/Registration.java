package de.ft.ledwall.nativeapps.registration;

import de.ft.ledwall.Application;
import de.ft.ledwall.SystemInterface;
import org.jetbrains.annotations.NotNull;

public class Registration implements Application {
    int code=-1;
    @Override
    public void onCreate() {
        SystemInterface.table.clear();
    }

    @Override
    public void onDraw() {
        SystemInterface.table.setColor(70,50,0);
        SystemInterface.table.fillRect(0,0,10,15);
        SystemInterface.table.setColor(255,255,0);
        SystemInterface.table.fillRect(0,12,8,3);
        if(code!=-1){
            System.out.println("code: "+code);
            for(int i=0;i<14;i=i+2){
                int f = (code>>i)&0x03;
                System.out.println("s: "+i+"  :  "+f);
                switch (f){
                    case 0:{
                        SystemInterface.table.setColor(255,255,255);
                        break;
                    }
                    case 1:{
                        SystemInterface.table.setColor(255,0,0);
                        
                        break;
                    }
                    case 2:{
                        SystemInterface.table.setColor(0,255,0);
                        break;
                    }
                    case 3:{
                        SystemInterface.table.setColor(0,0,255);
                        break;
                    }
                }
                SystemInterface.table.drawPixel((int)(i*0.5f),14);
                SystemInterface.table.drawPixel((int)(i*0.5f),13);

            }
        }
    }

    @Override
    public void onRun() {

    }

    @Override
    public void onDataReceive(@NotNull String data, int playerID) {
        if(data!=null) {
            this.code = Integer.parseInt(data);
        }else{
            code=0;
        }
    }

    @NotNull
    @Override
    public String getName() {
        return "Registration";
    }

    @Override
    public void onStop() {

    }

    @Override
    public String getVersion() {
        return "-1";
    }

    @NotNull
    @Override
    public String getUUID() {
        return "-1";
    }
}
