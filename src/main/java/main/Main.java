package main;

import dao.AppConfig;
import dao.Phone;
import dao.PhoneDao;
import dao.Product;
import scraper.EbayScraper;
import scraper.ScraperManager;
import scraper.WebScraper;

import java.util.ArrayList;
import java.util.List;

public class Main {
    long scrapeDelay;
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        List<WebScraper> scraperManagerList = appConfig.scraperManager().getScraperList();

        scraperManagerList.get(0).run();
        appConfig.scraperManager().getScraperList().get(0).getDao().shutDown();
    }
}