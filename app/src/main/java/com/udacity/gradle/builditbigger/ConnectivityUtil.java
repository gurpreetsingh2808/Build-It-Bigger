package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * <code>ConnectivityUtil</code> provides various utility methods on checking internet connectivity
 *
 * @author Gurpreet Singh
 * @since  26-Sept-2016
 */
public class ConnectivityUtil {

    // Check if internet connection is available at the moment or not
    public boolean isConnected(Context context) {

        ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
