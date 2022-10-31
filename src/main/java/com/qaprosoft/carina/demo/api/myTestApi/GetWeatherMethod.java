package com.qaprosoft.carina.demo.api.myTestApi;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "https://api.openweathermap.org/data/2.5/weather?q=Kyiv&appid=ddcd7c29b69a543c41d1c001edb3c319",
        methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/myTestApi/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetWeatherMethod extends AbstractApiMethodV2 {

    public GetWeatherMethod() {
    }
}

