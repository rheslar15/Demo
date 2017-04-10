package com.liro.applications.myapplication.Services;

import com.liro.applications.myapplication.Models.AuthenticationRes;
import com.liro.applications.myapplication.Models.Result;
import com.liro.applications.myapplication.Networking.ApplicationConstants;
import com.liro.applications.myapplication.Utils.DateUtil;
import com.liro.applications.myapplication.Utils.RandomStringGuid;

import java.util.Date;

/**
 * Created by HeslarR on 4/10/2017.
 */
public class MockRestService {
    MockRestService(){}

    public static AuthenticationRes GetUser(String UserName, String Password)
    {
//        HttpUtils.get("api\\AuthenticationReq", null, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                // If the response is JSONObject instead of expected JSONArray
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
//                // Pull out the first event on the public timeline
//                //JSONObject firstEvent = timeline.get(0);
//               // String tweetText = firstEvent.getString("text");
//
//                // Do something with the response
//                //System.out.println(tweetText);
//            }
//        });

        AuthenticationRes res = new AuthenticationRes();
        res.result = new Result(ApplicationConstants.UserNotFoundString, ApplicationConstants.FailString, ApplicationConstants.NotFound);
        res.firstName = "";
        res.lastName = "";
        res.token = "";
        if(UserName != null && !UserName.isEmpty()) {
            if (UserName == "admin") {
                res.firstName = UserName;
                res.lastName = UserName;
            }
        }
        if(Password != null && !Password.isEmpty()) {

            Date newDate = new Date();
            newDate = DateUtil.addDays(newDate, 1);

            if (Password == "admin") {
                res.result.message = ApplicationConstants.LoginOKString;
                res.result.type = ApplicationConstants.NoErrorString;
                res.result.code = ApplicationConstants.NoError;
                res.DBVersion = "";
                res.expiryTime = newDate;
                res.token = RandomStringGuid.NewGuid();
            }
        }




        return res;
    }
}
