package Model;

import java.util.List;

public interface Repository {
    List<Note> getAllNotes();
    String CreateNote(Note n);
    void deleteNote(String id) throws Exception;
    public void replaceNoteInFile(Note n,String id);
}
