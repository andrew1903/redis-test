package ua.andrew1903.redistest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("Product")
@AllArgsConstructor
public class Product implements Serializable {
    private Long id;
    private double price;
}
