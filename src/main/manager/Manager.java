package main.manager;

import data.transfer.Paginator;
import main.domain.Author;
import main.domain.News;
import main.domain.User;
import main.domain.dto.NewsDto;
import main.repository.AuthorRepo;
import main.repository.NewsRepo;
import main.repository.UserRepo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by 1 on 10/8/2015.
 */
public class Manager {

    private NewsRepo    newsRepo;
    private AuthorRepo  authorRepo;
    private UserRepo    userRepo;

    private ExecutorService executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Manager(NewsRepo newsRepo, AuthorRepo authorRepo, UserRepo userRepo) {
        this.newsRepo   = newsRepo;
        this.authorRepo = authorRepo;
        this.userRepo   = userRepo;
    }

    public void save(News news) throws FileNotFoundException {
        newsRepo.save(news);

        //lazy, but saves to xml every new news added
        //TODO: think of when to save to xml data
        newsRepo.saveAllToXml();
    }

    public void save(Author author){
        authorRepo.save(author);
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public List<News> getAllNews(){
        return newsRepo.getAll();
    }

    public Paginator<NewsDto> getNewsPages(){

        //submit a new thread to the executor service
        //in other words, prepare a thread to be executed
        Future<List<News>> futureNews = executorService.submit(() -> {
            newsRepo.loadAllFromXML();
            return newsRepo.getAll();
        });

        //get the results

        List<News> news = new ArrayList<>();

        try {

            //get the news
            news = futureNews.get();

        } catch (InterruptedException e){
            LOGGER.log(Level.SEVERE, "Fetch action interrupted");
        } catch (ExecutionException e){
            LOGGER.log(Level.SEVERE, "Execution error");
        }

        //sort ascending, create the dto-s for each news and collect them in a list
        //TODO: refactor to allow order and filtering
        List<NewsDto> packet = news.stream()
                .sorted((n1, n2) -> n1.getTitle().compareTo(n2.getTitle()))
                .map((article) -> {

                    //create a new dto for pagination
                    NewsDto newsDto = new NewsDto();

                    //add the title and content of the article to the dto object
                    newsDto.title   = article.getTitle();
                    newsDto.content = article.getContent();

                    return newsDto;
                })
                .collect(Collectors.toList());

        Paginator<NewsDto> paginator = new Paginator<NewsDto>(packet);
        paginator.setPageSize(5);

        return paginator;

    }

    //shutdown all active threads
    public void shutdown() {
        executorService.shutdownNow();
    }
}
