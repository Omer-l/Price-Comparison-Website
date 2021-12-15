package main;

import dao.AppConfig;
import dao.Phone;
import scraper.WebScraper;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        List<WebScraper> scraperManagerList = appConfig.scraperManager().getScraperList();

        //runs all the scrapers as threads
//        scraperManagerList.get(0).run(); //run Ebay scraper
//        scraperManagerList.get(1).run(); //run techinbasket scraper
//        scraperManagerList.get(2).run(); //run Wex scraper
//        scraperManagerList.get(3).run(); //run Amazon scraper
        scraperManagerList.get(4).run(); //run JD Williams scraper

        //Closes the sessionfactory
        appConfig.sessionFactory().close();
    }
}