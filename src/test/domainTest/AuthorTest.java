package test.domainTest;

import junit.framework.TestCase;
import main.domain.Author;

/**
 * Created by 1 on 10/9/2015.
 */
public class AuthorTest extends TestCase {

    public static final int AGE = 21;
    public static final String NAME = "name";

    private void createAuthor(){
        Author author = new Author(NAME, AGE);

        assertEquals(author.getAge(), AGE);
        assertEquals(author.getName(), NAME);
    }


    public void testSetAge() throws Exception {
        Author author = new Author(NAME, AGE);
        author.setAge(22);

        assertEquals(author.getAge(), 22);
    }

    public void testSetName() throws Exception {
        Author author = new Author(NAME, AGE);
        author.setName("ame");

        assertEquals("ame", author.getName());
    }

    public void testSetId() throws Exception {
        Author author = new Author(NAME, AGE);
        author.setId(21);

        assertEquals(author.getId(), 21);
    }
}