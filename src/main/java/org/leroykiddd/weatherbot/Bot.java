package org.leroykiddd.weatherbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

        long chatId = update.getMessage().getChatId();
        String msg_text = update.getMessage().getText(); //Input text
        SendMessage message = new SendMessage().setChatId(chatId); //Output message
        String msg; //Output text
        Parser weather;

        switch (msg_text){
            case "/start":
                message.setText("Привет! Я бот погоды, введи \"/help\", чтобы получить справку.");
                break;
            case "/help":
                message.setText("Введи название города(на английском), чтобы получить сведеня о погоду.\nПример:\n\t" +
                        "-Moscow\n\t-Сейчас в этом городе: 0\n" +
                        "\tВ ближайшие два часа осадков не ожидается");
                break;
            default:
                try {
                    weather = new Parser(update.getMessage().getText());
                    msg = weather.getNowTemperature() + "\n" + weather.getNowFactWeather();
                    message.setText(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                    msg = "Что-то пошло не так!";
                    message.setText(msg);
                }
        }

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "leroy_kid_javalearnbot";
    }

    public String getBotToken() {
        return "934478099:AAEwNs3eYIsbv9FgS5lBjfc5DH-hPFyj9Kc";
    }
}
