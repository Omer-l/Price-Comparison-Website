package main;

import dao.AppConfig;
import scraper.TechInTheBasketScraper;
import scraper.WebScraper;
import java.util.List;

/**
 * Runs the application by executing all threads and then closing the session factory.
 * @author Omer Kacar
 * @see dao.AppConfig
 * @see dao.PhoneDao
 * @see scraper.AmazonScraper
 * @see scraper.EbayScraper
 * @see scraper.JdWilliamsScraper
 * @see TechInTheBasketScraper
 * @see scraper.WexScraper
 * @see WebScraper
 */
public class Main {

    /**
     * Creates an instance of AppConfig.java and runs the threads for each scraper class.
     * @param args  are the arguments when running this class
     */
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        List<WebScraper> scraperManagerList = appConfig.scraperManager().getScraperList();

//        runs all the scrapers as threads
        scraperManagerList.get(0).run(); //run Ebay scraper
        scraperManagerList.get(1).run(); //run techinbasket scraper
        scraperManagerList.get(2).run(); //run Wex scraper
        scraperManagerList.get(3).run(); //run Amazon scraper
        scraperManagerList.get(4).run(); //run JD Williams scraper

        //Closes the session factory to avoid connection leakage
        appConfig.sessionFactory().close();
    }
}