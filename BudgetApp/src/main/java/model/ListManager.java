package model;

import java.util.List;

public interface ListManager<T> {
    List<T> asList();
    void add(T newElement);
    T getAt(int index);
    void removeAt(int index);
}
