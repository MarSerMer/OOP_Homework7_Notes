package Model;

public class NoteMapper implements Mapper{

    @Override // чтобы перевести эту красоту в строку и записать в файл
    public String map(Note n) {
        return String.format("%s;%s;%s",n.getId(),n.getHeadOfNote(),n.getNote());
    }

    @Override
    public Note map(String s) { // чтобы выцепленную из файла строку превратить в красоту
        String[] lines = s.split(";");
        return new Note(lines[0],lines[1],lines[2]);
    }
}
