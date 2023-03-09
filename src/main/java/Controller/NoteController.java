package Controller;

import Model.Note;
import Model.Repository;
import Model.Validateable;

import java.io.IOException;
import java.util.List;

public class NoteController implements Validateable {
    public Repository repos;

    public NoteController(Repository repos) {
        this.repos = repos;
    }

    @Override
    public void validate(Note n) throws Exception{
        if (n.getHeadOfNote().isEmpty()){
            n.setHeadOfNote("--");
            System.out.println("Empty head of note entered. The '--' will be saved instead of it.");
        } if (n.getHeadOfNote().length()>100){
            throw new Exception("The head of note is too long.");
        } if (n.getNote().isEmpty()){
            n.setNote("--");
            System.out.println("Empty note entered. The '--' will be saved instead of it.");
        }
    }

    public void saveNote(Note n) throws Exception{
        validate(n);
        repos.CreateNote(n);
    }
    public void replaceNote(Note note, String id) throws Exception{
        validate(note);
        repos.replaceNoteInFile(note,id);
    }
    public Note readNote(String id) throws Exception {
        List<Note> notes = repos.getAllNotes();
        for(Note n:notes){
            if(n.getId().equals(id)) {
                return n;
            }
        }
        throw new Exception("Note not found.");
    }
    public List<Note> readNotes() throws IOException {
        List<Note> notes = repos.getAllNotes();
        return notes;
    }
    public void deleteNote(String id) throws Exception {
        repos.deleteNote(id);
    }
    public void updateNote(String id, String option,String newInformation, Note note) throws Exception {
        if (option.equals("1")){
            note.setHeadOfNote(newInformation);
            replaceNote(note,id);
        } if (option.equals("2")) {
            note.setNote(newInformation);
            replaceNote(note,id);
        }
    }
}
