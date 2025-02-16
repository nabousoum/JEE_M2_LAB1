package com.dev.services.mapping;

import com.dev.services.dto.ProductDto;
import com.dev.services.entities.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    ProductDto toProductDto(Product product);
    Product fromProduct(ProductDto produitDto);
}
