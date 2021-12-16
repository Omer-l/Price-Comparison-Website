package scraper;

import dao.AppConfig;
import dao.Phone;
import dao.PhoneDao;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

/**
 * The skeleton for all web scrapers in this package is provided by this class.
 * Contains static methods for obtaining product information.
 * @author Omer Kacar
 */
public abstract class WebScraper extends Thread {
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

    /**
     * Sets up the driver.
     */
    private void initialiseDriver() {
        //We need an options class to run headless - not needed if we want default options
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\omerk\\Downloads\\chromedriver.exe");
        options.addArguments("--log-level=3");

        //Create instance of web driver - this must be on the path.
        this.driver = new ChromeDriver(options);
    }

    /**
     * If a product has more than one color, multiple products will be added to the database, so should return an array of colors.
     * @param title     String where the color is stated
     * @return          Color in given title
     */
    public String parseColor(String title) {
        String tmp = title.toLowerCase(); //to make color easier to find

        if(tmp.contains("black"))
            return "Black";
        else if (tmp.contains("white") || tmp.contains("weiß"))
            return "White";
        else if (tmp.contains("pacific blue"))
            return "Pacific Blue";
        else if (tmp.contains("graphite"))
            return "Graphite";
        else if (tmp.contains("blue"))
            return "Blue";
        else if (tmp.contains("gold"))
            return "Gold";
        else if (tmp.contains("pink"))
            return "Pink";
        else if (tmp.contains("phantom silver"))
            return "Phantom Silver";
        else if (tmp.contains("silver"))
            return "Silver";
        else if (tmp.contains("space grey"))
            return "Space Grey";
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
        else if (tmp.contains("aura"))
            return "Aura";
        else if (tmp.contains("violet"))
            return "Violet";
        else if (tmp.contains("gray") || tmp.contains("grey"))
            return "Gray";
        else if (tmp.contains("starlight"))
            return "Starlight";
        else
            return "Unknown";
    }

    /**
     * Given a string, returns the storage size of the phone
     * @param title     is the string to evaluate
     * @return          the storage size of the phone
     */
    public int parseStorageSize(String title) {
        String tmp = title.toLowerCase(); //easier to search

        if(tmp.contains("16"))
            return 16;
        else if(tmp.contains("32") || tmp.contains("(32)"))
            return 32;
        else if(tmp.contains("64") || tmp.contains("(64)"))
            return 64;
        else if(tmp.contains("128") || tmp.contains("(128)"))
            return 128;
        else if(tmp.contains("256") || tmp.contains("(256)"))
            return 256;
        else if(tmp.contains("512") || tmp.contains("(512)"))
            return 512;
        else if(tmp.contains("1TB") || tmp.contains("1000 TB") || tmp.contains("1000TB") || tmp.contains("(1TB)"))
            return 1000;
        else
            return 0;
    }

    /**
     * Given a string, returns the brand of the phone
     * @param s     is the string to evaluate
     * @return      the brand of the phone
     */
    public String parseBrand(String s) {
        String tmp = s.toLowerCase();

        if(tmp.contains("iphone"))
            return "Apple";
        else
            return "Samsung";
    }

    /**
     * Given a string, returns the model of the phone
     * @param s     is the string to evaluate
     * @return      the model of the phone
     */
    public String parseModelApple(String s) {
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
        else if(tmp.contains("iphone 13 pro max"))
            return "iPhone 13 Pro Max";
        else if(tmp.contains("iphone 13 pro"))
            return "iPhone 13 Pro";
        else if(tmp.contains("iphone 13 mini"))
            return "iPhone 13 Mini";
        else if(tmp.contains("iphone 13"))
            return "iPhone 13";
        else
            return "";
    }

