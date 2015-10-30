package main.domain;

import main.data.domain.Validator;
import main.data.exceptions.ValidationException;

/**
 * Created by 1 on 10/9/2015.
 */
public class NewsValidator implements Validator<News>{

    public void validate(News news) {
        if (news.getTitle() == "") {
            throw new ValidationException("News title is not set!");
        }
        if (news.getContent() == "") {
            throw new ValidationException("News content is not set!");
        }
    }

}
