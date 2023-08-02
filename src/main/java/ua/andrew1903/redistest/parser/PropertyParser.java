package ua.andrew1903.redistest.parser;

import org.jsoup.nodes.Document;
import ua.andrew1903.redistest.model.Product;

import java.util.regex.Pattern;

public interface PropertyParser {
    Pattern getDataPattern();

    String getDataSelector();

    ParamToParse getParam();

    Product<?> parse(Long id, String url);

    default String extractData(Document doc) {
        String text = doc.select(getDataSelector()).get(0).text().replaceAll(",", "");

        return getDataPattern()
                .matcher(text)
                .results()
                .findFirst()
                .orElseThrow()
                .group();
    }
}
