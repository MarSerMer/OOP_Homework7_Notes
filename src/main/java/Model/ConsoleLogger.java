package Model;

import java.sql.Date;
import java.time.Clock;
import java.time.LocalDateTime;

public class ConsoleLogger implements Logable {

    public ConsoleLogger() {
    }

    @Override
    public void log(String infoToLog) {
        System.out.println(LocalDateTime.now()+" : " +infoToLog);
    }
}
