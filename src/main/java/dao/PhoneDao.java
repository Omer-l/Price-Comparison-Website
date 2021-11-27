package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class PhoneDao {
    private SessionFactory sessionFactory;
    //To ensure duplicate phones are not added to the database...
//    HashSet<dao.Phone> set = new HashSet<>();


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /** Closes Hibernate down and stops its threads from running*/
    public void shutDown() {
        sessionFactory.close();
    }

    public List<Phone> getAllPhones() {
        List<Phone> phones = new ArrayList<Phone>();

        return phones;
    }

    public void updatePhoneDetails() {

    }

    public void deletePhone() {

    }

    /**
     * gets the id of a phone model in the database that hibernate finds
     * @param phone     a phone to compare phones in the database with.
     * @return          if the phone model exists, return it
     */
    public Phone findPhone(Phone phone) {
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        String queryStr = "from Phone where model='" + phone.getModel() + "'";

        System.out.println(queryStr);

        List<Phone> phoneList = session.createQuery(queryStr).getResultList();

        session.close();

        if(phoneList.isEmpty()) {
            System.out.println("THIS IS A NEW PHONE MODEL!");
            return null;
        } else {
            return phoneList.get(0);
        }
    }

    /**
     * This function adds the new phone to the database.
     * @param phone phone object to add to database
     */
    public void addPhone(Phone phone) {
        //Get a new Session instance from the SessionFactory
        Session session = sessionFactory.getCurrentSession();

        // Start transaction
        session.beginTransaction();
        System.out.println("HERE: " + phone);

        //Add phone to the database - will not be stored until we commit the transaction
        session.saveOrUpdate(phone);
        //commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();

        System.out.println("PHONE ADDED TO DATABASE WITH FOLLOWING DEETS: " + phone);
    }

    /**
     * This function adds the new product to the database.
     * @param product phone object to add to database
     */
    public void addProduct(Product product) {
        //Get a new Session instance from the SessionFactory
        Session session = sessionFactory.getCurrentSession();

        // Start transaction
        session.beginTransaction();
        System.out.println("HERE: " + product);

        //Add phone to the database - will not be stored until we commit the transaction
        session.save(product);
        //commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();

        System.out.println("PRODUCT ADDED TO DATABASE WITH FOLLOWING DEETS: " + product);
    }

    /**
     * This function a list of products matching the given product
     * @param product       product to search database
 *     @return              list of products with matching details.
     */
    public Product findProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        String queryStr = "from Product where url='" + product.getUrl()+ "'";

        System.out.println(queryStr);

        List<Product> productList = session.createQuery(queryStr).getResultList();

        session.close();

        if(productList.isEmpty()) {
            System.out.println("THIS IS A NEW PRODUCT SO IT IS ADDED TO DB!");
            return null;
        } else {
            return productList.get(0);
        }
    }
}
