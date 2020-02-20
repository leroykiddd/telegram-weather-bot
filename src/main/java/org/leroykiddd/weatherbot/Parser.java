package org.leroykiddd.weatherbot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Parser  {

    private String city;
    private String url;
    private  Document doc;

    Parser(String city) throws IOException {
        this.city = city;
        url = "https://yandex.ru/pogoda/" + this.city.toLowerCase();
        doc = Jsoup.connect(url).get();
    }

    public String getNowTemperature() throws IOException{

        String temperature = "Сейчас в этом городе: ";
        Elements temp = doc.getElementsByAttributeValue("class", "temp fact__temp fact__temp_size_s");
        temperature += temp.select("span").first().text();

        return temperature;
    }

    public String getNowFactWeather() throws IOException{
        String fact;

        Elements el = doc.getElementsByAttributeValue("class", "maps-widget-fact__title");
        fact = el.text();

        if (fact.equals("Погода сейчас и прогноз — на картах")){
            fact += "\n" + url;
        }

        return fact;
    }
}
