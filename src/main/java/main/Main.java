package main;

import dao.AppConfig;
import dao.Phone;
import dao.PhoneDao;
import dao.Product;
import scraper.EbayScraper;

public class Main {
    long scrapeDelay;
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        EbayScraper ebayScraper = new EbayScraper(appConfig, 3000, "s-item__title", "s-item__price", "s-item__image-img", "s-item__link", "s-item");
//        PhoneDao phoneDaoExample = appConfig.phoneDao();
        ebayScraper.scrapeAPhoneModel(0, 1);

//        Phone testPhone = new Phone("Apple", "iPhone BIG FAT L","Red", 128, 4.7f, "image url");
//        Phone testPhone = new Phone("iPhone XS"," productModel", "productColor", 64, 4.7f, "productImgURL");
//        Phone phoneMapped = phoneDaoExample.findPhone(testPhone);
//
//        Product product = new Product("testPhone.getName()", "12.20", "testPhone.getUrl()");
//
//        if(phoneMapped != null) {
//            product.setPhone(phoneMapped);
//            phoneDaoExample.addPhone(phoneMapped);
//        } else
//            product.setPhone(testPhone);

//        phoneDaoExample.addProduct(product);
//        phoneDaoExample.shutDown();

    }
}