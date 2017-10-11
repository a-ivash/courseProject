package project.model.users;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/** Administrator class does not have any fields like firstName, lastName etc, as
 * it's data keeps in configuration file not in database.
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public class Administrator extends AbstractUser {
    @Override
    public boolean isAdministrator() {
        return true;
    }

    @Override
    public void setId(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public Long getId() {
        throw new NotImplementedException();
    }
}
