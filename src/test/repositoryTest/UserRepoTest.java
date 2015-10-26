package test.repositoryTest;

import junit.framework.TestCase;
import main.domain.News;
import main.domain.User;
import main.domain.UserValidator;
import main.exceptions.ValidationException;
import main.repository.UserRepo;

import java.util.ArrayList;

/**
 * Created by 1 on 10/10/2015.
 */
public class UserRepoTest extends TestCase {

    public static final String USERNAME = "username";
    public static final String EMPTY_STRING = "";
    private UserRepo repo;
    private User     user;
    private User     badUser;

    public void setUp() throws Exception {
        repo    = new UserRepo();
        user    = new User(USERNAME);
        badUser = new User(EMPTY_STRING);

        UserValidator userValidator = new UserValidator();

        repo.setValidator(userValidator);

        super.setUp();
    }

    public void testSave() throws Exception {
        repo.save(user);

        try{
            repo.save(badUser);
            assertEquals(true, false);
        }
        catch (ValidationException e){
            assertEquals(true, true);
        }
    }

    public void testGetAll() throws Exception {
        repo.save(user);

        ArrayList<User> allUsers = new ArrayList<User>();

        allUsers = (ArrayList) repo.getAll();
        assertEquals(allUsers.get(0).getUsername(), USERNAME);
    }
}