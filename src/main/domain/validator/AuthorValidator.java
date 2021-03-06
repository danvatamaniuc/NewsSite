package main.domain.validator;

import data.domain.Validator;
import data.exceptions.ValidationException;
import main.domain.Author;
import main.utils.StringUtils;

/**
 * Created by 1 on 10/9/2015.
 */
public class AuthorValidator implements Validator<Author>{

    public void validate(Author author) {
        if (author.getAge() < 0){
            throw new ValidationException("Invalid author age");
        }

        if (StringUtils.checkForNumbers(author.getName())){
            throw new ValidationException("Invalid author name");
        }
    }
}
