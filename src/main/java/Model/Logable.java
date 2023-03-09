package Model;


import java.io.IOException;

public interface Logable {
    public void log(String infoToLog) throws IOException;
}
