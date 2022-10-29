package com.qaprosoft.carina.demo.api.myTestApi;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.RequestTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;

@Endpoint(url = "https://jsonplaceholder.typicode.com/posts", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/myTestApi/_post/rq.json")
@ResponseTemplatePath(path = "api/myTestApi/_post/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
public class PostMethod extends AbstractApiMethodV2 {

    public PostMethod() {
    }
}
