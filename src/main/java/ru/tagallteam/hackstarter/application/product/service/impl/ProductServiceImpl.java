package ru.tagallteam.hackstarter.application.product.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tagallteam.hackstarter.application.product.domain.Product;
import ru.tagallteam.hackstarter.application.product.domain.ProductRepository;
import ru.tagallteam.hackstarter.application.product.mapper.ProductMapper;
import ru.tagallteam.hackstarter.application.product.model.ProductDto;
import ru.tagallteam.hackstarter.application.product.service.ProductService;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::convertToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(ErrorDescriptor.PRODUCT_NOT_FOUND::applicationException);
        return productMapper.convertToProductDto(product);
    }

    @Override
    public void buyProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ErrorDescriptor.PRODUCT_NOT_FOUND::applicationException);
        ErrorDescriptor.PRODUCT_OUT_OF_STOCK.throwIsFalse(product.getNumberOfProducts() > 0);
        product.setNumberOfProducts(product.getNumberOfProducts() - 1);
        product.setPurchased(product.getPurchased() + 1);
        productRepository.save(product);
    }

}
