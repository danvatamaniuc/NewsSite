package main.domain.validator;

import data.domain.Validator;
import data.exceptions.ValidationException;
import main.domain.User;

/**
 * Created by 1 on 10/10/2015.
 */
public class UserValidator implements Validator<User>{
    public void validate(User user){
        if (user.getUsername() == ""){
            throw new ValidationException("Numele utilizatorului nu e definit!");
        }
    }
}
