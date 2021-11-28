package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @Autowired
    public Product(String name, String price, String url) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.url = url;
    }

    @Autowired
    public Product(String name, BigDecimal price, String url) {
        this.name = name;
        this.price = price;
        this.url = url;
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

    public static String getPrice(String price) { //example use: £34.95 to £35.95 -> £34.95
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