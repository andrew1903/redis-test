package ua.andrew1903.redistest.parser.ebay;

import lombok.RequiredArgsConstructor;
import org.jooq.lambda.Seq;
import org.springframework.stereotype.Component;
import ua.andrew1903.redistest.model.Product;
import ua.andrew1903.redistest.parser.PropertyParser;
import ua.andrew1903.redistest.parser.ParamToParse;
import ua.andrew1903.redistest.parser.Store;
import ua.andrew1903.redistest.parser.StoreParser;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EbayParser implements StoreParser {
    private final List<PropertyParser> propertyParsers;

    @Override
    public Product<?> parse(Long id, ParamToParse param) {
        String url = getURL().formatted(id, id);
        PropertyParser propertyParser = Seq.seq(propertyParsers)
                .findFirst(property -> property.getParam() == param)
                .orElseThrow(RuntimeException::new);
        return propertyParser.parse(id, url);
    }

    @Override
    public String getURL() {
        return "https://www.ebay.com/itm/%d";
    }

    @Override
    public Store getSite() {
        return Store.EBAY;
    }
}
