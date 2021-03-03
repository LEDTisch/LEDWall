package de.ft.ledwall.data;

import de.ft.ledwall.Main;
import de.ft.ledwall.api.ServerConnection;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.FileHandler;

public class DataManager {
    public static String programfolder=System.getProperty("user.home") + "\\" + ".LEDWall";

    public static File folder_main;
    public static File folder_apps;
    public static File folder_ApiData;

    public static File file_apiconf;

    public static ArrayList<File>foldersshouldexist=new ArrayList<>();
    public static ArrayList<File>filesshouldexist=new ArrayList<>();


    public static void checkinnerFolders() throws IOException {
        for(File file : foldersshouldexist){
            if(!file.exists()){
                file.mkdir();
            }
        }
        for(File file : filesshouldexist){
            if(!file.exists()){
                file.createNewFile();
            }
        }
    }
    public static String init() throws Exception {
        folder_main = new File(programfolder);

        folder_apps = new File(programfolder+"\\apps");
        folder_ApiData = new File(programfolder+"\\ApiData");
        file_apiconf = new File(programfolder+"\\ApiData\\APIconf.json");

        foldersshouldexist.add(folder_apps);
        foldersshouldexist.add(folder_ApiData);

        filesshouldexist.add(file_apiconf);

        if(!folder_main.exists()){
            System.out.println("Mainfolder existiert nicht beginne erstellung");
            folder_main.mkdir();
        }
        if(!folder_main.exists()){
            return "Main Folder kann nicht erstellt werden!";
        }

            try {
            checkinnerFolders();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return "success";
    }

    public static boolean save_APIKey() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(file_apiconf.getAbsolutePath())));
        if(content==null){
            return false;
        }
        if(content==""){
            return false;
        }
        JSONObject obj = new JSONObject(content);

        obj.put("apikey", Main.serverConnection.getApiKey());

        try {
            FileWriter fw = new FileWriter(file_apiconf.getAbsolutePath());
            fw.write(new JSONObject().put("apikey",Main.serverConnection.getApiKey()).toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return true;
    }
    public static String load_APIkey() throws IOException, ParseException {
        String content = new String(Files.readAllBytes(Paths.get(file_apiconf.getAbsolutePath())));
        if(content==null){
            return "-1";
        }
        if(content==""){
            return "-1";

        }
        JSONObject obj = new JSONObject(content);

        if(!obj.isNull("apikey")){
            return obj.getString("apikey");
        }else{
            return "-1";
        }


    }

}
