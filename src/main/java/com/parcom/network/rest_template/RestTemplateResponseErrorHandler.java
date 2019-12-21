package com.parcom.network.rest_template;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;


public class RestTemplateResponseErrorHandler
        implements ResponseErrorHandler {



    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {
        return (httpResponse.getStatusCode().series() == CLIENT_ERROR
                                || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
    {
        throw new RuntimeException("Remote procedure call error");
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse httpResponse) {
      throw new RuntimeException(String.format("Remote procedure call error. For %s %s",method,url));

    }
}