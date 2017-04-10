package com.liro.applications.myapplication.Models;

import java.util.Date;

/**
 * Created by HeslarR on 4/10/2017.
 */

public class AuthenticationRes implements IResult {
    public Result result;
    public String firstName;
    public String lastName;
    public String token;
    public Date expiryTime;
    // added 1/21/2016
    public String DBVersion;
    public AuthenticationRes()
    {
        result = new Result();
    }
}
