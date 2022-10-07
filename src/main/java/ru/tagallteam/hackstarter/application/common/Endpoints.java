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

        String USERS_PROFILES = "/profiles";

        String USER_PROFILE = "/profiles/users/{userId}";
    }

    interface ProductService {
        String PRODUCTS = "/products";

        String PRODUCT = "/products/{productId}";

        String PRODUCT_BUY = "/products/{productId}/buy";
    }

    interface ActivityService {
        String ACTIVITIES = "/activities";

        String USER_ACTIVITIES = "/users/{userId}/activities";
    }

    interface AchievementService {
        String ACHIEVEMENTS = "/achievements";

        String ACHIEVEMENTS_USER = "/achievements/users/{userId}";
    }

    interface EventService {
        String EVENTS = "/events";

        String EVENTS_USER = "/events/users/{userId}";

        String EVENT_FEEDBACK = "/event";

        String SIGN_UP_FOR_EVENT = "/events/{eventId}";

    }

    interface PaymentService {
        String TRANSFER = "/transfer/users/{userId}";
    }

    interface FileService {
        String FILE = "/files/{uuid}";
    }

}
