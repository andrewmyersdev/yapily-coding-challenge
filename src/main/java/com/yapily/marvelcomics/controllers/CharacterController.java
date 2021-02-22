package com.yapily.marvelcomics.controllers;

import com.yapily.marvelcomics.models.MarvelCharacter;
import com.yapily.marvelcomics.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @EventListener(classes = ApplicationStartedEvent.class)
    public void populateCache() {
        characterService.getCharacterIds();
    }

    @GetMapping
    public @ResponseBody List<Integer> getCharacterIds() {
        return characterService.getCharacterIds();
    }

    @GetMapping("/{id}")
    public @ResponseBody MarvelCharacter getCharacterById(@PathVariable("id") Integer id) {
        return characterService.getCharacterById(id);
    }

}
