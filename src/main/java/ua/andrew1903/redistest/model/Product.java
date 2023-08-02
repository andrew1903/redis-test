package ua.andrew1903.redistest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RedisHash(value = "Product", timeToLive = 200L)
public class Product<T> implements Serializable {
    private Long id;
    private T value;
}
