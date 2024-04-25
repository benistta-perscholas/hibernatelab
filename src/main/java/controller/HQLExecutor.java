package controller;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HQLExecutor {
//g. this is our main method.
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        try (Session session = factory.openSession()) {
            // Using HQL queries
            listUsers(session);
//            deleteUserByName(session, "Updated Name");
        } finally {
            factory.close();
        }
    }

    public static void listUsers(Session session) {
//        a. here we are just using a query. we could switch this to a typequery if we want.
//        but here we are just creating our query and iterating over our users.
        Query<User> query = session.createQuery("FROM User", User.class);
        for (User user : query.list()) {
        System.out.println("User: " + user.getFullName());
    }
}

//    b. in our second example we are gonna create a method for deleting a user by name
    public static void deleteUserByName(Session session, String fullName) {
//        c. here since we are actually doing something with the database we need to start a transaction
            Transaction transaction = session.beginTransaction();
//            d. and we are creating our query and we are saying we want to delete from our user where the full name is equal
//        to the full name we passed as a parameter in line 21:(, String fullName) )
            Query query = session.createQuery("DELETE FROM User u WHERE u.fullName = :fullName");
//            e. and again we are setting this parameter of ful name here:
            query.setParameter("fullName", fullName);
//          f. and finally we are executing our update and printing out whenever is successful that  the user is deleted.
            int result = query.executeUpdate();
            System.out.println("Users deleted: " + result);
            transaction.commit();
        }


}
