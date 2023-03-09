package Model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository{
    private Mapper mapper;
    private FileChanger fileChange;

    public RepositoryFile(FileChanger fileChange,Mapper mapper) {
        this.mapper = mapper;
        this.fileChange = fileChange;
    }
    @Override
    public List<Note> getAllNotes(){
        List<String> ls = fileChange.readAll();
        List<Note> notes = new ArrayList<>();
        for(String s:ls){
            notes.add(mapper.map(s));
        }
        return notes;
    }

    @Override
    public String CreateNote(Note note) {
        List<Note> notes = getAllNotes();
        int max = 0;
        for(Note n:notes){
            int id = Integer.parseInt(n.getId());
            if (max<id){
                max = id;
            }
        }
        int newID = max+1;
        String id = String.format("%d",newID);
        note.setId(id);
        notes.add(note);
        saveRepository(notes);
        return id;
    }

    private void saveRepository(List<Note> notes){
        List<String> notesList = new ArrayList<>();
        for (Note n:notes){
            notesList.add(mapper.map(n));
        }
        fileChange.saveAll(notesList);
    }
    @Override
    public void replaceNoteInFile(Note note, String id) {
        List<Note> notes = getAllNotes();
        int i = 0;
        for (Note n:notes){
            if(n.getId().equals(id)){
                i = notes.indexOf(n);
                note.setId(id);
                notes.set(i,note);
                saveRepository(notes);
            }
        }
    }

    @Override
    public void deleteNote(String id) throws Exception {
    List<Note> notes = getAllNotes();
    Note result = null;
    for(Note n:notes){
        if(n.getId().equals(id)){
            result = n;
            break;
        }
    }
    if(result!=null){
        notes.remove(result);
        saveRepository(notes);
    } else {
        throw new Exception("Note not found. Nothing was deleted.");
    }
    }

}
