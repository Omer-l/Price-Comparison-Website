package scraper;

import dao.AppConfig;
import dao.PhoneDao;

public class LaptopsDirectScraper extends WebScraper {

    public LaptopsDirectScraper(PhoneDao dao, long scrapeDelay_ms) {
        super(dao, scrapeDelay_ms);
    }
}

