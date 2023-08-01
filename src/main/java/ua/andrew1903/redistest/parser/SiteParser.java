package ua.andrew1903.redistest.parser;

import ua.andrew1903.redistest.model.Product;

import java.util.regex.Pattern;

public interface SiteParser {
    Product parse(Long productId);

    String getURL();

    Site getSite();

    Pattern getDataPattern();

    String getDataSelector();
}
