package com.qaprosoft.carina.demo.api.myTestApi;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.RequestTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;

@Endpoint(url = "https://reqres.in/api/users", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/myTestApi/_postUser/rq.json")
@ResponseTemplatePath(path = "api/myTestApi/_postUser/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
public class PostUserMethod extends AbstractApiMethodV2 {
    public PostUserMethod() {
    }
}
