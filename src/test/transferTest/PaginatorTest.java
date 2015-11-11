package test.transferTest;

import data.transfer.Paginator;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 11/11/2015.
 */
public class PaginatorTest extends TestCase {

    public void testPaginator() throws Exception {

        Paginator<String> paginator = new Paginator<>(5);

        List<String> list = new ArrayList<>();

        String a = "a";
        for (int i = 0; i < 9; i++){

            list.add(a);
            a += "a";
        }

        paginator.setContent(list);

        while (paginator.canPageForward()){
            list = paginator.getPage();
            paginator.nextPage();
        }

        assertEquals("aaaaaa", list.get(0));

    }
}