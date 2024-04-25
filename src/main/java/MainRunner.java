
import controller.UserController;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainRunner {
    public static void main(String[] args) {
////        FROM Clause:
////You will use the FROM clause if you want to load a complete persistent object into memory. Following is the simple syntax of using the FROM clause:
//        SessionFactory factory = new Configuration().configure().buildSessionFactory();
////        a.When we look here we see we have our typical session factory and session set up
//        Session session = factory.openSession();
////        b.And we have our HQLstatement and we are sving this in String format and they are just specifying from users so again
////        we are just gonna get all of it back from our table.
//        String hql = "FROM User"; // Example of HQL to get all records of user class
////        c. so here we are creating our query, this typedquery so here should be specifying user
//        TypedQuery query = session.createQuery(hql);
////        d.and here we are getting back our results in a type of list and we are specifying that the type will be
////        of type user
//        List<User> results = query.getResultList();
////        e. and here we are simply iterating over the result list and we are getting back each of those entities.
//        for (User u : results) {
//            System.out.println("User Id: " +u.getId() + "|" + " Full name:" + u.getFullName() +"|"+ "Email: " + u.getEmail() +"|"+ "password" + u.getPassword());
//        }


//// f.   WHERE Clause:
//// If you want to narrow the specific objects that are returned from storage, use the WHERE clause. Following is the simple syntax of using the WHERE clause:
//        SessionFactory factory = new Configuration().configure().buildSessionFactory();
//        Session session = factory.openSession();
////  g.      Now we are specifying a clause condition WHERE we want to get back our employee so again we are saying we want
////        to get everything from our user where this entity id  is equal to 2
//        String hql = "FROM User u WHERE u.id = 2" ; // Example of HQL to get all records of user class
//        TypedQuery query = session.createQuery(hql);
//        List<User> results = query.getResultList();
//        for (User u : results) {
//            System.out.println("User Id: " +u.getId() + "|" + " Full name:" + u.getFullName() +"|"+ "Email: " + u.getEmail() +"|"+ "password" + u.getPassword());
//        }


////h.ORDER BY Clause:
////To sort your HQL query results, you will need to use the ORDER BY clause. You can order the results by any property on the objects in the result set (ascending [ASC] or descending [DESC]). Following is the simple syntax of using ORDER BY clause:
//        SessionFactory factory = new Configuration().configure().buildSessionFactory();
//        Session session = factory.openSession();
////        i. sohere we have where id as long as id is greater than 3 we are gonna get our results back.
//        String hql = "FROM User E WHERE E.id > 3 ORDER BY E.salary DESC";
//        TypedQuery query = session.createQuery(hql);
//        List<User> results = query.getResultList();
//        for (User u : results) {
//            System.out.println("User Id: " +u.getId() + "|" + " Full name:" + u.getFullName() +"|"+ "Email: " + u.getEmail() +"|"+ "password" + u.getPassword());
//        }
//    }


//
////        j.Multiple SELECT Expressions
////        SELECT U.salary, U.fullname FROM User AS U;
////        The result list of above query contains Object[] elements, one per result. The length of each result Object[] element is 2. The first array cell contains the salary (U.salary) and the second array cell contains the Full name (U.fullname). The following code demonstrates the above query
//        SessionFactory factory = new Configuration().configure().buildSessionFactory();
//        Session session = factory.openSession();
////        k. here we are specifying we want to get the salary and full name and we are using alias U and here we are specyfying
////        that this going to be an array of objects :(Object[].class)
//        TypedQuery<Object[]> queryy = session.createQuery( "SELECT U.salary, U.fullName FROM User AS U", Object[].class);
////        l.again we are storing our results.
//        List<Object[]> resultss = queryy.getResultList();
////        n. if we want to print individual results:
////        System.out.println(Arrays.toString(resultss.get(0)));
////        System.out.println(Arrays.toString(resultss.get(1)));
////        m. and iterating over them
//        for (Object[] a : resultss) {
//            System.out.println("Salary: " + a[0] + ", Full name: " + a[1]); }
//    }




//o.GROUP BY Clause and Aggregate function
//This clause lets Hibernate pull information from the database and group it based on a value of an attribute; and typically, uses the result to include an aggregate value. Following is the simple syntax of using GROUP BY clause:
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
//        so again we are getting back the sum of our salary and the city from our user and we want
//        to group by our city.
        String hql = "SELECT SUM(U.salary), U.city FROM User U GROUP BY U.city";
        //The query returns Object[] arrays of length 2
        TypedQuery query = session.createQuery(hql);
        List<Object[]> result =query.getResultList();
        for (Object[] o : result) {
            System.out.println("Total salary " +o[0] +" | city: "+ o[1] );
        }
    }


}


