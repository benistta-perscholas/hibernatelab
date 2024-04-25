package controller;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserController {
//w. Now lets test out our functionality. so the first thing we want to do is go to our databse and we are going to
// run our first method. so again when we run this we are gonna start this transaction(line 20) :(SessionFactory factory = new Configuration().configure().buildSessionFactory();)
// thats only gonna be starting once and we are gonan strat our session object:(Session session = factory.openSession();)
//    From there we are gonna call our add user(line 20)
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        try {
            // Perform CRUD operations
//              addUser(session);  // Uncomment to add users
//              findUser(session, 2); // Replace '3' with the actual user ID you want to find
//            updateUser(session, 3); // Replace '3' with the actual user ID you want to update
            deleteUser(session, 4); // Replace '4' with the actual user ID you want to delete
        } finally {
            session.close();
            factory.close();
        }
    }

//    a. This first method of add user we are taking in pur session and we will be bringing in our transaction object.
//    and we are starting our transaction like in line 13 with this begin transaction method(session.beginTransaction();)
    public static void addUser(Session session) {
        Transaction transaction = session.beginTransaction();
    //    b.and here inside our try block we are gonna be creating 5 new users
        try {
            User uOne = new User("Moh Haseeb", "haseeb@gmail.com", "has123", 20, 2000.69, "NYC");
            User uTwo = new User("James Santana", "James@gmail.com", "James123", 25, 2060.69, "Dallas");
            User uThree = new User("AH Shahparan", "Shahparan@gmail.com", "Shahparan123", 30, 3060.69, "Chicago");
            User uFour = new User("Christ", "christ@gmail.com", "147852", 35, 35000.3, "NJ");
            User uFive = new User("Sid", "Sid@gmail.com", "s258", 29, 4000.36, "LA");
    //c. and here we are gonna called persist on each one of the users
            session.persist(uOne);
            session.persist(uTwo);
            session.persist(uThree);
            session.persist(uFour);
            session.persist(uFive);
    //d. after we do we simply goning to commit it. then those entities (line 22-26) will be persisted into our database.
            transaction.commit();
    //        e.After everything is succesfull we will get a nice message in our console saying these users were added successfully
            System.out.println("Users added successfully");
    //        f. and here if the transaction is not equal to null it will simply roll it back and print the stacktrace.
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

//g. This next method is find user. We are going to be finding this user by the id. Again, we are taking in our session object.
// and we are also gonna be taking the userId:(public static void findUser(Session session, int userId) {)
//  Technically this finduser doesnt return an actual object, it is just gonna be returning this user's full name
// If we want to we could return the object but we can see in the name of the method (with the void) that we are not returning anything
    public static void findUser(Session session, int userId) {
//      h.  we are gonna start our transaction like in the previous method.
        Transaction transaction = session.beginTransaction();
        try {
//            i. and in our try block we are gonna be grabbing our user by the user id . so our get method takes two parameters
//            the first one is the type of object :(User.class), here is going to be of type user , secong one is specifically the id of that user
            User user = session.get(User.class, userId);
//            j.here if the user is not equal to null we are gonna print out that we found the user whatever that user name is
//            by calling our getter of getfullname.
            if (user != null) {
                System.out.println("User found: " + user.getFullName());
            } else {
//                k. else if the user is null we are gonna print out we did not found the user
                System.out.println("No user found with ID: " + userId);
            }
//            l. and after everything is complete we are gonna to go ahead an commit this transaction.
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

// m.   our next method is going to be our update user. Here similar to our find user we are gonna be
// bringing our session object and the user id
    public static void updateUser(Session session, int userId) {
//        n.Inside of this method we are gonna start our transaction
        Transaction transaction = session.beginTransaction();
        try {
//            o. we are gonna grab the user by its id:(userId);)
            User user = session.get(User.class, userId);
            if (user != null) {
//                p. and we are gonna check as long as the user is not null we are gonna set full name,
//                set email and update our user
                user.setFullName("Updated Name");
                user.setEmail("updated.email@example.com");
                session.update(user);
//                q.finally we are gonna commit the transaction
                transaction.commit();
                System.out.println("User updated successfully");
            } else {
                System.out.println("No user found to update.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
//    r. our last method of delete user is again going to bring our session and user id and we are
//    going to start our transaction.
    public static void deleteUser(Session session, int userId) {
        Transaction transaction = session.beginTransaction();
        try {
//            s. we are gonna be grabbing the user by the id
            User user = session.get(User.class, userId);
            if (user != null) {
//                t. inside of our conditional as long as the user is not null. we are gonna delete this user and commit the transaction
                session.delete(user);
                transaction.commit();
//               u. if succesfully:
                System.out.println("User deleted successfully.");
            } else {
//               v. otherwise:
                System.out.println("No user found to delete.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

}