    /**
     * Given a string, returns the model of the phone
     * @param s     is the string to evaluate
     * @return      the model of the phone
     */
    public String parseModelSamsung(String s) {
        String tmp = s.toLowerCase();

        if(tmp.contains("galaxy a13"))
            return "Galaxy A13";
        else if(tmp.contains("galaxy a03"))
            return "Galaxy A03";
        else if(tmp.contains("galaxy a03 core"))
            return "Galaxy A03 Core";
        else if(tmp.contains("galaxy f42"))
            return "Galaxy F42";
        else if(tmp.contains("galaxy m52"))
            return "Galaxy M52";
        else if(tmp.contains("galaxy m22"))
            return "Galaxy M22";
        else if(tmp.contains("galaxy m32"))
            return "Galaxy M32";
        else if(tmp.contains("galaxy a03s"))
            return "Galaxy A03s";
        else if(tmp.contains("galaxy a52"))
            return "Galaxy A52";
        else if(tmp.contains("galaxy z fold3"))
            return "Galaxy Z Fold3";
        else if(tmp.contains("galaxy z flip3"))
            return "Galaxy Z Flip3";
        else if(tmp.contains("galaxy watch4 classic"))
            return "Galaxy Watch4 Classic";
        else if(tmp.contains("galaxy watch4"))
            return "Galaxy Watch4";
        else if(tmp.contains("galaxy a12"))
            return "Galaxy A12";
        else if(tmp.contains("galaxy a12 nacho"))
            return "Galaxy A12 Nacho";
        else if(tmp.contains("galaxy m21"))
            return "Galaxy M21";
        else if(tmp.contains("galaxy f22"))
            return "Galaxy F22";
        else if(tmp.contains("galaxy m32"))
            return "Galaxy M32";
        else if(tmp.contains("galaxy a22"))
            return "Galaxy A22";
        else if(tmp.contains("galaxy a22"))
            return "Galaxy A22";
        else if(tmp.contains("galaxy f52"))
            return "Galaxy F52";
        else if(tmp.contains("galaxy m42"))
            return "Galaxy M42";
        else if(tmp.contains("galaxy m12"))
            return "Galaxy M12";
        else if(tmp.contains("galaxy quantum 2"))
            return "Galaxy Quantum 2";
        else if(tmp.contains("galaxy f12"))
            return "Galaxy F12";
        else if(tmp.contains("galaxy f02s"))
            return "Galaxy F02s";
        else if(tmp.contains("galaxy a72"))
            return "Galaxy A72";
        else if(tmp.contains("galaxy a52"))
            return "Galaxy A52";
        else if(tmp.contains("galaxy a52"))
            return "Galaxy A52";
        else if(tmp.contains("galaxy xcover 5"))
            return "Galaxy Xcover 5";
        else if(tmp.contains("galaxy a32"))
            return "Galaxy A32";
        else if(tmp.contains("galaxy m62"))
            return "Galaxy M62";
        else if(tmp.contains("galaxy f62"))
            return "Galaxy F62";
        else if(tmp.contains("galaxy m12"))
            return "Galaxy M12";
        else if(tmp.contains("galaxy s21 ultra"))
            return "Galaxy S21 Ultra";
        else if(tmp.contains("galaxy s21+"))
            return "Galaxy S21+";
        else if(tmp.contains("galaxy s21"))
            return "Galaxy S21";
        else if(tmp.contains("galaxy s20 ultra"))
            return "Galaxy S20 Ultra";
        else if(tmp.contains("galaxy s20+"))
            return "Galaxy S20+";
        else if(tmp.contains("galaxy s20"))
            return "Galaxy S20";
        else if(tmp.contains("galaxy a32"))
            return "Galaxy A32";
        else if(tmp.contains("galaxy m02s"))
            return "Galaxy M02s";
        else if(tmp.contains("galaxy a12"))
            return "Galaxy A12";
        else if(tmp.contains("galaxy m02"))
            return "Galaxy M02";
        else if(tmp.contains("galaxy a02"))
            return "Galaxy A02";
        else if(tmp.contains("galaxy a02s"))
            return "Galaxy A02s";
        else if(tmp.contains("galaxy m21s"))
            return "Galaxy M21s";
        else if(tmp.contains("galaxy m31 prime"))
            return "Galaxy M31 Prime";
        else if(tmp.contains("galaxy f41"))
            return "Galaxy F41";
        else if(tmp.contains("galaxy s20 fe"))
            return "Galaxy S20 FE";
        else if(tmp.contains("galaxy s20 fe"))
            return "Galaxy S20 FE";
        else if(tmp.contains("galaxy a42"))
            return "Galaxy A42";
        else if(tmp.contains("galaxy m51"))
            return "Galaxy M51";
        else if(tmp.contains("galaxy a51 uw"))
            return "Galaxy A51 UW";
        else if(tmp.contains("galaxy z fold2"))
            return "Galaxy Z Fold2";
        else if(tmp.contains("galaxy note20 ultra"))
            return "Galaxy Note20 Ultra";
        else if(tmp.contains("galaxy note20"))
            return "Galaxy Note20";
        else if(tmp.contains("galaxy note10 plus"))
            return "Galaxy Note10 Plus";
        else if(tmp.contains("galaxy note10"))
            return "Galaxy Note10";
        else if(tmp.contains("galaxy watch3"))
            return "Galaxy Watch3";
        else if(tmp.contains("galaxy z flip"))
            return "Galaxy Z Flip";
        else if(tmp.contains("galaxy m31s"))
            return "Galaxy M31s";
        else if(tmp.contains("galaxy m01s"))
            return "Galaxy M01s";
        else if(tmp.contains("galaxy m01 core"))
            return "Galaxy M01 Core";
        else if(tmp.contains("galaxy a01 core"))
            return "Galaxy A01 Core";
        else if(tmp.contains("galaxy a71 uw"))
            return "Galaxy A71 UW";
        else if(tmp.contains("galaxy m01"))
            return "Galaxy M01";
        else if(tmp.contains("galaxy a21s"))
            return "Galaxy A21s";
        else if(tmp.contains("galaxy j2 core"))
            return "Galaxy J2 Core";
        else if(tmp.contains("galaxy a quantum"))
            return "Galaxy A Quantum";
        else if(tmp.contains("galaxy a71"))
            return "Galaxy A71";
        else if(tmp.contains("galaxy a51"))
            return "Galaxy A51";
        else if(tmp.contains("galaxy a21"))
            return "Galaxy A21";
        else if(tmp.contains("galaxy m11"))
            return "Galaxy M11";
        else if(tmp.contains("galaxy a31"))
            return "Galaxy A31";
        else if(tmp.contains("galaxy a41"))
            return "Galaxy A41";
        else if(tmp.contains("galaxy m21"))
            return "Galaxy M21";
        else if(tmp.contains("galaxy a11"))
            return "Galaxy A11";
        else if(tmp.contains("galaxy m31"))
            return "Galaxy M31";
        else if(tmp.contains("galaxy s20 ultra"))
            return "Galaxy S20 Ultra";
        else
            return "";
    }

