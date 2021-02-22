package com.yapily.marvelcomics.models;

import lombok.Data;

@Data
public class MarvelCharacter {

    private Integer id;
    private String name;
    private String description;
    private MarvelCharacterThumbnail thumbnail;

}
