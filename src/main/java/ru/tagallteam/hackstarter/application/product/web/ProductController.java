package ru.tagallteam.hackstarter.application.product.web;

import io.swagger.annotations.ApiOperation;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tagallteam.hackstarter.application.common.Endpoints;
import ru.tagallteam.hackstarter.application.product.model.ProductDto;
import ru.tagallteam.hackstarter.application.product.service.ProductService;
import ru.tagallteam.hackstarter.errors.annotations.SystemError;

@RestController
@RequiredArgsConstructor
public class ProductController {

    @ApiOperation(value = "Полученеи товаров",
            notes = "Получение товаров и информации о них")
    @SystemError
    @GetMapping(Endpoints.ProductService.PRODUCTS)
    public List<ProductDto> products() {
        return Collections.emptyList();
    }

    @ApiOperation(value = "Полученеи товара",
            notes = "Получение товара по ИД")
    @SystemError
    @GetMapping(Endpoints.ProductService.PRODUCT)
    public ProductDto productById(@PathVariable Long productId) {
        return new ProductDto();
    }

    @ApiOperation(value = "Полученеи товаров",
            notes = "Получение товаров по фильтру")
    @SystemError
    @PostMapping(Endpoints.ProductService.PRODUCT_SEARCH)
    public List<ProductDto> productsSearch() {
        return Collections.emptyList();
    }

    @ApiOperation(value = "Покупка товара",
            notes = "Покупка товара")
    @SystemError
    @PostMapping(Endpoints.ProductService.PRODUCT_BUY)
    public void productsBuy(@PathVariable Long productId) {
    }
}
