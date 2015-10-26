package main.repository;

import main.data.repository.AbstractRepo;
import main.domain.Author;
import main.domain.AuthorValidator;

/**
 * Created by 1 on 10/9/2015.
 */
public class AuthorRepo extends AbstractRepo<Author> {

    public AuthorRepo() {
        super();
    }

    public void setEntityId(Author author){
        author.setId(lastId);
    }
}
