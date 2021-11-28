package scraper;

import dao.Phone;
import dao.PhoneDao;
import dao.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class EbayScraper extends WebScraper {

    private ArrayList<ArrayList<String>> linksToProducts; //2D List of different brand's products links -> i.e. list of index 0 is a list of Apple's product links
    private ArrayList<ArrayList<String>> productModelNames;  //2D List of different model's names for each brand -> i.e. index 0 contains is a list of Apple's product model names
    private final String TITLE_CLASS_NAME;
    private final String PRICE_CLASS_NAME;
    private final String IMG_URL_CLASS_NAME;
    private final String URL_CLASS_NAME;
    private final String ITEM_CONTAINER_CLASS_NAME;


    public EbayScraper(PhoneDao app, long scrapeDelay_ms, String TITLE_CLASS_NAME, String PRICE_CLASS_NAME, String IMG_URL_CLASS_NAME, String URL_CLASS_NAME, String ITEM_CONTAINER_CLASS_NAME) {
        super(app, scrapeDelay_ms);
        this.TITLE_CLASS_NAME = TITLE_CLASS_NAME;
        this.PRICE_CLASS_NAME = PRICE_CLASS_NAME;
        this.IMG_URL_CLASS_NAME = IMG_URL_CLASS_NAME;
        this.URL_CLASS_NAME = URL_CLASS_NAME;
        this.ITEM_CONTAINER_CLASS_NAME = ITEM_CONTAINER_CLASS_NAME;
        linksToProducts = new ArrayList<>();
        productModelNames = new ArrayList<>();
        linksToProducts.add(getAppleProductModelLinks());
        productModelNames.add(getAppleProductModelNames());
        initialiseStore();
    }

    /**
     * Initialises links, model names and driver
     */
    @Override
    public void initialiseStore() {
        initialisePhoneLinksAndModels();
    }

    /**
     * initialises a 2D array, each list in the array contains a brand's phones.
     * @return  a 2D array, each list in the array contains a brand's phones
     */
    private void initialiseLinksAndProductModelsForAllBrands() {
        linksToProducts.add(getAppleProductModelLinks());
        productModelNames.add(getAppleProductModelNames());
    }

    private ArrayList<String> getAppleProductModelLinks() {
        ArrayList<String> links = new ArrayList <>();
//        EBAY LINKS TO iPhones
        links.add("https://www.ebay.co.uk/b/Apple-Mobile-Smartphones/9355/bn_449685?LH_BIN=1&LH_ItemCondition=1000&mag=1&rt=nc&_pgn=");                     // ->All iphones
        links.add("https://www.ebay.co.uk/b/Apple-iPhone-8-Mobile-Phones-Smartphones/9355/bn_86147745?LH_BIN=1&LH_ItemCondition=1000&mag=1&rt=nc&_pgn=");   // ->iphone 8
        links.add("https://www.ebay.co.uk/b/iPhone-X-Phones/9355/bn_86757129?LH_BIN=1&LH_ItemCondition=1000&mag=1&rt=nc&_pgn=");                            // -> iphone x
        links.add("https://www.ebay.co.uk/b/iPhone-XR-Phones/9355/bn_7109728856?LH_BIN=1&LH_ItemCondition=1000&mag=1&rt=nc&_pgn=");                         // -> iphone xs
        links.add("https://www.ebay.co.uk/b/Apple-iPhone-11/9355/bn_7116331164?LH_BIN=1&LH_ItemCondition=1000&mag=1&rt=nc&_pgn=");                          // -> iphone 11
        links.add("https://www.ebay.co.uk/b/Apple-iPhone-11-Pro/9355/bn_7116331166?LH_BIN=1&LH_ItemCondition=1000&mag=1&rt=nc&_pgn=");                      // -> iphone 11 pro
        links.add("https://www.ebay.co.uk/b/Apple-iPhone-11-Pro-Max/9355/bn_7116318165?LH_BIN=1&LH_ItemCondition=1000&mag=1&rt=nc&_pgn=");                  // -> iphone 11 pro max
        links.add("https://www.ebay.co.uk/b/Apple-iPhone-12-mini/9355/bn_7117592268?LH_BIN=1&LH_ItemCondition=1000&mag=1&rt=nc&_pgn=");                     // -> iphone 12 mini
        links.add("https://www.ebay.co.uk/b/iPhone-SE-2nd-Generation/9355/bn_7117172647?LH_BIN=1&LH_ItemCondition=1000&mag=1&rt=nc&_pgn=");                 // -> iphone se
        links.add("https://www.ebay.co.uk/b/Apple-iPhone-12-Pro/9355/bn_7117593357?LH_BIN=1&LH_ItemCondition=1000&mag=1&rt=nc&_pgn=");                      // -> iphone 12 pro
        links.add("https://www.ebay.co.uk/b/Apple-iPhone-12-Pro-Max/9355/bn_7117596158?LH_BIN=1&LH_ItemCondition=1000&mag=1&rt=nc&_pgn=");                  // -> iphone 12 pro max

        return links;
    }

    private ArrayList<String> getAppleProductModelNames() {
        // Indexes 0 to 10      -> iphones.
//      MODEL NAMES OF THE IPHONE LINKS
        ArrayList<String> phoneModelNames = new ArrayList<>();
        phoneModelNames.add("iphone");
        phoneModelNames.add("iphone 8");
        phoneModelNames.add("iphone x");
        phoneModelNames.add("iphone xr");
        phoneModelNames.add("iphone 11");
        phoneModelNames.add("iphone 11 pro");
        phoneModelNames.add("iphone 11 pro max");
        phoneModelNames.add("iphone 12 mini");
        phoneModelNames.add("iphone se");
        phoneModelNames.add("iphone 12");
        phoneModelNames.add("iphone 12");

        return phoneModelNames;
    }


    /**
     * This function adds a brand's list of products that the store offers in a one to one relationship
     * @param linksToPhones         Links to the page containing the list of the products
     * @param productModelNames     The model name of the p
     */
    public void addBrand(ArrayList<String> linksToPhones, ArrayList<String> productModelNames) {
        this.linksToProducts.add(linksToPhones);
        this.productModelNames.add(productModelNames);
    }


    /**
     * scrapes for a phone, given the model of the phone.
     * @param brandIndex                 which brand? i.e. index 0 can be Apple.
     * @param productModelIndex         which product model? i.e. index 4 can be Apple's "iphone XR"
     * @return                          a list of phones. (Same model as given in the parameter)
     */
    @Override
    public List<Phone> scrapeAPhoneModel(int brandIndex, int productModelIndex) {

        ArrayList<Phone> listOfProducts = new ArrayList<>();
        int pageIterator = 1;
        //get pages
        int tmpIt = 1;
        while (pageIterator < 5) {
            //get next page
            getDriver().get(linksToProducts.get(brandIndex).get(productModelIndex) + pageIterator);

            //Wait for page to load
            try {
                Thread.sleep(getScrapeDelay_ms());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            final List<WebElement> items = getDriver().findElements(By.className(ITEM_CONTAINER_CLASS_NAME));
            List<String> itemLinks = new ArrayList<>();

            System.out.println("\n**********\nPAGE NUMBER: " + pageIterator + "\n**********");

            for (WebElement item : items) {
                try {
                    String productTitle = item.findElement(By.className(TITLE_CLASS_NAME)).getText();
                    //get the products details.

                    if(validProduct(productTitle) && (productTitle.toLowerCase().contains(productModelNames.get(brandIndex).get(productModelIndex)))) { //validates that the product is in fact a product

                        final String productPrice = Product.getPrice(item.findElement(By.className(PRICE_CLASS_NAME)).getText());
                        final String productImgURL = item.findElement(By.className(IMG_URL_CLASS_NAME)).getAttribute("src");
                        String productUrl = item.findElement(By.className(URL_CLASS_NAME)).getAttribute("href");
                        productUrl = productUrl.substring(0, productUrl.indexOf('?')); //might need to exclude products with /p/ path link
                        final String productBrand = getBrand(productTitle);
                        final String productModel = getModel(productTitle);
                        final String productColor = getColor(productTitle);
                        final int productStorageSize = getStorageSize(productTitle);
                        final float productDisplaySize = getDisplaySize(productTitle);

                        itemLinks.add(productUrl);
                        Phone newPhone = new Phone(productBrand, productModel, productColor, productStorageSize, productDisplaySize, productImgURL);
                        Product newProduct = new Product(productTitle, productPrice, productUrl);

                        Phone phoneMapped = getDao().findPhone(newPhone);
                        Product productMapped = getDao().findProduct(newProduct);

                        if(phoneMapped != null)
                            newProduct.setPhone(phoneMapped);
                        else {
                            getDao().addPhone(newPhone);
                            newProduct.setPhone(newPhone);
                        }
                        if(productMapped == null) //ensures duplicate phone is not added.
                            getDao().addProduct(newProduct);

                        System.out.println(tmpIt + ": " + newPhone.toString());
                        listOfProducts.add(newPhone);
                        tmpIt++;
                    }
                } catch (NoSuchElementException nsee) {
                    System.out.println("This item has at least one missing attribute.");
                    nsee.printStackTrace();
                }
            }
            tmpIt = 1;
            pageIterator++;
        }

        return listOfProducts;
    }


    /**
     * Depending on the brand, this function will return all phones belonging to that brand.
     * @param brandIndex    the brands index (i.e., 0 for Apple phones)
     * @return              a list of phones belong to that brand
     */
    public ArrayList<ArrayList<Phone>> getAllProductsFromABrand(int brandIndex) {
        ArrayList<ArrayList<Phone>> listOfProducts = new ArrayList<>();
        int i = 1;
        //get pages
        int tmpIt = 1;
        for(int productIndex = 1; productIndex < productModelNames.size(); productIndex++) {
            System.out.println(productModelNames.get(brandIndex).get(productIndex).toUpperCase() + "'s LIST: BELOW") ;
            listOfProducts.add(new ArrayList<>(scrapeAPhoneModel(brandIndex, productIndex)));
        }

        return listOfProducts;
    }


}
