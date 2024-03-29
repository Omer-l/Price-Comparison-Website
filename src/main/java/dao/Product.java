package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The class wraps the object in a form that is ready to send the data to the database. For every attribute, a column is
 * associated with the database.
 * This class has a foreign key relationship with Phone {@link Phone}
 *
 * @author Omer Kacar
 * @see Phone
 */
@Component
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "url")
    private String url;

    //products table has phone_id
    @ManyToOne
    @JoinColumn(name = "phone_id", nullable = false)
    private Phone phone;

    @Column(name = "store")
    private String store;

    @Autowired
    public Product(String name, String price, String url, String store) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.url = url;
        this.store = store;
    }

    @Autowired
    public Product(String name, BigDecimal price, String url, String store) {
        this.name = name;
        this.price = price;
        this.url = url;
        this.store = store;
    }

    public Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static String renderPrice(String price) { //example use: £34.95 to £35.95 -> £34.95
        String tmp = "";

        for (char c : price.toCharArray())
            if (c == ' ')
                break;
            else if (Character.isDigit(c) || c == '.')
                tmp += c;

        return tmp;
    }
    public void setPrice(String price) {
        this.price = new BigDecimal(price);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "dao.Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }
}