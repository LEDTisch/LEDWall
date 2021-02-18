package de.ft.ledwall.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    public static boolean init(){
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
        try {
            checkinnerFolders();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        if (!new File(programfolder + "\\data").exists()) {
            new File(programfolder + "\\data").mkdir();
            if (!new File(programfolder + "\\data").exists()){
                System.out.println("Failed to create directory: "+ programfolder + "\\data");
                return false;
            }
        }*/

        return true;
    }

}
