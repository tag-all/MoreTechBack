package ru.tagallteam.hackstarter.application.product.mapper;

import org.springframework.stereotype.Component;
import ru.tagallteam.hackstarter.application.product.domain.Product;
import ru.tagallteam.hackstarter.application.product.model.ProductDto;

@Component
public class ProductMapper {

    public ProductDto convertToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setPurchased(product.getPurchased());
        productDto.setNumberOfProducts(product.getNumberOfProducts());
        productDto.setType(product.getType());
        productDto.setFileId(product.getFile().getId());
        return productDto;
    }

}
