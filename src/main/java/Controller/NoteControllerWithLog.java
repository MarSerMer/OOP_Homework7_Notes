package Controller;

import Model.Logable;
import Model.Note;
import Model.Repository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class NoteControllerWithLog extends NoteController{
    Logable whatLoggerToUse;

    public NoteControllerWithLog(Repository repos, Logable whatLoggerToUse) {
        super(repos);
        this.whatLoggerToUse = whatLoggerToUse;
    }
    @Override
    public void validate(Note n) throws Exception{
        whatLoggerToUse.log("Validation started.");
        String validationInfo = "No problems found";
        if (n.getHeadOfNote().isEmpty()){
            n.setHeadOfNote("--");
            System.out.println("Empty head of note entered. The '--' will be saved instead of it.");
            validationInfo = "Empty head of note entered.";
        } if (n.getHeadOfNote().length()>100){
            validationInfo = "The head of note is too long.";
            whatLoggerToUse.log("The head of note is too long.");
            throw new Exception("The head of note is too long.");
        } if (n.getNote().isEmpty()){
            n.setNote("--");
            System.out.println("Empty note entered. The '--' will be saved instead of it.");
            validationInfo = "Empty note entered.";
        }
        whatLoggerToUse.log("Validation complete. " + validationInfo);
    }

    public void saveNote(Note n) throws Exception{
        whatLoggerToUse.log("The saveNote started.");
        validate(n);
        super.repos.CreateNote(n);
        whatLoggerToUse.log("The note saved.");
    }

    public void replaceNote(Note note, String id) throws Exception{
        whatLoggerToUse.log("replaceNote method started.");
        validate(note);
        repos.replaceNoteInFile(note,id);
        whatLoggerToUse.log("The note with id " + id+ " replaced");
    }

    public Note readNote(String id) throws Exception {
        whatLoggerToUse.log("The readNote started.");
        String validationInfo = "Note id " +id+" not found.";
        List<Note> notes = repos.getAllNotes();
        for(Note n:notes){
            if(n.getId().equals(id)) {
                validationInfo = "Reading note "+id+" successfull.";
                return n;
            }
        }
        whatLoggerToUse.log(validationInfo);
        throw new Exception("Note not found.");
    }
    public List<Note> readNotes() throws IOException {
        whatLoggerToUse.log("readNotes method started.");
        List<Note> notes = repos.getAllNotes();
        whatLoggerToUse.log("readNotes method finished.");
        return notes;
    }
    public void deleteNote(String id) throws Exception {
        whatLoggerToUse.log("deleteNotes method started.");
        repos.deleteNote(id);
        whatLoggerToUse.log("deleteNotes method finished.");
    }
    public void updateNote(String id, String option,String newInformation, Note note) throws Exception {
        whatLoggerToUse.log("updateNote method started.");
        if (option.equals("1")){
            note.setHeadOfNote(newInformation);
            replaceNote(note,id);
        } if (option.equals("2")) {
            note.setNote(newInformation);
            replaceNote(note,id);
        }
    }
}


