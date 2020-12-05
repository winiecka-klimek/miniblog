package javaldz26.demo.miniblog26.dao;

import javaldz26.demo.miniblog26.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional //cale cialo tej metody jest w transakcji,  transakcja kontenerowa
    public void save(User user) {
//        entityManager.getTransaction().begin();
        entityManager.persist(user);
//        entityManager.getTransaction().commit();

//        entityManager.close();
    }
}
