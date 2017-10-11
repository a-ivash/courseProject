package project.service.interfaces;

import project.database.exceptions.WrongEmailPasswordException;
import project.model.users.AbstractUser;

import java.sql.SQLException;

public interface LoginService {
    /**
     *
     * @param email
     * @param rawPassword
     * @return user matched these credentials
     * @throws WrongEmailPasswordException when email OR password not matching any user
     * @throws SQLException
     */
    AbstractUser login(String email, String rawPassword) throws WrongEmailPasswordException, SQLException;
}
