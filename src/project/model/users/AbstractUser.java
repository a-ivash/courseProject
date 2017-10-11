package project.model.users;

import project.model.AbstractEntity;

/**
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public abstract class AbstractUser extends AbstractEntity<Long> {
    public abstract boolean isAdministrator();
}
