package com.yapily.marvelcomics.models;

import lombok.Data;

import java.util.List;

@Data
public class CharacterDataContainer {

    private Integer offset;
    private Integer limit;
    private Integer total;
    private List<MarvelCharacter> results;

}
