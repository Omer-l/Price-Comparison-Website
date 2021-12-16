package scraper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WebScraperTest {
    //can have null attributes, no scraping will be done.
    private final EbayScraper webScraper = new EbayScraper(null, (long)1.0,"", "", "", "", "", "");

    @Test
    void parseColor() {
        String testString = "Samsung Galaxy S20 4G Cosmic Grey 6.2 128GB 4G Unlocked & SIM Free Smartphone";

        String expected = "Gray";
        String actual = webScraper.parseColor(testString);

        assertEquals(expected, actual);
    }

    @Test
    void parseStorageSize() {
        String testString = "Samsung Galaxy S20 4G Cosmic Grey 6.2 128GB 4G Unlocked & SIM Free Smartphone";

        int expected = 128;
        int actual = webScraper.parseStorageSize(testString);

        assertEquals(expected, actual);
    }

    @Test
    void parseBrand() {
        String testString = "Apple iPhone 12 Pro - 128GB - Pacific Blue (EE)";

        String expected = "Apple";
        String actual = webScraper.parseBrand(testString);

        assertEquals(expected, actual);
    }

    @Test
    void parseModelApple() {
        String testString = "Apple iPhone 13 mini (512GB) - Starlight";

        String expected = "iPhone 13 Mini";
        String actual = webScraper.parseModelApple(testString);

        assertEquals(expected, actual);
    }

    @Test
    void parseModelSamsung() {
        String testString = "Samsung Galaxy S20 4G Cosmic Grey 6.2 128GB 4G Unlocked & SIM Free Smartphone";

        String expected = "Galaxy S20";
        String actual = webScraper.parseModelSamsung(testString);

        assertEquals(expected, actual);
    }
}