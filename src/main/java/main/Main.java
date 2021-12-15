package main;

import dao.AppConfig;
import dao.Phone;
import scraper.WebScraper;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    long scrapeDelay;

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        List<WebScraper> scraperManagerList = appConfig.scraperManager().getScraperList();
//
//        scraperManagerList.get(0).run(); //run ebay scraper
        scraperManagerList.get(1).run(); //run techinbasket scraper
        appConfig.scraperManager().getScraperList().get(1).getDriver().close();
        appConfig.scraperManager().getScraperList().get(0).getDriver().close();


    }

    //DELETE THIS.
    public static String getData() {
        String s = "";
        File file = new File("C:\\Users\\omerk\\samsung.txt");

        try {
            Scanner input = new Scanner(file);

            while (input.hasNext()) {
                String model = input.nextLine();
                s += "else if(tmp.contains(\"" + model.toLowerCase() + "\"))\nreturn \"" + model + "\";\n";
            }
        } catch (Exception e) {

        }
        return s;
    }
}