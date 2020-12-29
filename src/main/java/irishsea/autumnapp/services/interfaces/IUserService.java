package irishsea.autumnapp.services.interfaces;

import irishsea.autumnapp.domain.PlainObjects.UserPojo;
import irishsea.autumnapp.domain.User;

import java.util.List;

public interface IUserService {
    UserPojo createUser(User user);
    UserPojo getUser(long id);
    List<UserPojo> getAllUsers();
    UserPojo updateUser(User user, long id);
    String deleteUser(long id);
    UserPojo findUserByEmailAndPassword(String email, String password);

}
