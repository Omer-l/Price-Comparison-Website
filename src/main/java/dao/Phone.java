package dao;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "storage")
    private int storage;

    @Column(name = "display_size")
    private float display_size;

    @Column(name = "url_image")
    private String url_image;

    public Phone() {
    }

    public Phone(String brand, String model, String color, int storage, float display_size, String url_image) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.storage = storage;
        this.display_size = display_size;
        this.url_image = url_image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public float getDisplaySize() {
        return display_size;
    }

    public void setDisplay_size(float display_size) {
        this.display_size = display_size;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "dao.Phone{" +
//                    "Name='" + getName() + '\'' +
//                    "price='£" + getPrice() + '\'' +
//                    "url='" + getUrl() + '\'' +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color=" + color +
                ", storage=" + storage + "GB" +
                ", display_size=" + display_size +
                ", url_image='" + url_image + '\'' +
                '}';
    }
}

