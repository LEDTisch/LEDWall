package de.ft.ledwall.utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Weather {
    private String apikey="de5cb76164c907901fbe0be72ed0907e";

    public CurrentWeather currentWeather=new CurrentWeather();


    public Weather(){
        currentWeather.refresh();
    }

    private String getContent(URL targetURL) {
        String line = "";
        String lines = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(targetURL.openStream()));
            while ((line = in.readLine()) != null){
                lines = lines + line;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    public class CurrentWeather{
        public float lon=0;
        public float lat=0;
        public int id=0;
        public String main="";
        public String description="";
        public String icon="";
        public String base="";
        public float temp=0;
        public float feels_like=0;
        public float temp_min=0;
        public float temp_max=0;
        public float pressure=0;
        public float humidity=0;
        public float visibility=0;
        public float speed=0;
        public float deg=0;
        public float all=0;
        public long dt=0;
        public float type=0;
        public int id2=0;
        public String country="";
        public long sunrise=0;
        public long sunset=0;
        public float timezone=0;
        public float id3=0;
        public String name="";
        public float cod=0;
        public void refresh(){
            try {
                JSONObject currentweather=new JSONObject(getContent(new URL("http://api.openweathermap.org/data/2.5/weather?q=H%C3%B6sbach&appid="+apikey)));
                lon=currentweather.getJSONObject("coord").getFloat("lon");
                lat=currentweather.getJSONObject("coord").getFloat("lon");
                id=currentweather.getJSONArray("weather").getJSONObject(0).getInt("id");
                main=currentweather.getJSONArray("weather").getJSONObject(0).getString("main");
                description=currentweather.getJSONArray("weather").getJSONObject(0).getString("description");
                icon=currentweather.getJSONArray("weather").getJSONObject(0).getString("icon");
                base=currentweather.getString("base");
                temp=currentweather.getJSONObject("main").getFloat("temp");
                feels_like=currentweather.getJSONObject("main").getFloat("feels_like");
                temp_min=currentweather.getJSONObject("main").getFloat("temp_min");
                temp_max=currentweather.getJSONObject("main").getFloat("temp_max");
                pressure=currentweather.getJSONObject("main").getFloat("pressure");
                humidity=currentweather.getJSONObject("main").getFloat("humidity");
                visibility=currentweather.getFloat("visibility");
                speed=currentweather.getJSONObject("wind").getFloat("speed");
                deg=currentweather.getJSONObject("wind").getFloat("deg");
                all=currentweather.getJSONObject("clouds").getFloat("all");
                dt=currentweather.getLong("dt");
                type=currentweather.getJSONObject("sys").getFloat("type");
                id2=currentweather.getJSONObject("sys").getInt("id");
                country=currentweather.getJSONObject("sys").getString("country");
                sunrise=currentweather.getJSONObject("sys").getLong("sunrise");
                sunset=currentweather.getJSONObject("sys").getLong("sunset");
                timezone=currentweather.getFloat("timezone");
                id3=currentweather.getLong("id");
                name=currentweather.getString("name");
                cod=currentweather.getFloat("cod");


                System.out.println(cod);

            } catch (MalformedURLException e) {
                System.out.println("Wetter konnte nicht aus dem internet heruntergeladen werden!!!!!!!");
            }

        }
    }
}
