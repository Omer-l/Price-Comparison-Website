package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.*;
import scraper.*;

import java.util.ArrayList;

/**
 * Web scrapers, data access objects, and session factories are all provided in this class, which makes use of Spring to
 * put everything together.
 */
@Configuration
public class AppConfig {

    //to interact with database.
    private SessionFactory sessionFactory;

    @Bean
    public ScraperManager scraperManager() {

        ScraperManager scraperManager = new ScraperManager();

        //creates a list of web scrapers to run
        ArrayList<WebScraper> scraperList = new ArrayList();
        scraperList.add(ebayScraper());
        scraperList.add(techInBasketScraper());
        scraperList.add(wexScraper());
        scraperList.add(amazonScraper());
        scraperList.add(jdWilliamsScraper());

        //adds scrapers to scraper manager, which will then be used to run each scraper
        scraperManager.setScraperList(scraperList);

        return scraperManager;
    }

    /**
     * Gets the scraper for Wex store
     * @return  the Wex scraper
     */
    @Bean
    public WexScraper wexScraper() {

        PhoneDao phoneDao = phoneDao();
        phoneDao.setSessionFactory(sessionFactory());
        final long scrapeDelay_ms = 100;
        final String titleClassName = "-title";
        final String productInfoContainer = "-details";
        final String priceClassName = "--price";
        final String imgUrlClassName = "-image";
        final String urlClassName = "a[class=\"ng-scope\"]";
        final String itemContainerClassName = "WEX-productCard__container";
        final String storeName = "Wex";
        return new WexScraper(phoneDao, scrapeDelay_ms, titleClassName, productInfoContainer, priceClassName, imgUrlClassName, urlClassName, itemContainerClassName, storeName);
    }

    /**
     * Gets the scraper for JD WIlliams store
     * @return  the JD WIlliams scraper
     */
    @Bean
    public JdWilliamsScraper jdWilliamsScraper() {

        PhoneDao phoneDao = phoneDao();
        phoneDao.setSessionFactory(sessionFactory());
        final long scrapeDelay_ms = 100;
        final String titleClassName = "js-product-title-anchor";
        final String productInfoContainer = "js-info-anchor";
        final String priceClassName = "product-price__now";
        final String imgUrlClassName = "js-plp-image";
        final String urlClassName = "js-product-title-anchor";
        final String itemContainerClassName = "product__item";
        final String storeName = "JD Williams";
        return new JdWilliamsScraper(phoneDao, scrapeDelay_ms, titleClassName, productInfoContainer, priceClassName, imgUrlClassName, urlClassName, itemContainerClassName, storeName);
    }

    /**
     * Gets the scraper for Amazon store
     * @return  the Amazon scraper
     */
    @Bean
    public AmazonScraper amazonScraper() {

        PhoneDao phoneDao = phoneDao();
        phoneDao.setSessionFactory(sessionFactory());
        final long scrapeDelay_ms = 100;
        final String titleClassName = "a[class=\"a-link-normal a-text-normal\"]";
        final String productInfoContainer = "-details";
        final String priceClassName = "a-price-whole";
        final String imgUrlClassName = "s-image";
        final String urlClassName = "a[class=\"a-link-normal a-text-normal\"]";
        final String itemContainerClassName = "//div[@class='s-include-content-margin s-latency-cf-section s-border-bottom s-border-top']";
        final String storeName = "Amazon";
        return new AmazonScraper(phoneDao, scrapeDelay_ms, titleClassName, priceClassName, imgUrlClassName, urlClassName, itemContainerClassName, storeName);
    }

    /**
     * Gets the scraper for techinbasket store
     * @return  the techinbasket scraper
     */
    @Bean
    public TechInTheBasketScraper techInBasketScraper() {

        PhoneDao phoneDao = phoneDao();
        phoneDao.setSessionFactory(sessionFactory());
        final long scrapeDelay_ms = 100;
        final String titleClassName = "product-item-link";
        final String productInfoContainer = "productInfo";
        final String priceClassName = "price";
        final String imgUrlClassName = "product-image-photo";
        final String urlClassName = "product-item-link";
        final String itemContainerClassName = "product-item";
        final String storeName = "techinbasket";
        return new TechInTheBasketScraper(phoneDao, scrapeDelay_ms, titleClassName, productInfoContainer, priceClassName, imgUrlClassName, urlClassName, itemContainerClassName, storeName);
    }

    /**
     * Gets the scraper for Ebay store
     * @return  the Ebay scraper
     */
    @Bean
    public EbayScraper ebayScraper() {

        PhoneDao phoneDao = phoneDao();
        phoneDao.setSessionFactory(sessionFactory());
        final long scrapeDelay_ms = 1000;
        final String titleClassName = "s-item__title";
        final String priceClassName = "s-item__price";
        final String imgUrlClassName = "s-item__image-img";
        final String urlClassName = "s-item__link";
        final String itemContainerClassName = "s-item";
        final String storeName = "Ebay";
        return new EbayScraper(phoneDao, scrapeDelay_ms, titleClassName, priceClassName, imgUrlClassName, urlClassName, itemContainerClassName, storeName);
    }

    /**
     * Gets the data access object for the database
     * @return  the data access object that all scrapers will utilise.
     */
    @Bean
    public PhoneDao phoneDao() {
        PhoneDao phoneDao = new PhoneDao();
        phoneDao.setSessionFactory(sessionFactory());
        return phoneDao;
    }

    /**
     * Gets the session factory used to connect with the database
     * @return  the session factory
     */
    @Bean
    public SessionFactory sessionFactory() {
        if(sessionFactory == null) { //ensures sessionFactory is built only once.

            try {
                //Create a builder for the standard service registry
                StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

                //Load configuration from hibernate configuration file
                standardServiceRegistryBuilder.configure("hibernate-annotations.cfg.xml");

                //Create the registry that will be used to build the session factory
                StandardServiceRegistry registry = standardServiceRegistryBuilder.build();

                try {
                    //Create the session factory - this is the goal of the init method.
                    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
                }
                catch (Exception e) {
                    /* The registry would be destroyed by the SessionFactory,
                        but we had trouble building the SessionFactory, so destroy it manually */
                    System.err.println("Session Factory build failed.");
                    e.printStackTrace();
                    StandardServiceRegistryBuilder.destroy( registry );
                }

                //Ouput result
                System.out.println("Session factory built.");

            }
            catch (Throwable ex) {
                // Make sure you log the exception, as it might be swallowed
                System.err.println("SessionFactory creation failed." + ex);
                ex.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
