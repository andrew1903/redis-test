package ua.andrew1903.redistest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.andrew1903.redistest.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
