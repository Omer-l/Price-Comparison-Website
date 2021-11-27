package scraper;

import dao.AppConfig;

public class LaptopsDirectScraper extends WebScraper {

    public LaptopsDirectScraper(AppConfig app, long scrapeDelay_ms) {
        super(app, scrapeDelay_ms);
    }
}
