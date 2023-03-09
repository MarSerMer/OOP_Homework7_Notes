package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileLogger implements Logable{

    private String fileForLogging;
    @Override
    public void log(String infoToLog) throws IOException {
        try (FileWriter writer = new FileWriter(fileForLogging, true)) {
            writer.write(LocalDateTime.now() + " : " + infoToLog +"\n");
            writer.flush();
        }
         catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public FileLogger(String fileForLogging) {
        this.fileForLogging = fileForLogging;
        try (FileWriter writer = new FileWriter(fileForLogging, true)) {
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
