package com.ebilag.ebilag.System.restemplate;

import com.ebilag.ebilag.System.model.brreg.Enhet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Component
public class BrregRestTemplate {

    private static final String BASE_URL = "https://data.brreg.no/enhetsregisteret/api";
    private final RestTemplate restTemplate;

    @Autowired
    public BrregRestTemplate(@Qualifier("restemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Enhet getCompanyByOrgNumber(String orgnr) {
        String url = BASE_URL + "/enheter/" + orgnr;

        var response = restTemplate.exchange(url, HttpMethod.GET, null, Enhet.class);

        return Objects.requireNonNull(response.getBody());
    }

    public Enhet getAllCompanies(int page, int size) {
        String url = BASE_URL + "/enheter";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("page", page)
                .queryParam("size", size);

        var response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, Enhet.class);

        return Objects.requireNonNull(response.getBody());
    }
}
