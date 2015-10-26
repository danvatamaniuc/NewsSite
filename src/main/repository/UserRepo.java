package main.repository;

import main.data.repository.AbstractRepo;
import main.domain.User;
import main.domain.UserValidator;

/**
 * Created by 1 on 10/10/2015.
 */
public class UserRepo extends AbstractRepo<User>{

    public UserRepo() {
        super();
    }

    public void setEntityId(User user){
        user.setId(lastId);
    }
}
