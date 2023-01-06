package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(User user) {
        entityManager.unwrap(Session.class).save(user);
    }


    @Override
    @Transactional(readOnly = true)
    public User get(Long id) {
        return entityManager.find(User.class, id);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        TypedQuery<User> query = entityManager.unwrap(Session.class).createQuery("from User");
        return query.getResultList();
    }
}
