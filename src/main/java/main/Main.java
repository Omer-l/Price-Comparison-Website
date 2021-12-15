package main;

import dao.AppConfig;
import dao.Phone;
import scraper.WebScraper;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    long scrapeDelay;

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        List<WebScraper> scraperManagerList = appConfig.scraperManager().getScraperList();

        //runs all the scrapers as threads
//        scraperManagerList.get(0).run(); //run Ebay scraper
//        scraperManagerList.get(1).run(); //run techinbasket scraper
//        scraperManagerList.get(2).run(); //run Wex scraper
        scraperManagerList.get(3).run(); //run Amazon scraper

        //Closes the sessionfactory
        appConfig.sessionFactory().close();
    }
}