package model;
//e. the last thing we need to do is to set up the annotation mapping and change jakarta for javax:
//import jakarta.persistence.*;
import javax.persistence.*;
//This annotation entity is saying i am gonna map over this User: public class User {(line9) to our user database
//and create a table called User. So this will map to that User db and we will have that full connection
@Entity
//-- he took this line away. This is saying that our user table is going to be specified as USER in our database
@Table(name = "USER")
//####1 ADDING NAMEQUERIES TO USER CLASS: ADDED THIS AFTER HQLEXECUTOR, AND MAINRUNNER. LESSON 304 PAGE 57
// A1. We are updating our user class with these name queries.
//NOW IN OUR USER class we have created these 3 namequeries . we are using this outter wrapper of this
// A2. namequeries annotation and in the inside we are specifying each query with our @namequery annotation.
@NamedQueries({
//    A3.    so here we are saying that this going to be specified to our user at the findall
        @NamedQuery(name = "User.findAll", query = "FROM User"),
//        A4. this method will be based on the user and will be findbyid
        @NamedQuery(name = "User.findById", query = "FROM User u WHERE u.id = :id"),
//        A5. and here we have our delete by name.
        @NamedQuery(name = "User.deleteByName", query = "DELETE FROM User u WHERE u.fullName = :fullName")
//        A6. so everytime we lok at our findbyid and find by name, everytime we see this colon :(:) we can understand it's gonna be dynamic.
//        its gonna be based on the argument we passed in. so one will be based on the id we passed in and the other besed
//        on the fullName we passed in :(:id for findbyid and fullName for deletebyname)
})
public class User {
//    The next piece we need to do is to take this annotations paste them over our ID column.
//    So our @column is just specifying what the column is going to be for our id. so we are saying in the database is going to be called User ID
//    but here we are specifying it as id (like in line 19)
    @Column(name = "USER_ID")
//    this @Id annotation specifies to hibernate that this in fact our primary key for this table (line20)
    @Id
//    this @GeneratedValue specifies that we are going to have a unique value for this(line 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    make sure the hibernate file has this:(<property name="dialect">org.hibernate.dialect.MySQL8Dialect</property>) and add the right schema:(<property name="connection.url">jdbc:mysql://localhost:3306/usersDb?createDatabaseIfNotExist=true</property>)
// a.   Inside of our user it looks like we have 7 different fields
    private Integer id;
    private String fullName;
    private String email;
    private String password;
    private int age;
    private double salary;
    private String city;

//  c.  and our constructor with every attribute
    public User(String fullName, String email, String password, int age, double salary, String city) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.salary = salary;
        this.city = city;
    }
//  b.  We have our default constructor
    public User() {
    }
//d. Down here we have our typical setters and getters so our typical POJO
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

