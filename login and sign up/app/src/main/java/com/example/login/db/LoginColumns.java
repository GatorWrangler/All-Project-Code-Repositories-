package com.example.login.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * login 中的常量
 *
 * @author x8.phoenix
 */
public final class LoginColumns implements BaseColumns {
    static final String TABLE_NAME = "login";
    //public static final String DEFAULT_SORT_ORDER = "level desc";
    static final String USER_NAME = "user_name";
    static final String FIRST_NAME = "first_name";
    static final String LAST_NAME = "last_name";
    static final String USER_EMAIL = "user_email";
    static final String USER_PASSWORD = "user_password";
}