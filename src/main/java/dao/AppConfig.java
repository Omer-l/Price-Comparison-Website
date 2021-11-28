package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.*;
import scraper.EbayScraper;
import scraper.ScraperManager;
import scraper.WebScraper;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

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
//        scraperList.get(0).
        scraperManager.setScraperList(scraperList);

        return scraperManager;
    }

    @Bean
    public EbayScraper ebayScraper() {
        PhoneDao phoneDao = phoneDao();
        final long scrapeDelay_ms = 3000;
        final String titleClassName = "s-item__title";
        final String priceClassName = "s-item__price";
        final String imgUrlClassName = "s-item__image-img";
        final String urlClassName = "s-item__link";
        final String itemContainerClassName = "s-item";
        return new EbayScraper(phoneDao, scrapeDelay_ms, titleClassName, priceClassName, imgUrlClassName, urlClassName, itemContainerClassName);
    }

    @Bean
    public PhoneDao phoneDao() {
        PhoneDao phoneDao = new PhoneDao();
        phoneDao.setSessionFactory(sessionFactory());
        return phoneDao;
    }

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
