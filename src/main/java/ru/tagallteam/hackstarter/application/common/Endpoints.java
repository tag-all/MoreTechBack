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

        String ACHIEVEMENTS_USER_GET = "/achievements/users/get/{userId}";
        String ACHIEVEMENTS_USER_NOT_GET = "/achievements/users/notget/{userId}";
    }

    interface EventService {
        String EVENTS = "/events";

        String EVENTS_USER = "/events/users/{userId}";

        String EVENT_FEEDBACK = "/event";

        String SIGN_UP_FOR_EVENT = "/events/{eventId}";

    }

    interface TransferService {
        String TRANSFER = "/transfer/users/{userId}";

        String TRANSFER_STATUS = "/transfers/{txHash}";

        String TRANSFER_INFO = "/transfers/{txHash}/info";
    }

    interface FileService {
        String FILE = "/files/{uuid}";
    }

    interface ClanService {

        String CLANS = "/clans";

        String CLANS_USER = "/clans/user/{userId}";

        String CLAN_NFT = "/clan/nft/{nftId}";

        String CLAN_NEW_USER = "/clans/{clanId}";

    }

    interface NftService{
        String USER_NFT = "/ntfs/users/{userId}";

        String NFT_INFO_WITH_USER = "/ntfs/{tokenId}";
    }

}

