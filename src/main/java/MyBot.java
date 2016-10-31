import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by ${DPudov} on 31.10.2016.
 */
public class MyBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            SendMessage sendMessageRequest = new SendMessage();
            sendMessageRequest.setChatId(update.getMessage().getChatId().toString());
            try {
                String answer = Calculator.generate(messageText);
                sendMessageRequest.setText("I think it is " + answer);
            } catch (InputException e) {
                sendMessageRequest.setText("Sorry, but I don't understand what you mean. Please try to write in format DD.MM.YYYY");
            }
            try {
                sendMessage(sendMessageRequest);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "DayCalculatorBot";
    }

    public String getBotToken() {
        return "244772895:AAH-45t95YVHIKrLypo-u-uAJL059HjKmK8";
    }

    public String getBotPath() {
        return "DayCalculatorBot";
    }
}
