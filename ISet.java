package BST_AnyType;

import java.io.Serializable;


public interface ISet<T extends Comparable<T>>
{
    int size();

    void clear();

    boolean isEmpty();

    T find(T elem);

    boolean insert(T value);

    boolean remove(T value);

    int countNodes();

    boolean equals(Object other);

    void print();

    String toString();
}
