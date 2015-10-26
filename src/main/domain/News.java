package main.domain;

/**
 * Created by 1 on 10/8/2015.
 */
public class News {

    private int id;
    private String content;
    private String title;

    public News(String title, String content) {
        this.content = content;
        this.title = title;
    }

    public News() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String conent) {
        this.content = conent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