    /**
     * Given a string, returns the model of the phone
     * @param model is the string to evaluate
     * @return      the model of the phone
     */
    public float parseDisplaySizeSamsung(String model) {
        String tmp = model.toLowerCase();

        if(tmp.contains("galaxy a13"))
            return 6.5f;
        else if(tmp.contains("galaxy a03"))
            return 6.5f;
        else if(tmp.contains("galaxy a03 core"))
            return 6.5f;
        else if(tmp.contains("galaxy f42"))
            return 6.6f;
        else if(tmp.contains("galaxy m52"))
            return 6.7f;
        else if(tmp.contains("galaxy m22"))
            return 6.4f;
        else if(tmp.contains("galaxy a03s"))
            return 6.5f;
        else if(tmp.contains("galaxy a52"))
            return 7.2f;
        else if(tmp.contains("galaxy z fold3"))
            return 6.1f;
        else if(tmp.contains("galaxy z flip3"))
            return 6.3f;
        else if(tmp.contains("galaxy watch4 classic"))
            return 2f;
        else if(tmp.contains("galaxy watch4"))
            return 2.1f;
        else if(tmp.contains("galaxy a12"))
            return 6.2f;
        else if(tmp.contains("galaxy a12 nacho"))
            return 6.2f;
        else if(tmp.contains("galaxy m21"))
            return 6.3f;
        else if(tmp.contains("galaxy f22"))
            return 6.4f;
        else if(tmp.contains("galaxy m32"))
            return 6.3f;
        else if(tmp.contains("galaxy a22"))
            return 6.3f;
        else if(tmp.contains("galaxy f52"))
            return 6.3f;
        else if(tmp.contains("galaxy m42"))
            return 6.3f;
        else if(tmp.contains("galaxy m12"))
            return 6.6f;
        else if(tmp.contains("galaxy quantum 2"))
            return 6.6f;
        else if(tmp.contains("galaxy f12"))
            return 6.2f;
        else if(tmp.contains("galaxy f02s"))
            return 6.1f;
        else if(tmp.contains("galaxy a72"))
            return 6.6f;
        else if(tmp.contains("galaxy a52"))
            return 6.6f;
        else if(tmp.contains("galaxy a52"))
            return 6.6f;
        else if(tmp.contains("galaxy xcover 5"))
            return 1.7f;
        else if(tmp.contains("galaxy a32"))
            return 6.6f;
        else if(tmp.contains("galaxy m62"))
            return 6.6f;
        else if(tmp.contains("galaxy f62"))
            return 6.6f;
        else if(tmp.contains("galaxy m12"))
            return 5.6f;
        else if(tmp.contains("galaxy s21 ultra"))
            return 6.8f;
        else if(tmp.contains("galaxy s21+"))
            return 6.7f;
        else if(tmp.contains("galaxy s21"))
            return 6.1f;
        else if(tmp.contains("galaxy s20 ultra"))
            return 6.8f;
        else if(tmp.contains("galaxy s20+"))
            return 6.7f;
        else if(tmp.contains("galaxy s20"))
            return 6.1f;
        else if(tmp.contains("galaxy a32"))
            return 6.2f;
        else if(tmp.contains("galaxy m02s"))
            return 6.2f;
        else if(tmp.contains("galaxy a12"))
            return 6.5f;
        else if(tmp.contains("galaxy m02"))
            return 6.3f;
        else if(tmp.contains("galaxy a02"))
            return 6.4f;
        else if(tmp.contains("galaxy a02s"))
            return 6.1f;
        else if(tmp.contains("galaxy m21s"))
            return 6.2f;
        else if(tmp.contains("galaxy m31 prime"))
            return 6.5f;
        else if(tmp.contains("galaxy f41"))
            return 6.3f;
        else if(tmp.contains("galaxy s20 fe"))
            return 6.7f;
        else if(tmp.contains("galaxy s20 fe"))
            return 6.7f;
        else if(tmp.contains("galaxy a42"))
            return 6.2f;
        else if(tmp.contains("galaxy m51"))
            return 6.5f;
        else if(tmp.contains("galaxy a51 uw"))
            return 6.2f;
        else if(tmp.contains("galaxy z fold2"))
            return 6.3f;
        else if(tmp.contains("galaxy note20 ultra"))
            return 6.2f;
        else if(tmp.contains("galaxy note20"))
            return 6.5f;
        else if(tmp.contains("galaxy note10 plus"))
            return 6.25f;
        else if(tmp.contains("galaxy note10"))
            return 6.6f;
        else if(tmp.contains("galaxy watch3"))
            return 6.5f;
        else if(tmp.contains("galaxy z flip"))
            return 6.4f;
        else if(tmp.contains("galaxy m31s"))
            return 6.3f;
        else if(tmp.contains("galaxy m01s"))
            return 6.3f;
        else if(tmp.contains("galaxy m01 core"))
            return 6.3f;
        else if(tmp.contains("galaxy a01 core"))
            return 6.6f;
        else if(tmp.contains("galaxy a71 uw"))
            return 6.3f;
        else if(tmp.contains("galaxy m01"))
            return 6.6f;
        else if(tmp.contains("galaxy a21s"))
            return 6.7f;
        else if(tmp.contains("galaxy j2 core"))
            return 6.8f;
        else if(tmp.contains("galaxy a quantum"))
            return 6.7f;
        else if(tmp.contains("galaxy a71"))
            return 6.7f;
        else if(tmp.contains("galaxy a51"))
            return 6.5f;
        else if(tmp.contains("galaxy a21"))
            return 6.7f;
        else if(tmp.contains("galaxy m11"))
            return 6.6f;
        else if(tmp.contains("galaxy a31"))
            return 6.4f;
        else if(tmp.contains("galaxy a41"))
            return 6.2f;
        else if(tmp.contains("galaxy m21"))
            return 6.3f;
        else if(tmp.contains("galaxy a11"))
            return 6.75f;
        else if(tmp.contains("galaxy m31"))
            return 6.35f;
        else
            return 0f;
    }

    /**
     * Given a string, returns the display size of the phone
     * @param s     is the string to evaluate
     * @return      the display size of the phone
     */
    public float parseDisplaySizeApple(String s) {
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
                || s.contains("fault") || s.contains("broken")
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

    //Runs the thread.
    @Override
    public void run() {

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
