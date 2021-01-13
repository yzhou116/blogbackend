package com.yizhou.yiblog.util;

public interface Constrants {
    int DEFAULT_SIZE = 30;

    interface User {
        String ROLE_ADMIN = "role_admin";
        String ROLE_NORMAL = "role_normal";
        String DEFAULT_AVATAR = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg";
        String DEFAULT_STATE = "1";
        String KEY_CAPTCHA_CONTENT = "key_captcha_content_";
        String KEY_EMAIL_CODE_CONTENT = "key_email_code_content_";
        String KEY_EMAIL_SEND_IP = "KEY_EMAIL_SEND_IP_";
        String KEY_EMAIL_SEND_ADDRESS = "KEY_EMAIL_SEND_ADDRESS_";
        String KEY_TOKEN = "key_token_";
        String KEY_TOKEN_COOKIE = "blog_token";
    }

    interface Settings {
        String HAD_MANAGER_ACCOUNT_INIT = "had_manager_account_init";
    }

    interface Page {
        int DEFAULT_PAGE = 1;
        int MIN_SIZE = 5;
    }

    interface TimeValue {
        int MIN_1 = 60 * 60;
        int HOUR_1 = MIN_1 * 60;
        int DAY_1 = HOUR_1 * 24;
        int WEEK_1 = DAY_1 * 7;
        int MONTH_1 = 30 * DAY_1;

    }
}
