package Model;

public class Note {
    String id = "";
    String headOfNote;
    String note;

    public Note(String headOfNote, String note) {
        this.headOfNote = headOfNote;
        this.note = note;
    }

    public Note(String id, String headOfNote, String note) {
        this(headOfNote, note);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadOfNote() {
        return headOfNote;
    }

    public void setHeadOfNote(String headOfNote) {
        this.headOfNote = headOfNote;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return String.format("ID: %s\n%s\n%s", id, headOfNote, note);
    }


}
