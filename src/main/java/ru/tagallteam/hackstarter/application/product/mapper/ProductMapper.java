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
        productDto.setNumber_of_product(product.getNumber_of_products());
        productDto.setType(product.getType());
        productDto.setFile_id(product.getFile().getId());
        return productDto;
    }



}
