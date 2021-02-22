package com.yapily.marvelcomics.models;

import lombok.Data;

@Data
public class CharacterDataWrapper {

    private String code;
    private String status;
    private CharacterDataContainer data;

}
