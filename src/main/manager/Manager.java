package main.manager;

import main.domain.Author;
import main.domain.News;
import main.domain.User;
import main.repository.AuthorRepo;
import main.repository.NewsRepo;
import main.repository.UserRepo;

/**
 * Created by 1 on 10/8/2015.
 */
public class Manager {

    private NewsRepo    newsRepo;
    private AuthorRepo  authorRepo;
    private UserRepo    userRepo;

    public Manager(NewsRepo newsRepo, AuthorRepo authorRepo, UserRepo userRepo) {
        this.newsRepo   = newsRepo;
        this.authorRepo = authorRepo;
        this.userRepo   = userRepo;
    }

    public void save(News news) {
        newsRepo.save(news);
    }

    public void save(Author author){
        authorRepo.save(author);
    }

    public void save(User user){
        userRepo.save(user);
    }

    public Iterable<News> getAllNews(){
        return newsRepo.getAll();
    }
}
