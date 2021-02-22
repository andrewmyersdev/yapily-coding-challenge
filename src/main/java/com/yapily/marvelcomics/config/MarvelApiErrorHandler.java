package com.yapily.marvelcomics.config;

import com.yapily.marvelcomics.exceptions.NotFoundException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class MarvelApiErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getRawStatusCode() == 404) {
            throw new NotFoundException();
        }
    }

}
