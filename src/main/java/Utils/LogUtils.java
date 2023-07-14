package Utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

public class LogUtils {
    @Attachment(value = "Логи", type = "text/plain")
    public static String formatBrowserLogs(LogEntries logEntries) {
        StringBuilder formattedLogs = new StringBuilder();

        for (LogEntry logEntry : logEntries) {
            formattedLogs.append(logEntry.getLevel())
                    .append(" - ")
                    .append(logEntry.getMessage())
                    .append("\n");
        }

        return formattedLogs.toString();
    }
}

