package uz.pdp.sotx;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.springframework.context.ApplicationContext;
import uz.pdp.sotx.bot.TelegramService;

public class TelegramBotAppender extends AppenderBase<ILoggingEvent> {

    @Override
    protected void append(ILoggingEvent loggingEvent) {

        if (loggingEvent.getLevel() == Level.ERROR) {
            String message = loggingEvent.getMessage();

            ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
            TelegramService telegramService = applicationContext.getBean(TelegramService.class);

            telegramService.sendError(message);
        }
    }
}
