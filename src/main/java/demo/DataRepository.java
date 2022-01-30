package demo;

import java.util.Optional;

public interface DataRepository {
    void add(String key, String value);
    Optional<String> getFromList(String key);
    Optional<String> getFromStream(String key);
}
