package ua.andrew1903.redistest.service;

import lombok.RequiredArgsConstructor;
import org.jooq.lambda.Seq;
import org.springframework.stereotype.Service;
import ua.andrew1903.redistest.model.Product;
import ua.andrew1903.redistest.parser.Site;
import ua.andrew1903.redistest.parser.SiteParser;
import ua.andrew1903.redistest.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final List<SiteParser> parsers;
    private final ProductRepository repository;

    public Product getProduct(Long productId, Site parseFrom) {
        return repository.findById(productId).orElse(createNew(productId, parseFrom));
    }

    private Product createNew(Long productId, Site parseFrom) {
        return repository.save(parse(productId, parseFrom));
    }

    private Product parse(Long id, Site parseFrom) {
        SiteParser siteParser = Seq.seq(parsers)
                .findFirst(parser -> parser.getSite() == parseFrom)
                .orElseThrow(RuntimeException::new);

        return siteParser.parse(id);
    }
}
