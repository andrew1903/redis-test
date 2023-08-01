package ua.andrew1903.redistest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.andrew1903.redistest.model.Product;
import ua.andrew1903.redistest.parser.Site;
import ua.andrew1903.redistest.service.ProductService;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final ProductService service;

    @GetMapping("/api/products/{productId}")
    public Product getProduct(@PathVariable Long productId, @RequestParam Site parseFrom) {
        return service.getProduct(productId, parseFrom);
    }
}
