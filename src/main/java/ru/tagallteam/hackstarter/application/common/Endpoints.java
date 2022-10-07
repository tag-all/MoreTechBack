package ru.tagallteam.hackstarter.application.common;

public interface Endpoints {
    interface AuthService {
        String LOGIN = "/login";
        String REGISTRATION = "/registration";
        String LOGOUT = "/logout";
        String UPDATE_TOKEN = "/token/update";
    }

    interface UserService {
        String PROFILE = "/profile";
    }

    interface ProductService {
        String PRODUCTS = "/products";

        String PRODUCT = "/products/{productId}";

        String PRODUCT_SEARCH = "/products/search";

        String PRODUCT_BUY = "/products/{productId}/buy";
    }
}
