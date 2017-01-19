package guru.springframework.services;

import guru.springframework.domain.DomainObject;

import java.util.*;

/**
 * Created by juancho on 19/01/2017.
 */
public abstract class AbstractMapService {

    protected Map<Integer, DomainObject> domainMap;

    public AbstractMapService() {
        domainMap = new HashMap<>();
        loadDomainObjects();
    }

    public List<DomainObject> listAll() {
        return new ArrayList<>(domainMap.values());
    }

    public DomainObject getById(Integer id) {
        return domainMap.get(id);
    }

    public DomainObject saveOrUpdate(DomainObject domainObject) {
        if(domainObject != null) {
            if(domainObject.getId() == null) {
                domainObject.setId(getNextKey());
            }
            domainMap.put(domainObject.getId(), domainObject);

            return domainObject;
        } else {
            throw new RuntimeException("Domain object cannot be null");
        }
    }

    private Integer getNextKey() {
        return Collections.max(domainMap.keySet()) + 1;
    }

    public void delete(Integer id) {
        domainMap.remove(id);
    }

    protected abstract void loadDomainObjects();
}
