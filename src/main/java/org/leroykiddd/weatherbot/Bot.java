package org.leroykiddd.weatherbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String msg = "Что-то пошло не так";
            Parser p = new Parser(update.getMessage().getText());
            try {
                msg = p.getTodayTemperature();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SendMessage message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText(msg);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "name";
    }

    public String getBotToken() {
        return "token";
    }
}