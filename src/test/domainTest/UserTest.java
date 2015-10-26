package test.domainTest;

import junit.framework.TestCase;
import main.domain.User;

/**
 * Created by 1 on 10/10/2015.
 */
public class UserTest extends TestCase {

    public static final String USERNAME = "username";
    public static final String USERNAME_2 = "username2";
    public static final int ID = 2;
    private User user;

    public void setUp() throws Exception {
        user = new User(USERNAME);
        super.setUp();
    }

    public void testGetUsername() throws Exception {
        assertEquals(USERNAME, user.getUsername());
    }

    public void testSetUsername() throws Exception {
        user.setUsername(USERNAME_2);

        assertEquals(USERNAME_2, user.getUsername());
    }

    public void testSetAndGetId() throws Exception {
        user.setId(ID);

        assertEquals(ID, user.getId());
    }
}