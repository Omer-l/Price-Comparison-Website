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
        Thread scraperThread1 = new Thread(scraperManagerList.get(0));
        Thread scraperThread2 = new Thread(scraperManagerList.get(1));
        Thread scraperThread3 = new Thread(scraperManagerList.get(2));
        Thread scraperThread4 = new Thread(scraperManagerList.get(3));
        Thread scraperThread5 = new Thread(scraperManagerList.get(4));
        scraperThread1.start(); //runs Ebay scraper
        scraperThread2.start(); //runs Techinthebasket scraper
        scraperThread3.start(); //runs Wex scraper
        scraperThread4.start(); //runs Amazon scraper
        scraperThread5.start(); //runs JD Williams scraper

    }
}