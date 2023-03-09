import Controller.NoteController;
import Controller.NoteControllerWithLog;
import Model.*;
import View.ViewNotes;

public class Main {
    public static void main(String[] args) {

        FileChanger fc = new FileChanger("notes.txt");
        Repository repository = new RepositoryFile(fc,new NoteMapper());
        //NoteController controller = new NoteController(repository);
        //NoteController controller = new NoteControllerWithLog(repository,new ConsoleLogger());
        FileLogger logFCh = new FileLogger("logger.txt");
        NoteController controller = new NoteControllerWithLog(repository,logFCh);
        ViewNotes vn = new ViewNotes(controller);
        vn.start();
    }
}