package View;

import Controller.NoteController;
import Model.Note;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ViewNotes {
    private NoteController noteContr;

    public ViewNotes(NoteController noteContr) {
        this.noteContr = noteContr;
    }

    public void start(){
        Commands com = Commands.NONE;

        while(true){
            try {
                String command = enterInfo("Введите команду: ");
                com = Commands.valueOf(command.toUpperCase());

                if (com == Commands.EXIT) return;
                switch (com) {
                    case CREATE:
                        caseCreate();
                        break;
                    case READ:
                        caseRead();
                        break;
                    case LIST:
                        caseList();
                        break;
                    case DELETE:
                        caseDelete();
                        break;
                    case UPDATE:
                        caseUpdate();
                        break;
                }
            } catch (Exception ee){
                System.out.printf("Something went wrong with commands: %s \n", ee.getMessage());
            }
        }
    }
    private String enterInfo(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private void caseCreate() throws Exception {
        String headOfNote = enterInfo("Head of note: ");
        String note = enterInfo("Note: ");
        noteContr.saveNote(new Note(headOfNote, note));
    }

    private void caseRead() {
        String id = enterInfo("Идентификатор пользователя: ");
        try {
            Note note = noteContr.readNote(id);
            System.out.println(note);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void caseList() throws IOException {
        List<Note> notesList = noteContr.readNotes();
        for (Note n:notesList){
            System.out.println(n);
        }
        if (notesList.size()==0){
            System.out.println("No notes found.");
        }
    }
    private void caseUpdate() throws Exception {
        System.out.println("So you want to update one of the notes");
        String noteID = enterInfo("Please enter ID: ");
        Note n = noteContr.readNote(noteID);
        String askOption = enterInfo("Please enter 1 to change the head of the note, enter 2 to change the note itself: ");
        if (askOption.equals("1")||askOption.equals("2")){
            String newInformation = enterInfo("Please enter new information: ");
            noteContr.updateNote(noteID,askOption,newInformation,n);
        } else {
            System.out.println("Wrong option");
        }
    }

    private void caseDelete() throws Exception {
        String id = enterInfo("Enter the ID: ");
        noteContr.deleteNote(id);
        System.out.println();
    }
}
