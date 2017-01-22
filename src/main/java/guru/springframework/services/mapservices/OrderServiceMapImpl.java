package guru.springframework.services.mapservices;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.Order;
import guru.springframework.services.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by juancho on 22/01/2017.
 */
@Service
@Profile("map")
public class OrderServiceMapImpl extends AbstractMapService implements OrderService {

    @Override
    public Order saveOrUpdate(Order domainObject) {
        return (Order) super.saveOrUpdate(domainObject);
    }

    @Override
    protected void loadDomainObjects() {
        //TODO
    }

    @Override
    public List<DomainObject> listAll(){
        return super.listAll();
    }

    @Override
    public Order getById(Integer id){
        return (Order) super.getById(id);
    }

    @Override
    public void delete(Integer id){
        super.delete(id);
    }
}
