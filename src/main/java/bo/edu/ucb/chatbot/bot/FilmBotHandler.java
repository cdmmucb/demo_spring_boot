package bo.edu.ucb.chatbot.bot;


import bo.edu.ucb.chatbot.bl.BotFilmSearchBl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class FilmBotHandler extends TelegramLongPollingBot {

    private BotFilmSearchBl botFilmSearchBl;

    @Autowired
    public FilmBotHandler(BotFilmSearchBl botFilmSearchBl) {
        this.botFilmSearchBl = botFilmSearchBl;
    }

    @Override
    public String getBotUsername() {
        return  "carlos1999_bot";
    }

    @Override
    public String getBotToken() {
        return "2070287759:AAEI7rOq4b0UJpIF1gmxVfOjg-xsfKf0ahM";
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String title = update.getMessage().getText();
            List<String> responses = botFilmSearchBl.processMessage(title);
            sendMessage(chatId, responses);
        }
    }

    private void sendMessage( String chatId, String messageText) {
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(chatId);
        message.setText(messageText);
        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage( String chatId, List<String> messageText) {
        for ( String message: messageText) {
            sendMessage(chatId, message);
        }
    }
}
