package com.qaprosoft.carina.demo;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.APIMethodPoller;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.Priority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority;
import com.qaprosoft.carina.demo.api.DeleteUserMethod;
import com.qaprosoft.carina.demo.api.GetUserMethods;
import com.qaprosoft.carina.demo.api.myTestApi.PostUserMethod;
import com.qaprosoft.carina.demo.api.myTestApi.GetCatMethod;
import com.qaprosoft.carina.demo.api.myTestApi.GetUserMethod;
import com.qaprosoft.carina.demo.api.myTestApi.GetWeatherMethod;
import com.qaprosoft.carina.demo.api.myTestApi.PostMethod;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyApiTest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test(description = "get info about wheather")
    @MethodOwner(owner = "chekmezov gleb")
    public void testGetWeather() {
        GetWeatherMethod getWeatherMethod = new GetWeatherMethod();
        getWeatherMethod.callAPIExpectSuccess();
        getWeatherMethod.validateResponseAgainstSchema("api/myTestApi/_getWeather/rs.schema");
    }

    @Test(description = "get info about cat")
    @MethodOwner(owner = "chekmezov gleb")
    public void testGetCat() {
        GetCatMethod getCatMethod = new GetCatMethod();
        getCatMethod.callAPIExpectSuccess();
        getCatMethod.validateResponseAgainstSchema("api/myTestApi/_getCat/rs.schema");
    }

    @Test(description = "get info about user")
    @MethodOwner(owner = "chekmezov gleb")
    public void testGetUser() {
        GetUserMethod getUserMethod = new GetUserMethod();
        getUserMethod.callAPIExpectSuccess();
        getUserMethod.validateResponseAgainstSchema("api/myTestApi/_getUser/rs.schema");
    }

    @Test(description = "post info")
    @MethodOwner(owner = "chekmezov gleb")
    public void testPostMethod() {
        PostMethod postMethod = new PostMethod();
        postMethod.setProperties("api/myTestApi/post.properties");
        postMethod.callAPI();
        postMethod.validateResponse();
    }

    @Test(description = "post info about user")
    @MethodOwner(owner = "chekmezov gleb")
    public void testPostUserMethod() {
        PostUserMethod postUserMethod = new PostUserMethod();
        postUserMethod.setProperties("api/myTestApi/postUser.properties");
        postUserMethod.callAPI();
        postUserMethod.validateResponse();
    }



}
