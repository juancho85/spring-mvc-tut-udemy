package guru.springframework.services.mapservices;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by juancho on 22/01/2017.
 */
@Service
@Profile("map")
public class UserServiceMapImpl extends AbstractMapService implements UserService {

    @Override
    public User saveOrUpdate(User domainObject) {
        return (User) super.saveOrUpdate(domainObject);
    }

    @Override
    protected void loadDomainObjects() {

    }

    @Override
    public List<DomainObject> listAll(){
        return super.listAll();
    }

    @Override
    public User getById(Integer id){
        return (User) super.getById(id);
    }

    @Override
    public void delete(Integer id){
        super.delete(id);
    }
}
