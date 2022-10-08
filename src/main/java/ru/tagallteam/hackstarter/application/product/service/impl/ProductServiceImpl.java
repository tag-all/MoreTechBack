package ru.tagallteam.hackstarter.application.product.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tagallteam.hackstarter.application.product.domain.Product;
import ru.tagallteam.hackstarter.application.product.domain.ProductRepository;
import ru.tagallteam.hackstarter.application.product.mapper.ProductMapper;
import ru.tagallteam.hackstarter.application.product.model.ProductDto;
import ru.tagallteam.hackstarter.application.product.service.ProductService;

import java.util.ArrayList;
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
    public ProductDto getProductById(Long id) {
        return productMapper.convertToProductDto(productRepository.getReferenceById(id));
    }

    @Override
    public void buyProduct(Long id) {

    }

}
