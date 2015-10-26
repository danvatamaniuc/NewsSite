package main.data.repository;

import main.data.domain.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 10/20/2015.
 */
public abstract class AbstractRepo<E> implements CRUDRepository<E> {

    protected int lastId;
    private List<E> entities = new ArrayList<E>();
    private Validator<E> validator;

    public AbstractRepo() {
        this.lastId = 0;
    }

    public abstract void setEntityId(E e) ;

    public void save(E e){
        validator.validate(e);

        lastId += 1;
        setEntityId(e);

        entities.add(e);
    }

    public Iterable<E> getAll(){
        return entities;
    }

    public void setValidator(Validator<E> validator) {
        this.validator = validator;
    }
}
