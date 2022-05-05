package com.kumu.trent.config;

import com.kumu.trent.vendor.android.java.security.SecurityLayer;



public class Url extends SecurityLayer {
    public static final String PRODUCTION_URL = decrypt("https://itunes.apple.com/");
    public static final String DEBUG_URL = decrypt("https://itunes.apple.com/");

    public static final String APP = App.production ? PRODUCTION_URL : DEBUG_URL;

    public static final String getSearch(){return "/search";}
    public static final String getLookup(){return "/lookup";}
}
