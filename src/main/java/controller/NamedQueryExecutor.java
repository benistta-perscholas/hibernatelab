//##########2 . Cretae a namequery executor under the controller package
package controller;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class NamedQueryExecutor {


    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        try (Session session = factory.openSession()) {
//             Using named queries
               listUsers(session);
//            deleteUserByName(session, "Sid");
//          User user = findById(session, 1); // Assume 1 is a valid ID for demonstration
//          if (user != null) {
//              System.out.println("Found User: " + user.getFullName());
//          } else {
//              System.out.println("No user found with the specified ID.");
//          }
        } finally {
            factory.close();
        }
    }


////   a. In here we are gonna be using this findall. doesnt necessary mean you have to have the same name of what that query is
////    you just have to implement it somewhere in your query.
//    public static void listUsers(Session session) {
////        b. in here we are creating our typequery. we are creating thos anmedquery. we are specifying that it is our user.findall query
////        what we are passing in to our typequery is that user.class
//        TypedQuery<User> query = session.createNamedQuery("User.findAll", User.class);
////        c. we are iterating over
//        for (User user : query.getResultList()) {
////            d and list out all of our users.
//            System.out.println("User: " + user.getFullName());
//        }
//    }


    public static void deleteUserByName(Session session, String fullName) {
        Transaction transaction = session.beginTransaction();
        try {
            Query<User> query = session.createNamedQuery("User.deleteByName", User.class);
            query.setParameter("fullName", fullName);
            int result = query.executeUpdate();
            System.out.println("Users deleted: " + result);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }




    public static User findById(Session session, Integer id) {
        try {
            TypedQuery<User> query = session.createNamedQuery("User.findById", User.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No user found with ID: " + id);
            return null;
        }
    }

}
