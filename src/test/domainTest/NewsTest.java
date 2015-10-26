package test.domainTest;

import junit.framework.TestCase;
import main.domain.News;

/**
 * Created by 1 on 10/8/2015.
 */
public class NewsTest extends TestCase {

    private News news;

    public void setUp() throws Exception {
        news = new News("a", "b");
        super.setUp();
    }

    public void tearDown() throws Exception {

    }

    public void testSetId() throws Exception {
        news.setId(1);

        assertEquals(1, news.getId());
    }

    public void testGetConent() throws Exception {
        assertEquals("b", news.getContent());
    }

    public void testSetConent() throws Exception {
        news.setContent("c");

        assertEquals("c", news.getContent());
    }

    public void testGetTitle() throws Exception {
        assertEquals("a", news.getTitle());
    }

    public void testSetTitle() throws Exception {
        news.setTitle("c");

        assertEquals("c", news.getTitle());
    }
}