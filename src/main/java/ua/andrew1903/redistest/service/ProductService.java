package ua.andrew1903.redistest.service;

import lombok.RequiredArgsConstructor;
import org.jooq.lambda.Seq;
import org.springframework.stereotype.Service;
import ua.andrew1903.redistest.model.Product;
import ua.andrew1903.redistest.parser.ParamToParse;
import ua.andrew1903.redistest.parser.Store;
import ua.andrew1903.redistest.parser.StoreParser;
import ua.andrew1903.redistest.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final List<StoreParser> parsers;
    private final ProductRepository repository;

    public Product<?> getProduct(Long productId, Store parseFrom, ParamToParse parseParam) {
        return repository.findById(productId).orElseGet(() -> parseAndSave(productId, parseFrom, parseParam));
    }

    private Product<?> parseAndSave(Long productId, Store parseFrom, ParamToParse parseParam) {
        return repository.save(parse(productId, parseFrom, parseParam));
    }

    private Product<?> parse(Long id, Store parseFrom, ParamToParse parseParam) {
        StoreParser storeParser = Seq.seq(parsers)
                .findFirst(parser -> parser.getSite() == parseFrom)
                .orElseThrow(RuntimeException::new);

        return storeParser.parse(id, parseParam);
    }
}
