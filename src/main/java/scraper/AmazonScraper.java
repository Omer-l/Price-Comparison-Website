package scraper;

import dao.Phone;
import dao.PhoneDao;
import dao.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AmazonScraper extends WebScraper {

    private ArrayList<ArrayList<String>> linksToProducts; //2D List of different brand's products links -> i.e. list of index 0 is a list of Apple's product links
    private final String titleClassName;
    private final String priceClassName;
    private final String imgUrlClassName;
    private final String urlClassName;
    private final String itemContainerClassName;


    public AmazonScraper(PhoneDao phoneDao, long scrapeDelay_ms, String titleClassName, String priceClassName, String imgUrlClassName, String urlClassName, String itemContainerClassName, String storeName) {
        super(phoneDao, scrapeDelay_ms, storeName);
        this.titleClassName = titleClassName;
        this.priceClassName = priceClassName;
        this.imgUrlClassName = imgUrlClassName;
        this.urlClassName = urlClassName;
        this.itemContainerClassName = itemContainerClassName;
        linksToProducts = new ArrayList<>();
        linksToProducts.add(getAppleProductModelLinks());
        linksToProducts.add(getSamsungProductModelLinks());
    }

    private ArrayList<String> getAppleProductModelLinks() {
        ArrayList<String> links = new ArrayList<>();
        links.add("https://www.amazon.co.uk/s?k=SIM-Free+%26+Unlocked+Mobile+Phones&i=electronics&rh=n%3A356496011%2Cp_n_condition-type%3A12319067031%2Cp_89%3AApple&dc&page=");
        return links;
    }

    private ArrayList<String> getSamsungProductModelLinks() {
        ArrayList<String> links = new ArrayList<>();
        links.add("https://www.amazon.co.uk/s?k=SIM-Free+%26+Unlocked+Mobile+Phones&i=electronics&rh=n%3A356496011%2Cp_89%3ASamsung%2Cp_n_condition-type%3A12319067031&dc&page=");
        return links;
    }


    /**
     * scrapes for a phone, given the model of the phone.
     *
     * @param brandIndex        which brand? i.e. index 0 can be Apple.
     * @param productModelIndex which product model? i.e. index 4 can be Apple's "iphone XR"
     * @return a list of phones. (Same model as given in the parameter)
     */
    @Override
    public List<Phone> scrapeAPhoneModel(int brandIndex, int productModelIndex) {

        ArrayList<Phone> listOfProducts = new ArrayList<>();
        int pageIterator = 1;
        //get pages
        int tmpIt = 1;
        while (pageIterator < 5) {
            //get next page
            getDriver().get(linksToProducts.get(brandIndex).get(productModelIndex) + pageIterator + "&_encoding=UTF8");

            //Wait for page to load
            try {
                Thread.sleep(getScrapeDelay_ms());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            final List<WebElement> items = getDriver().findElements(By.xpath(itemContainerClassName));
            List<String> itemLinks = new ArrayList<>();

            System.out.println("\n**********\nPAGE NUMBER: " + pageIterator + "\n**********");

            for (WebElement item : items) {
                try {
                    String productTitle = item.findElement(By.cssSelector(titleClassName)).getText();
                    //get the products details.

                    if (validProduct(productTitle)) { //validates that the product is in fact a product

                        final String productBrand = getBrand(productTitle);
                        final String productModel = productBrand == "Samsung" ? getModelSamsung(productTitle) : getModelApple(productTitle);
                        if (productModel == "")
                            continue; // don't save the product. It is unknown.

                        final String productPrice = Product.renderPrice(item.findElement(By.className(priceClassName)).getText());
                        final String productImgURL = item.findElement(By.className(imgUrlClassName)).getAttribute("src");
                        String productUrl = item.findElement(By.cssSelector(urlClassName)).getAttribute("href");
                        productUrl = productUrl.substring(0, productUrl.indexOf('?')); //might need to exclude products with /p/ path link
                        final String productColor = getColor(productTitle);
                        final int productStorageSize = getStorageSize(productTitle);
                        final float productDisplaySize = productBrand == "Samsung" ? getDisplaySizeSamsung(productModel) : getDisplaySizeApple(productTitle);

                        itemLinks.add(productUrl);
                        Phone newPhone = new Phone(productBrand, productModel, productColor, productStorageSize, productDisplaySize, productImgURL);
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
     * Scrapes the web for all the phone models across all brands.
     * @return a list of phones belonging to all brands
     */
    @Override
    public List<List<Phone>> scrapeAllPhonesAllBrands() {

        List<List<Phone>> listOfAllProductsAllBrands = new ArrayList<>();

        for (int brandIterator = 0; brandIterator < linksToProducts.size(); brandIterator++) { //go through each brand (i.e., Apple))

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

