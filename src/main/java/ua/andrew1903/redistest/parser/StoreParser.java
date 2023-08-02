package ua.andrew1903.redistest.parser;

import ua.andrew1903.redistest.model.Product;

public interface StoreParser {
    Product<?> parse(Long productId, ParamToParse param);

    String getURL();

    Store getSite();
}
