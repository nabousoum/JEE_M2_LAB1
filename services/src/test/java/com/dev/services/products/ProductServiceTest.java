package com.dev.services.products;

import com.dev.services.dto.ProductDto;
import com.dev.services.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductService productService;

    @Test
    void getProducts() {
        when(productService.getProducts()).thenReturn(List.of(new ProductDto()));
        List<ProductDto> productList = productService.getProducts();

        Assertions.assertEquals(1, productList.size());
    }

    @Test
    void getProduct() {
        when(productService.getProduct(anyInt())).thenReturn(new ProductDto());
        ProductDto product = productService.getProduct(1);

        Assertions.assertNotNull(product);
    }

    @Test
    void createProduct() {
        ProductDto product = new ProductDto();
        product.setNom("Laptop");

        when(productService.createProduct(any())).thenReturn(product);
        ProductDto productSaved = productService.createProduct(product);

        Assertions.assertNotNull(productSaved);
        Assertions.assertEquals("Laptop", productSaved.getNom());
    }

    @Test
    void updateProduct() {
        ProductDto product = new ProductDto();
        product.setNom("Smartphone");

        when(productService.updateProduct(anyInt(), any())).thenReturn(product);
        ProductDto productUpdated = productService.updateProduct(2, product);

        Assertions.assertEquals("Smartphone", productUpdated.getNom());
    }

    @Test
    void deleteProduct() {
        doNothing().when(productService).deleteProduct(isA(Integer.class));
        productService.deleteProduct(7);

        verify(productService, times(1)).deleteProduct(7);
    }
}
