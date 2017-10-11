package project.model;

/**
 * This class is a base class for any entity in this system.
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public abstract class AbstractEntity<K> {
    public abstract void setId(K id);
    public abstract K getId();
}
