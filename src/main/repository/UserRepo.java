package main.repository;

import main.data.repository.AbstractRepo;
import main.domain.User;
import main.domain.UserValidator;

import java.util.HashMap;

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

    protected User getObject(HashMap<String, String> objectProperties){

        //get the username and the id
        String username = objectProperties.get("username");

        String idString = objectProperties.get("id");
        int id = Integer.parseInt(idString);

        User user = new User(username);
        user.setId(id);

        return user;
    }
}
