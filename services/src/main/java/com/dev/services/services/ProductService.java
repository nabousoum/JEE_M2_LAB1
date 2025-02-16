package com.dev.services.services;

import com.dev.services.dao.IProductRepository;
import com.dev.services.dto.ProductDto;
import com.dev.services.entities.Product;
import com.dev.services.exception.EntityNotFoundException;
import com.dev.services.mapping.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDto getProduct(int id) {
        return productMapper.toProductDto(productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id)));
    }

    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.fromProduct(productDto);
        return productMapper.toProductDto(productRepository.save(product));
    }

    @Transactional
    public ProductDto updateProduct(int id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
        product.setNom(productDto.getNom());
        product.setQtStock(productDto.getQtStock());
        return productMapper.toProductDto(productRepository.save(product));
    }

    @Transactional
    public void deleteProduct(int id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }
}
