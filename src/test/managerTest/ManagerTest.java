package test.managerTest;

import data.transfer.Paginator;
import junit.framework.TestCase;
import main.domain.*;
import main.domain.dto.NewsDto;
import main.domain.validator.AuthorValidator;
import main.domain.validator.NewsValidator;
import main.domain.validator.UserValidator;
import main.manager.Manager;
import main.repository.AuthorRepo;
import main.repository.NewsRepo;
import main.repository.UserRepo;

import java.util.ArrayList;

/**
 * Created by 1 on 10/9/2015.
 */
public class ManagerTest extends TestCase {

    private NewsRepo    newsRepo;
    private AuthorRepo  authorRepo;
    private UserRepo    userRepo;
    private Manager     manager;
    private News        news;
    private Author      author;
    private User        user;

    public void setUp() throws Exception {

        newsRepo   = new NewsRepo();
        authorRepo = new AuthorRepo();
        userRepo   = new UserRepo();
        manager    = new Manager(newsRepo, authorRepo, userRepo);
        news       = new News("a", "b");
        author     = new Author("name", 21);
        user       = new User("username");

        UserValidator userValidator = new UserValidator();
        AuthorValidator authorValidator = new AuthorValidator();
        NewsValidator newsValidator = new NewsValidator();

        newsRepo.setValidator(newsValidator);
        userRepo.setValidator(userValidator);
        authorRepo.setValidator(authorValidator);

        super.setUp();
    }

    public void testSaveNews() throws Exception {
        manager.save(news);
        ArrayList<News> allNews = (ArrayList) newsRepo.getAll();

        assertEquals(news.getTitle(), allNews.get(0).getTitle());
        assertEquals(news.getContent(), allNews.get(0).getContent());
    }

    public void testSaveAuthor() throws Exception {
        manager.save(author);
        ArrayList<Author> allAuthors = (ArrayList) authorRepo.getAll();

        assertEquals(author.getAge(), allAuthors.get(0).getAge());
        assertEquals(author.getName(), allAuthors.get(0).getName());
    }

    public void testSave() throws Exception {
        manager.save(user);
        ArrayList<User> allUsers = (ArrayList) userRepo.getAll();

        assertEquals(allUsers.get(0).getUsername(), user.getUsername());
    }

    public void testGetNewsPages() throws Exception {
        newsRepo.save(news);
        newsRepo.save(news);
        newsRepo.save(news);

        newsRepo.setXMLFilename("testNewsRepo.xml");

        newsRepo.saveAllToXml();

        Paginator<NewsDto> paginator = manager.getNewsPages();

        assertFalse(paginator.canPageBackwards());
        assertTrue(paginator.canPageForward());

        paginator.setPageSize(2);

        assertFalse(paginator.canPageBackwards());
        assertTrue(paginator.canPageForward());
    }
}