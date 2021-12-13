package scraper;

import dao.AppConfig;
import dao.PhoneDao;

public class LaptopsDirectScraper extends WebScraper {

    public LaptopsDirectScraper(PhoneDao dao, long scrapeDelay_ms, String store) {
        super(dao, scrapeDelay_ms, store);
    }

    @Override
    public void run() {

    }
}

