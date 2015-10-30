package test.repositoryTest;

import junit.framework.TestCase;
import main.domain.News;
import main.domain.NewsValidator;
import main.repository.NewsRepo;

import java.util.ArrayList;

/**
 * Created by 1 on 10/9/2015.
 */
public class NewsRepoTest extends TestCase {

    public void testSave() throws Exception {
        NewsRepo repo = new NewsRepo();

        NewsValidator newsValidator = new NewsValidator();

        repo.setValidator(newsValidator);

        News news1 = new News("","");
        News news2 = new News("a","b");

        try {
            repo.save(news1);
            assertEquals(true, false);
        }
        catch (Exception e){
            assertEquals(true, true);
        }
        repo.save(news2);
    }

    public void testGetAll() throws Exception {
        NewsRepo repo = new NewsRepo();

        NewsValidator newsValidator = new NewsValidator();

        repo.setValidator(newsValidator);

        News news1 = new News("","");
        News news2 = new News("a","b");

        try {
            repo.save(news1);
            assertEquals(true, false);
        }
        catch (Exception e){
            assertEquals(true, true);
        }
        repo.save(news2);

        ArrayList<News> allNews = new ArrayList<News>();

        allNews = (ArrayList) repo.getAll();

        assertEquals(news2.getTitle(), allNews.get(0).getTitle());
        assertEquals(news2.getContent(), allNews.get(0).getContent());
    }
}