package scraper;

import dao.AppConfig;
import dao.Phone;
import dao.PhoneDao;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

/**
 * javadoc here
 */
public abstract class WebScraper implements Runnable {
    private final PhoneDao dao;
    private WebDriver driver;
    private long scrapeDelay_ms;
    private String storeName;

    public WebScraper(PhoneDao dao, long scrapeDelay_ms, String storeName) {
        this.dao = dao;
        initialiseDriver();
        this.storeName = storeName;
    }

    /**
     * scrapes for a phone, given the model of the phone.
     * @param brandIndex                    which brand? i.e. index 0 can be Apple.
     * @param productModelIndex             which product model? i.e. index 4 can be Apple's "iphone XR"
     * @return                              a list of phones. (Same model as given in the parameter)
     */
    public List<Phone> scrapeAPhoneModel(int brandIndex, int productModelIndex) {
        return null;
    }


    /**
     * Scrapes the web for all the phone models across all brands.
     * @return a list of phones belonging to all brands
     */
    public List<List<Phone>> scrapeAllPhonesAllBrands() {
        return null;
    }

    public void initialiseStore() {
    }

    public void initialisePhoneLinksAndModels() {
    }

    /**
     * Sets up the driver.
     */
    public void initialiseDriver() {
        //We need an options class to run headless - not needed if we want default options
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\omerk\\Downloads\\chromedriver.exe");
        options.addArguments("--log-level=3");

        //Create instance of web driver - this must be on the path.
        this.driver = new ChromeDriver(options);

//        driver.quit();
    }

    /**
     * If a product has more than one color, multiple products will be added to the database, so should return an array of colors.
     * @param title     String where the color is stated
     * @return          Color in given title
     */
    public String getColor(String title) {
        String tmp = title.toLowerCase(); //to make color easier to find

        if(tmp.contains("black"))
            return "Black";
        else if (tmp.contains("white"))
            return "White";
        else if (tmp.contains("pacific blue"))
            return "Pacific Blue";
        else if (tmp.contains("graphite"))
            return "Graphite";
        else if (tmp.contains("blue"))
            return "Blue;";
        else if (tmp.contains("gold"))
            return "Gold";
        else if (tmp.contains("pink"))
            return "Pink";
        else if (tmp.contains("silver"))
            return "Silver";
        else if (tmp.contains("space grey"))
            return "Space Grey;";
        else if (tmp.contains("red"))
            return "Red";
        else if (tmp.contains("midnight green"))
            return "Midnight Green";
        else if (tmp.contains("midnight"))
            return "Midnight";
        else if (tmp.contains("purple"))
            return "Purple";
        else if (tmp.contains("yellow"))
            return "Yellow";
        else if (tmp.contains("green"))
            return "Green";
        else if (tmp.contains("coral"))
            return "Coral";

        return "Unknown";
    }

    public int getStorageSize(String title) {
        String tmp = title.toLowerCase(); //easier to search

        if(tmp.contains("32"))
            return 32;
        else if(tmp.contains("64"))
            return 64;
        else if(tmp.contains("128"))
            return 128;
        else if(tmp.contains("256"))
            return 256;
        else if(tmp.contains("512"))
            return 512;
        else
            return 0;
    }

    public String getBrand(String s) {
        String tmp = s.toLowerCase();

        if(tmp.contains("iphone"))
            return "Apple";
        else
            return "Samsung";
    }

    public String getModel(String s) {
        String tmp = s.toLowerCase();

        if(tmp.contains("iphone se"))
            return "iPhone SE";
        else if(tmp.contains("iphone 8"))
            return "iPhone 8";
        else if(tmp.contains("iphone xr"))
            return "iPhone XR";
        else if(tmp.contains("iphone x"))
            return "iPhone X";
        else if(tmp.contains("iphone 11 pro max"))
            return "iPhone 11 Pro Max";
        else if(tmp.contains("iphone 11 pro"))
            return "iPhone 11 Pro";
        else if(tmp.contains("iphone 11"))
            return "iPhone 11";
        else if(tmp.contains("iphone 12 pro max"))
            return "iPhone 12 Pro Max";
        else if(tmp.contains("iphone 12 pro"))
            return "iPhone 12 Pro";
        else if(tmp.contains("iphone 12 mini"))
            return "iPhone 12 Mini";
        else if(tmp.contains("iphone 12"))
            return "iPhone 12";
        else
            return "";
    }

    public float getDisplaySize(String s) {
        String tmp = s.toLowerCase();

        if(tmp.contains("iphone se")) {
            return 4.7f;
        } else if(tmp.contains("iphone 8"))
            return 4.7f;
        else if(tmp.contains("iphone xr"))
            return 6.1f;
        else if(tmp.contains("iphone x"))
            return 5.8f;
        else if(tmp.contains("iphone 11 pro max"))
            return 6.5f;
        else if(tmp.contains("iphone 11 pro"))
            return 5.8f;
        else if(tmp.contains("iphone 11"))
            return 6.1f;
        else if(tmp.contains("iphone 12 pro max"))
            return 6.7f;
        else if(tmp.contains("iphone 12 pro"))
            return 6.1f;
        else if(tmp.contains("iphone 12 mini"))
            return 5.4f;
        else if(tmp.contains("iphone 12"))
            return 5.4f;
        else
            return 0f;
    }

    /**
     * @param productDetails    input product title and other details here
     * @return                  returns true if product is a new phone.
     */
    public static boolean validProduct(String productDetails) {
        String s = productDetails.toLowerCase(); //to lower case so that given product title is case-insensitive (easier to analyse)
        if (s.contains("case") || s.contains("cover") || s.contains("glass")
                || s.contains("tempered") || s.contains("screen") || s.contains("damaged")
                || s.contains("tpu") || s.contains("wallet") || s.contains("front")
                || s.contains("back") || s.contains("tough") || s.contains("shockproof")
                || s.contains("clear") || s.contains("no") || s.contains("leather")
                || s.contains("protector") || s.contains("faulty") || s.contains("guard")
                || s.contains("fault") || s.contains("broken") || !s.contains("iphone")
                || s.contains("gadget") || s.contains("tripod") || s.contains("used")
                || s.contains("preowned") || s.contains("good") || s.contains("condition")
                || s.contains("pristine") || s.contains("replacement") || s.contains("refurbished")
                || s.contains("opened") || s.contains("pre") || s.contains("owned")
                || s.contains("repair"))
            //is it an ad? is it a product accessory instead? is it even the right product?
            return false;
        else
            return true;
    }

    public PhoneDao getDao() {
        return dao;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public long getScrapeDelay_ms() {
        return scrapeDelay_ms;
    }

    public void setScrapeDelay_ms(long scrapeDelay_ms) {
        this.scrapeDelay_ms = scrapeDelay_ms;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
