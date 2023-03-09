package Model;

import java.util.List;

public interface FileChange {
    List<String> readAll();
    void saveAll(List<String> l);
}
