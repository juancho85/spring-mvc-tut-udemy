package guru.springframework.services;

import guru.springframework.domain.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by juancho on 21/01/2017.
 */
@Service
@Profile("jpadao")
public class ProductServiceJapDaoImpl implements ProductService {

    public EntityManagerFactory getEmf() {
        return emf;
    }

    //Indicate to inject emf
    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf;

    @Override
    public List<Product> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Product.class, id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //merge = save or update
        Product savedProduct = em.merge(domainObject);
        em.getTransaction().commit();
        return savedProduct;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //merge = save or update
        em.remove(em.find(Product.class, id));
        em.getTransaction().commit();
    }
}
