package com.liro.applications.myapplication.Utils;

import java.util.UUID;

/**
 * Created by HeslarR on 4/10/2017.
 */

public class RandomStringGuid {
    public static String NewGuid(){
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }
}
