package aar;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.logging.Logger;


/**
 * This class performs the insert, read, delete and search operations
 */
public class DatabaseService {

    Logger log = Logger.getLogger(DatabaseService.class.getName());


    public int insert(User user) {
        EntityManager entityManager = EntityManagerListener.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
           
            
        }
        return user.getId();
    }

    public User read(int id) {
        EntityManager entityManager = EntityManagerListener.createEntityManager();
        User product = null;
        try {
            product = entityManager.find(User.class, id);
        } finally {
            entityManager.close();
           
            if (product == null) log.warning("No records records were found with given id value");

        }
        return product;
    }

    public boolean delete(int id) {
	    EntityManager entityManager = EntityManagerListener.createEntityManager();
        boolean result = false;
        try {
            entityManager.getTransaction().begin();
            User entity = null;
           
            entity = entityManager.find(User.class, id);
            if (entity != null) {
                entityManager.remove(entity);
                entityManager.getTransaction().commit();
                result = true;
            } else {
                log.warning("No records records were found with given id value !!");
                result = false;
            }
            
        } catch (Exception e) {
            result = false;
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
          
        }
        return result;
    }

    public List<User> search(String key, String value) {
	    EntityManager entityManager = EntityManagerListener.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            Predicate where = criteriaBuilder.equal(root.get(key), value);
            criteriaQuery.where(where);
            return entityManager.createQuery(criteriaQuery).getResultList();
        } finally {
            entityManager.close();
           
        }
    }

    public List<User> findAll() {
        EntityManager entityManager = EntityManagerListener.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
 
            Root<User> rootEntry = criteriaQuery.from(User.class);
            CriteriaQuery<User> all = criteriaQuery.select(rootEntry);
            TypedQuery<User> allQuery = entityManager.createQuery(all);
            return allQuery.getResultList();
        } finally {
            entityManager.close();
           
        }
    }
}
