package com.yapily.marvelcomics.services;

import com.yapily.marvelcomics.models.MarvelCharacter;
import com.yapily.marvelcomics.models.CharacterDataWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CharacterService {

    private final RestTemplate restTemplate;

    @Autowired
    public CharacterService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("characterIds")
    public List<Integer> getCharacterIds() {

        log.info("Character ID Cache Running.");

        CharacterDataWrapper firstResponse = makeGetRequest("/characters?limit=100");
        List<MarvelCharacter> characters = new ArrayList<>(firstResponse.getData().getResults());

        int total = firstResponse.getData().getTotal();

        for (int offset = 100; offset < total; offset += 100) {
            characters.addAll(makeGetRequest("/characters?limit=100&offset=" + offset).getData().getResults());
        }

        log.info("Character ID Cache Finished.");

        return characters.stream()
                .map(MarvelCharacter::getId)
                .collect(Collectors.toList());
    }

    public MarvelCharacter getCharacterById(final Integer id) {
        CharacterDataWrapper response = makeGetRequest("/characters/" + id);
        return response.getData().getResults().get(0);
    }

    private CharacterDataWrapper makeGetRequest(final String url) {
        CharacterDataWrapper response = restTemplate.getForObject(url, CharacterDataWrapper.class);
        assert response != null;
        return response;
    }

}
