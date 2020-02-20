package org.leroykiddd.weatherbot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Parser  {

    private String city;

    Parser(String city){
        this.city = city;
    }

    public String getTodayTemperature() throws IOException{

        String temperature = "Температура сегодня: ";
        String url = "https://yandex.ru/pogoda/" + city.toLowerCase();
        Document doc = Jsoup.connect(url).get();
        Elements temp = doc.getElementsByAttributeValue("class", "temp fact__temp fact__temp_size_s");
        temperature += temp.select("span").first().text();

        return temperature;
    }
}
