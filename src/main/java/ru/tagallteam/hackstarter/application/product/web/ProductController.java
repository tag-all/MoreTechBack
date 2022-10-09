package ru.tagallteam.hackstarter.application.product.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tagallteam.hackstarter.application.common.Endpoints;
import ru.tagallteam.hackstarter.application.product.model.ProductDto;
import ru.tagallteam.hackstarter.application.product.service.impl.ProductServiceImpl;
import ru.tagallteam.hackstarter.errors.annotations.SystemError;

import java.util.List;

@Api(tags = "Работа с товарами")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @ApiOperation(value = "Получение товаров",
            notes = "Получение товаров и информации о них")
    @SystemError
    @GetMapping(Endpoints.ProductService.PRODUCTS)
    public List<ProductDto> products() {
        return productService.getAllProducts();
    }

    @ApiOperation(value = "Получение товара",
            notes = "Получение товара по ИД")
    @SystemError
    @GetMapping(Endpoints.ProductService.PRODUCT)
    public ProductDto productById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @ApiOperation(value = "Покупка товара",
            notes = "Покупка товара")
    @SystemError
    @PostMapping(Endpoints.ProductService.PRODUCT_BUY)
    public void productsBuy(@PathVariable Long productId) {
        productService.buyProduct(productId);
    }
}
