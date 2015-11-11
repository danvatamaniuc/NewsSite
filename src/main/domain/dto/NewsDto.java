package main.domain.dto;

/**
 * Created by 1 on 11/11/2015.
 */
public class NewsDto {
    public String title;
    public String content;

    @Override
    public String toString() {
        return "News: " +
                "title = '" + title + '\'' +
                ", content = '" + content + '\'';
    }
}
