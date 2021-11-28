package scraper;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates how you can use Selenium Chrome Driver to access data from websites
 * that load data dynamically with JavaScript
 */
public class ScraperManager {
    List<WebScraper> scraperList;

    public ScraperManager(ArrayList<WebScraper> scraperList) {
        this.scraperList = scraperList;
    }

    public ScraperManager() {
    }

    public List<WebScraper> getScraperList() {
        return scraperList;
    }

    public void setScraperList(ArrayList<WebScraper> scraperList) {
        this.scraperList = scraperList;
    }
}
