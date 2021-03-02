package com.mindorks.framework.mvvm.custom.remote.volley.helpers;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import androidx.annotation.NonNull;

public class VolleyExceptions {

    private static VolleyExceptions volleyExceptions;

    @NonNull
    public String VOLLEY_ERROR_STRING_CONNECTION_TIMEOUT = "Connection Timeout!";
    @NonNull
    public String VOLLEY_ERROR_STRING_UNAUTHORIZED = "You are not Authorized, Please Login Again!";
    @NonNull
    public String VOLLEY_ERROR_STRING_CHECK_DATA = "Please Check your data and Try Again!";
    @NonNull
    public String VOLLEY_ERROR_STRING_FORBIDDEN_ACCESS = "Forbidden Error, You cannot access this!";
    @NonNull
    public String VOLLEY_ERROR_STRING_PAGE_NOT_FOUND = "Page not found or Missing Resources!";
    @NonNull
    public String VOLLEY_ERROR_STRING_NETWORK = "Network Failure, Please Try Again!";
    @NonNull
    public String VOLLEY_ERROR_STRING_PARSING = "Parsing Error,Unable to Parse Request!";
    @NonNull
    public String VOLLEY_ERROR_STRING_ELSE = "Check Connection and Try Again!";
    @NonNull
    public String VOLLEY_ERROR_STRING_WRONG_METHOD = "Request Method not allowed!";

    public static VolleyExceptions get() {
        if (volleyExceptions == null) {
            volleyExceptions = new VolleyExceptions();
        }
        return volleyExceptions;
    }

    public void setVolleyErrors(@NonNull String for400, @NonNull String for401, @NonNull String for403, @NonNull String for404, @NonNull String forElse,
                                @NonNull String forConnectionTimeOut, @NonNull String forNetworkError, @NonNull String forParsing, @NonNull String forMethod) {
        VOLLEY_ERROR_STRING_CONNECTION_TIMEOUT = forConnectionTimeOut;
        VOLLEY_ERROR_STRING_UNAUTHORIZED = for401;
        VOLLEY_ERROR_STRING_CHECK_DATA = for400;
        VOLLEY_ERROR_STRING_FORBIDDEN_ACCESS = for403;
        VOLLEY_ERROR_STRING_NETWORK = forNetworkError;
        VOLLEY_ERROR_STRING_PAGE_NOT_FOUND = for404;
        VOLLEY_ERROR_STRING_ELSE = forElse;
        VOLLEY_ERROR_STRING_PARSING = forParsing;
        VOLLEY_ERROR_STRING_WRONG_METHOD = forMethod;
    }

    /* Returns error code from volley error */
    public int getVolleyErrorStatusCode(VolleyError error) {
        if (error != null && error.networkResponse != null) {
            return error.networkResponse.statusCode;
        }
        return 0;
    }

    /* Handling all error */
    public String getVolleyErrorMessage(VolleyError error) {
        if (error != null && error.networkResponse != null) {
            switch (error.networkResponse.statusCode) {
                case 400:
                    return VOLLEY_ERROR_STRING_CHECK_DATA;
                case 401:
                    return VOLLEY_ERROR_STRING_UNAUTHORIZED;
                case 403:
                    return VOLLEY_ERROR_STRING_FORBIDDEN_ACCESS;
                case 404:
                    return VOLLEY_ERROR_STRING_PAGE_NOT_FOUND;
                case 405:
                    return VOLLEY_ERROR_STRING_PAGE_NOT_FOUND;
                default:
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        return VOLLEY_ERROR_STRING_CONNECTION_TIMEOUT;
                    } else if (error instanceof NetworkError) {
                        return VOLLEY_ERROR_STRING_NETWORK;
                    } else if (error instanceof ParseError) {
                        return VOLLEY_ERROR_STRING_PARSING;
                    } else {
                        return VOLLEY_ERROR_STRING_ELSE;
                    }
            }
        }
        return "";
    }

    public int getVolleyErrorStatusCode(Exception e) {
        return getVolleyErrorStatusCode(new VolleyError(e.getMessage()));
    }

    public String getVolleyErrorMessage(Exception e) {
        return getVolleyErrorMessage(new VolleyError(e.getMessage()));
    }
}
