package data.repository;

import data.domain.Validator;

/**
 * Created by 1 on 10/20/2015.
 */
public interface CRUDRepository<E> extends Repository<E> {
    void setValidator(Validator<E> validator);

    void save(E e);

    Iterable<E> getAll();
}
