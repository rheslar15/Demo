package com.liro.applications.myapplication.Models;

/**
 * Created by HeslarR on 4/10/2017.
 */

public class Result {
    public Result() {
        this.message = "";
        this.code = -1;
        this.type = "";
    }

    public Result(String message, String type, int code) {
        this.message = message;
        this.type = type;
        this.code = code;
    }

    public String message;
    public String type;
    public int code;


}
