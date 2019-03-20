package Model.Utils;

public interface IMyList<T> {
    void add(T x);

    Iterable<T> getElements();

    void remove(T x);
}
