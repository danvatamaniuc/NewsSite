package test.repositoryTest;

import junit.framework.TestCase;
import main.domain.Author;
import main.domain.AuthorValidator;
import main.repository.AuthorRepo;

import java.util.ArrayList;

/**
 * Created by 1 on 10/10/2015.
 */
public class AuthorRepoTest extends TestCase {

    private AuthorRepo  repo;
    private Author      author;

    public void setUp() throws Exception {
        repo   = new AuthorRepo();
        author = new Author("name", 21);

        AuthorValidator authorValidator = new AuthorValidator();

        repo.setValidator(authorValidator);
        super.setUp();
    }

    public void testSaveAndGet() throws Exception {
        repo.save(author);

        ArrayList<Author> allAuthors = new ArrayList<Author>();

        allAuthors = (ArrayList) repo.getAll();

        try {
            Author author1 = new Author("b2", -1);
            repo.save(author1);
            assertEquals(true, false);
        }
        catch (Exception e){
            assertEquals(true, true);
        }

//        repo.saveAllToXml();

        assertNotNull(allAuthors.get(0));
//        assertEquals(allAuthors.get(0).getAge(), author.getAge());
//        assertEquals(allAuthors.get(0).getName(), author.getName());
    }
}