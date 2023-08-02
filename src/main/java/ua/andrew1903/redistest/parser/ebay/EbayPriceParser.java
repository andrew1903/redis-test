package ua.andrew1903.redistest.parser.ebay;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import ua.andrew1903.redistest.model.Product;
import ua.andrew1903.redistest.parser.PropertyParser;
import ua.andrew1903.redistest.parser.ParamToParse;

import java.util.regex.Pattern;

@Component
public class EbayPriceParser implements PropertyParser {
    @Override
    public Pattern getDataPattern() {
        return Pattern.compile("\\b[0-9,]+\\.\\d+\\b");
    }

    @Override
    public String getDataSelector() {
        return "div.x-price-primary>span";
    }

    @Override
    public ParamToParse getParam() {
        return ParamToParse.PRICE;
    }

    @Override
    @SneakyThrows
    public Product<Double> parse(Long id, String url) {
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla")
                .get();
        String matched = extractData(doc);

        return new Product<>(id, Double.parseDouble(matched));
    }
}
