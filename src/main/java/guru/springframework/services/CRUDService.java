package guru.springframework.services;

import java.util.List;

/**
 * Created by juancho on 19/01/2017.
 */
public interface CRUDService<T> {
    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);
}
