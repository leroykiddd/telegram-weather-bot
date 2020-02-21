package org.leroykiddd.weatherbot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

    public String getNowTemperature() {

        String temperature;
        String output_text = "";
        Elements temp = doc.getElementsByAttributeValue("class", "temp fact__temp fact__temp_size_s");
        try {
            temperature = temp.select("span").first().text();
            output_text = "Сейчас в этом городе: " + temperature;
        } catch (NullPointerException e) {
            output_text = "Не удалось получить сведения о температуре";
        }

        return output_text;
    }

    public String getNowFactWeather() {
        String fact;
        Elements el = doc.getElementsByAttributeValue("class", "maps-widget-fact__title");
        fact = el.text();

        if (fact.equals("Погода сейчас и прогноз — на картах")){
            fact += "\n" + url;
        } else if (fact.equals("")) {
            fact = "Произошла ошибка: не удалось получить факт о погоде";
        }

        return fact;
    }
}
