package ua.andrew1903.redistest.parser;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import ua.andrew1903.redistest.model.Product;

import java.util.regex.Pattern;

@Component
public class EbayParser implements SiteParser {
    @Override
    public Product parse(Long id) {
        String url = getURL().formatted(id, id);

        double price = parse(url);

        return new Product(id, price);
    }

    @SneakyThrows
    private double parse(String url) {
        Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .get();

        String matched = extractData(doc);

        return Double.parseDouble(matched);
    }

    private String extractData(Document doc) {
        String text = doc.select(getDataSelector()).get(0).text();

        return getDataPattern()
                .matcher(text)
                .results()
                .findFirst()
                .orElseThrow()
                .group();
    }

    @Override
    public String getURL() {
        return "https://www.ebay.com/itm/%d";
    }

    @Override
    public Site getSite() {
        return Site.EBAY;
    }

    @Override
    public Pattern getDataPattern() {
        return Pattern.compile("\\b\\d+\\.\\d+\\b");
    }

    @Override
    public String getDataSelector() {
        return "div.x-price-primary>span";
    }
}
