package com.yapily.marvelcomics.config;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.time.Instant;

public class MarvelApiRequestInterceptor implements ClientHttpRequestInterceptor {

    private final String publicApiKey;
    private final String privateApiKey;

    public MarvelApiRequestInterceptor(final String publicApiKey, final String privateApikey) {
        this.publicApiKey = publicApiKey;
        this.privateApiKey = privateApikey;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        String timestamp = Instant.now().toString();
        String hash = DigestUtils.md5Hex(timestamp + privateApiKey + publicApiKey);

        URI uri = UriComponentsBuilder.fromHttpRequest(request)
                .queryParam("apikey", publicApiKey)
                .queryParam("ts", timestamp)
                .queryParam("hash", hash)
                .build().toUri();

        HttpRequest modifiedRequest = new HttpRequestWrapper(request) {
            @Override
            public URI getURI() {
                return uri;
            }
        };
        return execution.execute(modifiedRequest, body);
    }

}
