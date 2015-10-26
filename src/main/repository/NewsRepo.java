package main.repository;

import main.data.repository.AbstractRepo;
import main.domain.News;
import main.domain.NewsValidator;

import javax.xml.bind.ValidationException;

/**
 * Created by 1 on 10/8/2015.
 */
public class NewsRepo extends AbstractRepo<News>{

    public NewsRepo() {
        super();
    }

    public void setEntityId(News news){
        news.setId(lastId);
    }


}
