import main.UI.ConsoleUI;
import main.domain.validator.AuthorValidator;
import main.domain.validator.NewsValidator;
import main.domain.validator.UserValidator;
import main.manager.Manager;
import main.repository.AuthorRepo;
import main.repository.NewsRepo;
import main.repository.UserRepo;

public class Main {

    public static void main(String[] args) {
        NewsRepo    newsRepo    = new NewsRepo();
        AuthorRepo  authorRepo  = new AuthorRepo();
        UserRepo    userRepo    = new UserRepo();

        AuthorValidator authorValidator = new AuthorValidator();
        UserValidator userValidator = new UserValidator();
        NewsValidator newsValidator = new NewsValidator();

        newsRepo.setValidator(newsValidator);
        authorRepo.setValidator(authorValidator);
        userRepo.setValidator(userValidator);

        Manager     manager     = new Manager(newsRepo, authorRepo, userRepo);
        ConsoleUI   console     = new ConsoleUI(manager);

        //commons logging

        console.run();

        manager.shutdown();
    }
}
