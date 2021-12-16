package scraper;

import dao.Phone;
import dao.PhoneDao;
import dao.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

/**
 * This scraper collects Apple and Samsung products from the Techinthebasket store and stores them to the
 * database.
 * @author Omer Kacar
 * @see WebScraper
 */
public class TechInTheBasketScraper extends WebScraper {


    private ArrayList<ArrayList<String>> linksToProducts; //2D List of different brand's products links -> i.e. list of index 0 is a list of Apple's product links
    private final String titleClassName;
    private final String productInfoContainer;
    private final String priceClassName;
    private final String imgUrlClassName;
    private final String urlClassName;
    private final String itemContainerClassName;


    public TechInTheBasketScraper(PhoneDao phoneDao, long scrapeDelay_ms, String titleClassName, String productInfoContainer, String priceClassName, String imgUrlClassName, String urlClassName, String itemContainerClassName, String storeName) {
        super(phoneDao, scrapeDelay_ms, storeName);
        this.titleClassName = titleClassName;
        this.productInfoContainer = productInfoContainer;
        this.priceClassName = priceClassName;
        this.imgUrlClassName = imgUrlClassName;
        this.urlClassName = urlClassName;
        this.itemContainerClassName = itemContainerClassName;
        linksToProducts = new ArrayList<>();
        this.linksToProducts.add(getAppleProductModelLinks());
        this.linksToProducts.add(getSamsungProductModelLinks());
    }

    private ArrayList<String> getAppleProductModelLinks() {
        ArrayList<String> links = new ArrayList<>();

        links.add("https://www.techinthebasket.com/mobiles/mobile-phones/apple.html?product_list_limit=1008");   // all iphones
        return links;
    }

    private ArrayList<String> getSamsungProductModelLinks() {
        ArrayList<String> links = new ArrayList<>();
        links.add("https://www.techinthebasket.com/mobiles.html?manufacturer=28&product_list_limit=100");
        return links;
    }

    /**
     * Scrapes for a phone, given the model of the phone.
     * @param brandIndex        which brand? i.e. index 0 can be Apple.
     * @param productModelIndex which product model? i.e. index 4 can be Apple's "iphone XR"
     * @return a list of phones. (Same model as given in the parameter)
     */
    @Override
    public List<Phone> scrapeAPhoneModel(int brandIndex, int productModelIndex) {

        ArrayList<Phone> listOfProducts = new ArrayList<>();

        //get page for products
        String link = linksToProducts.get(brandIndex).get(productModelIndex);
        getDriver().get(link);

        //Wait for page to load
        try {
            Thread.sleep(getScrapeDelay_ms());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        final List<WebElement> items = getDriver().findElements(By.className(itemContainerClassName));

        for (WebElement item : items) {
            try {
                //get the phone's details
                String productTitle = item.findElement(By.className(titleClassName)).getText();

                if (validProduct(productTitle)) { //is it even a phone?
                    String productPrice = Product.renderPrice(item.findElement(By.className(priceClassName)).getText());
                    String productImgUrl = item.findElement(By.className(imgUrlClassName)).getAttribute("src");
                    String productUrl = item.findElement(By.className(urlClassName)).getAttribute("href");
                    final String productBrand = parseBrand(productTitle);

                    final String productModel = productBrand == "Samsung" ? parseModelSamsung(productTitle) : parseModelApple(productTitle);

                    if (productModel == "")
                        continue; // don't save the product. It is unknown.
                    final String productColor = parseColor(productTitle);
                    final int productStorageSize = parseStorageSize(productTitle);
                    final float productDisplaySize = productBrand == "Samsung" ? parseDisplaySizeSamsung(productModel) : parseDisplaySizeApple(productTitle);

                    Phone newPhone = new Phone(productBrand, productModel, productColor, productStorageSize, productDisplaySize, productImgUrl);
                    Product newProduct = new Product(productTitle, productPrice, productUrl, getStoreName());

                    Phone phoneMapped = getDao().findPhone(newPhone);
                    Product productMapped = getDao().findProduct(newProduct);

                    if (phoneMapped != null)
                        newProduct.setPhone(phoneMapped);
                    else {
                        getDao().addPhone(newPhone);
                        newProduct.setPhone(newPhone);
                    }
                    if (productMapped == null) //ensures duplicate phone is not added.
                        getDao().addProduct(newProduct);

                    System.out.println("NEW PHONE:" + newPhone);
                    listOfProducts.add(newPhone);
                }
            } catch (Exception nsee) {
                System.out.println("This item has at least one missing attribute.");
            }
        }

        return listOfProducts;
    }


    /**
     * Scrapes the web for all the phone models across all brands.
     * @return a list of phones belonging to all brands
     */
    @Override
    public List<List<Phone>> scrapeAllPhonesAllBrands() {

        List<List<Phone>> listOfAllProductsAllBrands = new ArrayList<>();

        for (int brandIterator = 0; brandIterator < this.linksToProducts.size(); brandIterator++) { //go through each brand (i.e., Apple))
            List<Phone> listOfProducts = scrapeAPhoneModel(brandIterator, 0);
            listOfAllProductsAllBrands.add(listOfProducts);
        }

        return listOfAllProductsAllBrands;
    }

    /**
     * Runs the web scraper
     */
    @Override
    public void run() {
        scrapeAllPhonesAllBrands();
        getDriver().close();
    }
}

